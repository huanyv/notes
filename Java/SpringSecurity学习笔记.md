# SpringSecurity学习笔记

## 目录

[TOC]

## 1. Hello world

* 引入依赖
* 默认用户名`user`
* springboot启动时会生成一个密码

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
## 2. web权限方案

### 2.1 用户认证

* 配置文件设置用户名密码

```yaml
spring:
  security:
    user:
      name: admin
      password: 111111
```

* 配置类用户名密码

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String password = new BCryptPasswordEncoder().encode("222222");
        auth.inMemoryAuthentication()
               .withUser("admin").password(password).roles("admin");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

* 数据库查询
    1. 配置处理登录的Sevice和加密方式
    1. 实现`UserDetailsService`
    2. 实现`loadUserByUsername(String usrename)`方法

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}

```

```java
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<com.example.pojo.User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        com.example.pojo.User loginUser = userMapper.selectOne(queryWrapper);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        String password = new BCryptPasswordEncoder().encode(loginUser.getPassword());

        return new User(loginUser.getUsername(), password, authorities);
    }
}
```

### 2.2 更改登录页面

* 重写`configure(HttpSecurity http)`方法
* `http.loginPage("")`设置登录页面
* `http.loginProcessingUrl("")`设置登录POST请求地址
* `http.defaultSuccessUrl("")`登录成功后重定向的页面
* `http.authorizeRequests("")`允许基于使用HttpServletRequest的限制访问
* `antMatchers("","","")`表达支持ant风格的路径通配符
* `anyRequest()`所有请求,**这个必须要配置在所有antMatchers()下边**
* `permitAll()`允许没有任何限制面
* `authenticated()`表示需要认证,在执行请求时，必须要登录

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login") // 登录页面
                .loginProcessingUrl("/user/login") // 登录controller请求地址  POST请求
                .defaultSuccessUrl("/") // 登录成功重定向的页面
                .failureUrl("/fail"); // 登录失败
        http.authorizeRequests()
                .antMatchers("/login","/user/login","/layui/**").permitAll() // 设置不拦截的路径
                .anyRequest().authenticated(); // 其它路径拦截，一定要在最后一个
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}

```

### 2.3 权限访问控制

* `http.authorizeRequests().antMatchers("/").hasAuthority("admin")`设置路径的访问权限，登录用户要有这个权限才能访问，否则403
* `hasAnyAuthority("admin,manager")`满足一个权限可用，权限之间用逗号分割

```java
// 设置权限方式1
List<GrantedAuthority> authorities = new ArrayList<>();
authorities.add(new SimpleGrantedAuthority("admin"));

// 设置权限方式2，多个用逗号分隔
List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
```

### 2.4 角色访问控制

* 与权限类似
* `http.authorizeRequests().antMatchers("/").hasRole("USER")`设置路径的访问角色，角色会自动加上`ROLE_`前缀
* `hasAnyRole("USER,SYSTEM")`满足一个角色可访问

```java
// 设置权限方式1
List<GrantedAuthority> authorities = new ArrayList<>();
authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

// 设置权限方式2，多个用逗号分隔
List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
```

### 2.5 自定义403

* 在templates文件夹下加入`error/403.html`
* `http.exceptionHandling().accessDeniedPage("/unauth");`配置类配置

### 2.6 常用注解

#### 2.6.1 @Secured

* 判断是否有某个角色
* 用在方法上、类上
* 要加上`ROLE_`
* 使用注解开启此功能`@EnableGlobalMethodSecurity(securedEnabled=true)`

```java
@PostMapping("/update")
@Secured("ROLE_USER") // 有权限才能访问的方法
public String update() {
    log.info("update方法执行....");
    return "update";
}
```

#### 2.6.2 @PreAuthorize

* 开启功能`@EnableGlobalMethodSecurity(prePostEnabled = true)`
* 方法前的验证权限或角色
* `@PreAuthorize("hasRole('ROLE_ADMIN')")`
* `@PreAuthorize("hasAnyAuthority('menu:system')")`

#### 2.6.3 @PostAuthorize

* 方法后验证
* 与`@PreAuthorize`相反
* 方法中的代码会执行

#### 2.6.4 @PostFilter

* 权限验证之后对数据进行过滤
* 方法返回值会被`@PostFilter`的value属性条件过滤

```java
@GetMapping("/getAll")
@PostFilter("filterObject.username == 'admin1'")
public List<User> getAll() {
    List<User> list = new ArrayList<>();
    list.add(new User(1L,"admin1","","",""));
    list.add(new User(2L,"admin2","","",""));
    log.info("用户数据=>{}",list);
    return list;
}
```

#### 2.6.5 @PreFilter

* 进入控制器之前对数据进行过滤

```
@RequestMapping("getTestPreFilter")
@PreAuthorize("hasRole('ROLE_管理员')")
@PreFilter(value = "filterObject.id%2==0")
@ResponseBody
public List<UserInfo> getTestPreFilter(@RequestBody List<UserInfo> list) {
    list.forEach(t-> {
        System.out.println(t.getId()+"\t"+t.getUsername());
    });
    return list;
}
```

### 2.7 免密登录

* 建表
* `JdbcTokenRepositoryImpl`放到IOC容器中
    * `setDataSource()`设置数据源
    * `setCreateTableOnStartup()`自动建表，**运行一次过后要删掉**
* `public RememberMeConfigurer<H> tokenValiditySeconds(int tokenValiditySeconds)`设置过期时间
* 表单中要有checkbox并且name值必需是`remember-me`

```sql
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
```

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
        // 其它省略....

        // 开启记住我功能
        http.rememberMe().tokenValiditySeconds(60 * 10) // 设置记住的时间
                .tokenRepository(tokenRepository()) // 设置
                .userDetailsService(userService);
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource); // 设置数据源
//        tokenRepository.setCreateTableOnStartup(true); // 自动建表
        return tokenRepository;
    }
}

```

### 2.8 用户注销

* 设置一个超链接，地址是`logoutUrl()`方法中的值
* `http.logout().logoutUrl("/logout").logoutSuccessUrl("/index").permitAll`

### 2.9 CSRF

* CSRF默认是开启的，`http.csrf().disable();`可以关闭
* Spring Security CSRF 会针对 PATCH，POST，PUT 和 DELETE 方法进行防护。
* 开启CSRF后，每个POST请求都要有`_csrf: token`参数
    * 登录页面好像会自动加上？？？
    * **已解决**：表单中用`th:action=‘@{/login}`替代`action=’/login’`，thymeleaf模板会自动加入

```xml
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
    <version>3.0.4.RELEASE</version>
</dependency>
```

```html
<input type="hidden" name="_csrf" th:value="${_csrf.token}"/>
<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
```

### 2.10 前端获得登录信息

* thymeleaf
* 导入依赖`<artifactId>thymeleaf-extras-springsecurity5</artifactId>`
* 页面上加上security的命名空间
* 在注入spring模板引擎时设置`AdditionalDialects`属性为`SpringSecurityDialect`
    * **SpringBoot已经默认开启此功能**
* 显示登录用户名：
    * `<span sec:authentication="name"></span>`
* 显示当前用户权限：
    * 方式1： `<span sec:authentication="principal.authorities"></span>`
	* 方式2： `<span sec:authentication="authorities"></span>`

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
```

```xml
<bean id="templateEngine" class="org.thymeleaf.spring5.SpringTemplateEngine">
   <!--在thymeleaf中使用使用sec:* -->
    <property name="additionalDialects">
        <set>
            <bean class="org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect"></bean>
        </set>
    </property>
</bean>
```

```java
@Bean
public SpringSecurityDialect securityDialect() {
    return new SpringSecurityDialect();
}

@Bean
public ITemplateResolver templateResolver() {
    // 省略......
    return templateResolver;
}
@Bean
public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    // ------------------------------------------
    Set<IDialect> set = new HashSet<>();
    set.add(securityDialect());
    templateEngine.setAdditionalDialects(set);
    // ------------------------------------------
    return templateEngine;
}
@Bean
public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
    // 省略......
    return viewResolver;
}
```

### 2.11 X-Frame-Options错误

Refused to display http:// localhost:8080 /xxx’in a frame because it set ‘X-Frame-Options’ to ‘DENY’

>X-Frame-Options HTTP 响应头是用来给浏览器指示允许一个页面可否在 frame , iframe 或者 object 中展现的标记。网站可以使用此功能，来确保自己网站的内容没有被嵌到别人的网站中去，也从而避免了点击劫持 (clickjacking) 的攻击。

* Spring Security4默认是将`X-Frame-Options`设置为 ‘DENY’
* 参考：<https://blog.csdn.net/qq_43203949/article/details/120277170>

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //disable frameOptions检验
        http.headers().frameOptions().disable();
    }
}

```

### 2.12 自定义验证码

* 导入依赖
* 配置验证码图片路径，谷歌验证码提供了一个默认servlet`KaptchaServlet`
* 自定义filter进行验证码校验
* 将自定义的filter，加入到Security的过滤链中，身份验证过滤器`UsernamePasswordAuthenticationFilter`之前

```xml
<dependency>
  <groupId>com.github.penggle</groupId>
  <artifactId>kaptcha</artifactId>
  <version>2.3.2</version>
</dependency>
```

```java
@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<KaptchaServlet> servletRegistrationBean() {
        KaptchaServlet kaptchaServlet = new KaptchaServlet();
        return new ServletRegistrationBean<>(kaptchaServlet,"/captcha.jpg");
    }
}
```

```java
@Slf4j
@Component
public class CheckCaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String realRequestUrl = request.getRequestURI().substring(request.getContextPath().length());
        // 不是登录请求，放行
        if (!"/login".equals(realRequestUrl)) {
            filterChain.doFilter(request,response);
            return;
        }
        // 获取默认的 captcha
        String sessionCaptcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String captcha = request.getParameter("captcha");
        if (sessionCaptcha == null || captcha == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }
        if(sessionCaptcha.equals(captcha)) {
            // 验证码正确，清除验证码session
            request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
            filterChain.doFilter(request,response);
        } else {
//            throw new RuntimeException("验证码错误");
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    }
    
}
```

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CheckCaptchaFilter checkCaptchaFilter;

     // 省略.....

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
       // 省略.....

        http.addFilterBefore(checkCaptchaFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
```




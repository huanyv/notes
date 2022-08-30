# SpringBoot2学习笔记

## 目录

[TOC]


## 1. Hello World

* 新建一个maven项目
* 指名父项目

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.6.6</version>
</parent>
```

* 加入SpringBoot2的依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

* 编写主类

```java
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
```

* 编写Controller
* **注意**：controller的包必须和主类在同一个包下，这样才能扫描到controller层

```java
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello SpringBoot2";
    }
}
```

* 运行主类中的`main`方法
* 默认会以8080端口起一个服务器
* resouces目录下加入`application.properties`文件
    * `server.port=8090`更改端口号
* pom文件中加入以下插件
* 运行插件可以将项目打jar包
    * 运行：`java -jar xxxxxxx.jar`

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## 2. 自动配置

### 2.1 依赖管理

* `<artifactId>spring-boot-dependencies</artifactId>`管理了所有依赖的版本
* 如果想要用另的版本，可以在自己的pom文中定义

```xml
<properties>
    <mysql.version>5.1.43</mysql.version>
</properties>
```

* 默认的包结构
  * 主程序所在包及其下面的所有子包里面的组件都会被默认扫描进来
  * 无需以前的包扫描配置
  * 想要改变扫描路径，`@SpringBootApplication(scanBasePackages="com.atguigu")`
    * 或者`@ComponentScan` 指定扫描路径

## 3. 容器功能

### 3.1 组件添加


* `@Configuration`
    * 标记一个配置类，基本使用
    * Full模式与Lite模式
        * 配置 类组件之间无依赖关系用Lite模式加速容器启动过程，减少判断
        * 配置类组件之间有依赖关系，方法会被调用得到之前单实例组件，用Full模式
    * 配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
    * 配置类本身也是组件
    * `proxyBeanMethods`：代理bean的方法
         * Full`：(proxyBeanMethods = true)`、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
         * Lite：`(proxyBeanMethods = false)`【每个@Bean方法被调用多少次返回的组件都是新创建的】
    * 组件依赖必须使用Full模式默认。其他默认是否Lite模式
* `@Bean、@Component、@Controller、@Service、@Repository`
    * `@Bean`创建一个实例到IOC容器中，是一个方法，方法的返回类型，相当于`<bean>`标签的class；方法名相当于`id`,`return`就是到IOC容器的实例
* `@Import`
    * value是一个`Class`对象数组
    * 给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
* `@Conditional`
    * 条件装配：满足Conditional指定的条件，则进行组件注入
    * `@Conditional(name = "tom")`
        * 标记在@bean方法上：容器中有tom组件，才往容器中注入此组件
        * 标记在配置类上：容器中有tom组件，这个类中的所有组件才注入

### 3.2 原生配置文件引入

* `@ImportResource`
    * 引入原生的配置文件
    * `@ImportResource("classpath:beans.xml")`

### 3.3 配置绑定

* `@ConfigurationProperties`
    * 配置绑定
    * 从`properties`文件中读取内容，封闭到javabean中


* @Component + @ConfigurationProperties

```xml
mycar.brand=BYD
mycar.price=100000
```

```java
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
    
    
}
```

* @EnableConfigurationProperties + @ConfigurationProperties

```java
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
    
    
}
```

```java
@EnableConfigurationProperties(Car.class)
//1、开启Car配置绑定功能
//2、把这个Car这个组件自动注册到容器中
public class MyConfig {
}
```

### 3.4 开发技巧

#### 3.4.1 lombok

* 简化日志

```java
@Slf4j
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String handle01(@RequestParam("name") String name){
        
        log.info("请求进来了....");
        
        return "Hello, Spring Boot 2!"+"你好："+name;
    }
}
```

#### 3.4.2 dev-tools

* 热布署
* 加入以下依赖
* 项目或者页面修改以后：Ctrl+F9；

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

#### 3.4.5 日志

* 开启日志

```java
debug: true #开启日志
logging:
  level:
    com.sangeng: debug #设置日志级别
```

## 4. 配置文件

### 4.1 yaml

#### 4.1.1 基本语法

* key: value；kv之间有空格
* 大小写敏感
* 使用缩进表示层级关系
* 缩进不允许使用tab，只允许空格
* 缩进的空格数不重要，只要相同层级的元素左对齐即可
* '#'表示注释
* 字符串无需加引号，如果要加，`''`与`""`表示字符串内容 会被 转义/不转义

#### 4.1.2 数据类型

* 字面量（单个值）：`key: value`
* 对象、map、object

```yaml
# 行内写法：  

k: {k1:v1,k2:v2,k3:v3}

# 或

k: 
  k1: v1
  k2: v2
  k3: v3
```

* 数组、list、set


```yaml
# 行内写法： 

k: [v1,v2,v3]

# 或者

k:
 - v1
 - v2
 - v3
```

* 示例

```java
@Data
@ConfigurationProperties(prefix = "person")
@Component
public class Person {
        private String userName;
        private Boolean boss;
        private Date birth;
        private Integer age;
        private Pet pet;
        private String[] interests;
        private List<String> animal;
        private Map<String, Object> score;
        private Set<Double> salarys;
        private Map<String, List<Pet>> allPets;
}

@Data
public class Pet {
	private String name;
	private Double weight;
}
```

```yaml
person:
  userName: zhangsan
  boss: true
  birth: 2022/4/11
  age: 19
  pet:
    name: 柯基
    weight: 19.8
  interests: [哈哈,呵呵]
  animal:
    - 嘻嘻
    - 呜呜
  score:
    shuxun: 100
    yuwen: 80
  salarys:
    - 8000
    - 1111
  allPets:
    dog:
      - name: 柯基
        weight: 19.8
      - name: 二哈
        weight: 99.2
    cat: [{name: 波斯猫, weight: 10.1},{name: 橘猫,weight: 20.2}]
```

#### 4.1.3 设置启动图

* 在resouces目录加入banner.txt文件
* 在resouces目录加入banner文件名的图片文件
* 指定启动图片(图片在resources目录下)

```yaml
spring:
  banner:
    image:
      location: classpath:bug.png
```

#### 4.1.4 @Value注解

该注解作用的作用是将我们配置文件的属性读出来
* `@Value("${}")`
    * `${ property : default_value }`
    * 注入的是外部配置文件对应的property
* `@Value("#{}")`
    * `#{ obj.property? :default_value }`
    * 表示SpEl表达式通常用来获取bean的属性，或者调用bean的某个方法。当然还有可以表示常量

## 5. web开发

### 5.1 简单功能分析

#### 5.1.1 静态资源访问

* 静态资源放在类路径下
    * resources
        * /static
        * /public
        * /resources
        * /META-INF/resource
* 访问：/ + 静态资源名
* 请求进来，先去找Controller看能不能处理。不能处理的所有请求又都交给静态资源处理器。静态资源也找不到则响应404页面
* 改变默认的静态资源路径

```yaml
spring:
  web:
    resources:
      static-locations: [classpath:/haha/]
```

* 设置静态资源访问前缀

```yaml
spring:
  mvc:
    static-path-pattern: /res/**
```

* webjar
* 自动映射 `/webjars/**`
* <https://www.webjars.org/>
* 访问地址：<http://localhost:8080/webjars/jquery/3.5.1/jquery.js>后面地址要按照依赖里面的包路径

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.5.1</version>
</dependency>
```

#### 5.1.2 欢迎页

* 静态资源目录下的index.html文件
* `/index`controller请求
* 可以用自己配置的静态资源目录
* **不可以配置静态资源的访问前缀。否则导致 index.html不能被默认访问**

#### 5.1.3 标签小图标

* `favicon.ico`放到静态资源目录下
* 配置静态资源的访问前缀，会使此功能失效

#### 5.1.4 设置项目路径

```yaml
server:
  servlet:
    context-path: /book
```

### 5.2 请求参数处理

#### 5.2.1 REST使用

* 开启REST风格
* POST请求设置请求参数`_method`

```yaml
spring:
  mvc:
    hiddenmethod:
      filter:
        enabled: true
```

#### 5.2.2 请求参数映射

* `@PathVariable`路径参数
* `@RequestParam`获取请求参数，只能接收`Content-Type: aplication/x-www-form-urlencoded`
* `@RequestPart`获取文件
* `@RequestHeader`获取请求头
* `@RequestBody`获取请求体（POST）application/json,
  * 接收实体类
  * 如果没有实体对应，可以用一个map映射
* `@CookieValue`获取cookie
* `@ModelAttribute`获取request域数据
* `@MatrixVariable`获取矩阵请求参数
* `@MatrixVariable`使用
    * 与路径参数绑定
    * 请求地址：`/matrix/user;name=zhangsan;aihao=chang,yiao,rap,lanqiu`
    * springboot默认禁用了此功能

```java
@GetMapping("/matrix/{user}")
public Map MatrixVariableTest(@MatrixVariable("name")String name,@MatrixVariable("aihao") String[] aihao) {
    Map<String,Object> map = new HashMap<>();
    map.put("name",name);
    map.put("aihao",aihao);
    return map;
}
```

* 开启矩阵变量

```java
@Bean
public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void configurePathMatch(PathMatchConfigurer configurer) {
            UrlPathHelper urlPathHelper = new UrlPathHelper();
            urlPathHelper.setRemoveSemicolonContent(false);
            configurer.setUrlPathHelper(urlPathHelper);
        }
    };
}
```

#### 5.2.3 设置出入参日期格式化

```yaml
spring:
  mvc:
    format:
      date: yyyy-MM-dd
  jackson:
    date-format: yyyy-MM-dd
    time-zone: GMT+8
```

#### 5.2.4 允许跨域请求

* 可以在支持跨域的方法上或者是`Controller`上加`@CrossOrigin`注解
* 配置类,使用`WebMvcConfigurer`的`addCorsMappings()` 方法配置`CorsInterceptor`

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}
```


### 5.3 视图解析器

* 引入starter
* springboot已经自动配置好了、前缀、后缀
* html页面放在resources/templates文件夹内

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

### 5.4 拦截器

* 创建一个interceptor类，实现`HandlerInterceptor`接口
* 重写方法

```java
public class loginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        // 登录成功
        if (user != null) {
            // 放行
            return true;
        } else {
            request.setAttribute("msg","请先登录！");
            request.getRequestDispatcher("/").forward(request,response);
            // 拦截
            return false;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

```

#### 5.4.1 配置拦截器

* `addPathPatterns()`配置拦截的路径，拦截所有请求、包含静态资源
* `excludePathPatterns()`排除拦截路径

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor()).addPathPatterns("/**") // 配置拦截的路径，拦截所有请求、包含静态资源
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**"); // 放行的路径
    }
}

```


### 5.5 文件上传

* 设置单个文件上传大小
* 设置所有文件总和上传大小

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 100MB
      file-size-threshold: 10MB
```

### 5.6 异常处理

* 默认规则
    * 默认情况下，Spring Boot提供/error处理所有错误的映射
    * 对于机器客户端，它将生成JSON响应，其中包含错误，HTTP状态和异常消息的详细信息。对于浏览器客户端，响应一个“whitelabel”错误视图，以HTML格式呈现相同的数据
* 定制错误页
    * `error/400.html`、`error/5xx.html`
    * 有精确的错误状态码页面就匹配精确，没有就找4xx.html；如果都没有就触发白页

#### 5.6.1 定制异常处理

* 创建类加上`@ControllerAdvice`注解进行标识
* 定义异常处理方法，使用`@ExceptionHandler`标识可以处理的异常。

```java
@RestControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handlerException(Exception e){
        //获取异常信息，存放如ResponseResult的msg属性
        String message = e.getMessage();
        ResponseResult result = new ResponseResult(400,message);
        //把ResponseResult作为返回值返回，要求到时候转换成json存入响应体中
        return result;
    }
}
```

### 5.7 使用原生的JavaWeb

#### 5.7.1 注解

* `@ServletComponentScan(basePackages = "com.atguigu.admin")`指定原生Servlet组件都放在那里
* `@WebServlet(urlPatterns = "/my")`：不经过Spring的拦截器
* `@WebFilter(urlPatterns={"/css/*","/images/*"})`
* `@WebListener`

#### 5.7.2 使用RegistrationBean

```java
@Configuration
public class MyServletConfig {

    @Bean
    public ServletRegistrationBean myServletRegistrationBean() {
        MyServlet myServlet = new MyServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(myServlet,"/my");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilterRegistrationBean() {
        MyFilter myFilter = new MyFilter();
//        return new FilterRegistrationBean(myFilter,myServletRegistrationBean());
        FilterRegistrationBean bean = new FilterRegistrationBean(myFilter);
        bean.setUrlPatterns(Arrays.asList("/css/*"));
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean myServletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean(new MyListener());
    }

}
```

### 5.8 定制化原理

* 修改配置文件；
* xxxxxCustomizer；
* 编写自定义的配置类   xxxConfiguration；+ @Bean替换、增加容器中默认组件 
* Web应用 编写一个配置类**实现 WebMvcConfigurer** 即可定制化web功能；+ @Bean给容器中再扩展一些组件
* @EnableWebMvc + WebMvcConfigurer —— @Bean  可以全面接管SpringMVC，所有规则全部自己重新配置； 实现定制和扩展功能
  * 原理
  * 1、WebMvcAutoConfiguration  默认的SpringMVC的自动配置功能类。静态资源、欢迎页.....
  * 2、一旦使用 @EnableWebMvc 、。会 @Import(DelegatingWebMvcConfiguration.class)
  * 3、DelegatingWebMvcConfiguration 的 作用，只保证SpringMVC最基本的使用
    * 把所有系统中的 WebMvcConfigurer 拿过来。所有功能的定制都是这些 WebMvcConfigurer  合起来一起生效
    * 自动配置了一些非常底层的组件。RequestMappingHandlerMapping、这些组件依赖的组件都是从容器中获取
    *  public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport
  * 4、WebMvcAutoConfiguration 里面的配置要能生效 必须  @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
  * 5、@EnableWebMvc  导致了 WebMvcAutoConfiguration  没有生效。

### 5.9 启动预加载

* 在使用SpringBoot构建项目时，我们通常有一些预先数据的加载。那么SpringBoot提供了一个简单的方式来实现`CommandLineRunner`。
* 实现`CommandLineRunner`接口，并把对象放到IOC容器中
* 可以使用`@Order`设置加载执行顺序

```java
@Component
public class UpdateViewCountRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
       System.out.println("应用启动了.......");
    }
}
```

### 5.10 定时任务

* `@EnableScheduling`开启定时任务
* `@Scheduled`标注定时任务方法
    * `cron`cron表达式
        * cron表达式生成器：<http://cron.ciding.cc/>
    * `zone`设置时区
* 使用`@EnableScheduling`开启定时任务，并把类放到IOC容器中，在定时方法上使用`@Scheduled`注解

```java
@Component
@EnableScheduling
public class ArticleViewCountJob {

    @Scheduled(cron = "0/3 * * * * ? ")
    public void test() {
        System.out.println("3秒钟过去了....");
    }
}
```

## 6. 数据访问

### 6.1 数据源的配置

* 引入JDBC的starter
* 引入数据库驱动
* 修改配置项、设置数据库连接信息

```xml
<properties>
    <java.version>1.8</java.version>
    <mysql.version>5.1.49</mysql.version>
</properties>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

* application.yaml

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mydb01?useSSL=false
    username: root
    password: 123
```

### 6.2 使用Druid数据源

* 官方地址：<https://github.com/alibaba/druid>

#### 6.2.1 自定义

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.8</version>
</dependency>
```

```java
@Configuration
public class MyDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setFilters("stat,wall"); // 开启sql统计监控和防火墙监控
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean
                = new ServletRegistrationBean<>(statViewServlet,"/druid/*");
//        registrationBean.addInitParameter("loginUsername","admin"); // 设置druid后台监控的登录和密码
//        registrationBean.addInitParameter("loginPassword","123456");
        return registrationBean;
    }

    /**
     * 设置druid的URI监控
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(webStatFilter);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }

}

```

#### 6.2.2 使用starter

* 官方springboot配置文档：<https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter>
* 配置属性列表：<https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8>

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.8</version>
</dependency>
```

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db_account
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver

    druid:
      aop-patterns: com.atguigu.admin.*  #监控SpringBean
      filters: stat,wall     # 底层开启功能，stat（sql监控），wall（防火墙）

      stat-view-servlet:   # 配置监控页功能
        enabled: true
        login-username: admin
        login-password: admin
        resetEnable: false

      web-stat-filter:  # 监控web
        enabled: true
        urlPattern: /*
        exclusions: '*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*'


      filter:
        stat:    # 对上面filters里面的stat的详细配置
          slow-sql-millis: 1000
          logSlowSql: true
          enabled: true
        wall:
          enabled: true
          config:
            drop-table-allow: false
```

### 6.3 整合Mybatis

* 导入mybatis的starter
* 创建mybatis-config.xml配置文件
* 创建mapper接口，在接口上添加注解`@Mapper`
* 创建mapper映射文件
* 修改application.yaml，指定mybatis-config和mapper映射文件的位置
    * 可以不写全局；配置文件，所有全局配置文件的配置都放在configuration配置项中即可 
    * mybatis-config文件和mapper映射不要在同一目录下

```xml
 <dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.4</version>
</dependency>
```

```java
@Mapper
public interface UserMapper {
}
```

```yaml
# 配置mybatis规则
mybatis:
#  config-location: classpath:mybatis/mybatis-config.xml
  mapper-locations: classpath:mybatis/mapper/*.xml
  configuration:
    map-underscore-to-camel-case: true # 开启驼峰命名规则
```

#### 6.3.1 配置pageHelper

* 引入starter
* **使用新的版本，旧版本会出现循环依赖错误**

```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.4.1</version>
</dependency>
```

* application.yaml添加配置

```yaml
# 分页插件
pagehelper:
  # 指定数据库
  helper-dialect: mysql
  # 分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页
  reasonable: true
  # 支持通过 Mapper 接口参数来传递分页参数，默认值false
  support-methods-arguments: true
  params: count=countSql
```

## 7. 单元测试

### 7.1 Junit5的变化

* Spring Boot 2.2.0 版本开始引入 JUnit 5 作为单元测试默认库
* 注意：
* SpringBoot 2.4 以上版本移除了默认对 Vintage 的依赖。如果需要兼容junit4需要自行引入（不能使用junit4的功能 @Test）
* `JUnit 5’s Vintage Engine Removed from spring-boot-starter-test`,如果需要继续兼容junit4需要自行引入vintage

```xml
<dependency>
    <groupId>org.junit.vintage</groupId>
    <artifactId>junit-vintage-engine</artifactId>
    <scope>test</scope>
    <exclusions>
        <exclusion>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

### 7.2 Junit5的常用注解

* JUnit5的注解与JUnit4的注解有所变化:<https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations>
* `@Test` :表示方法是测试方法。但是与JUnit4的@Test不同，他的职责非常单一不能声明任何属性，拓展的测试将会由Jupiter提供额外测试
* `@ParameterizedTest` :表示方法是参数化测试，下方会有详细介绍
* `@RepeatedTest` :表示方法可重复执行，下方会有详细介绍
* `@DisplayName` :为测试类或者测试方法设置展示名称
* `@BeforeEach` :表示在每个单元测试之前执行
* `@AfterEach` :表示在每个单元测试之后执行
* `@BeforeAll` :表示在所有单元测试之前执行
* `@AfterAll` :表示在所有单元测试之后执行
* `@Tag` :表示单元测试类别，类似于JUnit4中的@Categories
* `@Disabled` :表示测试类或测试方法不执行，类似于JUnit4中的@Ignore
* `@Timeout` :表示测试方法运行如果超过了指定时间将会返回错误
* `@ExtendWith` :为测试类或测试方法提供扩展类引用

### 7.3 断言Assertion

* 这些断言方法都是 `org.junit.jupiter.api.Assertions` 的静态方法。
* 所有的测试运行结束以后，会有一个详细的测试报告；

方法            |        	说明
----------------|-----------------------------------------
assertEquals	|    判断两个对象或两个原始类型是否相等
assertNotEquals	|    判断两个对象或两个原始类型是否不相等
assertSame	    |    判断两个对象引用是否指向同一个对象
assertNotSame	|    判断两个对象引用是否指向不同的对象
assertTrue	    |    判断给定的布尔值是否为 true
assertFalse	    |    判断给定的布尔值是否为 false
assertNull	    |    判断给定的对象引用是否为 null
assertNotNull	|    判断给定的对象引用是否不为 null

```java
@Test
public void testAssertEquals() {
//        Assertions.assertEquals(4,sum(1,2));
//        String str1 = "1234";
//        String str2 = "1234";
//        Assertions.assertSame(str1,str2);
    Assertions.assertAll(""
            ,()->{Assertions.assertEquals(2,1+1);}
            ,()->{Assertions.assertTrue(true);});

}

int sum(int a, int b) {
    return a + b;
}
```

* 数组断言
* 通过 assertArrayEquals 方法来判断两个对象或原始类型的数组是否相等

```
@Test
@DisplayName("array assertion")
public void array() {
    assertArrayEquals(new int[]{1, 2}, new int[] {1, 2});
}
```

* 组合断言
* assertAll 方法接受多个 org.junit.jupiter.api.Executable 函数式接口的实例作为要验证的断言，可以通过 lambda 表达式很容易的提供这些断言

```java
@Test
@DisplayName("assert all")
public void all() {
 assertAll("Math",
    () -> assertEquals(2, 1 + 1),
    () -> assertTrue(1 > 0)
 );
}
```

* 异常断言

```java
@Test
@DisplayName("异常测试")
public void exceptionTest() {
    ArithmeticException exception = Assertions.assertThrows(
   //扔出断言异常
    ArithmeticException.class, () -> System.out.println(1 % 0));

}
```

* 超时断言

```java
@Test
@DisplayName("超时测试")
public void timeoutTest() {
    //如果测试方法时间超过1s将会异常
    Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
}
```

* 异常断言

```java
@Test
@DisplayName("fail")
public void shouldFail() {
    fail("This should fail");
}
```

### 7.4 前置条件（assumptions）

* JUnit 5 中的前置条件（assumptions【假设】）类似于断言，
* 不满足的前置条件只会使得测试方法的执行终止。会跳过测试
* assumeTrue 和 assumFalse 确保给定的条件为 true 或 false，不满足条件会使得测试执行终止。
* assumingThat 的参数是表示条件的布尔值和对应的 Executable 接口的实现对象。只有条件满足时，Executable 对象才会被执行；当条件不满足时，测试执行并不会终止。

```java
@DisplayName("前置条件")
public class AssumptionsTest {
    private final String environment = "DEV";
    
    @Test
    @DisplayName("simple")
    public void simpleAssume() {
        assumeTrue(Objects.equals(this.environment, "DEV"));
        assumeFalse(() -> Objects.equals(this.environment, "PROD"));
    }
    
    @Test
    @DisplayName("assume then do")
    public void assumeThenDo() {
        assumingThat(
           Objects.equals(this.environment, "DEV"),
           () -> System.out.println("In DEV")
        );
    }
}
```

### 7.5 嵌套测试

* JUnit 5 可以通过 Java中的内部类和`@Nested`注解实现嵌套测试，从而可以更好的把相关的测试方法组织在一起。在内部类中可以使用@BeforeEach 和@AfterEach 注解，而且嵌套的层次没有限制。

### 7.6 参数化测试

* 利用@ValueSource等注解，指定入参，我们将可以使用不同的参数进行多次单元测试，而不需要每新增一个参数就新增一个单元测试，省去了很多冗余代码。
* `@ValueSource`: 为参数化测试指定入参来源，支持八大基础类以及String类型,Class类型
* `@NullSource`: 表示为参数化测试提供一个null的入参
* `@EnumSource`: 表示为参数化测试提供一个枚举入参
* `@CsvFileSource`：表示读取指定CSV文件内容作为参数化测试入参
* `@MethodSource`：表示读取指定方法的返回值作为参数化测试入参(注意方法返回需要是一个流)

>当然如果参数化测试仅仅只能做到指定普通的入参还达不到让我觉得惊艳的地步。让我真正感到他的强大之处的地方在于他可以支持外部的各类入参。如:CSV,YML,JSON 文件甚至方法的返回值也可以作为入参。只需要去实现ArgumentsProvider接口，任何外部文件都可以作为它的入参。

```java
@ParameterizedTest
@ValueSource(strings = {"one", "two", "three"})
@DisplayName("参数化测试1")
public void parameterizedTest1(String string) {
    System.out.println(string);
    Assertions.assertTrue(StringUtils.isNotBlank(string));
}


@ParameterizedTest
@MethodSource("method")    //指定方法名
@DisplayName("方法来源参数")
public void testWithExplicitLocalMethodSource(String name) {
    System.out.println(name);
    Assertions.assertNotNull(name);
}

static Stream<String> method() {
    return Stream.of("apple", "banana");
}
```

### 7.7 迁移指南

* 在进行迁移的时候需要注意如下的变化：
    * 注解在 org.junit.jupiter.api 包中，断言在 org.junit.jupiter.api.Assertions 类中，前置条件在 org.junit.jupiter.api.Assumptions 类中。
    * 把@Before 和@After 替换成@BeforeEach 和@AfterEach。
    * 把@BeforeClass 和@AfterClass 替换成@BeforeAll 和@AfterAll。
    * 把@Ignore 替换成@Disabled。
    * 把@Category 替换成@Tag。
    * 把@RunWith、@Rule 和@ClassRule 替换成@ExtendWith。


## 8. 环境切换配置

### 8.1 profile功能

* 针对不同的环境进行不同的配置，然后可以通过激活、指定参数等方式快速切换环境。
* 默认配置文件  `application.yaml`；任何时候都会加载，一般在这个文件写不会改变的配置，比如出入参格式化、mybatis映射文件位置
* 指定环境配置文件  `application-{env}.yaml`，一般在这里配置不同环境下，不同的配置，比如数据库连接信息
* 激活指定环境
  * 配置文件激活
  * 命令行激活：`java -jar xxx.jar --spring.profiles.active=prod  --person.name=haha`
    * **application.yml**文件中使用`spring.profiles.active={env}`属性来配置激活哪个环境，**命令行优先**
* 默认配置与环境配置同时生效
* 同名配置项，profile配置优先

### 8.2 外部化配置

* 常用：Java属性文件、YAML文件、环境变量、命令行参数；
* 配置文件查找的位置，后面的会覆盖前面的
    1. classpath 根路径
    1. classpath 根路径下config目录
    1. jar包当前目录
    1. jar包当前目录的config目录
    1. /config子目录的直接子目录
* 配置文件加载的顺序
    1. 当前jar包内部的application.properties和application.yml
    2. 当前jar包内部的application-{profile}.properties 和 application-{profile}.yml
    3. 引用的外部jar包的application.properties和application.yml
    4. 引用的外部jar包的application-{profile}.properties 和 application-{profile}.yml
* 指定环境优先，外部优先，后面的可以覆盖前面的同名配置项

## 常用配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test?useSSL=false
    username: root
    password: 123

  mvc:
    hiddenmethod:
      filter:
        enabled: true
    format:
      date: yyyy-MM-dd

  jackson:
    date-format: yyyy-MM-dd
    time-zone: GMT+8

  main:
    # 2.6版本以上默认禁止了循环依赖
    allow-circular-references: true

server:
  servlet:
    context-path: /book

mybatis:
  mapper-locations: classpath:mapper/*.xml




# 分页插件
pagehelper:
  # 指定数据库
  helper-dialect: mysql
  # 分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页
  reasonable: true
  # 支持通过 Mapper 接口参数来传递分页参数，默认值false
  support-methods-arguments: true
  params: count=countSql
```




















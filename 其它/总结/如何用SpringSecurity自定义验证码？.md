# 如何用SpringSecurity自定义验证码？

## 导入依赖
* 这里使用Google的验证码
* 因为Google官方的maven坐标导入不了，使用的是镜像

```xml
<dependency>
	<groupId>com.github.penggle</groupId>
	<artifactId>kaptcha</artifactId>
	<version>2.3.2</version>
</dependency>
```

## 设置图片验证码路径
* 谷歌提供了一个默认的Servlet`com.google.code.kaptcha.servlet.KaptchaServlet`
* 设置路径

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

## 自定义filter
* 自定义一个filter来校验验证码

```java
@Component
public class CheckCaptchaFilter extends OncePerRequestFilter {

	// 谷歌的默认验证码session key
    private static final String CAPTCAH_SESSION_KEY = Constants.KAPTCHA_SESSION_KEY;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String realRequestUrl = request.getRequestURI().substring(request.getContextPath().length());
        // 不是登录请求，放行
        if (!"/login".equals(realRequestUrl)) {
            filterChain.doFilter(request,response);
            return;
        }
        // 获取默认的 captcha
        String sessionCaptcha = (String) request.getSession().getAttribute(CAPTCAH_SESSION_KEY);
        String captcha = request.getParameter("captcha");
        if (sessionCaptcha == null || captcha == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }
        if(sessionCaptcha.equalsIgnoreCase(captcha)) {
            // 验证码正确，清除验证码session
            request.getSession().removeAttribute(CAPTCAH_SESSION_KEY);
            filterChain.doFilter(request,response);
        } else {
//            throw new RuntimeException("验证码错误");
            response.sendRedirect(request.getContextPath() + "/login.html");
        }

    }

}

```

## Filter添加
* 把自定义的filter添加到Security的过滤器链中
* 应当在身份验证过滤器之前`UsernamePasswordAuthenticationFilter`

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
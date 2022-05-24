# 使用AOP打印日志信息

[TOC]

## 0、说明

* 打印日志记录接口的调用信息，便于后期排查
* 日志记录可能有多个接口
* 对原有的方法增强，可以使用AOP功能实现

### 日志格式

```
=======Start=======
URL            : http://localhost:7777/article/articleList
BusinessName   : 无
HTTP Method    : GET
Class Method   : top.huanyv.controller.ArticleController.articleList
IP             : 0:0:0:0:0:0:0:1
Request Args   : [1,10,2]
Response       : {"code":200,"msg":"操作成功","data":[]}
=======End=======
```

## 1、创建注解

* 使用注解来标记要打印日志的接口
* 在对应要打印日志的接口方法上使用注解
* 注解中要有当前接口的简易描述信息

```java
@Target(METHOD)
@Retention(RUNTIME)
public @interface SystemLog {
	// 描述信息属性
    String businessName() default "无";
}
```

## 2、获取当前的request对象

```java
public static HttpServletRequest getCurrentRequest() {
    ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    return request.getRequest();
}
```

## 3、创建切面

* 加上lombok的`@Slf4j`注解以便打印日志
* 要引入lombok依赖

```java
@Component
@Aspect
@Slf4j
public class LogAspect {
}
```

## 4、配置切入点

* 使用`@annotation`方式切入

```java
@Pointcut("@annotation(top.huanyv.annotation.SystemLog)")
public void pt() {
}
```

## 5、增强编写

* 因为要打印请求数据，还要打印接口返回数据
* 所以使用环绕增强
* `joinPoint.proceed();`返回值是方法的返回结果，一定要记得返回

```java
@Around("pt()")
public Object printLog(ProceedingJoinPoint joinPoint) {

    Object proceed = null;
    try {
        beforeLog(joinPoint);
        // 目标方法执行
        proceed = joinPoint.proceed();
        afterLog(proceed);
    } catch (Throwable throwable) {
        throwable.printStackTrace();
    } finally {
    	// 返回方法结果
        return proceed;
    }
}

private void beforeLog(ProceedingJoinPoint joinPoint) throws JsonProcessingException {
    // 获取当前请求对象
    HttpServletRequest request = WebUtils.getCurrentRequest();
    // 获取打印日志注解
    SystemLog anno = getSystemLogAnnotation(joinPoint);

    log.info("=======Start=======");
    // 打印请求 URL
    log.info("URL            : {}", request.getRequestURL());
    // 打印描述信息
    log.info("BusinessName   : {}", anno.businessName());
    // 打印 Http method
    log.info("HTTP Method    : {}", request.getMethod());
    // 打印调用 controller 的全路径以及执行方法
    log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    // 打印请求的 IP
    log.info("IP             : {}", request.getRemoteHost());
    // 打印请求入参
    log.info("Request Args   : {}", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
}

private void afterLog(Object proceed) throws JsonProcessingException {
    // 打印出参
    log.info("Response       : {}", new ObjectMapper().writeValueAsString(proceed));
    // 结束后换行
    log.info("=======End=======" + System.lineSeparator());
}

private SystemLog getSystemLogAnnotation(ProceedingJoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    return signature.getMethod().getAnnotation(SystemLog.class);
}
```

## 6、切面完整代码

```java
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(top.huanyv.annotation.SystemLog)")
    public void pt() {
    }

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) {

        Object proceed = null;
        try {
            beforeLog(joinPoint);
            proceed = joinPoint.proceed();
            afterLog(proceed);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            // 返回方法结果
            return proceed;
        }
    }

    private void beforeLog(ProceedingJoinPoint joinPoint) throws JsonProcessingException {
        // 获取当前请求对象
        HttpServletRequest request = WebUtils.getCurrentRequest();
        // 获取打印日志注解
        SystemLog anno = getSystemLogAnnotation(joinPoint);

        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", anno.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

    private void afterLog(Object proceed) throws JsonProcessingException {
        // 打印出参
        log.info("Response       : {}", new ObjectMapper().writeValueAsString(proceed));
        // 结束后换行
        log.info("=======End=======" + System.lineSeparator());
    }

    private SystemLog getSystemLogAnnotation(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(SystemLog.class);
    }
}
```
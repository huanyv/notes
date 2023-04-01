# Java常用类

## StringJoiner

```java
// 分割符， 前缀， 后缀
StringJoiner joiner = new StringJoiner(", ", "[", "]");
joiner.add("hello").add("world");
```

* 还可以使用List集合`String.join(", ", list);`

## 正则

```java
Pattern pattern = Pattern.compile("regex");
Matcher matcher = pattern.matcher("str");
while (matcher.find()) {
    String group = matcher.group();
    System.out.println(group);
}
```

## ClassLoader

* `ClassLoader`在tomcat中会出现获取失败的情况
* 使用`Thread.currentThread().getContextClassLoader()`

## 泛型获取

### 方法返回值泛型

```java
public class MethodUtilTest {
    @Test
    public void getMethodReturnGenerics() throws Exception{
        Method method = this.getClass().getMethod("getString");
        Type type = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
        System.out.println(type);
    }

    public List<String> getString() {
        return null;
    }
}
```

* 获取类的泛型接口集合`clazz.getGenericInterfaces()`

## 从不同jar中加载同名的properties配置文件

```java
public static List<InputStream> loadResources(final String name, final ClassLoader classLoader) throws IOException {
    final List<InputStream> list = new ArrayList<>();
    final Enumeration<URL> systemResources = (classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader)
                    .getResources(name);
    while (systemResources.hasMoreElements()) {
        list.add(systemResources.nextElement().openStream());
    }
    return list;
}
```

## ServiceLoader

* 从META-INF/services目录下的配置文件加载指定接口的实现类
* 作用：最大程度实现插拔，解耦。模块之间只使用配置文件交互

定义一个接口

```java
package org.example.service;

public interface IService {
    void doService();
}
```

* 实现类，一般在另一个模块，依赖上一个接口的模块

```java
public class UserService implements IService {
    @Override
    public void doService() {
        System.out.println(this.getClass().getName());
    }
}

public class BookService implements IService {
    @Override
    public void doService() {
        System.out.println(this.getClass().getName());
    }
}
```

* 配置文件，以接口全名（含包名）为配置文件名，配置文件内容是实现类的全名（含包名）

```
java
resources
	META-INF
		services
			org.example.service.IService


org.example.UserService
org.example.BookService
```

```java
public class Main {
    public static void main(String[] args) {
        // 通过接口找到实现类，执行方法
        ServiceLoader<IService> services = ServiceLoader.load(IService.class);
        Iterator<IService> iterator = services.iterator();
        while (iterator.hasNext()) {
            IService next = iterator.next();
            next.doService();
        }
    }
}
```

## 定时任务

```java
public static void main(String[] args) {
    // 创建任务队列，并且设置线程数量为10
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 执行任务
    scheduledExecutorService.scheduleAtFixedRate(()->{
        System.out.println("定时任务1：" + dateFormat.format(new Date()));
    },0,3, TimeUnit.SECONDS);

    scheduledExecutorService.scheduleAtFixedRate(()->{
        System.out.println("定时任务2：" + dateFormat.format(new Date()));
    },0,5, TimeUnit.SECONDS);
}
```

## 获取当前方法名

```java
public void log() {
    System.out.println("==================================================");
    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    for (int i = 0; i < stackTrace.length; i++) {
        if ("log".equals(stackTrace[i].getMethodName())) {
            stackTraceElement = stackTrace[i + 1];
            break;
        }
    }

    System.out.println("stackTraceElement.getClassName() = " + stackTraceElement.getClassName());
    System.out.println("stackTraceElement.getLineNumber() = " + stackTraceElement.getLineNumber());
    System.out.println("stackTraceElement.getMethodName() = " + stackTraceElement.getMethodName());
    System.out.println("stackTraceElement.getFileName() = " + stackTraceElement.getFileName());
    System.out.println("==================================================");
}
```

## Jsoup

### 乱码

```java
String url = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?g_tk=&uins=" + qq;
Document document = Jsoup.parse(new URL(url).openStream(), "GBK", url);
```

## Jackson

### 忽略null值、默认值

```java
public String toString() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
    try {
        return objectMapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }
    return null;
}
```



## logback

* 常用配置

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jul-to-slf4j</artifactId>
    <version>1.7.25</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

* 默认日志文件输出到`./logs`，自定义`java -jar -DLOD_HOME=/xxx/xxx xxxx.jar`

```xml
<?xml version="1.0" encoding="UTF-8"  ?>
<configuration debug="false"><!--debug=false表示不打印logback的debug信息-->
    <statusListener class="ch.qos.logback.core.status.NopStatusListener"/>

    <!--自定义日志输出路径 java -jar -DLOG_HOME=/log xxxxxx.jar -->
    <property name="LOG_PATH" value="${LOG_HOME:-./logs}"/>

    <!--自定义颜色配置-->
    <conversionRule conversionWord="levelColor" converterClass="org.example.LevelColor"/>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %date{yyyy-MM-dd HH:mm:ss.sss} %levelColor(%-5level) [%magenta(%16thread)] %cyan(%-35class{30}) : %msg%n
            </pattern>
        </encoder>
    </appender>

    <appender name="RollingFileInfo" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_PATH}/info.log</file>
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>info</level>
        </filter>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>error</level>
            <onMatch>DENY</onMatch>
            <onMismatch>ACCEPT</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_PATH}/%d{yyyy-MM, aux}/info.%d{yyyy-MM-dd}.log.gz</fileNamePattern>
            <totalSizeCap>5GB</totalSizeCap>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%date{yyyy-MM-dd HH:mm:ss.sss} %-5level [%16thread] %-35class{30} : %msg%n</pattern>
        </encoder>
    </appender>


    <appender name="RollingFileError" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_PATH}/error.log</file>
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_PATH}/%d{yyyy-MM, aux}/error.%d{yyyy-MM-dd}.%i.log.gz</fileNamePattern>
            <maxFileSize>50MB</maxFileSize>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%date{yyyy-MM-dd HH:mm:ss.sss} %-5level [%16thread] %-35class{30} : %msg%n</pattern>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="RollingFileInfo"/>
        <appender-ref ref="RollingFileError"/>
    </root>

</configuration>
```

```java
public class LevelColor extends ForegroundCompositeConverterBase<ILoggingEvent> {

    static {
        // jul-to-slf4j 替换java内置logging
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        Level level = event.getLevel();
        switch (level.toInt()) {
            case Level.ERROR_INT:
                return ANSIConstants.RED_FG;
            case Level.WARN_INT:
                return ANSIConstants.YELLOW_FG;
            case Level.INFO_INT:
                return ANSIConstants.GREEN_FG;
            case Level.DEBUG_INT:
                return ANSIConstants.BLUE_FG;
            //其他为默认颜色
            default:
                return ANSIConstants.DEFAULT_FG;
        }
    }
}

```

## Runtime打开浏览器

```java
@Slf4j
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        // 使用默认浏览器打开
        try {
            Runtime.getRuntime().exec(String.format("cmd /c start %s", "http://localhost:8080/index.html"));
        } catch (Exception e) {
            log.warn("打开客户端主页失败", e);
        }
    }
}
```









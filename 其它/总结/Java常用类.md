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











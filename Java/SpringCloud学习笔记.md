# SpringCloud学习笔记

## 目录

[TOC]

## 1. 开始

* 版本信息：<https://spring.io/projects/spring-cloud#learn>
* springboot和cloud版本对应关系：<https://spring.io/projects/spring-cloud#overview>
* 更加详细对应关系：<https://start.spring.io/actuator/info>
* 进入springcloud文档可以看到官方推荐的springboot版本

## 2. 服务注册

### 2.1 Eureka

![](img/SpringCloud学习笔记/2022-06-26-17-21-13.png)

#### 2.1.1 注册中心Server

* 引入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

* 配置文件
* `eureka.instance.hostname`实例主机地址
* `eureka.client.register-with-eureka`是否向注册中心注册自己
* `eureka.client.fetch-registry`false表示自己就是注册中心，职责是维护实例，不去检索服务
* `eureka.client.service-url.defaultZone`设置与eureka server交到的地址服务和注册都需要依赖这个地址

```yaml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

* 在主启动类上，使用`@EnableEurekaServer`注解开启eureka服务

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

#### 2.1.2 服务Client

* 服务有服务的**提供者**和**消费者**（客户端）
* 引入依赖

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

* 配置文件
* `spring.application.name`为注册到服务中实例的名字(一定要有)
* `eureka.client.service-url.defaultZone`注册到哪个注册中心

```xml
server:
  port: 80

spring:
  application:
    name: cloud-order-service

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka
```


* 在主启动类上，使用`@EnableEurekaClient`注解开启eureka服务

```java
@SpringBootApplication
@EnableEurekaClient
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}

```

#### 2.1.3 EurekaServer集群

* 虚拟本地域名，在etc的hosts文件中

```
127.0.0.1  eureka7001.com
127.0.0.1  eureka7002.com
```

* 每个EurekaServer注册中心之间互相注册,相互守望

```xml
server:
  port: 7001
eureka:
  instance:
    hostname: eureka7001.com
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://eureka7002.com:7002/eureka/
```

```xml
server:
  port: 7002
eureka:
  instance:
    hostname: eureka7002.com
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/
```

* 因为有了多个Eureka注册中心，所以其中的Client也要注册到每一个Server中

```yaml
server:
  port: 80
spring:
  application:
    name: cloud-order-service
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
```

#### 2.1.4 服务提供者集群环境

* 两个服务提供者在不同的服务器中，同时注册到多个Server中
* 使用同一个`spring.application.name`，代表是同样的服务提供者
* 此时，有了两台服务提供者，那么，在客户端中访问地埋不能写死

```java
@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://cloud-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/consumer")
    public ResponseResult savePayment(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, ResponseResult.class);
    }

    @GetMapping("/consumer/{id}")
    public ResponseResult getPaymentById(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, ResponseResult.class);
    }
}
```

##### 2.1.4.1 负载均衡

* 如果是用了`RestTemplate`，可以使用`@LoadBalanced`注解

```
@Configuration
public class WebConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

#### 2.1.5 actuator微服务信息完善

* 服务名称修改
	* yaml配置文件中：`eureka.instance.instance-id={服务名称}`
* 访问信息IP提示
	* `eureka.instance.prefer-ip-address=true`

#### 2.1.6 服务发现Discovery

* 对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息
* 在主启动上使用`@EnableDiscoveryClient`注解

```java
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private DiscoveryClient discoveryClient;
    
    // ......

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("servcie: {}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-provider-payment");
        for (ServiceInstance instance : instances) {
            log.info("{}\t{}\t{}\t{}"
                    , instance.getInstanceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }
        return discoveryClient;
    }
}
```

#### 2.1.7 关闭自我保护

* 当某个微服务不可用了，Eureka不会自动清理，依旧会对该微服务的信息进行保存
* 自我保护机制是默认开启的
    * 关闭：`eureka.server.enable-self-preservation=false`
* 客户端的配置
    * 向服务器改善心跳时间间隔
    * `eureka.instance.lease-renewal-interval-in-seconds=30`
    * 服务端最后一次收到心跳等待时间上限
    * `eureka.instance.lease-expiration-duration-in-seconds=90`

### 2.3 Zookeeper

* 引入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
    <exclusions>
 		<!--排除掉，因为这个zookeeper依赖的版本可以与自己用的zookpeer版本不一样-->   
        <exclusion>
            <artifactId>zookeeper</artifactId>
            <groupId>org.apache.zookeeper</groupId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.4.11</version>
    <exclusions>
        <exclusion>
            <artifactId>slf4j-log4j12</artifactId>
            <groupId>org.slf4j</groupId>
        </exclusion>
    </exclusions>
</dependency>
```

* springboot配置文件
* 要指定`spring.application.name`

```yaml
spring:
  application:
    name: cloud-consumerzk-order80
  cloud:
    zookeeper:
      connect-string: 127.0.0.1:2181
```

### 2.4 Consul

* 引入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

* springboot配置文件
* 要指定`spring.application.name`，并设置consul的`service-name`

```yaml
spring:
  application:
    name: cloud-consumerconsul-order
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        service-name: ${spring.application.name}
```

### 2.5 三个注册中心的异同

| 组件名    | 语言 | CAP  | 服务健康检查 | 对外暴露接口 | SpringCloud集成 |
| --------- | ---- | ---- | ------------ | ------------ | --------------- |
| Eureka    | Java | AP   | 可配支持     | HTTP         | 已集成          |
| Consul    | Go   | CP   | 支持         | HTTP/DNS     | 已集成          |
| Zookeeper | Java | CP   | 支持         | 客户端       | 已集成          |


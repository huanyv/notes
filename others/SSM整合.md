# SSM整合

[TOC]

## 1. 导入依赖

```xml
<dependencies>

    <!-- spring -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.15</version>
    </dependency>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.7</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.3.15</version>
    </dependency>
    <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf-spring5</artifactId>
        <version>3.0.12.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.3</version>
    </dependency>

    <!-- 数据库 -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.49</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.9</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.7</version>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.2.8</version>
    </dependency>
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>5.2.0</version>
    </dependency>
    
    <!-- 文件处理 -->
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.11.0</version>
    </dependency>
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.4</version>
    </dependency>
    
    <!--其它-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.16.10</version>
    </dependency>
</dependencies>
```

## 2. 缩写mybatis全局配置文件

* mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <typeAliases>
        <package name="com.book.BookManagement.pojo"/>
    </typeAliases>
    <plugins>
        <!--设置分页插件-->
        <plugin interceptor="com.github.pagehelper.PageInterceptor"></plugin>
    </plugins>
</configuration>
```

## 3. 编写Spring配置文件

* applicationContext.xml

```xml
<context:component-scan base-package="com.book.BookManagement">
    <!--取消扫描controller层，扫描controller层在springmvc中控制-->
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>

<!--引入外部配置文件-->
<context:property-placeholder location="classpath:jdbc.properties" />
<bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource">
    <property name="driverClassName" value="${jdbc.driver}"></property>
    <property name="url" value="${jdbc.url}"></property>
    <property name="username" value="${jdbc.username}"></property>
    <property name="password" value="${jdbc.password}"></property>
</bean>

<!-- 配置Spring的声明式事务管理 -->
<!-- 配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

<tx:annotation-driven transaction-manager="transactionManager"/>

<bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sessionFactory">
    <property name="dataSource" ref="dataSource"></property>
    <property name="configLocation" value="classpath:mybatis-config.xml"></property>
</bean>

<!--扫描映射器-->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer" id="scannerConfigurer">
    <property name="basePackage" value="com.book.BookManagement.dao"></property>
</bean>
```

## 4. 编写SpringMVC配置文件

* spring-mvc.xml

```xml
<context:component-scan base-package="com.book.BookManagement.controller" />

<!-- 配置Thymeleaf视图解析器 -->
<bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver" id="templateResolver">
    <property name="prefix" value="/WEB-INF/templates/"></property>
    <property name="suffix" value=".html"></property>
    <property name="characterEncoding" value="UTF-8"></property>
    <property name="templateMode" value="HTML5"></property>
</bean>
<bean class="org.thymeleaf.spring5.SpringTemplateEngine" id="templateEngine">
    <property name="templateResolver" ref="templateResolver"></property>
</bean>
<bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver" id="thymeleafViewResolver">
    <property name="characterEncoding" value="UTF-8"></property>
    <property name="templateEngine" ref="templateEngine"></property>
</bean>

<!-- 配置JSP视图解析器 -->
<!--<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">-->
    <!-- JSP文件所在的目录 -->
<!--    <property name="prefix" value="/WEB-INF/view/" />-->
    <!-- 文件的后缀名 -->
<!--    <property name="suffix" value=".jsp" />-->
<!--</bean>-->

<!--
        支持AOP的注解支持，AOP底层使用代理技术
        JDK动态代理，要求必须有接口
        cglib代理，生成子类对象，proxy-target-class="true" 默认使用cglib的方式
    -->
<aop:aspectj-autoproxy proxy-target-class="true"/>

<!-- 配置文件解析器对象，要求id名称必须是multipartResolver -->
<bean id="multipartResolver"
      class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <!-- 设置文件上传限制大小为10M -->
    <property name="maxUploadSize" value="10485760"/>
</bean>

<!--    开启静态资源-->
<mvc:default-servlet-handler />

<!--    开启注解驱动-->
<mvc:annotation-driven />
```

## 5. 编写web.xml文件

* web.xml

```xml
<!--解决请求乱码-->
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceResponseEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>

<!--设置RESTful请求-->
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>

<!--spring配置文件-->
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
</context-param>
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

<!--spring-mvc-->
<servlet>
    <servlet-name>DispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>DispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

## 6. 编写实体类

```java
public class Book {
    private Integer bid;
    private String bname;
    private String author;
    private String pubcomp;
    private Date pubdate;
    private Integer bcount;
    private BigDecimal price;
}
```

## 7. 编写dao层

```java
public interface BookMapper {
}
```

### 7.1 编写mybatis映射器接口配置文件

* xxxMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.book.BookManagement.dao.BookMapper">
<!--    public Book getBookById(Integer id);-->
    <select id="getBookById" resultType="book">
        select * from t_book where bid = #{bid}
    </select>
</mapper>
```

## 8. 编写Service层

```java
public interface BookService {
}
```

```java
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    
    // 省略
    
}
```

## 9. 编写Controller层

```java
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String bookPageList(
            @RequestParam(value = "pagenum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "size",defaultValue = "5") Integer size,
            Model model
    ) {
        Page<Book> bookByPage = bookService.getBookByPage(pageNum, size);
        model.addAttribute("bookList",bookByPage);
        return "index";
    }
    
}
```

















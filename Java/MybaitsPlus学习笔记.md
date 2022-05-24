# MybaitsPlus学习笔记

## 目录

[TOC]

## 0. 介绍

* 文档地址
  * 官方地址: http://mp.baomidou.com
  * Github: https://github.com/baomidou/mybatis-plus
  * Gitee: https://gitee.com/baomidou/mybatis-plus
  * 文档发布地址: https://baomidou.com/pages/24112f

## 1.HelloWorld

* 创建工程
* 加入依赖

```xml
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus-boot-starter</artifactId>
  <version>3.5.1</version>
</dependency>
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>druid-spring-boot-starter</artifactId>
  <version>1.2.8</version>
</dependency>
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
</dependency>
```

* 配置数据源
* 打开日志

```yaml
  spring:
    datasource:
      url: jdbc:mysql://localhost:3306/temp?useSSL=false&characterEncoding=utf-8
      username: root
      password: 123
      driver-class-name: com.mysql.jdbc.Driver
  mybatis-plus:
    configuration:
      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

* 创建bean对象
* 创建mapper接口，并继承`BaseMapper<>`，泛型是bean对象类型

```java
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
```

* 测试

```java
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }
}
```

## 2. 基本CURD

* 在`BaseMapper<>`中有很多基本常用增删改查的方法，这些对**单表**的操作很方便
* 批量是用的`in (?, ?, ?)`
* map是`key1 = value1 and key2 = value2`

### 2.1 插入

```java
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("adminadmin111");
        user.setPassword("123456");
        user.setEmail("000@qq.com");
        user.setSex("女");
        int result = userMapper.insert(user);
        System.out.println("result->" + result);
    }

}
```

### 2.2 更新

```java
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    
    @Test
    public void testUpdate() {
        User user = new User();
        user.setUid(166L);
        user.setUsername("adminadmin");
        user.setPassword("123456");
        user.setEmail("000@qq.com");
        user.setSex("女");
        int result = userMapper.updateById(user); // 根据 ID 修改

        System.out.println("result->" + result);
    }
}
```

### 2.3 删除

```java
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    
    @Test
    public void testDeleteById() {
//        int result = userMapper.deleteById(171L); // 根据ID删除
//        int result = userMapper.deleteBatchIds(Arrays.asList(168L, 169L, 170L)); // 根据 ID list集合，批量删除
        Map<String,Object> map = new HashMap<>();
        map.put("uid","167");
        map.put("username","admin7");
        int result = userMapper.deleteByMap(map); // 根据map集合条件删除
        System.out.println("result->" + result);
    }
}
```

### 2.4 查询

```java
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    
    @Test
    public void testSelectList() {
        // 查询所有
        // List<User> userList = userMapper.selectList(null); 
        // 根据id查询
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 150L));
        System.out.println(userList);
    }
    
    @Test
    public void testSelect() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }
    
    @Test
    public void testSelectByMap(){
        //通过map条件查询用户信息
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("name", "admin");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }
}
```

### 2.5 通用Service

* `Service`接口继承`IService<T`
* `ServiceImpl`实现类继承`ServiceImpl<M extends BaseMapper<T>, T>`，并实现自己的`Service`接口
* 前缀命名方式区分 Mapper 层避免混淆，
    * get 查询单行 
    * remove 删除 
    * list 查询集合
    * page 分页 

```java
public interface AccountService extends IService<Account> {
}
```

```java
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{
}
```

### 2.6 自动填充

* 实现`MetaObjectHandler`接口
* 重写方法
* `setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)`通用填充
* 在需要自动填充字段的对应**属性上**使用注解`@TableField(fill = FieldFill.DEFAULT)`
    * `FieldFill.DEFAULT`默认不处理
    * `FieldFill.INSERT`,插入时填充字段
    * `FieldFill.UPDATE`更新时填充字段
    * `FieldFill.INSERT_UPDATE`插入和更新时填充字段

```java
@Component
public class MetaObjectHandlerImpl implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = SecurityUtil.getUserId();
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = SecurityUtil.getUserId();
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }
}
```

## 3. 常用注解

### 3.1 @TableName

* 加在实体bean对象类上
* 指定value是数据库表名，解决表名与bean类名不一致的问题
* 可以通过全局配置解决此问题

```yaml
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
global-config:
  db-config:
    # 配置MyBatis-Plus操作表的默认前缀
    table-prefix: t_
```

### 3.2 @TableId

1. 添加到属性上指定表的主键id
2. 设置value值，指定**表的**id名（类的ID与表ID名称不一致）
3. type属性
    * `IdType.ASSIGN_ID`（默认）基于雪花算法的策略生成数据id，与数据库id是否设置自增无关
    * `IdType.AUTO`使用数据库的自增策略，**数据库ID必须开启自增**
* 可以在yaml配置全局设置主键生成策略

```yaml
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
global-config:
  db-config:
    # 配置MyBatis-Plus操作表的默认前缀
    table-prefix: t_
    # 配置MyBatis-Plus的主键策略
    id-type: auto
```

### 3.3 @TableField

* mp自动开启了驼峰规则
* 用在属性上
* value属性指定表字段名
* 表名与类属性名不一致
* `@TableField(exist = false)`用来表明有表中是否存在这个属性字段，`false`表中没有这个字段

### 3.4 @TableLogic

* 物理删除：真实删除，将对应数据从数据库中删除，之后查询不到此条被删除的数据
* 逻辑删除：假删除，将对应数据中代表是否被删除字段的状态修改为“被删除状态”，之后在数据库中仍旧能看到此条数据记录
* 使用场景：可以进行数据恢复
* 用在**类属性上**指定删除标记
* 表中的删除标记字段默认是0，删除后变成1，在用mp查询时也查询不出

```yaml
# 逻辑删除全局配置
mybatis-plus:
  global-config:
    db-config:
      logic-delete-field: delFlag
      logic-not-delete-value: 0
      logic-delete-value: 1
```

## 4. 条件构造器

### 4.1 QueryWrapper

#### 4.1.1 组装普通查询条件

```java
@Test
public void testSelect() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.between("uid","100","200")
            .like("email","123")
            .eq("sex","男")
            .orderByAsc("username");
    List<User> userList = userMapper.selectList(queryWrapper);
    System.out.println(userList);
}
```

#### 4.1.2 删除查询条件

```java
@Test
public void testDelete() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("username","adminadmin");
    int result = userMapper.delete(queryWrapper);
    System.out.println("result->" + result);
}
```


#### 4.1.3 SQL语句条件的`()`功能

```java
@Test
public void testSelect() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    // SELECT uid,username,password,sex,email FROM user WHERE (username LIKE ? AND (email LIKE ? OR password LIKE ?))
    queryWrapper.like("username","admin").
            and(i->{
                i.like("email","000")
                        .or().like("password","000");
            });
    List<User> userList = userMapper.selectList(queryWrapper);
    System.out.println(userList);
}
```



#### 4.1.4 指定字段查询

```java
@Test
public void testSelectColumn() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("username","password")
            .eq("username","lisi")
            .eq("password","1234");
    List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
    System.out.println(maps);
}
```


#### 4.1.5 子查询

```java
@Test
public void testSubSelect() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.ltSql("uid","select avg(uid) from user");
    List<User> userList = userMapper.selectList(queryWrapper);
    System.out.println(userList);
}
```

#### 4.1.6 动态查询

```java
@Test
public void testSelectOfCondition() {
    String username = "admin";
    String sex = "男";
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like(username != null && !"".equals(username), "username",username)
            .eq(sex != null && !"".equals(sex), "sex",sex);
    List<User> userList = userMapper.selectList(queryWrapper);
    System.out.println(userList);
}
```

### 4.2 UpdateWrapper

```java
@Test
public void testUpdateWrapper() {
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("username","lisi")
            .eq("password","1234")
            .set("password","123456");
    int result = userMapper.update(null, updateWrapper);
    System.out.println("result->" + result);
}
```

### 4.3 LambdaQueryWrapper

```java
@Test
public void test09() {
    //定义查询条件，有可能为null（用户未输入）
    String username = "a";
    Integer ageBegin = 10;
    Integer ageEnd = 24;
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    //避免使用字符串表示字段，防止运行时错误
    queryWrapper
        .like(StringUtils.isNotBlank(username), User::getName, username)
        .ge(ageBegin != null, User::getAge, ageBegin)
        .le(ageEnd != null, User::getAge, ageEnd);
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
}
```

## 5. 插件

### 5.1 分页插件

* 配置类
* 使用selectPage方法，传入`new Page<>(pageNum, pageSize);`
* Page对象
    * `page.getCurrent()`当前页
    * `page.getSize()`每页显示的条数
    * `page.getTotal()`总记录数
    * `page.getPages()`总页数
    * `page.hasPrevious()`是否有上一页
    * `page.hasNext()`是否有下一页

```java
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor () {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

```java
@SpringBootTest
public class PageTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage() {
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPage(page,null);
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }
}
```

### 5.2 自定义分页

```java
@Mapper
public interface UserMapper extends BaseMapper<User> {
    Page<User> listUser(@Param("page") Page<User> page);
}
```

```xml
<select id="listUser" resultType="com.example.pojo.User">
    select * from user
</select>
```

```java
@Test
public void testPageXml() {
    Page<User> page = new Page<>(2, 3);
    userMapper.listUser(page);
    System.out.println(page.getRecords());
    System.out.println(page.getTotal());
}
```

### 5.3 乐观锁

>一件商品，成本价是80元，售价是100元。老板先是通知小李，说你去把商品价格增加50元。小李正在玩游戏，耽搁了一个小时。正好一个小时后，老板觉得商品价格增加到150元，价格太高，可能会影响销量。又通知小王，你把商品价格降低30元。  
此时，小李和小王同时操作商品后台系统。小李操作的时候，系统先取出商品价格100元；小王也在操作，取出的商品价格也是100元。小李将价格加了50元，并将100+50=150元存入了数据库；小王将商品减了30元，并将100-30=70元存入了数据库。是的，如果没有锁，小李的操作就完全被小王的覆盖了。  
现在商品价格是70元，比成本价低10元。几分钟后，这个商品很快出售了1千多件商品，老板亏1万多。  
如果是悲观锁，小李取出数据后，小王只能等小李操作完之后，才能对价格进行操作，也会保证最终的价格是120元。  
乐观锁，小王保存价格前，会检查下价格是否被人修改过了。如果被修改过了，则重新取出的被修改后的价格，150元，这样他会将120元存入数据库。

* 配置类添加拦截器
* 表中添加version字段
* 实体类添加version属性，并用`@version`标注

```java
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
```

```java
@Test
public void testLock() {
    Account account1 = accountService.getById(1); // 1号获取
    Account account2 = accountService.getById(1); // 2号获取

    account1.setMoney(account1.getMoney() + 50);
    accountService.updateById(account1); // 添加后version字段数值 +1

    account2.setMoney(account2.getMoney() - 30);
    // account2的version是0 ,然而上一次操作已 +1，所以这次会失败
    boolean b = accountService.updateById(account2); 
    if (!b) {  // 上面添加失败，重新操作
        account2 = accountService.getById(1); // 2号重新获取
        account2.setMoney(account2.getMoney() - 30);
        accountService.updateById(account2);
    }
}
```

## 6. 代码生成器

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.5.1</version>
</dependency>
<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>2.3.31</version>
</dependency>
```

```
public static void main(String[] args) {
    FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/mybatis_plus?characterEncoding=utf-8&userSSL=false", "root", "123456")
                    .globalConfig(builder -> {
                        builder.author("") // 设置作者
                                //.enableSwagger() // 开启 swagger 模式
                                .fileOverride() // 覆盖已生成文件
                                .outputDir("D://mybatis_plus"); // 指定输出目录
                    })
                    .packageConfig(builder -> {
                        builder.parent("com.atguigu") // 设置父包名
                                .moduleName("mybatisplus") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://mybatis_plus"));
// 设置mapperXml生成路径
                    })
                    .strategyConfig(builder -> {
                        builder.addInclude("t_user") // 设置需要生成的表名
                                .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    })
                    .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker 引擎模板，默认的是Velocity引擎模板
                    .execute();
}
```

## 7. 多数据源

* 引入starter
* 配置yaml，多数据源
* 在sevice的实现类上使用`@DS("master")`标记，value指定数据源的名字

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
    <version>3.5.0</version>
</dependency>
```

```yaml
spring:
  datasource:
    dynamic:
      # 设置默认的数据源或者数据源组,默认值即为master
      primary: master
      # 严格匹配数据源,默认false.true未匹配到指定数据源时抛异常,false使用默认数据源
      strict: false
      datasource:
        master:
          url: jdbc:mysql://localhost:3306/temp?useSSL=false&characterEncoding=utf-8
          username: root
          password: 123
          driver-class-name: com.mysql.jdbc.Driver
        db_1:
          url: jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8
          username: root
          password: 123
          driver-class-name: com.mysql.jdbc.Driver
```

```java
@Service
@DS("master") // 指定数据源
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
}
```

```java
@Service
@DS("db_1") // 指定数据源
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{
}
```

```java
@Test
public void testDatas() {
    Account account = accountService.getById(1);
    System.out.println(account);
    User user = userService.getById(1L);
    System.out.println(user);
}
```















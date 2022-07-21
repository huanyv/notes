# JDBC学习笔记

## 目录

[TOC]

## 下载链接

* [MySQL Connector/J](https://downloads.mysql.com/archives/c-j/)
* classpath=.;jar包路径

## JDBC概述

JDBC是一套连接和操作数据库的标准、规范。通过提供`DriverManager`、`Connection`、`Statement`、`ResultSet`等接口将开发人员与数据库提供商隔离，开发人员只需要面对JDBC接口，无需关心怎么跟数据库交互。

## JDBC操作步骤

1. 注册驱动
2. 获取连接
3. 获取数据库操作对象
4. 执行SQL语句
5. 处理查询结果集
6. 释放资源

## 常用的接口、类和方法

* `Class.forName()` 这个方法可以类加载，会完成驱动的注册
* 驱动的包名
    * 5.0版本`com.mysql.jdbc.Driver`
    * 8.0版本`com.mysql.cj.jdbc.Driver`
* `DriverManager` 驱动管理器
    * `registerDriver()` 注册驱动
    * `getConnection()` 获取连接
        * url
            * 5.0`url=jdbc:mysql://127.0.0.1:3306/mydatabase?useSSL=false&useUnicode=true&characterEncoding=utf-8`
            * 8.0`url=jdbc:mysql://127.0.0.1:3306/mydatabase?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8`
* `Connection` 数据库连接
    * `createStatement()` 创建并获取数据库操作对象
    * `prepareStatement()` 获取预编译操作对象
    * `setAutoCommit()`关闭MySQL自动提交，开启事务
    * `commit()` 提交事务
    * `rollback()` 回滚事务
* `Statement` SQL执行器
    * `executeUpdate()` 执行DML，返回执行条数
    * `executeQuery()` 执行DQL，返回ResultSet结果集
* `preparedStatement` 预编译对象
    * `setxxxx()`  给定预编译值
* `ResultSet` 查询结果集
    * `next()` 结果集跌代
    * `getxxxx()` 获取结果集值xxx处int double String
* `close()` 关闭方法


```java
import java.sql.*;

public class JDBCTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
            // 1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
            // 2、获取连接
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/bjpowernode","root","2233");
			// (mysql8)的url   jdbc:mysql://127.0.0.1/bjpowernode?serverYimezone=UTC
            // 3、获取数据库操作对象
			stmt = conn.createStatement();
            // 4、执行SQL语句
			String sql = "select e.sal as sal from emp e";
			rs = stmt.executeQuery(sql);
            // 5、处理查询结果集
			while(rs.next()) {
			    System.out.println(rs.getString("sal"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		    // 6、释放资源
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
```

## SQL注入

### 什么是SQL注入

* SQL注入是比较常见的网络攻击方式之一，它不是利用操作系统的BUG来实现攻击，而是针对程序员编写时的疏忽，通过SQL语句，实现无账号登录，甚至篡改数据库。
* 用户通过客户端输入就是或参数成为了要执行SQL语句的一部分，面改变了SQL语句的执行目的。

```
String sql = "select * from user_table where username = '" + userName + "' and password = '" + password + "'";

aaa

bbb' or '1' = '1

--当输入了上面的用户名和密码，上面的SQL语句变成：

String sql = "select * from user_table where username='aaa' and password = 'bbb' or '1' = '1'";

--分析SQL语句：

'1' = '1'一定会成功，而前面又用了or，并且and的优先级要高于or，所以  xxx and xxx 不管结果是什么，这条语句一定会通过。

SELECT * FROM user_table WHERE
username='' ;DROP DATABASE (DB Name) --' and password=''
--其后果可想而知…
"""
```

### SQL预编译

* 参数化的语句进行预编译
* 因为预编译的原因，PrepardStatement要比Statement的效率要高一些
* 大部分时候用PreparedStatement，个别业务需求要用SQL注入就用Statement

```java
import java.sql.*;

public class JDBCTest05 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/bjpowernode","root","2233");
            // 获取预编译操作对象并预编译
            String sql = "select ename,sal from emp where job = ?";
            ps = conn.prepareStatement(sql);
            
            //写入参数值
            ps.setString(1,"CLERK");
            //获取结果集，这次就不要编译了
            rs = ps.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getString("ename") + "," + rs.getString("sal"));
            }


        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

## 事务

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest06 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/bjpowernode","root","2233");
            //打开事务
            conn.setAutoCommit(false);
            String sql1 = "insert into dept (deptno,dname,loc) values(?,?,?)";
            String sql2 = "update dept set loc = ? where deptno = ?";
            ps1 = conn.prepareStatement(sql1);
            ps2 = conn.prepareStatement(sql2);
            ps1.setInt(1,50);
            ps1.setString(2,"开发");
            ps1.setString(3,"南京");

            ps2.setString(1,"北京");
            ps2.setInt(2,50);
            int count1 = ps1.executeUpdate();
            int count2 = ps2.executeUpdate();

            System.out.println(count1==1&&count2==1 ? "修改成功" : "修改失败");

            //提交事务
            conn.commit();

        } catch (ClassNotFoundException e) {
            try {
                // 回滚事务
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}

```


## 数据库依赖

### commons-dbutils

* commons-dbutils是Apache开源组织提供的用于操作数据库的工具包。
* 下载地址：<https://commons.apache.org/>

#### 方法列表

* `org.apache.commons.dbutils`（该包中的类主要帮助我们更便捷的操作JDBC）
* `org.apache.commons.dbutils.handlers`（该包中的类都是实现`org.apache.commons.dbutils.ResultSetHandler`接口的实现类）
* `org.apache.commons.dbutils.wrappers`（该包中的类主要是封装了对Sql结果集的操作）


* `public Object query(Connection conn, String sql, Object[] params, ResultSetHandler rsh) throws SQLException`：执行一个查询操作，在这个查询中，对象数组中的每个元素值被用来作为查询语句的置换参数。该方法会自行处理 PreparedStatement 和 ResultSet 的创建和关闭。
* `public Object query(String sql, Object[] params, ResultSetHandler rsh) throws SQLException`:　几乎与第一种方法一样；唯一的不同在于它不将数据库连接提供给方法，并且它是从提供给构造方法的数据源(DataSource) 或使用的setDataSource 方法中重新获得 Connection。
* `public Object query(Connection conn, String sql, ResultSetHandler rsh) throws SQLException` : 执行一个不需要置换参数的查询操作。
* `public int update(Connection conn, String sql, Object[] params) throws SQLException`:用来执行一个更新（插入、更新或删除）操作。
* `public int update(Connection conn, String sql) throws SQLException`：用来执行一个不需要置换参数的更新操作。


返回值	|方法名|	说明
-|-|-
int[]|	batch(Connection conn, String sql, Object[][] params)|	批量执行INSERT、UPDATE或DELETE
int[]|	batch(String sql, Object[][] params)	|批量执行INSERT、UPDATE或DELETE
T  	  | insert(Connection conn, String sql, ResultSetHandler rsh)|	执行一个插入查询语句
T	  | insert(Connection conn, String sql, ResultSetHandler rsh, Object… params)|	执行一个插入查询语句
T	  | insert(String sql, ResultSetHandler rsh)|	执行一个插入查询语句
T	  | insert(String sql, ResultSetHandler rsh, Object… params)|	执行一个插入查询语句
T	  | insertBatch(Connection conn, String sql, ResultSetHandler rsh, Object[][] params)|	批量执行插入语句
T	  | insertBatch(String sql, ResultSetHandler rsh, Object[][] params)|	批量执行插入语句
T	  | query(Connection conn, String sql, ResultSetHandler rsh)|	查询
T	  | query(Connection conn, String sql, ResultSetHandler rsh, Object… params)|	查询
T	  | query(String sql, ResultSetHandler rsh)	|查询
T	  | query(String sql, ResultSetHandler rsh, Object… params)	|查询
int	  | update(Connection conn, String sql)	|执行INSERT、UPDATE或DELETE
int	  | update(Connection conn, String sql, Object… params)	|执行INSERT、UPDATE或DELETE
int	  | update(Connection conn, String sql, Object param)|	执行INSERT、UPDATE或DELETE
int	  | update(String sql)	|执行INSERT、UPDATE或DELETE
int	  | update(String sql, Object… params)	|执行INSERT、UPDATE或DELETE
int	  | update(String sql, Object param)	|执行INSERT、UPDATE或DELETE

#### 参数说明

* **`ScalarHandler<>()`返回一个对象，用于读取结果集中第一行指定列的数据。（单个数据）**
* `ArrayHandler()`返回一个数组，用于将结果集第一行数据转换为数组。
* `ArrayListHandler()`会返回一个集合，集合中的每一项对应结果集指定行中的数据转换后的数组。
* `KeyedHandler<>()`会返回一个Map，我们可以指定某一列的值作为该Map的键，Map中的值为对应行数据转换的键值对，键为列名。
* `ColumnListHandler<>()`会返回一个集合，集合中的数据为结果集中指定列的数据。
* `MapHandler()`会将结果集中第一行数据转换为键值对，键为列名。
* `MapListHandler()`会将结果集中的数据转换为一个集合，集合中的数据为对应行转换的键值对，键为列名 
* **`BeanHandler<>()`实现了将结果集第一行数据转换为Bean对象。**
* **`BeanListHandler<>()`会将结果集的所有行进行转换，返回一个集合。**
* `BeanMapHandler<>()`也会将结果集转换为Bean对象，不过返回的是已指定列的值作为键的键值对。

```java
abstract class BaseDAO {
    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql, Object... agrs) {
        Connection conn = JdbcUtils.getConnection();
        try {
            int update = queryRunner.update(conn, sql, agrs);
            return update;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return -1;
    }

    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            T query = queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();
        try {
            List<T> query = queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    public Object queryForOneValue(String sql,Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            Object query = queryRunner.query(conn,sql, new ScalarHandler(), args);
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
    
}
```


### Druid

* Druid使用步骤及详细案例(赞)：<https://blog.csdn.net/qq_43619271/article/details/107765166?utm_source=app&app_version=4.21.0&code=app_1562916241&uLinkId=usr1mkqgl919blen>
* github<https://github.com/alibaba/druid/>

```
username=root
password=123
url=jdbc:mysql://localhost:3306/book
driverClassName=com.mysql.jdbc.Driver
initialSize=5
maxActive=10
```

```java
public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){
        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();//从数据库连接池中获取连接
                conns.set(conn); // 保存到ThreadLocal对象中，供后面的jdbc操作使用
                conn.setAutoCommit(false); // 设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null) { // 如果不等于null，说明 之前使用过连接，操作过数据库
            try {
                connection.commit(); // 提交 事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null) { // 如果不等于null，说明 之前使用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }


    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
    public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```
# MySQL学习笔记

## 目录

[TOC]

## 下载链接

* [MySQL](https://dev.mysql.com/downloads/mysql/)
* [Navicat](https://navicat.com.cn/products)
* [DBeaver](https://github.com/dbeaver/dbeaver)
* [MySQL安装教程](https://blog.csdn.net/u013235478/article/details/50623693)

## 数据库概述

### SQL、DB、DBMS

* DB:DataBase（数据库，数据库实际上在硬盘上以文件的形式存在）
* DBMS: DataBase Management System（数据库管理系统，常见的有：MySQL Oracle DB2 Sybase SqlServer...）
* SQL: 结构化查询语言，是一门标准通用的语言。标准的sql适合于所有的数据库产品。SQL语句在执行的时候，实际上内部也会先进行编译，然后再执行sql。（sql语句的编译由DBMS完成。）
* DBMS负责执行sql语句，通过执行sql语句来操作DB当中的数据。
* DBMS -(执行)-> SQL -(操作)-> DB

### 表（table）是什么

* table
* table是数据库的基本组成单元，所有的数据都以表的形式组织，目的是可读性强。
* 一个表可以包括行和列
    * 行：被称为数据、记录(data)
    * 列：被称为字段(column)字段名、数据类型、相关的约束。

### SQL语句分类

1. DQL（数据查询语言）：查询语句，凡是select语句都是DQL。
2. DML（数据操作语言）：insert delete update，对表当中的数据进行增删改。
3. DDL（数据定义语言）：create drop alter，对表结构的增删改。
4. TCL（事务控制语言）：commit提交事务，rollback回滚事务。(TCL中的T是Transaction)
5. DCL（数据控制语言）：grant授权、revoke撤销权限等。

### 导入数据

1. 登录：`mysql -uroot -p`
2. 查看有哪些数据库：`show databases;`
3. 创建属于自己的数据库：`create database bjpowernode;`
4. 使用自己刚刚创建的数据库：`use bjpowernode;`
5. 查看数据库中的表：`show tables;`
6. 写入数据：`source Path`
7. 删除数据库：`drop database bjpowernode;`
8. 这些都不是SQL语句，属于MySQL自己的命令。

## 常用命令

1. `select database();` 查看当前使用的数据库
2. `select version();` 查看当前MySQL版本
3. `\c` 结束当前语句
4. `exit`、`quit`、`\q` 退出MySQL
5. `show create database 数据库名;`查看数据库的创建语句
5. `show create table 表名` 查看表的创建语句
6. `show tables from 表名;` 查看其它数据库中的表
7. `desc 表名;` 查看表的结构
8. `drop table 表名`删除表

## 简单查询

1. `select 字段 form 表名;`查询一个字段
2. `select 字段名1,字段名2,字段名3,.... from 表名;` 查询多个字段
3. `select empno, ename, sal*12 from emp;` 查询时可以执行运算
4. `select * from emp;` 查询所有字段（开发中不建议使用，因为效率比较低）
5. `select empno as ‘员工编号’, ename as ‘员工姓名’, sal*12 as ‘年薪’ from emp;` as 可以用来重命名，可以省略，**中文和字符串用`''`**

## 条件查询

运算符           | 说明
-----------------|---------------------------------------------
=                | 等于
<>或!=           | 不等于
<                | 小于
<=               | 小于等于
>                | 大于
>=               | 大于等于
between...and... | 两个值之间（等同于 >= and <= ）闭区间左小右大
is null          | 为 null （is not null 不为空）
and              | 并且（与）
or               | 或者（或）
in               | 包含
not              | 取非（用在 is 和 in 中）
like             | 模糊查询('_'单个字符、'%'多个字符)

* 语法： `select 字段 from 数据 where 条件`
* `between ... and ...`可以用在字符串方面，左闭右开
* NULL不等于0
* 任意数与NULL做任意运算，结果都是NULL
    * 解决方法：**空处理函数**：`ifnull(可能为NULL的字段，代替的值)` 

## 排序数据

* 语法：
    * `select 字段 from 数据 order by 字段;`
    * `select 字段 from 数据 where 条件 order by 字段;` 默认是升序
    * `select 字段 from 数据 where 条件 order by 字段 asc;` 升序
    * `select 字段 from 数据 where 条件 order by 字段 desc;` 降序
    * `select 字段 from 数据 where 条件 order by 字段 desc,字段 desc;` 多个排序条件
* 可以用数字表示排序第几列`select * from emp order by 5;`
* 多个排序时越靠前的字段越有主导作用，只有当前字段的数据一样时，才会按照后面的字段排序。

## 分组函数


* `count()` 计数
* `sum()` 求和
* `avg()` 取平均值
* `max()` 取最大值
* `min()` 取最小值
* 分组函数自动忽略空值NULL，不需要手动的加where条件排除空值。
* `select sum(sal) from emp;` 不为NULL的薪资的总和
* **分组函数不能用在where语句中。**
* 可以将这些组合函数都放到select中一起使用 `select count(*),sum(sal),avg(sal),max(sal),min(sal) from emp;`
* count(*)和count(具体的某个字段)，他们有什么区别？
    * count(*):不是统计某个字段中数据的个数，而是统计总记录条数。包含NULL（和某个字段无关）
    * count(comm): 表示统计comm字段中不为NULL的数据总数量。
    * count(1): ?
* 多行处理函数：若干行数据一起处理
    * 分组函数
* 单行处理函数：一行一行的处理
    * `ifnull()`


### group by 和 having

* group by：按照某个字段或者某些字段进行分组。
    * 分组函数一般都会和`group by`联合使用
    * 任何一个分组函数都是在`group by`之后执行的
    * 当一条SQL语句没有用`group by`的话，整张表会自成一组。
    * **当一条语句中有group by的话，select后面只能跟分组函数和参与分组的字段。**
    * 多个字段可以联合一起分组。
* having：是对分组之后的数据进行再次过滤。
    * 能用where过滤的话，尽量使用where。
    * 如果没有使用`group by`的话，不能使用`having`。

### 一个完整的DQL语句

```sql
select	    	5
	...
from			1
	...		
where			2
	...	
group by		3
	...
having	    	4
	...
order by		6
	...
limit			7
	...;
```

### 查询去重

* `distinct`关键字
* `mysql> select distinct job from emp;`
* mysql> select ename,distinct job from emp;
    * 以上的sql语句是错误的，**distinct只能出现在所有字段的最前面。**（联合去重）
    * mysql> select distinct deptno,job from emp;
* 统计岗位的数量？`select count(distinct job) from emp;`

## 连接查询

* 在实际开发中，大部分情况下都不是从单表中查询数据，一般都是多张表联合查询取出最终结果。
* 连接查询的分类
    * 根据年代来分
        * SQL92
        * SQL99
    * 根据连接方式
        * 内连接
            * 等值连接（连接条件是等量关系）
            * 非等值连接（连接条件是非等量关系）
            * 自连接（一张表看做两张表。自己连接自己）
        * 外连接
            * 左外连接
            * 右外连接
        * 全连接

### 笛卡尔积现象

* `select ename,dname from emp,dept;`
* 别名方式：`select e.ename,d.dname from emp e,dept d;`
* 在表的连接查询方面有一种现象被称为：笛卡尔积现象。（笛卡尔乘积现象）
* **笛卡尔积现象**：当两张表进行连接查询的时候，没有任何条件进行限制，最终的查询结果条数是两张表记录条数的乘积。
* 笛卡尔现象的避免
    * 加条件过滤
    * **使用条件过滤避免笛卡尔现象，匹配次数并没有减少，并不会提高查询效率**

```mysql
select	
	e.ename,d.dname
from
	emp e , dept d
where                     // 过滤条件
	e.deptno = d.deptno; //SQL92，以后不用。联接关系
//-------------------------------------------------------
select 
	e.ename,d.dname
from
	emp e
inner join   // inner可以省略的，带着inner目的是可读性好一些。
	dept d
on
	e.deptno = d.deptno;     // 联接关系，过滤条件
```

### 外连接

* 什么是外连接？和内连接有什么区别？
    * **内连接**在查询的结果中只有匹配成功的，只有符合匹配条件的数据，内连接的两张表没有主副之分，两张表是平等的。
    * **外连接**的表中有主表，有副表，主要查询主表中的内容，捎带查询副表。主表中的所有数据**一定**会被查询出来，当副表中没有数据和主表匹配，会自动用NULL与之匹配。
    * 外连接的SQL语句上有left和right，用来指定主表，内连接没有。
* 外连接的分类
    * 左外连接（左边是主表）
    * 右外连接（右边是主表）
    * 左右外连接的写法可以互相转换
* 语法：
    * 内连接：`select 字段... from 表1 inner join 表2 on 过滤条件`
    * 外连接：`select 字段... from 表1 left/right outer join 表2 on 过滤条件`
    * `inner`和`outer`可以省略

```mysql
select d.* from emp e right outer join dept d on d.deptno=e.deptno where e.ename is null;
+--------+------------+--------+
| DEPTNO | DNAME      | LOC    |
+--------+------------+--------+
|     40 | OPERATIONS | BOSTON |
+--------+------------+--------+
```

### 多表连接

```mysql
找出每一个员工的部门名称以及工资等级。
mysql> select 
            e.ename,d.dname,e.sal,s.grade 
        from 
            emp e 
        left join 
            dept d 
        on 
            e.deptno=d.deptno 
        left join 
            salgrade s 
        on 
            e.sal between losal and hisal;

+--------+------------+---------+-------+
| ename  | dname      | sal     | grade |
+--------+------------+---------+-------+
| SMITH  | RESEARCH   |  800.00 |     1 |
| ALLEN  | SALES      | 1600.00 |     3 |
| WARD   | SALES      | 1250.00 |     2 |
| JONES  | RESEARCH   | 2975.00 |     4 |
| MARTIN | SALES      | 1250.00 |     2 |
| BLAKE  | SALES      | 2850.00 |     4 |
| CLARK  | ACCOUNTING | 2450.00 |     4 |
| SCOTT  | RESEARCH   | 3000.00 |     4 |
| KING   | ACCOUNTING | 5000.00 |     5 |
| TURNER | SALES      | 1500.00 |     3 |
| ADAMS  | RESEARCH   | 1100.00 |     1 |
| JAMES  | SALES      |  950.00 |     1 |
| FORD   | RESEARCH   | 3000.00 |     4 |
| MILLER | ACCOUNTING | 1300.00 |     2 |
+--------+------------+---------+-------+


找出每一个员工的部门名称、工资等级、以及上级领导。
mysql> select 
            e.ename,d.dname,s.grade,e1.ename 
        from 
            emp e 
        left join 
            dept d 
        on 
            d.deptno=e.deptno 
        left join 
            salgrade s 
        on 
            e.sal between s.losal and hisal 
        left join 
            emp e1 
        on 
            e1.empno=e.mgr;

+--------+------------+-------+-------+
| ename  | dname      | grade | ename |
+--------+------------+-------+-------+
| SMITH  | RESEARCH   |     1 | FORD  |
| ALLEN  | SALES      |     3 | BLAKE |
| WARD   | SALES      |     2 | BLAKE |
| JONES  | RESEARCH   |     4 | KING  |
| MARTIN | SALES      |     2 | BLAKE |
| BLAKE  | SALES      |     4 | KING  |
| CLARK  | ACCOUNTING |     4 | KING  |
| SCOTT  | RESEARCH   |     4 | JONES |
| KING   | ACCOUNTING |     5 | NULL  |
| TURNER | SALES      |     3 | BLAKE |
| ADAMS  | RESEARCH   |     1 | SCOTT |
| JAMES  | SALES      |     1 | BLAKE |
| FORD   | RESEARCH   |     4 | JONES |
| MILLER | ACCOUNTING |     2 | CLARK |
+--------+------------+-------+-------+

```

## 子查询

* select语句中嵌套select语句，被嵌套的select语句就是子查询。
* 子查询可以出现在哪里？
* `select ..(select).. from ..(select).. where ..(select)..`
* 在from 后用子查询可以把子查询的结果看成一张新表，可以重命名。
    * `select t.xxx from (select语句) t where 条件`

## union和limit

### union

* 可以合并集合,可以将查询的两张表连接。
* `select * from emp where job='MANAGER' union select * from emp where job='SALESMAN'`
* 合并结果集的时候，需要查询字段对应个数相同。在Oracle中更严格，不但要求个数相同，

### limit

* limit是mysql特有的，其他数据库中没有，不通用。（Oracle中有一个相同的机制，叫做rownum）
* limit取结果集中的部分数据，这是它的作用。
* limit是sql语句最后执行的一个环节。
* 语法：
    * `select 字段 from 表 oeder by 字段 asc/desc limit startIndex, length`
    * `select 字段 from 表 oeder by 字段 asc/desc limit length`
    * 如果没有起始位默认从0开始。

delete 后面是支持 limit 关键字的，但仅支持单个参数，也就是 [limit row_count]，用于告知服务器在控制命令被返回到客户端前被删除的行的最大值。  

有什么用？

1. 降低写错SQL的代价，就算删错了，比如limit 1,那也就丢了1条数据，并不致命，通过binlog也可以很快恢复数据。
1. 对于单条删除和更新操作，在 delete 和 update 后面加 limit 1，假如第一条就命中了删除行，这时就 return 了，否则还会执行完全表扫描才 return。
2. `delete from t where id = 1 limit 1; `

#### 通用的标准分页SQL

每页显示pageSize条记录：  
第pageNo页：(pageNo - 1) * pageSize, pageSize  
java代码  
```java
{
	int pageNo = 2; // 页码是2
	int pageSize = 10; // 每页显示10条
	
	limit (pageNo - 1) * pageSize, pageSize
}
```

## 查询技巧

1. 第几多
    * 去重，升序排序，用limit
2. 第二多
    * 找出第一，从小于第一中找最大值
    * `select * from emp t1 where (select count(distinct sal) from emp t2 where t2.sal > t1.sal) = 2`
3. 

## 增删改(CRUD)

### DDL（表的结构）

#### create

语法：  

```mysql
create table 表名(
	字段名1 数据类型(长度限制) default 默认值 列级约束, // 可不写
	字段名2 数据类型(长度限制),
	字段名3 数据类型(长度限制),
	....
	表级约束(字段名,...) // 可不写
) engine=存储引擎 charset=字符集 comment='说明注释' //可不写
```

**MySQL常用数据类型**

类型                         | 描述
-----------------------------|-------------------------------------
char(长度)                   | 定长字符串
varchar()                    | 变长字符串
double(有效数字位数，小数位) | 数值型
float(有效数字位数，小数位)	 | 数值型
decimal                      |对应Java的bigDecimal，高精度
int( 长度)	                 | 整型
bigint(长度)	             | 长整型
Date                         | 日期型 年月日
DateTime                     | 日期型 年月日 时分秒 毫秒
time                         | 日期型 时分秒 
BLOB                         | Binary Large OBject（二进制大对象）
CLOB                         | Character Large OBject（字符大对象）
enum(数据,数据,...)          | 枚举
其它…………………	                 |     

#### alter

* ---------修改表名：`alter table 表名 rename to 新表名;`
* ------修改字段名：`alter table 表名 change 字段名 新字段名 新字段类型;`
* 修字段数据类型：`alter table 表名 modify 字段名 新字段类型;`
* ---------增加字段：`alter table 表名 add 字段名 字段类型;`
* ---------删除字段：`alter table 表名 drop 字段名;`

#### drop

* 删除表
    * `drop table 表名;` // 这个通用。
	* `drop table if exists 表名;` // 如果存在删除，oracle不支持这种写法。

### DML（表的内容）

#### insert

* 语法：
    1. `insert into 表名(字段名1,字段名2,字段名3,....) values(值1,值2,值3,....)`
        * 字段和写入值的数里相同
    2. `insert into 表名 values(值1,值2,值3,....)`
        * 字段可以省略不写，values中的数据数量与顺序必须和原表相同
    3. `insert into 表名(字段名1,字段名2,字段名3,....) values(值1,值2,值3,....),(值1,值2,值3,....)`
        * 可以同时插入多行数据
* 如果插入的数据不全，会自动补NULL。
* 当一条insert语句执行后，表中必然会多一行数据。无法用insert修改单行中的单个数据。

##### 表的复制

* `create table 表名 as select语句;`将查询结果当做表创建出来。
* `insert into 表名 select语句`查询结果插入到一张表中

#### update

* 语法：
* `update 表名 set 字段名1=值1,字段名2=值2... where 条件;`
* `update 表名 set 字段名1=值1,字段名2=值2...;`
* 没有条件会将整个表全部更新。

#### delete

* 语法：
    * `delete from 表名 where 条件;`
    * `delete from dept1;`
* 删除大表中的数据？（重点）
    * `truncate table 表名;` // 表被截断，不可回滚。永久丢失。

## 约束

* 在创建表的时候，可以给表的字段添加相应的约束，添加约束的目的是为了保证表中数据的合法性、有效性、完整性。
* 约束有**列级约束**和**表级约束**
* 常见的约束有哪些呢？
    * 非空约束(not null)：约束的字段不能为NULL
    * 唯一约束(unique)：约束的字段不能重复
    * 主键约束(primary key)：约束的字段既不能为NULL，也不能重复（简称PK）
    * 外键约束(foreign key)：...（简称FK）
    * 检查约束(check)：注意Oracle数据库有check约束，但是mysql没有，目前mysql不支持该约束（好像最新的支持了），但不会报错。(<https://dev.mysql.com/doc/refman/8.0/en/create-table-check-constraints.html>)

### 非空约束

* `not null`
* 针对某个字段insert时，其值不能为空。
* not null约束只有列级约束。没有表级约束。

```mysql
drop table if exists t_stu;
create table t_stu(
	id int(2) not null,
	name varchar(255),
	sex char(1)
)
```

### 唯一性约束

* `unique`
* `unique(字段1,字段2, ...)`
* 唯一约束修饰的字段具有唯一性，不能重复。但可以为NULL。
* 支持列级约束和表级约束


### 主键约束

* `primary key`
* `primary key(字段1,字段2, ...)`
* 主键约束的字段不能为空，也不能重复。
* 主键的作用：主键值是这行记录在这张表当中的唯一标识。
* **一张表的主键约束只能有1个**
* 主键的分类
    * 根据字段数量分
        * 单一主键（推荐使用）
        * 复合（联合）主键(多个字段联合起来添加一个主键约束起来添加一个主键约束 ，不建议使用，因为复合主键违背三范式。)
    * 根据主键性质分
        * 自然主键：主键值最好就是一个和业务没有任何关系的自然数
        * 业务主键：主键值和系统的业务挂钩（身份证号、银行卡号）
* **MySQL提供主键值自增类型**：列级`auto_increment`

### 外键约束


* `foreign key(外键字段) references 父表(父表主键字段)`
* 外键主要是维护表之间的关系的，主要是为了保证参照完整性，如果表中的某个字段为外键字段，那么该字段的值必须来源于参照的表的主键
* 存在外键的表就是子表，参照的表就是父表，所以存在一个父子关系，也就是主从关系，主表就是班级表，从表就是学生表
* 外键值可以为NULL
* 删除数据的时候，先删除子表，再删除父表。添加数据的时候，先添加父表，在添加子表。创建表的时候，先创建父表，再创建子表。删除表的时候，先删除子表，在删除父表。
* **被引用的字段不一定是主键，但至少具有unique约束。**


## 存储引擎*

### 存储引擎概述

* 查看当前mysql支持的存储引擎：`show engines \G`
* 完整的建表语句
    * `CREATE TABLE `t_x` (`id` int(11) DEFAULT NULL)ENGINE=InnoDB DEFAULT CHARSET=utf8;`
    * 在MySQL当中，凡是标识符是可以使用反引号括起来的。
* mysql默认使用的存储引擎是InnoDB方式，默认采用的字符集是UTF8。
* 存储引擎这个名字只有在MySQL中存在。（Oracle中有对应的机制，但是不叫做存储引擎。Oracle中没有特殊的名字，就是“表的存储方式”）

### 常见的存储引擎

```
Engine: MyISAM
	  Support: YES
	  Comment: MyISAM storage engine
Transactions: NO
			 XA: NO
  Savepoints: NO
```

* MyISAM这种存储引擎不支持事务。
* MyISAM是mysql最常用的存储引擎，但是这种引擎不是默认的。
* MyISAM采用三个文件组织一张表：
    * xxx.frm（存储格式的文件）
    * xxx.MYD（存储表中数据的文件）
    * xxx.MYI（存储表中索引的文件）
* 优点：可被压缩，节省存储空间。并且可以转换为只读表，提高检索效率。
* 缺点：不支持事务。

```
Engine: InnoDB
	  Support: DEFAULT
	  Comment: Supports transactions, row-level locking, and foreign keys
Transactions: YES
			 XA: YES
  Savepoints: YES
```

* 优点：支持事务、行级锁、外键等。这种存储引擎数据的安全得到保障。
* 表的结构存储在xxx.frm文件中
* 数据存储在tablespace这样的表空间中（逻辑概念），无法被压缩，无法转换成只读。
* 这种InnoDB存储引擎在MySQL数据库崩溃之后提供自动恢复机制。
* InnoDB支持级联删除和级联更新。

```
Engine: MEMORY
	  Support: YES
	  Comment: Hash based, stored in memory, useful for temporary tables
Transactions: NO
			 XA: NO
  Savepoints: NO
```

* 缺点：不支持事务。数据容易丢失。因为所有数据和索引都是存储在内存当中的。
* 优点：查询速度最快。
* 以前叫做HEPA引擎。

## 事务TCL（Transaction）

### 事务概述

* 一个事务是一个完整的业务逻辑单元，不可再分。
* 事务可以保证多个操作原子性，要么全成功，要么全失败。对于数据库来说事务保证批量的DML要么全成功，要么全失败。
* 和事务相关的语句只有DML语句（delete insert update）。
* 事务的一些概念
    * 事务（Transaction）：一批操作（一组DML）
    * 开启事务`Start Transaction`
    * 回滚事务`rollback`
    * 提交事务`commit`
    * SET AUTOCOMMIT：禁用或启用事务的自动提交模式
* rollback或者commit后事务就结束了。


### 事务的特性

**事务具有四个特征ACID**  

1. 原子性（Atomicity）
    * 整个事务中的所有操作，必须作为一个单元全部完成（或全部取消）。
2. 一致性（Consistency）
    * 在事务开始之前与结束之后，数据库都保持一致状态。
3. 隔离性(Isolation)
    * 一个事务不会影响其他事务的运行。
4. 持久性(Durability)
    * 在事务完成以后，该事务对数据库所作的更改将持久地保存在数据库之中，并不会被回滚。

### 自动提交模式

* MySQL是默认自动提交的
* 自动提交模式用于决定新事务如何及何时启动。 
* 启用自动提交模式：
    * 如果自动提交模式被启用，则单条DML语句将缺省地开始一个新的事务。 
    * 如果该语句执行成功，事务将自动提交，并永久地保存该语句的执行结果。 
    * 如果语句执行失败，事务将自动回滚，并取消该语句的结果。 
    * 在自动提交模式下，仍可使用START TRANSACTION语句来显式地启动事务。这时，一个事务仍可包含多条语句，直到这些语句被统一提交或回滚。 
* 禁用自动提交模式： 
    * 如果禁用自动提交，事务可以跨越多条语句。 
    * 在这种情况下，事务可以用COMMIT和ROLLBACK语句来显式地提交或回滚。 
* 自动提交模式可以通过服务器变量AUTOCOMMIT来控制。 
* 例如：

```mysql
mysql> SET AUTOCOMMIT = OFF； 
mysql> SET AUTOCOMMIT = ON； 
或
mysql> SET SESSION AUTOCOMMIT = OFF； 
mysql> SET SESSION AUTOCOMMIT = ON； 
show variables like '%auto%'; -- 查看变量状态
------------------------------------------------
mysql> show variables like '%auto%';
+-----------------------------+-------+
| Variable_name               | Value |
+-----------------------------+-------+
| auto_increment_increment    | 1     |
| auto_increment_offset       | 1     |
| autocommit                  | ON    |
| automatic_sp_privileges     | ON    |
| innodb_autoextend_increment | 8     |
| innodb_autoinc_lock_mode    | 1     |
| sql_auto_is_null            | OFF   |
+-----------------------------+-------+
```

### 事务的隔离级别

#### 隔离级别

* 事务的隔离级别决定了事务之间可见的级别。
* 当多个客户端并发地访问同一个表时，可能出现下面的一致性问题：
    * **脏读取（Dirty Read）** 一个事务开始读取了某行数据，但是另外一个事务已经更新了此数据但没有能够及时提交，这就出现了脏读取。
    * **不可重复读（Non-repeatable Read）** 在同一个事务中，同一个读操作对同一个数据的前后两次读取产生了不同的结果，这就是不可重复读。
    * **幻像读（Phantom Read）** 幻像读是指在同一个事务中以前没有的行，由于其他事务的提交而出现的新行。

#### 四个隔离级别

* InnoDB 实现了四个隔离级别，用以控制事务所做的修改，并将修改通告至其它并发的事务：
    * **读未提交（READ UMCOMMITTED）** 允许一个事务可以看到其他事务未提交的修改。
    * **读已提交（READ COMMITTED）** 允许一个事务只能看到其他事务已经提交的修改，未提交的修改是不可见的。
    * **可重复读（REPEATABLE READ）** 确保如果在一个事务中执行两次相同的SELECT语句，都能得到相同的结果，不管其他事务是否提交这些修改。 （银行总账）
	该隔离级别为InnoDB的缺省设置。
    * **串行化（SERIALIZABLE） 【序列化】** 将一个事务与其他事务完全地隔离。 
* mysql数据库默认的隔离级别是：可重复读。
* oracle数据库默认的隔离级别是：读已提交。

#### 设置隔离级别

* 通过修改配置文件设置 
* 可以在my.ini文件中使用transaction-isolation选项来设置服务器的缺省事务隔离级别。 
* 该选项值可以是： 
    * READ-UNCOMMITTED
    * READ-COMMITTED
    * REPEATABLE-READ
    * SERIALIZABLE
* 例如：[mysqld] transaction-isolation = READ-COMMITTED
* 通过命令动态设置隔离级别
* 隔离级别也可以在运行的服务器中动态设置，应使用SET TRANSACTION ISOLATION LEVEL语句。 
* 其语法模式为：`SET [GLOBAL | SESSION] TRANSACTION ISOLATION LEVEL <isolation-level>`
	* 其中的<isolation-level>可以是：
    * READ UNCOMMITTED
    * READ COMMITTED
    * REPEATABLE READ
    * SERIALIZABLE
* 例如： SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

#### 隔离级别的作用范围

* 事务隔离级别的作用范围分为两种： 
    * 全局级：对所有的会话有效 
    * 会话级：只对当前的会话有效 
* 例如，设置会话级隔离级别为READ COMMITTED ：
    * `mysql> SET TRANSACTION ISOLATION LEVEL READ COMMITTED;`
    * `mysql> SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;`
* 设置全局级隔离级别为READ COMMITTED ： 
    * `mysql> SET GLOBAL TRANSACTION ISOLATION LEVEL READ COMMITTED;`

#### 查看隔离级别

* 服务器变量tx_isolation（包括会话级和全局级两个变量）中保存着当前的会话隔离级别。 
* 为了查看当前隔离级别，可访问tx_isolation变量： 
    * 查看会话级的当前隔离级别：
	    * `mysql> SELECT @@tx_isolation;`
	    * `mysql> SELECT @@session.tx_isolation;`
    * 查看全局级的当前隔离级别： 
	    * `mysql> SELECT @@global.tx_isolation;`

## 索引

* 如果不使用索引会全表扫描。
* MYISAM和INNODB都是用B+Tree作为索引结构
* 怎么创建索引对象
    * 创建索引：`create index 索引名称 on 表名(字段名);`
    * 查看索引：`show index from 表名;`
    * 使用索引：`explain select语句;`，不可使用`select *`
    * 删除索引：`drop index 索引名称 on 表名;`
* 什么时候添加索引？
    * 表中该字段中的数据量庞大
    * 经常被检索，经常出现在where子句中的字段
    * 经常被DML操作的字段不建议添加索引
* 主键和具有unique约束的字段自动会添加索引。根据主键查询效率较高。尽量根据主键检索。
* 索引的分类
    * 单一索引：给单个字段添加索引
	* 复合索引: 给多个字段联合起来添加1个索引
	* 主键索引：主键上会自动添加索引
	* 唯一索引：有unique约束的字段上会自动添加索引  
* 索引会什么时候失效
    * 模糊查询的时候第一个通配符是%。

## 视图

### 视图概述

* 视图是一种根据查询（也就是SELECT表达式）定义的数据库对象，用于获取想要看到和使用的局部数据。 
* 视图有时也被成为“虚拟表”。 
* 视图可以被用来从常规表（称为“基表”）或其他视图中查询数据。
* 相对于从基表中直接获取数据，视图有以下好处：
    * 访问数据变得简单
    * 可被用来对不同用户显示不同的表的内容
* 用来协助适配表的结构以适应前端现有的应用程序
* 视图作用：
    * 提高检索效率
    * 隐藏表的实现细节【面向视图检索】

### 视图操作

* 创建视图：`create view 视图名 as select语句`
* 删除视图：`drop view 视图名`
* 只有DQL语句才能以视图对象的方式创建出来。
* 对视图的增删改查会影响到原表数据。


## DBA命令

### 数据导出

* 将数据库当中的数据导出（不用登录）
    * 导出整个库：`mysqldump 数据库名 > "路径" -uroot -p`
    * 导出单个表：`mysqldump 数据库名 表名 > "路径" -uroot -p`
* 导入数据
    * `create database bjpowernode;`
    * `use bjpowernode;`
    * `source D:\bjpowernode.sql`

### 远程连接

`mysql -h 192.168.241.128 -P 3306 -uroot -p`

### 授权（DCL）

#### 用户授权

```
mysql> grant all privileges on *.* to 'yangxin'@'%' identified by 'yangxin123456' with grant option;
```

* all privileges：表示将所有权限授予给用户。也可指定具体的权限，如：SELECT、CREATE、DROP等。
* on：表示这些权限对哪些数据库和表生效，格式：数据库名.表名，这里写“*”表示所有数据库，所有表。如果我要指定将权限应用到test库的user表中，可以这么写：test.user
* to：将权限授予哪个用户。格式：”用户名”@”登录IP或域名”。%表示没有限制，在任何主机都可以登录。比如：”yangxin”@”192.168.0.%”，表示yangxin这个用户只能在192.168.0IP段登录
* identified by：指定用户的登录密码
* with grant option; 表示该用户还可以授权给其他用户

>可以使用GRANT给用户添加权限，权限会自动叠加，不会覆盖之前授予的权限，比如你先给用户添加一个SELECT权限，后来又给用户添加了一个INSERT权限，那么该用户就同时拥有了SELECT和INSERT权限。

#### 刷新权限

```
mysql> flush privileges;
```

#### 查看用户权限

```
mysql> grant select,create,drop,update,alter on *.* to 'yangxin'@'localhost' identified by 'yangxin0917' with grant option;
```

#### 回收权限

```
 mysql> revoke create on *.* from 'yangxin@localhost';
 mysql> flush privileges;
```

#### 删除用户

```
mysql> select host,user from user;
mysql> drop user 'yangxin'@'localhost';
```

#### 用户重命名

```
shell> rename user 'test3'@'%' to 'test1'@'%';
```

#### 修改密码

```
mysql> use mysql;
mysql5.7之前
mysql> update user set password=password('123456') where user='root';
mysql5.7之后
mysql> update user set authentication_string=password('123456') where user='root';
```

## 数据库的设计三范式

* 设计表的依据。按照这个三范式设计的表不会出现数据冗余。
* **第一范式**：每张表都应该有主键，并且第一个字段原子性不可再分。
    * 每一行必须唯一，也就是每个表必须有主键，这是我们数据库设计的最基本要求，主要通常采用数值型或定长字符串表示，关于列不可再分，应该根据具体的情况来决定。如联系方式，为了开发上的便利行可能就采用一个字段了。
* **第二范式**：建立在第一范式的基础上，要求非主键字段完全依赖主键，不可产生部分依赖。
    * 如果一个表是单一主键，那么它就复合第二范式，部分依赖和主键有关系
    * 多对多：建立两张表和一张关系表，在关系表上添加外键
* **第三范式**：在第二范式的基础上，非主键字段不能传递依赖于主键。
    * 一对多：将冗余字段单独拿出来建立表，在多的那张表中添加外键指向一的一方的主键。

### 三范式总结

* 第一范式：有主键，具有原子性，字段不可分割。
* 第二范式：完全依赖，没有部分依赖。
* 第三范式：没有传递依赖。
* 数据库设计尽量遵循三范式，但是还是根据实际情况进行取舍，有时可能会拿冗余换速度，最终用目的要满足客户需求。
* 多对多：两张表，关系表上加外键。
* 一对多：两张表，多的表上加外键。
* 一对一：主键共享、外键唯一。


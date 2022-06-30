# MySQL题

[TOC]

## 部门工资前三高的所有员工

SQL架构  
Employee 表包含所有员工信息，每个员工有其对应的工号 Id，姓名 Name，工资 Salary 和部门编号 DepartmentId 。  

```
+----+-------+--------+--------------+
| Id | Name  | Salary | DepartmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 85000  | 1            |
| 2  | Henry | 80000  | 2            |
| 3  | Sam   | 60000  | 2            |
| 4  | Max   | 90000  | 1            |
| 5  | Janet | 69000  | 1            |
| 6  | Randy | 85000  | 1            |
| 7  | Will  | 70000  | 1            |
+----+-------+--------+--------------+
```

Department 表包含公司所有部门的信息。  

```
+----+----------+
| Id | Name     |
+----+----------+
| 1  | IT       |
| 2  | Sales    |
+----+----------+
```

编写一个 SQL 查询，找出每个部门获得前三高工资的所有员工。例如，根据上述给定的表，查询结果应返回：

```
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Max      | 90000  |
| IT         | Randy    | 85000  |
| IT         | Joe      | 85000  |
| IT         | Will     | 70000  |
| Sales      | Henry    | 80000  |
| Sales      | Sam      | 60000  |
+------------+----------+--------+
```

解释：  
IT 部门中，Max 获得了最高的工资，Randy 和 Joe 都拿到了第二高的工资，Will 的工资排第三。销售部门（Sales）只有两名员工，Henry 的工资最高，Sam 的工资排第二。

**答案：**  
```
select e1.Name as 'Employee', e1.Salary
from Employee e1
where
(
    select count(distinct e2.Salary)
    from Employee e2
    where e2.Salary > e1.Salary
) > 3

SELECT
    d.Name AS 'Department', e1.Name AS 'Employee', e1.Salary
FROM
    Employee e1
        JOIN
    Department d ON e1.DepartmentId = d.Id
WHERE
    3 > (SELECT
            COUNT(DISTINCT e2.Salary)
        FROM
            Employee e2
        WHERE
            e2.Salary > e1.Salary
                AND e1.DepartmentId = e2.DepartmentId
        )
;
```

## 求订单数

```sql
create table user (
  id int(10) primary key,
  name varchar(200)
);
  
 create table orders(
   user_id int(10),
   price double(10,2),
   submit_time datetime
 );
 
 insert into user (id, name) values(1001, "张三");
 insert into user (id, name) values(1002, "李四");  
 insert into user (id, name) values(1003, "王五");  
  
 insert into orders (user_id, price, submit_time) values(1001, 123.14, "2018-01-01 12:42:23");
 insert into orders (user_id, price, submit_time) values(1002, 623.11, "2018-01-11 16:42:23");  
 insert into orders (user_id, price, submit_time) values(1002, 923.37, "2018-01-21 02:42:23");  
```

```
|   id |  name |
|------|-------|
| 1001 |   张三 |
| 1002 |   李四 |
| 1003 |   王五 |

        
| user_id |  price |          submit_time |
|---------|--------|----------------------|
|    1001 | 123.14 | 2018-01-01 12:42:23  |
|    1002 | 623.11 | 2018-01-11 16:42:23  |
|    1002 | 923.37 | 2018-01-21 02:42:23  |
```

* 编写SQL语句统计 2018年1月份 ，每个人每天的订单数，输出结果字段（用户名，日期，订单数）。（注意SQL执行效率）。查询结果示例

```
|用户名 |  日期    |订单数|
------------------------
| 张三 |2018-01-01|  5  |
| 张三 |2018-01-02|  7  |
| 李四 |2018-01-30|  2  |
| 王五 |2018-01-31|  2  |
```

```sql
select t1.name 用户名, t2.date 日期, t2.count 订单数
from user t1
join (
  select user_id, substr(submit_time, 1, 10) date, count(*) count 
  from orders 
  where submit_time 
  like "2018-01%" 
  group by substr(submit_time, 1, 10), user_id
) t2
on t2.user_id = t1.id;
```
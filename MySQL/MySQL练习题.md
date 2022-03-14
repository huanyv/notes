# MySQL练习题

## table

```mysql
DROP TABLE IF EXISTS EMP;
DROP TABLE IF EXISTS DEPT;
DROP TABLE IF EXISTS SALGRADE;

CREATE TABLE DEPT (
    DEPTNO int(2) not null ,
	DNAME VARCHAR(14) ,
	LOC VARCHAR(13),
	primary key (DEPTNO)
	);
	
CREATE TABLE EMP (
    EMPNO int(4)  not null ,
	ENAME VARCHAR(10),
	JOB VARCHAR(9),
	MGR INT(4),
	HIREDATE DATE  DEFAULT NULL,
	SAL DOUBLE(7,2),
	COMM DOUBLE(7,2),
	primary key (EMPNO),
	DEPTNO INT(2) 
	);

CREATE TABLE SALGRADE (
    GRADE INT,
	LOSAL INT,
	HISAL INT );

INSERT INTO DEPT ( DEPTNO, DNAME, LOC ) VALUES ( 
10, 'ACCOUNTING', 'NEW YORK'); 
INSERT INTO DEPT ( DEPTNO, DNAME, LOC ) VALUES ( 
20, 'RESEARCH', 'DALLAS'); 
INSERT INTO DEPT ( DEPTNO, DNAME, LOC ) VALUES ( 
30, 'SALES', 'CHICAGO'); 
INSERT INTO DEPT ( DEPTNO, DNAME, LOC ) VALUES ( 
40, 'OPERATIONS', 'BOSTON'); 
commit;
 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7369, 'SMITH', 'CLERK', 7902,  '1980-12-17'
, 800, NULL, 20); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7499, 'ALLEN', 'SALESMAN', 7698,  '1981-02-20'
, 1600, 300, 30); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7521, 'WARD', 'SALESMAN', 7698,  '1981-02-22'
, 1250, 500, 30); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7566, 'JONES', 'MANAGER', 7839,  '1981-04-02'
, 2975, NULL, 20); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7654, 'MARTIN', 'SALESMAN', 7698,  '1981-09-28'
, 1250, 1400, 30); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7698, 'BLAKE', 'MANAGER', 7839,  '1981-05-01'
, 2850, NULL, 30); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7782, 'CLARK', 'MANAGER', 7839,  '1981-06-09'
, 2450, NULL, 10); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7788, 'SCOTT', 'ANALYST', 7566,  '1987-04-19'
, 3000, NULL, 20); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7839, 'KING', 'PRESIDENT', NULL,  '1981-11-17'
, 5000, NULL, 10); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7844, 'TURNER', 'SALESMAN', 7698,  '1981-09-08'
, 1500, 0, 30); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7876, 'ADAMS', 'CLERK', 7788,  '1987-05-23'
, 1100, NULL, 20); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7900, 'JAMES', 'CLERK', 7698,  '1981-12-03'
, 950, NULL, 30); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7902, 'FORD', 'ANALYST', 7566,  '1981-12-03'
, 3000, NULL, 20); 
INSERT INTO EMP ( EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM,
DEPTNO ) VALUES ( 
7934, 'MILLER', 'CLERK', 7782,  '1982-01-23'
, 1300, NULL, 10); 
commit;
 
INSERT INTO SALGRADE ( GRADE, LOSAL, HISAL ) VALUES ( 
1, 700, 1200); 
INSERT INTO SALGRADE ( GRADE, LOSAL, HISAL ) VALUES ( 
2, 1201, 1400); 
INSERT INTO SALGRADE ( GRADE, LOSAL, HISAL ) VALUES ( 
3, 1401, 2000); 
INSERT INTO SALGRADE ( GRADE, LOSAL, HISAL ) VALUES ( 
4, 2001, 3000); 
INSERT INTO SALGRADE ( GRADE, LOSAL, HISAL ) VALUES ( 
5, 3001, 9999); 
commit;
```

## 表格

```mysql
mysql> select * from emp;
+-------+--------+-----------+------+------------+---------+---------+--------+
| EMPNO | ENAME  | JOB       | MGR  | HIREDATE   | SAL     | COMM    | DEPTNO |
+-------+--------+-----------+------+------------+---------+---------+--------+
|  7369 | SMITH  | CLERK     | 7902 | 1980-12-17 |  800.00 |    NULL |     20 |
|  7499 | ALLEN  | SALESMAN  | 7698 | 1981-02-20 | 1600.00 |  300.00 |     30 |
|  7521 | WARD   | SALESMAN  | 7698 | 1981-02-22 | 1250.00 |  500.00 |     30 |
|  7566 | JONES  | MANAGER   | 7839 | 1981-04-02 | 2975.00 |    NULL |     20 |
|  7654 | MARTIN | SALESMAN  | 7698 | 1981-09-28 | 1250.00 | 1400.00 |     30 |
|  7698 | BLAKE  | MANAGER   | 7839 | 1981-05-01 | 2850.00 |    NULL |     30 |
|  7782 | CLARK  | MANAGER   | 7839 | 1981-06-09 | 2450.00 |    NULL |     10 |
|  7788 | SCOTT  | ANALYST   | 7566 | 1987-04-19 | 3000.00 |    NULL |     20 |
|  7839 | KING   | PRESIDENT | NULL | 1981-11-17 | 5000.00 |    NULL |     10 |
|  7844 | TURNER | SALESMAN  | 7698 | 1981-09-08 | 1500.00 |    0.00 |     30 |
|  7876 | ADAMS  | CLERK     | 7788 | 1987-05-23 | 1100.00 |    NULL |     20 |
|  7900 | JAMES  | CLERK     | 7698 | 1981-12-03 |  950.00 |    NULL |     30 |
|  7902 | FORD   | ANALYST   | 7566 | 1981-12-03 | 3000.00 |    NULL |     20 |
|  7934 | MILLER | CLERK     | 7782 | 1982-01-23 | 1300.00 |    NULL |     10 |
+-------+--------+-----------+------+------------+---------+---------+--------+
14 rows in set (0.00 sec)

mysql> select * from dept;
+--------+------------+----------+
| DEPTNO | DNAME      | LOC      |
+--------+------------+----------+
|     10 | ACCOUNTING | NEW YORK |
|     20 | RESEARCH   | DALLAS   |
|     30 | SALES      | CHICAGO  |
|     40 | OPERATIONS | BOSTON   |
+--------+------------+----------+
4 rows in set (0.00 sec)

mysql> select * from salgrade;
+-------+-------+-------+
| GRADE | LOSAL | HISAL |
+-------+-------+-------+
|     1 |   700 |  1200 |
|     2 |  1201 |  1400 |
|     3 |  1401 |  2000 |
|     4 |  2001 |  3000 |
|     5 |  3001 |  9999 |
+-------+-------+-------+
5 rows in set (0.00 sec)
```

## 题

1、取得每个部门最高薪水的人员名称  
2、哪些人的薪水在部门的平均薪水之上  
3、取得部门中（所有人的）平均的薪水等级，如下：  
4、**不准用组函数（Max），取得最高薪水（给出两种解决方案**）  
5、**取得平均薪水最高的部门的部门编+号（至少给出两种解决方案）**  
6、取得平均薪水最高的部门的部门名称  
7、求平均薪水的等级最低的部门的部门名称  
8、**取得比普通员工(员工代码没有在mgr字段上出现的)的最高薪水还要高的领导人姓名**  
9、取得薪水最高的前五名员工  
10、取得薪水最高的第六到第十名员工  
11、取得最后入职的5名员工  
12、取得每个薪水等级有多少员工  
13、**面试题**    
有 3 个表 S(学生表)，C（课程表），SC（学生选课表）   
S（SNO，SNAME）代表（学号，姓名）    
C（CNO，CNAME，CTEACHER）代表（课号，课名，教师）    
SC（SNO，CNO，SCGRADE）代表（学号，课号，成绩）   
```mysql
SNO| SNAME |
---+-------+
1  |学生 1 |
2  |学生 2 |
3  |学生 3 |
4  |学生 4 |

CNO|CNAME|CTEACHER|
---+-----+--------+
1  |语文   |张    |
2  |政治   |王    |
3  |英语   |李    |
4  |数学   |赵    |
5  |物理   |黎明  |

SNO|CNO|SCGRADE|
---+---+-------+
1  |1  |40     |
1  |2  |30     |
1  |3  |20     |
1  |4  |80     |
2  |1  |60     |
2  |2  |60     |
2  |3  |60     |
2  |4  |60     |
2  |5  |40     |
3  |1  |60     |
3  |3  |80     |
```
**问题：**  

1. 找出没选过“黎明”老师的所有学生姓名。
2. 列出 2 门以上（含 2 门）不及格学生姓名及平均成绩。
3. 即学过 1 号课程又学过 2 号课所有学生的姓名。
请用标准 SQL 语言写出答案，方言也行（请说明是使用什么方言）。

```mysql
CREATE TABLE SC
(
SNO VARCHAR(200),
CNO VARCHAR(200),
SCGRADE VARCHAR(200)
);
CREATE TABLE S
(
SNO VARCHAR(200 ),
SNAME VARCHAR(200)
);
CREATE TABLE C
(
CNO VARCHAR(200),
CNAME VARCHAR(200),
CTEACHER VARCHAR(200)
);
INSERT INTO C ( CNO, CNAME, CTEACHER ) VALUES ( '1', '语文', '张');
INSERT INTO C ( CNO, CNAME, CTEACHER ) VALUES ( '2', '政治', '王');
INSERT INTO C ( CNO, CNAME, CTEACHER ) VALUES ( '3', '英语', '李');
INSERT INTO C ( CNO, CNAME, CTEACHER ) VALUES ( '4', '数学', '赵');
INSERT INTO C ( CNO, CNAME, CTEACHER ) VALUES ( '5', '物理', '黎明');
commit;
INSERT INTO S ( SNO, SNAME ) VALUES ( '1', '学生 1');
INSERT INTO S ( SNO, SNAME ) VALUES ( '2', '学生 2');
INSERT INTO S ( SNO, SNAME ) VALUES ( '3', '学生 3');
INSERT INTO S ( SNO, SNAME ) VALUES ( '4', '学生 4');
commit;
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '1', '1', '40');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '1', '2', '30');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '1', '3', '20');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '1', '4', '80');

INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '1', '5', '60');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '2', '1', '60');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '2', '2', '60');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '2', '3', '60');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '2', '4', '60');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '2', '5', '40');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '3', '1', '60');
INSERT INTO SC ( SNO, CNO, SCGRADE ) VALUES ( '3', '3', '80');
commit;
```

14、列出所有员工及领导的姓名  
15、列出受雇日期早于其直接上级的所有员工的编号,姓名,部门名称  
16、列出部门名称和这些部门的员工信息,同时列出那些没有员工的部门.  
17、列出至少有5个员工的所有部门  
18、列出薪金比"SMITH"多的所有员工信息.  
19、**列出所有"CLERK"(办事员)的姓名及其部门名称,部门的人数.**  
20、**列出最低薪金大于1500的各种工作及从事此工作的全部雇员人数.**  
21、**列出在部门"SALES"<销售部>工作的员工的姓名,假定不知道销售部的部门编号.**  
22、列出薪金高于公司平均薪金的所有员工,所在部门,上级领导,雇员的工资等级.  
23、列出与"SCOTT"从事相同工作的所有员工及部门名称.  
24、**列出薪金等于部门30中员工的薪金的其他员工的姓名和薪金.**  
25、列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金.部门名称.  
26、**列出在每个部门工作的员工数量,平均工资和平均服务期限.**  
27、列出所有员工的姓名、部门名称和工资  
28、列出所有部门的详细信息和人数  
29、列出各种工作的最低工资及从事此工作的雇员姓名  
30、列出各个部门的MANAGER(领导)的最低薪金  
31、列出所有员工的年工资,按年薪从低到高排序	  
32、求出员工领导的薪水超过3000的员工名称与领导名称  
33、**求出部门名称中,带'S'字符的部门员工的工资合计、部门人数.**  
34、**给任职日期超过30年的员工加薪10%.**  




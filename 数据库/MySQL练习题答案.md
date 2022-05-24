# MySQL练习题答案

## create table

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

```mysql
select e.deptno,e.ename,e.sal from emp e join (select deptno,max(sal) as maxsal from emp group by deptno) t on t.maxsal=e.sal and t.deptno=e.deptno;
+--------+-------+---------+
| deptno | ename | sal     |
+--------+-------+---------+
|     30 | BLAKE | 2850.00 |
|     20 | SCOTT | 3000.00 |
|     10 | KING  | 5000.00 |
|     20 | FORD  | 3000.00 |
+--------+-------+---------+
4 rows in set (0.00 sec)
```

2、哪些人的薪水在部门的平均薪水之上  

```mysql
select deptno,avg(sal) as avgsal from emp group by deptno;
+--------+-------------+
| deptno | avg(sal)    |
+--------+-------------+
|     10 | 2916.666667 |
|     20 | 2175.000000 |
|     30 | 1566.666667 |
+--------+-------------+

select e.ename,e.sal from emp e join (select deptno,avg(sal) as avgsal from emp group by deptno) t on t.deptno=e.deptno and e.sal>t.avgsal;
+-------+---------+
| ename | sal     |
+-------+---------+
| ALLEN | 1600.00 |
| JONES | 2975.00 |
| BLAKE | 2850.00 |
| SCOTT | 3000.00 |
| KING  | 5000.00 |
| FORD  | 3000.00 |
+-------+---------+
6 rows in set (0.00 sec)
```

3、取得部门中（所有人的）平均的薪水等级，如下：  

```mysql
select avg(s.grade) as avggrade from emp e join salgrade s on e.sal between s.losal and hisal group by deptno;
+----------+
| avggrade |
+----------+
|   3.6667 |
|   2.8000 |
|   2.5000 |
+----------+
3 rows in set (0.00 sec)
```

4、不准用组函数（Max），取得最高薪水（给出两种解决方案）  

```mysql
1. 
select sal from emp order by sal desc limit 0, 1;


2.select sal from emp where sal not in(select distinct e.sal from emp e join emp e1 on e.sal < e1.sal);
+---------+
| sal     |
+---------+
| 5000.00 |
+---------+
```

5、取得平均薪水最高的部门的部门编号（至少给出两种解决方案）  

```mysql
1.
select deptno from emp group by deptno order by avg(sal) desc limit 0, 1;

2.
select t.deptno,max(t.avgsal) as maxavgsal from (select deptno,avg(sal) as avgsal from emp group by deptno) t;
+--------+-------------+
| deptno | maxavgsal   |
+--------+-------------+
|     10 | 2916.666667 |
+--------+-------------+
1 row in set (0.00 sec)
```

6、取得平均薪水最高的部门的部门名称  

```mysql
select e.deptno,d.dname,avg(sal) as avgsal from emp e join dept d on d.deptno=e.deptno group by e.deptno;

select t.dname,max(t.avgsal) as maxavgsal from (select e.deptno,d.dname,avg(sal) as avgsal from emp e join dept d on d.deptno=e.deptno group by e.deptno) t;
+------------+-------------+
| dname      | maxavgsal   |
+------------+-------------+
| ACCOUNTING | 2916.666667 |
+------------+-------------+
1 row in set (0.00 sec)
```

7、求平均薪水的等级最低的部门的部门名称  

```mysql
select deptno,avg(sal) as avgsal from emp group by deptno;

select t.deptno,min(t.avgsal),s.grade from salgrade s join (select deptno,avg(sal) as avgsal from emp group by deptno) t on t.avgsal between s.losal and s.hisal;
+--------+---------------+-------+
| deptno | min(t.avgsal) | grade |
+--------+---------------+-------+
|     30 |   1566.666667 |     3 |
+--------+---------------+-------+
```

8、**取得比普通员工(员工代码没有在mgr字段上出现的)的最高薪水还要高的领导人姓名**  

```mysql
select ename,sal from emp where sal > (select max(sal) as maxsal from emp where empno not in(select distinct mgr from emp where mgr is not null));
+-------+---------+
| ename | sal     |
+-------+---------+
| JONES | 2975.00 |
| BLAKE | 2850.00 |
| CLARK | 2450.00 |
| SCOTT | 3000.00 |
| KING  | 5000.00 |
| FORD  | 3000.00 |
+-------+---------+
```

9、取得薪水最高的前五名员工  

```mysql
select ename,sal from emp order by sal desc limit 0, 5;
+-------+---------+
| ename | sal     |
+-------+---------+
| KING  | 5000.00 |
| SCOTT | 3000.00 |
| FORD  | 3000.00 |
| JONES | 2975.00 |
| BLAKE | 2850.00 |
+-------+---------+
5 rows in set (0.00 sec)
```

10、取得薪水最高的第六到第十名员工  

```mysql
 select ename,sal from emp order by sal desc limit 5, 5;
+--------+---------+
| ename  | sal     |
+--------+---------+
| CLARK  | 2450.00 |
| ALLEN  | 1600.00 |
| TURNER | 1500.00 |
| MILLER | 1300.00 |
| MARTIN | 1250.00 |
+--------+---------+
```

11、取得最后入职的5名员工  

```mysql
select ename,hiredate from emp order by hiredate desc limit 0, 5;
+--------+------------+
| ename  | hiredate   |
+--------+------------+
| ADAMS  | 1987-05-23 |
| SCOTT  | 1987-04-19 |
| MILLER | 1982-01-23 |
| FORD   | 1981-12-03 |
| JAMES  | 1981-12-03 |
+--------+------------+
5 rows in set (0.00 sec
```

12、取得每个薪水等级有多少员工  

```mysql
select s.grade,count(s.grade) as countgrade from emp e join salgrade s on e.sal between s.losal and s.hisal group by s.grade;
+-------+------------+
| grade | countgrade |
+-------+------------+
|     1 |          3 |
|     2 |          3 |
|     3 |          2 |
|     4 |          5 |
|     5 |          1 |
+-------+------------+
5 rows in set (0.00 sec)
```

13、面试题	  

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
---+-------+------+
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
    1. 找出黎明老师的课号：`select cno from c where cteacher = '黎明';`
    2. 找出选了黎明老师课的学生：`select sno from sc where cno = (select cno from c where cteacher = '黎明');`
    3. not in 选了黎明老师课的学生：`select sno,sname from s where sno not in(select sno from sc where cno = (select cno from c where cteacher = '黎明'));`
2. 列出 2 门以上（含 2 门）不及格学生姓名及平均成绩。
    1. 找出2门以上（含2门）不及格学生学号：`select sno from sc where scgrade<60 group by sno having count(*)>=2;`
    2. 连接上表，找出姓名：`select s.sname from s join (select sno from sc where scgrade<60 group by sno having count(*)>=2) t on t.sno=s.sno;`
    3. 求平均成绩，连接上表：`select s.sname,avg(sc.scgrade) from s join (select sno from sc where scgrade<60 group by sno having count(*)>=2) t on t.sno=s.sno join sc on sc.sno = t.sno;`
3. 即学过 1 号课程又学过 2 号课所有学生的姓名。
请用标准 SQL 语言写出答案，方言也行（请说明是使用什么方言）。
    1. 分别找出学过1号课程和学过2号课程的学生学号：`select sno from sc where cno = 1;`  `select sno from sc where cno = 2;`
    2. 找出1号课程的人 in 2号课程人的（求交集）学号：`select sno from sc where cno = 1 and sno in (select sno from sc where cno = 2);`
    3. 连接学生表，找出姓名：`select s.sname from sc join s on s.sno = sc.sno and sc.cno = 1 and sc.sno in (select sc.sno from sc where sc.cno = 2); `


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

```mysql
SELECT E.ENAME AS '员工',E1.ENAME as '领导' FROM EMP E LEFT JOIN EMP E1 ON E.MGR=E1.EMPNO;
+--------+-------+
| 员工   | 领导  |
+--------+-------+
| SMITH  | FORD  |
| ALLEN  | BLAKE |
| WARD   | BLAKE |
| JONES  | KING  |
| MARTIN | BLAKE |
| BLAKE  | KING  |
| CLARK  | KING  |
| SCOTT  | JONES |
| KING   | NULL  |
| TURNER | BLAKE |
| ADAMS  | SCOTT |
| JAMES  | BLAKE |
| FORD   | JONES |
| MILLER | CLARK |
+--------+-------+
14 rows in set (0.00 sec)
```

15、列出受雇日期早于其直接上级的所有员工的编号,姓名,部门名称  

```mysql
 select e.empno,e.ename,e.hiredate,e1.ename,e1.hiredate,d.dname from emp e join emp e1 on e.mgr=e1.empno and e.hiredate<e1.hiredate join dept d on d.deptno=e.deptno;
+-------+-------+------------+-------+------------+------------+
| empno | ename | hiredate   | ename | hiredate   | dname      |
+-------+-------+------------+-------+------------+------------+
|  7782 | CLARK | 1981-06-09 | KING  | 1981-11-17 | ACCOUNTING |
|  7369 | SMITH | 1980-12-17 | FORD  | 1981-12-03 | RESEARCH   |
|  7566 | JONES | 1981-04-02 | KING  | 1981-11-17 | RESEARCH   |
|  7499 | ALLEN | 1981-02-20 | BLAKE | 1981-05-01 | SALES      |
|  7521 | WARD  | 1981-02-22 | BLAKE | 1981-05-01 | SALES      |
|  7698 | BLAKE | 1981-05-01 | KING  | 1981-11-17 | SALES      |
+-------+-------+------------+-------+------------+------------+
```

16、列出部门名称和这些部门的员工信息,同时列出那些没有员工的部门.  

```mysql
select d.dname,e.* from dept d left join emp e on d.deptno=e.deptno;
+------------+-------+--------+-----------+------+------------+---------+---------+--------+
| dname      | EMPNO | ENAME  | JOB       | MGR  | HIREDATE   | SAL     | COMM    | DEPTNO |
+------------+-------+--------+-----------+------+------------+---------+---------+--------+
| ACCOUNTING |  7782 | CLARK  | MANAGER   | 7839 | 1981-06-09 | 2450.00 |    NULL |     10 |
| ACCOUNTING |  7839 | KING   | PRESIDENT | NULL | 1981-11-17 | 5000.00 |    NULL |     10 |
| ACCOUNTING |  7934 | MILLER | CLERK     | 7782 | 1982-01-23 | 1300.00 |    NULL |     10 |
| RESEARCH   |  7369 | SMITH  | CLERK     | 7902 | 1980-12-17 |  800.00 |    NULL |     20 |
| RESEARCH   |  7566 | JONES  | MANAGER   | 7839 | 1981-04-02 | 2975.00 |    NULL |     20 |
| RESEARCH   |  7788 | SCOTT  | ANALYST   | 7566 | 1987-04-19 | 3000.00 |    NULL |     20 |
| RESEARCH   |  7876 | ADAMS  | CLERK     | 7788 | 1987-05-23 | 1100.00 |    NULL |     20 |
| RESEARCH   |  7902 | FORD   | ANALYST   | 7566 | 1981-12-03 | 3000.00 |    NULL |     20 |
| SALES      |  7499 | ALLEN  | SALESMAN  | 7698 | 1981-02-20 | 1600.00 |  300.00 |     30 |
| SALES      |  7521 | WARD   | SALESMAN  | 7698 | 1981-02-22 | 1250.00 |  500.00 |     30 |
| SALES      |  7654 | MARTIN | SALESMAN  | 7698 | 1981-09-28 | 1250.00 | 1400.00 |     30 |
| SALES      |  7698 | BLAKE  | MANAGER   | 7839 | 1981-05-01 | 2850.00 |    NULL |     30 |
| SALES      |  7844 | TURNER | SALESMAN  | 7698 | 1981-09-08 | 1500.00 |    0.00 |     30 |
| SALES      |  7900 | JAMES  | CLERK     | 7698 | 1981-12-03 |  950.00 |    NULL |     30 |
| OPERATIONS |  NULL | NULL   | NULL      | NULL | NULL       |    NULL |    NULL |   NULL |
+------------+-------+--------+-----------+------+------------+---------+---------+--------+
```

17、列出至少有5个员工的所有部门  

```mysql
select d.dname from emp e join dept d on e.deptno=d.deptno group by e.deptno having count(e.deptno)>=5;
+----------+
| dname    |
+----------+
| RESEARCH |
| SALES    |
+----------+
```

18、列出薪金比"SMITH"多的所有员工信息.  

```mysql
select * from emp where sal > (select sal from emp where ename='SMITH');
+-------+--------+-----------+------+------------+---------+---------+--------+
| EMPNO | ENAME  | JOB       | MGR  | HIREDATE   | SAL     | COMM    | DEPTNO |
+-------+--------+-----------+------+------------+---------+---------+--------+
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
```

19、**列出所有"CLERK"(办事员)的姓名及其部门名称,部门的人数.**  

```mysql
select e.ename,d.dname from emp e join dept d on e.deptno=d.deptno where e.job='CLERK';
```

20、**列出最低薪金大于1500的各种工作及从事此工作的全部雇员人数.**  

```mysql

select job,count(*) from emp group by job having min(sal)>1500;

+-----------+----------+
| job       | count(*) |
+-----------+----------+
| ANALYST   |        2 |
| MANAGER   |        3 |
| PRESIDENT |        1 |
+-----------+----------+

```

21、**列出在部门"SALES"<销售部>工作的员工的姓名,假定不知道销售部的部门编号.**  

```mysql
 select e.ename from dept d join emp e on e.deptno=d.deptno and d.dname='SALES';
+--------+
| ename  |
+--------+
| ALLEN  |
| WARD   |
| MARTIN |
| BLAKE  |
| TURNER |
| JAMES  |
+--------+
```

22、列出薪金高于公司平均薪金的所有员工,所在部门,上级领导,雇员的工资等级.  

```mysql
平均薪金
select avg(sal) as avgsal from emp;

select ename,sal from emp where sal > (select avg(sal) as avgsal from emp);

select e.ename,e.sal,d.dname from emp e join dept d on d.deptno=e.deptno where sal > (select avg(sal) as avgsal from emp);

select e.ename,e.sal,d.dname,e1.ename as '领导',s.grade from emp e join dept d on d.deptno=e.deptno left join emp e1 on e1.empno=e.mgr join salgrade s on e.sal between s.losal and s.hisal where e.sal > (select avg(sal) as avgsal from emp);
+-------+---------+------------+-------+-------+
| ename | sal     | dname      | 领导    | grade |
+-------+---------+------------+-------+-------+
| JONES | 2975.00 | RESEARCH   | KING  |     4 |
| BLAKE | 2850.00 | SALES      | KING  |     4 |
| CLARK | 2450.00 | ACCOUNTING | KING  |     4 |
| SCOTT | 3000.00 | RESEARCH   | JONES |     4 |
| KING  | 5000.00 | ACCOUNTING | NULL  |     5 |
| FORD  | 3000.00 | RESEARCH   | JONES |     4 |
+-------+---------+------------+-------+-------+
```

23、列出与"SCOTT"从事相同工作的所有员工及部门名称.  

```mysql
select job from emp where ename='SCOTT';

select e.ename,d.dname from emp e join (select job from emp where ename='SCOTT') t on t.job=e.job join dept d on d.deptno=e.deptno;
+-------+----------+
| ename | dname    |
+-------+----------+
| SCOTT | RESEARCH |
| FORD  | RESEARCH |
+-------+----------+
```

24、**列出薪金等于部门30中员工的薪金的其他员工的姓名和薪金.**  

```mysql
select distinct(sal) from emp where deptno = 30;
select ename,sal from emp where sal in (select distinct(sal) from emp where deptno = 30) and deptno <> 30;
Empty set (0.00 sec)
```

25、列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金.部门名称.  

```mysql
select max(sal) sal from emp where deptno = 30;

select e.ename,e.sal,d.dname from emp e join (select max(sal) sal from emp where deptno = 30) t on e.sal > t.sal join dept d on d.deptno = e.deptno;
+-------+---------+------------+
| ename | sal     | dname      |
+-------+---------+------------+
| KING  | 5000.00 | ACCOUNTING |
| JONES | 2975.00 | RESEARCH   |
| SCOTT | 3000.00 | RESEARCH   |
| FORD  | 3000.00 | RESEARCH   |
+-------+---------+------------+
4 rows in set (0.04 sec)
```

26、**列出在每个部门工作的员工数量,平均工资和平均服务期限.**  

```mysql
select deptno,count(*) from emp group by deptno;

select deptno,count(*),avg(sal),avg(hiredate) from emp group by deptno;
```

27、列出所有员工的姓名、部门名称和工资  

```mysql
select e.ename,d.dname,e.sal from emp e join dept d on d.deptno=e.deptno;
+--------+------------+---------+
| ename  | dname      | sal     |
+--------+------------+---------+
| CLARK  | ACCOUNTING | 2450.00 |
| KING   | ACCOUNTING | 5000.00 |
| MILLER | ACCOUNTING | 1300.00 |
| SMITH  | RESEARCH   |  800.00 |
| JONES  | RESEARCH   | 2975.00 |
| SCOTT  | RESEARCH   | 3000.00 |
| ADAMS  | RESEARCH   | 1100.00 |
| FORD   | RESEARCH   | 3000.00 |
| ALLEN  | SALES      | 1600.00 |
| WARD   | SALES      | 1250.00 |
| MARTIN | SALES      | 1250.00 |
| BLAKE  | SALES      | 2850.00 |
| TURNER | SALES      | 1500.00 |
| JAMES  | SALES      |  950.00 |
+--------+------------+---------+
```

28、列出所有部门的详细信息和人数  

```mysql
select d.*,count(e.ename) as '人数' from dept d left join emp e on e.deptno=d.deptno group by e.deptno;
+--------+------------+----------+------+
| DEPTNO | DNAME      | LOC      | 人数     |
+--------+------------+----------+------+
|     40 | OPERATIONS | BOSTON   |    0 |
|     10 | ACCOUNTING | NEW YORK |    3 |
|     20 | RESEARCH   | DALLAS   |    5 |
|     30 | SALES      | CHICAGO  |    6 |
+--------+------------+----------+------+
```

29、列出各种工作的最低工资及从事此工作的雇员姓名  

```mysql
select job,min(sal) as minsal from emp e group by job;

select t.job,t.minsal,e.ename from emp e join (select job,min(sal) as minsal from emp e group by job) t on t.job=e.job and t.minsal=e.sal;

+-----------+---------+--------+
| job       | minsal  | ename  |
+-----------+---------+--------+
| CLERK     |  800.00 | SMITH  |
| SALESMAN  | 1250.00 | WARD   |
| SALESMAN  | 1250.00 | MARTIN |
| MANAGER   | 2450.00 | CLARK  |
| ANALYST   | 3000.00 | SCOTT  |
| PRESIDENT | 5000.00 | KING   |
| ANALYST   | 3000.00 | FORD   |
+-----------+---------+--------+

```

30、列出各个部门的MANAGER(领导)的最低薪金  

```mysql

select deptno,min(sal) as minsal from emp group by deptno;

select e.deptno,min(e1.sal) as minsal from emp e join emp e1 on e1.empno = e.mgr group by e.deptno;

select deptno,min(sal) as minsal from emp where job = 'MANAGER' group by deptno;
+--------+---------+
| deptno | minsal  |
+--------+---------+
|     10 | 2450.00 |
|     20 | 2975.00 |
|     30 | 2850.00 |
+--------+---------+
```

31、列出所有员工的年工资,按年薪从低到高排序	  

```mysql

select ename,(sal+ifnull(comm,0))*12 as '年薪' from emp order by '年薪' desc;
+--------+----------+
| ename  | 年薪        |
+--------+----------+
| SMITH  |  9600.00 |
| ALLEN  | 22800.00 |
| WARD   | 21000.00 |
| JONES  | 35700.00 |
| MARTIN | 31800.00 |
| BLAKE  | 34200.00 |
| CLARK  | 29400.00 |
| SCOTT  | 36000.00 |
| KING   | 60000.00 |
| TURNER | 18000.00 |
| ADAMS  | 13200.00 |
| JAMES  | 11400.00 |
| FORD   | 36000.00 |
| MILLER | 15600.00 |
+--------+----------+

```

32、求出员工领导的薪水超过3000的员工名称与领导名称  

```mysql
select e.ename,e1.ename from emp e join emp e1 on e1.empno=e.mgr and e1.sal>3000;
+-------+-------+
| ename | ename |
+-------+-------+
| JONES | KING  |
| BLAKE | KING  |
| CLARK | KING  |
+-------+-------+
```

33、**求出部门名称中,带'S'字符的部门员工的工资合计、部门人数.**  

```mysql
select sum(sal) from emp where ename like '%S%';

select sum(sal) from emp where ename like '%S%';

select d.dname,sum(e.sal) as sumsal from dept d left join emp e on d.deptno=e.deptno where e.ename like '%S%' group by e.deptno;

```

34、**给任职日期超过30年的员工加薪10%.**  

```mysql

```





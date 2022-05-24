# 老杜告诉你java小白到大神是怎么炼成的

[toc]

## 1. 学习前的准备

1. 一个好的学习方法（应该怎么学习更高效）；
   - 一个合格的程序员应该具备两个能力
     - 有一个很好的指法速度（敲代码快）
     - 有一个很好的编程思想（编程思路：能够使用编程的思路去解决现实生活中问题的一种能力）
   - 指法速度
     - 键盘的手势要正确，左手：asdf，右手：jkl;，如果你的手势不对，从现在开始纠正它。
     - 刚开始的时候比较麻烦，还没有二指禅快呢，但是随着时间的推移，你的代码速度会有很明显的提升。只要坚持下来。一开始的时候一定要坚持盲打（强迫自己不看键盘）。
     - 可以安装一个金山打字通。可以练习打字。当然，也可以在敲代码的过程中去练习，都是可以的。
     - 练习到什么级别？肌肉记忆！！！！
   - 编程思想的形成
     - 编程思想就是编程思路，有很多同学问我：老师，我现在听课能听懂，但是你让我解决一个实际的问题，我不会，不知道该怎么下手！！！
     - 千万不要照抄代码，没用。或者说作用很小，如此下去可能会导致你对学习的厌烦。本来是有兴趣的，但是兴趣没了！只有一个程序可以抄写：HelloWorld，大家都知道HelloWorld是我们学习过程中的第一个程序，为什么这个程序可以照抄呢？因为HelloWorld程序的编写是为了测试本地的java环境是否搭建成功了。并不需要理解HelloWorld代码的含义。所以这个程序是可以严格照抄的。
     - 结论验证法
       - 听课的过程当中一定要把笔记记录下来。笔记当中只记忆重点的结论。
       - 课下第一件事不是慌着做作业。作业是次要的。首要的第一件事是消化课堂上的结论。比如老师说了：if语句后面有一个小括号，小括号当中的数据类型只允许是布尔类型：true或者false不能是其它数据类型。
       - 课下尝试编写属于自己的程序，想尽一切方法去推翻这个结论，如果你无法推翻，那么只能选择接受这个结论，采用这种方式测试之后，发现结论记住了。并且同时也锻炼了编程的思路！
2. 一个好用的截图工具；
   - Snipaste
3. 一个好的记笔记的软件；
   - 笔记中一定是只记忆重点结论。没有废话。
   - 课堂上笔记记简笔，没有时间记那么详细，课下的时候自己再整理。整理各种结论。
   - 图文并茂。
   - 推荐使用微软的onenote。专门的记笔记的工具。
   - 课下第一件事：是编写属于自己的程序，去推翻课堂上的结论。将每个知识点都学透彻！
4. 一台好一点的电脑；
   - 建议的配置：酷睿i7九代以上，至少16G内存。因为现在的开发，启动的环境太多，太吃内存，另外电子产品更新换代太频繁，所以建议配置稍微高一些！能多用几年。另外，开发效率也高。
   - 开发的过程中需要启动：数据库的服务、IDEA工具、虚拟机、虚拟机中的Linux系统等.....

## 2. 开启你的学习之旅（学习路线）

1. javase
   - Java语言的基础，是Java的最基本的库。基础语法加常用类库。
2. 数据库
   - mysql（或者oracle），数据库是所有程序员都需要学习的，因为不管是C语言，还是C++，还是Java，或者其他，软件最终都是操作数据，而数据存储在数据库当中，所以数据库是必须要学习的，大学中大部分都是学习微软的SQLServer，但是企业中，尤其是互联网公司大部分使用的都是mysql数据库，对于数据库来说，除了mysql之外，大家如果还有精力的话，可以再学习一下oracle数据库。
   - jdbc：Java语言连接数据库。（使用java语言对数据库中数据进行增删改查等操作。）
3. web前端
   - 对于系统结构来说，常见的有两种：第一种是CS结构，第二种是BS结构。BS结构是目前的主流。所谓的BS其实就是：Browser / Server，浏览器访问服务器的形式。
   - 浏览器B端的就是web前端，在浏览器上运行的语言包括：HTML、CSS、JavaScript【JS最重要】
   - 先学习HTML，再学习CSS，最后学习JavaScript。
4. javaweb（web后端）
   - BS结构，我们主要进行的是S端，S端是Server端（服务器端），服务器端可以是C语言，可以是PHP，当然也可以是Java。目前Java居多，占大部分！
   - 先学习Servlet，再学习JSP。
5. web进阶
   - AJAX（发送一步请求的一个技术）
   - jQuery（是一个JavaScript语言的高级框架）
6. javaweb项目实战
   - 基于Servlet + JSP + MySQL + JDBC + HTML + CSS + JavaScript + jQuery + AJAX能做一个web项目。
7. SSM三大框架（额外可选学SSH）
   - Spring + SpringMVC + MyBatis
   - 建议的学习顺序：先学习MyBatis，再学习Spring，最后学习SpringMVC。
   - 如果自己还有多余的时间，大家可以选学以下SSH：Spring + Struts2 + Hibernate
   - 建议SSH的学习顺序：Hibernate、Spring、Struts2
8. 互联网分布式
   - Dubbo
   - MQ
   - redis
   - zookeeper
   - fastDFS
   - Nginx
   - SpringBoot
   - SpringCloud
   - .....
9. 分布式项目实战
   - 最好能有一个商业级的项目对以上内容有一个练习。实战操作。
   - 然后你就可以出山了。（大概需要7个月的时间！如果参加培训面授的话，5个月的时间！）

## 3. B站视频、资料、代码的获取方式

### 3.1 JavaSE

1. 动力节点2020最新版零基础
   - 集数：685
   - JDK版本：13
   - 获取地址：<https://www.bilibili.com/video/BV1P7411V7nQ/>
   - 配套资料：课堂笔记、思维导图、每章作业、课堂源码、相关文档、面试题、工具
2. 动力节点2020最新JavaSE进阶
   - 集数：368
   - JDK版本：13
   - 获取地址：<https://www.bilibili.com/video/av93347594> 
3. JDK14新特性
   - 获取地址：<https://www.bilibili.com/video/BV1BQ4y1M7JJ> 
4. 数据结构与算法【持续更新中】                
    - 获取地址：<https://www.bilibili.com/video/BV1HQ4y1d7th>
4. JVM虚拟机【持续更新中】
    - 获取地址：<https://www.bilibili.com/video/BV1e64y197rh>

### 3.2 数据库

1. MySQL数据库
   - 获取地址：<https://www.bilibili.com/video/av57575364> 
2. JDBC
   - 获取地址：<https://www.bilibili.com/video/av59263860> 
3. Oracle（选学）
   - 获取地址：<https://www.bilibili.com/video/BV1kx411s71n> 

### 3.3 web前端

1. HTML
   - 获取地址：<https://www.bilibili.com/video/av62468669> 
2. CSS
   - 获取地址：<https://www.bilibili.com/video/av62315621> 
3. JavaScript
   - 获取地址：<https://www.bilibili.com/video/av62653534> 

### 3.4 javaweb

1. 网络通信流程（B/S结构的系统通信流程）
   - 获取地址：<https://www.bilibili.com/video/BV1Rk4y167L3> 
2. HTTP协议与Tomcat服务器
   - 获取地址：<https://www.bilibili.com/video/BV1sp4y1X7Tw> 
3. Servlet
   - 获取地址：<https://www.bilibili.com/video/BV1y5411p7kb> 
4. JSP
   - 获取地址：<https://www.bilibili.com/video/BV1354y1Q7pA> 
5. javaweb**经典**版全套（包括Servlet + JSP）
   - 获取地址：<https://www.bilibili.com/video/BV18z411i7gh> 

### 3.5 web进阶

1. AJAX
   - 获取地址：<https://www.bilibili.com/video/BV15k4y167XM> 
2. jQuery
   - 获取地址：<https://www.bilibili.com/video/BV1Jg4y1B7n4> 

### 3.6 javaweb项目实战

1. SVN
   - 获取地址：<https://www.bilibili.com/video/BV1ux411j7H2> 
2. EGOV项目实战（电子政务系统，政府部门使用的系统）
   - 获取地址：https://www.bilibili.com/video/BV194411c7SL
3. CRM项目实战（有时间的话，可以学习两个项目，没有时间的话，学一个也行，选择任何一个！）
   - 获取地址：https://www.bilibili.com/video/BV1fT4y1E7a6
4. Linux
   - 获取地址：https://www.bilibili.com/video/BV1dt411W7N1 

### 3.7 2020最新SSM三大框架（额外可选学SSH）

1. JDK动态代理
   - 获取地址：<https://www.bilibili.com/video/BV1HZ4y1p7F1> 
2. Maven入门
   - 获取地址：<https://www.bilibili.com/video/BV1dp4y1Q7Hf> 
3. MyBatis
   - 获取地址：<https://www.bilibili.com/video/BV185411s7Ry> 
4. Spring
   - 获取地址：<https://www.bilibili.com/video/BV1nz4y1d7uy> 
5. SpringMVC
   - 获取地址：<https://www.bilibili.com/video/BV1sk4y167pD> 
6. 典藏版SSM（eclipse全套）
   - 获取地址：https://www.bilibili.com/video/BV1Ug4y1i7W7 
7. SSH（选学）
   - 获取地址：https://www.bilibili.com/video/BV1uT4y1g7Qo 

### 3.8 互联网分布式

1. Dubbo
  - 获取地址：https://www.bilibili.com/video/BV1Sk4y197eD
2. Redis
  - 获取地址：https://www.bilibili.com/video/BV14t411H76W
3. Maven实战进阶
  - 获取地址：https://www.bilibili.com/video/BV1kg4y187td
4. Springboot
  - 获取地址：https://www.bilibili.com/video/BV1PZ4y1j7QK
5. Nginx
  - 获取地址：https://www.bilibili.com/video/BV11V411C7r9
6. SpringSession
  - 获取地址：https://www.bilibili.com/video/BV1iK411p7dZ
7. FastDFS
  - 获取地址：https://www.bilibili.com/video/BV1ta4y1v7Kw
8. RabbitMQ实战
  - 获取地址：https://www.bilibili.com/video/BV1Ap4y1D7tU
9. Springcloud
  - 获取地址：https://www.bilibili.com/video/BV1aC4y1p7Qi
10. MySQL集群
  - 获取地址：https://www.bilibili.com/video/BV1Rg4y1i7VR
11. Mycat
   - 获取地址：https://www.bilibili.com/video/BV1gK4y1b7iU
12. Shiro
   - 获取地址：https://www.bilibili.com/video/BV14T4y1g75M

### 3.9 分布式项目实战

- 电商互联网大型项目（已经解禁） 
  - 获取地址：https://www.bilibili.com/video/BV1RQ4y1K7Pq 

## 4. methods other get of resources

![](https://i0.hdslb.com/bfs/article/ef96843e5f5ebde6aae2121d810852751a1d1044.jpg@1320w_758h.webp)
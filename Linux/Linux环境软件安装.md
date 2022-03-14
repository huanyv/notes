# Linux环境软件安装

[TOC]

## 防火墙管理

* 查看端口监听
    * `netstat -lnpt`
* 查看防火墙新状态
	* `systemctl status firewalld`
* 开启、关闭、重启
	* `systemctl start firewalld`
	* `systemctl stop firewalld`
	* `systemctl restart firewalld`
* 查看所有已开放端口
	* `firewall-cmd --list-ports`
* 开启端口
	* `firewall-cmd --zone=public --add-port=80/tcp --permanent`
	* 重启后端口生效
	* 命令含义：  `--zone` 作用域   `--add-port=80/tcp` 添加端口，格式为：端口/通讯协议   `--permanent` 永久生效，没有此参数重启后失效
* 关闭端口
    * `firewall-cmd --zone=public --remove-port=80/tcp --permanent`
* 其它常用命令
	* `firewall-cmd --state`查看防火墙状态，是否是running
	* `firewall-cmd --reload`重新载入配置，比如添加规则之后，需要执行此命令
	* `firewall-cmd --get-zones`列出支持的zone
	* `firewall-cmd --get-services`列出支持的服务，在列表中的服务是放行的
	* `firewall-cmd --query-service ftp`查看ftp服务是否支持，返回yes或者no
	* `firewall-cmd --add-service=ftp`临时开放ftp服务
	* `firewall-cmd --add-service=ftp --permanent`永久开放ftp服务
	* `firewall-cmd --remove-service=ftp --permanent`永久移除ftp服务
	* `firewall-cmd --add-port=80/tcp --permanent`永久添加80端口 
	* `firewall-cmd --remove-port=80/tcp --permanent`永久移除80端口 
	* `firewall-cmd --zone=public --list-ports`查看已开放的端口
	* `iptables -L -n`查看规则，这个命令是和iptables的相同的
	* `man firewall-cmd` 

## 安装MySQL

下载地址        

[MySQL](https://dev.mysql.com/downloads/mysql/)  

### 方法一

1. 卸载mariadb
    * `rpm -qa | grep mariadb`
    * 逐个卸载
    * `yum -y remove 包名`
2. 安装MYSQL
3. 创建MYSQL数据文件夹
    * `mkdir /opt/mysql-5.7.35/data`
4. 创建MYSQL组和用户
    * `groupadd mysql`
    * `useradd -g mysql mysql`
5. 初始化
    * `./mysqld --initialize --user=mysql --datadir=/opt/mysql-5.7.35/data --basedir=/opt/mysql-5.7.35`
    * 如果出了`./bin/mysqld: error while loading shared libraries: libaio.so.1: cannot open shared object file: No such file or directory` 错误
        * 执行安装`yum install -y libaio`
    * `--initialize` 初始化mysql，创建mysql的root, 随机生成密码。记住密码，登录msyql使用。
    * `--user`执行msyqld 命令的linux用户名
    * `--datadir` : mysql数据文件的存放位置，目录位置参照本机的设置。
    * `--basedir` : msyql安装程序的目录，目录位置参照本机的设置。
    * **会生成一个初始密码，记住**
6. 启用安全功能
    * `./mysql_ssl_rsa_setup --datadir=/opt/mysql-5.7.35/data`
7. 修改MYSQL安装目录权限
    * `chown -R mysql:mysql /opt/mysql-5.7.35/`
    * `chmod 777 /opt/mysql-5.7.35/`
8. 启动MYSQL服务
    * `./mysqld_safe &`
9. 登陆
    * `./mysql -uroot -p`
    * 用之前生成的密码
10. 修改root密码
    * `set password for root@localhost = password('123');`
11. 远程授权
    * `grant all privileges on *.* to root@'%' identified by '123';`
    * 刷新授权：`flush privileges;`
12. 关闭服务
    * `mysqladmin -uroot -p shutdown`

### 方法二

1. 卸载mariadb
    * `rpm -qa | grep mariadb`
    * 逐个卸载
    * `yum -y remove 包名`
2. 解压安装MYSQL
3. 创建MYSQL数据文件夹
    * `mkdir /opt/mysql/data`
4. 创建MYSQL组和用户，并修改权限
    * `groupadd mysql`
    * `useradd -g mysql mysql`
    * `chown -R mysql:mysql mysql`
5. 在 /etc ⽬录下新建 my.cnf ⽂件

```
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8
socket=/var/lib/mysql/mysql.sock
[mysqld]
skip-name-resolve
#设置3306端⼝
port = 3306
socket=/var/lib/mysql/mysql.sock
# 设置mysql的安装⽬录
basedir=/opt/mysql
# 设置mysql数据库的数据的存放⽬录
datadir=/opt/mysql/data
# 允许最⼤连接数
max_connections=200
# 服务端使⽤的字符集默认为8⽐特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使⽤的默认存储引擎
default-storage-engine=INNODB
lower_case_table_names=1
max_allowed_packet=16M
```

6. 同时使⽤如下命令创建 /var/lib/mysql ⽬录，并修改权限：
    * `mkdir /var/lib/mysql`
    * `chmod 777 /var/lib/mysql`
7. 初始化
    * `cd /opt/mysql`
    * `./bin/mysqld --initialize --user=mysql --basedir=/opt/mysql --datadir=/opt/mysql/data`
    * 如果出了`./bin/mysqld: error while loading shared libraries: libaio.so.1: cannot open shared object file: No such file or directory` 错误
        * 执行安装`yum install -y libaio`
    * 记住上⾯打印出来的 root 的密码，后⾯⾸次登陆需要使⽤
8. 配置启动脚本
    * `cp ./support-files/mysql.server /etc/init.d/mysqld`
    * 并修改 /etc/init.d/mysqld ，修改其 basedir 和 datadir 为实际对应⽬录：45行左右
    * `basedir=/opt/mysql`
    * `datadir=/opt/mysql/data`
9. 设置MYSQL系统服务并开启⾃启
    * ⾸先增加 mysqld 服务控制脚本执⾏权限：`chmod +x /etc/init.d/mysqld`
    * 将 mysqld 服务加⼊到系统服务：`chkconfig --add mysqld`
    * 检查 mysqld 服务是否已经⽣效即可：`chkconfig --list mysqld`
    * `0:关 1:关 2:开 3:开 4:开 5:开 6:关`
10. 启动：`service mysqld start`


### 注意

远程连接授权

* 第一次：`grant all privileges on *.* to 'root'@'%' identified by '密码' with grant option;`
* `flush privileges`
* 每次：`systemctl stop firewalld`  

可以把安装目录下的bin目录放入环境变量

查看数据库编码：`show variables where Variable_name like '%char%';`
修改mysql的字符集：在mysql客户端执行如下命令

```
set character_set_client=utf8;
set character_set_connection=utf8;
set character_set_database=utf8;
set character_set_results=utf8;
set character_set_server=utf8;
set character_set_system=utf8;
set collation_connection=utf8;
set collation_database=utf8;
set collation_server=utf8;
```


## Nginx

下载地址  
[Nginx](http://nginx.org/en/download.html)

1. 在 /usr/local/ 下创建 nginx ⽂件夹并进⼊
    * `cd /usr/local/`
    * `mkdir nginx`
    * `cd nginx`
2. 将 Nginx 安装包解压到 /usr/local/nginx 中即可
    * `[root@localhost nginx]# tar zxvf /root/nginx-1.17.10.tar.gz -C ./`
    * 解压完之后， /usr/local/nginx ⽬录中会出现⼀个 nginx-1.17.10 的⽬录
3. 预先安装额外的依赖
    * `yum -y install pcre-devel`
    * `yum -y install openssl openssl-devel`
4. 编译安装NGINX
    * `cd nginx-1.17.10`
    * `./configure`
    * `make && make install`
5. 安装完成后，Nginx的可执⾏⽂件位置位于
    * `/usr/local/nginx/sbin/nginx`
6. 启动NGINX
    * 启动：`/usr/local/nginx/sbin/nginx`
    * 关闭：`/usr/local/nginx/sbin/nginx -s stop`
    * 重启：`/usr/local/nginx/sbin/nginx -s reload`
7. 配置文件位置：`/usr/local/nginx/conf/nginx.conf`

### LNMP(Liunx Nginx MySQL PHP)

1. 安装php以及相关工具
    * `yum install php php-fpm php-mysql -y`
    * 如果需要可以装以下软件`yum install 包名`
    * `php-gd-5.4.16-48.el7.x86_64`
    * `php-common-5.4.16-48.el7.x86_64`
    * `php-cli-5.4.16-48.el7.x86_64`
    * `php-pdo-5.4.16-48.el7.x86_64`
2. 启动PHP-FPM进程并加入开机启动项
    * `systemctl start php-fpm`
    * `systemctl enable php-fpm`
    * `Created symlink from /etc/systemd/system/multi-user.target.wants/php-fpm.service to /usr/lib/systemd/system/php-fpm.service.`
3. 启动之后，可以使用下面的命令查看 PHP-FPM 进程监听哪个端口 
    * `netstat -nlpt | grep php-fpm`
    * `tcp        0      0 127.0.0.1:9000          0.0.0.0:*               LISTEN      12566/php-fpm: mast`
    * 如果没有：php-fpm配置文件加入`listen = 127.0.0.1:9000`
4. 打开Nginx配置文件
    * `vim /usr/local/nginx/conf/nginx.conf`
5. `http{}`中的`server{}`改为以下
```
server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        # 新加，网页文件的根目录。
		root  html;

        location / {
        # 删掉，移到了上面，可以同时管理PHP和HTML
		# root  html;
         index index.html index.htm;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        # 取消注释，与PHP联动
        location ~ \.php$ {
        # 删掉因为上面已经定义
        #    root           html;
        # PHP-fpm端口的端口监听，应与php-fpm的配置文件一样
            fastcgi_pass   127.0.0.1:9000;
        # 首页文件设定，上面已经设定，删掉
        #    fastcgi_index  index.php;
        # 设置脚本文件请求路径
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
	    fastcgi_param  SCRIPT_FILENAME  $document_root$fastcgi_script_name;
            include        fastcgi_params;
        }

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }
```

6. 如果数据库乱码，在php代码中连接数据库后加入`$conn->query("set names utf8");`
7. 如果MySQL链接失败，数据地址改为`127.0.0.1`
7. 重启解决90%的问题
8. 重装解决95%和问题
9. 花钱解决100%的问题
10. [配置nginx只允许域名访问，禁止ip访问【图文教程】](https://blog.csdn.net/IT_Most/article/details/108784791)
11. [如何将域名解析到指定的端口（linux服务器，超级简单，图文）](https://blog.csdn.net/weixin_56775476/article/details/118492498)
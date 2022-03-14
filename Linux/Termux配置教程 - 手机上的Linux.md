# Termux配置教程

* 官网：<https://termux.com/>
* Github：<https://github.com/termux/termux-app>
* 文章来源：<https://www.sqlsec.com/2018/05/termux.html#toc-heading-62>

## 目录

[TOC]



## 基本命令

```bash
pkg search <query>              # 搜索包
pkg install <package>           # 安装包
pkg uninstall <package>         # 卸载包
pkg reinstall <package>         # 重新安装包
pkg update                      # 更新源
pkg upgrade                     # 升级软件包
pkg list-all                    # 列出可供安装的所有包
pkg list-installed              # 列出已经安装的包
pkg show <package>              # 显示某个包的详细信息
pkg files <package>             # 显示某个包的相关文件夹路径
```

除了通过上述的 `pkg` 命令安装软件以外，如果我们有 `.deb` 软件包文件，也可以使用 `dpkg` 进行安装。

```bash
dpkg -i ./package.de         # 安装 deb 包
dpkg --remove [package name] # 卸载软件包
dpkg -l                      # 查看已安装的包
man dpkg                     # 查看详细文档
```

### 更换源

```\
termux-change-repo
```



### 查看端口

```bash
# 安装nmap端口扫描神器
pkg install nmap

# 扫描本地端口
nmap 127.0.0.1
```

### 配色方案

**脚本项目地址**：https://github.com/Cabbagec/termux-ohmyzsh/

该脚本主要使用了`zsh`来替代`bash`作为默认 shell，并且支持色彩和字体样式，同时也激活了外置存储，可以直接访问SD卡下的目录。主题默认为 agnoster，颜色样式默认为 Tango，字体默认为 Ubuntu。

> 执行下面这个命令确保已经安装好了 curl 命令

```bash
sh -c "$(curl -fsSL https://github.com/Cabbagec/termux-ohmyzsh/raw/master/install.sh)"  
```

如果因为不可抗力的原因，出现`port 443: Connection refused`网络超时的情况，那么执行下面国光迁移到国内的地址的命令即可：

```bash
sh -c "$(curl -fsSL https://html.sqlsec.com/termux-install.sh)"  
```

Android6.0 以上会弹框确认是否授权访问文件，点击`始终允许`授权后 Termux 可以方便的访问SD卡文件。

手机 App 默认只能访问自己的数据，如果要访问手机的存储，需要请求权限，如果你刚刚不小心点了拒绝的话，那么可以执行以下命令来重新获取访问权限：

```bash
termux-setup-storage
```

脚本允许后先后有如下两个选项:

```bash
Enter a number, leave blank to not to change: 14
Enter a number, leave blank to not to change: 6
```

分别选择`色彩样式`和`字体样式`，重启 Termux app 后生效配置。不满意刚刚的效果，想要继续更改配色方案的话，可以根据下面命令来更改对应的色彩配色方案：

**设置色彩样式**：

输入`chcolor`命令更换色彩样式，或者执行`~/.termux/colors.sh`命令

**设置字体**

运行`chfont`命令更换字体，或者执行`~/.termux/fonts.sh`命令

## 远程连接

1. 查看IP：`ifconfig`
2. 安装namp：`pkg install namp`
3. 查看自己的用户名：`whoami`
4. 安装SSH：`pkg install opemssh`
5. 添加密码：`passwd`
6. nmap IP
7. 每次都要：`sshd`
8. 查看端口号：`nmap 127.0.0.1`

## 目录结构

```bash
echo $HOME
/data/data/com.termux/files/home

echo $PREFIX
/data/data/com.termux/files/usr

echo $TMPPREFIX
/data/data/com.termux/files/usr/tmp/zsh
```

## 开发环境

### VIM

Vim 被称为编辑器之神，基本上 Linux 发行版都会自带 Vim，这个在前文基本工具已经安装了，如果你没有安装的话，可以使用如下命令安装：

```bash
pkg install vim
```

并且官方也已经封装了`vim-python`，对Python相关的优化。

```bash
pkg install vim-python
```

#### 解决汉字乱码

如果你的 Vim 打开汉字出现乱码的话，那么在家目录(`~`)下，新建`.vimrc`文件

```bash
vim .vimrc
```

添加内容如下：

```ini
set fileencodings=utf-8,gb2312,gb18030,gbk,ucs-bom,cp936,latin1
set enc=utf8
set fencs=utf8,gbk,gb2312,gb18030
```

然后`source`下变量：

```bash
source .vimrc
```

### Java

```bash
pkg update
pkg install openjdk-17
```

### MariaDB (MySQL)

MariaDB 是 MySQL 关系数据库管理系统的一个复刻，由社区开发，有商业支持，旨在继续保持在 GNU GPL下开源。开发这个分支的原因之一是：甲骨文公司收购了 MySQL 后，有将 MySQL 闭源的潜在风险，因此社区采用分支的方式来避开这个风险

**安装 MariaDB**

Termux 官方也封装了 MariaDB，所以安装起来很方便：

```bash
pkg install mariadb
```

这里基本上会安装很顺利，但是早期用户可能出现安装失败的情况，如果安装失败的话，这个时候手动在配置目录下创建`my.cnf.d`文件夹即可：

```bash
$ cd /data/data/com.termux/files/usr/etc/
$ mkdir my.cnf.d
```

#### 初始化数据库

> 早期的 Termux 安装完 MySQL是需要初始化数据库的，新版本在安装时候就已经初始化了数据库

```bash
mysql_install_db
```

2020年4月19日：国光今天安装的 MySQL 发现已经存在 mysql.user 表了，无需初始化

#### 启停mysql服务

因为正常启动完成后，MySQL 这个会话就一直存活，类似与 Debug 调试一样，此时使用`Ctrl + C` -> 中止当前进程也无济于事，体验式就一点都不优雅，所以这里国光使用Linux自带的`nohup`命令将其放到后台启动。

**启动**

```
nohup mysqld &
```

**停止**

```
kill -9 `pgrep mysql`
```

#### 默认的两个用户

> 用户登录的前提是 MySQL 服务在后台运行，如果你按照上一小节操作把 MySQL kill 掉的话，请重新启动一下 MySQL 服务

新版本的 Termux 安装初始化数据库的时候包含两个高权限用户，一个是无法访问的 **root** 用户

另一个用户就是 Termux 的**用户名**，默认密码为`空`，我们来登录看看：

```bash
mysql -u $(whoami)
```

**可以成功登录 并执行 SQL 语句**

那么这个无法登录的 root 用户该怎么办呢 ？不要着急 继续往下看

**修改 root 密码**

老版本的 Termux 的直接使用`mysql_secure_installation`可以设置密码，但是新版本的安全策略变更了 我们在设置密码的时候回提示当前密码不正确，所以这条路行不通了。

这里我们只能使用 MySQL 的另一个用户名，即 Termux 用户名登录，然后来修改 root 的密码，使用如下命令修改 root 密码:

```bash
# 登录 Termux 用户
mysql -u $(whoami)

# 修改 root 密码的 SQL语句
use mysql;
set password for 'root'@'localhost' = password('你设置的密码');

# 刷新权限 并退出
flush privileges;
quit; 
```

那么修改 root 密码就成功了。

**root 用户登录**

修改完密码之后我们就可以美滋滋地使用 root 用户来登录了：`mysql -u root -p`

#### 远程登陆

默认是无法成功连接的，这个时候我们需要到数据库手动开启 root 用户的远程访问权限:

```
grant all on *.* to root@'%' identified by 'P@ssw0rd' with grant option;
flush privileges;
```

## 安装Linux系统

```
pkg install proot git python -y

git clone https://github.com/sqlsec/termux-install-linux
cd termux-install-linux
python termux-linux-install.py
```

**启动**

```
cd ~/Termux-Linux/CentOS
./start-centos.sh
```

### 安装ssh

#### CentOS

```
yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Yum/ssh-yum.sh && bash ssh-yum.sh
```

#### Ubuntu

```
wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/SSH/Apt/ssh-apt.sh && bash ssh-apt.sh
```

### 配置安装图形界面

#### Ubuntu

```
wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh && bash de-apt-xfce4.sh
```

安装成功确认密码，打开VNC viewver  
address为localhost:1  
 
二次启动  
```
vncserver-start
vncserver-stop
vncserver-start
```



# Linux学习笔记

## 目录

[TOC]

## 下载环境

* [VMware Workstation Pro](https://www.vmware.com/products/workstation-pro/workstation-pro-evaluation.html)
* [VMware Workstation Player](https://my.vmware.com/en/web/vmware/downloads/info/slug/desktop_end_user_computing/vmware_workstation_player/16_0)
* [VirtualBox](https://www.virtualbox.org/wiki/Downloads)
  * [XShell](https://www.xshell.com/zh/free-for-home-school/)
* [FinalShell](http://www.hostbuf.com/)
* [CentOS](https://www.centos.org/download/)

## Linux的目录结构

* Linux没有炫目的可视化操作界面，它的操作大部分都是直接执行命令，而可执行文件都是保存在相应的目录中的，所以我们对linux的操作大多数时候都是查找和执行这些可执行文件的。
* linux的文件系统是采用级层式的树状目录结构，在此结构中的最上层是根目录“/”，然后在此目录下再创建其他的目录。
* **在Linux世界里，一切皆文件。**

![Linux目录结构](https://www.runoob.com/wp-content/uploads/2014/06/d0c50-linux2bfile2bsystem2bhierarchy.jpg)

* **/bin**：bin 是 Binaries (二进制文件) 的缩写, 这个目录存放着最经常使用的命令。
* **/boot**：这里存放的是启动 Linux 时使用的一些核心文件，包括一些连接文件以及镜像文件。
* **/dev** ：dev 是 Device(设备) 的缩写, 该目录下存放的是 Linux 的外部设备，在 Linux 中访问设备的方式和访问文件的方式是相同的。
* **/etc**：etc 是 Etcetera(等等) 的缩写,这个目录用来存放所有的系统管理所需要的配置文件和子目录。
* **/home**：用户的主目录，在 Linux 中，每个用户都有一个自己的目录，一般该目录名是以用户的账号命名的，如上图中的 alice、bob 和 eve。
* **/lib**：lib 是 Library(库) 的缩写这个目录里存放着系统最基本的动态连接共享库，其作用类似于 Windows 里的 DLL 文件。几乎所有的应用程序都需要用到这些共享库。
* **/lost+found**：这个目录一般情况下是空的，当系统非法关机后，这里就存放了一些文件。
* **/media**：linux 系统会自动识别一些设备，例如U盘、光驱等等，当识别后，Linux 会把识别的设备挂载到这个目录下。
* **/mnt**：系统提供该目录是为了让用户临时挂载别的文件系统的，我们可以将光驱挂载在 /mnt/ 上，然后进入该目录就可以查看光驱里的内容了。
* **/opt**：opt 是 optional(可选) 的缩写，这是给主机额外安装软件所摆放的目录。比如你安装一个ORACLE数据库则就可以放到这个目录下。默认是空的。
* **/proc**：proc 是 Processes(进程) 的缩写，/proc 是一种伪文件系统（也即虚拟文件系统），存储的是当前内核运行状态的一系列特殊文件，这个目录是一个虚拟的目录，它是系统内存的映射，我们可以通过直接访问这个目录来获取系统信息。  
这个目录的内容不在硬盘上而是在内存里，我们也可以直接修改里面的某些文件，比如可以通过下面的命令来屏蔽主机的ping命令，使别人无法ping你的机器：`echo 1 > /proc/sys/net/ipv4/icmp_echo_ignore_all`
* **/root**：该目录为系统管理员，也称作超级权限者的用户主目录。
* **/sbin**：s 就是 Super User 的意思，是 Superuser Binaries (超级用户的二进制文件) 的缩写，这里存放的是系统管理员使用的系统管理程序。
* **/selinux**：这个目录是 Redhat/CentOS 所特有的目录，Selinux 是一个安全机制，类似于 windows 的防火墙，但是这套机制比较复杂，这个目录就是存放selinux相关的文件的。
* **/srv**：该目录存放一些服务启动之后需要提取的数据。
* **/sys**：这是 Linux2.6 内核的一个很大的变化。该目录下安装了 2.6 内核中新出现的一个文件系统 sysfs 。  
sysfs 文件系统集成了下面3种文件系统的信息：针对进程信息的 proc 文件系统、针对设备的 devfs 文件系统以及针对伪终端的 devpts 文件系统。  
该文件系统是内核设备树的一个直观反映。  
当一个内核对象被创建的时候，对应的文件和目录也在内核对象子系统中被创建。
* **/tmp**：tmp 是 temporary(临时) 的缩写这个目录是用来存放一些临时文件的。
* **/usr**：usr 是 unix shared resources(共享资源)的缩写，这是一个非常重要的目录，用户的很多应用程序和文件都放在这个目录下，类似于 windows 下的 program files 目录。
* **/usr/bin**：系统用户使用的应用程序。
* **/usr/sbin**：超级用户使用的比较高级的管理程序和系统守护程序。
* **/usr/src**：内核源代码默认的放置目录。
* **/var**：var 是 variable(变量) 的缩写，这个目录中存放着在不断扩充着的东西，我们习惯将那些经常被修改的目录放在这个目录下。包括各种日志文件。
* **/run**：是一个临时文件系统，存储系统启动以来的信息。当系统重启时，这个目录下的文件应该被删掉或清除。如果你的系统上有 /var/run 目录，应该让它指向 run。

在 Linux 系统中，有几个目录是比较重要的，平时需要注意不要误删除或者随意更改内部文件。
* /etc： 上边也提到了，这个是系统中的配置文件，如果你更改了该目录下的某个文件可能会导致系统不能启动。
* /bin, /sbin, /usr/bin, /usr/sbin: 这是系统预设的执行文件的放置目录，比如 ls 就是在 /bin/ls 目录下的。
* 值得提出的是，/bin, /usr/bin 是给系统用户使用的指令（除root外的通用户），而/sbin, /usr/sbin 则是给 root 使用的指令。
* /var： 这是一个非常重要的目录，系统上跑了很多程序，那么每个程序都会有相应的日志产生，而这些日志就被记录到这个目录下，具体在 /var/log 目录下，另外 mail 的预设放置也是在这里。

## Linux系统管理

### vi和vim

![vim键盘图](https://www.runoob.com/wp-content/uploads/2015/10/vi-vim-cheat-sheet-sch.gif)

基本上 vi/vim 共分为三种模式，分别是命令模式（Command mode），输入模式（Insert mode）和底线命令模式（Last line mode）。 这三种模式的作用分别是：   

#### 命令模式（一般模式）：
用户刚刚启动 vi/vim，便进入了命令模式。  
此状态下敲击键盘动作会被Vim识别为命令，而非输入字符。比如我们此时按下i，并不会输入一个字符，i被当作了一个命令。  

以下是常用的几个命令：  

* i 切换到输入模式，以输入字符。
* x 删除当前光标所在处的字符。
* : 切换到底线命令模式，以在最底一行输入命令。

若想要编辑文本：启动Vim，进入了命令模式，按下i，切换到输入模式。  
命令模式只有一些最基本的命令，因此仍要依靠底线命令模式输入更多命令。  

#### 输入模式（编辑模式）
在命令模式下按下i就进入了输入模式。    
在输入模式中，可以使用以下按键：    

* 字符按键以及Shift组合，输入字符
* ENTER，回车键，换行
* BACK SPACE，退格键，删除光标前一个字符
* DEL，删除键，删除光标后一个字符
* 方向键，在文本中移动光标
* HOME/END，移动光标到行首/行尾
* Page Up/Page Down，上/下翻页
* Insert，切换光标为输入/替换模式，光标将变成竖线/下划线
* ESC，退出输入模式，切换到命令模式

#### 底线命令模式（命令行）

在命令模式下按下‘:’（**英文冒号**）就进入了底线命令模式。  
底线命令模式可以输入单个或多个字符的命令，可用的命令非常多。  
在底线命令模式中，基本的命令有（已经省略了冒号）：  

* q 退出程序
* w 保存文件

按ESC键可随时退出底线命令模式。    
简单的说，我们可以将这三个模式想成底下的图标来表示：   

![vim工作模式](https://www.runoob.com/wp-content/uploads/2014/07/vim-vi-workmodel.png)

![](img/1596764532593063.png)

#### 常用快捷键

**一般模式**

行为           | 快捷键
---------------|---------
复制当前行     | yy
复制当前n行    | nyy
粘贴           | p
删除当前行     | dd
删除当前n行    | ndd
撤消           | u
关键字向上查找 | ?word
关键字向下查找 | /word
查找           | n
反向查找       | N
显示行号       | :set nu
关闭行号       | :set nonu

### Linux用户管理

* Linux系统是一个多用户多任务的操作系统，任何一个要使用系统资源的用户，都必须首先向系统管理员申请一个账号，然后以这个账号的身份进入系统。root用户是系统默认创建的管理员账号。
* 添加用户：`useradd 用户名`
    * `useradd -d /home/ls lisi`：创建一个账号叫lisi，并且给lisi指定家目录/ls。
    * `passwd 用户名`设置密码
* 删除用户：`userdel 用户名`
    * `userdel –r 用户名`：删除用户，并把用户目录也删除。
* 查询用户信息：`id 用户名`
* 切换用户：`su 用户名`
    * 从高权限用户切换到低权限用户时，不需要输密码；否则，需要输密码。
    * 另：exit命令可以回到原来的用户。
* `sudo`：用root用户执行某条指令

### Linux组管理

* Linux的组类似于角色，系统可以对有共性的多个用户进行统一的管理。**每一个用户都至少属于一个组**，创建用户时如果不指定组，会默认创建一个跟用户名相同的组，并且把新创建的用户分配到组中，root用户默认属于root组。
* 添加组：`groupadd 组名`
* 删除组：`groupdel 组名`
* 用户进组：`gpasswd -a 用户名 组名`
* 用户出组：`gpasswd -d 用户名 组名`
* 新建用户指定主组：`useradd -g 组名 用户名`

### Linux系统操作

* `clear`：清屏，保留历史命令
* `shutdown now`： 立即关机
* `shudown -h 1`：  1小时后关机
* `shutdown –r now`： 立即重启
* `reboot`： 立即重启
* `sync`： 内存的数据同步到磁盘.
* `whoami`：当前用户
* `uname [options]`：显示系统信息
    * `-a`：显示全部的信息。
    * `-m`：显示电脑类型。
    * `-n`：显示在网络上的主机名称。
    * `-r`：显示操作系统的发行编号。
    * `-s`：显示操作系统名称。
    * `-v`：显示操作系统的版本。
* `date`：显示当前时间
    * `date [+'FORMAT']`
    * `date '+%Y-%m-%d %H:%M:%S %j'`
    * `date -s 字符串`：设置时间
    * <https://www.runoob.com/linux/linux-comm-date.html>
* `cal`：查看本月日历
    * `cal 年份`：查看某一年日历
* `bc`：计算器
    * 执行`bc`后，输入`scale=数字`设置小数点位，默认小数点0位
* `cat /proc/version`：显示内核版本
* `cat /etc/redhat-release`： 查看操作系统版本
* `/etc/passwd`：用户信息配置文件
* `/etc/shadow`：用户密码信息配置文件

### 配置环境变量

查看环境变量PATH的值：`echo $PATH`
输出当前系统所有的环境变量：`export`

1. 执行命令：`export PATH:$PATH`
    * `export PATH=$PATH:/home/uusama/mysql/bin`
    * 生效时间：立即生效
    * 生效期限：当前终端有效，窗口关闭后无效
    * 生效范围：仅对当前用户有效
    * 配置的环境变量中不要忘了加上原来的配置，即$PATH部分，避免覆盖原来配置
2. 修改文件
    1. `JAVA_HOME=/opt/java-1.8`
    2. `PATH=$PATH:$JAVA_HOME/bin`
    3. `export JAVA_HOME PATH`
    4. 命令行执行：`source 配置文件`
    * 生效时间：`source`命令后生效
    * 生效期限：永久
    * 生效范围：用户文件仅对当前用户有效，系统文件对所有用户有效


* 用户级别环境变量定义文件：`~/.bashrc`、`~/.profile`（部分系统为：`~/.bash_profile`）
* 系统级别环境变量定义文件`：/etc/bashrc`、`/etc/profile`(部分系统为：`/etc/bash_profile`）、`/etc/environment`
* Linux加载环境变量的顺序为
    1. /etc/environment
    2. /etc/profile
    3. /etc/bash.bashrc
    4. /etc/profile.d/test.sh
    5. ~/.profile
    6. ~/.bashrc

## Linux操作命令

### 帮助命令

1. 用来查看linux系统手册上的帮助信息：`man 命令`
    * `man ls`
    * 分屏显示、按回车翻一行、按空格翻一页、按q退出查看。
2. 用来查看命名的内置帮助信息：
    * `help 命令`
    * `命令 --help`
    * 版本信息：`命令 --version`

### 文件目录命令

* 查看当前路径：`pwd`
* 查看目录下的文件及目录列表：`ls [选项]` 查看当前目录
    * `ls [选项] [指定路径]`：查看指定目录
    * `-s`：显示文件大小
    * `-l`：列表形式显示
        * `-h`：易读大小`ls -lh`
    * `-a`：显示所有文件（包括隐藏文件）
    * `-R`：目录递归显示
    * `-S`：大小排序
    * `-t`：时间排序
    * `-r`：反向排序
* 查看空间大小：`du [参数] [文件]`
    * `-a`：显示目录中所有文件大小
    * `-k`：以KB为单位显示文件大小
    * `-m`：以MB为单位显示文件大小
    * `-g`：以GB为单位显示文件大小
    * `-h`：以易读方式显示文件大小（常用）
    * `-s`：文件大小的总计（用来看目录大小）
    * `du -sh /opt`：查看opt文件夹大小
* 切换目录：`cd`：切换到用户目录，同`cd ~`
    * `cd [路径]`：支持相对路径、绝对路径
* 创建目录：`mkdir [option] 路径`
    * 递归创建多级目录：`mkdir -p 目录/目录/目录`
* 删除空目录：`rmdir [option] 目录名`
    * 递归删除多级空目录：`rmdir -p 目录/目录/目录`
* 创建空文件：`touch 文件1 文件2 文件3`
* 复制：`cp [option] source... dest`    
    * `-r`：递归持续复制，用于目录的复制行为；(常用)
    * `-p`：保留用户权限
    * `-d`：若源文件为连接文件属性，则复制连接文件属性而非文件本身。
    * `-a`：相当于`-dpr`
    * `-f`：直接覆盖
* 剪切：`mv [option] source... dest`
    * `-f` ：直接覆盖
    * **文件重命名**：`mv [option] 文件 文件`
* 删除：`rm [option] 文件或目录`
    * `-r` ：递归删除啊！最常用在目录的删除了！这是非常危险的选项！！！
    * `-f` ：就是 force 的意思，忽略不存在的文件，不会出现警告信息；
    * `rm -rf /*`：删库跑路！！！！！（不要执行）
* 查看命令
    * cat  由第一行开始显示文件内容（常用）
        * -A ：相当於 -vET 的整合选项，可列出一些特殊字符而不是空白而已；
        * -b ：列出行号，仅针对非空白行做行号显示，空白行不标行号！
        * -E ：将结尾的断行字节 $ 显示出来；
        * -n ：列印出行号，连同空白行也会有行号，与 -b 的选项不同；
        * -T ：将 [tab] 按键以 ^I 显示出来；
        * -v ：列出一些看不出来的特殊字符
    * tac  从最后一行开始显示，可以看出 tac 是 cat 的倒着写！
    * nl   显示的时候，顺道输出行号！
        * -b ：指定行号指定的方式，主要有两种：
        * -b a ：表示不论是否为空行，也同样列出行号(类似 cat -n)；
        * -b t ：如果有空行，空的那一行不要列出行号(默认值)；
        * -n ：列出行号表示的方法，主要有三种：
        * -n ln ：行号在荧幕的最左方显示；
        * -n rn ：行号在自己栏位的最右方显示，且不加 0 ；
        * -n rz ：行号在自己栏位的最右方显示，且加 0 ；
        * -w num ：行号栏位的占用的位数。
    * more 一页一页的显示文件内容
        * 空白键 (space)：代表向下翻一页；
        * Enter         ：代表向下翻『一行』；
        * /字串         ：代表在这个显示的内容当中，向下搜寻『字串』这个关键字；
        * :f            ：立刻显示出档名以及目前显示的行数；
        * q             ：代表立刻离开 more ，不再显示该文件内容。
        * b 或 [ctrl]-b ：代表往回翻页，不过这动作只对文件有用，对管线无用。
    * less 与 more 类似，但是比 more 更好的是，他可以往前翻页！
        * 空白键    ：向下翻动一页；
        * [pagedown]：向下翻动一页；
        * [pageup]  ：向上翻动一页；
        * /字串     ：向下搜寻『字串』的功能；
        * ?字串     ：向上搜寻『字串』的功能；
        * n         ：重复前一个搜寻 (与 / 或 ? 有关！)
        * N         ：反向的重复前一个搜寻 (与 / 或 ? 有关！)
        * q         ：离开 less 这个程序；
    * head 只看头几行
        * -n ：后面接数字，代表显示几行的意思（默认10行）
    * tail 只看尾巴几行
* echo：输出内容到控制台
    * `echo $PATH`：查看环境变量
* `指令 > 目标文件`：将执行结果覆盖到目标文件上
* `指令 >> 目标文件`：追加

### 查找命令

* `find [路径] [参数] 条件 `
    * `-name`：按名字查找目录及所有子目录（占位符`*`）
    * `-size`：按大小 +5M（大于5MB） -9k（小于9kB）
    * `-user`：按所有者
* `locate 要搜索的文件名`
    * `updatedb`：更新搜索数据库
* `grep [option] 正则 文件`使用正则表达式搜索文本，把匹配的行打印出来
    * `-i`：忽略大小写
    * `-c`：只输出匹配行的计数。
    * `-n`：显示匹配行及 行号。
    * `-v`：反转匹配，选择没有被匹配到的内容。
    * `-w`：匹配整词，精确地单词,单词的两边必须是非字符符号(即不能是字母数字或下划线)
    * `-x`：仅选择与整行完全匹配的匹配项。精确匹配整行内容(包括行首行尾那些看不到的空格内容都要完全匹配)
    * `-r -d`：以递归的方式匹配
    * `-e`: 使用正则搜索
    * `-A`: 显示匹配行及前面多少行, 如: -A3, 则表示显示匹配行及前3行
    * `-B`: 显示匹配行及后面多少行, 如: -B3, 则表示显示匹配行及后3行
    * `-C`: 显示匹配行前后多少行, 如: -C3, 则表示显示批量行前后3行
    * **可以配合其指令输出指定的结果**：`rpm -q -a | grep -i java`

### 通配符

* `*`：匹配任意字符
* `?`：匹配单个字符
* `[xyz]`：匹配xyz中任一个字符
* `[a-z]`、`[0-9]`：范围匹配
* `[!xyz]`、`[^xyz]`：反向匹配

### 解压缩

* `gzip`
    * 单个文件的解压缩，解压和压缩都会把原文件删除
    * 压缩：`gzip 文件`
    * 解压：`gunzip 文件.gz`
    * 后缀：.gz
* `zip`
    * 多个文件的解压缩
    * 压缩：`zip 包名.zip 文件目录列表`
    * 解压：`unzip 压缩文件.zip -d 路径`
    * 后缀：.zip
* `tar`
    * 多个文件的打包和压缩（常用）
    * 压缩：`tar [选项] 包名.tar.gz 文件目录列表`
    * 解压：`tar [选项] 包名.tar.gz -C 路径`
    * `-c`：打包
    * `-v`：显示执行过程
    * `-f`：指定压缩文件名
    * `-z`：打包后通过gzip（打包并压缩）
    * `-x`：解压
    * `-C`：指定路径
    * 后缀：.tar.gz

### 组管理

* Linux 系统是一种典型的多用户系统，不同的用户处于不同的地位，拥有不同的权限。
* 在linux中的每个用户必须属于一个组，不能独立于组外，可以改变用户所属组。用户属于多个组
* 每个文件、目录也必须属于一个组，只能属于一个组。
* 如何看所属用户和组：`ls -l`
* 修改文件的所属用户：`chown [option] 新的所属用户 文件`
* 修改文件的所属组：`chgrp [option] 新的所属组 文件`
* 修改文件的所属用户及所属组：`chown [option] 新的所属用户:新的所属组 文件`
* `-R`：递归修改

### 权限管理

#### 文件权限概述

查看目录或文件的权限：`ls -l`

![文件列表详解](https://www.runoob.com/wp-content/uploads/2014/06/file-llls22.jpg)

文件信息的第一列为权限，第一个字母表示文件类型
* 当为 d 则是目录
* 当为 - 则是文件；
* 若是 l 则表示为链接文档(link file)；
* 若是 b 则表示为装置文件里面的可供储存的接口设备(可随机存取装置)；
* 若是 c 则表示为装置文件里面的串行端口设备，例如键盘、鼠标(一次性读取装置)。

接下来的字符中，以三个为一组，且均为`rwx`的三个参数的组合。其中， r 代表可读(read)、 w 代表可写(write)、 x 代表可执行(execute)。 要注意的是，**这三个权限的位置不会改变，如果没有权限，就会出现减号 - 而已。**

![文件属性详解](https://www.runoob.com/wp-content/uploads/2014/06/363003_1227493859FdXT.png)

每个文件都有三种身份，每个身份有三个rwx权限

* 属主权限（user）
* 属组权限（group）
* 其它组权限（other）

#### 更改文件权限

Linux 文件的基本权限就有九个，分别是 owner/group/others(拥有者/组/其他) 三种身份各有自己的 read/write/execute 权限。

1. `chmod [u][g][o][a] [+][-][=] [r][w][x],... 文件`
    * u = user、g = group、o = other、a = all、+增加、-去除、=设为 
2. `chmod [u][g][o][a] = [r][w][x],... 文件`
    * 将rwx组合设置
3. `chmod [0-7][0-7][0-7] 文件`
    * 每个身份的三种权限用1和0来开关，然后转为八进制（常用）

```
-rw-rw-r--. 1 root root 526 8月  12 15:16 1.txt
---------->
-rwxrw-r-x. 1 root root 526 8月  12 15:16 1.txt

chmod u+x,o+x 1.txt
chmod u=rwx,o=rx 1.txt
chmod 765 1.txt

```

## 网络配置

### 配置Linux网络

打开文件：/etc/sysconfig/network-scripts/ifcfg-ens33

```
BOOTPROTO="static"
ONBOOT="yes"
IPADDR=192.168.11.128
GATEWAY=192.168.11.2
DNS1=192.168.11.2
```

* 重启linux:  `reboot`或`systemctl restart network.service`


![image53.png](https://upload-images.jianshu.io/upload_images/24973821-55c47db669a36610.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 防火墙管理

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
* 开放端口
	* `firewall-cmd --zone=public --add-port=80/tcp --permanent`
	* 重启后端口生效
	* 命令含义：  `--zone` 作用域   `--add-port=80/tcp` 添加端口，格式为：端口/通讯协议   `--permanent` 永久生效，没有此参数重启后失效
* 移除开放端口
    * `firewall-cmd --zone=public --remove-port=80/tcp --permanent`
* 指定IP访问指定端口
    * `firewall-cmd --permanent --add-rich-rule="rule family="ipv4" source address="192.168.44.101" port protocol="tcp" port="8080" accept"`
    * 移除：`firewall-cmd --permanent --remove-rich-rule="rule family="ipv4" source address="192.168.44.101" port port="8080" protocol="tcp" accept"`
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

## 远程连接

### 远程控制

* Linux端口：22
* 查看Linux的IP地址`ifconfig`或`ip addr`
* 最小安装没有网络工具`yum install net-tools`
* 终端命令：
    * `-p`：指定端口
    * `-l`：指定用户名
    * `ssh -l 用户名 -p 端口 IP地址`
    * `ssh 用户名@IP地址 -p 端口`
    * `ssh root@192.168.43.1 -p 22`

### 上传文件

* 最好是配置好公钥无密码连接。免去输密码的繁琐
* 如果是windows，要可以正常使用scp，可以通过安装Git获得
* 上传文件到linux：`scp 本地文件 root@192.168.0.101:远程目录 `
  * `scp ‪C:\Users\admin\Desktop\a.txt root@192.168.0.101:/root`
* 上传文件夹到linux：`scp -r 本地目录 root@192.168.0.101:远程目录`
  * `scp -r ‪C:\Users\admin\Desktop\test root@192.168.0.101:/root`
* 从linux下载文件：`scp root@192.168.0.101:远程文件 ‪本地目录`
  * `scp root@192.168.0.101:/var/www/test.txt ‪C:\Users\admin\Desktop`
* 从linux下载文件夹：`scp -r root@192.168.0.101:远程目录 ‪本地目录`
  * `scp -r root@192.168.0.101:/var/www/test ‪C:\Users\admin\Desktop`


### 公钥无密码连接

![](https://img-blog.csdnimg.cn/20210423203713810.png)

* 首先在客户机上能使用`ssh`命令，如果是windows系统可以通过安装Git的方式来拥有`ssh`的功能
* windows打开cmd窗口，输入`ssh`得到以下，即可

```
C:\Users\admin>ssh
usage: ssh [-46AaCfGgKkMNnqsTtVvXxYy] [-B bind_interface]
           [-b bind_address] [-c cipher_spec] [-D [bind_address:]port]
           [-E log_file] [-e escape_char] [-F configfile] [-I pkcs11]
           [-i identity_file] [-J [user@]host[:port]] [-L address]
           [-l login_name] [-m mac_spec] [-O ctl_cmd] [-o option] [-p port]
           [-Q query_option] [-R address] [-S ctl_path] [-W host:port]
           [-w local_tun[:remote_tun]] destination [command]
```

* 客户机生成公钥和私钥
	* `ssh-keygen`
* 生成后，可在`C:\Users\{你的用户名}\.ssh`文件夹下看到`id_rsa`和`id_rsa.pub`两个文件
* 把`id_rsa.pub`上传到远程服务器的`~/.ssh`目录下
* 把远程服务器的`id_rsa.pub`更名为`authorized_keys`
	* `cp id_rsa.pub authorized_keys`

#### VS Code远程连接

* 安装`Remote - SSH`插件
* 在插件的设置配置文件中加入以下
* 一个`Host`代表一个连接主机

```
Host {任意名称}
    HostName {远程服务器IP地址}
    User root
    Port 22
    IdentityFile "私钥文件路径"
    ForwardAgent yes 
```

## 进程管理

在Linux中，每个执行的程序（代码）都称为一个进程。每一个进程都分配一个ID号。每一个进程，都会对应一个父进程。  
进程有两种运行方式：前台和后台。前台方式是目前用户可以在前台操作的，后台方式是实际在运行，但用户在前台看不见。  
一般系统的服务都是以后台进程的方式存在，而且都会常驻在系统中。直到关机才结束。

### 查看进程

* `ps [options]`
    * `–a`：显示当前终端下的所有进程信息
    * `–u`：以用户的格式显示进程信息
    * `–x`：显示后台进程运行的参数
    * `–e`：显示所有进程信息
    * `–f`：以全格式显示进程信息
* 显示结果说明
    * `USER`：用户名称
    * `PID`：进程号
    * `%CPU`：进程占用CPU的百分比
    * `%MEM`：进程占用物理内存的百分比
    * `VSZ`：进程占用的虚拟内存大小（单位：KB）
    * `RSS`：进程占用的物理内存大小（单位：KB）
    * `TT`：终端名称,缩写.
    * `STAT`：进程状态，其中S-睡眠，s-表示该进程是会话的先导进程，N-表示进程拥有比普通优先级更低的优先级，R-正在运行，D-短期等待，Z-僵死进程，T-被跟踪或者被停止等等
    * `STARTED`：进程的启动时间
    * `TIME`：CPU时间，即进程使用CPU的总时间
    * `COMMAND`：启动进程所用的命令和参数，如果过长会被截断显示
    * `PPID` 父进程的ID
    * `C` CPU使用的资源百分比
    * PRI指进程的执行优先权(Priority的简写)，其值越小越早被执行
    * `SZ` 使用掉的内存大小

### 终止进程

* `kill [options] 进程ID`
    * -9:表示强迫进程立即停止。
* `killall 进程名称(支持通配符)`

### 服务管理

服务是支持Linux运行的一些必要程序，本质上也是进程，**叫守护进程**。守护进程通常默默地运行在后台，为应用程序提供必要支撑，比如sshd、防火墙等。

命令格式：`systemctl [option] 服务名称`

* start：立刻启动后面接的 unit。
* stop：立刻关闭后面接的 unit。
* restart：立刻关闭后启动后面接的 unit，亦即执行 stop 再 start 的意思。
* reload：不关闭 unit 的情况下，重新载入配置文件，让设置生效。
* enable：设置下次开机时，后面接的 unit 会被启动。
* disable：设置下次开机时，后面接的 unit 不会被启动。
* status：目前后面接的这个 unit 的状态，会列出有没有正在执行、开机时是否启动等信息。
* is-active：目前有没有正在运行中。
* is-enable：开机时有没有默认要启用这个 unit。
* kill ：不要被 kill 这个名字吓着了，它其实是向运行 unit 的进程发送信号。
* show：列出 unit 的配置。
* mask：注销 unit，注销后你就无法启动这个 unit 了。
* unmask：取消对 unit 的注销。

CentOS7之前，使用命令`service 服务名称 [option]`

#### 查看网路服务(了解)

* `netstat [options]`
    * `-an` 按一定顺序排列输出
    * `-p` 显示哪个进程在调用
* `ping IP地址`
    * 查看网络连接状态

## Linux软件包管理

### RPM

* 在Centos7的`/run/media/user1/CentOS 7 x86_64/Packages`目录下有很多自带的RPM包
* `rpm –q -a` :查询所安装的所有软件包 
* `rpm –e [option] 软件包名称`
	* `-nodeps` 表示强制删除，用于被删除的软件包有依赖的情况
* `rpm [options] RPM包全路径名`
	* `-i`=install 安装
	* `-v`=verbose 提示
	* `-h`=hash 进度条

### YUM

Yum 是一个Shell前端软件包管理器。基于RPM包管理，能够从指定的服务器(在公网上)自动下载RPM包并且安装，可以自动处理依赖性关系，并且一次安装所有依赖的软件包。  
**注意：使用YUM的前提是可以连接外网。**  

* `yum list`：列出可安装的软件
* `yum list installed | grep xx`：查询已安装到本地的
* `yum [option] install 包名`：**安装**
    * `-y`：自动yes
    * `-q`：不显示安装过程
    * `-h`：帮助
* `yum remove 包名`：卸载
* `yum check-update`：检查所有软件更新
* `yum update`：更新所有可更新的软件
* `yum update 包名`：更新指定可更新的软件
* 清除缓存命令:
    * `yum clean packages`: 清除缓存目录下的软件包
    * `yum clean headers`: 清除缓存目录下的 headers
    * `yum clean oldheaders`: 清除缓存目录下旧的 headers
    * `yum clean, yum clean all (= yum clean packages; yum clean oldheaders)` :清除缓存目录下的软件包及旧的 headers

#### 国内YUM源

网易（163）yum源是国内最好的yum源之一 ，无论是速度还是软件版本，都非常的不错。
将yum源设置为163 yum，可以提升软件包安装和更新的速度，同时避免一些常见软件版本无法找到。

**安装步骤**  

首先备份/etc/yum.repos.d/CentOS-Base.repo  

```
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
```

下载对应版本 repo 文件, 放入 /etc/yum.repos.d/ (操作前请做好相应备份)  

* CentOS5 ：<http://mirrors.163.com/.help/CentOS5-Base-163.repo>
* CentOS6 ：<http://mirrors.163.com/.help/CentOS6-Base-163.repo>
* CentOS7 ：<http://mirrors.163.com/.help/CentOS7-Base-163.repo>

```
wget http://mirrors.163.com/.help/CentOS6-Base-163.repo
mv CentOS6-Base-163.repo CentOS-Base.repo
```

运行以下命令生成缓存

```
yum clean all
yum makecache
```

除了网易之外，国内还有其他不错的 yum 源，比如中科大和搜狐。  
中科大的 yum 源，安装方法查看：<https://lug.ustc.edu.cn/wiki/mirrors/help/centos>  
sohu 的 yum 源安装方法查看: <http://mirrors.sohu.com/help/centos.html>    

### apt

* apt（Advanced Packaging Tool）是一个在 Debian 和 Ubuntu 中的 Shell 前端软件包管理器。
* apt 命令提供了查找、安装、升级、删除某一个、一组甚至全部软件包的命令，而且命令简洁而又好记。
* apt 命令执行需要超级管理员权限(root)。


* `apt [options] [command] [package ...]`
* 列出所有可更新的软件清单命令：`sudo apt update`
* 升级软件包：`sudo apt upgrade`
* 列出可更新的软件包及版本信息：`apt list --upgradeable`
* 升级软件包，升级前先删除需要更新软件包：`sudo apt full-upgrade`
* 安装指定的软件命令：`sudo apt install <package_name>`
* 安装多个软件包：`sudo apt install <package_1> <package_2> <package_3>`
* 更新指定的软件命令：`sudo apt update <package_name>`
* 显示软件包具体信息,例如：版本号，安装大小，依赖关系等等：`sudo apt show <package_name`
* 删除软件包命令：`sudo apt remove <package_name>`
* 清理不再使用的依赖和库文件: `sudo apt autoremove`
* 移除软件包及配置文件: `sudo apt purge <package_name>`
* 查找软件包命令： `sudo apt search <keyword>`
* 列出所有已安装的包：`apt list --installed`
* 列出所有已安装的包的版本信息：`apt list --all-versions`








# IDEA使用技巧



* 本文来自B站UP主：IT老哥
  * 视频地址：<https://www.bilibili.com/video/BV1Qm4y1S7z3?share_source=copy_web>
  * 博主地址：<https://space.bilibili.com/526653251>


[TOC]


## 快捷键

### 新手必须掌握：

| 快捷键名称           | 快捷键介绍                         |
| -------------------- | ---------------------------------- |
| **Alt+Insert**       | **快速生成构造器/Getter/Setter等** |
| **Ctrl+N**           | **快速打开类**                     |
| **Ctrl+R**           | **替换文本**                       |
| **Ctrl+F**           | **查找文本**                       |
| **Ctrl+X**           | **删除行**                         |
| **Ctrl+D**           | **复制行**                         |
| **Ctrl+O**           | **重写方法**                       |
| **Ctrl+I**           | **实现方法**                       |
| **Ctrl+Y**           | **删除当前行**                     |
| **Shift+Ente**       | **向下插入新行**                   |
| **Ctrl+Shift+F**     | **全局查找**                       |
| **Ctrl+”+/-”**       | **当前方法展开、折叠**             |
| **Ctrl+Shift+”+/-”** | **全部展开、折叠**                 |
| **Ctrl+Enter**       | **上插一行**                       |

### Ctrl：

| Ctrl + F         | 当前文件文本查找                                             |
| ---------------- | ------------------------------------------------------------ |
| Ctrl + R         | 当前文件文本替换                                             |
| Ctrl + Z         | 撤销                                                         |
| Ctrl + Y         | 删除光标行                                                   |
| Ctrl + X         | 剪切光标行                                                   |
| Ctrl + C         | 复制光标行                                                   |
| Ctrl + D         | 复制光标行                                                   |
| Ctrl + E         | 显示最近打开的文件记录列表                                   |
| Ctrl + N         | 根据输入的类名查找类文件                                     |
| Ctrl + G         | 在当前文件跳转到指定行处                                     |
| Ctrl + J         | 插入自定义动态代码模板                                       |
| Ctrl + P         | 方法参数提示显示                                             |
| Ctrl + Q         | 光标所在的变量 / 类名 / 方法名等上面（也可以在提示补充的时候按），显示文档内容 |
| Ctrl + U         | 前往当前光标所在的方法的父类的方法 / 接口定义                |
| Ctrl + B         | 进入光标所在的方法/变量的接口或是定义出，等效于 Ctrl + 左键单击 |
| Ctrl + H         | 显示当前类的层次结构                                         |
| Ctrl + /         | 注释光标所在行代码，会根据当前不同文件类型使用不同的注释符号 |
| Ctrl + \[        | 移动光标到当前所在代码的花括号开始位置                       |
| Ctrl + \]        | 移动光标到当前所在代码的花括号结束位置                       |
| Ctrl + F1        | 在光标所在的错误代码出显示错误信息                           |
| Ctrl + F3        | 调转到所选中的词的下一个引用位置                             |
| Ctrl + F4        | 关闭当前编辑文件                                             |
| Ctrl + F8        | Debug 模式下，设置光标当前行为断点，如果当前已经是断点则去掉断点 |
| Ctrl + F9        | 执行 Make Project 操作                                       |
| Ctrl + F11       | 选中文件 / 文件夹，使用助记符设定                            |
| Ctrl + F12       | 弹出当前文件结构层，可以在弹出的层上直接输入，进行筛选       |
| Ctrl + Tab       | 编辑窗口切换，如果在切换的过程又加按上delete，则是关闭对应选中的窗口 |
| Ctrl + Enter     | 智能分隔行                                                   |
| Ctrl + End       | 跳到文件尾                                                   |
| Ctrl + Home      | 跳到文件头                                                   |
| Ctrl + Space     | 基础代码补全，默认在 Windows 系统上被输入法占用，需要进行修改，建议修改为 Ctrl + 逗号 （必备） |
| Ctrl + Delete    | 删除光标后面的单词或是中文句                                 |
| Ctrl + BackSpace | 删除光标前面的单词或是中文句                                 |
| Ctrl + 1,2,3...9 | 定位到对应数值的书签位置                                     |
| Ctrl + 左键单击  | 在打开的文件标题上，弹出该文件路径                           |
| Ctrl + 光标定位  | 按 Ctrl 不要松开，会显示光标所在的类信息摘要                 |
| Ctrl + 左方向键  | 光标跳转到当前单词 / 中文句的左侧开头位置                    |
| Ctrl + 右方向键  | 光标跳转到当前单词 / 中文句的右侧开头位置                    |
| Ctrl + 前方向键  | 等效于鼠标滚轮向前效果                                       |
| Ctrl + 后方向键  | 等效于鼠标滚轮向后效果                                       |

### Alt：

| Alt + \`       | 显示版本控制常用操作菜单弹出层                               |
| -------------- | ------------------------------------------------------------ |
| Alt + Q        | 弹出一个提示，显示当前类的声明 / 上下文信息                  |
| Alt + F1       | 显示当前文件选择目标弹出层，弹出层中有很多目标可以进行选择   |
| Alt + F2       | 对于前面页面，显示各类浏览器打开目标选择弹出层               |
| Alt + F3       | 选中文本，逐个往下查找相同文本，并高亮显示                   |
| Alt + F7       | 查找光标所在的方法 / 变量 / 类被调用的地方                   |
| Alt + F8       | 在 Debug 的状态下，选中对象，弹出可输入计算表达式调试框，查看该输入内容的调试结果 |
| Alt + Home     | 定位 / 显示到当前文件的 Navigation Bar                       |
| Alt + Enter    | IntelliJ IDEA 根据光标所在问题，提供快速修复选择，光标放在的位置不同提示的结果也不同 |
| Alt + Insert   | 代码自动生成，如生成对象的 set / get 方法，构造函数，toString() 等 |
| Alt + 左方向键 | 按左方向切换当前已打开的文件视图                             |
| Alt + 右方向键 | 按右方向切换当前已打开的文件视图                             |
| Alt + 前方向键 | 当前光标跳转到当前文件的前一个方法名位置                     |

### Shift：

| Shift + F1  | 如果有外部文档可以连接外部文档                               |
| ----------- | ------------------------------------------------------------ |
| Shift + F2  | 跳转到上一个高亮错误 或 警告位置                             |
| Shift + F3  | 在查找模式下，查找匹配上一个                                 |
| Shift + F4  | 对当前打开的文件，使用新Windows窗口打开，旧窗口保留          |
| Shift + F6  | 对文件 / 文件夹 重命名                                       |
| Shift + F7  | 在 Debug 模式下，智能步入。断点所在行上有多个方法调用，会弹出进入哪个方法 |
| Shift + F8  | 在 Debug 模式下，跳出，表现出来的效果跟 F9 一样              |
| Shift + F9  | 等效于点击工具栏的 Debug 按钮                                |
| Shift + F10 | 等效于点击工具栏的 Run 按钮                                  |
| Shift + F11 | 弹出书签显示层                                               |
| Shift + Tab | 取消缩进                                                     |
| Shift + ESC | 隐藏当前 或 最后一个激活的工具窗口                           |

### Ctrl + Alt：

-----------

| Ctrl + Alt + L     | 格式化代码，可以对当前文件和整个包目录使用                   |
| ------------------ | ------------------------------------------------------------ |
| Ctrl + Alt + O     | 优化导入的类，可以对当前文件和整个包目录使用                 |
| Ctrl + Alt + I     | 光标所在行 或 选中部分进行自动代码缩进，有点类似格式化       |
| Ctrl + Alt + T     | 对选中的代码弹出环绕选项弹出层                               |
| Ctrl + Alt + J     | 弹出模板选择窗口，讲选定的代码加入动态模板中                 |
| Ctrl + Alt + H     | 调用层次                                                     |
| Ctrl + Alt + B     | 在某个调用的方法名上使用会跳到具体的实现处，可以跳过接口     |
| Ctrl + Alt + V     | 快速引进变量                                                 |
| Ctrl + Alt + Y     | 同步、刷新                                                   |
| Ctrl + Alt + S     | 打开 IntelliJ IDEA 系统设置                                  |
| Ctrl + Alt + F7    | 显示使用的地方。寻找被该类或是变量被调用的地方，用弹出框的方式找出来 |
| Ctrl + Alt + F11   | 切换全屏模式                                                 |
| Ctrl + Alt + Enter | 光标所在行上空出一行，光标定位到新行                         |
| Ctrl + Alt + Home  | 弹出跟当前文件有关联的文件弹出层                             |
| Ctrl + Alt + Space | 类名自动完成                                                 |

### Ctrl + Shift：

-------------

| Ctrl + Shift + F     | 根据输入内容查找整个项目 或 指定目录内文件                   |
| -------------------- | ------------------------------------------------------------ |
| Ctrl + Shift + R     | 根据输入内容替换对应内容，范围为整个项目 或 指定目录内文件   |
| Ctrl + Shift + J     | 自动将下一行合并到当前行末尾                                 |
| Ctrl + Shift + Z     | 取消撤销                                                     |
| Ctrl + Shift + W     | 递进式取消选择代码块。可选中光标所在的单词或段落，连续按会在原有选中的基础上再扩展取消选中范围 |
| Ctrl + Shift + N     | 通过文件名定位 / 打开文件 / 目录，打开目录需要在输入的内容后面多加一个正斜杠 |
| Ctrl + Shift + U     | 对选中的代码进行大 / 小写轮流转换                            |
| Ctrl + Shift + T     | 对当前类生成单元测试类，如果已经存在的单元测试类则可以进行选择 |
| Ctrl + Shift + C     | 复制当前文件磁盘路径到剪贴板                                 |
| Ctrl + Shift + V     | 弹出缓存的最近拷贝的内容管理器弹出层                         |
| Ctrl + Shift + E     | 显示最近修改的文件列表的弹出层                               |
| Ctrl + Shift + H     | 显示方法层次结构                                             |
| Ctrl + Shift + B     | 跳转到类型声明处                                             |
| Ctrl + Shift + I     | 快速查看光标所在的方法 或 类的定义                           |
| Ctrl + Shift + A     | 查找动作 / 设置                                              |
| Ctrl + Shift + F7    | 高亮显示所有该选中文本，按Esc高亮消失                        |
| Ctrl + Shift + F8    | 在 Debug 模式下，指定断点进入条件                            |
| Ctrl + Shift + F9    | 编译选中的文件 / 包 / Module                                 |
| Ctrl + Shift + F12   | 编辑器最大化                                                 |
| Ctrl + Shift + Space | 智能代码提示                                                 |
| Ctrl + Shift + Enter | 自动结束代码，行末自动添加分号                               |

### Alt + Shift：

------------

| Alt + Shift + N        | 选择 / 添加 task                                             |
| ---------------------- | ------------------------------------------------------------ |
| Alt + Shift + F        | 显示添加到收藏夹弹出层                                       |
| Alt + Shift + C        | 查看最近操作项目的变化情况列表                               |
| Alt + Shift + F        | 添加到收藏夹                                                 |
| Alt + Shift + I        | 查看项目当前文件                                             |
| Alt + Shift + F7       | 在 Debug 模式下，下一步，进入当前方法体内，如果方法体还有方法，则会进入该内嵌的方法中，依此循环进入 |
| Alt + Shift + F9       | 弹出 Debug 的可选择菜单                                      |
| Alt + Shift + 前方向键 | 移动光标所在行向上移动                                       |
| Alt + Shift + 后方向键 | 移动光标所在行向下移动                                       |

### 其他：

---

| F2   | 跳转到下一个高亮错误 或 警告位置                             |
| ---- | ------------------------------------------------------------ |
| F3   | 在查找模式下，定位到下一个匹配处                             |
| F4   | 编辑源                                                       |
| F7   | 在 Debug 模式下，进入下一步，如果当前行断点是一个方法，则进入当前方法体内，如果该方法体还有方法，则不会进入该内嵌的方法中 |
| F8   | 在 Debug 模式下，进入下一步，如果当前行断点是一个方法，则不进入当前方法体内 |
| F9   | 在 Debug 模式下，恢复程序运行，但是如果该断点下面代码还有断点则停在下一个断点上 |
| F11  | 添加书签                                                     |
| F12  | 回到前一个工具窗口                                           |
| Tab  | 缩进                                                         |
| ESC  | 从工具窗口进入代码文件窗口                                   |
## 表达式后缀

| 输入（exp：表达式）       | 输出                 |
| ---------- | ----------------------------------- |
| exp.var    | 自动判断类型、声明变量                 |
| exp.sout   | 快捷输出 `System.out.println(exp);` |
| exp.soutv  | 快捷输出，并自动加上说明 `System.out.println("exp = " + exp);` |
| exp.try    | 单行异常捕获                        |
| exp.return | 返回值                              |
| exp.nn     | `if (exp != null){}`                |
| exp.null   | `if (exp == null){}`                |
| exp.par    | `(exp)`光标在后                     |
| exp.arg    | `(exp)`光标跳到前面                 |
## 插件

### Codota

只需要点一下，便可以获取到在github、stackoverflow等上排名最高的片段，并在IDE中显示出来，更快、更方便。

如果你要找某个类的示例代码，只需要选择某个类名，点击右键选择Get relevant examples 或快捷键 ctrl+shift+O，如下图

![图片](https://img-blog.csdnimg.cn/img_convert/17f52962f3f75cd1426537b9b0f945f3.png)

![图片](https://img-blog.csdnimg.cn/img_convert/fdff8e42c3db03187164e86199ac2ebb.png)

### MyBatis Log Plugin

> 有时候我们需要运行过程中产生的SQL语句来帮助我们排查某些问题，这款插件可以把Mybatis输出的SQL日志还原成完整的SQL语句，就不需要我们去手动转换了。

首先我们需要打开这款插件的窗口；

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eae3956eed2~tplv-t2oaga2asx-watermark.awebp)

当我们调用方法，控制台输出Mybatis的SQL日志时；

```sql
2020-04-28 15:52:20.455 DEBUG 13960 --- [nio-8081-exec-1] c.m.m.m.UmsAdminMapper.selectByExample   : ==>  Preparing: select id, username, password, icon, email, nick_name, note, create_time, login_time, status from ums_admin WHERE ( username = ? ) 
2020-04-28 15:52:20.456 DEBUG 13960 --- [nio-8081-exec-1] c.m.m.m.UmsAdminMapper.selectByExample   : ==> Parameters: admin(String)
2020-04-28 15:52:20.463 DEBUG 13960 --- [nio-8081-exec-1] c.m.m.m.UmsAdminMapper.selectByExample   : <==      Total: 1

```

该插件会自动帮我们转换成对应的SQL语句；

```sql
1  2020-04-28 15:50:40.487 DEBUG 9512 --- [nio-8081-exec-9] c.m.m.m.UmsAdminMapper.selectByExample   : ==>
select id, username, password, icon, email, nick_name, note, create_time, login_time, status
 FROM ums_admin
 WHERE ( username = 'admin' );

```

有的时候我们需要转换的日志并不在自己的控制台上，这时可以使用插件的`SQL Text`功能：

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eae3a697f21~tplv-t2oaga2asx-watermark.awebp)

直接复制我们需要转换的日志，然后点击`Restore Sql`按钮即可。

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eae646446ef~tplv-t2oaga2asx-watermark.awebp)



### RestfulToolkit

> 一套Restful服务开发辅助工具集，提供了项目中的接口概览信息，可以根据URL跳转到对应的接口方法中去，内置了HTTP请求工具，对请求方法做了一些增强功能，总之功能很强大！

可以通过右上角的`RestServices`按钮显示项目中接口的概览信息；

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eae625c7169~tplv-t2oaga2asx-watermark.awebp)

可以通过搜索按钮，根据URL搜索对应接口；

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eae6a4e4d88~tplv-t2oaga2asx-watermark.awebp)

可以通过底部的HTTP请求工具来发起接口测试请求；

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eae6b2244d2~tplv-t2oaga2asx-watermark.awebp)

通过在接口方法上右键可以生成查询参数、请求参数、请求URL；

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eae6f43b2f8~tplv-t2oaga2asx-watermark.awebp)

通过在实体类上右键可以直接生成实体类对应的JSON；

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eae754c9d7e~tplv-t2oaga2asx-watermark.awebp)



### GsonFormat

> 这款插件可以把JSON格式的字符串转化为实体类，当我们要根据JSON字符串来创建实体类的时候用起来很方便。

- 首先我们需要先创建一个实体类，然后在类名上右键`Generate`，之后选择`GsonFormat`；

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eae997a872f~tplv-t2oaga2asx-watermark.awebp)

- 输入我们需要转换的JSON字符串：

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaea03e4151~tplv-t2oaga2asx-watermark.awebp)

- 选择性更改属性名称和类型：

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaeaf6a3301~tplv-t2oaga2asx-watermark.awebp)

- 点击确定后直接生成实体类。

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaebe23f2d6~tplv-t2oaga2asx-watermark.awebp)



### Grep Console

> 一款帮你分析控制台日志的插件，可以对不同级别的日志进行不同颜色的高亮显示，还可以用来按关键字搜索日志内容。

- 当项目打印日志的时候，可以发现不同日志级别的日志会以不同颜色来显示；

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaeb1115ddd~tplv-t2oaga2asx-watermark.awebp)

  

- 如果你需要修改配色方案的话，可以通过`Tools`打开该插件的配置菜单；

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaec20847bd~tplv-t2oaga2asx-watermark.awebp)

- 然后通过配置菜单修改配色方案；

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaec42af774~tplv-t2oaga2asx-watermark.awebp)

- 可以通过在控制台右键并使用`Grep`按钮来调出日志分析的窗口：

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaec6bd4c36~tplv-t2oaga2asx-watermark.awebp)

- 然后直接通过关键字来搜索即可。

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaed56b6d35~tplv-t2oaga2asx-watermark.awebp)



### Maven Helper

> 解决Maven依赖冲突的好帮手，可以快速查找项目中的依赖冲突，并予以解决！

- 我们可以通过`pom.xml`文件底部的`依赖分析`标签页查看当前项目中的所有依赖；

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaf004f2cfd~tplv-t2oaga2asx-watermark.awebp)

- 通过`冲突`按钮我们可以筛选出所有冲突的依赖，当前项目`guava`依赖有冲突，目前使用的是`18.0`版本；

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaf0054df0f~tplv-t2oaga2asx-watermark.awebp)

- 选中有冲突的依赖，点击`Exclude`按钮可以直接排除该依赖；

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaf053688d7~tplv-t2oaga2asx-watermark.awebp)

- 同时`pom.xml`中也会对该依赖添加`<exclusion>`标签，是不是很方便啊！

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaf09f59014~tplv-t2oaga2asx-watermark.awebp)



### Statistic

- 我们可以通过顶部菜单中的`View->Tool Windows->Statistic`按钮开启该功能；

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaf1b8d29c5~tplv-t2oaga2asx-watermark.awebp)

- 此时就可以看到我们项目代码的统计情况了，比如我的开源项目`mall`中`java`代码大小为`2818kB`，行数为`85645`。

  ![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/5/12/17208eaf220bf1d7~tplv-t2oaga2asx-watermark.awebp)



### Key Promoter X 快捷键插件

![img](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dddceef275ad46fc978727991076cb90~tplv-k3u1fbpfcp-watermark.awebp)

无需鼠标的编码速度更快、效率更高，这已经不是什么秘密了，但是当IntelliJ IDEA有这么多快捷键需要记住时，你怎么能以键盘为中心呢？

它会训练你使用它们，就像一个持久而细致的coach一样，当您单击IDE中的元素时，它将显示一个带有相关快捷方式的工具提示。此外，对于没有快捷方式的按钮，Key promotor X会提示您创建快捷方式。

熟能生巧！过了一段时间，你会发现你下意识地保存自己的点击和使用必要的快捷方式。



### Rainbow brackets 花括号插件

![img](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2d9af77cd27f47f78e4290e6afefdb23~tplv-k3u1fbpfcp-watermark.awebp)



### GenerateAllSetter

可以直接生成这个对象的所有set方法，非常方便。

将光标放在变量声明的那一行，注意不能是分号后面。然后按快捷键Alt + Enter，就会弹出菜单供你选择。

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/4/16/17181ec2e9dd3ea2~tplv-t2oaga2asx-watermark.awebp)

![img](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/4/16/17181ec2ebb0cda3~tplv-t2oaga2asx-watermark.awebp)





### HighlightBracketPair —— 括号开始结尾 高亮显示。

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6eba84edf59645bc9858f3020d40cf3c~tplv-k3u1fbpfcp-watermark.awebp)

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/159118c6774e44eb96eeddcc38d54263~tplv-k3u1fbpfcp-watermark.awebp)

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/47a21413b6f240cfaa77e51b4a5e95b2~tplv-k3u1fbpfcp-watermark.awebp)



### google-java-format —— 代码自动格式化

这个插件的优点在于不需要手动快捷键去格式化代码

### JUnit Generator V2.0

自动生成测试代码。

![IntelliJ IDEA 2020.2.4款 神级超级牛逼插件推荐（自用，真的超级牛逼）_IDEA_16](https://s5.51cto.com/images/blog/202105/28/f3cc903eec688a6bcc847880c6261b4d.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

![IntelliJ IDEA 2020.2.4款 神级超级牛逼插件推荐（自用，真的超级牛逼）_IDEA教程_17](https://s7.51cto.com/images/blog/202105/28/b74315a2c3d7755df958d6f1d3dd0188.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

## 主题

### **官网主题** | **Intellij Light**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0f350e55b22d445781f81943ea3d3433~tplv-k3u1fbpfcp-zoom-1.image)

在最开始的很长时间都是使用这个主题，最主要的问题是 白天还好，晚上写代码的话 就有点头疼了。所以官网也提供了 dark 版本

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/767f5b43048b4b65821c813029ff1534~tplv-k3u1fbpfcp-zoom-1.image)

现在大家大部分都是这两种中使用一种

### **Material Theme**

这个算是非常出名的主题了，不过在 2021.3 版本后就要收费了。不过别慌有解决办法 放在最后

### **主题名 | Acr Dark**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/70ec96f169c34354a5bc5843b5d82668~tplv-k3u1fbpfcp-zoom-1.image)

### **主题名 | Dracula**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/413b726ea9a540609b8815fe6e8bd500~tplv-k3u1fbpfcp-zoom-1.image)

**主题名 | Github**  

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b42cf29268d44d4966dfc0ac96c2570~tplv-k3u1fbpfcp-zoom-1.image)

**主题名 | Github Dark** 

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/bddd336d70c64effa841aca66aa77a9f~tplv-k3u1fbpfcp-zoom-1.image)

**主题名 | Darker**  

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/188a364bc73b49f7bc0bdc8d66785c1f~tplv-k3u1fbpfcp-zoom-1.image)

### 主题名 | Deep Ocean

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/27aeae6e75074348ac1e9bb2fee8633c~tplv-k3u1fbpfcp-zoom-1.image)

**主题名 | Lighter**  

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d4876cdbc2a548f489db8ed90723904b~tplv-k3u1fbpfcp-zoom-1.image)

**主题名 | Oceanic**  

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/37b73e7178ab43659d640b8e3919d540~tplv-k3u1fbpfcp-zoom-1.image)

**主题名 | Monokai Pro**  

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ca846b7e67494a7aa19ee2cc45fa27b5~tplv-k3u1fbpfcp-zoom-1.image)

**主题名 | Moonlight**  

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6e8323d89cad4f4c8bbd3c036e146fd3~tplv-k3u1fbpfcp-zoom-1.image)

### **主题名 | Solarized Light**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/313417a490a74cfc8c38aff81bdb1f96~tplv-k3u1fbpfcp-zoom-1.image)

### **tomorrow-theme**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6be7d8f78f004740a4ba5ab85aa6a068~tplv-k3u1fbpfcp-zoom-1.image)

上面的主题都来自一个开源项目 tomorrow-theme 就算是上面的 material ui 也算是这个项目后的衍生项目。下载可以去 Github 主题：

```
https://github.com/chriskempson/tomorrow-theme
```

### **solarized**


![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6c6c1cd6db8a423ba655d489c6844a0c~tplv-k3u1fbpfcp-zoom-1.image)

Github 地址 https://github.com/jkaving/intellij-colors-solarized 将主题地址下载下来直接导入 Idea 中即可。Intellij Idea 的主题项目是 solarized 项目的衍生项目。

```
https://github.com/altercation/solarized
```

### **Monokai Theme**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7b7dd4dd0a1d40ef927c89c187ded92b~tplv-k3u1fbpfcp-zoom-1.image)

下载地址：

```
https://darekkay.com/blog/monokai-theme-intellij/dk-monokai-142.jar
```

直接将下载的 Jar 包导入即可，如果没有选中，需要手动的到 close scheme 下去选择一下

### Intellij-Zenburn

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4e3ef18b589c46f697f003943446318f~tplv-k3u1fbpfcp-zoom-1.image)

Github 下载地址：

```
https://github.com/pedropenna/Intellij-Zenburn
```

### **dracula**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8eb052a6102540ba95a3c1c5c7135724~tplv-k3u1fbpfcp-zoom-1.image)

Github 下载地址：

```
https://github.com/dracula/jetbrains
```

### **gruvbox**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3f04e033f7364b02a8285384f660fa2f~tplv-k3u1fbpfcp-zoom-1.image)

Github 下载地址：

```
https://github.com/Vincent-P/gruvbox-intellij-theme
```

### **lucario**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7aa8803bfabc408da6618d9b10f333f0~tplv-k3u1fbpfcp-zoom-1.image)

Github 下载地址：

```
https://github.com/raphamorim/lucario#jetbrains-editors
```

### **Atom One Dark**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5d7f48b1a97d45028180295bffd506c5~tplv-k3u1fbpfcp-zoom-1.image)

我自己最爱的主题，直接在插件市场下载即可。如果处理 Material Theme 收费问题

2021.1 版本后，Material Theme 收费，一年 15 美元，对于我自己来说还是有点小贵的，这里给出几种方案处理这个问题

第一个就是去网上下载一个 2021.1 之前版本的插件。因为之前是免费的 就还是可以用的。

还有另外的一个办法就是，应为本来主题 本来其实就是一个配置而已，就是配置了哪个地方是什么颜色，所以其实我们只要拿到这个配置就可以了，这个 Jetbrains 官方有一个开源项目刚好可以处理这个问题。

### **colorSchemeTool**

colorSchemeTool 是 Jetbrains 官方出的主题转换工具

https://github.com/JetBrains/colorSchemeTool

该工具可以帮助您转换 TextMate 和 VS Code 中使用的配色方案，并使它们与基于 IntelliJ 的 IDE 兼容，例如 IntelliJ IDEA、WebStorm 和 PyCharm。

**使用步骤**

*   Clone 项目代码

*   在 VsCode 中下载你想要转换的主题的 JSON 文件

*   将转换的 JSON 文件移动到项目对应的 vscThemes 文件夹下

*   运行 convert.sh 脚本

*   在 intellijThemes 文件夹下看，可以找到你导出对应 VsCode 主题的 `.icls` 文件

 **🌈** **VsCode 导出自己当前正在使用主题的文件**

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1505d60e2ecd4f9cb19aa42a9e1e4456~tplv-k3u1fbpfcp-zoom-1.image)

command + shift + p 输入 Generate Color Theme From Current Settings

就可以将自己当前正在使用的主题文件转换为 JSON 文件

#### 将配置文件导入到 Idea 中

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8b797da15f8a42e797abe5a2434ef328~tplv-k3u1fbpfcp-zoom-1.image)

重启生效
# 如何创建自定义maven archetype？

[TOC]

## 1. 什么是archetype

* 一般在创建web项目时都会使用maven的archetype模板，但是往往不尽如人意，创建出来的项目结构并不符合我们的要求
* 如：webapp总有一些乱七八糟的东西，springboot initalizr的版本一直更新
* 这里可以定义自己的archetype

![在这里插入图片描述](https://img-blog.csdnimg.cn/7a34681b422745a8987346b6a349bd3a.png)

## 2. 创建项目模板

* 创建一个maven项目，按照自己的习惯改造项目，可以把平时常用的依赖都引入
* **注意**：项目名，即`artifactId`不要是`webapp`这样的，要有属于自己的辨识度、
* 项目的`groupId`要和项目中的包名一致，这样可以在以后用模板创建出的项目中包名会自动同步

## 3. 创建archetype目录结构

* 进入到项目的根目录，
* **注意**：如果是用idea、vscode、eclips创建的项目，把目录中的其它与软件有关的文件删除，只保留`pom.xml`、`src`
* 打开`cmd`，执行
	* `mvn clean`
	* `mvn archetype:create-from-project`
	* 这里如果报了warning，把maven的setting.xml自制一份到`C:\Users\{你的用户名}\.m2`目录下

## 4. 安装archetype

* 执行以上命令后，生成的archetype项目在`target\generated-sources\archetype`目录下
* 目录结构如下，执行`mvn clean install`

![在这里插入图片描述](https://img-blog.csdnimg.cn/d2ee715cabd14cb6a545040b97166c92.png)

* `archetype-resources` 目录描述未来你希望生成的项目的模板
* `META-INF/maven/archetype-metadata.xml`文件描述模板中文件的相关信息，如应该包含哪些文件，占位符如何替换等
* 更加详细可以看：<https://blog.csdn.net/gao_zhennan/article/details/116986244>
* 如果最后生成的项目不如意，往往是要修改`archetype-metadata.xml`文件的

## 5. archetype查看

* 这时在你【本地仓库】的根目录看到`archetype-catalog.xml`
* 这个文件中有你刚刚生成的archetype的gav坐标，`artifactId`会自动加上`-archetype`

## 6. 安装到集成开发环境中

* 如果你是vscode，创建项目时，选择archetype创建，就已经可以用了
* 如果是idea，
	* 创建maven项目
	* 勾选`Create from archetype`，点击`Add Archetype`
	* 输入在`archetype-catalog.xml`文件中的gav坐标

![在这里插入图片描述](https://img-blog.csdnimg.cn/20586ecc96364770ad9648c444bff6cf.png)

### 6.1 删除idea的archetype

* 默认在`C:\Users\{你的用户名}\AppData\Local\JetBrains\IntelliJIdea{版本号}\Maven\Indices\UserArchetypes.xml`文件中
* 删除对应即可

```xml
<archetypes>
  <archetype groupId="" artifactId="" version="" />
</archetypes>
```

## 7. 分享

* 分享出自己的三个archetype`webapp`、`ssm`、`SpringBoot`
	* ssm项目有启动测试，记得更改数据库密码
* <https://huanyv.lanzouf.com/b0baw84ij>
	* 密码:hcw5
* 注意这三个中有两个是改过`archetype-resources`目录结构的，`META-INF/maven/archetype-metadata.xml`文件也有修改，所以不要执行`mvn archetype:create-from-project`
* 直接到项目的`target\generated-sources\archetype`目录下，cmd执行`mvn clean install`即可
* 然后去到仓库查看`archetype-catalog.xml`文件，就可以看到以下的坐标

![在这里插入图片描述](https://img-blog.csdnimg.cn/28d9aea6cc3a4d0193dc443315e1b540.png)


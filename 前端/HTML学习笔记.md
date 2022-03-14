# HTML 

## 目录

[TOC]

## HTML概述

超文本标记语言（英语：HyperText Markup Language，简称：HTML）是一种用于创建网页的标准标记语言。  
您可以使用 HTML 来建立自己的 WEB 站点，HTML 运行在浏览器上，由浏览器来解析。

```html
<!DOCTYPE html>
<!--这是注释-->
<!--根-->
<html>
	<meta charset="UTF-8">
	<!--头-->
    <head>
	    <!--标题-->
        <title>Hello World!</title>
        <!--标签图标-->
        <link rel="icon" href="图标地址" type="image/x-icon">
        
    </head>
    <!--网页主体-->
    <body>
        Hello World!
    </body>
</html>

```

* `<!DOCTYPE html>` 声明为 HTML5 文档
* `<html>` 元素是 HTML 页面的根元素
* `<head>` 元素包含了文档的元（meta）数据，如 `<meta charset="utf-8">` 定义网页编码格式为 utf-8。
* `<title>` 元素描述了文档的标题
* `<body>` 元素包含了可见的页面内容
* `<h1>` 元素定义一个大标题
* 注：在浏览器的页面上使用键盘上的 F12按键开启调试模式，就可以看到组成标签。

## 基本标签

* 段落标记`<p>一个段落</p>`
* 标题`<h1>一级标题</h1>`
* 换行`<br>`、`<br />`
* 分割线`<hr>`
* 格式保留`<pre></pre>`
* 字体
    * `<b>粗体</b>`
    * `<i>斜体</i>`
    * `<ins>下划线</ins>`
    * `<del>删除线</del>`
    * `<sup>上标</sup>`
    * `<sub>下标</sub>`
* font字体标签`<font color="red" size="10">font字体标签</font>`

## 实体符号

* 空格`&nbsp;`
* 小于号`&lt;`
* 大于号`&gt;`

## 表格

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>表格</title>
  </head>

  <body>
    <table border="1px" height="200px" width="50%" align="center">
      <thead>
        <tr>
          <th>1</th>
          <th colspan="2" align="center">23</th>
          <!--<td>3</td>-->
        </tr>
      </thead>

      <tbody>
        <tr>
          <td>1</td>
          <td>2</td>
          <td rowspan="2" align="center">33</td>
        </tr>
        <tr>
            <td>1</td>
            <td>2</td>
            <!--<td>3</td>-->
          </tr>
      </tbody>

      <tfood>
        <tr>
          <td>1</td>
          <td>2</td>
          <td>3</td>
        </tr>
      </tfood>
    </table>
  </body>
</html>
```

* `<table></table>`表示表格
* `<tr></tr>`表示行
* `<td></td>`表示列
* `<th></th>`这个标签会自动单元格居中对齐，字体加粗
* `<thead></thead><tbody></tbody><tfoot></tfoot>`一个table可以被thead tbody tfoot分隔为三部分,这个语法主要是为了后期javascript提供方便。
* 属性
    * `border="1px"`边框宽度1像素
    * `height="200px"`单元格高度
    * `width="50%"`单元格宽度
    * `align="center"`对齐方式
    * `colspan="2"`列合并
    * `rowspan="2"`行合并

## 背景

* `<body></body>`上加属性
* `bgcolor="red"`指定背景颜色
* `background="./pic/1.jpg"`指定背景图片

## 图片

* 语法`<img src="./pic/1.jpg" />`
* 属性
* `src="./pic/1.jpg"`指定图片路径
* `width="800px"`指定高度和宽度中的一项，另一项会等比缩放
* `title="割绳子"`悬停提示文字
* `alt="加载失败"`加载失败提示文字

## 链接

* `<a href="http://www.baidu.com">百度</a>`
* `<a href="./pic/1.jpg">百度</a>`可以链接到本地的文件
* `<a href="http://www.baidu.com"><img src="https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png" title="百度一下，你就知道!"></a>`可以做图片链接嵌套
* 属性
* `target=""` 
    * `_blank`：新窗口
    * `_self`：当前窗口
    * `_parent`：当前窗口的父窗口
    * `_top`：当前窗口的顶级窗口

## 列表

* `type=""`更改列表标号

### 无序列表

```html
<ul>
    <li>1
        <ul>
            <li>1</li>
            <li>2</li>
            <li>3</li>
        </ul>
    </li>
    <li>2</li>
    <li>3</li>
</ul>
```

### 有序列表

```html
<ol>
  <li>111
    <ol>
      <li>111</li>
      <li>222</li>
      <li>333</li>
    </ol>
  </li>
  <li>222</li>
  <li>333</li>
</ol>
```

## 表单

```html
<form action="地址">
    <input type=“”类型 name="" value=""></input>
</form>
```

* action一般是服务器的地址路径
* 表单提交后会以name=value键值对儿的形式提交
    * `http://localhost/8080/src/php?name=value&name=value&name=value`
* value即为文本框的输入值，一般在写程序时不会理会
* `<input type=? readonly/disable maxlength>`
    * `text`文本框
    * `password`密码框
    * `radio`单选框
        * `name`相同的情况表示是一个组
        * `checked`默认选
    * `checkbox`复选框
        * `name`相同值同组
        * `checked`默认选
    * `submit`提交按钮
        * 如果submit有name的话，submit的name=value也会提交。
    * `reset`重置表单的内容
    * `file`选择文件
        * `multiple`多文件提交
        * `required`不可为空
    * `hidden`隐藏域
* `<select name="" size="" multiple ><option value="" selected ></option></select>`下拉列表
    * `selected`默认选中
    * `size`显示行数
    * `multiple`多选
    * `readonly`只读，提交
    * `disable`只读，不提交
    * `maxlength`最大输入数
* `<textarea rows="" cols="" name=""></textarea>`

### HTML5表单新特性

* 指路：
    * <https://www.cnblogs.com/libo-web/archive/2022/01/10/15784654.html>
    * <https://blog.csdn.net/tomato__/article/details/50674539>
* 属性
    * `maxlength`表单最多输入字符数
        * `maxlength = "10"`
    * `autofocus`自动获取焦点，如果有多个input有这个属性，获取第一个
    * `required`文本框为必填内容
    * `pattern="^[a-zA-Z0-9_]{5,15}$"`输入文本必须和正则匹配
        * `titie`属性可以增加输入错误提示
* 表单类型
    * `type = "number"`数字输入框
        * `step`步长（默认是1）
    * `type = "email"`邮箱输入框
    * `type = "url"`网址输入框
    * `type = "search"`可清除输入框，右边有一个叉号
    * `type="time"`时间选择器（时-分）
        * 可以有非空required属性 
        * 提交数据格式：`time=15%3A31`
    * `type="week"`周选择器
        * 提交数据格式：`week=2022-W03`
    * `type="month"`月选择器
        * 提交数据格式：`month=2022-07` 
    * `type = "date"`年月日选择器
        * 提交数据格式：`date=2022-02-17`
    * `type = "datetime"`unix时间输入框
    * `type = "datetime-local"`年月日时分选择器
        * 提交数据格式：`datetimelocal=2022-02-01T18%3A31`
    * `type = "color"`颜色选择器
    * `type = "range"`进度条选择器
        * min：最小值（默认为0）
        * max：最大值（默认为100）
        * step：步数（默认为1）
        * value：当前步数（默认为50）
    * `type="image"`图像表单按钮，点击图像按钮将提交表单，在提交的数据中会包含点击位置的坐标信息，因此可以让图像中的不同区域代表不同的操作，然后根据用户在图像上的点击位置做出相应的反应。
        * `alt`：提供元素的说明文字，对需要借助残障辅助技术的用户很有用；
         * `formaction`：同button元素；
         * `formenctype`：同button元素；
         * `formmethod`：同button元素；
         * `formtarget`：同button元素；
         * `formnovalidate`：同button元素；
         * `height`：以像素为单位设置图像的高度；
         * `src`：指定要显示的图像的URL；
         * `width`：以像素为单位设置图像的宽度；

## id属性

1. 在HTML中**任何一个节点**上，都有id属性
2. 在同一个网页中，id属性是**不能重复**的。
3. id代表了这个节点，id是这个节点的身份证号。
4. 后期学习了javascript之后，我们要通过javascript对HTML的节点进行增删改，对节点增删改的时候，需要先获取到该节点对象，id属性可以让我们方便的获取到该节点对象。

## div和span

1. div和span都是图层。（div和span都是盒子
2. 什么是图层，有什么用？
    * 每一个图层在网页当中都是一个独立的盒子。
    * 图层的最主要作用就是：网页布局
3. table表格不是可以布局吗？
    * table表格布局有什么缺点：不灵活
    * div和span布局更加灵活。
4. 每一个div和span左上角的顶点，都有x和轴的坐标，通过这个坐标可以定位div在网页当中的位置后面讲css的时候，我们可以通过css样式进行定位
5. DIV会独占一行，span不会
5. span的大小会根据内容的多少变化而变化




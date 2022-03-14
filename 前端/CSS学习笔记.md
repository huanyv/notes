# CSS

## 目录

[TOC]

## 使用CSS的三种方式


1. 内联定义
2. 定义内部样式块对象
    1. 标签选择器
    2. id选择器
    3. class选择器
3. 链入外部样式表文件
    1. 标签选择器
    2. id选择器
    3. class选择器

```html
1. 内联定义语法格式
<标签 style="样式名 : 样式值; 
			样式名 : 样式值; 
			样式名 : 样式值;"></标签>
任何一个HTML标签都可以指定style属性。

2. 定义内部样式块对象
<style type="text/css"> /*固定写法*/
	/*
		这是CSS的注释！！！！！！
		在CSS的地盘中，和java c的注释相同！！！！
	*/
   /* 
   这里应该怎么写样式，语法是什么？
		选择器 {
			样式名 : 样式值;
			样式名 : 样式值;
			样式名 : 样式值;
			样式名 : 样式值;
			样式名 : 样式值;
			....
		}
		
		CSS中常见的选择器包括：最常用的三种选择器。
			标签选择器
			id选择器
			class选择器
   */
  /* 
  标签选择器，很简单：
	标签名 {}
  */
	div{ /* 作用于所有的div元素*/
		background-color: aqua;
		width: 100px;
		height: 100px;
		border-color: red;
		border-style: solid;
		border-width: 10px;
	}
	/* ID选择器
		#id{} 只作用于id这个元素
	 */
	#username {
		width: 400px;
		height: 30px;
		border-color: black;
		border-style: solid;
		border-width: 1px;
	}
	
	/* 类选择器
		.class {} 样式作用于当前网页中某一类的标签
	 */
	.student {
		font-size: 12px;
		color: #FF0000;
	}
 
</style>


3. 链入外部样式表文件
<head>
	<meta charset="utf-8">
	<title></title>
	
	<!-- 引入外部独立的CSS样式表文件 -->
	<!-- 在web前端开发中，这种方式是最常用的！ -->
	<link rel="stylesheet" type="text/css" href="css/1.css"/>
</head>
```

## 隐藏样式

* 隐藏`display: none;`
* 显示`display: block;`
* 列表符号隐藏`list-style-type: none;`
* 列表符号显示`list-style-type : upper-roman;`


## 文本装饰样式

* 标准文本（超链接去下划线）`text-decoration: none;`
* 文本下划线`text-decoration : underline;`
* 文本上划线`text-decoration : overline;`
* 文本删除线`text-decoration : line-through;`
* 闪烁文本`text-decoration : blink;`

### 文本div内自动换行

```
word-wrap: break-word; 
//或
word-break: break-all;
```

## 内补丁和外补丁

* 内补丁`padding-left: 20px;`
* 外补丁`margin-top : 10px;`

## 浮动效果

* `float: right;`

## 鼠标悬停效果

* hover在使用的时候，这个冒号两边都是不允许有空格的。

```
span:hover {
	// 悬停后样式
}
```

* 鼠标小手
* `cursor: pointer;`
* 尽量不要使用hand，有浏览器兼容问题。

## 定位

* `position` 属性规定元素的定位类型。
* `absolute`
    * 生成绝对定位的元素，相对于 static 定位以外的第一个父元素进行定位。元素的位置通过 "left", "top", "right" 以及 "bottom" 属性进行规定。
* `fixed`
    * 生成绝对定位的元素，相对于浏览器窗口进行定位。元素的位置通过 "left", "top", "right" 以及 "bottom" 属性进行规定。
* `relative`
    * 生成相对定位的元素，相对于其正常位置进行定位。因此，"left:20" 会向元素的 LEFT 位置添加 20 像素。
* `static`
    * 默认值。没有定位，元素出现在正常的流中（忽略 top, bottom, left, right 或者 z-index 声明）。inherit	规定应该从父元素继承 position 属性的值。

## 背景

### 页面主题变灰

```
body {
    filter: grayscale(1); //将图像转换为灰阶。
    filter: blur(px);   //高斯模糊
    filter: contrast(%); //对比度
    filter: saturate(%);//饱和度
}
```

### 网页背景手机自适应

图片会异形  

HTML  

```
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
```

CSS  

```
html,body{height: 100%;width: 100%;margin:0;padding:0;}
body{
	background:url(bg-login.png)no-repeat;
    width:100%;
    height:100%;
    background-size:100% 100%;
    position:absolute;
    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='bg-login.png',sizingMethod='scale');
}
```

### 网页背景图片铺满

```
body{
    background-image:url("./pic/1.png");
    background-repeat:no-repeat;
    background-size: 100%;
    background-attachment:fixed
}
```

## 禁用选中

防止选取 <div> 元素的文本：

```
div {
  -webkit-user-select: none; /* Safari */
  -ms-user-select: none; /* IE 10+ and Edge */
  user-select: none; /* Standard syntax */
}
```

user-select 属性规定是否能选取元素的文本。  
在 web 浏览器中，如果您在文本上双击，文本会被选取或高亮显示。此属性用于阻止这种行为。  
JavaScript 语法：`object.style.userSelect="none"`
```
style="user-select: none;"
```
* `user-select:`
    * auto——默认值，用户可以选中元素中的内容
    * none——用户不能选择元素中的任何内容
    * text——用户可以选择元素中的文本
    * element——文本可选，但仅限元素的边界内(只有IE和FF支持)
    * all——在编辑器内，如果双击/上下文点击发生在子元素上，该元素的最高级祖先元素将被选中。
    * -moz-none——firefox私有，元素和子元素的文本将不可选，但是input输入框中的文字除外（IE浏览器下是通过onselectstart="javascript:return false;"事件来实现该功能的）

## 居中

### 水平垂直居中(div)

```
width: ;
height: ;
position: absolute;
left:50%;
top:50%;
transform: translate(-50%, -50%);
```

### 元素水平居中

```
position: absolute;
left: 0;
right: 0;
margin: auto;
```

## 元素颜色模糊（半透明）

```
background-color: rgba(R,G,B,模糊度(0-1));
```

## 边框

### 边框圆角

```
border-radius: 5px;
```

### 边框显示与消失

```
// 显示
border: 1px solid black;
// 消失
border: 0px;
```




## 响应式布局

* 媒体查询
* xs(xsmall phones) 超小屏(自动)
* sm(small tablets) 小屏(750px)
* md(middle desktops) 中屏(970px)
* lg(larger desktops) 大屏(1170px)
* 参考：<https://blog.csdn.net/weixin_30329623/article/details/96156530?utm_source=app&app_version=4.21.0&code=app_1562916241&uLinkId=usr1mkqgl919blen>
* <https://blog.csdn.net/xun__xing/article/details/107498098?utm_source=app&app_version=4.21.0&code=app_1562916241&uLinkId=usr1mkqgl919blen>
```
<link rel="stylesheet" media="screen and (max-width: 375px)" href="index.css">




@media only screen and (max-width: 980px) {
    #searchform,#search{
        height: 40px;
        width:95%;
        position: absolute;
        top: 40%;
        left: 0;
        right: 0;
        margin: auto;
    }
}
```


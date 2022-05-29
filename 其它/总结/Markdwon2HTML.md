# Markdwon2HTML

## 1. showdown

* gitee：<https://gitee.com/mirrors/Showdown>

```
<div id="content"></div>

<script src="./node_modules/showdown/dist/showdown.min.js"></script>
<script>
    // import showdown from 'showdown';
    let converter = new showdown.Converter();
    //html变量是HTML代码字符串
    //text是Markdown语法的字符串
    let html = converter.makeHtml("# wqvb");
    console.log(html);

    document.getElementById("content").innerHTML = html;
</script>
```

## 2. strapdownjs

* 官网：<http://strapdownjs.com/>
* github：<https://github.com/arturadib/strapdown>
    * `strapdown/v/0.2/`目录下
* 在自己的项目中，strapdown的js文件、css文件、themes文件夹要在同级目录下
* 如果用ajax加载的，要把异步关掉
    * 必要要保证在`<xmp></xmp>`标签后加载strapdownjs文件

```
<xmp theme="simplex" style="display:none;">
# markdown文本
</xmp>

<script src="http://strapdownjs.com/v/0.2/strapdown.js"></script>
```

## 3. vue markd

* `npm install marked`

```
<template>
  <div v-html="markdownToHtml"></div>
</template>
<script>
import {parse} from "marked"
export default {
  data() {
    return {
      markdown: "# 标题",
    };
  },
  computed: {
    markdownToHtml() {
      return parse(this.markdown);
    },
  },
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
```


## markdowm编辑器

*  程序员鱼皮：<https://mp.weixin.qq.com/s/IbXy_2fAiMbICyIfRM6Ypw>

### md-editor-v3

* `npm install md-editor-v3`

```
<template>
  <md-editor v-model="text" style="height: 550px" />
</template>
 
<script>
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";

export default {
  components: {
    MdEditor,
  },
  data() {
    return {
      text: "",
    };
  },
};
</script>
```

### editor.md

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./editor.md/css/editormd.css">
</head>
<body>
    <!-- https://pandao.github.io/editor.md/examples/ -->
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="./editor.md/editormd.js"></script>
    <script type="text/javascript">
        $(function() {
            var testEditor = editormd("test-editormd", {
                width: "90%",
                height: 640,
                markdown : "",
                path : './editor.md/lib/',
                //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为 true
                //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为 true
                //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为 true
                //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为 0.1
                //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为 #fff
                imageUpload : true,
                imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL : "/upload",
                saveHTMLToTextarea: true
     
                /*
                 上传的后台只需要返回一个 JSON 数据，结构如下：
                 {
                    success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
                    message : "提示的信息，上传成功或上传失败及错误信息等。",
                    url     : "图片地址"        // 上传成功时才返回
                 }
                 */
            });
            $("#btn").on("click",function() {

                console.log(testEditor.getMarkdown());
                var data = $("#content").html();

                console.log(data);
            })
        });
        
     </script>
<div id="layout">
    <div id="test-editormd">
         <textarea style="display:none;" id="content"></textarea>
     </div>
</div>
    
<button type="button" id="btn">获取</button>

</body>
</html>
```
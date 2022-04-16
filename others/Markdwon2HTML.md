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
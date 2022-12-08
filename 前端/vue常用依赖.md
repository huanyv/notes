# vue 常用依赖

[TOC]

## 0. 组件库收集

* 20 个顶级的 Vue 组件库：<https://mp.weixin.qq.com/s/m6BtLxGv4aMxuPGds66z0w>
* 又20个：<https://mp.weixin.qq.com/s/41WwODER5zjLGYLDnGc_vg>

## 1. element-plus

* `npm install element-plus --save`
* `import ElementPlus from 'element-plus'`
* `import 'element-plus/dist/index.css'`

### 1.1 icon

* `npm install @element-plus/icons-vue`
* `import { Timer, Promotion } from "@element-plus/icons-vue";`

## 2. routers

* `npm install vue-router@4`
* `import { createRouter, createWebHistory  } from 'vue-router'`

## 3. axios

* ` npm install axios`

```
<script>
import axios from 'axios';

export default {
  methods: {
    get() {
      axios
        .get("http://wthrcdn.etouch.cn/weather_mini?city=%E6%B2%A7%E5%B7%9E")
        .then((response) => {
          console.log(response.data)
        });
    },
  },
};
</script>
```

## 4. marked

* md翻译器（纯文本，无样式）
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
```

## 5. md-editor-v3

* `npm install md-editor-v3`
* markdown编辑器

```
<template>
  <md-editor v-model="text" />
</template>
 
<script>
    import { defineComponent } from 'vue';
    import MdEditor from 'md-editor-v3';
    import 'md-editor-v3/lib/style.css';
    
    export default defineComponent({
        components: { 
            MdEditor
        },
        data() {
            return { 
                text: ''
            };
        }
    });
</script>
```

## 6. v-md-editor

* markdown解渲染（样式好看，推荐）
* 引入依赖：`npm i @kangc/v-md-editor@next -S`
* 文档：<http://ckang1229.gitee.io/vue-markdown-editor/zh/>
* github: <https://github.com/code-farmer-i/vue-markdown-editor>
* main.js

```js
import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/index.js'

// element ui
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// markdown显示
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
// vue press样式
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
// Prism
import Prism from 'prismjs';
// 代码高亮
import 'prismjs/components/prism-json';
// 选择使用主题
VMdPreview.use(vuepressTheme, {
  Prism,
});

// 显示代码行数
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
VMdPreview.use(createLineNumbertPlugin());

// 快速复制代码
import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
VMdPreview.use(createCopyCodePlugin());

let app = createApp(App)
app.use(ElementPlus)
app.use(router)
// 使用
app.use(VMdPreview);

app.mount('#app')
```

```vue
<template>
<div id="content">
  <v-md-preview :text="markdownToHtml"></v-md-preview>
</div>
</template>
<script>
import { getArticleWeb, addTraffic } from '@/request/api'

export default {
  data() {
    return {
      markdownToHtml: ""
    };
  },
  methods: {
    get() {
      // 后端请求
      getArticleWeb({id: this.aid}).then(res => {
        let data = res.data
        if (data.code == 200) {
          this.markdownToHtml = data.data.content
          data.data.traffic = data.data.traffic + 1;
          addTraffic(data.data)
        }
      })
    },
  },
  mounted() {
    this.get()
  },
  props: {
    aid: String
  }
};
</script>
<style scoped>
#content {
  width: 60%;
  margin: auto;
}
</style>
```

## 7. share.js

* 原项目：<https://github.com/overtrue/share.js>
* Vue版：<https://github.com/sinchang/vue-social-share>
	* 问题：图标加载不出来
	* 解决方案：`<link href='https://cdn.bootcss.com/social-share.js/1.0.16/css/share.min.css' rel="stylesheet"> `加到index.html的head 



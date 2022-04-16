# vue 常用依赖

[TOC]

## 0. 组件库收集

* 20 个顶级的 Vue 组件库：<https://mp.weixin.qq.com/s/m6BtLxGv4aMxuPGds66z0w>

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

* md翻译器
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
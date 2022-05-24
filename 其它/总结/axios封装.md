# Vue中axios的封装

![在这里插入图片描述](https://img-blog.csdnimg.cn/ab1675aa2a724668b2e0ffb9377f71a8.png)
* request.js请求封装、

```js
import axios from 'axios'

import { getToken } from '@/utils'

const http = axios.create({
    baseURL: 'http://localhost:8080',
    // timeout: 4000
})

// 配置请求拦截器
http.interceptors.request.use(
    config => {
        config.headers = {
            // 每次请求前带上Token
            token: getToken()
        }
        return config;
    },
    err => Promise.reject(err)
);

// 配置响应拦截器
http.interceptors.response.use(res=>{
    console.log("响应拦截");
    return res;
}, err=>{
    return Promise.reject(err)
});

export const get = (url, param) => {
    return http.get(url, param)
}

export const post = (url, param) => {
    return http.post(url, param)
}

export const put = (url, param) => {
    return http.put(url, param)
}

export const del = (url, param) => {
    return http.del(url, param)
}
```

* api.js请求数据方法封装

```js
import { post,get,put,del } from './request'

// 登录
export const login = param => post("/admin/login",param)

// 校验登录状态
export const checkLoginState = param => get("/admin/checkLoginState",param)


export const listArticle = () => get("/admin/article")
```

* 使用

```js
// 后端调用，判定是否登录成功
 login({ username: this.username, password: this.password }).then(
   (response) => {
     let data = response.data;
     if (data.code == 200) {
       message.success(data.msg)
       // 保存token
       window.localStorage.setItem("token", data.data.token);
       window.localStorage.setItem("username", this.username)
       this.$router.push("/admin");
     } else {
       message.error(data.msg)
     }
   }
```
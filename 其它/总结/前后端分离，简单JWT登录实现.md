# 前后端分离，简单JWT登录实现

[toc]

## JWT登录流程
![](https://img-blog.csdnimg.cn/img_convert/900b3e81f832b2f08c2e8aabb540536a.png)

* 前端vue+axios+router
* 后端springboot+mybatisplus

## 1. 用户认证处理

```java
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ResponseResult adminLogin(Admin loginAdmin) {
    	// 从数据库查出用户信息
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername,loginAdmin.getUsername());
        Admin admin = adminMapper.selectOne(queryWrapper);
        // 用户不存在，直接认证失败
        if (admin == null) {
            return new ResponseResult(ResponseResult.FORBIDDEN,"登录失败");
        }
        // 用户名与密码校验
        if (admin.getUsername().equals(loginAdmin.getUsername())
                && admin.getPassword().equals(loginAdmin.getPassword())) {
            // 校验成功，生成jwt返回数据
            String jwt = JwtUtil.createJWT(String.valueOf(loginAdmin.getId()), 1000L * 60 * 60 * 24);
            Map<String,String> map = new HashMap<>();
            map.put("token",jwt);
            return new ResponseResult(ResponseResult.OK,"登录成功",map);
        }
        // 密码校验失败
        return new ResponseResult(ResponseResult.FORBIDDEN,"登录失败");
    }
}
```
## 2. 前端登录
```js
login({ username: this.username, password: this.password }).then(response => {
      let data = response.data;
      // 登录成功
      if (data.code == 200) {
        message.success(data.msg)
        // 保存token到localstorage
        window.localStorage.setItem("token", data.data.token)
       	// 用户信息保存
        window.localStorage.setItem("username", this.username)
        // 跳转到主页
        this.$router.push("/admin");
      } else {
        message.error(data.msg)
      }
    }
  );
```

## 3. 前端请求处理

* 前端每次发出请求时，请求头要携带之前**登录成功**保存的token
* 这里用axios拦截器示例

```js
const http = axios.create({
    baseURL: 'http://localhost:8080',
    // timeout: 4000
})

// 配置请求拦截器
http.interceptors.request.use(
    config => {
        config.headers = {
            // 每次请求前带上Token
            token: window.localStorage.getItem("token")
        }
        return config;
    },
    err => Promise.reject(err)
);

// 配置响应拦截器，主要做登录过期的处理，后端在解析token失效时返回401
http.interceptors.response.use(res => {
    // 登录过期，跳转到登录页面
    if (res.data.code == 401) {
        message.error(res.data.msg)
        router.push("/admin/login")
    }
    return res;
}, err => {
    return Promise.reject(err)
});
```

## 4. 后端请求处理
* 每次接收到请求，要校验token是否合法
* 这里用拦截器示例

```java

public class RequestCheckTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html; charset=utf-8");
		// OPTIONS为预检请求，直接放行
        if ("OPTIONS".equals(request.getMethod().toUpperCase())) {
            return true;
        }
		// 获取token
        String token = request.getHeader("token");
        // 如果没有token直接拦截，返回错误信息
        if (token == null) {
        	// 这里直接用的jackson
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(new ResponseResult(ResponseResult.UNAUTHORIZED, "登录失效"));
            response.getWriter().print(result);
            return false;
        }
        try {
            Claims claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
        	// token解析失败
            e.printStackTrace();
            // token不合法 拦截，返回错误信息
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(new ResponseResult(ResponseResult.UNAUTHORIZED, "登录失效"));
            response.getWriter().print(result);
            return false;
        }
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
```

## 5. 前端页面跳转处理

* 前端在跳转页面前，应当也要校验登录状态信息
* 这里用vue router的全局路由守卫示例

```js
// 全局路由导航守卫
router.beforeEach((to, from) => {
    // console.log(to); // 去哪儿
    // console.log(from); // 从哪儿来
    if (to.meta.requiresAuth == true) {
        let token = getToken()
        if (token == null || token == "") {
            message.warning("登录失效")
            router.push("/admin/login")
            return false;
        }
        // 校验Token合法性
        checkLoginState().then(response => {
            let data = response.date
            if (data.code == 200) {
                return true
            } else {
                message.warning(data.msg)
                router.push("/admin/login")
                return false;
            }
        })

    }
    return true;
});
```

## 6. 退出登录

* 可以看这篇文章：<https://blog.csdn.net/a1098766713/article/details/102914354>
* 这里使用最简单的方法

```js
logout() {
   // 无为而治
   window.localStorage.removeItem("token")
   window.localStorage.removeItem("username")
   this.$router.push("/admin/login");
 },
```

## 7. 本文关联代码
### 7.1 前端
* request.js

```js
import axios from 'axios'

import { getToken, message } from '@/utils'

import router from '@/router'

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
http.interceptors.response.use(res => {
    // console.log("响应拦截");
    // 登录过期
    if (res.data.code == 401) {
        message.error(res.data.msg)
        router.push("/admin/login")
    }
    return res;
}, err => {
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
    return http.delete(url, param)
}
```

* api.js

```js
import axios from 'axios'
import { post,get,put,del } from './request'

// 后台管理登录
export const login = param => post("/admin/login",param)

// 校验登录状态
export const checkLoginState = () => get("/admin/checkLoginState")
```

* utils.js

```js
import { ElMessage } from "element-plus";

export const message = {
    error: msg => {
        ElMessage({
            showClose: true,
            message: msg,
            type: "error",
        });
    },
    success: msg => {
        ElMessage({
            showClose: true,
            message: msg,
            type: "success",
        });
    },
    warning: msg => {
        ElMessage({
            showClose: true,
            message: msg,
            type: "warning",
        });
    }
}
```

### 7.2 后端

* ResponseResult.java

```java
public class ResponseResult {

    /**
     * 表明该请求被成功地完成，所请求的资源发送到客户端
     */
    public static final Integer OK = 200;
    /**
     * 请求要求身份验证，常见对于需要登录而用户未登录的情况。
     */
    public static final Integer UNAUTHORIZED = 401;
    /**
     * 服务器拒绝请求，常见于机密信息或复制其它登录用户链接访问服务器的情况。
     */
    public static final Integer FORBIDDEN = 403;
    /**
     * 服务器无法取得所请求的网页，请求资源不存在。
     */
    public static final Integer NOT_FOUND = 404;
    /**
     * 服务器内部错误。
     */
    public static final Integer SERVER_ERROR = 500;

    private Integer code;
    private String msg = "";
    private Object data = new int[0];

    public ResponseResult() {
    }

    public ResponseResult(Integer code) {
        this.code = code;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
```

* JwtUtil.java
* 依赖于jjwt

```java
/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 *1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "shfciksadasfa21435ggdlhvjanv";

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }


    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("huanyv")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }



    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
```

```xml
 <dependency>
     <groupId>io.jsonwebtoken</groupId>
     <artifactId>jjwt</artifactId>
     <version>0.9.0</version>
 </dependency>
```
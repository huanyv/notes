# HTTP协议

[TOC]

## 1\. HTTP 超文本传输协议

* HTTP 是基于客户端/服务端（C/S）的架构模型，通过一个可靠的链接来交换信息，是一个无状态的请求/响应协议。
* 一个HTTP "客户端"是一个应用程序（Web浏览器或其他任何客户端），通过连接到服务器达到向服务器发送一个或多个HTTP的请求的目的。
* 一个HTTP "服务器"同样也是一个应用程序（通常是一个Web服务，如Apache Web服务器或IIS服务器等），通过接收客户端的请求并向客户端发送HTTP响应数据。
* HTTP 使用统一资源标识符（Uniform Resource Identifiers, URI）来传输数据和建立连接。
* HTTP 请求到服务器的请求消息包括以下格式：请求行（request line）、请求头部（header）、空行和请求数据四个部分组成。
* HTTP1.0 定义了三种请求方法： GET, POST 和 HEAD方法。
* HTTP1.1 新增了五种请求方法：OPTIONS, PUT, DELETE, TRACE 和 CONNECT 方法。

## 2\. HTTP 请求方式

1. `GET` 请求指定的页面信息，并返回实体主体。
2. `HEAD` 类似于get请求，只不过返回的响应中没有具体的内容，用于获取报头
3. `POST` 向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中。POST请求可能会导致新的资源的建立和/或已有资源的修改。
4. `PUT` 从客户端向服务器传送的数据取代指定的文档的内容。
5. `DELETE` 请求服务器删除指定的页面。
6. `CONNECT` HTTP/1.1协议中预留给能够将连接改为管道方式的代理服务器。
7. `OPTIONS` 允许客户端查看服务器的性能。
8. `TRACE` 回显服务器收到的请求，主要用于测试或诊断。

## 3\. 常见的HTTP请求与响应头

### 3.1 常见的HTTP请求头

| 协议头 | 说明 | 示例 | 状态 |
| --- | --- | --- | --- |
| Accept | 可接受的响应内容类型（`Content-Types`）。 | `Accept: text/plain` | 固定 |
| Accept-Charset | 可接受的字符集 | `Accept-Charset: utf-8` | 固定 |
| Accept-Encoding | 可接受的响应内容的编码方式。 | `Accept-Encoding: gzip, deflate` | 固定 |
| Accept-Language | 可接受的响应内容语言列表。 | `Accept-Language: en-US` | 固定 |
| Accept-Datetime | 可接受的按照时间来表示的响应内容版本 | Accept-Datetime: Sat, 26 Dec 2015 17:30:00 GMT | 临时 |
| Authorization | 用于表示HTTP协议中需要认证资源的认证信息 | Authorization: Basic OSdjJGRpbjpvcGVuIANlc2SdDE== | 固定 |
| Cache-Control | 用来指定当前的请求/回复中的，是否使用缓存机制。 | `Cache-Control: no-cache` | 固定 |
| Connection | 客户端（浏览器）想要优先使用的连接类型 | `Connection: keep-alive``Connection: Upgrade` | 固定 |
| Cookie | 由之前服务器通过`Set-Cookie`（见下文）设置的一个HTTP协议Cookie | `Cookie: $Version=1; Skin=new;` | 固定：标准 |
| Content-Length | 以8进制表示的请求体的长度 | `Content-Length: 348` | 固定 |
| Content-MD5 | 请求体的内容的二进制 MD5 散列值（数字签名），以 Base64 编码的结果 | Content-MD5: oD8dH2sgSW50ZWdyaIEd9D== | 废弃 |
| Content-Type | 请求体的MIME类型 （用于POST和PUT请求中） | Content-Type: application/x-www-form-urlencoded | 固定 |
| Date | 发送该消息的日期和时间（以[RFC 7231](http://tools.ietf.org/html/rfc7231#section-7.1.1.1)中定义的"HTTP日期"格式来发送） | Date: Dec, 26 Dec 2015 17:30:00 GMT | 固定 |
| Expect | 表示客户端要求服务器做出特定的行为 | `Expect: 100-continue` | 固定 |
| From | 发起此请求的用户的邮件地址 | `From: user@itbilu.com` | 固定 |
| Host | 表示服务器的域名以及服务器所监听的端口号。如果所请求的端口是对应的服务的标准端口（80），则端口号可以省略。 | `Host: www.itbilu.com:80``Host: www.itbilu.com` | 固定 |
| If-Match | 仅当客户端提供的实体与服务器上对应的实体相匹配时，才进行对应的操作。主要用于像 PUT 这样的方法中，仅当从用户上次更新某个资源后，该资源未被修改的情况下，才更新该资源。 | If-Match: "9jd00cdj34pss9ejqiw39d82f20d0ikd" | 固定 |
| If-Modified-Since | 允许在对应的资源未被修改的情况下返回304未修改 | If-Modified-Since: Dec, 26 Dec 2015 17:30:00 GMT | 固定 |
| If-None-Match | 允许在对应的内容未被修改的情况下返回304未修改（ 304 Not Modified ），参考 超文本传输协议 的实体标记 | If-None-Match: "9jd00cdj34pss9ejqiw39d82f20d0ikd" | 固定 |
| If-Range | 如果该实体未被修改过，则向返回所缺少的那一个或多个部分。否则，返回整个新的实体 | If-Range: "9jd00cdj34pss9ejqiw39d82f20d0ikd" | 固定 |
| If-Unmodified-Since | 仅当该实体自某个特定时间以来未被修改的情况下，才发送回应。 | If-Unmodified-Since: Dec, 26 Dec 2015 17:30:00 GMT | 固定 |
| Max-Forwards | 限制该消息可被代理及网关转发的次数。 | `Max-Forwards: 10` | 固定 |
| Origin | 发起一个针对[跨域资源共享](http://itbilu.com/javascript/js/VkiXuUcC.html)的请求（该请求要求服务器在响应中加入一个`Access-Control-Allow-Origin`的消息头，表示访问控制所允许的来源）。 | `Origin: http://www.itbilu.com` | 固定: 标准 |
| Pragma | 与具体的实现相关，这些字段可能在请求/回应链中的任何时候产生。 | `Pragma: no-cache` | 固定 |
| Proxy-Authorization | 用于向代理进行认证的认证信息。 | Proxy-Authorization: Basic IOoDZRgDOi0vcGVuIHNlNidJi2== | 固定 |
| Range | 表示请求某个实体的一部分，字节偏移以0开始。 | `Range: bytes=500-999` | 固定 |
| Referer | 表示浏览器所访问的前一个页面，可以认为是之前访问页面的链接将浏览器带到了当前页面。`Referer`其实是`Referrer`这个单词，但RFC制作标准时给拼错了，后来也就将错就错使用`Referer`了。 | Referer: http://itbilu.com/nodejs | 固定 |
| TE | 浏览器预期接受的传输时的编码方式：可使用回应协议头`Transfer-Encoding`中的值（还可以使用"trailers"表示数据传输时的分块方式）用来表示浏览器希望在最后一个大小为0的块之后还接收到一些额外的字段。 | `TE: trailers,deflate` | 固定 |
| User-Agent | 浏览器的身份标识字符串 | `User-Agent: Mozilla/……` | 固定 |
| Upgrade | 要求服务器升级到一个高版本协议。 | Upgrade: HTTP/2.0, SHTTP/1.3, IRC/6.9, RTA/x11 | 固定 |
| Via | 告诉服务器，这个请求是由哪些代理发出的。 | Via: 1.0 fred, 1.1 itbilu.com.com (Apache/1.1) | 固定 |
| Warning | 一个一般性的警告，表示在实体内容体中可能存在错误。 | Warning: 199 Miscellaneous warning | 固定 |

### 3.2 常用的HTTP响应头

| 响应头 | 说明 | 示例 | 状态 |
| --- | --- | --- | --- |
| Access-Control-Allow-Origin | 指定哪些网站可以`跨域源资源共享` | `Access-Control-Allow-Origin: *` | 临时 |
| Accept-Patch | 指定服务器所支持的文档补丁格式 | Accept-Patch: text/example;charset=utf-8 | 固定 |
| Accept-Ranges | 服务器所支持的内容范围 | `Accept-Ranges: bytes` | 固定 |
| Age | 响应对象在代理缓存中存在的时间，以秒为单位 | `Age: 12` | 固定 |
| Allow | 对于特定资源的有效动作; | `Allow: GET, HEAD` | 固定 |
| Cache-Control | 通知从服务器到客户端内的所有缓存机制，表示它们是否可以缓存这个对象及缓存有效时间。其单位为秒 | `Cache-Control: max-age=3600` | 固定 |
| Connection | 针对该连接所预期的选项 | `Connection: close` | 固定 |
| Content-Disposition | 对已知MIME类型资源的描述，浏览器可以根据这个响应头决定是对返回资源的动作，如：将其下载或是打开。 | Content-Disposition: attachment; filename="fname.ext" | 固定 |
| Content-Encoding | 响应资源所使用的编码类型。 | `Content-Encoding: gzip` | 固定 |
| Content-Language | 响就内容所使用的语言 | `Content-Language: zh-cn` | 固定 |
| Content-Length | 响应消息体的长度，用8进制字节表示 | `Content-Length: 348` | 固定 |
| Content-Location | 所返回的数据的一个候选位置 | `Content-Location: /index.htm` | 固定 |
| Content-MD5 | 响应内容的二进制 MD5 散列值，以 Base64 方式编码 | Content-MD5: IDK0iSsgSW50ZWd0DiJUi== | 已淘汰 |
| Content-Range | 如果是响应部分消息，表示属于完整消息的哪个部分 | Content-Range: bytes 21010-47021/47022 | 固定 |
| Content-Type | 当前内容的`MIME`类型 | Content-Type: text/html; charset=utf-8 | 固定 |
| Date | 此条消息被发送时的日期和时间(以[RFC 7231](http://tools.ietf.org/html/rfc7231#section-7.1.1.1)中定义的"HTTP日期"格式来表示) | Date: Tue, 15 Nov 1994 08:12:31 GMT | 固定 |
| ETag | 对于某个资源的某个特定版本的一个标识符，通常是一个 消息散列 | ETag: "737060cd8c284d8af7ad3082f209582d" | 固定 |
| Expires | 指定一个日期/时间，超过该时间则认为此回应已经过期 | Expires: Thu, 01 Dec 1994 16:00:00 GMT | 固定: 标准 |
| Last-Modified | 所请求的对象的最后修改日期(按照 RFC 7231 中定义的“超文本传输协议日期”格式来表示) | Last-Modified: Dec, 26 Dec 2015 17:30:00 GMT | 固定 |
| Link | 用来表示与另一个资源之间的类型关系，此类型关系是在[RFC 5988](https://tools.ietf.org/html/rfc5988)中定义 | `Link: ` ; rel="alternate" | 固定 |
| Location | 用于在进行重定向，或在创建了某个新资源时使用。 | Location: http://www.itbilu.com/nodejs | 固定 |
| P3P | P3P策略相关设置 | P3P: CP="This is not a P3P policy! | 固定 |
| Pragma | 与具体的实现相关，这些响应头可能在请求/回应链中的不同时候产生不同的效果 | `Pragma: no-cache` | 固定 |
| Proxy-Authenticate | 要求在访问代理时提供身份认证信息。 | `Proxy-Authenticate: Basic` | 固定 |
| Public-Key-Pins | 用于防止中间攻击，声明网站认证中传输层安全协议的证书散列值 | Public-Key-Pins: max-age=2592000; pin-sha256="……"; | 固定 |
| Refresh | 用于重定向，或者当一个新的资源被创建时。默认会在5秒后刷新重定向。 | Refresh: 5; url=http://itbilu.com |  |
| Retry-After | 如果某个实体临时不可用，那么此协议头用于告知客户端稍后重试。其值可以是一个特定的时间段(以秒为单位)或一个超文本传输协议日期。 | 示例1:Retry-After: 120示例2: Retry-After: Dec, 26 Dec 2015 17:30:00 GMT | 固定 |
| Server | 服务器的名称 | `Server: nginx/1.6.3` | 固定 |
| Set-Cookie | 设置`HTTP cookie` | Set-Cookie: UserID=itbilu; Max-Age=3600; Version=1 | 固定: 标准 |
| Status | 通用网关接口的响应头字段，用来说明当前HTTP连接的响应状态。 | `Status: 200 OK` |  |
| Trailer | `Trailer`用户说明传输中分块编码的编码信息 | `Trailer: Max-Forwards` | 固定 |
| Transfer-Encoding | 用表示实体传输给用户的编码形式。包括：`chunked`、`compress`、 `deflate`、`gzip`、`identity`。 | Transfer-Encoding: chunked | 固定 |
| Upgrade | 要求客户端升级到另一个高版本协议。 | Upgrade: HTTP/2.0, SHTTP/1.3, IRC/6.9, RTA/x11 | 固定 |
| Vary | 告知下游的代理服务器，应当如何对以后的请求协议头进行匹配，以决定是否可使用已缓存的响应内容而不是重新从原服务器请求新的内容。 | `Vary: *` | 固定 |
| Via | 告知代理服务器的客户端，当前响应是通过什么途径发送的。 | Via: 1.0 fred, 1.1 itbilu.com (nginx/1.6.3) | 固定 |
| Warning | 一般性警告，告知在实体内容体中可能存在错误。 | Warning: 199 Miscellaneous warning | 固定 |
| WWW-Authenticate | 表示在请求获取这个实体时应当使用的认证模式。 | `WWW-Authenticate: Basic` | 固定 |

## 4\. HTTP返回状态码

* `1xx`：处理信息，服务器收到请求，需要请求者继续执行操作；
* `2xx`：请求成功，操作被成功接收并处理；
* `3xx`：重定向，需要进一步的操作以完成请求；
* `4xx`：客户端错误，请求包含语法错误或无法完成请求；
* `5xx`：服务器错误，服务器在处理请求的过程中发生了错误；

### 4.1 状态码返回

#### 4.1.1 100

1xx - 信息提示
　　这些状态代码表示临时的响应。客户端在收到常规响应之前，应准备接收一个或多个 1xx 响应。
100 - Continue 继续；
　　初始的请求已经接受，客户应当继续发送请求的其余部分。（HTTP 1.1新）
101 - Switching Protocols 切换协议；
　　服务器将遵从客户的请求转换到另外一种协议（HTTP 1.1新）

#### 4.1.2 200

```
2xx - 成功
　　这类状态代码表明服务器成功地接受了客户端请求。
200 - OK 正常；
　　对GET和POST请求的应答文档跟在后面。
201 - Created 已创建；
　　服务器已经创建了文档，Location头给出了它的URL。
202 - Accepted 接受；
　　已经接受请求，但处理尚未完成。
203 - Non-Authoritative Information 非权威的信息；
　　文档已经正常地返回，但一些应答头可能不正确，因为使用的是文档的拷贝，非权威性信息（HTTP 1.1新）。
204 - No Content 没有内容；
　　浏览器应该继续显示原来的文档。如果用户定期地刷新页面，而Servlet可以确定用户文档足够新，这个状态代码是很有用的。
205 - Reset Content 重置内容；
　　但浏览器应该重置它所显示的内容。用来强制浏览器清除表单输入内容（HTTP 1.1新）。
206 - Partial Content 部分内容；
　　客户发送了一个带有Range头的GET请求，服务器完成了它（HTTP 1.1新）。
207 - 多状态
　　紧跟消息体后面的是xml消息并且包含了多个单独的响应状态码，响应的数量取决于子请求的个数。
208 - 已经报告
　　一个DAV的绑定成员被前一个请求枚举，并且没有被再一次包括。
226 - IM Used
　　服务器已经满足了请求所要的资源，并且响应是一个或者多个实例操作应用于当前实例的结果
```

#### 4.1.3 300

```
3xx - 重定向
　　客户端浏览器必须采取更多操作来实现请求。
　　例如，浏览器可能不得不请求服务器上的不同的页面，或通过代理服务器重复该请求。
300 - Multiple Choices 多重选择；
　　客户请求的文档可以在多个位置找到，这些位置已经在返回的文档内列出。如果服务器要提出优先选择，则应该在Location应答头指明。
301 - Moved Permanently 永久移动；
　　客户请求的文档在其他地方，新的URL在Location头中给出，浏览器应该自动地访问新的URL。
302 - Found 发现；
　　但新的URL应该被视为临时性的替代，而不是永久性的。注意，在HTTP1.0中对应的状态信息是“Moved Temporatily”。出现该状态代码时，浏览器能够自动访问新的URL，因此它是一个很有用的状态代码。注意这个状态代码有时候可以和301替换使用。例如，如果浏览器错误地请求 http://host/~user （缺少了后面的斜杠），有的服务器返回301，有的则返回302。严格地说，我们只能假定只有当原来的请求是GET时浏览器才会自动重定向。请参见 307。
303 - See Other 查看其它；
　　类似于301/302，不同之处在于，如果原来的请求是POST，Location头指定的重定向目标文档应该通过GET提取（HTTP类似于301/302，不同之处在于，如果原来的请求是POST，Location头指定的重定向目标文档应该通过GET提取（HTTP 1.1新）。
304 - Not Modified 未修改；
　　客户端有缓冲的文档并发出了一个条件性的请求（一般是提供If-Modified-Since头表示客户只想比指定日期更新的文档）。服务器告诉客户，原来缓冲的文档还可以继续使用。
305 - Use Proxy 使用代理；
　　客户请求的文档应该通过Location头所指明的代理服务器提取（HTTP 1.1新）。
306 - 切换代理；
　　不再使用。原意是随后的请求应该使用指定的代理。
307 - Temporary Redirect 临时跳转；
　　许多浏览器会错误地响应302应答进行重定向，即使原来的请求是POST，即使它实际上只能在POST请求的应答是303时才能重定向。由于这个原因，HTTP许多浏览器会错误地响应302应答进行重定向，即使原来的请求是POST，即使它实际上只能在POST请求的应答是303时才能重定向。由于这个原因，HTTP 1.1新增了307，以便更加清除地区分几个状态代码：当出现303应答时，浏览器可以跟随重定向的GET和POST请求；如果是307应答，则浏览器只能跟随对GET请求的重定向。（HTTP 1.1新）
308 - 永久转移
　　这个请求和以后的请求都应该被另一个URI地址重新发送。307、308和302、301有相同的表现，但是不允许HTTP方法改变。例如，请求表单到一个永久转移的资源将会继续顺利地执行。
```

#### 4.1.4 400

```
4xx - 客户端错误
　　发生错误，客户端似乎有问题。
　　例如，客户端请求不存在的页面，客户端未提供有效的身份验证信息。
400 - Bad Request 错误请求；
　　请求出现语法错误。
401 - Unauthorized 未授权；
　　访问被拒绝，客户试图未经授权访问受密码保护的页面。应答中会包含一个WWW-Authenticate头，浏览器据此显示用户名字/密码对话框，然后在填写合适的Authorization头后再次发出请求。IIS 定义了许多不同的 401 错误，它们指明更为具体的错误原因。这些具体的错误代码在浏览器中显示，但不在 IIS 日志中显示：
401.1 - 登录失败。
401.2 - 服务器配置导致登录失败。
401.3 - 由于 ACL 对资源的限制而未获得授权。
401.4 - 筛选器授权失败。
401.5 - ISAPI/CGI 应用程序授权失败。
401.7 – 访问被 Web 服务器上的 URL 授权策略拒绝。这个错误代码为 IIS 6.0 所专用。
402 - 需要付款
　　为以后保留使用。原意是该状态码可被用于一些数字货币或者是微支付，但是目前还没有普及，所以这些代码不经常被使用。YouYube使用这个状态如果某个IP地址发出了过多的请求，并要求用户输入验证码。
403 - Forbidden 禁止访问；
　　资源不可用。服务器理解客户的请求，但拒绝处理它。通常由于服务器上文件或目录的权限设置导致。禁止访问：IIS资源不可用。服务器理解客户的请求，但拒绝处理它。通常由于服务器上文件或目录的权限设置导致。禁止访问：IIS 定义了许多不同的 403 错误，它们指明更为具体的错误原因：
403.1 - 执行访问被禁止。
403.2 - 读访问被禁止。
403.3 - 写访问被禁止。
403.4 - 要求 SSL。
403.5 - 要求 SSL 128。
403.6 - IP 地址被拒绝。
403.7 - 要求客户端证书。
403.8 - 站点访问被拒绝。
403.9 - 用户数过多。
403.10 - 配置无效。
403.11 - 密码更改。
403.12 - 拒绝访问映射表。
403.13 - 客户端证书被吊销。
403.14 - 拒绝目录列表。
403.15 - 超出客户端访问许可。
403.16 - 客户端证书不受信任或无效。
403.17 - 客户端证书已过期或尚未生效。
403.18 - 在当前的应用程序池中不能执行所请求的 URL。
　　这个错误代码为 IIS 6.0 所专用。
403.19 - 不能为这个应用程序池中的客户端执行 CGI。
　　这个错误代码为 IIS 6.0 所专用。
403.20 - Passport 登录失败。
　　这个错误代码为 IIS 6.0 所专用。
404 - Not Found 找不到；
　　无法找到指定位置的资源。这也是一个常用的应答。
404.0 -（无） – 没有找到文件或目录。
404.1 - 无法在所请求的端口上访问 Web 站点。
404.2 - Web 服务扩展锁定策略阻止本请求。
404.3 - MIME 映射策略阻止本请求。
405 - Method Not Allowed  方法不允许；
　　请求方法（GET、POST、HEAD、Delete、PUT、TRACE等）对指定的资源不适用，用来访问本页面的 HTTP 谓词不被允许（方法不被允许）（HTTP 1.1新）
406 - Not Acceptable 不可接受；
　　指定的资源已经找到，但它的MIME类型和客户在Accpet头中所指定的不兼容，客户端浏览器不接受所请求页面的 MIME 类型（HTTP 1.1新）。
407 - Proxy Authentication Required 需要代理认证；
　　要求进行代理身份验证，类似于401，表示客户必须先经过代理服务器的授权。（HTTP 1.1新）
408 - Request Timeout 请求超时；
　　在服务器许可的等待时间内，客户一直没有发出任何请求。客户可以在以后重复同一请求。（HTTP在服务器许可的等待时间内，客户一直没有发出任何请求。客户可以在以后重复同一请求。（HTTP 1.1新）
409 - Conflict 冲突；
　　通常和PUT请求有关。由于请求和资源的当前状态相冲突，因此请求不能成功。（HTTP 1.1新）
410 - Gone 遗失的；
　　所请求的文档已经不再可用，而且服务器不知道应该重定向到哪一个地址。它和404的不同在于，返回407表示文档永久地离开了指定的位置，而404表示由于未知的原因文档不可用。（HTTP所请求的文档已经不再可用，而且服务器不知道应该重定向到哪一个地址。它和404的不同在于，返回407表示文档永久地离开了指定的位置，而404表示由于未知的原因文档不可用。（HTTP 1.1新）
411 - Length Required 长度要求；
　　服务器不能处理请求，除非客户发送一个Content-Length头。（HTTP服务器不能处理请求，除非客户发送一个Content-Length头。（HTTP 1.1新）
412 - Precondition Failed 前置条件失败；
　　请求头中指定的一些前提条件失败（HTTP请求头中指定的一些前提条件失败（HTTP 1.1新）。
413 – Request Entity Too Large 响应实体太大；
　　目标文档的大小超过服务器当前愿意处理的大小。如果服务器认为自己能够稍后再处理该请求，则应该提供一个Retry-After头（HTTP 1.1新）。
414 - Request URI Too Long 请求URI太长;
　　被提供的URI对服务器的处理来说太长。经常出现在太多被编码的数据被作为查询字符串的GET请求的结果，因此需要被转换为POST请求。（HTTP 1.1新）。
415 – 不支持的媒体类型。
　　请求实体的媒体类型不被服务器或者资源支持。例如，客户端上传一个image/svg+xml的图片，但是服务器需要图片使用不同的格式
416 – Requested Range Not Satisfiable 请求范围不能满足；
　　服务器不能满足客户在请求中指定的Range头。（HTTP 1.1新）
417 – 执行失败。
　　服务器期望请求头字段的要求。
418 - 我是一个茶壶；
　　这个代码是在1998年作为传统的IETF April Fools‘ jokes被定义的在RFC2324，超文本咖啡罐控制协议，但是并没有被实际的HTTP服务器实现。RFC指定了这个代码应该是由茶罐返回给速溶咖啡。
419 - 认证超时；
　　并不是HTTP标注的一部分，419认证超时表示以前的有效证明已经失效了。同时也被用于401未认证的替代选择为了从其它被拒绝访问的已认证客户端中指定服务器的资源。
420 - 方法失效；
　　不是HTTP的标准，但是被Spring定义在HTTP状态类中当方法失时使用。这个状态码已经不推荐在Spring中使用。421 - 误导请求;
　　请求被直接定向到不能产生响应的服务器上（例如因为一个连接的复用）。
422 - 不可处理的实体（WebDAV）
　　请求符合要求但是不能接受错误由于语法错误。
423 - 锁定的
　　资源访问被锁定。
424 - 失败的依赖
　　请求由于上一个请求的失败而失败。
426 - 需要升级
　　客户端应该切换不同的协议例如TLS/1.0在指定的升级的头字段里。
428 - 需要前置条件
　　原始服务器需要有条件的请求。当客户端GET一个资源的状态的时候，同时又PUT回给服务器，与此同时第三方修改状态到服务器上的时候，为了避免丢失更新的问题发生将会导致冲突。
429 - 过多请求
　　用户已经发送了太多的请求在指定的时间里。用于限制速率。
431 - 请求头部字段太大
　　服务器由于一个单独的请求头部字段或者是全部的字段太大而不愿意处理请求。
440 - 登陆超时（微软）
　　一个微软的扩展，意味着你的会话已经超时。
444 - 无响应
　　被使用在Nginx的日志中表明服务器没有返回信息给客户端并且关闭了连接（在威慑恶意软件的时候比较有用）。
449 - 重试（微软）
　　一个微软的扩展。请求应该在执行适当的动作之后被重试。
450 - 被Windows家长控制阻塞（微软）
　　一个微软的扩展。这个错误是当Windows家长控制打开并且阻塞指定网页的访问的时候被指定。
451 - 由于法律原因而无效（因特网草稿）
　　被定义在因特网草稿“一个新的HTTP状态码用于法律限制的资源”。被用于当资源的访问由于法律原因被禁止的时候。例如检查制度或者是政府强制要求禁止访问。一个例子是1953年dystopian的小说Fahrenheit 451就是一个非法的资源。
451 - 重定向（微软）
　　被用在Exchange ActiveSync中如果一个更有效的服务器能够被使用或者是服务器不能访问用户的邮箱。
　　客户端会假定重新执行HTTP自动发现协议去寻找更适合的服务器。
494 - 请求头太大（Nginx）
　　Nginx内置代码和431类似，但是是被更早地引入在版本0.9.4（在2011年1月21日）。
495 - 证书错误（Nginx）
　　Nginx内置的代码，当使用SSL客户端证书的时候错误会出现为了在日志错误中区分它和4XX和一个错误页面的重定向。。
496 - 没有证书（Nginx）
　　Nginx内置的代码，当客户端不能提供证书在日志中分辨4XX和一个错误页面的重定向。
497 - HTTP到HTTPS（Nginx）
　　Nginx内置的代码，被用于原始的HTTP的请求发送给HTTPS端口去分辨4XX在日志中和一个错误页面的重定向。
498 - 令牌超时或失效（Esri）
　　由ArcGIS for Server返回。这个代码意味着令牌的超时或者是失效。
499 - 客户端关闭请求（Nginx）
　　被用在Nginx日志去表明一个连接已经被客户端关闭当服务器仍然正在处理它的请求，是的服务器无法返货状态码。
499 - 需要令牌（Esri）
　　由ArcGIS for Server返回。意味着需要一个令牌（如果没有令牌被提交）。
```

#### 4.1.5 500

```
5xx - 服务器错误
　　服务器由于遇到错误而不能完成该请求。

500 - Internal Server Error 服务器内部错误；
　　服务器遇到了意料不到的情况，不能完成客户的请求。
500.12 - 应用程序正忙于在 Web 服务器上重新启动。
500.13 - Web 服务器太忙。
500.15 - 不允许直接请求 Global.asa。
500.16 – UNC 授权凭据不正确。
　　这个错误代码为 IIS 6.0 所专用。
500.18 – URL 授权存储不能打开。
　　这个错误代码为 IIS 6.0 所专用。
500.100 - 内部 ASP 错误。
501 - Not Implemented 没有实现；
　　服务器不支持实现请求所需要的功能，页眉值指定了未实现的配置。例如，客户发出了一个服务器不支持的PUT请求。服务器不支持实现请求所需要的功能，页眉值指定了未实现的配置。例如，客户发出了一个服务器不支持的PUT请求。
502 - Bad Gateway 错误的网关；
　　服务器作为网关或者代理时，为了完成请求访问下一个服务器，但该服务器返回了非法的应答。 亦说Web 服务器用作网关或代理服务器时收到了无效响应。
502.1 - CGI 应用程序超时。
502.2 - CGI 应用程序出错。
503 - Service Unavailable 服务不可用；
　　服务器由于维护或者负载过重未能应答。
　　例如，Servlet可能在数据库连接池已满的情况下返回503。服务器返回503时可以提供一个服务器由于维护或者负载过重未能应答。例如，Servlet可能在数据库连接池已满的情况下返回503。服务器返回503时可以提供一个 Retry-After头。这个错误代码为 IIS 6.0 所专用。
504 - Gateway Timeout 网关超时；
　　由作为代理或网关的服务器使用，表示不能及时地从远程服务器获得应答。（HTTP由作为代理或网关的服务器使用，表示不能及时地从远程服务器获得应答。（HTTP 1.1新） 。
505 - HTTP Version Not Supported http版本不支持；
　　服务器不支持请求中所指明的HTTP版本。（HTTP 1.1新）。
506 - 变量也是导航
　　对于请求是透明的内容导航导致循环参照。
507 - 存储不足
　　服务器不能存储需要的内容去完成请求。
508 - 发现环路
　　服务器发现了一个无限的循环档处理请求的时候。
509 - 频带宽度超出限制(Apache的扩展)
　　这个状态码没有在任何RFCS中指定。使用方法是未知的。
511 - 需要网络授权
　　客户端需要授权去火的网络的访问权限。一般用于代理交互中被用来进行网络的访问控制。
520 - 未知错误
　　这个状态码也没有被指定在任何RFC中，并且只会被一些服务器返回，例如微软的Azure和CloudFlare服务器:”520错误本质上是一个捕获全部的响应当原始服务器返回一些未知的或者一些不能被忍受或者被解释的(协议违反或者空响应)”。
598 - 网络读取超时异常(未知)
　　这个状态码也没有在任何RFC中指定，但是被用在微软的HTTP代理中去标注一个网络读取超时在一个客户端之前的代理的后面。
599 - 网络连接超时异常(未知)
　　这态码也没有在任何RFC中指定，但是被用在微软的HTTP代理中去标注一个网络连接超时在一个客户端之前的代理的后面。
```
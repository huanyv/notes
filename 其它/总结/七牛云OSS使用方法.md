# 七牛云OSS使用方法

[toc]

* 七牛云OSS有10G的免费流量，但是默认的域名只通用一个月
* 这里是Java的演示

## 准备工作

* 进入七牛官网，实名认证后
* 存储空间 --> 新建存储空间

![在这里插入图片描述](https://img-blog.csdnimg.cn/30a556bf39ee4c47a01e5c6c94af3ca2.png)
* 新建空间，起个名字
* 区域尽量选择近一点的
* 访问控制一定要选择**公开**，这里的访问控制只是读取，不是改动
* 点击文件管理进入文件列表
* 会提供一个默认的域名（只能用一个月）

## 上传文件

* 官方文档：<https://developer.qiniu.com/kodo/1239/java>
* 引入依赖

```xml
 <dependency>
     <groupId>com.qiniu</groupId>
     <artifactId>qiniu-java-sdk</artifactId>
     <version>[7.7.0, 7.10.99]</version>
 </dependency>
```

### 查看示例

![在这里插入图片描述](https://img-blog.csdnimg.cn/683cce83acf24cd89547556bd8897c06.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/d8c29d2987104dedb706d152dc761455.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/bde5e645d1b64a31b3a4263ab8f3b9ac.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/6af83d9de89f4c50a7fc89b7f54c5d04.png)
### 获取密钥
![在这里插入图片描述](https://img-blog.csdnimg.cn/1b04df9bb188425080fafe858acc4b53.png)


### 编写代码
* 配置密钥
* 在springboot配置文件中加入以下
* 存储空间支持多级目录，设置存储文件名时用`/`分隔，比如：file/img/a.jpg

```yaml
oss:
  accessKey: 你的accessKey
  secretKey: 你的secretKey
  bucket: 你的存储空间名称
  # 访问域名
  urlPrefix: 你的默认域名
```

```java

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        String filename = img.getOriginalFilename();
        String url = uploadOss(img, "img/" + filename);
        return ResponseResult.okResult(url);
    }

    @Value("${oss.accessKey}")
    private String accessKey;
    @Value("${oss.secretKey}")
    private String secretKey;
    @Value("${oss.bucket}")
    private String bucket;
    @Value("${oss.urlPrefix}")
    private String urlPrefix;

    private String uploadOss(MultipartFile img, String uploadPath) {
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
		
		// 存储文件名
        String key = uploadPath;

        try {
        	// 获取文件的输入流
            InputStream inputStream = img.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
            	// 用文件流存储
                Response response = uploadManager.put(inputStream, key, upToken,null, null);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
               	// 返回图片的访问路径
                return urlPrefix + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                }
            }
        } catch (Exception ex) {
        }

        return "";
    }

}

```
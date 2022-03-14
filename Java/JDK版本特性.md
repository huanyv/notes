# JDK各个版本新特性

## 目录

[TOC]

## JDK8

## JDK9

### 1. 目录结构

&emsp;&emsp;包含jdk8及以前的版本,所有的目录结构含义如图  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcSc.i9eCHQS9cIhzXXNcNGiTI9OtEmRWDugNaw31kYxcxzd2OjHGvSvJ7VYs3*f55ZeANWNiU*UVGVq3tDKxIVk!/b&bo=XQMEAl0DBAIDGTw!&rf=viewer_4&t=5)

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcSc.i9eCHQS9cIhzXXNcNGhHxzrX3BIuqm8RSvRLVbG.uURmf5klj16wX1hOZpy*C9o590q7BizcVS4PX4nfqoY!/b&bo=IQNkASEDZAEDGTw!&rf=viewer_4&t=5)

&emsp;&emsp;JDK9之后, 目录结构发生变化如图:  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcZogx4x6GaC*ycbLMiCVjW2jY0WNKyV1HImRZivp0dt2SHINUowZXgnH878k4nmH.PCINFqEtnYZbc*rdsVMcn0!/b&bo=NgOGAjYDhgIDGTw!&rf=viewer_4&t=5)

&emsp;&emsp;这个新特性只要了解下就可以了，这个目录结构是方便为了接下来新特性做保证.  

### 2. JShell工具

&emsp;&emsp;怎么理解，怎么用呢？这个只是针对于Java9来说，相当于cmd工具，你可以和cmd一样，直接写方法等等，不过我认为只是适用于初学者做一些最简单的运算和写一些方法，在cmd中打开这个工具：  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcYMZWk64bUjka*Xj1Gkk6njJramybPIBvcivGIIdQxttxIROWTwUnTIwo8i5QdTbT050IViKJPk2GcEWnKO4hyg!/b&bo=qQFTAKkBUwADGTw!&rf=viewer_4&t=5)

&emsp;&emsp;进入工具后可以进行一些简单的Java操作  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcYMZWk64bUjka*Xj1Gkk6nhmL*hHyiw1AHrbkinjvpWH62YJFf3rgI5E6cOWn1xfrMQ4te3IPc9i8QrpeBrVTTE!/b&bo=JgIxASYCMQEDGTw!&rf=viewer_4&t=5)

&emsp;&emsp;等等，我认为只适用于初学者学习Java不用其他编辑工具就可以学习Java  

### 3. 模块化

&emsp;&emsp;一个大型的项目，比如淘宝商城等，都会包含多个模块，比如订单模块，前台模块，后台管理模块，广告位模块，会员模块.....等等，各个模块之间会相互调用，不过这种情况下会很少，只针对特殊情况，如果一个项目有30个模块系统进行开发，但是只要某个单独模块运行时，都会带动所有的模块，这样对于jvm来说在内存和性能上会很低，所以，java9提供了这一个特性，某一个模块运行的时候，jvm只会启动和它有依赖的模块，并不会加载所有的模块到内存中，这样性能大大的提高了。写法上如下：    

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcYMZWk64bUjka*Xj1Gkk6nhhGe8WOvYLvjWvC8Nm8Rnw3QBLnw3M.1CnrK1dcEfDfHZd*AJWPJEnPtVxCTVPse4!/b&bo=xwK4AccCuAEDGTw!&rf=viewer_4&t=5)

&emsp;&emsp;一个项目中的两个模块，模块之间通过module-info.java来关联，在IDEA编辑器右键创建package-info.java    

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcYMZWk64bUjka*Xj1Gkk6niEy.U7uEGL.G.i2nUZSBKV4alc78DK.rD*1fbb3CxfxU4dKx7ITsmf1GVXga4ofs8!/b&bo=MgE9AjIBPQIDGTw!&rf=viewer_4&t=5)

&emsp;&emsp;在这个两个模块java9Demo和java9Test中，java9demo编写一个实体类Person,在java9Test调用这样一个过程    


&emsp;&emsp;这个是java9Demo 将 java9Test 模块需要的文件导出exports  把它所在的包导出    

```java
module java9Demo{
    exports com.mdxl.layer_cj.entity;
}
```

&emsp;&emsp;然后在java9Test模块中创建一个package-info.java,引入java9Demo模块导出包名  

```Java
module java9Test{
    requires java9Demo;
}
```

&emsp;&emsp;这样就可以直接在java9Test中引入Person实体类了，exports 控制着那些包可以被模块访问，所以不被导出的包不能被其他模块访问。  

### 4. 多版本兼容jar包

&emsp;&emsp;怎么理解呢？    
&emsp;&emsp;好多公司用的JDK大部分还是老版本，JDK6、7都有，他们都不敢升级主要是因为兼容的问题，但是JDK9做到了这一点，就是不管公司的项目是用的JDK6、7、8甚至5，他都可以兼容不出错，打个比方，你之前用的是iphone5，现在出现了iPhone6，iphone7，iphon8和iphone9，但是你不敢买9，因为你自己已经适应了iphone5的所有手机的运行流程，6,7,8每个手机的运行流程不一样，但是这个9很强大，它能够识别你现在所用的版本iphone是5，所以当你升级到iphone9的时候，你的这个手机运行流程还是iphone5的流程，只是在原有基础上拥有了更多的iphone9的所有优势。  

### 5. 接口Interface升级

```Java
public interface FilterProcess<T> {

    //java 7 及以前 特性  全局常量 和抽象方法
    public static final String a ="22";
    boolean process(T t);

    //java 8 特性 静态方法和默认方法
    default void love(){
        System.out.println("java8 特性默认方法");
    }
    static void haha(){
        System.out.println("java8 特性静态方法");
    }

    //java 9 特性 支持私有方法
    private void java9(){}
}
```

### 6. 钻石操作符的升级

```java
//java6及以前
Map<String,String> map7 = new HashMap<String,String>();
//java7和8 <>没有了数据类型
Map<String,String> map8 = new HashMap<>();
//java9 添加了匿名内部类的功能 后面添加了大括号{}  可以做一些细节的操作
Map<String,String> map9 = new HashMap<>(){};
```
### 7. 异常处理try升级

&emsp;&emsp;首先看下jdk6,7,8,9 的try catch的比较    

&emsp;&emsp;Java6处理方式：    

```java
//java7及以前写法  每一个流打开的时候都要关闭
@Test
public void test7(){
    InputStreamReader reader = null;
    try{
        reader = new InputStreamReader(System.in);
        reader.read();
    }catch (IOException e){
        e.printStackTrace();
    }finally {
        if (reader != null){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

&emsp;&emsp;JDK7,8 共同的处理方式：    

```java
//java7和8及 每一个流打开的时候都要关闭,但是在try的括号中来进行关闭
@Test
public void test7(){
    try(InputStreamReader reader =new InputStreamReader(System.in)){
        reader.read();
    }catch (IOException e){
        e.printStackTrace();
    }
}
```
&emsp;&emsp;java9的处理方式：    

```java
//java9及 每一个流打开的时候都要关闭,但是在try的括号中来进行关闭，在
//java8的基础上进一步升级 直接在try括号中直接写入 变量就好，如果有多个流，就用分号隔开
//try(reader;writer){}
@Test
public void test7(){
    InputStreamReader reader =new InputStreamReader(System.in);
    try(reader){
        reader.read();
    }catch (IOException e){
        e.printStackTrace();
    }
}
```

### 8. 特殊标识符增

&emsp;&emsp;JDK8之前 String _ ="hello";  这样的标识符可以用，JDK9就用不到。     

### 9. String底层存储结构更换

&emsp;&emsp;JDK8之前 String的底层结构类型都是 char[] ,但是JDK9 就替换成 byte[] 这样来讲，更节省了空间和提高了性能。  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcTFedHk4e29*qNMGpMZxI1zYkMxLKMSfy334y3ZvfCgL8ZL9gliNRptMz2yocQkhVY*HQfS8TUHDtjX2yEt86oU!/b&bo=.AM1AfgDNQEDGTw!&rf=viewer_4&t=5)

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcTFedHk4e29*qNMGpMZxI1zTB951AxgdafYoahFVVCbyjgNeurJXhSr*bwheUsUvKpMhi8mKvmb7*cKMTTzPAp8!/b&bo=uwJ3ALsCdwADGTw!&rf=viewer_4&t=5)

&emsp;&emsp;之所以替换是因为 之前一直是最小单位是一个char，用到两个byte,但是JDK8是基于latin1的，而这个latin1编码可以用一个byte标识，所以当你数据明明可以用到一个byte的时候，我们用到了一个最小单位chat两个byte，就多出了一个byte的空间。所以JDK9在这一方面进行了更新，现在的JDK9 是基于ISO/latin1/Utf-16  ,latin1和ISO用一个byte标识,UTF-16用两个byte标识，JDK9会自动识别用哪个编码，当数据用到1byte，就会使用iSO或者latin1 ，当空间数据满足2byte的时候，自动使用utf-16,节省了很多空间。   

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcTFedHk4e29*qNMGpMZxI1zPUA3pf4IJy5TAgUqIBEx3NJskcr9ytnUbZHu4zddRLS9odFePaim1jEW.0qshc4Q!/b&bo=KAO5AigDuQIDKQw!&rf=viewer_4&t=5)

&emsp;&emsp;同理，StringBuilder StringBuffer也更换了底层数据结构   

### 10. Stream API 新方法的添加

&emsp;&emsp;在原有Stream API 新添加了4个方法，takeWhile dropWhile ofNullable iterate(新重载方法)    

&emsp;&emsp;首先解释 takeWhile 当达到一定条件就结束：输出结果为45 43，如图    

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcTFedHk4e29*qNMGpMZxI1xFCKmrGAfO11BvbSrj09S3B5twJ360Gztqf2VcmXZYWVH3alqSZvP6VehRefjXKqU!/b&bo=ogKjAKICowADGTw!&rf=viewer_4&t=5)

&emsp;&emsp;而 dropWhile 则和takeWhile 相反  

&emsp;&emsp;ofNullable, 在java8中 Stream中的元素不能完全为null，否则空指针异常，而在java9的升级中，允许创建null  

&emsp;&emsp;iterate 不加条件无线循环    

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcUNz8ndoLBclu8PGYwnGP*Ae02*51XIe5Qs9inB2MRx4ntl6Qs2A6PjRvrLG*F*47Rh7qGyYZAi0JZWjZegZxto!/b&bo=4wIzAeMCMwEDGTw!&rf=viewer_4&t=5)

### 11. 引进HttpClient

&emsp;&emsp;以往我们都是通过maven添加httpclient ,java9直接引入即可  

&emsp;&emsp;Java9所有特性都是为了提高性能和内存。。。。  

## JDK10

### 1. 局部变量var

&emsp;&emsp;将前端思想var关键字引入java后段，自动检测所属于类型，一种情况除外，不能为null，因为不能判断具体类型，会报异常。  

```java
@Test
public void test1(){
    var number = 10;
    var str    = "i like java";
    var list   = new ArrayList<>();
    var map    = new HashMap<>();
    var set    = new HashSet<>();

    list.add("test var is list");

    map.put("1","test var is map");

    set.add("test var is set");

    System.out.println(number);

    System.out.println(str);

    System.out.println(list.toString());

    System.out.println(map.toString());

    System.out.println(set.toString());

}
```

&emsp;&emsp;结果：    

```java
10
i like java
[test var is list]
{1=test var is map}
[test var is set]
```

&emsp;&emsp;var可以用于任何类型，基本类型，集合，以及实体类等等都可以，省略了我们在前面写入具体的类型，我们尽管var，让它自己去判断就好了。  

### 2. copyOf方法

&emsp;&emsp;在java.util.List、java.util.Set、java.util.Map新增加了一个静态方法copyOf，这些方法按照其迭代顺序返回一个不可修改的列表、集合或映射包含了给定的元素的集合。但是如果将返回后的集合继续修改，那么报异常。  

```java
@Test
public void test2(){
    var list = new ArrayList<>();

    list.add("first");
    list.add("second");
    list.add("third");

    var result = List.copyOf(list);

    System.out.println(result.toString());
}
```

&emsp;&emsp;结果：  

```java
[first, second, third]
```

### 3. ByteArrayOutputStream

&emsp;&emsp;Java.io.ByteArrayOutputStream，重载toString()方法，通过使用指定的字符集编码字节，将缓冲区的内容转换为字符串，以前是默认没有参数，现在加了一个编码的字符方法。   

```java
public static void main(String[] args) throws Exception {
    String str = "我喜欢java";
    ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes("utf-8"));

    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    int c = 0;
    while((c = bis.read()) != -1) {
        bos.write(c);
    }
    //bos.toString() 默认的使用的UTF-8编码
    System.out.println(bos.toString());
}
```

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcZjggSh8NWGFtihmrfynNY4Ur.saLl*vUwL7iI*oXLfMSByICdWf5VCk6MSu0mz4sjsoI5lr22oquhDa2jWuZb4!/b&bo=HAUJAhwFCQIDGTw!&rf=viewer_4&t=5)

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcZjggSh8NWGFtihmrfynNY7bsqzcdkPhmy1dfV8RZjgO8PhWYU4Iu5pHsMtALkahvpG.riWYodjeCN3GYu4.y74!/b&bo=JQXgASUF4AEDGTw!&rf=viewer_4&t=5)


&emsp;&emsp;这个是我们正常的格式，但是当我们修改代码，str.getBytes("gbk")的时候，创建的txt为乱码，那么我们使用toString()的新增方法，替换gbk。  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcb8utb1tVMXOjzd7Xh6k3Kg0kLeurWPgvipioYjdikerM7Ma*p7vZZN3N8leQk1BGOS54XzAWZSxFowcKdTpxsU!/b&bo=ZgXfAWYF3wEDGTw!&rf=viewer_4&t=5)

### 4. PrintStream、PrintWriter

&emsp;&emsp;`Java.io.PrintStream,Java.io.PrintWriter`，这两个类都有三个新的构造方法，他们需要而外的参数charset  

```java
@Test
public void test3() throws FileNotFoundException, UnsupportedEncodingException {
    String str = "我也特别喜欢java";
    var pri    = new PrintWriter("/Users/tentsuuhou/Desktop/11.txt");
    pri.println(str);
    pri.flush();
    pri.close();
}
```

&emsp;&emsp;我这个路径是mac电脑的，如果你是windows，可以写“d:/aa.txt“ 不过都需要提前创建好txt文件夹    

&emsp;&emsp;结果为：  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcb8utb1tVMXOjzd7Xh6k3Kj4HPmsmFNRG4O2vPZCINZWLFsYF9RPPs2AMPZdQm.34AfWBpj4w2t6C*mq73wZYq0!/b&bo=agSQAWoEkAEDGTw!&rf=viewer_4&t=5)


&emsp;&emsp;但是我们这个txt默认的都是utf-8格式的，如果突然改成gbk的话，格式会不匹配出现乱码，毕竟txt文件格式要和输出的格式一致才可以，这是我们可以使用一下。  

&emsp;&emsp;`var pri = new PrintWriter("/Users/tentsuuhou/Desktop/11.txt","gbk");`  
&emsp;&emsp;我们直接在后面添加一个格式参数就可以直接解决啦  

### 5. Reader:transferTo方法

&emsp;&emsp;Java.io.Reader:transferTo从这个Reader中读取所有字符串，并按照所读取的顺序将字符串写入给指定的Writer  

```java
@Test
public void test4() throws IOException {
    var reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/tentsuuhou/Desktop/11.txt"), "utf-8"));
    var p = new PrintWriter(new File("/Users/tentsuuhou/Desktop/12.txt"));
    reader.transferTo(p);
    p.flush();
    p.close();
    reader.close();
}
```
&emsp;&emsp;结果：12.txt文件将11.txt文件拷贝过来了，注意编码格式也要一致才可以。用这个方法方便了许多。    

### 6. Formatter、Scanner

&emsp;&emsp;`java.util.Formatter、java.util.Scanner`新增三个构造方法，除了其他参数之外，都需要一个charset参数，将11.txt内容替换成这样  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcb8utb1tVMXOjzd7Xh6k3Kh1i9FroW2.rW*15R48eW3UIWwtePmTLGP9vBsSALQpNnSq9JixuS33TydjaUMkf9k!/b&bo=UgQ6AVIEOgEDGTw!&rf=viewer_4&t=5)

```java
@Test
public void test5() throws FileNotFoundException {
    var scan = new Scanner(new FileInputStream(new File("/Users/tentsuuhou/Desktop/11.txt")),"utf-8");
    scan.useDelimiter(" |,");
    while(scan.hasNext()) {
        System.out.println(scan.next());
    }

}
```

&emsp;&emsp;`scan.useDelimiter(" |,");` 这是空格或者‘,’ 都要分割，如下结果为：  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcb8utb1tVMXOjzd7Xh6k3KhdumActLOLm8kmwS2lL01CKPD56KI2I*k8k4hAu3Mto2QBYtj6x.zMbl3ocgl51tk!/b&bo=8wGfAPMBnwADGTw!&rf=viewer_4&t=5)


### 7. 垃圾收集器的优化

1. JDK9垃圾收集器 ：新生代：ParNew收集器；老年代：Parallel Old收集器   Stop The World
2. JDK10垃圾收集器：JDK10：G1（Garbage-Frist）全收集器

---

## JDK11

### 1. 增强局部变量类型推断var

```java
Consumer<String> consumer = t -> System.out.println(t.toUpperCase());
Consumer<String> consumer = (var t) -> System.out.println(t.toUpperCase());

错误的形式：必须要有类型，可以加上var
Consumer<String> consumer = (@Deprecated t) ->System.out.println(t.toUpperCase())
正确的形式
Consumer<String> consumer = (@Deprecated var t) ->System.out.println(t.toUpperCase())
```

### 2. 增加一些实用的API

&emsp;&emsp;自JDK9和JDK10都为java增加了许多的API，如今JDK11又增加了许多字符串自带方法，如下  

```java
@Test
public void contextLoads() {
    String str = "woshidage";
    boolean isblank = str.isBlank();  //判断字符串是空白
    boolean isempty = str.isEmpty();  //判断字符串是否为空
    String  result1 = str.strip();    //首位空白
    String  result2 = str.stripTrailing();  //去除尾部空白
    String  result3 = str.stripLeading();  //去除首部空白
    String  copyStr = str.repeat(2);  //复制几遍字符串
    long  lineCount = str.lines().count();  //行数统计

    System.out.println(isblank);
    System.out.println(isempty);
    System.out.println(result1);
    System.out.println(result2);
    System.out.println(result3);
    System.out.println(copyStr);
    System.out.println(lineCount);
}
```
&emsp;&emsp;结果为：     

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcQzHDlHd9Aal.hoW6KhcHvUYtDj4.vCXqC6AQjTWQ580qJp1JIgwokdKSerTQa*Ejwx8mBoBLr3AcpkcwY6zLJ4!/b&bo=eALiAHgC4gADGTw!&rf=viewer_4&t=5)

### 3. 移除废弃的内容

#### 3.1 移除项

1. 移除了com.sun.awt.AWTUtilities
2. 移除了sun.misc.Unsafe.defineClass，使用java.lang.invoke.MethodHandles.Lookup.defineClass来替代
3. 移除了Thread.destroy()以及 Thread.stop(Throwable)方法
4. 移除了sun.nio.ch.disableSystemWideOverlappingFileLockCheck、sun.locale.formatasdefault属性
5. 移除了jdk.snmp模块
6. 移除了javafx，openjdk估计是从java10版本就移除了，oracle jdk10还尚未移除javafx，而java11版本则oracle的jdk版本也移除了javafx
7. 移除了Java Mission Control，从JDK中移除之后，需要自己单独下载
8. 移除了这些Root Certificates ：Baltimore Cybertrust Code Signing CA，SECOM ，AOL and Swisscom

#### 3.2 废弃项

1. -XX+AggressiveOpts选项
2. -XX:+UnlockCommercialFeatures
3. -XX:+LogCommercialFeatures选项也不再需要

### 4. HttpClient加强方法

&emsp;&emsp;现在 Java 自带了这个 HTTP Client API，我们以后还有必要用 Apache 的 HttpClient 工具包吗？  

#### 4.1 需要远程调用的接口

```java
@RequestMapping(value = "/dshjbca")
public String test1(){
    return "dfs";
}
```

#### 4.2 HttpClient调用该接口

```java
//同步调用
@Test
public void test2() throws IOException, InterruptedException {

    HttpClient client             = HttpClient.newHttpClient();
    HttpRequest request           = HttpRequest.newBuilder(URI.create("")).build();
    BodyHandler<String>  handler  = HttpResponse.BodyHandlers.ofString();
    HttpResponse<String> response = client.send(request,handler);
    String body                   = response.body();
    System.out.println(body);
}

//异步调用
@Test
public void test3() throws IOException, InterruptedException, ExecutionException {

    HttpClient client             = HttpClient.newHttpClient();
    HttpRequest request           = HttpRequest.newBuilder(URI.create("")).build();
    BodyHandler<String>  handler  = HttpResponse.BodyHandlers.ofString();
    CompletableFuture<HttpResponse<String>> response = client.sendAsync(request,handler);
    HttpResponse<String> result   = response.get();
    String body                   = result.body();
    System.out.println(body);
}
```
&emsp;&emsp;结果：  

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcQzHDlHd9Aal.hoW6KhcHvW6uVjacIym0vy9.Hp.25Z5*JDzr88yyRLrkdJCCM7ofm.nBc69qVkNxGKVWkrspjQ!/b&bo=.wPoAvsD6AIDKQw!&rf=viewer_4&t=5)

### 5. Unicode 10

&emsp;&emsp;Unicode 10 增加了8518个字符, 总计达到了136690个字符. 并且增加了4个脚本.同时还有56个新的emoji表情符号。  

### 6. Remove the JavaEE and CORBA Moudles

&emsp;&emsp;在java11中移除了不太使用的JavaEE模块和CORBA技术，在java11中将java9标记废弃的Java EE及CORBA模块移除掉。    

1. java.xml.ws,
2. java.xml.bind，
3. java.xml.ws，
4. java.xml.ws.annotation，
5. jdk.xml.bind，
6. jdk.xml.ws被移除，只剩下java.xml，java.xml.crypto,jdk.xml.dom这几个模块
7. java.corba，
8. java.se.ee，
9. java.activation，
10. java.transaction被移除，但是java11新增一个java.transaction.xa模块

### 7. JEP : 335 : Deprecate the Nashorn JavaScript Engine

&emsp;&emsp;废除Nashorn javascript引擎，在后续版本准备移除掉，有需要的可以考虑使用GraalVM。     

### 8、JEP : 336 : Deprecate the Pack200 Tools and API

&emsp;&emsp;Java5中带了一个压缩工具:Pack200，这个工具能对普通的jar文件进行高效压缩。其  实现原理是根据Java类特有的结构，合并常数  池，去掉无用信息等来实现对java类的高效压缩。由于是专门对Java类进行压缩的，所以对普通文件的压缩和普通压缩软件没有什么两样，但是对于Jar  文件却能轻易达到10-40%的压缩率。这在Java应用部署中很有用，尤其对于移动Java计算，能够大大减小代码下载量。  

&emsp;&emsp;Java5中还提供了这一技术的API接口，你可以将其嵌入到你的程序中使用。使用的方法很简单，下面的短短几行代码即可以实现jar的压缩和解压：    

压缩  

```java
Packer packer=Pack200.newPacker();

OutputStream output=new BufferedOutputStream(new  FileOutputStream(outfile));

packer.pack(new JarFile(jarFile), output);

output.close();
```

解压  

```java
Unpacker unpacker=Pack200.newUnpacker();

output=new JarOutputStream(new FileOutputStream(jarFile));

unpacker.unpack(pack200File, output);

output.close();
```

&emsp;&emsp;Pack200的压缩和解压缩速度是比较快的，而且压缩率也是很惊人的，在我是使用  的包4.46MB压缩后成了1.44MB（0.322%），而且随着包的越大压缩率会根据明显，据说如果jar包都是class类可以压缩到1/9的大  小。其实JavaWebStart还有很多功能，例如可以按不同的jar包进行lazy下载和 单独更新，设置可以根据jar中的类变动进行class粒度的下载。但是在java11中废除了pack200以及unpack200工具以及java.util.jar中的Pack200 API。因为Pack200主要是用来压缩jar包的工具，由于网络下载速度的提升以及java9引入模块化系统之后不再依赖Pack200，因此这个版本将其移除掉。    

### 9. 新的Epsilon垃圾收集器

&emsp;&emsp;A NoOp Garbage Collector JDK上对这个特性的描述是: 开发一个处理内存分配但不实现任何实际内存回收机制的GC, 一旦可用堆内存用完, JVM就会退出.   

&emsp;&emsp;如果有System.gc()调用, 实际上什么也不会发生(这种场景下和-XX:+DisableExplicitGC效果一样), 因为没有内存回收, 这个实现可能会警告用户尝试强制GC是徒劳.   

&emsp;&emsp;**用法**：`-XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC`  

&emsp;&emsp;**选项**：`-XX:+UseEpsilonGC`, 程序很快就因为堆空间不足而退出  

&emsp;&emsp;**原因** :提供完全被动的GC实现, 具有有限的分配限制和尽可能低的延迟开销,但代价是内存占用和内存吞吐量, java实现可广泛选择高度可配置的GC实现. 各种可用的收集器最终满足不同的需求, 即使它们的可配置性使它们的功能相交. 有时更容易维护单独的实现, 而不是在现有GC实现上堆积另一个配置选项.  

&emsp;&emsp;主要用途如下 :    

1. 性能测试(它可以帮助过滤掉GC引起的性能假象)
2. 内存压力测试(例如,知道测试用例 应该分配不超过1GB的内存, 我们可以使用-Xmx1g –XX:+UseEpsilonGC, 如果程序有问题, 则程序会崩溃)
3. 非常短的JOB任务(对象这种任务, 接受GC清理堆那都是浪费空间)
4. VM接口测试
5. Last-drop 延迟&吞吐改进

### 10. ZGC

&emsp;&emsp;ZGC, 这应该是JDK11最为瞩目的特性, 没有之一. 但是后面带了Experimental, 说明这还不建议用到生产环境。    
&emsp;&emsp;GC暂停时间不会超过10ms，既能处理几百兆的小堆, 也能处理几个T的大堆(OMG)，和G1相比, 应用吞吐能力不会下降超过15%，为未来的GC功能和利用colord指针以及Load barriers优化奠定基础，初始只支持64位系统   
&emsp;&emsp;ZGC的设计目标是：支持TB级内存容量，暂停时间低（<10ms），对整个程序吞吐量的影响小于15%。 将来还可以扩展实现机制，以支持不少令人兴奋的功能，例如多层堆（即热对象置于DRAM和冷对象置于NVMe闪存），或压缩堆。  
&emsp;&emsp;GC是java主要优势之一，当GC停顿太长, 就会开始影响应用的响应时间.消除或者减少GC停顿时长, java将对更广泛的应用场景是一个更有吸引力的平台. 此外, 现代系统中可用内存不断增长,用户和程序员希望JVM能够以高效的方式充分利用这些内存, 并且无需长时间的GC暂停时间     

&emsp;&emsp;用法：`-XX:+UnlockExperimentalVMOptions –XX:+UseZGC`, 因为ZGC还处于实验阶段, 所以需要通过JVM参数来解锁这个特性    

### 11. 完全支持Linux容器（包括Docker）

&emsp;&emsp;许多运行在Java虚拟机中的应用程序（包括Apache Spark和Kafka等数据服务以及传统的企业应用程序）都可以在Docker容器中运行。但是在Docker容器中运行Java应用程序一直存在一个问题，那就是在容器中运行JVM程序在设置内存大小和CPU使用率后，会导致应用程序的性能下降。这是因为Java应用程序没有意识到它正在容器中运行。随着Java 10的发布，这个问题总算得以解决，JVM现在可以识别由容器控制组（cgroups）设置的约束。可以在容器中使用内存和CPU约束来直接管理Java应用程序，其中包括：     

* 遵守容器中设置的内存限制
* 在容器中设置可用的CPU
* 在容器中设置CPU约束
* Java 10的这个改进在Docker for Mac、Docker for Windows以及Docker Enterprise Edition等环境均有效。
* 容器的内存限制
* 在Java 9之前，JVM无法识别容器使用标志设置的内存限制和CPU限制。而在Java 10中，内存限制会自动被识别并强制执行。Java将服务器类机定义为具有2个CPU和2GB内存，以及默认堆大小为物理内存的1/4。


### 12. 支持G1上的并行完全垃圾收集

&emsp;&emsp;对于 G1 GC，相比于 JDK 8，升级到 JDK 11 即可免费享受到：并行的 Full GC，快速的 CardTable 扫描，自适应的堆占用比例调整（IHOP），在并发标记阶段的类型卸载等等。这些都是针对 G1 的不断增强，其中串行 Full GC 等甚至是曾经被广泛诟病的短板，你会发现 GC 配置和调优在 JDK11 中越来越方便。    

---

## JDK12

### 1. Shenandoah低暂停时间垃圾收集器（实验性）

&emsp;&emsp;定义：  

&emsp;&emsp;添加一个名为Shenandoah的新垃圾收集（GC）算法，通过与正在运行的Java线程同时进行疏散工作
来减少GC暂停时间。使用Shenandoah的暂停时间与堆大小无关，这意味着无论堆是200MB还是200GB，都
将具有相同的一致暂停时间。  

&emsp;&emsp;非目标：  

&emsp;&emsp;这不是一个统治所有人的GC。还有其他垃圾收集算法可以优先考虑吞吐量或内存占用而不是响应性。Shenandoah是适用于评估响应性和可预测的短暂停顿的应用程序的算法。目标不是解决所有JVM暂停问题。由于GC之外的其他原因（例如安全时间点（TTSP）发布或监控通胀）而暂停时间超出了此JEP的范围。   


&emsp;&emsp;构建和调用：   

&emsp;&emsp;作为实验性功能，Shenandoah将`-XX:+UnlockExperimentalVMOptions`在命令行中要求。Shenandoah构建系统会自动禁用不受支持的配置。下游建设者可以选择`--with-jvm-features=-shenandoahgc`在其他支持的平台上禁用构建Shenandoah。要启用/使用`Shenandoah GC`，需要以下JVM选项：`-XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC`   

### 2. Microbenchmark Suite

&emsp;&emsp;定义：   

&emsp;&emsp;在JDK源代码中添加一套基本的微基准测试，使开发人员可以轻松运行现有的微基准测试并创建新的基准测试。  

&emsp;&emsp;目标：  

1. 基于[Java Microbenchmark线束（JMH）] [1]
2. 稳定且经过调整的基准测试，针对持续性能测试
    2.1 在功能发布的功能完成里程碑之后，以及非功能版本之后的稳定且不移动的套件
    2.2 支持与先前JDK版本的适用测试比较
3. 简单
	3.1 轻松添加新基准
    3.2 在API和选项更改，不推荐使用或在开发期间删除时，可以轻松更新测试
    3.3 易于构建
    3.4 易于查找和运行基准
    3.5 支持JMH更新
    3.6 在套件中包含大约一百个基准的初始集
    
### 3. Switch表达式（预览版）

&emsp;&emsp;预览功能，如果该jdk12的switch效果不好的话，会被下一版本jdk13直接移除该功能，并不是之后每个版本必须的。  

&emsp;&emsp;许多break使它不必要地冗长，如果忘记写break，则会产生意料之外的结果或者异常，所以针对于此jdk12在这里进行了优化升级。  

&emsp;&emsp;JDK12之前的版本：     

```java
switch (day) {
    case MONDAY:
    case FRIDAY:
    case SUNDAY:
        System.out.println(6);
        break;
    case TUESDAY:
        System.out.println(7);
        break;
    case THURSDAY:
    case SATURDAY:
        System.out.println(8);
        break;
    case WEDNESDAY:
        System.out.println(9);
        break;
}
```

&emsp;&emsp;JDK12:我们建议引入一种新形式的开关标签，写成`case L ->`表示如果标签匹配，则只执行标签右侧的代码。  

```java
switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
    case TUESDAY                -> System.out.println(7);
    case THURSDAY, SATURDAY     -> System.out.println(8);
    case WEDNESDAY              -> System.out.println(9);
}
```
&emsp;&emsp;许多Switch表达式，每个case都会赋值给一个变量或者执行某种操作，如下是赋值给numLetters变量具体值。  

&emsp;&emsp;JDK12之前：  

```java
int numLetters;
switch (day) {
    case MONDAY:
    case FRIDAY:
    case SUNDAY:
        numLetters = 6;
        break;
    case TUESDAY:
        numLetters = 7;
        break;
    case THURSDAY:
    case SATURDAY:
        numLetters = 8;
        break;
    case WEDNESDAY:
        numLetters = 9;
        break;
    default:
        throw new IllegalStateException("Wat: " + day);
}
```

&emsp;&emsp;JDK12:将此表达为一种陈述是迂回的，重复的，并且容易出错。意味着我们应该计算numLetters每一天的价值。应该可以直接说，使用更清晰，更安全Switch表达式    

```java
int numLetters = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> 6;
    case TUESDAY                -> 7;
    case THURSDAY, SATURDAY     -> 8;
    case WEDNESDAY              -> 9;
};
```

&emsp;&emsp;如果将它用在方法上，则可以为：  

```java
static void howMany(int k) {
    switch (k) {
        case 1 -> System.out.println("one");
        case 2 -> System.out.println("two");
        case 3 -> System.out.println("many");
    }
}
```

&emsp;&emsp;或者类上，我想到的这个switch这个功能可以用在抽象工厂类方面。   

```java
T result = switch (arg) {
    case L1 -> e1;
    case L2 -> e2;
    default -> e3;
};
```

### 4. JVM常量API

&emsp;&emsp;摘要：  

&emsp;&emsp;引入API来模拟关键类文件和运行时工件的名义描述，特别是可从常量池加载的常量。  

&emsp;&emsp;动机：  

&emsp;&emsp;每个Java类文件都有一个常量池，用于存储类中字节码指令的操作数。从广义上讲，常量池中的条目描述了运行时工件（如类和方法）或简单值（如字符串和整数）。所有这些条目都称为可加载常量，因为它们可以作为ldc指令的操作数（“加载常量”）。   
 
&emsp;&emsp;它们也可能出现在invokedynamic指令的bootstrap方法的静态参数列表中。执行一个ldc或invokedynamic指令导致加载常数被解析成一个标准的Java类，如“活”的值Class，String或int。  

&emsp;&emsp;操作class文件的程序需要对字节码指令进行建模，并依次对可加载的常量进行建模。但是，使用标准Java类型
来模拟可加载常量是不合适的。  

&emsp;&emsp;描述字符串（`CONSTANT_String_info`条目）的可加载常量可能是可以接受的，因为生成“实时” String对象很简单，但是对于描述类（`CONSTANT_Class_info`条目）的可加载常量是有问题的，因为产生“实时”ClassObject依赖于类加载的正确性和一致性。 
 
&emsp;&emsp;不幸的是，类加载有许多环境依赖和失败模式：所需的类不存在或者请求者可能无法访问; 类加载的结果因上下文
而异; 装载类有副作用;有时类加载可能根本不可能（例如当所描述的类尚不存在或者无法加载时，如在编译那些相同类
或在jlink转换期间）。

&emsp;&emsp;因此，处理可加载常量的程序如果能够以纯粹的名义符号形式操作类和方法，以及不太知名的工件（如方法句柄和动
态计算常量），则会更简单：

1. 字节码解析和生成库必须以符号形式描述类和方法句柄。如果没有标准机制，它们必须采用ad-hoc机制，无论是
ASM的描述符类型Handle，还是字符串元组（方法所有者，方法名称，方法描述符），或者这些机制的特殊（并且容易出
错）编码单个字符串。
2. 如果它们可以在符号域中工作而不是使用“实时”类和方法句柄，invokedynamic那么通过旋转字节码（例如LambdaMetafactory）来操作的Bootstraps 将更简单。
3. 编译器和脱机转换器（例如jlink插件）需要描述无法加载到正在运行的VM的类的类和成员。编译器插件（例如注
释处理器）同样需要用符号术语来描述程序元素。

### 5. 一个AArch64端口，而不是两个

&emsp;&emsp;摘要：  

&emsp;&emsp;arm64在保留32位ARM端口和64位aarch64端口的同时，删除与端口相关的所有源。  

&emsp;&emsp;动机：  

&emsp;&emsp;删除此端口将允许所有贡献者将他们的精力集中在单个64位ARM实现上，并消除维护两个端口所需的重复工作。   
### 6. 默认CDS档案

&emsp;&emsp;摘要：  

&emsp;&emsp;在64位平台上使用默认类列表增强JDK构建过程以生成类数据共享（CDS）归档。  

&emsp;&emsp;目标：  

1. 改善开箱即用的启动时间
2. 消除用户运行-Xshare:dump以从CDS中受益的需要
3. 
### 7. G1的可流动混合收集

&emsp;&emsp;摘要：  

&emsp;&emsp;如果G1混合集合可能超过暂停目标，则使其可以中止。  

&emsp;&emsp;动机：   

&emsp;&emsp;G1的目标之一是满足用户提供的暂停时间目标以暂停其收集暂停。G1使用高级分析引擎来选择在集合期间要完成的工作量（这部分基于应用程序行为）。此选择的结果是一组称为集合集的区域。一旦确定了集合集并且已经开始集合，则G1必须在不停止的情况下收集集合集的所有区域中的所有活动对象。如果启发式选择过大的收集，则此行为可导致G1超过暂停时间目标，例如，如果应用程序的行为发生变化，以致启发式工作在“陈旧”数据上，则可能发生这种情况。特别是在混合集合期间可以观察到这种情况，其中集合集通常可以包含太多旧区域。需要一种机制来检测启发式方法何时反复为集合选择错误的工作量，如果是，则让G1逐步递增地执行收集工作，其中集合可以在每个步骤之后中止。这种机制将允许G1更频繁地满足暂停时间目标。8、JDK12之从G1中立即返回未使用的已提交内存

&emsp;&emsp;摘要：  

&emsp;&emsp;增强G1垃圾收集器，以便在空闲时自动将Java堆内存返回给操作系统。  

&emsp;&emsp;成功指标：  

&emsp;&emsp;如果应用程序活动非常低，G1应该在合理的时间段内释放未使用的Java堆内存。  

&emsp;&emsp;动机：

&emsp;&emsp;目前G1垃圾收集器可能无法及时将已提交的Java堆内存返回给操作系统。G1仅在完整GC或并发周期内从Java堆返回内存。由于G1很难完全避免完整的GC，并且只触发基于Java堆占用和分配活动的并发周期，因此在许多情况下它不会返回Java堆内存，除非在外部强制执行此操作。  
&emsp;&emsp;在使用资源支付的容器环境中，这种行为特别不利。即使在VM由于不活动而仅使用其分配的内存资源的一小部分的阶段，G1也将保留所有Java堆。这导致客户始终为所有资源付费，云提供商无法充分利用其硬件。  
&emsp;&emsp;如果VM能够检测到Java堆的利用率不足（“空闲”阶段），并在此期间自动减少其堆使用量，则两者都将受益。   
&emsp;&emsp;Shenandoah和OpenJ9的GenCon收集器已经提供了类似的功能。   
 
### 9. 核心库java.lang中支持Unicode11

&emsp;&emsp;JDK 12版本包括对Unicode 11.0.0的支持。在发布支持Unicode 10.0.0的JDK 11之后，Unicode 11.0.0引入了以下JDK 12中包含的新功能：  

1. 684个新角色
    1.1. 66个表情符号字符
    1.2. Copyleft符号
    1.3. 评级系统的半星
    1.4. 额外的占星符号
    1.5. 象棋中国象棋符号
2. 11个新区块
    2.1. 格鲁吉亚扩展
    2.2. 玛雅数字
    2.3. 印度Siyaq数字
    2.4. 国际象棋符号
3. 7个新脚本
    3.1. Hanifi Rohingya
    3.2. Old Sogdian
    3.3. Sogdian
    3.4. Dogra
    3.5. Gunjala Gondi
    3.6. Makasar
    3.7. Medefaidrin
### 10. 核心库java.text支持压缩数字格式

&emsp;&emsp;NumberFormat添加了对以紧凑形式格式化数字的支持。紧凑数字格式是指以简短或人类可读形式表示的数字。例如，在en_US语言环境中，1000可以格式化为“1K”，1000000可以格式化为“1M”，具体取决于指定的样式`NumberFormat.Style`。紧凑数字格式由LDML的Compact Number格式规范定义。要获取实例，请使用NumberFormat紧凑数字格式所给出的工厂方法之一。  
&emsp;&emsp;例如：`NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);`
`String result = fmt.format(1000); `   
&emsp;&emsp;上面的例子导致“1K”。  

### 11. 安全库java.security

&emsp;&emsp;禁止并允许`java.security.manager`系统属性的选项   

&emsp;&emsp;新的“disallow”和“allow”令牌选项已添加到java.security.manager系统属性中。在JDK实现中，如果Java虚拟机以系统属性`java.security.manager`设置为“disallow”开始，则该`System.setSecurityManager`方法不能用于设置安全管理器并将抛出`UnsupportedOperationException`。“disallow”选项可以提高从未设置安全管理器的应用程序的运行时性能。    

&emsp;&emsp;groupname选项添加到keytool密钥对生成   

&emsp;&emsp;-groupname添加了一个新选项，keytool -genkeypair以便用户在生成密钥对时可以指定命名组。例如，keytool -genkeypair -keyalg EC -groupname secp384r1将使用secp384r1曲线生成EC密钥对。由于可能存在多个具有相同大小的曲线，因此使用该-groupname选项优先于该-keysize选项。

&emsp;&emsp;新Java飞行记录器（JFR）安全事件  

&emsp;&emsp;略过...  

&emsp;&emsp;自定义PKCS12密钥库生成,    


&emsp;&emsp;添加了新的系统和安全属性，使用户能够自定义PKCS＃12密钥库的生成。这包括用于密钥保护，证书保护和MacData的算法和参数。可以在文件的“PKCS12 KeyStore属性”部分中找到这些属性的详细说明和可能的值java.security。    

### 12. 安全库javax.net.ssl

&emsp;&emsp;ChaCha20和Poly1305 TLS密码

&emsp;&emsp;JSSE中添加了使用ChaCha20-Poly1305算法的新TLS密码套件。默认情况下启用这些密码套件。

&emsp;&emsp;`TLS_CHACHA20_POLY1305_SHA256`密码套件适用于TLS 1.3。


### 13. 安全库/ org.ietf.jgss

&emsp;&emsp;在`krb5.conf`中支持`dns_canonicalize_hostname`

&emsp;&emsp;该`dns_canonicalize_hostname`标志中`krb5.conf`的配置文件现在是由`JDK Kerberos`实现支持。
设置为“true”时，服务主体名称中的短主机名将被规范化为完全限定的域名（如果可用）。否则，不执行规
范化。默认值是true”。这也是JDK 12之前的行为。 

### 14. 删除项

&emsp;&emsp;核心库`/ java.util.jar`中，删除`java.util.ZipFile / Inflator / Deflator`中的`finalize`方法 

&emsp;&emsp;该finalize方法在`java.util.ZipFile`，`java.util.Inflator`和`java.util.Deflator`是在JDK9弃用用于移除及其执行了更新，是一个空操作。该finalize在方法`java.util.ZipFile`，`java.util.Inflator`以及`java.util.Deflator`在此版本中被删除。finalize应修改为执行清理而重写的子类，以使用备用清理机制并删除写finalize方法。  

&emsp;&emsp;finalize方法，去除将暴露`Object.finalize`到的子类`ZipFile`，`Deflater`和`Inflater`。finalize由于声明的异常发生更改，可能会在覆盖时发生编译错误。`Object.finalize`现在宣布投掷`java.lang.Throwable`。以前，只有`java.io.IOException`宣布。  

&emsp;&emsp;核心库`/ java.io`，从FileInputStream和FileOutputStream中删除finalize方法   

&emsp;&emsp;该finalize方法FileInputStream和FileOutputStream被弃用去除JDK 9.他们已经在此版本中被删除。所述`java.lang.ref.Cleaner`，自JDK9的主要机制已被实施，以关闭文件描述符不再从可到达的`FileInputStream`和`FileOutputStream`。关闭文件的推荐方法是显式调用close或使用try-with-resources。  

&emsp;&emsp;工具/ javac的删除javac支持6 / 1.6源，目标和发布值

&emsp;&emsp;为的javac的6/1.6的参数值的支持-source，-target以及--release标志已被删除。

---

## JDK13

### 1. switch优化更新（预览版）

&emsp;&emsp;什么是预览版，也就是说当前版本有这个功能，下一个版本不确定会有，如果该功能效果不是很好，会被下一版本移除该功能。  

JDK11以及之前的版本  

```java
switch (day) {
    case MONDAY: 
    case FRIDAY:
    case SUNDAY:
         System.out.println(6); 
         break; 
    case TUESDAY: 
        System.out.println(7); 
        break; case THURSDAY: 
    case SATURDAY: 
        System.out.println(8);
         break; 
    case WEDNESDAY:
         System.out.println(9);
         break; 
}
```

&emsp;&emsp;JDK12版本  

```java
switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> System.out.println(6); 
    case TUESDAY -> System.out.println(7); 
    case THURSDAY, SATURDAY -> System.out.println(8); 
    case WEDNESDAY -> System.out.println(9);
 }
```
 
&emsp;&emsp;JDK13版本，可以返回信息  

```java
@Test
void test1() {
   int k = 2;
   String result = switch (k) {
      case  1 -> "one";
      case  2 -> "two";
      default -> "many";
   };
   System.out.println(result);
}
```

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcZVE.txZu9IYpBxK2ssxkl.j8HE5UEG.nT.WW4psI.R2wy8VpUumOlaZmCyW2f4amnZCnE2kkylPPsz.whgUc4c!/b&bo=OAM0AjgDNAIDGTw!&rf=viewer_4&t=5)

### 2. 文本块升级

#### 2.1、html例子

&emsp;&emsp;JDK13之前  

```html
String html = "<html>\n" +
              "    <body>\n" +
              "        <p>Hello, world</p>\n" +
              "    </body>\n" +
              "</html>\n";
```

&emsp;&emsp;JDK13优化  

```java
String html = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """;
```

#### 2.2、SQL变化
 
&emsp;&emsp;JDK13之前  

```java
String query = "SELECT `EMP_ID`, `LAST_NAME` FROM `EMPLOYEE_TB`\n" +
               "WHERE `CITY` = 'INDIANAPOLIS'\n" +
               "ORDER BY `EMP_ID`, `LAST_NAME`;\n";
```

&emsp;&emsp;JDK13  

```java
String query = """    
                SELECT `EMP_ID`, `LAST_NAME` FROM `EMPLOYEE_TB`
                WHERE `CITY` = 'INDIANAPOLIS'
                ORDER BY `EMP_ID`, `LAST_NAME`;
               """;
```

#### 2.3、解释

&emsp;&emsp;文本块  

```java
"""
line 1
line 2
line 3
"""
```


&emsp;&emsp;相当于字符串文字：  

```java
"line 1\nline 2\nline 3\n"
```

### 3. 动态CDS档案

&emsp;&emsp;目标：  

&emsp;&emsp;提高应用程序类 - 数据共享（AppCDS）的可用性。消除了用户进行试运行以创建每个应用程序的类列表的需要。`-Xshare:dump`使用类列表由该选项启用的静态归档应继续工作。这包括内置类加载器和用户定义的类加载器的类。  

### 4. 取消使用未使用的内存

&emsp;&emsp;摘要：  

&emsp;&emsp;增强ZGC以将未使用的堆内存返回给操作系统。   

&emsp;&emsp;动机：    

&emsp;&emsp;ZGC目前没有取消提交并将内存返回给操作系统，即使该内存长时间未使用。对于所有类型的应用程序和环境，此行为并非最佳，尤其是那些需要关注内存占用的应用程序和环境 例如：通过使用支付资源的容器环境。应用程序可能长时间处于空闲状态并与许多其他应用程序共享或竞争资源的环境。应用程序在执行期间可能具有非常不同的堆空间要求。  

&emsp;&emsp;例如，启动期间所需的堆可能大于稳态执行期间稍后所需的堆。HotSpot中的其他垃圾收集器，如G1和Shenandoah，提供了这种功能，某些类别的用户发现它非常有用。将此功能添加到ZGC将受到同一组用户的欢迎。    

### 5. 重新实现旧版套接字API    

&emsp;&emsp;摘要：  

&emsp;&emsp;使用更简单，更现代的实现替换`java.net.Socke`t和`java.net.ServerSocketAPI`使用的底层实现，易于维护和调试。新的实现很容易适应用户模式线程，也就是光纤，目前正在Project Loom中进行探索。    

&emsp;&emsp;动机：    

&emsp;&emsp;在`java.net.Socket`和`java.net.ServerSocketAPI`，以及它们的底层实现，可以追溯到JDK1.0。实现是遗留Java和C代码的混合，维护和调试很痛苦。该实现使用线程堆栈作为I/O缓冲区，这种方法需要多次增加默认线程堆栈大小。该实现使用本机数据结构来支持异步关闭，这是多年来微妙可靠性和移植问题的根源。该实现还有几个并发问题，需要进行大修才能正确解决。在未来的光纤世界环境中，而不是在本机方法中阻塞线程，当前的实现不适用于目的。    


### 6. FileSystems.newFileSystem新方法

&emsp;&emsp;核心库/ java.nio中添加了`FileSystems.newFileSystem（Path，Map <String，？>）`方法     

&emsp;&emsp;添加了三种新方法`java.nio.file.FileSystems`，以便更轻松地使用将文件内容视为文件系统的文件系统提供程序。    

1. `newFileSystem(Path)`
2. `newFileSystem(Path, Map<String, ?>)`
3. `newFileSystem(Path, Map<String, ?>, ClassLoader)`
&emsp;&emsp;添加为`newFileSystem(Path, Map<String, ?>)` 已使用现有`2-arg newFileSystem(Path,ClassLoader)`并指定类加载器的代码创建源（但不是二进制）兼容性问题。例如，由于引用newFileSystem不明确，因此无法编译以下内容：   ` FileSystem fs = FileSystems.newFileSystem(path, null)`,为了避免模糊引用，需要修改此代码以将第二个参数强制转换为java.lang.ClassLoader。  

### 7. nio新方法

&emsp;&emsp;核心库/ java.nio中新的java.nio.ByteBuffer批量获取/放置方法转移字节而不考虑缓冲区位置。

&emsp;&emsp;java.nio.ByteBufferjava.nio现在，其他缓冲区类型定义绝对批量get和put传输连续字节序列的方法，而不考虑或影响缓冲区位置。

### 8. 核心库/ java.time

&emsp;&emsp;新日本时代名称Reiwa，此更新中添加了代表新Reiwa时代的实例。与其他时代不同，这个时代没有公共领域。它可以通过调用JapaneseEra.of(3)或获得`JapaneseEra.valueOf("Reiwa")`。JDK13及更高版本将有一个新的公共领域来代表这个时代。  

&emsp;&emsp;NewEra从2019年5月1日开始的日本时代的占位符名称“ ”已被新的官方名称取代。依赖占位符名称获取新时代单例（`JapaneseEra.valueOf("NewEra")`）的应用程序将不再起作用。  

### 9. 核心库/ java.util中：I18N

&emsp;&emsp;支持Unicode 12.1，此版本将Unicode支持升级到12.1，其中包括以下内容：    

&emsp;&emsp;`java.lang.Character`支持12.1级的Unicode字符数据库，其中12.0从11.0开始增加554个字符，总共137,928个字符。这些新增内容包括4个新脚本，总共150个脚本，以及61个新的表情符号字符。`U+32FF SQUARE ERA NAME REIWA`从12.0开始，12.1只添加一个字符。`java.text.Bidi`和`java.text.Normalizer`类分别支持12.0级的Unicode标准附件，＃9和＃15。`java.util.regexpackage`支持基于12.0级Unicode标准附件＃29的扩展字形集群。   

### 10. 热点/ GC

1. JEP 351 ZGC取消提交未使用的存储器 
2. 添加了`-XXSoftMaxHeapSize`标志
3. ZGC支持的最大堆大小从4TB增加到16TB
    
### 11. 安全库/ java.security

1. 该com.sun.security.crl.readtimeout系统属性设置为CRL检索的最大读取超时，单位为秒。如果尚未设置该属性，或者其值为负，则将其设置为默认值15秒。值0表示无限超时。
2. 新的keytool -showinfo -tls用于显示TLS配置信息的命令keytool -showinfo -tls添加了一个显示TLS配置信息的新命令。
3. SunMSCAPI提供程序现在支持以下一代加密（CNG）格式读取私钥。这意味着CNG格式的RSA和EC密钥可从Windows密钥库加载，例如“Windows-MY”。与EC（签名算法SHA1withECDSA，SHA256withECDSA等等）也支持。

### 12. 删除功能

&emsp;&emsp;删除的部分功能：   

1. 核心库`/java.net`中，不再支持`Pre-JDK 1.4` SocketImpl实现`java.net.SocketImpl`此版本已删除对为JavaSE1.3及更早版本编译的自定义实现的支持。此更改对SocketImpl为Java SE 1.4（2002年发布）或更新版本编译的实现没有影响。
2. 核心库/java.lang中，删除运行时跟踪方法，过时的方法`traceInstructions(boolean)`，并`traceMethodCalls(boolean)`已经从删除`java.lang.Runtime`类。这些方法对许多版本都不起作用，它们的预期功能由Java虚拟机工具接口（JVMTI）提供。


---

## JDK14

### 1. Switch（最终版）

&emsp;&emsp;和之前的jdk12、13功能一样，只不过确定下来为最终版  

```java
int numLetters = switch (day) {     
        case MONDAY, FRIDAY, SUNDAY -> 6;   
        case TUESDAY -> 7;     
        case THURSDAY, SATURDAY -> 8;
        case WEDNESDAY -> 9;
};
```
### 2. 垃圾回收器（更新优化）

1. Windows的ZGC：现在可以在Windows上作为实验功能使用，要启用它，请使用JVM标志`-XX:+UnlockExperimentalVMOptions` `-XX:+UseZGC。`
2. Mac的ZGC：现在可作为macOS上的实验功能使用。要启用它，请使用JVM标志`-XX:+UnlockExperimentalVMOptions -XX:+UseZGC`。
3. 并行GC的改进：并行GC已采用与其他收集器相同的任务管理机制来调度并行任务。这可能会显着提高性能。由于这一变化，以下产品标志
已过时：`-XX:BindGCTaskThreadsToCPUs`，`-XX:UseGCTaskAffinity`，和`-XX:GCTaskTimeStampEntries`。 
4. G1 NUMA感知内存分配：现在尝试跨垃圾收集在年轻一代的同一NUMA节点上分配并保留对象。这类似于并行GC NUMA意识。G1尝试使用
严格的交错在所有可用的NUMA节点上均匀分配Humongous和Old区域。从年轻一代复制到老一代的对象的放置是随机的。这些新的NUMA感知
内存分配试探法通过使用`-XX：+UseNUNMA`命令行选项自动启用。

### 3. Record（预览功能）

```java
@Data
@AllArgsConstructor
class Group {
    // 组名
    private String name;
    // 人数
    private int nums;
}
```

&emsp;&emsp;使用它可以替代构造器、equal方法、toString方法，hashCode方法  

```Java
Point（String name,int nums）{}
```
&emsp;&emsp;Java语言中一种新型的类型声明。像枚举一样enum， record是类的受限形式。它声明其表示形式，并提交与该表示形式匹配的API。记录放弃了类通常享有的自由：将API与表示分离的能力。作为回报，记录获得了很大程度的简洁性。  

### 4. 货币格式（优化）

&emsp;&emsp;可以通过 `NumberFormat.getCurrencyInstance(Locale`)使用“ u-cf-account” Unicode区域设置扩展名来获得具有记帐样式的货币格式实例，其中金额在某些区域设置中用括号表示，例如，`Locale.US`,它将格式化为($3.27)而不是-$3.27。    

&emsp;&emsp;而之前的版本是前边结果为负数。    

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcYejzqdZl5amjJwxfin*bZKrydmMHTWxRcerDL4s939RS147D383mR7QX2sgjGpuYun8vSGcP62aJwO3L7My*6Q!/b&bo=nAU.AZwFPgEDGTw!&rf=viewer_4&t=5)

### 5. NIO的Channel通道

&emsp;&emsp;阐明`ReadableByteChannel.read()`的规范和规格`DatagramChannel.receive()`,`FileChannel.read(ByteBuffer,long)`,`ReadableByteChannel.read()`,`ScatteringByteChannel.read()`方法已经在此版本已经更新到指定的`IllegalArgumentException`,如果（任何）缓冲区参数（S）是只读的抛出。       

### 8. 删除功能

1. CMS垃圾收集器已被删除。`-XX:UseConcMarkSweepGC`和别名`-Xconcgc，-Xnoconcgc`以及所有CMS特定选项（太多，无法列出）都已废弃。
2. 删除了安全库`java.security.acl API`

### 7. instanceof的模式匹配（预览版）

&emsp;&emsp;提供模式匹配来 增强Java编程语言`instanceof`  

```java
if (obj instanceof String s) { 
    // can use s here 
    } else {
     // can't use s here 
}
```

### 8. 弃用功能

&emsp;&emsp;线程：   

&emsp;&emsp;不建议使用线程挂起、删除，下面的方法中涉及的线程挂起Thread,并且Thread已在本版本中晚期弃用,`Thread.suspend()`,`Thread.resume()`,`ThreadGroup.suspend()`,`ThreadGroup.resume()`,`ThreadGroup.allowThreadSuspension(boolean)`这些方法将在将来的版本中删除。 
 
&emsp;&emsp;垃圾回收器：     

&emsp;&emsp;弃用`ParallelScavenge + SerialOld` GC组合，任何`UseParallelOldGC`用于启用此垃圾回收算法组合的命令行选项的使用，都会引
起弃用警告。嵌入式替换是通过`-XX:+UseParallelGC`在命令行上使用`ParallelScavenge` + `ParallelOld`垃圾收集器。    

&emsp;&emsp;椭圆曲线:      

&emsp;&emsp;`security-libs / javax.crypto`,已过时的旧椭圆曲线去除。  

### 9. 注意点

&emsp;&emsp;线程中断状态始终可用：   

&emsp;&emsp;该规范`java.lang.Thread::interrupt`允许实现仅跟踪活动线程的中断状态，并且以前就是这种情况。从此版本开始，a的中断状态
Thread始终可用，并且如果您在线程t启动之前或终止之后中断线程，查询`t.isInterrupted()`将返回`true`。  

&emsp;&emsp;**`DatagramSocket.send`和`MulticastSocket.send`抛出`IllegalArgumentException`当套接字没有连接和数据包不包含地址：**  

&emsp;&emsp;如果套接字未连接且没有套接字地址,send则由DatagramSocket和定义的方法MulticastSocket已更改为抛出。    

&emsp;&emsp;**`MulticastSocket  getOption（IP_MULTICAST_IF）`未设置传出接口时返回`null`：**    

&emsp;&emsp;该`MulticastSocket`方法getOption已更改为符合中描述的行为`StanndardSocketOptions.IP_MULTCAST_IF`。如果没有设置接口，`MulticastSocket.getOption(StanndardSocketOptions.IP_MULTCAST_IF)`现在返回null。    

&emsp;&emsp;**`MulticastSocket上getOption /`的`SetOption`为`IP_MULTICAST_LOOP`个符合随着`StandardSocketOptions.IP_MULTICAST_LOOP`规范的行为：**     

&emsp;&emsp;该`MulticastSocket`方法`getOption`和`setOption`已更改以符合所描述的行为`StandardSocketOptions.IP_MULTICAST_LOOP`规范,
`MulticastSocket.getOption(StanndardSocketOptions.IP_MULTCAST_IF)`现在，如果启用了环回模式，则返回`true`。  

&emsp;&emsp;设置`MulticastSocket.getOption(StanndardSocketOptions.IP_MULTCAST_IF)`启用回送模式。     

---

## JDK15

&emsp;&emsp;预览版：该功能在当前版本可以使用，如果效果不是很好的话，可能以后的其他版本就会删去该功能。   

&emsp;&emsp;最终版：该功能在之前版本效果很好，之后的每个版本中都会存在该功能。    

&emsp;&emsp;Java 5中的泛型，Java 8中的Lambda和Java 9中的模块。以下是各个JDK版本升级的JEP数量   

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcSvtBPlh5GfESY8gwNDib4neHPYsOEHoaVDzRz.ZBuvThtLgDf0u5jarTttBaIbq2o50VUxMQaMFOJ7hER2H.VQ!/b&bo=9gJpAfYCaQEDGTw!&rf=viewer_4&t=5)

### 1. 在CharSequence中添加了isEmpty默认方法

&emsp;&emsp;定义isEmpty用于测试字符序列是否为空的默认方法，`CharSequence::isEmpty`可以用作方法参考。实现的类`java.lang.CharSequence`和定义isEmpty方法的另一个接口应注意这一点，因为可能需要对其进行修改以覆盖isEmpty方法。

![](http://m.qpic.cn/psc?/V50t7e4N095HH74Gh0Mx0bAyha1mXTkf/45NBuzDIW489QBoVep5mcSvtBPlh5GfESY8gwNDib4lpOZgtbDOTQWihkyIWMwvC*E6kj4HrcjTsr3fAuxyWMJgu2ueMPV3i.XfhO8mvkII!/b&bo=wAMoAcADKAEDGTw!&rf=viewer_4&t=5)

### 2. 支持Unicode 13.0

&emsp;&emsp;此版本将Unicode支持升级到13.0，其中包括:`java.lang.Character13.0`水平，这增加了13.0 5930个字符，总共143859个字符类支持Unicode字符数据库。这些增加的内容包括4个新脚本，总共154个脚本，以及55个新表情符号字符。    

### 3. TreeMap新方法

TreeMap重新实现压倒性一切的如：`putIfAbsent`、`computeIfAbsent`、`computeIfPresent`、`compute`、`merage`  

举例说明putIfAbsent等价于如下：  

```java
default V putIfAbsent​(K key, V value)
 V v = map.get(key);
 if (v == null){
     v = map.put(key, value);
}

return v;
```
### 4. 文本块（最终版）

优势：简化了编写Java程序的任务，同时避免了常见情况下的转义序列；增强Java程序中表示用非Java语言编写的代码的字符串的可读性。

```java
String html = """ <html> <body> <p>Hello, world</p> </body> </html> """;
```

```java
String query = """    
                SELECT `EMP_ID`, `LAST_NAME` FROM `EMPLOYEE_TB`
                WHERE `CITY` = 'INDIANAPOLIS'
                ORDER BY `EMP_ID`, `LAST_NAME`;
               """;
```

### 5. 隐藏类

基于JVM构建的许多语言实现都依靠动态类生成来提高灵活性和效率。  

详情可见--> [JEP371](https://openjdk.java.net/jeps/371)  

### 6. GC回收期

淘汰-XXUseAdaptiveGDBoundary  

### 7. 改进的序列化处理，IO

&emsp;&emsp;使用`java.io.ObjectInputStream.setObjectInputFilter`方法设置序列化过滤器时，必须在从流中读取任何对象之前调用它。如果调用readObject或方法readUnshared，则该setObjectInputFilter方法将引发`IllegalStateException`。  

### 8. 优化空子字符串处理，String.substring

在某些情况下，String.substring返回“”，但在子字符串长度为零时，在所有情况下都可以进行改进。

之前逻辑是返回“”，需要增加一个新的地址，而目前为null，如下  

```java
public static String stripLeading(byte[] value) {
  int left = indexOfNonWhitespace(value);
  if (left == value.length) {
    return "";
  }
  return (left != 0) ? newString(value, left, value.length - left) : null;
}
```

### 9. 支持货币分组分隔符

&emsp;&emsp;`DecimalFormat / DecimalFormatSymbols`类现在可以处理货币值的分组分隔符。例如，在奥地利（de-AT语言环境）中使用的德语语言的货币分组分隔符为“。”，而在其他德语语言环境中的货币分组分隔符为“。”。    

### 10. time用默认值覆盖本地化值

&emsp;&emsp;`java.time.format.DateTimeFormatter.localizedBy(Locale)`方法现在采用默认的语言环境值，例如Chronology和/或DecimalStyle指定的语言环境参数。  

例如，在先前的JDK版本中：  

```java
jshell> DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
    .localizedBy(Locale.forLanguageTag("fa"))
    .format(LocalDate.now())
$3 ==> "جمعه 1 مهٔ 2020"
```

数字是阿拉伯文（西文）数字，在JDK 15中： 

```java
jshell> DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
    .localizedBy(Locale.forLanguageTag("fa"))
    .format(LocalDate.now())
$3 ==> "جمعه ۱ مهٔ ۲۰۲۰"
```

这些数字使用扩展阿拉伯语-印度数字，因为它是波斯语区域设置的默认编号系统。  

### 11. time ValueRange.of

&emsp;&emsp;`ValueRange.of（long min，long maxSmallest，long maxLargest）`，如果最小值大于最小最大值，则将引发异常。但是，仅当最小值大于最大最大值时才会发生例外。

12、性能改进InflaterOutputStream.write

1. `InflaterOutputStream(OutputStream out, Inflater infl, int bufLen)`允许指定要使用的解压缩器和缓冲区大小。
2. `InflaterOutputStream.write(byte[] b, int off, int len)`正在使用最大512字节的缓冲区大小写入数据。
3. 从JDK 15开始，通过`InflaterOutputStream(OutputStream out, Inflater infl,int bufLen)`所指定的缓冲区大小将在对的调用中使用`InflaterOutputStream.write(byte[] b,int off, int len)`。如果在调用InflaterOutputStream 构造函数时未指定缓冲区大小，则默认为512字节。

### 13. 集合性能提升（Better Listing of Arrays）

&emsp;&emsp;复制集合的首选方法是使用“复制构造函数”。例如，要将集合复制到新的ArrayList中，可以编写`new ArrayList<>(collection)`。在某些情况下，可能会制作其他临时副本，如果要复制的集合非常大，则应用程序应（意识到/监视）制作副本所需的大量资源。  

### 14. GC：G1优化

针对G1堆区域大小的改进  

&emsp;&emsp;默认的堆区域大小计算已更改为默认情况下返回较大的区域。计算仍以2048个区域为目标，但是两个方面发生了变化，这些更改提高了启动和运行时性能    
1. 仅考虑最大堆大小。旧的计算还考虑了初始堆大小，但是当未设置堆大小时，这可能会产生意外的行为。
2. 区域大小四舍五入到最接近的2的幂，而不是减小。在最大堆大小不是2的幂的情况下，这将返回更大的区域大小。

### 15. ZGC一种可扩展低延迟垃圾收集器

&emsp;&emsp;Z垃圾收集器（ZGC）现在可以在生产中使用，不再标记为实验功能。通过使用-XX:+UseZGC命令行选项启用ZGC   

### 16. 模式匹配的instanceof（第二预览版）

提供模式匹配来 增强Java编程语言instanceof  

```java
if (obj instanceof String s) { 
    // can use s here 
    } else {
     // can't use s here 
}
```

### 17. Record（第二预览版）

```java
@Data
@AllArgsConstructor
class Group {
    // 组名
    private String name;
    // 人数
    private int nums;
}
```

使用它可以替代构造器、equal方法、toString方法，hashCode方法  

```java
Point（String name,int nums）{}
```

&emsp;&emsp;Java语言中一种新型的类型声明。像枚举一样enum， record是类的受限形式。声明其表示形式，并提交与该表示形式匹配的API。记录放弃了类通常享有的自由：将API与表示分离的能力。作为回报，记录获得了很大程度的简洁性。  

### 18. Sealed Classes（第一预览版）

&emsp;&emsp;通过密封的类和接口增强Java编程语言。密封的类和接口限制可以扩展或实现它们的其他类或接口。  

详细参考--》[起因](https://cr.openjdk.java.net/~briangoetz/amber/datum.html)

---

## JDK16
# Java方法与类

[TOC]

## java.lang

### System

#### arraycopy()

`java.lang.System`  

```java
public static native void arraycopy(Object src,  int srcPos, 
                                    Object dest, int destPos, 
                                    int length)
```

将src数组指定长度元素拷贝dest数组中,其中两个数组的起始都是指定的    

* `Object src`被拷贝的数组 (多态 向上转型)
* `int srcPos`被拷贝数组元素的起始下标
* `Object dest`目标数组    (多态 向上转型)
* `int destPos`目标数组的起始下标
* `length` 被拷贝元素的长度

### Object类

#### toString

* toString 是 Object 类中的一个方法，返回值是对象的地址
* 可以用方法覆盖的方式重写方法

```java
public class Test 
	public static void main(String[] args) {
		Student s = new Student();
		System.out.println(s);
	}
}
class Student {
	public String toString() {
		return "你调用了 1 个String对象"; // 这样返回值就不会是一个看不懂的东西了
	}
}
```

#### equals

* Object中的一个方法
* 判断两个对象是否一样
* 方法中比较的是地址

```java
 // Object中的equals方法
public boolean equals(Object obj) {
	return (this == obj);
}

//Object中的equals方法比较的是内存地址.
//在现实的业务逻辑当中，不应该比较内存地址，应该比较内容。
//所以Object中的equals方法也要重写。
//根据需求规定重写equals方法，

public boolean equals(Object obj){
	
    if(obj == this) {
        return true;
    }
    
    if(obj == null && !(obj instanceof Star)) {
        return false;
    }
    
    Star s = (Star)obj;
    if( this.id == s.id ) {
        return true;
    }
    return false;

}
```

* 关于java语言中如何比较两个字符串是否一致.	
* 在java中比较两个字符串是否一致，不能用“==”
* 只能调用String类的equals方法.

```java
public class Test{
	public static void main(String[] args){
		
		String s1 = new String("ABC");
		String s2 = new String("ABC");
		
		System.out.println(s1==s2); //false
		
		//String已经重写了Object中的equals方法，比较的是内容。
		System.out.println(s1.equals(s2)); //true
		
	}
}
```

#### finalize

* finalize方法每个java对象都有
* finalize方法不需要程序员去调用，由系统自动调用java对象,如果没有更多的引用指向它，则该java对象成为垃圾数据，等待垃圾回收器的回收，垃圾回收器在回收这个java对象之前会自动调用该对象的finalize方法。
* finalize方法是该对象马上就要被回收了，例如：需要释放资源，则可以在该方法中释放。

```java
public class Test01{
	
	public static void main(String[] args){
		
		Person p1 = new Person();
		
		p1 = null; //没有引用再指向它.等待回收.
		
		//程序员只能“建议”垃圾回收器回收垃圾.
		System.gc();
	}
	
}
class Person{
	
	//重写Object中的finalize方法.
	public void finalize() throws Throwable { 
		
		System.out.println(this + "马上就要被回收了！");
		
		//让引用再次重新指向该对象，该对象不是垃圾数据，不会被垃圾回收器回收！
		//Person p = this;
	}
}
```

#### hashCode

* hashCode方法返回的是该对象的哈希码值
* java对象的内存地址经过哈希算法得出的int类型的数值.

### String

- 字符串一经声明是不能改变的
- String在底层是一个byte数组,并且是用`private final`修饰的
- 如果要大量的连接字符串可以使用`StringBuffer`和`StringBuilder`
    - `StringBuffer`中的方法都有`synchronized`关键字修饰, 表示`StringBuffer`在多线程下运行是安全的
    - `StringBuilder`反之

```java
public class StringTest02 {
	public static void main(String[] args) {

		StringBuffer sb = new StringBuffer(100);

		//sb = "123";

		sb.append("456");
		sb.append(false);

		System.out.println(sb);
		
	}
}
```

#### charAt()

`public char charAt(int index)`

返回字符串指定位置`int index`的值

#### compareTo()

`public int compareTo(String anotherString)`

比较两个字符串的大小, 由高位向低位比较

#### contains()

`public boolean contains(CharSequence s)`

查找字符串中的字符`CharSequence s`, 如存在,则返回true

#### concat()

`public String concat(String str)`

将指定字符串`String str`连接到此字符串的结尾。 

#### equalsIgnoreCase()

`public boolean equalsIgnoreCase(String anotherString)`

比较两个字符串是否相同, **忽略大小写**  

#### getBytes()

`public byte[] getBytes(String charsetName) throws UnsupportedEncodingException`

将字符串`String charsetName`转换成byte数组返回

#### isEmpty()

`public boolean isEmpty()`

判断字符串是不是**空字符串**, 如果是**null**, 则返回`NullPointerException`

#### length()

`public int length()`

返回字符串的长度

#### replace()

`public String replace(CharSequence target, CharSequence replacement)`

将字符串中的部分字符串`CharSequence target`替换成另一字符串`CharSequence replacement`

#### endsWith()

`public boolean endsWith(String suffix)`

测试此字符串是否以指定的`String suffix`后缀结束。

#### startsWith()

`public boolean startsWith(String prefix)`

测试此字符串是否以指定的`String prefix`前缀开始。 

#### indexOf()

`public int indexOf(int ch)`

返回指定字符`int ch`在此字符串中第一次出现处的索引  

#### substring()

`public String substring(int beginIndex)`

从指定位置`int beginIndex`开始至末尾, 返回一个新的字符串  

#### toCharArray()

`public char[] toCharArray()`

将此字符串转换为一个新的字符数组**返回**。 

#### tolower

**toLowerCase**

`public String toLowerCase()`

使用默认语言环境的规则将此 String 中的所有字符都转换为小写。

**toUpperCase**

`public String toUpperCase(Locale locale)`

将字符串中的所有字符都转成大写

#### split()

`public String[] split(String regex)`

以指定的正则`String regex`将字符串拆分成字符数组

#### trim()

`public String trim()`

去除字符串前面和后面的空白返回

#### valueOf()

`public static String valueOf(boolean b)`

将非字符串转换成字符串

### Integer

#### parseInt()

`public static int parseInt(String s) throws NumberFormatException`

将数字字符串转换成int类型  
如果是非数字字符串会发生`NumberFormatException`数字格式异常

***

## java.util

### Arrays工具类

#### sort()

`java.util.Arrays`

```java
public static void sort()
```

将数组按照升序排序

#### binarySearch()

`java.util.Arrays`

```java
public static int binarySearch(int[] a,
                               int key)
```

二分法查找


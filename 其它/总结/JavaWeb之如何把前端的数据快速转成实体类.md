# JavaWeb之如何把请求数据转成实体类

* 自己写个工具类加入下面两个静态方法
* 自定一个注解类DateTimeFormatting
* 调用方式`User user = util.ObjectFromMap(req.getParameterMap(), User.class)`
* 暂时支持8种基本数据类型对应包装类、`BigDecimal`、`java.util.Date`，可应对大多情况，如有需求，可以自己添加
* 暂不支持数组

```java
public static <T> T ObjectFromMap(Map<String,String[]> map, Class<T> type) {
    try {
        // 创建返回对象，是要转换的对象
        T t = type.getConstructor().newInstance();
        
        // 遍历map集合
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            // 获得属性名
            String key = entry.getKey();
            // 获得属性值
            String[] values = entry.getValue();

            // 自定义方法
            // 如果类中没有这个属性跳出[本次]循环
            if (!checkObjectHasField(type,key)) {
                continue;
            }
            
            // 获取属性描述对象，如果类中没有这个属性，报异常，所以要有上面那个操作
            PropertyDescriptor pd = new PropertyDescriptor(key,type);
            // 获取属性的set方法，所以对应属性要有set方法
            Method method = pd.getWriteMethod();

            // 暂时不支持数组
            String value = values[0];

            // req获取的值都是字符串，所以在对非字符串类型要转换
            // 获取属性的类型
            String propertyType = pd.getPropertyType().getName();
            // 如果字符串长度为0 说明空串，valueOf会转换失败
            if(value.length() > 0) {
                switch (propertyType) {
                    case "java.lang.Byte":
                        method.invoke(t, Byte.valueOf(value));
                        break;
                    case "java.lang.Integer":
                        method.invoke(t, Integer.valueOf(value));
                        break;
                    case "java.lang.Short":
                        method.invoke(t, Short.valueOf(value));
                        break;
                    case "java.lang.Long":
                        method.invoke(t, Long.valueOf(value));
                        break;
                    case "java.lang.Float":
                        method.invoke(t, Float.valueOf(value));
                        break;
                    case "java.lang.Double":
                        method.invoke(t, Double.valueOf(value));
                        break;
                    case "java.lang.Boolean":
                        method.invoke(t, Boolean.valueOf(value));
                        break;
                    case "java.lang.Character":
                        method.invoke(t, value.charAt(0));
                        break;
                    case "java.lang.String":
                        method.invoke(t, value);
                        break;
                    case "java.math.BigDecimal":
                        method.invoke(t, new BigDecimal(value));
                        break;
                    case "java.util.Date":
                        // 默认使用格式化日期的格式
                        String format = "yyyy-MM-dd";
                        // 获取属性对象
                        Field field = type.getDeclaredField(key);
                        // 通过属性对象获取 DateTimeFormatting 注解
                        // 自定义注解
                        DateTimeFormatting anno = field.getAnnotation(DateTimeFormatting.class);
                        // 检查注解存在否
                        if (anno != null) {
                            // 存在使用注解的 value 来格式化时间
                            // 不存在使用上面的默认格式
                            format = anno.value();
                        }
                        method.invoke(t, new SimpleDateFormat(format).parse(value));
                        break;
                    default:
                        break;
                }
            } else {
                // 如果字符串是空串，那么转成的bean对象对应属性也给个空串
                // 如果想给null可以去掉
                if (propertyType.equals("java.lang.String")) {
                    method.invoke(t, value);
                }
            }
        }
        return t;
    } catch (InstantiationException | IllegalAccessException
            | InvocationTargetException | NoSuchMethodException
            | IntrospectionException | NoSuchFieldException e
            | ParseException e) {
        e.printStackTrace();
    }
    return null;
}
```

```java
/**
 * 校验对象中是否有指定属性
 * @param type 对象的类型
 * @param fieldName 属性名
 * @return 是/否
 */
public static boolean checkObjectHasField(Class type,String fieldName) {
    Field[] fields = type.getDeclaredFields();
    for (Field field : fields) {
        if (field.getName().equals(fieldName)) {
            return true;
        }
    }
    return false;
}
```


```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeFormatting {
    String value() default "yyyy-MM-dd";
}
```
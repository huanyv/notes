# Java工具类

## 菜单列表转树形菜单

```java
public static List<MenuNode> toTree(List<MenuNode> treeList, Integer root) {
    List<MenuNode> children = new ArrayList<MenuNode>();
    //1、遍历list,给root找孩子
    for(MenuNode menu : treeList) {
        //2、判断父节点是否为root
        if(root.equals(menu.getPid())) {
            children.add(menu);
        }
    }
    //3、遍历孩子节点list
    for(MenuNode menu : children) {
        List<MenuNode> menuNodes = toTree(treeList, menu.getId());
        menu.setChild(menuNodes);
    }
    return children;
}
```


```java
public static List<MenuNode> toTree(List<MenuNode> treeList, Long root) {
    List<MenuNode> children = treeList.stream()
            .filter(menuNode -> root.equals(menuNode.getPid())) // 找孩子
            .map(menuNode -> {
                menuNode.setChild(toTree(treeList, menuNode.getId()));
                return menuNode;
            }) // 给孩子找孩子
            .collect(Collectors.toList());

    return children;
}
```

## 扫描包

### 1

* 如果继承的类没有引入依赖会报错

```java
public class ClassUtil {

    public static Set<Class<?>> getClasses(String pack) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    System.out.println("jar类型的扫描");
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        findClassesInPackageByJar(packageName, entries, packageDirName, recursive, classes);
                    } catch (IOException e) {
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private static void findClassesInPackageByJar(String packageName, Enumeration<JarEntry> entries, String packageDirName, final boolean recursive, Set<Class<?>> classes) {
        // 同样的进行循环迭代
        while (entries.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }
            // 如果前半部分和定义的包名相同
            if (name.startsWith(packageDirName)) {
                int idx = name.lastIndexOf('/');
                // 如果以"/"结尾 是一个包
                if (idx != -1) {
                    // 获取包名 把"/"替换成"."
                    packageName = name.substring(0, idx).replace('/', '.');
                }
                // 如果可以迭代下去 并且是一个包
                if ((idx != -1) || recursive) {
                    // 如果是一个.class文件 而且不是目录
                    if (name.endsWith(".class") && !entry.isDirectory()) {
                        // 去掉后面的".class" 获取真正的类名
                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                        try {
                            // 添加到classes
                            classes.add(Class.forName(packageName + '.' + className));
                        } catch (ClassNotFoundException e) {
                            // .error("添加用户自定义视图类错误 找不到此类的.class文件");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static void findClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            @Override
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    // classes.add(Class.forName(packageName + '.' +
                    // className));
                    // 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    e.printStackTrace();
                }
            }
        }
    }

}
```

### 2

* 面向对象的方式

```java
/**
 * PathScanner 扫描 @Path 注解，实现路由扫描功能
 */
public class ClassScanner {
 
	// 存放已被扫描过的 controller，避免被多次扫描
	private static final Set<Class<?>> scannedClass = new HashSet<>();
 
	// 扫描的基础 package，只扫描该包及其子包之下的类
	private String basePackage;
 
	private ClassLoader classLoader;
 
	public ClassScanner(String basePackage) {
		if (basePackage == null || basePackage.equals("")) {
			throw new IllegalArgumentException("basePackage can not be blank");
		}
 
		String bp = basePackage.replace('.', '/');
		bp = bp.endsWith("/") ? bp : bp + '/'; // 添加后缀字符 '/'
		bp = bp.startsWith("/") ? bp.substring(1) : bp; // 删除前缀字符 '/'
 
		this.basePackage = bp;
	}
 
	public Set<Class<?>> scan() {
		try {
			classLoader = getClassLoader();
			List<URL> urlList = getResources();
			scanResources(urlList);
			return scannedClass;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
 
	private ClassLoader getClassLoader() {
		ClassLoader ret = Thread.currentThread().getContextClassLoader();
		return ret != null ? ret : ClassScanner.class.getClassLoader();
	}
 
	private List<URL> getResources() throws IOException {
		List<URL> ret = new ArrayList<>();
 
		// 用于去除重复
		Set<String> urlSet = new HashSet<>();
		// ClassLoader.getResources(...) 参数只支持包路径分隔符为 '/'，而不支持 '\'
		Enumeration<URL> urls = classLoader.getResources(basePackage);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
 
			String urlStr = url.toString();
			if (!urlSet.contains(urlStr)) {
				urlSet.add(urlStr);
				ret.add(url);
			}
		}
		return ret;
	}
 
	private void scanResources(List<URL> urlList) throws IOException {
		for (URL url : urlList) {
			String protocol = url.getProtocol();
			if ("jar".equals(protocol)) {
				scanJar(url);
			} else if ("file".equals(protocol)) {
				scanFile(url);
			}
		}
	}
 
	private void scanJar(URL url) throws IOException {
		URLConnection urlConn = url.openConnection();
		if (urlConn instanceof JarURLConnection) {
			JarURLConnection jarUrlConn = (JarURLConnection) urlConn;
			try (JarFile jarFile = jarUrlConn.getJarFile()) {
				Enumeration<JarEntry> jarFileEntries = jarFile.entries();
				while (jarFileEntries.hasMoreElements()) {
					JarEntry jarEntry = jarFileEntries.nextElement();
					String en = jarEntry.getName();
					// 只扫描 basePackage 之下的类
					if (en.endsWith(".class") && en.startsWith(basePackage)) {
						// JarEntry.getName() 返回值中的路径分隔符在所有操作系统下都是 '/'
						en = en.substring(0, en.length() - 6).replace('/', '.');
						scanClass(en);
					}
				}
			}
		}
	}
 
	private void scanFile(URL url) {
		String path = url.getPath();
		path = decodeUrl(path);
		File file = new File(path);
		String classPath = getClassPath(file);
		scanFile(file, classPath);
	}
 
	private void scanFile(File file, String classPath) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File fi : files) {
					scanFile(fi, classPath);
				}
			}
		} else if (file.isFile()) {
			String fullName = file.getAbsolutePath();
			if (fullName != null && fullName.endsWith(".class")) {
				String className = fullName.substring(classPath.length(), fullName.length() - 6).replace(File.separatorChar, '.');
				scanClass(className);
			}
		}
	}
 
	private String getClassPath(File file) {
		String ret = file.getAbsolutePath();
		// 添加后缀，以便后续的 indexOf(bp) 可以正确获得下标值，因为 bp 确定有后缀
		if (!ret.endsWith(File.separator)) {
			ret = ret + File.separator;
		}
 
		// 将 basePackage 中的路径分隔字符转换成与 OS 相同，方便处理路径
		String bp = basePackage.replace('/', File.separatorChar);
		int index = ret.lastIndexOf(bp);
		if (index != -1) {
			ret = ret.substring(0, index);
		}
 
		return ret;
	}
 
	private void scanClass(String className) {
		// 跳过不需要被扫描的 className
		Class<?> c = loadClass(className);
		if (c != null && !scannedClass.contains(c)) {
			// 确保 class 只被扫描一次
			scannedClass.add(c);
		}
	}
 
	private Class<?> loadClass(String className) {
		try {
			return classLoader.loadClass(className);
		}
		// 此处不能 catch Exception，否则抓不到 NoClassDefFoundError，因为它是 Error 的子类
		catch (Throwable t) {
			//log.debug("PathScanner can not load the class \"" + className + "\"");
 
			/**
			 * 由于扫描是一种主动行为，所以 pom.xml 中的 provided 依赖会在此被 loadClass， 从而抛出
			 * NoClassDefFoundError、UnsupportedClassVersionError、 ClassNotFoundException
			 * 异常。return null 跳过这些 class 不处理
			 * 
			 * 如果这些异常并不是 provided 依赖的原因而引发，也会在后续实际用到它们时再次抛出异常， 所以 return null 并不会错过这些异常
			 */
			return null;
		}
	}
 
	/**
	 * 支持路径中存在空格百分号等等字符
	 */
	private String decodeUrl(String url) {
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
```



## Promise

```java
public class Promise<T> {

    private T data;

    public Promise(T data) {
        this.data = data;
    }

    public<R> Promise<R> then(Function<T, R> callback) {
        R apply = callback.apply(data);
        return new Promise<>(apply);
    }

    public<R> R then(Supplier<R> callback) {
        return callback.get();
    }

    @Override
    public String toString() {
        return "Promise{" +
                "data=" + data +
                '}';
    }
}

```


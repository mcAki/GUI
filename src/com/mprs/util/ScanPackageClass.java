package com.mprs.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Scan某个package命名空间下所有的类(源码非常类似SpringAutoScan)
 * 
 * @author Seraph
 */
public class ScanPackageClass {

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack 需要搜索的package地址　 如:com.xxx.yyy
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack) {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换          例: com.xxx.yyy 换成 com/xxx/yyy 
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			//获取给出指定包（命名空间）再当前执行线程包资源地址
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while(dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					System.err.println("file类型的扫描");
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) { // 如果是jar包文件,则走这段
					// 定义一个JarFile
					System.err.println("jar类型的扫描");
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while(entries.hasMoreElements()) {
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
										String className = name.substring(packageName.length() + 1,
											name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											// log
											// .error("添加用户自定义视图类错误 找不到此类的.class文件");
											e.printStackTrace();
										}
									}
								}
							}
						}
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

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName 包package形式路径    如:com.xxx.yyy
	 * @param packagePath 包物路径    如:/D:/project/bin/com/xxx/yyy
	 * @param recursive 是否重复迭代
	 * @param classes 靠这个返回类的列表
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
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
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则用递归继续扫描
			if (file.isDirectory()) {
				//递归子目录搜索
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
					file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					//根据.class文件对应1个类的原则，从文件名获得类名并用反射方法loadClass提取实体类
					Class<?> extractedClass = Thread.currentThread().getContextClassLoader().loadClass(
						packageName + '.' + className);
					// 把提取的类添加到Set集合中去
					classes.add(extractedClass);
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
				} catch (ClassNotFoundException e) {
					// log.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}

	
	/*
Java 反射 class与Class.forName()与ClassLoader.loadClass的区别
 类名.class是Class对象的句柄，每个被加载的类，在jvm中都会有一个Class对象与之相对应，如果要创建新的对象，直接使用Class对象的局部class.forName就可以了，不需要用new       类名。 
在java中，每个class都有一个相应的Class对象，当编写好一个类，编译完成后，在生成的.class文件中，就产生一个class对象，用来表示这个类的类型信息。获得Class实例的三中方式： 
1.利用对象调用getClass()方法获取该对象的Class实例 
2.使用Class的静态方法forName()，用类的名字获取一个Class实例 
3.运用.calss的方式获取Class实例，对基本数据类型的封装类，还可以采用.TYPE来获取对应的基本数据类型的Class实例 
calss ClassTest 
{ 
                public static void main(String[] args) 
                { 
                        
                        //利用对象调用getClass()方法获取该对象的Class实例 
                        Point pt=new Point();                
                        Class c1=pt.getClass(); 
                        System.out.println(c1.getName());                        //结果:Point 
                
                        //使用Class的静态方法forName()，用类的名字获取一个Class实例 
                        try 
                        { 
                                Class c2=Class.forName("Point"); 
                                System.out.println(c2.getName());                //结果:Point 
                        } 
                        catch(Exception e) 
                        { 
                                e.printStackTrace(); 
                        } 
                        //运用.calss的方式获取Class实例(类) 
                        Class c3=Point.calss; 
                        System.out.println(c3.getName());                        //结果:Point 
                        //运用.calss的方式获取Class实例(基本类型) 
                        Class c4=int.calss; 
                        System.out.println(c4.getName());                        //结果:int 
                        //运用.calss的方式获取Class实例(基本数据类型的封装类) 
                        Class c5=Integer.TYPE; 
                        System.out.println(c5.getName());                        //结果:int 
                
                        Class c6=Integer.class; 
                        System.out.println(c6.getName());                        //结果:java.lang.Integer 
                       
                
                        //以下结果是：          before new Point() 
                                        loading point 
                                        after new Point() 
                                        loading Line        
                        //当new Point()的时候加载这个类，用forName构造实例的时候也加载该类。 
                        System.out.println("before new Point()"); 
                        new Point(); 
                        System.out.println("after new Point()"); 
                        try 
                        { 
                                Class.forName("Line"); 
                        }catch(Exception e) 
                        { 
                                e.printStackTrace(); 
                        } 
                
                } 
} 
class Point() 
{ 
                static 
                { 
                        System.out.println("loading point"); 
                } 
                int x,y; 
} 
class Line 
{ 
                static 
                { 
                        System.out.println("loading Line"); 
                } 
} 
在运行期间，如果我们要产生某个类的对象，java虚拟机会检测该类型的Class对象是否已被加载。如果没有加载，java虚拟机会根据类的名称找到.class文件并加载它。一旦某个类型的Class对象已经被加载到内存，就可以用它来产生该类型的所有对象。 
newInstance()调用内中缺省的构造方法。 
newInstance()调用类中缺省的构造方法，如果要实例的对象中有了自己定义的构造方法（除重写的和默认构造方法相同的构造方法外） 
创建此 Class 对象所表示的类的一个新实例 
class ClassTest 
{ 
               public static void main(String[] args) 
               { 
                       if(args.length!=1) 
                       { 
                               System.out.println("args.length!=1"); 
                               return; 
                       } 
                       try 
                       { 
                               Class c=Class.forName(args[0]); 
                               Point pt=(Point)c.newInstance(); 
                               pt.output(); 
                       }catch(Exception e) 
                       { 
                               e.printStackTrace(); 
                       } 
               } 
} 
class Point 
{ 
               int x; 
               int y; 
               static 
               { 
                       System.out.println("Loading point"); 
               } 
               void output() 
               { 
                       System.out.println("x="+x+",y="+y); 
               } 
} 

 
 
	 */
}

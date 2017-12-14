package com.ages.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GenLuaPo {
	
	private final static String poPath = "com/ages/util/po";
	
	public void gen(StringBuffer buffer , File f) throws IOException{
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for (File file : files) {
				try {
					if(file.isFile()){
						String name = file.getName();
						String parent = file.getParent();
						String classPath = parent.substring(parent.indexOf("com"), parent.length()).replace("\\", ".") + ".";
						Class<?> c = Class.forName(classPath + name.substring(0, name.indexOf(".")));
						buffer.append(c.getSimpleName() + " = {");
						Field[] fields = c.getDeclaredFields();
						for (int i = 0; i < fields.length; i++) {
							String fieldName = fields[i].getName();
							Method method = c.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
							String value = method.invoke(c.newInstance()).toString();
							buffer.append(fieldName + "=" + value + (i == fields.length - 1 ? "}\n" : ","));
						}
					}
					if(file.isDirectory()){
						gen(buffer, file);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringBuffer buffer = new StringBuffer();
		String path =GenLuaPo.class.getClassLoader().getResource("").getPath() + poPath;
		File f = new File(path);
		new GenLuaPo().gen(buffer,f);
		System.out.println(buffer.toString());
	}
}

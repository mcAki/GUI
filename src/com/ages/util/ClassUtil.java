package com.ages.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
	/**
	 * 复制已经派生的]
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static Object copy(Object object) throws Exception {
		// 获得对象的类型
		Class<?> classType = object.getClass();
		System.out.println("Class:" + classType.getName());

		// 通过默认构造方法创建一个新的对象
		Object objectCopy = classType.getConstructor(new Class[] {})
				.newInstance(new Object[] {});

		// 获得对象的所有属性
		Field fields[] = classType.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			String fieldName = field.getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			// 获得和属性对应的getXXX()方法的名字
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			// 获得和属性对应的setXXX()方法的名字
			String setMethodName = "set" + firstLetter + fieldName.substring(1);

			// 获得和属性对应的getXXX()方法
			Method getMethod = classType.getMethod(getMethodName,
					new Class[] {});
			// 获得和属性对应的setXXX()方法
			Method setMethod = classType.getMethod(setMethodName,
					new Class[] { field.getType() });

			// 调用原对象的getXXX()方法
			Object value = getMethod.invoke(object, new Object[] {});
			System.out.println(fieldName + ":" + value);
			// 调用拷贝对象的setXXX()方法
			setMethod.invoke(objectCopy, new Object[] { value });
		}
		return objectCopy;
	}
	
	public static Object create(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static Object create(String className,Object...params) {
		if(params==null || params.length==0){
			return create(className);
		}
		Class<?>[] paramTypes =null;
		try {
			paramTypes = new Class<?>[params.length];
			for(int i=0;i<params.length;i++) {
//				System.out.println("params["+i+"]:"+params[i]);
				paramTypes[i] = params[i].getClass();
			}
			className =className.trim();
			return Class.forName(className).getConstructor(paramTypes).newInstance(params);
		} catch (Exception e) {
			System.out.println("**********error className:"+className+",params:"+params);
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			try {
				paramTypes =null;
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}

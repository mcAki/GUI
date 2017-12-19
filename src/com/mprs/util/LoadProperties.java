package com.mprs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件公用类 (建议重载使用)
 * @author Seraph.Yang
 */
public class LoadProperties {

	protected Properties configProperties;

	protected String fileName;

	InputStream inputStream;

	public LoadProperties(String fileName) throws IOException {
		reload();
	}

	public void reload() throws IOException {
		configProperties = new Properties();
		inputStream = LoadProperties.class.getResourceAsStream(fileName);
		configProperties.load(inputStream);
	}

	/**
	 * 获取一个字符串的属性值
	 * 
	 * @param propertyName
	 *            属性名
	 * @return
	 */
	public String getString(String propertyName) {
		return configProperties.getProperty(propertyName, "");
	}

	/**
	 * 获取一个字符串的属性值
	 * 
	 * @param propertyName
	 *            属性名
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public String getString(String propertyName, String defaultValue) {
		return configProperties.getProperty(propertyName, defaultValue);
	}

	/**
	 * 获取一个整型的属性值
	 * 
	 * @param propertyName
	 *            属性名
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public int getInteger(String propertyName, Integer defaultValue) {
		String str = configProperties.getProperty(propertyName, "");
		return Integer.parseInt(str, defaultValue);
	}

	/**
	 * 获取一个整型的属性值
	 * 
	 * @param propertyName
	 *            属性名
	 * @return 默认值0
	 */
	public int getInteger(String propertyName) {
		String str = configProperties.getProperty(propertyName, "");
		return Integer.parseInt(str, 0);
	}

	/**
	 * 获取一个整型的属性值
	 * 
	 * @param propertyName
	 *            属性名
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public boolean getBoolean(String propertyName, boolean defaultValue) {
		String str = configProperties.getProperty(propertyName, "").toLowerCase();

		if (str == "true") {
			return true;
		} else if (str == "false") {
			return false;
		} else if (str == "t") {
			return true;
		} else if (str == "f") {
			return false;
		} else if (str == "1") {
			return true;
		} else if (str == "0") {
			return false;
		} else if (str == "-1") {
			return true;
		} else {
			return defaultValue;
		}
	}

	/**
	 * 获取一个整型的属性值
	 * 
	 * @param propertyName
	 *            属性名
	 * @return 默认值false
	 */
	public boolean getBoolean(String propertyName) {
		return getBoolean(propertyName, false);
	}
	
	public static String loadConfig(String fileName,String propertyName){
	 	Properties properties = new Properties(); 
        InputStream in = FileTool.class.getResourceAsStream(fileName); 
        try { 
        	properties.load(in); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
		return (String)properties.get(propertyName);
	}
}

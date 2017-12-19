package com.mprs.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * 系统常用工具类
 * 
 * @author 郭熹
 * 
 */
public class SysUtil {
	public static boolean isNull(String param) {
		return (param == null || param.length() == 0 || param.trim().equals("")) ? true : false;
	}

	public static boolean isNull(Object param) {
		return (param == null || param.equals("")) ? true : false;
	}

	/**
	 * 创建Document对象
	 * 
	 * @return
	 * @throws ParserConfigurationException
	 */
	public static Document buildDocument() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		return document;
	}

	/**
	 * 把Document的数据输出到StringWriter中，通过Transformer完成
	 * 
	 * @param document
	 * @param stringwriter
	 * @throws TransformerFactoryConfigurationError
	 */
	public static StringWriter documentToStringWriter(Document document)
			throws TransformerFactoryConfigurationError {

		StringWriter stringwriter = new StringWriter();
		DOMSource domsource = new DOMSource(document);

		StreamResult streamresult = new StreamResult(stringwriter);
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerfactory.newTransformer();
			transformer.setOutputProperty("method", "xml");
			transformer.transform(domsource, streamresult);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return stringwriter;
	}

	/**
	 * 读取配置文件
	 * 
	 * @param fileName
	 * @param propertyName
	 * @return
	 */
	public static String loadConfig(String fileName, String propertyName) {
		Properties properties = new Properties();
		InputStream in = FileTool.class.getResourceAsStream(fileName);
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String) properties.get(propertyName);
	}

	/**
	 * 调用翻身API产生对象实例
	 * 
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object getInstance(String className) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Class<?> targetClass = Class.forName(className);
		return targetClass.newInstance();
	}

	/**
	 * 调试函数
	 * 
	 * @param dir
	 * @param data
	 */
	public static void debugData(byte[] data) {
		System.out.println("调试数据长度:" + data.length);
		int count = 0;
		for (int i = 0; i < data.length; i++) {
			int b = data[i];
			if (b < 0) {
				b += 256;
			}
			// 16进制如果不满2位则补零
			String hexString = Integer.toHexString(b);
			hexString = (hexString.length() == 1) ? "0" + hexString : hexString;
			System.out.print(hexString + " ");
			count++;
			if (count % 4 == 0) {
				System.out.print(" ");
			}
			if (count % 16 == 0) {
				System.out.println("\r\n");
			}
		}
		System.out.println("\r\n");
	}

	/**
	 * 反射数据库记录到JAVA对象
	 * 
	 * @param result
	 * @param modelClass
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public static Object getModel(ResultSet result, Class modelClass) {
		Object tReturn = null;
		try {
			tReturn = modelClass.newInstance();
		} catch (Exception ex) {
		}

		//Object returnValue = null;
		Field[] fields = modelClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field fiel = fields[i];
			String name = fiel.getName();
			Class type = fiel.getType();
			// System.out.println("------------"+name);
			// System.out.println("------------"+type);

			String methodName = "set" + name.substring(0, 1).toUpperCase()
								+ name.substring(1, name.length());
			try {
				Class[] param = { type };
				Method methoded = modelClass.getDeclaredMethod(methodName, param);
				Object paramValue = null;
				try {
					if (type == String.class) {
						paramValue = result.getString(name);
						if (paramValue != null) {
							paramValue = ((String) paramValue).trim();
						} else if (paramValue == null) {
							paramValue = "";
						}
					} else if (type == short.class) {
						paramValue = new Short(result.getShort(name));
					} else if (type == long.class) {
						paramValue = new Long(result.getLong(name));
					} else if (type == int.class || type == Integer.class) {
						paramValue = new Integer(result.getInt(name));
					} else if (type == float.class) {
						paramValue = new Float(result.getFloat(name));
					} else if (type == double.class) {
						paramValue = new Double(result.getDouble(name));
					} else if (type == Double.class) {
						paramValue = new Double(result.getDouble(name));
					} else if (type == java.util.Date.class) {
						String tmp = result.getDate(name).toString() + " "
										+ result.getTime(name).toString();
						if (tmp.trim() != "") {
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							paramValue = formatter.parse(tmp);
							java.util.Date de = formatter.parse(tmp);
						} else {
							paramValue = "";
						}
					} else if (type == java.sql.Date.class) {
						String tmp = result.getDate(name).toString() + " "
										+ result.getTime(name).toString();
						if (tmp.trim() != "") {
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							paramValue = new java.sql.Date(formatter.parse(tmp).getTime());
						} else {
							paramValue = "";
						}
					} else if (type == java.sql.Timestamp.class) {
						String tmp = result.getTimestamp(name).toString() + " "
										+ result.getTime(name).toString();
						if (tmp.trim() != "") {
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							paramValue = new java.sql.Timestamp(formatter.parse(tmp).getTime());
						} else {
							paramValue = "";
						}
					} else {
					}
				} catch (Exception ee) {
				}
				Object[] params = { paramValue };
				methoded.invoke(tReturn, params);
			} catch (Exception e) {
			}
		}
		return tReturn;
	}

	/**
	 * 简易写文本文件工具
	 * 
	 * @param fileFullPath
	 * @param content
	 * @param Encode
	 * @throws IOException
	 */
	public static void WriteTxtFile(String fileFullPath, String content, String Encode)
			throws IOException {
		// 写包
		File file = new File(fileFullPath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		Writer out = new OutputStreamWriter(fos, Encode);
		out.write(content);
		out.close();
		fos.flush();
		fos.close();

	}

	/**
	 * 生成文件夹
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static boolean CreatePath(String path) throws Exception {
		File dirFile = null;
		try {
			dirFile = new File(path);
			if (!(dirFile.exists()) && !(dirFile.isDirectory())) {
				boolean creadok = dirFile.mkdirs();
				if (creadok) {
					System.out.println(" ok:创建文件夹成功！ ");
				} else {
					System.out.println(" err:创建文件夹失败！ " + path);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
		return true;
	}

	/**
	 * 字符转数字（带默认值）
	 * @param number
	 * @param defValue
	 * @return
	 */
	public static int strToIntDef(String number, int defValue) {
		int len;
		try {
			len = Integer.parseInt(number);
		} catch (Exception e) {
			len = defValue;
		}
		return len;
	}
	
	/**
	 * JDK生成UUID
	 * @return
	 */
	public static String getUUID(){
		String s=UUID.randomUUID().toString();
		return s.replaceAll("-", "");
	}
	
	/**
	 * 获取remoteAddress的IP地址
	 * @param address
	 * @return
	 */
	public static String getLoginIp(String address){
		address=getAddress(address);
		String[] addresses=address.split(":");
		if (addresses.length>0) {
			return addresses[0];
		}
		return "";
	}
	
	/**
	 * 获取remoteAddress的端口号
	 * @param address
	 * @return
	 */
	public static String getLoginPort(String address){
		address=getAddress(address);
		String[] addresses=address.split(":");
		if (addresses.length>0) {
			return addresses[1];
		}
		return "";
	}
	
	/**
	 * 去除address的"/"
	 * @param address
	 * @return
	 */
	private static String getAddress(String address){
		return address.replace("/", "");
	}
}

package com.mprs.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * 配置文件读取类（把配置文件读成配置类） 
 * author Seraph.Yang
 * 使用方法:<br>
 * XmlConfigReader xmlConfigReader = new XmlConfigReader("serverConfig.xml");<br>
 * Game game = (Game) xmlConfigReader.loadConfig(Game.class);<br>
 */
public class XmlConfigReader {
	protected String fileName;
	protected InputStream stream;
	protected Element root = null;

	public XmlConfigReader(String xmlFileName) throws Exception {
		fileName = xmlFileName;

		stream = XmlConfigReader.class.getClassLoader().getResourceAsStream(fileName);
		if (stream == null) {
			throw new Exception("读取配置文件" + fileName + "错误");
		}

		DocumentBuilderFactory docfact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docb = null;
		Document doc = null;
		try {
			docb = docfact.newDocumentBuilder();
			doc = docb.parse(stream);
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		} catch (SAXException ex) {
			ex.printStackTrace();
		}

		root = doc.getDocumentElement();

	}

	@SuppressWarnings("static-access")
	public Object loadConfig(Class<?> beanClass) throws Exception {
		// 返回的反射对象
		Object config = null;
		// 临时元件
		Element element = null;
		Element classElement = null;
		//
		String classTag = beanClass.getSimpleName();

		NodeList classNode = root.getElementsByTagName(classTag);

		if (classNode.getLength() < 1) {
			throw new Exception("没有对应反射类的节点!");
		} else if (classNode.getLength() > 1) {
			throw new Exception("多于一个以上的反射类的节点!请用另外方法读取");
		}
		config = beanClass.newInstance();

		// Method[] methods = beanClass.getMethods();
		// 获取对应节点，然后读取属性值
		classElement = (Element) classNode.item(0);

		Field[] fields = beanClass.getDeclaredFields();

		// 获取所有property属性
		NodeList nodes = classElement.getChildNodes();
		// classElement.getElementsByTagName("property");
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			// Class<?> fieldType = fields[i].getType();
			// String caseFieldName = fieldName.substring(0,1).toUpperCase() +
			// fieldName.substring(1);
			// Method method = beanClass.getMethod("set" + (caseFieldName), fieldType);
			String valueString = null;

			String pName = null;
			// String pValue=null;
			for (int j = 0; j < nodes.getLength(); j++) {
				//System.out.println(nodes.item(j).getNodeType() + "__" + nodes.item(j).getNodeName());
				if (nodes.item(j).getNodeType() == element.ELEMENT_NODE) {
					// 节点
					element = (Element) nodes.item(j);
					
					if (element.getNodeName().equals("property")) {
						//默认的property属性
						pName = element.getAttribute("name");
						if (pName.equals(fieldName)) {
							valueString = element.getAttribute("value");
//							if (valueString == null || valueString.equals("")) {
//								valueString = element.getFirstChild().getNodeValue();
//							}
							break;
						}
					} else {
						pName = element.getNodeName();
						if (pName.equals(fieldName)) {
							valueString = element.getFirstChild().getNodeValue();
							System.out.println(pName + " : " + valueString);
							break;
						}
					}
					
				} else if (nodes.item(j).getNodeType() == element.ATTRIBUTE_NODE) {
					// 属性
				}
			}
			if (valueString != null) {
				try {
					invokeField(beanClass, config, valueString, fields[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return config;
	}

	/**
	 * 反射字段
	 * @param beanClass 目标类的Class
	 * @param config 目标类的实体
	 * @param valueString 反射值
	 * @param field 字段
	 * @throws Exception
	 */
	private void invokeField(final Class<?> beanClass, Object config, final String valueString, final Field field)
			throws Exception {
		if (valueString != null) {
			String fieldName = field.getName();
			Class<?> fieldType = field.getType();
			String caseFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method method = beanClass.getMethod("set" + (caseFieldName), fieldType);

			if (fieldType.equals(String.class)) {
				method.invoke(config, valueString);
			} else if (fieldType.equals(int.class)) {
				method.invoke(config, Integer.parseInt(valueString));
			} else if (fieldType.equals(Integer.class)) {
				method.invoke(config, Integer.parseInt(valueString));				
			} else if (fieldType.equals(long.class)) {
				method.invoke(config, Long.parseLong(valueString));
			} else if (fieldType.equals(Long.class)) {
				method.invoke(config, Long.parseLong(valueString));
			} else if (fieldType.equals(boolean.class)) {
				method.invoke(config, Boolean.parseBoolean(valueString));
			} else if (fieldType.equals(Boolean.class)) {
				method.invoke(config, Boolean.parseBoolean(valueString));
			} else if (fieldType.equals(Character.class)) {
				method.invoke(config, valueString.toCharArray()[0]);
			} else if (fieldType.equals(char.class)) {
				method.invoke(config, valueString.toCharArray()[0]);				
			} else {
				throw new NoSuchFieldException("Config没有定义的数据类型");
			}
		}
	}
}

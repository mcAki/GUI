package com.mprs.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mprs.protocol.GenerateProtocolPackage.ProtocolCollection;
import com.mprs.protocol.GenerateProtocolPackage.ProtocolSetting;
import com.mprs.protocol.GenerateProtocolPackage.Protocol_Q_A;

/**
 * 加载配置文件
 * 
 * @author 郭熹
 * 
 */
public class ConfigParser {

	protected static Log log = LogFactory.getLog(ConfigParser.class.getClass());

	// public static Map<Integer, ParseContainer> createDefault() throws
	// IOException {
	// return createFromClasspathConfig("protcol_main.xml");
	// }

	/**
	 * 加载配置文件
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Map<Integer, ProtocolContainer> readFromProtocolConfig(String path, String subPackageName, ProtocolSetting ps, int protocolPfx)
			throws Exception {
		InputStream ins = ConfigParser.class.getClassLoader().getResourceAsStream(path);
		if (ins != null) {
			if (log.isDebugEnabled()) {
				log.debug("Parsing config from classpath file " + path);
			}
			try {
				return parse(ins, subPackageName, ps, protocolPfx);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				ins.close();
			}
		} else {
			log.warn("File not found in classpath: " + path);
		}
		return null;
	}

	// /**
	// * 加载配置文件
	// *
	// * @param path
	// * @return
	// * @throws IOException
	// */
	// public static PackageSetting createFromPackageConfig(String path) throws IOException {
	// InputStream ins = ConfigParser.class.getClassLoader().getResourceAsStream(path);
	// if (ins != null) {
	// if (log.isDebugEnabled()) {
	// log.debug("Parsing config from classpath file " + path);
	// }
	// try {
	// return parseSetting(ins);
	// } finally {
	// ins.close();
	// }
	// } else {
	// log.warn("File not found in classpath: " + path);
	// }
	// return null;
	// }

	// public static PackageSetting parseSetting(InputStream stream) throws IOException {
	//
	// final DocumentBuilderFactory docfact = DocumentBuilderFactory.newInstance();
	// DocumentBuilder docb = null;
	// Document doc = null;
	// try {
	// docb = docfact.newDocumentBuilder();
	// doc = docb.parse(stream);
	// } catch (ParserConfigurationException ex) {
	// log.error("Cannot parse XML configuration", ex);
	// return null;
	// } catch (SAXException ex) {
	// log.error("Parsing XML configuration", ex);
	// return null;
	// }
	// // 返回值
	// PackageSetting pps = new PackageSetting();
	//
	// final Element root = doc.getDocumentElement();
	// Element elem = null;
	//
	// NodeList nodePackageHeaders = root.getElementsByTagName("packageHeader");
	// for (int i = 0; i < nodePackageHeaders.getLength(); i++) {
	// elem = (Element) nodePackageHeaders.item(i);
	// String packageType = elem.getAttribute("type").toString();
	// if ("amf".equals(packageType.toLowerCase().trim())) {
	// // AMF包
	// NodeList fields = elem.getElementsByTagName("byte");
	// if (fields.getLength() != PACKAGE_HEADER_SIZE) {
	// throw new IOException("配置文件包头大小设置错误");
	// }
	// byte[] headerBuf = new byte[PACKAGE_HEADER_SIZE];
	// for (int j = 0; j < PACKAGE_HEADER_SIZE; j++) {
	// Element f = (Element) fields.item(j);
	// String s = f.getFirstChild().getNodeValue();
	// byte b = (byte) s.charAt(0);
	// headerBuf[j] = b;
	// }
	// pps.setAmfHeader(headerBuf);
	// }
	//
	// }
	//
	// return pps;
	//
	// }

	// private static String javaPackage;
	// private static String flashPackage;

	/**
	 * 读取传输VO包(PO)
	 * 
	 * @param stream
	 * @return
	 * @throws Exception
	 */
	protected static Map<Integer, ProtocolContainer> parse(InputStream stream, String subPackageName, ProtocolSetting ps, int protocolPfx)
			throws Exception {
		Map<Integer, ProtocolContainer> map = new TreeMap<Integer, ProtocolContainer>();

		// 检查协议前缀范围
		if (protocolPfx < 0 || protocolPfx > 4095) {
			throw new Exception(
				"import Protocol Xml must be have 'protocolPfx' code between 1 - 4095");
		}

		final DocumentBuilderFactory docfact = DocumentBuilderFactory.newInstance();
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
		final Element root = doc.getDocumentElement();
		Element element = null;

		// 把namespace的最后的点去掉，以免重复
		if (subPackageName.endsWith(".")) subPackageName = subPackageName.substring(0,
			subPackageName.length() - 1);

		String originalPackageName = ps.getJavaPackage();
		//String basePoImport = ps.getJavaBasePoImport();

		// 检查是否配置好javaPackage路径与flashPackage路径
		if ((originalPackageName.length() < 1)) {
			throw new IOException(
				"Error default setting，have not 'javaPackage' and 'flashPackage' roots! [seraph notice]");
		}

		NodeList pcNodes = root.getElementsByTagName("parse");
		ProtocolCollection pc = ProtocolCollection.parseFromNode(protocolPfx, pcNodes, ps);

		for (Protocol_Q_A pp : pc.getProtocolPackageList()) {
			// 创建协议保存容器
			ProtocolContainer containerQuestion = new ProtocolContainer();

			String classNameQ = pp.formatClassName(ps.getQuestionPackageFormat());
			String classNameA = pp.formatClassName(ps.getAnswerPackageFormat());

			String nameSpaceClass;
			String beanName = pp.getSpringBeanName();
			String comment = pp.getComment();
			String writeSubPackageName = subPackageName;

			// 写
			if (subPackageName.length() > 0) {
				nameSpaceClass = originalPackageName + "." + subPackageName + "." + classNameQ;
			} else {
				nameSpaceClass = originalPackageName + "." + pp.getClassName();
				writeSubPackageName = "default";
			}

			containerQuestion.setClassName(classNameQ);
			containerQuestion.setSubPackageName(writeSubPackageName);
			containerQuestion.setParseProcotol(pp.getProtocol());
			containerQuestion.setNameSpaceClass(nameSpaceClass);
			containerQuestion.setSpringBeanName(beanName);
			containerQuestion.setComment(comment);
			containerQuestion.setMethodName(pp.getMethodName());
			// 把整个列表给容器
			containerQuestion.setParsetList(pp.getQuestionFields());

			if (map.containsKey(containerQuestion.getParseProtocol())) {
				throw new Exception("有键值相同了，相同的是:" + subPackageName + "->"
									+ containerQuestion.getClassName() + ",protocol:"
									+ containerQuestion.getParseProtocol());
			}
			// 把容器加入哈希表
			map.put(containerQuestion.getParseProtocol(), containerQuestion);

			// 检测此包有没有对应的回复包
			if (pp.getAnwserFields().size() > 0) {
				ProtocolContainer containerAnswer = new ProtocolContainer();

				// 写
				if (subPackageName.length() > 0) {
					nameSpaceClass = originalPackageName + "." + subPackageName + "." + classNameA;
				} else {
					nameSpaceClass = originalPackageName + "." + pp.getClassName();
					writeSubPackageName = "default";
				}

				containerAnswer.setClassName(classNameA);
				containerAnswer.setSubPackageName(writeSubPackageName);
				containerAnswer.setParseProcotol(-pp.getProtocol());
				containerAnswer.setNameSpaceClass(nameSpaceClass);
				containerAnswer.setSpringBeanName(beanName);
				containerAnswer.setComment(comment);
				containerAnswer.setMethodName(pp.getMethodName());
				// 把整个列表给容器
				containerAnswer.setParsetList(pp.getAnwserFields());

				if (map.containsKey(containerAnswer.getParseProtocol())) {
					throw new Exception("有键值相同了，相同的是:" + subPackageName + "->"
						+ containerAnswer.getClassName() + ",protocol:"
						+ containerAnswer.getParseProtocol());
				}
				// 把容器加入哈希表
				map.put(containerAnswer.getParseProtocol(), containerAnswer);
			}

		}

		// NodeList nodes = root.getElementsByTagName("parse");
		// for (int i = 0; i < nodes.getLength(); i++) {
		// ParseContainer container = new ParseContainer();
		// element = (Element) nodes.item(i);
		// Integer parseType = Integer.parseInt(element.getAttribute("protocol"));
		// if (parseType == null || parseType < 1 || parseType > 9999) {
		// throw new IOException("key 'protocol' must be between 1-9999");
		// } else {
		// // 根据前缀计算真正协议号
		// parseType = protocolPfx * 10000 + parseType;
		// }
		//			
		// String parseClass = element.getAttribute("className");
		// String nameSpaceClass;
		// container.setClassName(parseClass);
		// if (subPackageName.length() > 0) {
		// nameSpaceClass = javaPackage + "." + subPackageName + "." + parseClass;
		// container.setSubPackageName(subPackageName);
		// } else {
		// nameSpaceClass = javaPackage + "." + parseClass;
		// container.setSubPackageName("default");
		// }
		// String beanName = element.getAttribute("springBeanName");
		// String comment = element.getAttribute("comment");
		// container.setParseType(parseType);
		// container.setNameSpaceClass(nameSpaceClass);
		// container.setSpringBeanName(beanName);
		// container.setComment(comment);
		// NodeList fields = element.getElementsByTagName("field");
		// for (int j = 0; j < fields.getLength(); j++) {
		// ParseField parseField = new ParseField();
		// Element f = (Element) fields.item(j);
		// int length = 0;
		// if (f.getAttribute("length").length() > 0) {
		// length = Integer.parseInt(f.getAttribute("length"));
		// parseField.setDataLength(length);
		// }
		// String fieldType = f.getAttribute("fieldType");
		// parseField.setFieldType(FieldType.valueOf(fieldType));
		//
		// String targetFieldName = f.getAttribute("fieldName");
		// parseField.setTargetFieldName(targetFieldName);
		//
		// container.getParsetList().add(parseField);
		// }
		// map.put(container.getParseType(), container);
		// }

		// 查找要从其他XML设定文件导入的文件名
		NodeList importNodes = root.getElementsByTagName("import");
		for (int i = 0; i < importNodes.getLength(); i++) {
			element = (Element) importNodes.item(i);
			// String voPackage = elem.getAttribute("voPackage");
			String _subPackageName = element.getAttribute("subPackageName");

			if (_subPackageName == null || ("").equals(_subPackageName)) {
				throw new IOException("can not found key 'subPackageName' from the node 'import'");
			}

			Integer _protocolPfx = Integer.parseInt(element.getAttribute("protocolPfx"));
			if (_protocolPfx == null) {
				throw new IOException("can not found key 'protocolPfx' from the node 'import'");
			}
			String configFileName = element.getAttribute("configFileName");
			Map<Integer, ProtocolContainer> importMap = readFromProtocolConfig(configFileName,
				_subPackageName, ps, _protocolPfx);
			if (!importMap.isEmpty()) {
				map.putAll(importMap);
			}
		}

		return map;

	}
}

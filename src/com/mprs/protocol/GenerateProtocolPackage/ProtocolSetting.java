package com.mprs.protocol.GenerateProtocolPackage;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 协议配置文件读取配置(配置文件的setting字段)
 * 
 * @author admin
 * 
 */
public class ProtocolSetting {
	/**
	 * 包头长度
	 */
	private static final int PACKAGE_HEADER_SIZE = 4;
	
	private String javaPackage;
	private String javaBasePoImport;
	private String javaGenerateDir;
	private String flashPackage;
	private String flashBasePoImport;
	private String flashGenerateDir;

	private String questionPackageFormat;
	private String answerPackageFormat;

	private String packageHeader;
	private byte[] packageHeaderBytes;
	private String packageType;

	//<!-- compress 默认压缩方式 -->
	private int defaultCompress;
	private int defaultProtocolType;
	//<!-- compressType 默认传输包类型 -->	
	
	public static ProtocolSetting loadFromXml(String configFile) throws Exception{
		InputStream stream = ProtocolSetting.class.getClassLoader().getResourceAsStream(configFile);
		if (stream == null) {
			throw new Exception("读取配置文件" + configFile + "错误");
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

		// 读取设定
		NodeList settingNodes = root.getElementsByTagName("setting");
		if (settingNodes.getLength() > 0) {
			return  ProtocolSetting.parseFromNode(settingNodes);
		}else {
			throw new Exception("没法读取协议配置文件");
		}
	}
	
	/**
	 * (配置文件的setting节点)
	 * 
	 * @param defNodes
	 * @return
	 */
	public static ProtocolSetting parseFromNode(NodeList settingNodes) {
		ProtocolSetting ps = new ProtocolSetting();
		Element property = null;
		NodeList categorySet = null;
		Element element = null;

		if (settingNodes.getLength() > 0) {
			element = (Element) settingNodes.item(0);
			// 读java
			categorySet = element.getElementsByTagName("java");
			property = (Element) categorySet.item(0);
			if (property.hasAttributes()) {
				ps.javaPackage = property.getAttribute("package");

				// 把namespace的最后的点去掉，以免重复
				if (ps.javaPackage.endsWith(".")) {
					ps.javaPackage = ps.javaPackage.substring(0, ps.javaPackage.length() - 1);
				}

				ps.javaBasePoImport = property.getAttribute("BasePoImport");
				if (!ps.javaBasePoImport.endsWith(";")) ps.javaBasePoImport += ";";

				ps.javaGenerateDir = property.getAttribute("GenerateDir");
				if (!ps.javaGenerateDir.endsWith("\\")) ps.javaGenerateDir += "\\";
			}
			// 读flash
			categorySet = element.getElementsByTagName("flash");
			property = (Element) categorySet.item(0);
			if (property.hasAttributes()) {
				ps.flashPackage = property.getAttribute("package");

				// 把namespace的最后的点去掉，以免重复
				if (ps.flashPackage.endsWith(".")) {
					ps.flashPackage = ps.flashPackage.substring(0, ps.flashPackage.length() - 1);
				}

				ps.flashBasePoImport = property.getAttribute("BasePoImport");
				if (!ps.flashBasePoImport.endsWith(";")) ps.flashBasePoImport += ";";

				ps.flashGenerateDir = property.getAttribute("GenerateDir");
				if (!ps.flashGenerateDir.endsWith("\\")) ps.flashGenerateDir += "\\";
			}
			// 读包头 packageHeader
			categorySet = element.getElementsByTagName("packageHeader");
			property = (Element) categorySet.item(0);			
			ps.packageType = property.getAttribute("type");
			ps.packageHeader = property.getAttribute("code");
			//把包头处理成字节流						
			byte[] headerBuf = new byte[PACKAGE_HEADER_SIZE];
			for (int j = 0; j < PACKAGE_HEADER_SIZE; j++) {
				byte b = (byte) ps.packageHeader.charAt(j);
				headerBuf[j] = b;
			}
			ps.packageHeaderBytes = headerBuf;
			
			// 读Question包格式 questionPackageFormat
			categorySet = element.getElementsByTagName("questionPackageFormat");
			ps.questionPackageFormat = categorySet.item(0).getFirstChild().getNodeValue();
			// 读Answer包格式 answerPackageFormat
			categorySet = element.getElementsByTagName("answerPackageFormat");			
			ps.answerPackageFormat = categorySet.item(0).getFirstChild().getNodeValue();
			
			categorySet = element.getElementsByTagName("defaultCompress");
			ps.defaultCompress = Integer.parseInt(categorySet.item(0).getFirstChild().getNodeValue());
			categorySet = element.getElementsByTagName("defaultProtocolType");
			ps.defaultProtocolType = Integer.parseInt(categorySet.item(0).getFirstChild().getNodeValue());
			

		}

		return ps;
	}

	public String getJavaPackage() {
		return javaPackage;
	}

	public String getJavaBasePoImport() {
		return javaBasePoImport;
	}

	public String getJavaGenerateDir() {
		return javaGenerateDir;
	}

	public String getFlashPackage() {
		return flashPackage;
	}

	public String getFlashBasePoImport() {
		return flashBasePoImport;
	}

	public String getFlashGenerateDir() {
		return flashGenerateDir;
	}

	public String getQuestionPackageFormat() {
		return questionPackageFormat;
	}

	public String getAnswerPackageFormat() {
		return answerPackageFormat;
	}

	public String getPackageHeader() {
		return packageHeader;
	}

	public String getPackageType() {
		return packageType;
	}

	public byte[] getPackageHeaderBytes() {
		return packageHeaderBytes;
	}

	public void setPackageHeaderBytes(byte[] packageHeaderBytes) {
		this.packageHeaderBytes = packageHeaderBytes;
	}

	public int getDefaultCompress() {
		return defaultCompress;
	}

	public void setDefaultCompress(int defaultCompress) {
		this.defaultCompress = defaultCompress;
	}

	public int getDefaultProtocolType() {
		return defaultProtocolType;
	}

	public void setDefaultProtocolType(int defaultProtocolType) {
		this.defaultProtocolType = defaultProtocolType;
	}

}

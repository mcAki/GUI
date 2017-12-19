package com.sys.volunteer.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.UUID;

import javax.jms.JMSException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 自己重写Java源码的StringWriter
 * @author Administrator
 *
 */
public class SysUtil {
	public static boolean isNull(String param) {
		return (param == null || param.length() == 0 || param.trim().equals("")) ? true
				: false;
	}

	public static boolean isNull(Object param) {
		return (param == null || param.equals("")) ? true : false;
	}
	
	/**
	 * 创建Document对象
	 * @return
	 * @throws ParserConfigurationException
	 */
	public static Document buildDocument() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		return document;
	}
	

	/**
	 * 把Document的数据输出到StringWriter中，通过Transformer完成
	 * @param document
	 * @param stringwriter
	 * @throws TransformerFactoryConfigurationError
	 */
	public static StringWriter documentToStringWriter(Document document)
			throws TransformerFactoryConfigurationError {

		StringWriter stringwriter = new StringWriter();
		DOMSource domsource = new DOMSource(document);
		
		StreamResult streamresult = new StreamResult(stringwriter);
		TransformerFactory transformerfactory = TransformerFactory
				.newInstance();
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
	 * FUNC:将String转换为xml Document
	 * 
	 * @param xmlString
	 * @return db - Document
	 * @throws IOException
	 * @throws SAXException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document stringToXml(String xmlString) throws SAXException, IOException, ParserConfigurationException {
		// InputStream is = new StringBufferInputStream(xmlString);
		InputStream is = new ByteArrayInputStream(xmlString.getBytes());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(is);
	}
	
	/**
	 * 将Java Object转换为对应的xml字符串.参数永远都是Object类.,并进行校验.
	 * 
	 * @param message
	 * @return xmlString - String
	 * @throws JAXBException
	 * @throws TransformerException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public static String ObjectToXmlstring(Object message) throws JAXBException, TransformerException, SAXException, IOException, ParserConfigurationException {

		Document doc = null;
		String xmlString = null;
		// 创建document对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		doc = db.newDocument();

		// 转换
		JAXBContext jaxbContext = JAXBContext.newInstance(message.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(message, doc);
		xmlString = documentToStringWriter(doc).toString();

		return xmlString;
	}

	/**
	 * 将xml转换为对应的Java Object.即将XML中的数据植入Msg类
	 * @param xmlMessage
	 * @param cl
	 * @return
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws JMSException
	 * @throws TransformerException
	 */
	public static Object XmlToJavaobject(String xmlMessage,Class cl ) throws JAXBException, SAXException, IOException, ParserConfigurationException,  TransformerException {

		JAXBContext jaxbContext = JAXBContext.newInstance(cl);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Document doc = stringToXml(xmlMessage);
		Object message = unmarshaller.unmarshal(doc);

		return message;
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
	 * int数字补零
	 * @param num
	 * @param iso
	 * @return
	 */
	public static String formatInt(int num, String iso){
		java.text.DecimalFormat df=new java.text.DecimalFormat(iso);
		return df.format(num);

	}
	
	/**
	 * long数字补零
	 * @param num
	 * @param iso
	 * @return
	 */
	public static String formatLong(long num, String iso){
		java.text.DecimalFormat df=new java.text.DecimalFormat(iso);
		return df.format(num);

	}
	
	/**
	 * double数字补零
	 * @param num
	 * @param iso
	 * @return
	 */
	public static String formatDouble(double num, String iso){
		java.text.DecimalFormat df=new java.text.DecimalFormat(iso);
		return df.format(num);

	}
	
}

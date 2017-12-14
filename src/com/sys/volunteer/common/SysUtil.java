package com.sys.volunteer.common;

import java.io.StringWriter;

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
}

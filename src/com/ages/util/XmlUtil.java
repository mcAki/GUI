package com.ages.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

//import com.ages.core.Context;
//import com.ages.newitem.ItemKernel;
//import com.ages.pojo.AttachAttribute;
//import com.ages.pojo.ItemTemplate;
//import com.ages.pojo.TipsTemplate;

public class XmlUtil {
	
//	/**
//	 * 
//	 * @Title: buidlTipsTemplateToXml
//	 * @Description: 生成Tips xml数据
//	 * @param root
//	 *            void
//	 */
//	public static void buidlTipsTemplateToXml(Document root, List<TipsTemplate> list, String name) {
//		Element we = root.createElement(TipsTemplate.class.getSimpleName() + "s");
//		Node n = root.appendChild(we);
//		Document doc = n.getOwnerDocument();
//		for (TipsTemplate obj : list) {
//			Element xmlrow = doc.createElement("t");
//			xmlrow.setAttribute("id", String.valueOf(obj.getId()));
//			// xmlrow.setTextContent(obj.getTipsTemplate());
//			xmlrow.appendChild(doc.createTextNode(obj.getTipsTemplate()));
//			n.appendChild(xmlrow);
//		}
//		buildXmlFile(root, System.getProperty("user.dir") + "/data/xml/" + name + ".xml");
//	}

/*	*//**
	 * 
	 * @Title: buildTemplateToXml
	 * @Description: 生成模板 xml数据
	 * @param list
	 * @param classes
	 * @param name
	 * @param constantMap
	 * @param filterField
	 * @throws Exception
	 *             void
	 *//*
	public static <T> void buildTemplateToXml(Document root, List<T> list, Class<T> classes, String name, Map<String, String> constantMap, String... filterField)
			throws Exception {

		Element data = root.createElement("data");
		Element we = root.createElement(name + "s");

		if (constantMap != null)
		for (Map.Entry<String, String> entry : constantMap.entrySet()) {
			we.setAttribute(entry.getKey(), entry.getValue());
		}

		Node n = root.appendChild(we);
		Document doc = n.getOwnerDocument();

		List<Field> ls = new ArrayList<Field>(Arrays.asList(classes.getDeclaredFields()));// 字段
		if (filterField != null && filterField.length > 0) {// 判断过滤字段
			List<String> strs=Arrays.asList(filterField);
			Set<String> s=new HashSet<String>(strs);

		Iterator<Field> it=ls.iterator();
		while(it.hasNext()){
			Field f=it.next();
				if (s.contains(f.getName())) {
					it.remove();
				}
			}
		}
		
		for (Object obj : list) {
			Element xmlrow = doc.createElement(classes.getSimpleName());

			for (Field f : ls) {
				Object value = GroovyUtil.invokeMethod("utils", "getObjectFieldValue", true, obj, f.getName());
				xmlrow.setAttribute(f.getName(), String.valueOf(value));
			}
			n.appendChild(xmlrow);
		}
		data.appendChild(we);
		root.appendChild(data);
		buildXmlFile(root, System.getProperty("user.dir") + "/data/xml/" + name + ".xml");
	}*/

	public static void buildXmlFile(Document doc, String path) {
		TransformerFactory tfactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tfactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File f = new File(path);
			if (f.exists()) {
				f.delete();
			}
			f.getParentFile().mkdirs();
			f.createNewFile();

			StreamResult result = new StreamResult(f);
			transformer.setOutputProperty("encoding", "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void build() throws Exception {

		// build itemTemplate xml
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document root = builder.newDocument();

		Map<String, String> constantMap = new HashMap<String, String>();
//		constantMap.put("listLimit0", String.valueOf(ItemKernel.EQUIP_SLOT_MAX));
//		constantMap.put("listLimit1", String.valueOf(ItemKernel.PACK_MAX_SLOT));
//		constantMap.put("listLimit2", String.valueOf(ItemKernel.PACK_MAX_SLOT));
//		constantMap.put("listLimit3", String.valueOf(ItemKernel.PACK_MAX_SLOT));
		// 过滤掉的字段
			String[] strs = new String[] { "serialVersionUID", "simpleAttribute", "weaponType", "equipLvTemplateId", "stoneLv", "classname", "attribute", "attribute1",
					"physicalPowerLimit", "nimbleLimit" };
			String name="itemTemplate";
//		buildTemplateToXml(root, ItemKernel.getInstance().getItemTemplateList(), ItemTemplate.class, name, constantMap, strs);

		
		
		// build attachAttribute xml
		builder = factory.newDocumentBuilder();
		root = builder.newDocument();
		name="attachAttribute";
//		buildTemplateToXml(root, ItemKernel.getInstance().getAllAttachAttribute(), AttachAttribute.class, name, null, null);

		// build tipsTemplate xml
		builder = factory.newDocumentBuilder();
		root = builder.newDocument();
		name = "tipsTemplate";
//		buidlTipsTemplateToXml(root, ItemKernel.getInstance().getAllTipsTemplate(), name);

	}

	public static void main(String[] args) throws Exception {
		// 从单例模式提取Context
//		Context context = Context.getInstance();
//		context.reloadConfig();
		// 加载spring环境
//		ApplicationContext springContext = new ClassPathXmlApplicationContext(new String[] { "applicationSpringContext.xml" });
//		context.setSpringContext(springContext);

//		ItemKernel.getInstance().init();
		build();
		System.out.println("buildObjectToXml success...");

		// // 得到DOM解析器的工厂实例
		// DocumentBuilderFactory dbFactory =
		// DocumentBuilderFactory.newInstance();
		// // 从DOM工厂中获得DOM解析器
		// DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
		// // 把要解析的xml文档读入DOM解析器
		// Document doc = dbBuilder.parse(System.getProperty("user.dir") +
		// "/data/xml/tipsTemplate.xml");
		//
		// // 得到文档名称为Student的元素的节点列表
		// NodeList e = doc.getElementsByTagName("t");
		// for (int i = 0; i < e.getLength(); i++) {
		// Node n = e.item(i);
		// System.out.println(n.getTextContent() + ":");
		// }
	}
}

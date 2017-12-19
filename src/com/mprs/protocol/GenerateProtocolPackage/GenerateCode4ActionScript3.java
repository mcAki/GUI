package com.mprs.protocol.GenerateProtocolPackage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mprs.protocol.FieldType;
import com.mprs.protocol.ProtocolContainer;
import com.mprs.util.PackageUtil;
import com.mprs.util.SysUtil;

public class GenerateCode4ActionScript3 implements IGenerateCode, IGenerateConstTable {

	private String generateEncode = "utf-8";

	public String getGenerateEncode() {
		return generateEncode;
	}

	/**
	 * 设置编码
	 * 
	 * @param generateEncode
	 */
	public void setGenerateEncode(String generateEncode) {
		this.generateEncode = generateEncode;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// new GenerateCode4ActionScript3().GenerateCode("protocol_main.xml", "", 0, -1,
			// "c:\\vo\\f\\");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 生成AS代码
	 * 
	 * @param configFile
	 *            配置文件XML名称
	 * @param subPackageName
	 *            包名
	 * @param type
	 *            -1 生成全部文件 如果具体某个值与配置文件中 <parse type="2000" 对应
	 * 
	 * @param dir
	 *            生成目录，自己配置
	 * @throws Exception
	 */
	public void GenerateCode(String configFile, String subPackageName, int protocolPfx, ProtocolSetting ps, String dir)
			throws Exception {
		// 检查协议前缀范围
		if (protocolPfx < 0 || protocolPfx > 4095) {
			throw new Exception(
				"import Protocol Xml must be have 'protocolPfx' code between 1 - 4095");
		}

		InputStream stream = GenerateCode4ActionScript3.class.getClassLoader().getResourceAsStream(
			configFile);
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
		Element element = null;

		// 把namespace的最后的点去掉，以免重复
		if (subPackageName.endsWith(".")) subPackageName = subPackageName.substring(0,
			subPackageName.length() - 1);

		String originalPackageName = ps.getFlashPackage();
		String basePoImport = ps.getFlashBasePoImport();

		// 检查是否配置好javaPackage路径与flashPackage路径
		if ((originalPackageName.length() < 1)) {
			throw new IOException(
				"Error default setting，have not 'javaPackage' and 'flashPackage' roots! [seraph notice]");
		}

		// 这里是重点！！===================================================================
		// 这里读取XML该节点内所有字段信息变成生成用的中间件：Protocol_Q_A
		NodeList pcNodes = root.getElementsByTagName("parse");
		ProtocolCollection pc = ProtocolCollection.parseFromNode(protocolPfx, pcNodes, ps);
		// ================================================================================

		for (Protocol_Q_A pp : pc.getProtocolPackageList()) {
			// StringBuffer buffer = new StringBuffer();
			StringBuffer bodyHeader = new StringBuffer();
			StringBuffer bodyQuestion = new StringBuffer();
			StringBuffer bodyAnswer = new StringBuffer();

			// 包头
			if (subPackageName.length() > 0) {
				bodyHeader.append("package " + originalPackageName + "." + subPackageName + "{\n\n");
			} else {
				bodyHeader.append("package " + originalPackageName + "{\n\n");
			}

			bodyHeader.append("\timport " + basePoImport + "\n\n");
			// 批量导入import
			boolean isHadList = false;
			for (ProtocolField pf : pp.getQuestionFields()) {
				if (pf.getFieldType() == FieldType.eList) {
					isHadList = true;
				}
			}
			for (ProtocolField pf : pp.getAnwserFields()) {
				if (pf.getFieldType() == FieldType.eList) {
					isHadList = true;
				}
			}
			
			if (isHadList) bodyHeader.append("\timport mx.collections.ArrayCollection;\n\n");

			// AS3注释[Bindable]
			bodyHeader.append("\t[Bindable]\n");

			// --提问包
			String classNameQ = pp.formatClassName(ps.getQuestionPackageFormat());

			// as特有的绑定注释
			if (subPackageName.length() > 0) {
				bodyQuestion.append("\t[RemoteClass(alias=\"" + originalPackageName + "."
									+ subPackageName + "." + classNameQ + "\")]\n");
			} else {
				bodyQuestion.append("\t[RemoteClass(alias=\"" + originalPackageName + "."
									+ classNameQ + "\")]\n");
			}

			// 传输包头
			bodyQuestion.append("\t/**\n\t *" + pp.getComment() + "\n\t */\n");
			bodyQuestion.append("\tpublic class " + classNameQ + " extends BasePo{" + "\n\n");

			// 制作构造函数
			bodyQuestion.append("\t\tpublic function " + classNameQ + "() {\n\t\t\tsuper();"
								+ "\n\t\t\tcommandId=" + pp.getProtocol() + ";\n\t\t}\n\n");

			// bodyAnswer
			String classNameA = pp.formatClassName(ps.getAnswerPackageFormat());

			// as特有的绑定注释
			if (subPackageName.length() > 0) {
				bodyAnswer.append("\t[RemoteClass(alias=\"" + originalPackageName + "."
									+ subPackageName + "." + classNameA + "\")]\n");
			} else {
				bodyAnswer.append("\t[RemoteClass(alias=\"" + originalPackageName + "."
									+ classNameA + "\")]\n");
			}

			// 传输包头
			bodyAnswer.append("\t/**\n\t *" + pp.getComment() + "\n\t */\n");
			bodyAnswer.append("\tpublic class " + classNameA + " extends BasePo{" + "\n\n");

			// 制作构造函数
			bodyAnswer.append("\t\tpublic function " + classNameA + "() {\n\t\t\tsuper();"
								+ "\n\t\t\tcommandId=" + (-pp.getProtocol()) + ";\n\t\t}\n\n");

			/*
			 * private var _echoArray:ArrayCollection;
			 * 
			 * public function get echoArray():ArrayCollection { return _echoArray; }
			 * 
			 * public function set echoArray(value:ArrayCollection):void { _echoArray = value; }
			 */

			// 生成属性
			for (ProtocolField pf : pp.getQuestionFields()) {
				String targetFieldName = pf.getFieldName();
				String comString = pf.getComment();
				String javaType = TranslateType(pf.getFieldType());
				// 属性
				bodyQuestion.append("\t\t/**\n\t\t *" + comString + "\n\t\t */\n");
				bodyQuestion.append("\t\tprivate var _" + targetFieldName + ":" + javaType + "; \n");
			}

			// 生成get set方法
			for (ProtocolField pf : pp.getQuestionFields()) {
				String targetFieldName = pf.getFieldName();
				String comString = pf.getComment();
				String javaType = TranslateType(pf.getFieldType());
				// SET
				bodyQuestion.append("\t\t/**\n\t\t *" + comString + "\n\t\t */\n");
				bodyQuestion.append("\t\tpublic function set " + targetFieldName + "(value:"
									+ javaType + "):void{\n");
				bodyQuestion.append("\t\t\t_" + targetFieldName + "=value;\n");
				bodyQuestion.append("\t\t}\n");

				// GET
				bodyQuestion.append("\t\t/**\n\t\t *" + comString + "\n\t\t */\n");
				bodyQuestion.append("\t\tpublic function get " + targetFieldName + "():" + javaType
									+ "{\n");

				bodyQuestion.append("\t\t\treturn _" + targetFieldName + "; \n");
				bodyQuestion.append("\t\t}\n");

			}

			bodyQuestion.append("\n\t}\n}");
			System.out.println(bodyQuestion.toString());

			String detalClassName = "";

			// bodyAnswer
			// 生成属性
			for (ProtocolField pf : pp.getAnwserFields()) {
				if (pf.getFieldType() == FieldType.eList) {
					detalClassName = " 类型：" + pf.getListDetailType();
				}
				String targetFieldName = pf.getFieldName();
				String comString = pf.getComment();
				String javaType = TranslateType(pf.getFieldType());
				// 属性
				bodyAnswer.append("\t\t/**\n\t\t *" + comString + detalClassName + "\n\t\t */\n");
				bodyAnswer.append("\t\tprivate var _" + targetFieldName + ":" + javaType + "; \n");
			}

			// 生成get set方法
			for (ProtocolField pf : pp.getAnwserFields()) {
				String targetFieldName = pf.getFieldName();
				String comString = pf.getComment();
				String javaType = TranslateType(pf.getFieldType());

				// SET
				bodyAnswer.append("\t\t/**\n\t\t *" + comString + detalClassName + "\n\t\t */\n");
				bodyAnswer.append("\t\tpublic function set " + targetFieldName + "( value:"
									+ javaType + "):void{\n");
				bodyAnswer.append("\t\t\t_" + targetFieldName + "=value;\n");
				bodyAnswer.append("\t\t}\n");

				// GET
				bodyAnswer.append("\t\t/**\n\t\t *" + comString + detalClassName + "\n\t\t */\n");
				bodyAnswer.append("\t\tpublic function get " + targetFieldName + "():" + javaType
									+ "{\n");

				bodyAnswer.append("\t\t\treturn _" + targetFieldName + "; \n");
				bodyAnswer.append("\t\t}\n");

			}

			bodyAnswer.append("\n\t}\n}");
			System.out.println(bodyAnswer.toString());

			// 输出到文件系统
			if (!dir.endsWith("\\")) {
				dir += "\\";
			}
			String path = dir + (originalPackageName + "." + subPackageName).replace(".", "\\");

			// 自动创建对应目录
			SysUtil.CreatePath(path);

			// 写包
			if (pp.getQuestionFields().size() > 0) {
				SysUtil.WriteTxtFile(path + "\\" + classNameQ + ".as", bodyHeader.toString()
																		+ bodyQuestion.toString(),
					generateEncode);
			}

			// 写包
			if (pp.getAnwserFields().size() > 0) {
				SysUtil.WriteTxtFile(path + "\\" + classNameA + ".as", bodyHeader.toString()
																		+ bodyAnswer.toString(),
					generateEncode);
			}

		}

		// // 输出到文件系统
		// if (!dir.endsWith("\\")) {
		// dir += "\\";
		// }
		// String path = dir + (originalPackageName + "." + subPackageName).replace(".", "\\");
		//
		// // 自动创建对应目录
		// SysUtil.CreatePath(path);
		//
		// // 写包
		// SysUtil.WriteTxtFile(path + "\\" + className + ".as", buffer.toString(), generateEncode);
		// }
		//

		// 这里是重点！！===================================================================
		// 这里读取XML该节点内所有字段信息变成生成用的中间件：definition
		NodeList pcDefinition = root.getElementsByTagName("definition");
		ProtocolCollection.parseDefineFromNode(pc, pcDefinition, ps);
		// ================================================================================
		StringBuffer defineHeader = new StringBuffer();
		StringBuffer defineBody = new StringBuffer();
		
		// 处理文件名
		String fileName=subPackageName;
		if(fileName.length()>1){
			fileName = fileName.substring(0,1).toUpperCase() + fileName.substring(1) + "Const";
		}else{
			fileName = "Default" + "Const";
		}		
		
		// 包头
		if (subPackageName.length() > 0) {
			defineHeader.append("package " + originalPackageName + "." + subPackageName + "{\n\n");
		} else {
			defineHeader.append("package " + originalPackageName + "{\n\n");
		}

		defineHeader.append("\tpublic class " + fileName + "{\n\n");
		for (DefineConst defineConst : pc.getDefineConsts()) {

			defineBody.append("\t\t/**\n\t\t *" + defineConst.getComment() + "\n\t\t */\n");
			String fieldType = TranslateType(defineConst.getFieldType());
			// defineBody.append("\t\tprivate static final " + fieldType + " " +
			// defineConst.getFieldName() + " = ");
			defineBody.append("\t\tpublic static const " + defineConst.getFieldName() + ":"
								+ fieldType + " = ");
			switch (defineConst.getFieldType()) {
				case eDate:
				case eDateTime:
				case eStr:
					defineBody.append("\"" + defineConst.getConstValue() + "\"; \n\n");
					break;
				default:
					defineBody.append(defineConst.getConstValue() + "; \n\n");
					break;
			}
		}
		// defineBody
		defineBody.append("\t}\n}");
		System.out.println(defineBody.toString());
		// 输出到文件系统
		String path2 = dir + (originalPackageName + "." + subPackageName).replace(".", "\\");
		// 写包
		if (pc.getDefineConsts().size() > 0) {
			SysUtil.WriteTxtFile(path2 + "\\" + fileName + ".as", defineHeader.toString()
																	+ defineBody.toString(),
				generateEncode);
		}

		// =以下是递归导入其他文件========================================================================================
		// 查找要从其他XML设定文件导入的文件名
		NodeList importNodes = root.getElementsByTagName("import");
		for (int i = 0; i < importNodes.getLength(); i++) {
			element = (Element) importNodes.item(i);
			String _package = element.getAttribute("subPackageName");

			if (_package == null || ("").equals(_package)) {
				_package = subPackageName;
			}
			Integer _protocolPfx = Integer.parseInt(element.getAttribute("protocolPfx"));
			if (_protocolPfx == null) {
				throw new Exception("can not found key 'protocolPfx' from the node 'import'");
			}
			String configFileName = element.getAttribute("configFileName");
			// 递归写文件
			GenerateCode(configFileName, _package, _protocolPfx, ps, dir);
		}

	}

	/**
	 * 解释类型
	 * 
	 * @param fieldType
	 * @return
	 */
	public String TranslateType(FieldType type) {
		String asType = "";
		String fieldType = type.toString();
		if (fieldType.trim().equals("eStr") || fieldType.trim().equals("eFixedStr")) {
			asType = "String";
		} else if (fieldType.trim().equals("eInt")) {
			asType = "int";
		} else if (fieldType.trim().equals("eFloat")) {
			asType = "Number";
		} else if (fieldType.trim().equals("eBoolean")) {
			asType = "Boolean";
		} else if (fieldType.trim().equals("eByte")) {
			asType = "Byte";
		} else if (fieldType.trim().equals("eShort")) {
			asType = "Short";
		} else if (fieldType.trim().equals("eLong")) {
			asType = "Number";
		} else if (fieldType.trim().equals("eDouble")) {
			asType = "Number";
		} else if (fieldType.trim().equals("eList")) {
			asType = "ArrayCollection";
		}
		return asType;
	}

	@Override
	public void GenerateConstTable(Map<Integer, ProtocolContainer> map, ProtocolSetting ps, String dir)
			throws Exception {
		TreeMap<Integer, ProtocolContainer> tMap = (TreeMap<Integer, ProtocolContainer>) map;

		String originalPackageName = ps.getFlashPackage();
		// String basePoImport = ps.getFlashBasePoImport();
		if (originalPackageName.length() < 0) {
			throw new Exception("please run 'GenerateCode' method first!");
		}

		if (tMap.size() < 1) {
			throw new Exception("没任何常量记录可以生成");
		}
		// 常量对照表
		StringBuffer bufProtocol = new StringBuffer();
		bufProtocol.append("package " + originalPackageName + "{\n\n");
		bufProtocol.append("\t/**\n");
		bufProtocol.append("\t *协议常量对照表\n");
		bufProtocol.append("\t */\n");
		bufProtocol.append("\tpublic class Protocols{\n\n");

		// 加入包头
		bufProtocol.append("\t\t/**\n");
		bufProtocol.append("\t\t * 包头类型 \n");
		bufProtocol.append("\t\t */\n");
		bufProtocol.append("\t\tpublic static const packageType:String = \"" + ps.getPackageType()
							+ "\"");
		bufProtocol.append(";\n\n");

		bufProtocol.append("\t\t/**\n");
		bufProtocol.append("\t\t * 包头 \n");
		bufProtocol.append("\t\t */\n");
		bufProtocol.append("\t\tpublic static const packageHeader:String = \"" + ps.getPackageHeader() + "\"");
		bufProtocol.append(";\n\n");

		// Flash端事件表
		StringBuffer bufEvent = new StringBuffer();
		bufEvent.append("package " + originalPackageName + "{\n\n");
		bufEvent.append("\t/**\n");
		bufEvent.append("\t *接受事件表\n");
		bufEvent.append("\t */\n");
		bufEvent.append("\tpublic class RecvEvents{\n\n");

		bufEvent.append("\t\t/**\n");
		bufEvent.append("\t\t *通用收到包消息\n");
		bufEvent.append("\t\t */\n");
		bufEvent.append("\t\tpublic static const ALL_EVENT:String = " + "\"recv_all\"");
		bufEvent.append(";\n\n");
		//HashMap<Integer, String> moduleEvents = new HashMap<Integer, String>();

		HashMap<String, String> writtenSubPackageName = new HashMap<String, String>();

		for (Entry<Integer, ProtocolContainer> entry : tMap.entrySet()) {
			//String key = entry.getKey().toString();
			ProtocolContainer value = entry.getValue();
			if (!writtenSubPackageName.containsKey(value.getSubPackageName())) {
				String ConstEventName = value.getSubPackageName().toUpperCase() + "_ALL_EVENT";
				bufEvent.append("\t\t/**\n");
				bufEvent.append("\t\t *" + value.getSubPackageName() + "综合事件包 \n");
				bufEvent.append("\t\t */\n");

				bufEvent.append("\t\tpublic static const " + ConstEventName + ":String = "
								+ "\"recv_"
								+ PackageUtil.getProtocolPfxCode(value.getParseProtocol()) + "\"");
				bufEvent.append(";\n\n");
				// 写入已经写入的标记，以防重复录入
				writtenSubPackageName.put(value.getSubPackageName(), "");
			}
		}

		for (Entry<Integer, ProtocolContainer> entry : tMap.entrySet()) {
			if (entry.getKey() < 0) {
				continue;
			}
			String key = entry.getKey().toString();
			ProtocolContainer value = entry.getValue();
			System.out.println("协议常量:" + key);

			// Integer x = entry.getKey();

			String ConstProtocolName = value.getSubPackageName().toUpperCase() + "_"
										+ value.getClassName().toUpperCase();

			bufProtocol.append("\t\t/**\n");
			bufProtocol.append("\t\t *" + value.getComment() + "\n");
			bufProtocol.append("\t\t *所属：" + value.getSubPackageName() + "\n");
			bufProtocol.append("\t\t */\n");
			bufProtocol.append("\t\tpublic static const " + ConstProtocolName + ":int = "
								+ value.getParseProtocol().toString());
			bufProtocol.append(";\n\n");

			// ===============

			String ConstEventName = value.getSubPackageName().toUpperCase() + "_"
									+ value.getClassName().toUpperCase() + "_EVENT";
			bufEvent.append("\t\t/**\n");
			bufEvent.append("\t\t *" + value.getComment());
			if (value.getParseProtocol() > 0) {
				bufEvent.append(" 发出包\n");
			} else {
				bufEvent.append(" 应答包\n");
			}
			bufEvent.append("\t\t *所属：" + value.getSubPackageName() + "\n");
			bufEvent.append("\t\t */\n");
			bufEvent.append("\t\tpublic static const " + ConstEventName + ":String = " + "\"recv_"
							+ value.getParseProtocol().toString() + "\"");
			bufEvent.append(";\n\n");
		}

		//for (Entry<Integer, ProtocolContainer> entry : tMap.entrySet()) {
			//String key = entry.getKey().toString();
			//ProtocolContainer value = entry.getValue();

		//}

		/*
		 * public static function registerClass():void {
		 * registerClassAlias("flex.messaging.io.ArrayCollection", ArrayCollection);
		 * registerClassAlias("com.ages.protocol.po.EchoPo_Rp", EchoPo_Rp);
		 * registerClassAlias("com.ages.protocol.po.TestObj", TestObj); }
		 */
		// 常量对照表
		StringBuffer bufInitProtocols = new StringBuffer();

		// 临时生成字符缓冲
		StringBuffer bufInitProtocols_import = new StringBuffer();
		StringBuffer bufInitProtocols_regcode = new StringBuffer();

		// 批量加入import
		for (Entry<Integer, ProtocolContainer> entry : tMap.entrySet()) {
			ProtocolContainer value = entry.getValue();

			bufInitProtocols_import.append("\timport ");

			if (value.getSubPackageName().equals("default")) {
				// 主包
				bufInitProtocols_import.append(originalPackageName + "."
												+ value.getClassName().toString() + ";\n");

				bufInitProtocols_regcode.append("\t\t\tregisterClassAlias(\"");
				bufInitProtocols_regcode.append(originalPackageName + "."
												+ value.getClassName().toString() + "\" , ");
				bufInitProtocols_regcode.append(originalPackageName + "."
												+ value.getClassName().toString() + ");\n\n");
			} else {
				// 子包
				bufInitProtocols_import.append(originalPackageName + "."
												+ value.getSubPackageName() + "."
												+ value.getClassName().toString() + ";\n");

				bufInitProtocols_regcode.append("\t\t\tregisterClassAlias(\"");
				bufInitProtocols_regcode.append(originalPackageName + "."
												+ value.getSubPackageName() + "."
												+ value.getClassName().toString() + "\" , ");
				bufInitProtocols_regcode.append(originalPackageName + "."
												+ value.getSubPackageName() + "."
												+ value.getClassName().toString() + ");\n\n");
			}

		}
		bufInitProtocols.append("package " + originalPackageName + "{\n");
		bufInitProtocols.append("\timport flash.net.registerClassAlias;\n");
		bufInitProtocols.append("\timport mx.collections.ArrayCollection;\n\n");

		bufInitProtocols.append(bufInitProtocols_import.toString());

		bufInitProtocols.append("\t/**\n");
		bufInitProtocols.append("\t *协议常量对照表\n");
		bufInitProtocols.append("\t */\n");
		bufInitProtocols.append("\tpublic class InitProtocols{\n\n");
		bufInitProtocols.append("\t\tpublic static function Init():void {\n\n");
		bufInitProtocols.append("\t\t\tregisterClassAlias(\"flex.messaging.io.ArrayCollection\", ArrayCollection);\n\n");

		bufInitProtocols.append(bufInitProtocols_regcode.toString());

		bufProtocol.append("\t}\n}\n");
		bufEvent.append("\t}\n}\n");
		bufInitProtocols.append("\t\t}\n\t}\n}\n");

		System.out.println(bufProtocol.toString());

		// 输出文件系统
		if (!dir.endsWith("\\")) {
			dir += "\\";
		}

		String path = dir + originalPackageName.replace(".", "\\");

		// 自动创建对应目录
		SysUtil.CreatePath(path);

		// 写包
		SysUtil.WriteTxtFile(path + "\\Protocols.as", bufProtocol.toString(), generateEncode);
		// 写包2
		SysUtil.WriteTxtFile(path + "\\RecvEvents.as", bufEvent.toString(), generateEncode);
		// 写包3
		SysUtil.WriteTxtFile(path + "\\InitProtocols.as", bufInitProtocols.toString(),
			generateEncode);

	}

}

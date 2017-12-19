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
import com.mprs.util.SysUtil;

public class GenerateCode4JAVA implements IGenerateCode, IGenerateConstTable {

	private String generateEncode = "utf8";

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
			//new GenerateCode4JAVA().GenerateCode("protocol_main.xml", "", 0, -1, "c:\\vo\\j\\");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// private String originalPackageName = "";

	// private String basePoImport = "";

	/**
	 * 生成JAVA代码
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
	public void GenerateCode(String configFile, String subPackageName, int protocolPfx, ProtocolSetting ps, String dir) throws Exception {
		InputStream stream = GenerateCode4JAVA.class.getClassLoader().getResourceAsStream(configFile);
		if (stream == null) {
			throw new Exception("读取配置文件" + configFile + "错误");
		}

		// 检查协议前缀范围
		if (protocolPfx < 0 || protocolPfx > 4095) {
			throw new Exception("import Protocol Xml must be have 'protocolPfx' code between 1 - 4095");
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
		if (subPackageName.endsWith(".")) subPackageName = subPackageName.substring(0, subPackageName.length() - 1);

		String originalPackageName = ps.getJavaPackage();
		String basePoImport = ps.getJavaBasePoImport();

		// 检查是否配置好javaPackage路径与flashPackage路径
		if ((originalPackageName.length() < 1)) {
			throw new IOException("Error default setting，have not 'javaPackage' and 'flashPackage' roots! [seraph notice]");
		}

		
		
		//这里是重点！！===================================================================
		//这里读取XML该节点内所有字段信息变成生成用的中间件：Protocol_Q_A
		NodeList pcNodes = root.getElementsByTagName("parse");		
		ProtocolCollection pc = ProtocolCollection.parseFromNode(protocolPfx, pcNodes, ps);
		//================================================================================
		
		for (Protocol_Q_A pp : pc.getProtocolPackageList()) {
			// StringBuffer buffer = new StringBuffer();
			StringBuffer bodyHeader = new StringBuffer();
			StringBuffer bodyQuestion = new StringBuffer();
			StringBuffer bodyAnswer = new StringBuffer();
			

			// 包头
			if (subPackageName.length() > 0) {
				bodyHeader.append("package " + originalPackageName + "." + subPackageName + ";\n\n");
			} else {
				bodyHeader.append("package " + originalPackageName + ";\n\n");
			}

			
			bodyHeader.append("import " + basePoImport + "\n\n");
			bodyHeader.append("/**\n *" + pp.getComment() + "\n */\n");
			bodyHeader.append("@SuppressWarnings(\"serial\")\n");
			
			String classNameQ = pp.formatClassName(ps.getQuestionPackageFormat());
			// 传输包头
			bodyQuestion.append("public class " + classNameQ + " extends BasePo{" + "\n\n");
			
			// 制作构造函数
			bodyQuestion.append("\tpublic " + classNameQ + "() {\n\t\tsuper();" + "\n\t\tcommandId=" + pp.getProtocol() + ";\n\t}\n\n");

			String classNameA = pp.formatClassName(ps.getAnswerPackageFormat());
			// 传输包头
			bodyAnswer.append("public class " + classNameA + " extends BasePo{" + "\n\n");

			// 制作构造函数
			bodyAnswer.append("\tpublic " + classNameA + "() {\n\t\tsuper();" + "\n\t\tcommandId=" + (-pp.getProtocol()) + ";\n\t}\n\n");

			// 生成属性
			for (ProtocolField pf : pp.getQuestionFields()) {
				String targetFieldName = pf.getFieldName();
				String comString = pf.getComment();
				String javaType = TranslateType(pf.getFieldType());
				// 属性
				bodyQuestion.append("\t/**\n\t *" + comString + "\n\t */\n");
				if(pf.getFieldType()==FieldType.eList){
					String detailType = pf.getListDetailType();					
					bodyQuestion.append("\tprivate " + javaType + "<" + detailType + "> " + targetFieldName + "; \n\n");
				}else{
					bodyQuestion.append("\tprivate " + javaType + " " + targetFieldName + "; \n\n");
				}
			}

			// 生成get set方法
			for (ProtocolField pf : pp.getQuestionFields()) {
				String targetFieldName = pf.getFieldName();
				String comString = pf.getComment();
				String javaType = TranslateType(pf.getFieldType());
				
				bodyQuestion.append("\t/**\n\t *" + comString + "\n\t */\n");
				/*
				 	private List<Integer> echoArray;

					public List<Integer> getEchoArray() {
						return echoArray;
					}
					public void setEchoArray(java.util.List<Integer> echoArray) {
						this.echoArray = echoArray;
					}
				*/
				
				// SET
				if(pf.getFieldType()==FieldType.eList){					
					bodyQuestion.append("\tpublic void set" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1) + "("
						+ javaType + "<" + pf.getListDetailType() + "> " + targetFieldName + "){\n");
				}else{					
					bodyQuestion.append("\tpublic void set" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1) + "("
						+ javaType + " " + targetFieldName + "){\n");					
				}
				
				
				
				bodyQuestion.append("\t\tthis." + targetFieldName + "=" + targetFieldName + ";\n");
				bodyQuestion.append("\t}\n\n");
				bodyQuestion.append("\t/**\n\t *" + comString + "\n\t */\n");
				// GET
				if(pf.getFieldType()==FieldType.eList){
					bodyQuestion.append("\tpublic " + javaType + "<" + pf.getListDetailType() + "> get" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1)
						+ "(){  \n");
				}else{					
					bodyQuestion.append("\tpublic " + javaType + "  get" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1)
						+ "(){  \n");			
				}

				bodyQuestion.append("\t\treturn " + targetFieldName + "; \n").append("\t}\n");
			}

			bodyQuestion.append("\n}");
			System.out.println(bodyQuestion.toString());

			// 生成属性
			for (ProtocolField pf : pp.getAnwserFields()) {
				String targetFieldName = pf.getFieldName();
				String comString = pf.getComment();
				String javaType = TranslateType(pf.getFieldType());
				// 属性
				bodyAnswer.append("\t/**\n\t *" + comString + "\n\t */\n");
				
				if(pf.getFieldType()==FieldType.eList){
					bodyAnswer.append("\tprivate " + javaType + "<" + pf.getListDetailType() + "> " + targetFieldName + "; \n\n");
				}else{
					bodyAnswer.append("\tprivate " + javaType + " " + targetFieldName + "; \n\n");
				}				
				
			}

			// 生成get set方法
			for (ProtocolField pf : pp.getAnwserFields()) {
				String targetFieldName = pf.getFieldName();
				String comString = pf.getComment();
				String javaType = TranslateType(pf.getFieldType());
				// SET
				bodyAnswer.append("\t/**\n\t *" + comString + "\n\t */\n");
//				bodyAnswer.append("\tpublic void set" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1) + "(" 
//					+ javaType + " " + targetFieldName + "){\n");
				
				if(pf.getFieldType()==FieldType.eList){					
					bodyAnswer.append("\tpublic void set" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1) + "("
						+ javaType + "<" + pf.getListDetailType() + "> " + targetFieldName + "){\n");
				}else{					
					bodyAnswer.append("\tpublic void set" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1) + "("
						+ javaType + " " + targetFieldName + "){\n");					
				}
				
				bodyAnswer.append("\t\tthis." + targetFieldName + "=" + targetFieldName + ";\n");
				bodyAnswer.append("\t}\n\n");
				bodyAnswer.append("\t/**\n\t *" + comString + "\n\t */\n");
				// GET
//				bodyAnswer.append("\tpublic " + javaType + "  get" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1)
//									+ "(){  \n");
				if(pf.getFieldType()==FieldType.eList){
					bodyAnswer.append("\tpublic " + javaType + "<" + pf.getListDetailType() + "> get" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1)
						+ "(){  \n");
				}else{					
					bodyAnswer.append("\tpublic " + javaType + "  get" + targetFieldName.substring(0, 1).toUpperCase() + targetFieldName.substring(1)
						+ "(){  \n");			
				}
				
				bodyAnswer.append("\t\treturn " + targetFieldName + "; \n").append("\t}\n");
			}

			bodyAnswer.append("\n}");
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
				SysUtil.WriteTxtFile(path + "\\" + classNameQ + ".java", bodyHeader.toString() + bodyQuestion.toString(), generateEncode);
			}
			// 写包
			if(pp.getAnwserFields().size()>0){
				SysUtil.WriteTxtFile(path + "\\" + classNameA + ".java", bodyHeader.toString() + bodyAnswer.toString(), generateEncode);
			}
		}

		
		
		//这里是重点！！===================================================================
		//这里读取XML该节点内所有字段信息变成生成用的中间件：definition		
		NodeList pcDefinition = root.getElementsByTagName("definition");
		ProtocolCollection.parseDefineFromNode(pc, pcDefinition, ps);
		//================================================================================
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
			defineHeader.append("package " + originalPackageName + "." + subPackageName + ";\n\n");
		} else {
			defineHeader.append("package " + originalPackageName + ";\n\n");				
		}
		defineHeader.append("public class " + fileName + "{\n\n");
		for (DefineConst defineConst : pc.getDefineConsts()){			
			defineBody.append("\t\t/**\n\t\t *" + defineConst.getComment() + "\n\t\t */\n");
			//defineBody.append(defineConst.getComment());
			String fieldType = TranslateType(defineConst.getFieldType());
			defineBody.append("\t\tpublic static final " + fieldType + " " + defineConst.getFieldName() + " = ");
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
		//defineBody
		defineBody.append("\n}");
		System.out.println(defineBody.toString());
		// 输出到文件系统
		String path2 = dir + (originalPackageName + "." + subPackageName).replace(".", "\\");
		// 写包
		if (pc.getDefineConsts().size() > 0) {			
			SysUtil.WriteTxtFile(path2 + "\\" + fileName  + ".java", defineHeader.toString() + defineBody.toString(), generateEncode);
		}
		
		//=以下是递归导入其他文件========================================================================================
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
	 * @param fieldType
	 * @return
	 */
	public String TranslateType(FieldType type) {
		String javaType = "";
		String fieldType = type.toString();
		if (fieldType.trim().equals("eStr") || fieldType.trim().equals("eFixedStr")) {
			javaType = "String";
		} else if (fieldType.trim().equals("eInt")) {
			javaType = "int";
		} else if (fieldType.trim().equals("eFloat")) {
			javaType = "float";
		} else if (fieldType.trim().equals("eBoolean")) {
			javaType = "boolean";
		} else if (fieldType.trim().equals("eByte")) {
			javaType = "Byte";
		} else if (fieldType.trim().equals("eShort")) {
			javaType = "Short";
		} else if (fieldType.trim().equals("eLong")) {
			javaType = "long" ;
		} else if (fieldType.trim().equals("eDouble")) {
			javaType = "Double";
		} else if (fieldType.trim().equals("eList")) {
			javaType = "java.util.List";
		} 
		return javaType;
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
		
		
		/*
		 package com.ages.protocol.po;

			public class DefaultConst{

				public static final int MODULE_ID_UNSET = 0; 
 
		 
		 * */
		
		// 常量对照表
		StringBuffer bufProtocol = new StringBuffer();
		bufProtocol.append("package " + originalPackageName + ";\n\n");
		bufProtocol.append("/**\n");
		bufProtocol.append(" *协议常量对照表\n");
		bufProtocol.append(" */\n");
		bufProtocol.append("public class Protocols{\n\n");

		// 加入包头
		bufProtocol.append("\t/**\n");
		bufProtocol.append("\t * 包头类型 \n");
		bufProtocol.append("\t */\n");
		bufProtocol.append("\tpublic static final String packageType = \"" + ps.getPackageType()
							+ "\"");
		bufProtocol.append(";\n\n");

		bufProtocol.append("\t/**\n");
		bufProtocol.append("\t * 包头 \n");
		bufProtocol.append("\t */\n");
		bufProtocol.append("\tpublic static final String packageHeader = \"" + ps.getPackageHeader() + "\"");
		bufProtocol.append(";\n\n");

		HashMap<String, String> writtenSubPackageName = new HashMap<String, String>();

		for (Entry<Integer, ProtocolContainer> entry : tMap.entrySet()) {
			//String key = entry.getKey().toString();
			ProtocolContainer value = entry.getValue();
			if (!writtenSubPackageName.containsKey(value.getSubPackageName())) {
				//String ConstEventName = value.getSubPackageName().toUpperCase() + "_ALL_EVENT";
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

			bufProtocol.append("\t/**\n");
			bufProtocol.append("\t *" + value.getComment() + "\n");
			bufProtocol.append("\t *所属：" + value.getSubPackageName() + "\n");
			bufProtocol.append("\t */\n");
			bufProtocol.append("\tpublic static final int " + ConstProtocolName + " = "
								+ value.getParseProtocol().toString());
			bufProtocol.append(";\n\n");

			// ===============

		}

		// 常量对照表
		///StringBuffer bufInitProtocols = new StringBuffer();

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


		bufProtocol.append("}\n");
		
		System.out.println(bufProtocol.toString());

		// 输出文件系统
		if (!dir.endsWith("\\")) {
			dir += "\\";
		}

		String path = dir + originalPackageName.replace(".", "\\");

		// 自动创建对应目录
		SysUtil.CreatePath(path);

		// 写包
		SysUtil.WriteTxtFile(path + "\\Protocols.java", bufProtocol.toString(), generateEncode);
		// 写包2
		//SysUtil.WriteTxtFile(path + "\\RecvEvents.java", bufEvent.toString(), generateEncode);
		// 写包3
		///SysUtil.WriteTxtFile(path + "\\InitProtocols.java", bufInitProtocols.toString(),	generateEncode);

	}

}

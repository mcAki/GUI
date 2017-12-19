package com.mprs.protocol.GenerateProtocolPackage;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.mprs.protocol.FieldType;
import com.mprs.util.SysUtil;

/**
 * 协议配置文件读取配置(配置文件的setting字段)
 * 
 * @author admin
 * 
 */
public class ProtocolCollection {

	private int protocolPfx;

	private ArrayList<Protocol_Q_A> protocolPackageList = new ArrayList<Protocol_Q_A>();

	private ArrayList<DefineConst> defineConsts = new ArrayList<DefineConst>();

	/**
	 * 从define节点获取常量定义
	 * 
	 * @param pc
	 * @param parseNodes
	 * @param ps
	 */
	public static void parseDefineFromNode(ProtocolCollection pc, NodeList parseNodes, ProtocolSetting ps) {
		//Element property = null;
		//NodeList categorySet = null;
		Element element = null;
		for (int i = 0; i < parseNodes.getLength(); i++) {
			element = (Element) parseNodes.item(i);
			// 获取常量头
			String namePfx = element.getAttribute("namePfx");

			NodeList fields;
			// 获取提问包
			fields = element.getElementsByTagName("const");
			for (int j = 0; j < fields.getLength(); j++) {
				DefineConst defineConst = new DefineConst();
				Element f = (Element) fields.item(j);

				String targetFieldName = namePfx + f.getAttribute("name");
				String fieldType = f.getAttribute("fieldType");
				String comString = f.getAttribute("comment");
				String constValue = f.getAttribute("value");

				defineConst.setComment(comString);
				defineConst.setConstValue(constValue);
				defineConst.setFieldName(targetFieldName);
				defineConst.setFieldType(FieldType.valueOf(fieldType));
				pc.defineConsts.add(defineConst);
			}

			// defineConst.setComment(element.getAttribute("comment"));
			// defineConst.setComment(element.getAttribute("comment"));
			// String targetFieldName = f.getAttribute("fieldName");
			// String fieldType = f.getAttribute("fieldType");
			// String comString = f.getAttribute("comment");
			//						
		}

	}

	/**
	 * (配置文件的parse节点)
	 * 
	 * @param defNodes
	 * @return
	 * @throws Exception
	 */
	public static ProtocolCollection parseFromNode(Integer protocolPfx, NodeList parseNodes, ProtocolSetting ps)
			throws Exception {
		ProtocolCollection pc = new ProtocolCollection();
		//Element property = null;
		//NodeList categorySet = null;
		Element element = null;

		pc.protocolPfx = protocolPfx;

		int autoGenerateProtocol = 50000;

		for (int i = 0; i < parseNodes.getLength(); i++) {
			Protocol_Q_A pp = new Protocol_Q_A();

			element = (Element) parseNodes.item(i);

			// 提取协议号字符
			String strProtocol = element.getAttribute("protocol");
			// 提取子协议号
			Integer parseProtocol;

			if (strProtocol.toLowerCase().equals("auto")) {
				parseProtocol = autoGenerateProtocol;
				autoGenerateProtocol++;
			} else {
				parseProtocol = Integer.parseInt(element.getAttribute("protocol"));
				if (parseProtocol == null || parseProtocol < 1 || parseProtocol > 49999) {
					throw new Exception("key 'protocol' must be between 1-49999");
				}
			}
			// 根据前缀计算真正协议号(提问协议)
			pp.setProtocol((protocolPfx << 16) + parseProtocol);

			// 提取协议注释
			pp.setComment(element.getAttribute("comment"));
			pp.setClassName(element.getAttribute("className"));
			pp.setSpringBeanName(element.getAttribute("springBeanName"));
			pp.setMethodName(element.getAttribute("methodName"));

			String compress = element.getAttribute("compress");
			String protocolType = element.getAttribute("protocolType");

			if (compress == null || compress.equals("")) {
				pp.setCompress(ps.getDefaultCompress());
			} else {
				pp.setCompress(Integer.parseInt(compress));
			}

			if (protocolType == null || protocolType.equals("")) {
				pp.setProtocolType(ps.getDefaultProtocolType());
			} else {
				pp.setProtocolType(Integer.parseInt(protocolType));
			}

			NodeList fields;
			// 获取提问包
			fields = element.getElementsByTagName("q");
			for (int j = 0; j < fields.getLength(); j++) {
				ProtocolField pf = new ProtocolField();
				Element f = (Element) fields.item(j);
				String targetFieldName = f.getAttribute("fieldName");
				String fieldType = f.getAttribute("fieldType");
				String comString = f.getAttribute("comment");
				String listDetailType = f.getAttribute("listDetailType");
				int len = SysUtil.strToIntDef(f.getAttribute("length"), 0);
				pf.setFieldName(targetFieldName);
				pf.setFieldType(FieldType.valueOf(fieldType));
				pf.setListDetailType(listDetailType);
				pf.setComment(comString);
				pf.setDataLength(len);
				pp.getQuestionFields().add(pf);
			}
			// 获取回答包
			fields = element.getElementsByTagName("a");
			for (int j = 0; j < fields.getLength(); j++) {
				ProtocolField pf = new ProtocolField();
				Element f = (Element) fields.item(j);
				String targetFieldName = f.getAttribute("fieldName");
				String fieldType = f.getAttribute("fieldType");
				String comString = f.getAttribute("comment");
				String listDetailType = f.getAttribute("listDetailType");
				int len = SysUtil.strToIntDef(f.getAttribute("length"), 0);
				pf.setFieldName(targetFieldName);
				pf.setFieldType(FieldType.valueOf(fieldType));
				pf.setComment(comString);
				pf.setListDetailType(listDetailType);
				pf.setDataLength(len);
				pp.getAnwserFields().add(pf);
			}

			// 把包加入
			pc.protocolPackageList.add(pp);
		}

		return pc;
	}

	public int getProtocolPfx() {
		return protocolPfx;
	}

	public void setProtocolPfx(int protocolPfx) {
		this.protocolPfx = protocolPfx;
	}

	public ArrayList<Protocol_Q_A> getProtocolPackageList() {
		return protocolPackageList;
	}

	public void setProtocolPackageList(ArrayList<Protocol_Q_A> protocolPackageList) {
		this.protocolPackageList = protocolPackageList;
	}

	public ArrayList<DefineConst> getDefineConsts() {
		return defineConsts;
	}

	public void setDefineConsts(ArrayList<DefineConst> defineConsts) {
		this.defineConsts = defineConsts;
	}

}

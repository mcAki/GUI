package com.mprs.protocol;

import java.util.ArrayList;
import java.util.List;

import com.mprs.protocol.GenerateProtocolPackage.ProtocolField;
import com.mprs.util.PackageUtil;

/**
 * 把XML配置映射到该解析容器
 * 
 * @author 郭熹
 * 
 */
public class ProtocolContainer{

	private List<ProtocolField> parsetList = new ArrayList<ProtocolField>(); // 从XML配置文件获得要解析的属性
	private Integer protocol;// 从XML配置文件获得要解析的消息类型
	private String subPackageName;//所属子包
	private String className;//包类名
	private String nameSpaceClass;// 消息要转换的VO
	private String springBeanName;//对应Spring的事务
	private String comment;//注释
	private String methodName;//对应处理的方法名称
	private Integer compress=0;
	private Integer protocolType=PackageUtil.msgType_AMF;
	
	// spring bean name
	public List<ProtocolField> getParsetList() {
		return parsetList;
	}

	public void setParsetList(List<ProtocolField> parsetList) {
		this.parsetList = parsetList;
	}

	public Integer getParseProtocol() {
		return protocol;
	}

	public void setParseProcotol(Integer parseProtocol) {
		this.protocol = parseProtocol;
	}

	public String getNameSpaceClass() {
		return nameSpaceClass;
	}

	public void setNameSpaceClass(String nameSpaceClass) {
		this.nameSpaceClass = nameSpaceClass;
	}

	public String getSpringBeanName() {
		return springBeanName;
	}

	public void setSpringBeanName(String springBeanName) {
		this.springBeanName = springBeanName;
	}
	
	public String getSubPackageName() {
		return subPackageName;
	}

	public void setSubPackageName(String subPackageName) {
		this.subPackageName = subPackageName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Integer getCompress() {
		return compress;
	}

	public void setCompress(Integer compress) {
		this.compress = compress;
	}

	public Integer getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(Integer protocolType) {
		this.protocolType = protocolType;
	}



}

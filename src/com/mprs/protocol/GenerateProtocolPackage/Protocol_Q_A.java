package com.mprs.protocol.GenerateProtocolPackage;

import java.util.ArrayList;

public class Protocol_Q_A {
	private int protocol;
	private String comment;
	private String className;
	private String springBeanName;	 
	private String methodName;//对应处理的方法名称
	private Integer compress;
	private Integer protocolType;
	
	
	private ArrayList<ProtocolField> questionFields = new ArrayList<ProtocolField>();
	private ArrayList<ProtocolField> anwserFields = new ArrayList<ProtocolField>();


	public String formatClassName(String formatStr){
		return formatStr.replace("%", className); 
	}
	
	public int getProtocol() {
		return protocol;
	}
	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public ArrayList<ProtocolField> getQuestionFields() {
		return questionFields;
	}
	public void setQuestionFields(ArrayList<ProtocolField> questionFields) {
		this.questionFields = questionFields;
	}
	public ArrayList<ProtocolField> getAnwserFields() {
		return anwserFields;
	}
	public void setAnwserFields(ArrayList<ProtocolField> anwserFields) {
		this.anwserFields = anwserFields;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSpringBeanName() {
		return springBeanName;
	}
	public void setSpringBeanName(String springBeanName) {
		this.springBeanName = springBeanName;
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

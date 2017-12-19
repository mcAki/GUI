package com.mprs.protocol.GenerateProtocolPackage;

import com.mprs.protocol.FieldType;

/**
 * 配置文件定义服务器客户端公用常量表字段
 * @author Seraph.Yang
 *
 */
public class DefineConst {

	private FieldType fieldType;//解析的类型	
	private String fieldName;//字段名
	private String comment;//注释	
	private String constValue;//常量的值
	
	public FieldType getFieldType() {
		return fieldType;
	}
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getConstValue() {
		return constValue;
	}
	public void setConstValue(String constValue) {
		this.constValue = constValue;
	}
	
}

package com.mprs.protocol.GenerateProtocolPackage;

import com.mprs.protocol.FieldType;

public class ProtocolField {

	private FieldType fieldType;//解析的类型	
	private String fieldName;//字段名
	private String comment;//注释
	private int dataLength;//解析变长字符串时，要解析的长度
	private String listDetailType;
	
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
	public int getDataLength() {
		return dataLength;
	}
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
	public String getListDetailType() {
		return listDetailType;
	}
	public void setListDetailType(String listDetailType) {
		this.listDetailType = listDetailType;
	}

}

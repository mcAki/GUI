package com.sys.volunteer.vo;
/**
 * 超级布尔值(带原因)
 * @author Administrator
 *
 */
public class BooleanEx {
	/**
	 * 布尔值
	 */
	private boolean boolValue;
	/**
	 * 原因
	 */
	private String reason;
	private int reasonId;
	
	/**
	 * 创建带原因的布尔值
	 * @param boolValue
	 * @param reason
	 */
	public BooleanEx(boolean boolValue, String reason) {
		super();
		this.boolValue = boolValue;
		this.reason = reason;
	}
	
	/**
	 * 创建带原因的布尔值
	 * @param boolValue
	 * @param reason
	 * @param reasonId
	 */
	public BooleanEx(boolean boolValue, String reason,int reasonId) {
		super();
		this.boolValue = boolValue;
		this.reason = reason;
		this.reasonId=reasonId;
	}
	public BooleanEx() {
		
	}

	public boolean isBoolValue() {
		return boolValue;
	}

	public void setBoolValue(boolean boolValue) {
		this.boolValue = boolValue;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}
	
}

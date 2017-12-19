package com.sys.volunteer.pojo;

public class CardInfo {

	/**
	 * 卡号
	 */
	private String cardCode;
	/**
	 * 卡密
	 */
	private String password;
	/**
	 * 有效时间
	 */
	private long time;
	
	public String getCardCode() {
		return cardCode;
	}
	
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}

	public CardInfo(String cardCode, String password, long time) {
		super();
		this.cardCode = cardCode;
		this.password = password;
		this.time = time;
	}
}

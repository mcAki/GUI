package com.sys.volunteer.ka;

public class Query91KARechargeOrder {

	private String cpid;
	private String cpOrderNo;
	private String kaOrderNo;
	private String retResult;
	private String sign;
	public String getCpid() {
		return cpid;
	}
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	public String getRetResult() {
		return retResult;
	}
	public void setRetResult(String retResult) {
		this.retResult = retResult;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getCpOrderNo() {
		return cpOrderNo;
	}
	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}
	public String getKaOrderNo() {
		return kaOrderNo;
	}
	public void setKaOrderNo(String kaOrderNo) {
		this.kaOrderNo = kaOrderNo;
	}
	
	/**
	 * 初始化
	 * @param arg
	 * @return
	 */
	public Query91KARechargeOrder init(String arg){
		if (arg==null||arg.equals("")) {
			return null;
		}
		String[] params = arg.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] childParas = params[i].split("=");
			String key = childParas[0];
			String value = "";
			if (childParas.length==2) {
				value = childParas[1];
			}
			this.parseType(this, key, value);
		}
		
		return this;
	}
	
	/**
	 * 转化对应类型
	 * @param order91ka
	 * @param key
	 * @param value
	 * @return
	 */
	public Query91KARechargeOrder parseType(Query91KARechargeOrder gameRecharge,String key,String value){
		if (key.equals("cpid")) {
			gameRecharge.setCpid(value);
		}
		if (key.equals("cp_order_no")) {
			gameRecharge.setCpid(value);
		}
		if (key.equals("91ka_order_no")) {
			gameRecharge.setKaOrderNo(value);
		}
		if (key.equals("ret_result")) {
			gameRecharge.setRetResult(value);
		}
		if (key.equals("sign")) {
			gameRecharge.setSign(value);
		}
		return gameRecharge;
	}
	
}

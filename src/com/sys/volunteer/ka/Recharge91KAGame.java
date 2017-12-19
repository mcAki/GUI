package com.sys.volunteer.ka;

public class Recharge91KAGame {

	private String cpid;
	private String gameId;
	private String createTime;
	private String cpOrderNo;
	private String kaOrderNo;
	private String retPara;
	private String retResult;
	private String chargeDesc;
	private String chargeMsg;
	private String chargeReturn;
	private String sign;
	public String getCpid() {
		return cpid;
	}
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public String getRetPara() {
		return retPara;
	}
	public void setRetPara(String retPara) {
		this.retPara = retPara;
	}
	public String getChargeDesc() {
		return chargeDesc;
	}
	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}
	public String getChargeMsg() {
		return chargeMsg;
	}
	public void setChargeMsg(String chargeMsg) {
		this.chargeMsg = chargeMsg;
	}
	public String getChargeReturn() {
		return chargeReturn;
	}
	public void setChargeReturn(String chargeReturn) {
		this.chargeReturn = chargeReturn;
	}
	
	/**
	 * 初始化
	 * @param arg
	 * @return
	 */
	public Recharge91KAGame init(String arg){
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
	public Recharge91KAGame parseType(Recharge91KAGame gameRecharge,String key,String value){
		if (key.equals("cpid")) {
			gameRecharge.setCpid(value);
		}
		if (key.equals("game_id")) {
			gameRecharge.setGameId(value);
		}
		if (key.equals("create_time")) {
			gameRecharge.setCreateTime(value);
		}
		if (key.equals("cp_order_no")) {
			gameRecharge.setCpid(value);
		}
		if (key.equals("91ka_order_no")) {
			gameRecharge.setKaOrderNo(value);
		}
		if (key.equals("ret_para")) {
			gameRecharge.setRetPara(value);
		}
		if (key.equals("ret_result")) {
			gameRecharge.setRetResult(value);
		}
		if (key.equals("charge_desc")) {
			gameRecharge.setChargeDesc(value);
		}
		if (key.equals("charge_msg")) {
			gameRecharge.setChargeMsg(value);
		}
		if (key.equals("charge_return")) {
			gameRecharge.setChargeReturn(value);
		}
		if (key.equals("sign")) {
			gameRecharge.setSign(value);
		}
		return gameRecharge;
	}
	
}

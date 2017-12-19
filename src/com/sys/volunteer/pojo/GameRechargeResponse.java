package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MissionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "91ka_order_response", catalog = "mprs")
public class GameRechargeResponse {

	private Integer orderResponseId;
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
	private String orderId;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "order_response_id", unique = true, nullable = false)
	public Integer getOrderResponseId() {
		return orderResponseId;
	}
	
	public void setOrderResponseId(Integer orderResponseId) {
		this.orderResponseId = orderResponseId;
	}
	
	@Column(name="cpid")
	public String getCpid() {
		return cpid;
	}
	
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	
	@Column(name="game_id")
	public String getGameId() {
		return gameId;
	}
	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="cp_order_no")
	public String getCpOrderNo() {
		return cpOrderNo;
	}
	
	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}
	
	@Column(name="91ka_order_no")
	public String getKaOrderNo() {
		return kaOrderNo;
	}
	
	public void setKaOrderNo(String kaOrderNo) {
		this.kaOrderNo = kaOrderNo;
	}
	
	@Column(name="ret_para")
	public String getRetPara() {
		return retPara;
	}
	
	public void setRetPara(String retPara) {
		this.retPara = retPara;
	}
	
	@Column(name="ret_result")
	public String getRetResult() {
		return retResult;
	}
	
	public void setRetResult(String retResult) {
		this.retResult = retResult;
	}
	
	@Column(name="charge_desc")
	public String getChargeDesc() {
		return chargeDesc;
	}
	
	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}
	
	@Column(name="charge_msg")
	public String getChargeMsg() {
		return chargeMsg;
	}
	
	public void setChargeMsg(String chargeMsg) {
		this.chargeMsg = chargeMsg;
	}
	
	@Column(name="charge_return")
	public String getChargeReturn() {
		return chargeReturn;
	}
	
	public void setChargeReturn(String chargeReturn) {
		this.chargeReturn = chargeReturn;
	}
	
	@Column(name="sign")
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@Column(name="order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 初始化
	 * @param arg
	 * @return
	 */
	public GameRechargeResponse init(String arg){
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
	public GameRechargeResponse parseType(GameRechargeResponse gameRecharge,String key,String value){
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
			gameRecharge.setCpOrderNo(value);
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

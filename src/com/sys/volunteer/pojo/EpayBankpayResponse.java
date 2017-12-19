package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "epay_bankpay_response", catalog = "mprs")
public class EpayBankpayResponse implements java.io.Serializable{

	private Integer responseId;
	private String tranCode;
	private String termNo;
	private String trackNo;
	private String respCode;
	private String respMsg;
	private String sign;
	private String finishTime;
	private String retCode;
	private String retMsg;
	private String busiRefNo;
	private String balance;
	private String serviceFee;
	private String orderNo;
	private String amount;
	private String summary;
	private String xmlText;
	private String merNo;
	private String userId;
	private String userName;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "response_id", unique = true, nullable = false)
	public Integer getResponseId() {
		return responseId;
	}

	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}

	@Column(name="tranCode")
	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	@Column(name="termNo")
	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	@Column(name="trackNo")
	public String getTrackNo() {
		return trackNo;
	}

	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}

	@Column(name="sign")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name="orderNo")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name="respCode")
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	@Column(name="respMsg")
	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	@Column(name="finishTime")
	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name="retCode")
	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	@Column(name="retMsg")
	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	@Column(name="busiRefNo")
	public String getBusiRefNo() {
		return busiRefNo;
	}

	public void setBusiRefNo(String busiRefNo) {
		this.busiRefNo = busiRefNo;
	}

	@Column(name="balance")
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Column(name="serviceFee")
	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	@Column(name="amount")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name="summary")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name="xmlText")
	public String getXmlText() {
		return xmlText;
	}

	public void setXmlText(String xmlText) {
		this.xmlText = xmlText;
	}
	
	@Column(name="merNo")
	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public static EpayBankpayResponse init(EpayBankpayResponse response, Map<String, String> map,String xmlText){
		if (!map.isEmpty()) {
			if (map.containsKey("tranCode")) {
				response.setTranCode(map.get("tranCode"));
			}
			if (map.containsKey("termNo")) {
				response.setTermNo(map.get("termNo"));
			}
			if (map.containsKey("trackNo")) {
				response.setTrackNo(map.get("trackNo"));
			}
			if (map.containsKey("respCode")) {
				response.setRespCode(map.get("respCode"));
			}
			if (map.containsKey("sign")) {
				response.setSign(map.get("sign"));
			}
			if (map.containsKey("respMsg")) {
				response.setRespMsg(map.get("respMsg"));
			}
			if (map.containsKey("finishTime")) {
				response.setFinishTime(map.get("finishTime"));
			}
			if (map.containsKey("retCode")) {
				response.setRetCode(map.get("retCode"));
			}
			if (map.containsKey("retMsg")) {
				response.setRetMsg(map.get("retMsg"));
			}
			if (map.containsKey("busiRefNo")) {
				response.setBusiRefNo(map.get("busiRefNo"));
			}
			if (map.containsKey("orderNo")) {
				response.setOrderNo(map.get("orderNo"));
			}
			if (map.containsKey("amount")) {
				response.setAmount(map.get("amount"));
			}
			if (map.containsKey("balance")) {
				response.setBalance(map.get("balance"));
			}
			if (map.containsKey("serviceFee")) {
				response.setServiceFee(map.get("serviceFee"));
			}
			if (map.containsKey("summary")) {
				response.setSummary(map.get("summary"));
			}
			if (map.containsKey("merNo")) {
				response.setMerNo(map.get("merNo"));
			}
			response.setXmlText(xmlText);
		}
		return response;
	}

	

	

	
}

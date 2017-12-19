package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "liandong", catalog = "mprs")
public class Liandong implements java.io.Serializable{

	private Integer liandongId;
	private String UserName;
	private String OrderID;
	private String CodeSN;
	private String Mobile;
	private String Money;
	private String Status;
	private String ReverseStatus;
	private String Account;
	private Integer isDeal;
	private Integer ErrorID;
	private String ErrorMsg;
	private String xmlText;
	private String queryXmlText;
	private String reverseXmlText;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "liandong_id", unique = true, nullable = false)
	public Integer getLiandongId() {
		return liandongId;
	}

	public void setLiandongId(Integer liandongId) {
		this.liandongId = liandongId;
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	@Column(name="order_id")
	public String getOrderID() {
		return OrderID;
	}
	
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	
	@Column(name="code_sn")
	public String getCodeSN() {
		return CodeSN;
	}
	
	public void setCodeSN(String codeSN) {
		CodeSN = codeSN;
	}
	
	@Column(name="mobile")
	public String getMobile() {
		return Mobile;
	}
	
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	
	@Column(name="money")
	public String getMoney() {
		return Money;
	}
	
	public void setMoney(String money) {
		Money = money;
	}
	
	@Column(name="status")
	public String getStatus() {
		return Status;
	}
	
	public void setStatus(String status) {
		Status = status;
	}
	
	@Column(name="account")
	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}
	
	@Column(name="is_deal")
	public Integer getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}
	
	@Column(name="errorId")
	public Integer getErrorID() {
		return ErrorID;
	}

	public void setErrorID(Integer errorID) {
		ErrorID = errorID;
	}

	@Column(name="errorMsg")
	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}

	@Column(name="xml_text")
	public String getXmlText() {
		return xmlText;
	}
	
	public void setXmlText(String xmlText) {
		this.xmlText = xmlText;
	}
	
	@Column(name="query_xml_text")
	public String getQueryXmlText() {
		return queryXmlText;
	}

	public void setQueryXmlText(String queryXmlText) {
		this.queryXmlText = queryXmlText;
	}

	@Column(name="reverse_status")
	public String getReverseStatus() {
		return ReverseStatus;
	}

	public void setReverseStatus(String reverseStatus) {
		ReverseStatus = reverseStatus;
	}

	@Column(name="reverse_xml_text")
	public String getReverseXmlText() {
		return reverseXmlText;
	}

	public void setReverseXmlText(String reverseXmlText) {
		this.reverseXmlText = reverseXmlText;
	}

	
	
	public static Liandong init(Map<String, String> map,int time,String xmlText){
		Liandong liandong=new Liandong();
		if (!map.isEmpty()) {
			if (map.containsKey("UserName")) {
				liandong.setUserName(map.get("UserName"));
			}
			if (map.containsKey("OrderID")) {
				liandong.setOrderID(map.get("OrderID"));
			}
			if (map.containsKey("CodeSN")) {
				liandong.setCodeSN(map.get("CodeSN"));
			}
			if (map.containsKey("Mobile")) {
				liandong.setMobile(map.get("Mobile"));
			}
			if (map.containsKey("Money")) {
				liandong.setMoney(map.get("Money"));
			}
			if (map.containsKey("Status")) {
				liandong.setStatus(map.get("Status"));
			}
			if (map.containsKey("ReverseStatus")) {
				liandong.setReverseStatus(map.get("ReverseStatus"));
			}
			if (map.containsKey("Account")) {
				liandong.setAccount(map.get("Account"));
			}
			if (map.containsKey("ErrorID")) {
				liandong.setErrorID(Integer.valueOf(map.get("ErrorID")));
			}
			if (map.containsKey("ErrorMsg")) {
				liandong.setErrorMsg(map.get("ErrorMsg"));
			}
			switch (time) {
			case 1:
				liandong.setXmlText(xmlText);
				break;
			case 2:
				liandong.setQueryXmlText(xmlText);
				break;
			case 3:
				liandong.setReverseXmlText(xmlText);
				break;
			}
		}
		return liandong;
	}

	
}

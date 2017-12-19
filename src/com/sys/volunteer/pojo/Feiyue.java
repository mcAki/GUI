package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MissionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "feiyue", catalog = "mprs")
public class Feiyue {

	private Integer feiyueId;
	private String agtte1;
	private String phone;
	private String money;
	private String sn;
	private String pwd;
	private String streamid;
	private String balance;
	private String opmoney;
	private String optime;
	private String state;
	private String mutid;
	private String retcode;
	private String retmessage;
	private String orderId;
	private String xmlText;
	private String reverseXmlText;
	private String queryXmlText;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "feiyue_id", unique = true, nullable = false)
	public Integer getFeiyueId() {
		return feiyueId;
	}
	
	public void setFeiyueId(Integer feiyueId) {
		this.feiyueId = feiyueId;
	}
	
	@Column(name="agtte1")
	public String getAgtte1() {
		return agtte1;
	}
	
	public void setAgtte1(String agtte1) {
		this.agtte1 = agtte1;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="money")
	public String getMoney() {
		return money;
	}
	
	public void setMoney(String money) {
		this.money = money;
	}
	
	@Column(name="sn")
	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	@Column(name="pwd")
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Column(name="streamid")
	public String getStreamid() {
		return streamid;
	}
	
	public void setStreamid(String streamid) {
		this.streamid = streamid;
	}
	
	@Column(name="balance")
	public String getBalance() {
		return balance;
	}
	
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	@Column(name="opmoney")
	public String getOpmoney() {
		return opmoney;
	}
	
	public void setOpmoney(String opmoney) {
		this.opmoney = opmoney;
	}
	
	@Column(name="optime")
	public String getOptime() {
		return optime;
	}
	
	public void setOptime(String optime) {
		this.optime = optime;
	}
	
	@Column(name="state")
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="mutid")
	public String getMutid() {
		return mutid;
	}
	
	public void setMutid(String mutid) {
		this.mutid = mutid;
	}
	
	@Column(name="retcode")
	public String getRetcode() {
		return retcode;
	}
	
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	
	@Column(name="retmessage")
	public String getRetmessage() {
		return retmessage;
	}
	
	public void setRetmessage(String retmessage) {
		this.retmessage = retmessage;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column(name="xml_text")
	public String getXmlText() {
		return xmlText;
	}
	
	public void setXmlText(String xmlText) {
		this.xmlText = xmlText;
	}
	
	@Column(name="reverse_xml_text")
	public String getReverseXmlText() {
		return reverseXmlText;
	}
	
	public void setReverseXmlText(String reverseXmlText) {
		this.reverseXmlText = reverseXmlText;
	}
	
	@Column(name="query_xml_text")
	public String getQueryXmlText() {
		return queryXmlText;
	}
	
	public void setQueryXmlText(String queryXmlText) {
		this.queryXmlText = queryXmlText;
	}
	
	
	public static Feiyue init(Map<String, String> map,int time,String xmlText){
		Feiyue feiyue=new Feiyue();
		if (!map.isEmpty()) {
			if (map.containsKey("phone")) {
				feiyue.setPhone(map.get("phone"));
			}
			if (map.containsKey("streamid")) {
				feiyue.setStreamid(map.get("streamid"));
			}
			if (map.containsKey("balance")) {
				feiyue.setBalance(map.get("balance"));
			}
			if (map.containsKey("opmoney")) {
				feiyue.setOpmoney(map.get("opmoney"));
			}
			if (map.containsKey("Money")) {
				feiyue.setMoney(map.get("Money"));
			}
			if (map.containsKey("optime")) {
				feiyue.setOptime(map.get("optime"));
			}
			if (map.containsKey("state")) {
				feiyue.setState(map.get("state"));
			}
			if (map.containsKey("mutid")) {
				feiyue.setMutid(map.get("mutid"));
			}
			if (map.containsKey("retcode")) {
				feiyue.setRetcode(map.get("retcode"));
			}
			if (map.containsKey("retmessage")) {
				feiyue.setRetmessage(map.get("retmessage"));
			}
			switch (time) {
			case 1:
				feiyue.setXmlText(xmlText);
				break;
			case 2:
				feiyue.setQueryXmlText(xmlText);
				break;
			case 3:
				feiyue.setReverseXmlText(xmlText);
				break;
			}
		}
		return feiyue;
	}
}

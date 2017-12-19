package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esai_charge", catalog = "mprs")
public class ESaiCharge {

	private Integer esaiChargeId;
	private String UserNumber;
	private String PhoneNumber;
	private String PhoneMoney;
	private String Province;
	private String City;
	private String PhoneClass;
	private String result;
	private String RecordKey;
	private String Remark;
	private String InOrderNumber;
	private String OutOrderNumber;
	private String SellPrice;
	private String StartTime;
	private String TimeOut;
	private String xmlText;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "esai_charge_id", unique = true, nullable = false)
	public Integer getEsaiChargeId() {
		return esaiChargeId;
	}
	
	public void setEsaiChargeId(Integer esaiChargeId) {
		this.esaiChargeId = esaiChargeId;
	}
	
	@Column(name="UserNumber")
	public String getUserNumber() {
		return UserNumber;
	}
	
	public void setUserNumber(String userNumber) {
		UserNumber = userNumber;
	}
	
	@Column(name="PhoneNumber")
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
	@Column(name="Province")
	public String getProvince() {
		return Province;
	}
	
	public void setProvince(String province) {
		Province = province;
	}
	
	@Column(name="City")
	public String getCity() {
		return City;
	}
	
	public void setCity(String city) {
		City = city;
	}
	
	@Column(name="PhoneClass")
	public String getPhoneClass() {
		return PhoneClass;
	}
	
	public void setPhoneClass(String phoneClass) {
		PhoneClass = phoneClass;
	}
	
	@Column(name="result")
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	@Column(name="RecordKey")
	public String getRecordKey() {
		return RecordKey;
	}
	
	public void setRecordKey(String recordKey) {
		RecordKey = recordKey;
	}
	
	@Column(name="Remark")
	public String getRemark() {
		return Remark;
	}
	
	public void setRemark(String remark) {
		Remark = remark;
	}
	
	@Column(name="InOrderNumber")
	public String getInOrderNumber() {
		return InOrderNumber;
	}
	
	public void setInOrderNumber(String inOrderNumber) {
		InOrderNumber = inOrderNumber;
	}
	
	@Column(name="OutOrderNumber")
	public String getOutOrderNumber() {
		return OutOrderNumber;
	}
	
	public void setOutOrderNumber(String outOrderNumber) {
		OutOrderNumber = outOrderNumber;
	}
	
	@Column(name="SellPrice")
	public String getSellPrice() {
		return SellPrice;
	}
	
	public void setSellPrice(String sellPrice) {
		SellPrice = sellPrice;
	}
	
	@Column(name="TimeOut")
	public String getTimeOut() {
		return TimeOut;
	}
	
	public void setTimeOut(String timeOut) {
		TimeOut = timeOut;
	}
	
	@Column(name="xmlText",length = 1000)
	public String getXmlText() {
		return xmlText;
	}
	
	public void setXmlText(String xmlText) {
		this.xmlText = xmlText;
	}
	
	public static ESaiCharge init(Map<String, String> map,String xmlText){
		ESaiCharge eSaiCharge=new ESaiCharge();
		if (!map.isEmpty()) {
			if (map.containsKey("result")) {
				eSaiCharge.setResult(map.get("result"));
			}
			if (map.containsKey("inOrdernumber")) {
				eSaiCharge.setInOrderNumber(map.get("inOrdernumber"));
			}
			if (map.containsKey("outOrderNumber")) {
				eSaiCharge.setOutOrderNumber(map.get("outOrderNumber"));
			}
			if (map.containsKey("remark")) {
				eSaiCharge.setRemark(map.get("remark"));
			}
			if (map.containsKey("recordKey")) {
				eSaiCharge.setRecordKey(map.get("recordKey"));
			}
			eSaiCharge.setXmlText(xmlText);
		}
		return eSaiCharge;
	}

	@Column(name="PhoneMoney")
	public String getPhoneMoney() {
		return PhoneMoney;
	}

	public void setPhoneMoney(String phoneMoney) {
		PhoneMoney = phoneMoney;
	}

	@Column(name="StartTime")
	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	
}

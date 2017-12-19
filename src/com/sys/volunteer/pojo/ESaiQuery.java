package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esai_query", catalog = "mprs")
public class ESaiQuery {

	private Integer esaiQueryId;
	private String UserNumber;
	private String payResult;
	private String RecordKey;
	private String Remark;
	private String InOrderNumber;
	private String OutOrderNumber;
	private String QueryType;
	private String xmlText;
	private String result;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "esai_query_id", unique = true, nullable = false)
	public Integer getEsaiQueryId() {
		return esaiQueryId;
	}
	
	public void setEsaiQueryId(Integer esaiQueryId) {
		this.esaiQueryId = esaiQueryId;
	}
	
	@Column(name="UserNumber")
	public String getUserNumber() {
		return UserNumber;
	}
	
	public void setUserNumber(String UserNumber) {
		this.UserNumber = UserNumber;
	}
	
	@Column(name="payResult")
	public String getPayResult() {
		return payResult;
	}
	
	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}
	
	@Column(name="RecordKey")
	public String getRecordKey() {
		return RecordKey;
	}
	
	public void setRecordKey(String RecordKey) {
		this.RecordKey = RecordKey;
	}
	
	@Column(name="Remark")
	public String getRemark() {
		return Remark;
	}
	
	public void setRemark(String Remark) {
		this.Remark = Remark;
	}
	
	@Column(name="InOrderNumber")
	public String getInOrderNumber() {
		return InOrderNumber;
	}
	
	public void setInOrderNumber(String InOrderNumber) {
		this.InOrderNumber = InOrderNumber;
	}
	
	@Column(name="OutOrderNumber")
	public String getOutOrderNumber() {
		return OutOrderNumber;
	}
	
	public void setOutOrderNumber(String OutOrderNumber) {
		this.OutOrderNumber = OutOrderNumber;
	}
	
	@Column(name="QueryType")
	public String getQueryType() {
		return QueryType;
	}
	
	public void setQueryType(String QueryType) {
		this.QueryType = QueryType;
	}
	
	
	@Column(name="xmlText",length = 1000)
	public String getXmlText() {
		return xmlText;
	}
	
	public void setXmlText(String xmlText) {
		this.xmlText = xmlText;
	}
	
	@Column(name="result")
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	
	public static ESaiQuery init(Map<String, String> map,String xmlText){
		ESaiQuery eSaiQuery=new ESaiQuery();
		if (!map.isEmpty()) {
			if (map.containsKey("result")) {
				eSaiQuery.setResult(map.get("result"));
			}
			if (map.containsKey("payResult")) {
				eSaiQuery.setPayResult(map.get("payResult"));
			}
			if (map.containsKey("queryType")) {
				eSaiQuery.setQueryType(map.get("queryType"));
			}
			if (map.containsKey("inOrdernumber")) {
				eSaiQuery.setInOrderNumber(map.get("inOrdernumber"));
			}
			if (map.containsKey("outOrderNumber")) {
				eSaiQuery.setOutOrderNumber(map.get("outOrderNumber"));
			}
			if (map.containsKey("remark")) {
				eSaiQuery.setRemark(map.get("remark"));
			}
			if (map.containsKey("recordKey")) {
				eSaiQuery.setRecordKey(map.get("recordKey"));
			}
			eSaiQuery.setXmlText(xmlText);
		}
		return eSaiQuery;
	}
	
}

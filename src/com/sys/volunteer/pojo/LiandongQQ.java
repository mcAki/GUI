package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "liandong_qq", catalog = "mprs")
public class LiandongQQ implements java.io.Serializable{

	private Integer liandongQQId;
	private String UserName;
	private String OrderID;
	private String OrderSN;
	private String QQnum;
	private Integer BuyCount;
	private String Type;
	private String Status;
	private Integer isDeal;
	private String xmlText;
	private String queryXmlText;
	private String Comm1;
	private String Demo;
	private String OrderSN97;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "liandong_qq_id", unique = true, nullable = false)
	public Integer getLiandongQQId() {
		return liandongQQId;
	}

	public void setLiandongQQId(Integer liandongQQId) {
		this.liandongQQId = liandongQQId;
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
	
	@Column(name="OrderSN")
	public String getOrderSN() {
		return OrderSN;
	}
	
	public void setOrderSN(String OrderSN) {
		this.OrderSN = OrderSN;
	}
	
	@Column(name="QQnum")
	public String getQQnum() {
		return QQnum;
	}
	
	public void setQQnum(String QQnum) {
		this.QQnum = QQnum;
	}
	
	@Column(name="BuyCount")
	public Integer getBuyCount() {
		return BuyCount;
	}
	
	public void setBuyCount(Integer BuyCount) {
		this.BuyCount = BuyCount;
	}
	
	@Column(name="status")
	public String getStatus() {
		return Status;
	}
	
	public void setStatus(String status) {
		Status = status;
	}
	
	@Column(name="97OrderSN")
	public String getOrderSN97() {
		return OrderSN97;
	}

	public void setOrderSN97(String OrderSN97) {
		this.OrderSN97 = OrderSN97;
	}
	
	@Column(name="is_deal")
	public Integer getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}
	
	@Column(name="Comm1")
	public String getComm1() {
		return Comm1;
	}

	public void setComm1(String Comm1) {
		this.Comm1 = Comm1;
	}

	@Column(name="Demo")
	public String getDemo() {
		return Demo;
	}

	public void setDemo(String Demo) {
		this.Demo = Demo;
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


	
	
	public static LiandongQQ init(Map<String, String> map,int time,String xmlText){
		LiandongQQ liandongQQ=new LiandongQQ();
		if (!map.isEmpty()) {
			if (map.containsKey("UserName")) {
				liandongQQ.setUserName(map.get("UserName"));
			}
			if (map.containsKey("OrderID")) {
				liandongQQ.setOrderID(map.get("OrderID"));
			}
			if (map.containsKey("OrderSN")) {
				liandongQQ.setOrderSN(map.get("OrderSN"));
			}
			if (map.containsKey("QQnum")) {
				liandongQQ.setQQnum(map.get("QQnum"));
			}
			if (map.containsKey("BuyCount")) {
				liandongQQ.setBuyCount(Integer.parseInt(map.get("BuyCount")));
			}
			if (map.containsKey("Status")) {
				liandongQQ.setStatus(map.get("Status"));
			}
			if (map.containsKey("OrderSN")) {
				liandongQQ.setOrderSN97(map.get("97OrderSN"));
			}
			if (map.containsKey("Comm1")) {
				liandongQQ.setComm1(map.get("Comm1"));
			}
			if (map.containsKey("Demo")) {
				liandongQQ.setDemo(map.get("Demo"));
			}
			switch (time) {
			case 1:
				liandongQQ.setXmlText(xmlText);
				break;
			case 2:
				liandongQQ.setQueryXmlText(xmlText);
				break;
			}
		}
		return liandongQQ;
	}

	@Column(name="[Type]")
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	
}

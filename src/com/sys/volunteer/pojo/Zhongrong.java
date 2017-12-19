package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zhongrong", catalog = "mprs")
public class Zhongrong implements java.io.Serializable{

	private Integer zhongrongId;
	private String userid;
	private String orderid;
	private String productid;
	private String productName;
	private String productType;
	private String ordercash;
	private String price;
	private String num;
	private String mobile;
	private String spordertime;
	private String sporderid;
	private String resultno;
	private Integer isDeal;
	private String xmlText;
	private String queryXmlText;
	private Integer isGame;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "zhongrong_id", unique = true, nullable = false)
	public Integer getZhongrongId() {
		return zhongrongId;
	}

	public void setZhongrongId(Integer zhongrongId) {
		this.zhongrongId = zhongrongId;
	}
	
	@Column(name="userid")
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Column(name="orderid")
	public String getOrderid() {
		return orderid;
	}
	
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name="price")
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Column(name="resultno")
	public String getResultno() {
		return resultno;
	}
	
	public void setResultno(String resultno) {
		this.resultno = resultno;
	}
	
	@Column(name="is_deal")
	public Integer getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
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

	@Column(name="productid")
	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	@Column(name="productname")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name="productType")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column(name="ordercash")
	public String getOrdercash() {
		return ordercash;
	}

	public void setOrdercash(String ordercash) {
		this.ordercash = ordercash;
	}

	@Column(name="num")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Column(name="spordertime")
	public String getSpordertime() {
		return spordertime;
	}

	public void setSpordertime(String spordertime) {
		this.spordertime = spordertime;
	}

	@Column(name="sporderid")
	public String getSporderid() {
		return sporderid;
	}

	public void setSporderid(String sporderid) {
		this.sporderid = sporderid;
	}
	
	@Column(name="is_game")
	public Integer getIsGame() {
		return isGame;
	}

	public void setIsGame(Integer isGame) {
		this.isGame = isGame;
	}

	public static Zhongrong init(Map<String, String> map,int time,String xmlText){
		Zhongrong zhongrong=new Zhongrong();
		if (!map.isEmpty()) {
			if (map.containsKey("userid")) {
				zhongrong.setUserid(map.get("userid"));
			}
			if (map.containsKey("productid")) {
				zhongrong.setProductid(map.get("productid"));
			}
			if (map.containsKey("productType")) {
				zhongrong.setProductType(map.get("productType"));
			}
			if (map.containsKey("ordercash")) {
				zhongrong.setOrdercash(map.get("ordercash"));
			}
			if (map.containsKey("price")) {
				zhongrong.setPrice(map.get("price"));
			}
			if (map.containsKey("num")) {
				zhongrong.setNum(map.get("num"));
			}
			if (map.containsKey("mobile")) {
				zhongrong.setMobile(map.get("mobile"));
			}
			if (map.containsKey("spordertime")) {
				zhongrong.setSpordertime(map.get("spordertime"));
			}
			if (map.containsKey("sporderid")) {
				zhongrong.setSporderid(map.get("sporderid"));
			}
			if (map.containsKey("resultno")) {
				zhongrong.setResultno(map.get("resultno"));
			}
			switch (time) {
			case 1:
				zhongrong.setXmlText(xmlText);
				break;
			case 2:
				zhongrong.setQueryXmlText(xmlText);
				break;
			}
		}
		return zhongrong;
	}

}

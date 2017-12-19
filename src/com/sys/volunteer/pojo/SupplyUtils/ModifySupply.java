package com.sys.volunteer.pojo.SupplyUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "supply", catalog = "mprs")
public class ModifySupply implements java.io.Serializable {

	private String supplyName;
	private Integer supplyType;
	private Integer sellType;
	private String contactPeople;
	private String address;
	private String mobile;
	private String desc;
	
	public String getSupplyName() {
		return supplyName;
	}
	
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public Integer getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Integer supplyType) {
		this.supplyType = supplyType;
	}

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}

	public String getContactPeople() {
		return contactPeople;
	}

	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

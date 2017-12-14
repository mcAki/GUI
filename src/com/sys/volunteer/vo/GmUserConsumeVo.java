package com.sys.volunteer.vo;

import java.util.Date;

public class GmUserConsumeVo {

	private String id;
	private String playerId;
	private Integer number;
	private Date createTime;
	private Integer type;
	private Double price;
	private String itemTemplateId;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getItemTemplateId() {
		return itemTemplateId;
	}

	public void setItemTemplateId(String itemTemplateId) {
		this.itemTemplateId = itemTemplateId;
	}
	
	
}

package com.sys.volunteer.vo;

import java.util.Date;

public class GmUserChargeVo {

	private String id;
	private String playerId;
	private Integer amount;
	private Date createTime;
	private Integer type;
	private Double originalPrice;
	private Double discountPrice;
	private Integer sumAmount;
	
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
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
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
	
	public Double getOriginalPrice() {
		return originalPrice;
	}
	
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	public Double getDiscountPrice() {
		return discountPrice;
	}
	
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(Integer sumAmount) {
		this.sumAmount = sumAmount;
	}
	
	
}

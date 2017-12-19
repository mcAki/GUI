package com.sys.volunteer.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MainorderVO implements Serializable {

	private Integer orderType;
	private Double sumValue;
	private Double sumComm;
	private Double sumRetail;
	private Double sumStockPrice;
	
	public Integer getOrderType() {
		return orderType;
	}
	
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	public Double getSumValue() {
		return sumValue;
	}
	
	public void setSumValue(Double sumValue) {
		this.sumValue = sumValue;
	}
	
	public Double getSumComm() {
		return sumComm;
	}
	
	public void setSumComm(Double sumComm) {
		this.sumComm = sumComm;
	}
	
	public Double getSumRetail() {
		return sumRetail;
	}
	
	public void setSumRetail(Double sumRetail) {
		this.sumRetail = sumRetail;
	}

	public Double getSumStockPrice() {
		return sumStockPrice;
	}

	public void setSumStockPrice(Double sumStockPrice) {
		this.sumStockPrice = sumStockPrice;
	}
	
}

package com.sys.volunteer.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatisticsVO implements Serializable {

	private String goodsName;
	private String supplyName;
	private BigDecimal stockPrice;
	private BigDecimal retailPrice;
	private BigDecimal goodsNo;
	private BigDecimal stockTotal;
	private BigDecimal retailTotal;
	private BigDecimal profits;
	private BigDecimal profitsTotal;
	
	public String getGoodsName() {
		return goodsName;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getSupplyName() {
		return supplyName;
	}
	
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	
	public BigDecimal getStockPrice() {
		return stockPrice;
	}
	
	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	public BigDecimal getGoodsNo() {
		return goodsNo;
	}
	
	public void setGoodsNo(BigDecimal goodsNo) {
		this.goodsNo = goodsNo;
	}
	
	public BigDecimal getStockTotal() {
		return stockTotal;
	}
	
	public void setStockTotal(BigDecimal stockTotal) {
		this.stockTotal = stockTotal;
	}
	
	public BigDecimal getRetailTotal() {
		return retailTotal;
	}
	
	public void setRetailTotal(BigDecimal retailTotal) {
		this.retailTotal = retailTotal;
	}
	
	public BigDecimal getProfits() {
		return profits;
	}
	
	public void setProfits(BigDecimal profits) {
		this.profits = profits;
	}
	
	public BigDecimal getProfitsTotal() {
		return profitsTotal;
	}
	
	public void setProfitsTotal(BigDecimal profitsTotal) {
		this.profitsTotal = profitsTotal;
	}

	
	
}

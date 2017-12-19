package com.sys.volunteer.vo;

import java.math.BigInteger;

import com.sys.volunteer.common.SysUtil;

public class SppinUsergroupVo {

	private Integer id;
	private Integer supplyId;
	private String supplyName;
	private Integer goodsId;
	private String goodsName;
	private Double stockPrice;//进货价
	private Double retailPrice;//销售价
	private Double value;//面额；
	private Integer state;//状态
	private Integer canReverse;//能否冲正
	private Integer usergroupId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public Double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCanReverse() {
		return canReverse;
	}
	public void setCanReverse(Integer canReverse) {
		this.canReverse = canReverse;
	}
	public Integer getUsergroupId() {
		return usergroupId;
	}
	public void setUsergroupId(Integer usergroupId) {
		this.usergroupId = usergroupId;
	}
	
	public String getChecked(){
		if(SysUtil.isNull(usergroupId)) return "";
		return "checked='checked'";
	}
	
}

package com.sys.volunteer.muticharge.engine;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Mainorder entity. @author MyEclipse Persistence Tools
 */
public class OrderVO {

	// Fields

	private String mainOrderId;
	private String userId;
	private String userName;
	private String desc;
	private Integer goodsTypeId;
	private String goodsTypeName;
	private Integer goodsId;
	private String goodsName;
	private Integer goodsNo;
	private Long goodsValue;
	private String cardLibIds;
	private Double stockPrice;
	private Double retailPrice;
	private Double totalMoney;
	private Double commission;
	private Date createTime;
	private Integer state;
	private Integer supplyId;
	private String supplyName;
	private Integer supplyInterfaceId;
	private String sign;
	private String mobile;
	private String terminalNo;
	private Integer isTerminal;
	private Integer orderType;
	private Integer canReverse;
	private Integer reversalState;
	private Integer respTime;
	
	private String uniqueCode;
	
	private Double cBalance;

	// Constructors

	/** default constructor */
	public OrderVO() {
	}

	// Property accessors
	public String getMainOrderId() {
		return this.mainOrderId;
	}

	public void setMainOrderId(String mainOrderId) {
		this.mainOrderId = mainOrderId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
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

	public Integer getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(Integer goodsNo) {
		this.goodsNo = goodsNo;
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

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public Integer getIsTerminal() {
		return isTerminal;
	}

	public void setIsTerminal(Integer isTerminal) {
		this.isTerminal = isTerminal;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardLibIds() {
		return cardLibIds;
	}

	public void setCardLibIds(String cardLibIds) {
		this.cardLibIds = cardLibIds;
	}


	public Integer getCanReverse() {
		return canReverse;
	}

	public void setCanReverse(Integer canReverse) {
		this.canReverse = canReverse;
	}

	public Integer getReversalState() {
		return reversalState;
	}

	public void setReversalState(Integer reversalState) {
		this.reversalState = reversalState;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Integer getSupplyInterfaceId() {
		return supplyInterfaceId;
	}

	public void setSupplyInterfaceId(Integer supplyInterfaceId) {
		this.supplyInterfaceId = supplyInterfaceId;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public Integer getRespTime() {
		return respTime;
	}

	public void setRespTime(Integer respTime) {
		this.respTime = respTime;
	}
	
	public Long getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(Long goodsValue) {
		this.goodsValue = goodsValue;
	}

	public Double getcBalance() {
		return cBalance;
	}

	public void setcBalance(Double cBalance) {
		this.cBalance = cBalance;
	}
	
}
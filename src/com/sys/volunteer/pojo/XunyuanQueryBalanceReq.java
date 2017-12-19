package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Mainorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xunyuan_query_balance_req", catalog = "mprs")
public class XunyuanQueryBalanceReq implements java.io.Serializable {

	// Fields

	private Integer queryBalanceReqId;
	private String tradeType;
	private String mobile;
	private String storeSeq;
	private Date createTime;
	
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "query_balance_req_id", unique = true, nullable = false)
	public Integer getQueryBalanceReqId() {
		return this.queryBalanceReqId;
	}

	public void setQueryBalanceReqId(Integer queryBalanceReqId) {
		this.queryBalanceReqId = queryBalanceReqId;
	}

	@Column(name = "mobile", length = 13)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "store_seq", length = 20)
	public String getStoreSeq() {
		return storeSeq;
	}

	
	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 0)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "trade_type")
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}




}
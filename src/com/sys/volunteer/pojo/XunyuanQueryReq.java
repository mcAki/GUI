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
@Table(name = "xunyuan_query_req", catalog = "mprs")
public class XunyuanQueryReq implements java.io.Serializable {

	// Fields

	private Integer queryReqId;
	private String tradeType;
	private String amount;
	private String mobile;
	private String queryStoreSeq;
	private String storeSeq;
	private String orderId;
	private Date createTime;
	private Integer logFor;
	
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "query_req_id", unique = true, nullable = false)
	public Integer getQueryReqId() {
		return this.queryReqId;
	}

	public void setQueryReqId(Integer queryReqId) {
		this.queryReqId = queryReqId;
	}

	@Column(name = "amount", length = 10)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	@Column(name = "order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	@Column(name = "query_store_seq")
	public String getQueryStoreSeq() {
		return queryStoreSeq;
	}

	public void setQueryStoreSeq(String queryStoreSeq) {
		this.queryStoreSeq = queryStoreSeq;
	}

	@Column(name = "log_for")
	public Integer getLogFor() {
		return logFor;
	}

	public void setLogFor(Integer logFor) {
		this.logFor = logFor;
	}




}
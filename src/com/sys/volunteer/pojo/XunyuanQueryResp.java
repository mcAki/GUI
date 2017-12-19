package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mainorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xunyuan_query_resp", catalog = "mprs")
public class XunyuanQueryResp implements java.io.Serializable {

	// Fields

	private Integer queryRespId;
	private String queryRespCode;
	private String recSeq;
	private String tradeTime;
	private String spBalance;
	private String cBalance;
	private String storeSeq;
	private String orderId;
	private Integer logFor;
	
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "query_resp_id", unique = true, nullable = false)
	public Integer getQueryRespId() {
		return this.queryRespId;
	}

	public void setQueryRespId(Integer queryRespId) {
		this.queryRespId = queryRespId;
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

	@Column(name = "query_resp_code", length = 4)
	public String getQueryRespCode() {
		return queryRespCode;
	}

	public void setQueryRespCode(String queryRespCode) {
		this.queryRespCode = queryRespCode;
	}

	@Column(name = "rec_seq")
	public String getRecSeq() {
		return recSeq;
	}

	public void setRecSeq(String recSeq) {
		this.recSeq = recSeq;
	}

	@Column(name = "trade_time")
	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Column(name = "sp_balance")
	public String getSpBalance() {
		return spBalance;
	}

	public void setSpBalance(String spBalance) {
		this.spBalance = spBalance;
	}

	@Column(name = "c_balance")
	public String getcBalance() {
		return cBalance;
	}

	public void setcBalance(String cBalance) {
		this.cBalance = cBalance;
	}

	@Column(name = "log_for")
	public Integer getLogFor() {
		return logFor;
	}

	public void setLogFor(Integer logFor) {
		this.logFor = logFor;
	}


}
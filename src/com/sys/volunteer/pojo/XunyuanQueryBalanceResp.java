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
@Table(name = "xunyuan_query_balance_resp", catalog = "mprs")
public class XunyuanQueryBalanceResp implements java.io.Serializable {

	// Fields

	private Integer queryBalanceRespId;
	private String cBalance;
	private String storeSeq;
	private String payType;
	private String cName;
	private String mobile;
	private String respCode;
	
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "query_balance_resp_id", unique = true, nullable = false)
	public Integer getQueryBalanceRespId() {
		return this.queryBalanceRespId;
	}

	public void setQueryBalanceRespId(Integer queryBalanceRespId) {
		this.queryBalanceRespId = queryBalanceRespId;
	}

	@Column(name = "store_seq", length = 20)
	public String getStoreSeq() {
		return storeSeq;
	}

	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}

	@Column(name = "c_balance")
	public String getcBalance() {
		return cBalance;
	}

	public void setcBalance(String cBalance) {
		this.cBalance = cBalance;
	}

	@Column(name = "pay_type")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "c_name")
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "resp_code")
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}



}
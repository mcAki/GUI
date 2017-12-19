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
@Table(name = "xunyuan_charge_resp", catalog = "mprs")
public class XunyuanChargeResp implements java.io.Serializable {

	// Fields

	private Integer chargeRespId;
	private String chargeRespCode;
	private String recSeq;
	private String chargeTime;
	private String spBalance;
	private String cBalance;
	private String storeSeq;
	private String orderId;
	
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "charge_resp_id", unique = true, nullable = false)
	public Integer getChargeRespId() {
		return this.chargeRespId;
	}

	public void setChargeRespId(Integer chargeRespId) {
		this.chargeRespId = chargeRespId;
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

	@Column(name = "charge_resp_code", length = 4)
	public String getChargeRespCode() {
		return chargeRespCode;
	}

	public void setChargeRespCode(String chargeRespCode) {
		this.chargeRespCode = chargeRespCode;
	}

	@Column(name = "rec_seq")
	public String getRecSeq() {
		return recSeq;
	}

	public void setRecSeq(String recSeq) {
		this.recSeq = recSeq;
	}

	@Column(name = "charge_time")
	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
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




}
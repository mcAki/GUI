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
@Table(name = "xunyuan_charge_result_resp", catalog = "mprs")
public class XunyuanChargeResultNotifyResp implements java.io.Serializable {

	// Fields

	private Integer chargeResultId;
	private String chargeResultRespCode;
	private String recSeq;
	private String chargeTime;
	private String storeSeq;
	private String orderId;
	
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "charge_result_id", unique = true, nullable = false)
	public Integer getChargeResultId() {
		return this.chargeResultId;
	}

	public void setChargeResultId(Integer chargeResultId) {
		this.chargeResultId = chargeResultId;
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

	@Column(name = "charge_result_resp_code", length = 4)
	public String getChargeResultRespCode() {
		return chargeResultRespCode;
	}

	public void setChargeResultRespCode(String chargeResultRespCode) {
		this.chargeResultRespCode = chargeResultRespCode;
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




}
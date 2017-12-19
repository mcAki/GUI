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
@Table(name = "xunyuan_reverse_resp", catalog = "mprs")
public class XunyuanReverseResp implements java.io.Serializable {

	// Fields

	private Integer reverseRespId;
	private String reverseRespCode;
	private String recSeq;
	private String reverseSeq;
	private String reverseTime;
	private String storeSeq;
	private String orderId;
	
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "reverse_resp_id", unique = true, nullable = false)
	public Integer getReverseRespId() {
		return this.reverseRespId;
	}

	public void setReverseRespId(Integer reverseRespId) {
		this.reverseRespId = reverseRespId;
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

	@Column(name = "reverse_resp_code", length = 4)
	public String getReverseRespCode() {
		return reverseRespCode;
	}

	public void setReverseRespCode(String reverseRespCode) {
		this.reverseRespCode = reverseRespCode;
	}

	@Column(name = "rec_seq")
	public String getRecSeq() {
		return recSeq;
	}

	public void setRecSeq(String recSeq) {
		this.recSeq = recSeq;
	}

	@Column(name = "reverse_time")
	public String getReverseTime() {
		return reverseTime;
	}

	public void setReverseTime(String reverseTime) {
		this.reverseTime = reverseTime;
	}

	@Column(name = "reverse_seq")
	public String getReverseSeq() {
		return reverseSeq;
	}

	public void setReverseSeq(String reverseSeq) {
		this.reverseSeq = reverseSeq;
	}





}
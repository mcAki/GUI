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
@Table(name = "xunyuan_reverse_req", catalog = "mprs")
public class XunyuanReverseReq implements java.io.Serializable {

	// Fields

	private Integer reverseReqId;
	private String amount;
	private String mobile;
	private String reverseSeq;
	private String storeSeq;
	private String uimCard;
	private String orderId;
	private Date createTime;
	
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "reverse_req_id", unique = true, nullable = false)
	public Integer getReverseReqId() {
		return this.reverseReqId;
	}

	public void setReverseReqId(Integer reverseReqId) {
		this.reverseReqId = reverseReqId;
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

	@Column(name = "uim_card", length = 13)
	public String getUimCard() {
		return uimCard;
	}

	public void setUimCard(String uimCard) {
		this.uimCard = uimCard;
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

	@Column(name = "reverse_seq")
	public String getReverseSeq() {
		return reverseSeq;
	}

	public void setReverseSeq(String reverseSeq) {
		this.reverseSeq = reverseSeq;
	}




}
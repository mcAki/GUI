package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Mainorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "terminalairreversalrequestnew", catalog = "mprs")
public class AirReversalRequestNew implements java.io.Serializable {

	// Fields

	private Integer reversalId;
	private String storeSeq;
	private String reversalStoreSeq;
	private String terminalNo;
	private String mobileNum;
	private Double amount;
	private Date requestTime;
	private String sign;
	
	public AirReversalRequestNew() {
		super();
	}
	
	public AirReversalRequestNew(Integer reversalId, String storeSeq,
			String reversalStoreSeq, String terminalNo, String mobileNum,
			Double amount, Date requestTime, String sign) {
		super();
		this.reversalId = reversalId;
		this.storeSeq = storeSeq;
		this.reversalStoreSeq = reversalStoreSeq;
		this.terminalNo = terminalNo;
		this.mobileNum = mobileNum;
		this.amount = amount;
		this.requestTime = requestTime;
		this.sign = sign;
	}

	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "reversal_id", unique = true, nullable = false)
	public Integer getReversalId() {
		return this.reversalId;
	}

	public void setReversalId(Integer reversalId) {
		this.reversalId = reversalId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "requestTime", nullable = false, length = 0)
	public Date getRequestTime() {
		return this.requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	@Column(name = "amount", nullable = true, precision = 10, scale = 0)
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "storeSeq", length = 32)
	public String getStoreSeq() {
		return storeSeq;
	}

	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}

	@Column(name = "terminalNo", length = 32)
	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	@Column(name = "mobileNum", length = 32)
	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	@Column(name = "sign", length = 32)
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name="reversalStoreSeq")
	public String getReversalStoreSeq() {
		return reversalStoreSeq;
	}

	public void setReversalStoreSeq(String reversalStoreSeq) {
		this.reversalStoreSeq = reversalStoreSeq;
	}



}
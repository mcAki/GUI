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
@Table(name = "terminalairdepositresponsenew", catalog = "mprs")
public class AirDepositResponseNew implements java.io.Serializable {

	// Fields

	private Integer depositReqId;
	private String respCode;
	private String respDisc;
	private String orderNo;
	private Mainorder mainorder;
	private String storeSeq;
	private String terminalNo;
	private String mobileNum;
	private Double amount;
	private String areaCode;
	private Date responseTime;
	private String sign;
	
	public AirDepositResponseNew() {
		super();
	}
	
	public AirDepositResponseNew(Integer depositReqId, String respCode,
			String respDisc, String orderNo, Mainorder mainorder,
			String storeSeq, String terminalNo, String mobileNum, Double amount,
			String areaCode, Date responseTime, String sign) {
		super();
		this.depositReqId = depositReqId;
		this.respCode = respCode;
		this.respDisc = respDisc;
		this.orderNo = orderNo;
		this.mainorder = mainorder;
		this.storeSeq = storeSeq;
		this.terminalNo = terminalNo;
		this.mobileNum = mobileNum;
		this.amount = amount;
		this.areaCode = areaCode;
		this.responseTime = responseTime;
		this.sign = sign;
	}

	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "depositReqId", unique = true, nullable = false)
	public Integer getDepositReqId() {
		return this.depositReqId;
	}

	public void setDepositReqId(Integer depositReqId) {
		this.depositReqId = depositReqId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId", nullable = false)
	public Mainorder getMainorder() {
		return this.mainorder;
	}

	public void setMainorder(Mainorder mainorder) {
		this.mainorder = mainorder;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "responseTime", nullable = false, length = 0)
	public Date getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	@Column(name = "amount", nullable = true, precision = 10, scale = 0)
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "respCode", length = 32)
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	@Column(name = "respDisc", length = 32)
	public String getRespDisc() {
		return respDisc;
	}

	public void setRespDisc(String respDisc) {
		this.respDisc = respDisc;
	}

	@Column(name = "orderNo", length = 32)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	@Column(name = "areaCode", length = 32)
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "sign", length = 32)
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}



}
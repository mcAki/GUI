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
@Table(name = "orderqueryresponsenew", catalog = "mprs")
public class OrderQueryResponseNew implements java.io.Serializable {

	// Fields

	private Integer orderQueryId;
	private String respCode;
	private String respDisc;
	private String orderNo;
	private String orderId;
	private String storeSeq;
	private String terminalNo;
	private Integer depositId;
	private String userMobileNum;
	private Integer depositState;
	private Integer reversalState;
	private Date beginDate;
	private Date endDate;
	private Date responseTime;
	private String sign;
	private Integer logFor;
	private Integer isDeal;
	
	public OrderQueryResponseNew() {
		super();
	}
	
	public OrderQueryResponseNew(Integer orderQueryId, String respCode,
			String respDisc, String orderNo, String orderId,
			String storeSeq, String terminalNo, Integer depositId,
			String userMobileNum, Date beginDate, Date endDate, Date responseTime,
			String sign) {
		super();
		this.orderQueryId = orderQueryId;
		this.respCode = respCode;
		this.respDisc = respDisc;
		this.orderNo = orderNo;
		this.orderId = orderId;
		this.storeSeq = storeSeq;
		this.terminalNo = terminalNo;
		this.depositId = depositId;
		this.userMobileNum = userMobileNum;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.responseTime = responseTime;
		this.sign = sign;
	}

	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "orderQueryId", unique = true, nullable = false)
	public Integer getOrderQueryId() {
		return orderQueryId;
	}

	public void setOrderQueryId(Integer orderQueryId) {
		this.orderQueryId = orderQueryId;
	}

	@Column(name = "orderId", nullable = false)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "responseTime", nullable = false, length = 0)
	public Date getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
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

	@Column(name = "userMobileNum", length = 32)
	public String getUserMobileNum() {
		return userMobileNum;
	}

	public void setUserMobileNum(String userMobileNum) {
		this.userMobileNum = userMobileNum;
	}

	@Column(name = "sign", length = 32)
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name="depositId", length=11)
	public Integer getDepositId() {
		return depositId;
	}

	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "beginDate", nullable = false, length = 0)
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endDate", nullable = false, length = 0)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name="depositState", length=11)
	public Integer getDepositState() {
		return depositState;
	}

	public void setDepositState(Integer depositState) {
		this.depositState = depositState;
	}

	@Column(name="reversalState", length=11)
	public Integer getReversalState() {
		return reversalState;
	}

	public void setReversalState(Integer reversalState) {
		this.reversalState = reversalState;
	}

	@Column(name="log_for")
	public Integer getLogFor() {
		return logFor;
	}

	public void setLogFor(Integer logFor) {
		this.logFor = logFor;
	}

	@Column(name="isDeal")
	public Integer getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}



}
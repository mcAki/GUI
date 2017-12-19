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
@Table(name = "orderquerynew", catalog = "mprs")
public class OrderQueryNew implements java.io.Serializable {

	private Integer id;
	private String terminalNo;
	private String orderNo;
	private Integer depositId;
	private String userMobileNum;
	private String aggenMobileNum;
	private double amount;
	private Date applyTime;
	private double userMobileBalance;
	private Integer depositState;
	private Integer reversalState;
	private Date depositTime;
	private Date reversalTime;
	private Integer reversalId;
	private String storeSeq;
	private Integer orderqueryresponseid;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="terminalNo")
	public String getTerminalNo() {
		return terminalNo;
	}
	
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	
	@Column(name="orderNo")
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name="depositId")
	public Integer getDepositId() {
		return depositId;
	}
	
	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
	}
	
	@Column(name="userMobileNum")
	public String getUserMobileNum() {
		return userMobileNum;
	}
	
	public void setUserMobileNum(String userMobileNum) {
		this.userMobileNum = userMobileNum;
	}
	
	@Column(name="aggenMobileNum")
	public String getAggenMobileNum() {
		return aggenMobileNum;
	}
	
	public void setAggenMobileNum(String aggenMobileNum) {
		this.aggenMobileNum = aggenMobileNum;
	}
	
	@Column(name="amount", precision = 10, scale = 2)
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "applyTime", length = 0)
	public Date getApplyTime() {
		return applyTime;
	}
	
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@Column(name="userMobileBalance", precision = 10, scale = 2)
	public double getUserMobileBalance() {
		return userMobileBalance;
	}
	
	public void setUserMobileBalance(double userMobileBalance) {
		this.userMobileBalance = userMobileBalance;
	}
	
	@Column(name="depositState")
	public Integer getDepositState() {
		return depositState;
	}
	
	public void setDepositState(Integer depositState) {
		this.depositState = depositState;
	}
	
	@Column(name="reversalState")
	public Integer getReversalState() {
		return reversalState;
	}
	
	public void setReversalState(Integer reversalState) {
		this.reversalState = reversalState;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "depositTime", length = 0)
	public Date getDepositTime() {
		return depositTime;
	}
	
	public void setDepositTime(Date depositTime) {
		this.depositTime = depositTime;
	}
	
	@Column(name="reversalId")
	public Integer getReversalId() {
		return reversalId;
	}
	
	public void setReversalId(Integer reversalId) {
		this.reversalId = reversalId;
	}
	
	@Column(name="storeSeq")
	public String getStoreSeq() {
		return storeSeq;
	}
	
	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}

	@Column(name="orderqueryresponseid")
	public Integer getOrderqueryresponseid() {
		return orderqueryresponseid;
	}

	public void setOrderqueryresponseid(Integer orderqueryresponseid) {
		this.orderqueryresponseid = orderqueryresponseid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reversalTime", length = 0)
	public Date getReversalTime() {
		return reversalTime;
	}

	public void setReversalTime(Date reversalTime) {
		this.reversalTime = reversalTime;
	}
	



}
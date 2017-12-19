package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Useraccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "useraccount", catalog = "mprs")
public class Useraccount implements java.io.Serializable {

	// Fields

	private Integer useraccountId;
	private Double balance;
	private Double freezeMoney;
	private Double commission;
	private Integer state;
	private Double creditLimit;
	private String creditPassword;

	// Constructors

	/** default constructor */
	public Useraccount() {
	}

	/** minimal constructor */
	public Useraccount(Integer useraccountId,  Double balance, Integer state,
			Double creditLimit,String creditPassword) {
		this.useraccountId = useraccountId;
		this.balance = balance;
		this.state = state;
		this.creditLimit = creditLimit;
		this.creditPassword = creditPassword;
	}

	/** full constructor */
	public Useraccount(Integer useraccountId, Double balance,
			Double freezeMoney, Integer state, Double creditLimit,String creditPassword) {
		this.useraccountId = useraccountId;
		this.balance = balance;
		this.freezeMoney = freezeMoney;
		this.state = state;
		this.creditLimit = creditLimit;
		this.creditPassword = creditPassword;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "useraccount_id", unique = true, nullable = false, length = 11)
	public Integer getUseraccountId() {
		return this.useraccountId;
	}

	public void setUseraccountId(Integer useraccountId) {
		this.useraccountId = useraccountId;
	}

	@Column(name = "balance", nullable = false, precision = 20, scale = 2)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "freezeMoney", precision = 20, scale = 2)
	public Double getFreezeMoney() {
		return this.freezeMoney;
	}

	public void setFreezeMoney(Double freezeMoney) {
		this.freezeMoney = freezeMoney;
	}

	@Column(name = "state", nullable = false)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "creditLimit", precision = 10, scale = 2)
	public Double getCreditLimit() {
		return this.creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Column(name = "creditPassword")
	public String getCreditPassword() {
		return creditPassword;
	}

	public void setCreditPassword(String creditPassword) {
		this.creditPassword = creditPassword;
	}

	@Column(name = "commission", precision = 20, scale = 2)
	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

}
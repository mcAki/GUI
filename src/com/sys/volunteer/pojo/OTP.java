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
 * MissionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OTP", catalog = "mprs")
public class OTP implements java.io.Serializable {

	// Fields

	private Long keyId;
	private String authKey;
	private int currDrift;
	private long currSuccess;
	private Date buyDate;

	// Constructors

	/** default constructor */
	public OTP() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "key_id", unique = true, nullable = false)
	public Long getKeyId() {
		return this.keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	@Column(name = "auth_key", length = 32)
	public String getAuthKey() {
		return this.authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	@Column(name="curr_drift")
	public int getCurrDrift() {
		return currDrift;
	}

	public void setCurrDrift(int currDrift) {
		this.currDrift = currDrift;
	}

	@Column(name="curr_success")
	public long getCurrSuccess() {
		return currSuccess;
	}

	public void setCurrSuccess(long currSuccess) {
		this.currSuccess = currSuccess;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "buy_date", nullable = false, length = 0)
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

}
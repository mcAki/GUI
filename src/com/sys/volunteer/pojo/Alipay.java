package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alipay", catalog = "mprs")
public class Alipay implements java.io.Serializable {

	private Integer alipayId;
	private String totalFee; // 金额
	private String tradeNo; // 订单号
	private Date tradeTime; // 下单时间
	private String status;
	private String userId;
	private String userName;
    private Integer isIntoaccount;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "alipay_id", unique = true, nullable = false)
	public Integer getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(Integer alipayId) {
		this.alipayId = alipayId;
	}

	@Column(name = "total_fee")
	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	@Column(name = "trade_no")
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Column(name = "trade_time")
	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "is_intoaccount")
	public Integer getIsIntoaccount() {
		return isIntoaccount;
	}

	public void setIsIntoaccount(Integer isIntoaccount) {
		this.isIntoaccount = isIntoaccount;
	}

}

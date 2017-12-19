package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_feedback", catalog = "mprs")
public class CustomerFeedback {

	private int cfId ;
	private String userName;
	private String userPhone;
	private Date cfTime;
	private String cfMessage;
	private String cfTitle;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cf_id", unique = true, nullable = false)
	public int getCfId() {
		return cfId;
	}
	public void setCfId(int cfId) {
		this.cfId = cfId;
	}
	
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "user_phone")
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	@Column(name = "cf_time")
	public Date getCfTime() {
		return cfTime;
	}
	public void setCfTime(Date cfTime) {
		this.cfTime = cfTime;
	}
	
	@Column(name = "cf_message")
	public String getCfMessage() {
		return cfMessage;
	}
	public void setCfMessage(String cfMessage) {
		this.cfMessage = cfMessage;
	}
	@Column(name = "cf_title")
	public String getCfTitle() {
		return cfTitle;
	}
	public void setCfTitle(String cfTitle) {
		this.cfTitle = cfTitle;
	}
}

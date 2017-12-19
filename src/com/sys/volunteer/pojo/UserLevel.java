package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_level", catalog = "mprs")
public class UserLevel implements Serializable {

	private Integer userLevelId;
	private String userLevel;
	private Integer discount;
	private Integer defaultAlarm;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_level_id", unique = true, nullable = false)
	public Integer getUserLevelId() {
		return userLevelId;
	}
	
	public void setUserLevelId(Integer userLevelId) {
		this.userLevelId = userLevelId;
	}
	
	@Column(name="user_level" ,nullable=false)
	public String getUserLevel() {
		return userLevel;
	}
	
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	
	@Column(name="discount",nullable=false)
	public Integer getDiscount() {
		return discount;
	}
	
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	@Column(name="default_alarm",nullable=false)
	public Integer getDefaultAlarm() {
		return defaultAlarm;
	}

	public void setDefaultAlarm(Integer defaultAlarm) {
		this.defaultAlarm = defaultAlarm;
	}
}

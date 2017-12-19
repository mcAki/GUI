package com.sys.volunteer.pojo.UserUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sys.volunteer.pojo.Usergroup;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "mprs")
public class ModifyUsers implements java.io.Serializable {
	
	private Usergroup usergroup;
	private String userName;
	private String email;
	private String remark;
	private String mobile;
	private Integer gender;
	private Integer reversalCount;
	private Integer maxReversalCount;
	private Integer isShowRecharge;
	private String qq;
	public Integer getIsShowRecharge() {
		return isShowRecharge;
	}
	public void setIsShowRecharge(Integer isShowRecharge) {
		this.isShowRecharge = isShowRecharge;
	}
	public Usergroup getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getReversalCount() {
		return reversalCount;
	}
	public void setReversalCount(Integer reversalCount) {
		this.reversalCount = reversalCount;
	}
	public Integer getMaxReversalCount() {
		return maxReversalCount;
	}
	public void setMaxReversalCount(Integer maxReversalCount) {
		this.maxReversalCount = maxReversalCount;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}


}
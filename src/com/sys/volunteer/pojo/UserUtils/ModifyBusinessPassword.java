package com.sys.volunteer.pojo.UserUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "mprs")
public class ModifyBusinessPassword implements java.io.Serializable {

	private String businessPassword;

	@Column(name = "business_password", length = 50)
	public String getBusinessPassword() {
		return businessPassword;
	}

	public void setBusinessPassword(String businessPassword) {
		this.businessPassword = businessPassword;
	}
	
	
}
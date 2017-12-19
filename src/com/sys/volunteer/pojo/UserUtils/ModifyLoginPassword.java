package com.sys.volunteer.pojo.UserUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "mprs")
public class ModifyLoginPassword implements java.io.Serializable {

	private String userPassword;
	
	@Column(name = "user_password", length = 50)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
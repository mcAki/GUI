package com.sys.volunteer.pojo.UserUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "mprs")
public class UserUpdateProfile implements java.io.Serializable {

	private String userEnglishName;
	private String email;
	private String districtName;

	
	@Column(name = "user_english_name", length = 50)
	public String getUserEnglishName() {
		return this.userEnglishName;
	}

	public void setUserEnglishName(String userEnglishName) {
		this.userEnglishName = userEnglishName;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "district_name", length = 50)
	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

}
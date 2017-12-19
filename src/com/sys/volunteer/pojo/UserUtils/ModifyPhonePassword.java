package com.sys.volunteer.pojo.UserUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "mprs")
public class ModifyPhonePassword implements java.io.Serializable {
	
	private String operationCode;

	@Column(name = "operation_code", length = 16)
	public String getOperationCode() {
		return this.operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
}
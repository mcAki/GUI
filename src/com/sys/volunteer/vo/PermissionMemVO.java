package com.sys.volunteer.vo;

import java.io.Serializable;

public class PermissionMemVO implements Serializable {
	

	private Integer permissionId;
	private Integer userGroupId;
	 
	private String key;
	private String condition;
	private String name;
	
	private Integer privilegeAccess;
	private String description;
	private String matchReturn;
		
	
	public PermissionMemVO() {
		super();
	}


	/**
	 * @return the permissionId
	 */
	public Integer getPermissionId() {
		return permissionId;
	}


	/**
	 * @param permissionId the permissionId to set
	 */
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}


	/**
	 * @return the userGroupId
	 */
	public Integer getUserGroupId() {
		return userGroupId;
	}


	/**
	 * @param userGroupId the userGroupId to set
	 */
	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}


	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}


	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}


	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}


	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the privilegeAccess
	 */
	public Integer getPrivilegeAccess() {
		return privilegeAccess;
	}


	/**
	 * @param privilegeAccess the privilegeAccess to set
	 */
	public void setPrivilegeAccess(Integer privilegeAccess) {
		this.privilegeAccess = privilegeAccess;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the matchReturn
	 */
	public String getMatchReturn() {
		return matchReturn;
	}


	/**
	 * @param matchReturn the matchReturn to set
	 */
	public void setMatchReturn(String matchReturn) {
		this.matchReturn = matchReturn;
	}



	

	
}

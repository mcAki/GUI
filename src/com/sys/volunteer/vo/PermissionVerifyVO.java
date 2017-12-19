package com.sys.volunteer.vo;

import java.io.Serializable;

import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.pojo.PermissionType;

public class PermissionVerifyVO implements Serializable {
	

	private Integer permissionId;
	private Integer userGroupId;
	private PermissionType permissionType;
	private Integer privilegeAccess;
	private String description;
	private String typeName;
	private int matchReturn;
	
	
	public PermissionVerifyVO() {
		super();
	}

	
	public boolean getInUse(){
		if(SysUtil.isNull(userGroupId)) return false;
		return userGroupId>0;
	}
	
	public String getChecked(){
		if(SysUtil.isNull(userGroupId)) return "";
		return "checked='checked'";
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
	 * @return the permissionType
	 */
	public PermissionType getPermissionType() {
		return permissionType;
	}


	/**
	 * @param permissionType the permissionType to set
	 */
	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
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
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}


	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	/**
	 * @return the matchReturn
	 */
	public int getMatchReturn() {
		return matchReturn;
	}


	/**
	 * @param matchReturn the matchReturn to set
	 */
	public void setMatchReturn(int matchReturn) {
		this.matchReturn = matchReturn;
	}
	
}

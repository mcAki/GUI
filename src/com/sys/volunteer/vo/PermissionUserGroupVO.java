package com.sys.volunteer.vo;

import java.util.Hashtable;

import com.sys.volunteer.pojo.Permission;

public class PermissionUserGroupVO {
	private int UsergroupId;
	private Hashtable<String, Permission> permissions;

	public Permission getPermission(String Key){
		return permissions.get(Key);
	}
	
	public PermissionUserGroupVO(int usergroupId, Hashtable<String, Permission> permissions) {
		super();
		UsergroupId = usergroupId;
		this.permissions = permissions;
	}
	
	public PermissionUserGroupVO() {
		super();
	}

	/**
	 * @return the usergroupId
	 */
	public int getUsergroupId() {
		return UsergroupId;
	}
	/**
	 * @param usergroupId the usergroupId to set
	 */
	public void setUsergroupId(int usergroupId) {
		UsergroupId = usergroupId;
	}
	/**
	 * @return the permissionVOs
	 */
	public Hashtable<String, Permission> getPermissions() {
		return permissions;
	}
	/**
	 * @param permissionVOs the permissionVOs to set
	 */
	public void setPermissions(Hashtable<String, Permission> permissions) {
		this.permissions = permissions;
	}
}

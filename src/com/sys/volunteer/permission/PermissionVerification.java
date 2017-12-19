package com.sys.volunteer.permission;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sys.volunteer.pojo.Permission;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.vo.PermissionUserGroupVO;

/**
 * 权限验证模块
 * 
 * @author Administrator
 * 
 */
@Component
@Scope("singleton")
public class PermissionVerification {
	@Resource
	PermissionService permissionService;

	// public PermissionVerification permissionVerification;//static 有问题，估计不能用静态
	public Hashtable<Integer, PermissionUserGroupVO> permissionUserGroups;

	
	
	/**
	 * 重新读取数据库
	 * 
	 * @throws Exception
	 */
	// public void Reload() throws Exception {
	// permissionVerification = null;
	// permissionVerification = new PermissionVerification();
	// permissionVerification.ReloadDate();
	// }

	public void ReloadDate() throws Exception {
		// permissionVerification.permissionUserGroups = null;
		// permissionVerification.permissionUserGroups = new Hashtable<Integer,
		// PermissionUserGroupVO>();
		permissionUserGroups = null;
		permissionUserGroups = new Hashtable<Integer, PermissionUserGroupVO>();
		List<Usergroup> userGroupList = (List<Usergroup>) permissionService.findAll(Usergroup.class);

		for (Usergroup usergroup : userGroupList) {
			Hashtable<String, Permission> permissions = new Hashtable<String, Permission>();

			// 获取对应此usergroup的权限读库取出

			Set<Permission> permissionList = usergroup.getPermissions();
			for (Permission permission : permissionList) {
				String sKey = permission.getPermissionType().getKey() + '-' +permission.getPrivilegeAccess();
				permissions.put(sKey.toLowerCase(), permission);
			}

			// 把任务权限保存
			PermissionUserGroupVO permissionUserGroupVO = new PermissionUserGroupVO(
				usergroup.getId(), permissions);

			permissionUserGroups.put(usergroup.getId(), permissionUserGroupVO);

		}

	}

	/**
	 * 
	 * @param userGroupId
	 *            验证的用户组别ID
	 * @param verifyKey
	 *            验证的键值（如果没有该键值将抛出一个键值错误）
	 * @param privilegeAccess 验证权限内容（参考PrivilegeAccessConst）
	 * @return
	 */
	public Permission getPermission(int userGroupId, String verifyKey, int privilegeAccess) {
		PermissionUserGroupVO permissionUserGroupVO = getPermissionUserGroups().get(userGroupId);
		String key=verifyKey + '-' + privilegeAccess;
		Permission permission = permissionUserGroupVO.getPermission(key.toLowerCase()); 
		return permission;
	}

	/**
	 * 
	 * @param userGroupId
	 *            验证的用户组别ID
	 * @param verifyKey
	 *            验证的键值（如果没有该键值将抛出一个键值错误）
	 * @param privilegeAccess 验证权限内容（参考PrivilegeAccessConst）
	 * @return
	 */
	public int VerifyPermission(int userGroupId, String verifyKey, int privilegeAccess) {		
		Permission permission = getPermission(userGroupId,verifyKey,privilegeAccess);
		if (permission==null){
			return 0;
		}else {
			return permission.getMatchReturn();	
		}
		
	}
	
	/**
	 * 获取实例化
	 * 
	 * @return
	 * @throws Exception
	 */
	// public static PermissionVerification getHinstance() throws Exception {
	// if (permissionVerification == null) {
	// Reload();
	// }
	// return permissionVerification;
	// }

	/**
	 * @return the permissionUserGroups
	 */
	public Hashtable<Integer, PermissionUserGroupVO> getPermissionUserGroups() {
		if (permissionUserGroups == null) {
			try {
				ReloadDate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return permissionUserGroups;
	}

	/**
	 * @param permissionUserGroups
	 *            the permissionUserGroups to set
	 */
	// public void setPermissionUserGroups(Hashtable<Integer, PermissionUserGroupVO>
	// permissionUserGroups) {
	// this.permissionUserGroups = permissionUserGroups;
	// }

}

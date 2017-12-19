package com.sys.volunteer.permission;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.Permission;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.vo.PermissionVO;

@Service
@Transactional
public class PermissionServiceBean extends CommonDao implements PermissionService {
		
	@Override
	public List<PermissionVO> query(Integer usergroupId) throws Exception {
		String hqlString = "SELECT pid as permissionId,usergroup_id as userGroupId,T.permissionTypeName as typeName,T.description as description,T.match_return as matchReturn,"
							+ "T.privilege_access as privilegeAccess FROM (SELECT permission.id as pid, permission_type_id as type_id,"
							+ " permission.privilege_access, permission.match_return, permission.description,"
							+ " permission_type.key, permission_type.condition, permission_type.name as permissionTypeName"
							+ " from permission,permission_type "
							+ "where permission.permission_type_id = permission_type.id) as T LEFT JOIN "
							+ " (SELECT * FROM permission_usergroup WHERE usergroup_id =?) as B ON T.pid=B.permission_id "
							+ "order by typeName";
		PageView pageView = new PageView(Integer.MAX_VALUE, 1);
		Object[] objects = new Object[] { usergroupId };
		QueryResult queryResult = this.getScrollDataBySQLQuery(hqlString, objects,
			pageView.getFirstResult(), pageView.getMaxresult(), null, null, PermissionVO.class);
		return queryResult.getResultlist();
	}

	@Override
	public void addUserGroupByPermissionId(String addId, String removeId, int usergroupId) {
		// TODO usergroup被锁死
		String[] addIds = addId.split(",");
		String[] removeIds = removeId.split(",");
		/*
		 * Object[] insObjects; Object[] delObjects;
		 * 
		 * if (removeIds.length > 0) { String delHQL = "DELETE FROM permission_usergroup " +
		 * "WHERE permission_id in(?) " + "AND usergroup_id=? "; String delIdsString = addId +
		 * removeId; if (delIdsString.endsWith(",")) { delIdsString = delIdsString.substring(0,
		 * delIdsString.length() - 1); } System.out.println(delIdsString); delObjects = new Object[]
		 * { delIdsString, usergroupId };
		 * 
		 * int res = this.executHql(delHQL, delObjects); System.out.println(res); }
		 * 
		 * String InsHQL = "INSERT INTO permission_usergroup " + "(permission_id, usergroup_id ) " +
		 * "VALUES " + "(?,?);";
		 * 
		 * for (int i = 0; i < addIds.length; i++) { try { insObjects = new Object[] { addIds[i],
		 * usergroupId }; System.out.println(addIds[i]); this.executHql(InsHQL, insObjects);
		 * 
		 * } catch (Exception e) { // TODO: handle exception }
		 * 
		 * }
		 */

//		usergroup = (Usergroup) usergroupService.findById(Usergroup.class, usergroupId);
//		Set<Permission> permissions = usergroup.getPermissions();
//		for (int i = 0; i < addIds.length; i++) {
//			Permission permission = (Permission) this.findById(Permission.class,
//				Integer.parseInt(addIds[i]));
//			if (permissions.contains(permission)) permissions.add(permission);
//		}
//		for (int i = 0; i < removeIds.length; i++) {
//			Permission permission = (Permission) this.findById(Permission.class,
//				Integer.parseInt(removeIds[i]));
//			if (permissions.contains(permission)) permissions.remove(permission);
//		}
//		usergroup.setPermissions(permissions);
//		usergroupService.update(usergroup);


		Usergroup usergroup = (Usergroup) this.findById(Usergroup.class, usergroupId);

		Set<Permission> permissions = usergroup.getPermissions();

		for (int i = 0; i < removeIds.length; i++) {
			if (SysUtil.isNull(removeIds[i]) || removeIds[i].trim().isEmpty()) {
				break;
			}
			Permission permission = (Permission) this.findById(Permission.class,
				Integer.parseInt(removeIds[i]));
			usergroup.getPermissions().remove(permission);
		}

		// usergroup.getPermissions().clear();
		for (int i = 0; i < addIds.length; i++) {
			if (SysUtil.isNull(addIds[i]) || addIds[i].trim().isEmpty()) {
				break;
			}
			Permission permission = (Permission) this.findById(Permission.class,
				Integer.parseInt(addIds[i]));
			usergroup.getPermissions().add(permission);
		}
		update(usergroup);
	}
	
	@Override
	public List<PermissionVO> queryByUserGroupIdAndPermissionTypeId(Integer usergroupId,Integer permissionTypeId) throws Exception {
		String hqlString = "SELECT pid as permissionId,usergroup_id as userGroupId,T.permissionTypeName as typeName,T.description as description,T.match_return as matchReturn,"
							+ "T.privilege_access as privilegeAccess FROM (SELECT permission.id as pid, permission_type_id as type_id,"
							+ " permission.privilege_access, permission.match_return, permission.description,"
							+ " permission_type.key, permission_type.condition, permission_type.name as permissionTypeName"
							+ " from permission,permission_type "
							+ "where permission.permission_type_id = permission_type.id) as T LEFT JOIN "
							+ " (SELECT * FROM permission_usergroup WHERE usergroup_id =?) as B ON T.pid=B.permission_id " 
							+ "where type_id=? "
							+ "order by typeName";
		PageView pageView = new PageView(Integer.MAX_VALUE, 1);
		Object[] objects = new Object[] { usergroupId ,permissionTypeId };
		QueryResult queryResult = this.getScrollDataBySQLQuery(hqlString, objects,
			pageView.getFirstResult(), pageView.getMaxresult(), null, null, PermissionVO.class);
		return queryResult.getResultlist();
	}
}

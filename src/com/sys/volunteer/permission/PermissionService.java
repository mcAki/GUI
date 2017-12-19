package com.sys.volunteer.permission;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.vo.PermissionVO;

public interface PermissionService extends IDao {
	public List<PermissionVO> query(Integer usergroupId) throws Exception;
	
	public void addUserGroupByPermissionId(String addId,String removeId, int usergroupId);
	
	public List<PermissionVO> queryByUserGroupIdAndPermissionTypeId(Integer usergroupId,Integer permissionTypeId) throws Exception;
}

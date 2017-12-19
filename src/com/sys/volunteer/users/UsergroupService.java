package com.sys.volunteer.users;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.pojo.Users;

public interface UsergroupService extends IDao {

	Usergroup findUsergroupById(int id);

	/**
	 * 根据用户组权限把查询权限
	 * @param user
	 * @return
	 */
	List<Usergroup> findUsergroupsByStatus(Users user);
	
	/**
	 * 查询出所有用户组
	 */
	public List<Usergroup> findAllUsergroup();

	public Usergroup addUsergroup(Usergroup usergroup);
}

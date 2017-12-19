package com.sys.volunteer.users;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Users;

public interface UserService extends IDao {

	public Users findUserById(String id);

	public List<Users> findUserByLoginName(String loginName);
	
	public void addUser(Users user);
	
	public void addUsers(Users user,String currentUsers);

	public void updateUserDelfalgById(String userId,int flag);
	
	public boolean isUniqueLoginName(String loginName);
	
	//public Users findUserByMobile(String mobile);
	
	public List<Users> findChildUsers(String userId);
	
	/**
	 * 获取管理员
	 * @return
	 */
	public Users getAdminUser();

	public Users findUserByTerminalNo(String terminalNo);
	
    /**
     * 更新阅读协议
     */
	public void updateReadProtocolResult(Users user);
	
	/**
	 * 查询一级商户总量
	 */
	public int findExNum();
	/**
	 * 查询二级商户数量
	 */
	public int findExExNum();
	
	/**
	 * 根据group查询所有一级商户
	 * @return
	 */
	public List<Users> findAllUsersByGroup(int groupId);
	

	
}

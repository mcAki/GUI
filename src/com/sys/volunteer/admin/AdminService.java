package com.sys.volunteer.admin;

import com.ages.model.Administrators;
import com.sys.volunteer.dao.IDao;

public interface AdminService extends IDao {
	/**
	 * 获取admin包
	 * @param id 主键id
	 * @return
	 */
	public Administrators findAdminById(int id);
	
	/**
	 * 删除admin记录
	 * @param id
	 */
	public void delAdminById(int id);
	
	
	public void save(Administrators administrators);
	
	public void update(Administrators administrators);
	
	public Administrators checkLogin(String userName,String passWord);
}

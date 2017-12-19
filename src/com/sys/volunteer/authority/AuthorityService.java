package com.sys.volunteer.authority;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Menutree;
import com.sys.volunteer.pojo.Usergroup;

public interface AuthorityService extends IDao{

	/**
	 * 根据用户的用户组获得他的菜单列表
	 * @param group
	 * @return
	 */
	public List<Menutree> getMenuTreeBy(Usergroup group);
}

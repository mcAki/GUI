package com.sys.volunteer.authority;

import java.util.List;

import com.ages.model.Administratorsgroup;
import com.ages.model.Menutree;
import com.sys.volunteer.dao.IDao;

public interface AuthorityService extends IDao{

	/**
	 * 根据用户的用户组获得他的菜单列表
	 * @param group
	 * @return
	 */
	public List<Menutree> getMenuTreeBy(Administratorsgroup group);
}

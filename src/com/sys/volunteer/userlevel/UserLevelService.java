package com.sys.volunteer.userlevel;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.UserLevel;

public interface UserLevelService extends IDao {

	public UserLevel findUserLevelById(Integer id);
}

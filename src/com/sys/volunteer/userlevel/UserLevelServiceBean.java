package com.sys.volunteer.userlevel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.UserLevel;

@Service
@Transactional
public class UserLevelServiceBean extends CommonDao implements UserLevelService {

	@Override
	public UserLevel findUserLevelById(Integer id) {
		UserLevel userLevel=(UserLevel)this.findById(UserLevel.class, id);
		return userLevel;
	}

	
}

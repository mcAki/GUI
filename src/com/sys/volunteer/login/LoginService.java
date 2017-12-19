package com.sys.volunteer.login;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Users;

public interface LoginService extends IDao {

	public List<Users> checkLogin(String loginName,String passWord,int loginType);

	public List<Users> checkLoginByUserName(String loginName, String passWord,
			int loginType);

}

package com.sys.volunteer.login;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.common.MD5;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Users;
/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class LoginServiceBean extends CommonDao implements LoginService {

	@Override
	public List<Users> checkLogin(String loginName, String passWord,int loginType) {
		//把输入密码用MD5加密,再判断
		MD5 md5=MD5.getiInstance();
		String password=md5.getMD5ofStr(passWord);
		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
		switch (loginType) {
		case 2:
			dec.add(Restrictions.eq("loginName", loginName));
			break;
		case 3:
			dec.add(Restrictions.eq("terminalNo", loginName));
			break;
		default:
			break;
		}
		dec.add(Restrictions.eq("userPassword", password));
		List<Users> list=this.findByDetachedCriteria(dec);
		return list;
	}
	
	
	@Override
	public List<Users> checkLoginByUserName(String loginName, String passWord,int loginType) {
		//把输入密码用MD5加密,再判断
		MD5 md5=MD5.getiInstance();
		String password=md5.getMD5ofStr(passWord);
		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
		switch (loginType) {
		case 2:
			dec.add(Restrictions.or(Restrictions.eq("loginName", loginName), Restrictions.eq("userName", loginName)));
			break;
		case 3:
			dec.add(Restrictions.eq("terminalNo", loginName));
			break;
		default:
			break;
		}
		dec.add(Restrictions.eq("userPassword", password));
		List<Users> list=this.findByDetachedCriteria(dec);
		return list;
	}

}

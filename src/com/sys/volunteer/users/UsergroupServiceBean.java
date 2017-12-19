package com.sys.volunteer.users;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.pojo.Users;
@Service
@Transactional
public class UsergroupServiceBean extends CommonDao implements UsergroupService {

	@Override
	public Usergroup findUsergroupById(int id) {
		Usergroup usergroup = (Usergroup)this.findById(Usergroup.class, id);
		return usergroup;
	}

	@Override
	public List<Usergroup> findUsergroupsByStatus(Users user){
		if (user.getUsergroup().getStatus()==-1) {
			return null;
		}else {
			DetachedCriteria dec=DetachedCriteria.forClass(Usergroup.class);
			dec.add(Restrictions.lt("status", user.getUsergroup().getStatus()));
			List<Usergroup> list=this.findByDetachedCriteria(dec);
			return list;
		}
	}

	@Override
	public List<Usergroup> findAllUsergroup() {
		List<Usergroup> list = (List<Usergroup>)this.findAll(Usergroup.class);
		return list;
	}
	
	@Override
	public Usergroup addUsergroup(Usergroup usergroup) {
		save(usergroup);
		return usergroup;
	}
}

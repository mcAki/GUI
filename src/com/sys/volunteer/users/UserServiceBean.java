package com.sys.volunteer.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.MD5;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.UserLevel;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.userlevel.UserLevelService;
@Service
@Transactional
public class UserServiceBean extends CommonDao implements UserService {

	@Resource
	UserLevelService userLevelService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	UsergroupService usergroupService;
	
	@Override
	public Users findUserById(String id) {
		Users user=(Users)this.findById(Users.class, id);
		return user;
	}
	
	@Override
	public List<Users> findUserByLoginName(String loginName) {
		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
		dec.add(Restrictions.eq("loginName", loginName));
		List<Users> list=this.findByDetachedCriteria(dec);
		return list;
	}
	
	
	@Override
	public void addUser(Users user) {
		MD5 md5=MD5.getiInstance();
		String passWord=md5.getMD5ofStr(user.getUserPassword());
		user.setUserPassword(passWord);
		String businessPassword = md5.getMD5ofStr(user.getBusinessPassword());
		user.setBusinessPassword(businessPassword);
		user.setCreateDate(new Date());
		user.setRecodeOperator(user.getLoginName());
		user.setState(0);
		if (user.getDefaultAlarm()==null) {
			UserLevel userLevel=userLevelService.findUserLevelById(user.getUserLevel().getUserLevelId());
			user.setDefaultAlarm(userLevel.getDefaultAlarm());
		}
		Useraccount useraccount=useraccountService.addUseraccount();
		user.setUseraccount(useraccount);
		
		//自动增加终端机数量
		Usergroup usergroup = usergroupService.findUsergroupById(user.getUsergroup().getId());
		if (usergroup.getGroupType()==Const.GroupType.GRADE_TWO&&user.getTerminalNo()!=null) {
			Users parentUser=this.findUserById(user.getParentUsers().getUserId());
			if (parentUser.getTerminalCount()==null) {
				parentUser.setTerminalCount(0);
			}
			parentUser.setTerminalCount(parentUser.getTerminalCount()+1);
			this.update(parentUser);
		}
		user.setReadProtocolResult(0);
		this.save(user);
	}
	
	@Override
	public void addUsers(Users user,String currentUsers) {
		MD5 md5=MD5.getiInstance();
		String passWord=md5.getMD5ofStr(user.getUserPassword());
		user.setUserPassword(passWord);
		String businessPassword = md5.getMD5ofStr(user.getBusinessPassword());
		user.setBusinessPassword(businessPassword);
		user.setCreateDate(new Date());
		user.setRecodeOperator(currentUsers);
		user.setState(0);
		if (user.getDefaultAlarm()==null) {
			UserLevel userLevel=userLevelService.findUserLevelById(user.getUserLevel().getUserLevelId());
			user.setDefaultAlarm(userLevel.getDefaultAlarm());
		}
		Useraccount useraccount=useraccountService.addUseraccount();
		user.setUseraccount(useraccount);
		//自动增加终端机数量
		Usergroup usergroup = usergroupService.findUsergroupById(user.getUsergroup().getId());
		if (usergroup.getGroupType()==Const.GroupType.GRADE_TWO&&user.getTerminalNo()!=null) {
			Users parentUser=this.findUserById(user.getParentUsers().getUserId());
			if (parentUser.getTerminalCount()==null) {
				parentUser.setTerminalCount(0);
			}
			parentUser.setTerminalCount(parentUser.getTerminalCount()+1);
			this.update(parentUser);
		}
		user.setReadProtocolResult(0);
		this.save(user);
	}

	@Override
	public void updateUserDelfalgById(String userId,int flag) {
		//自动减少终端机数量
		Users user = (Users) this.findUserById(userId);
		Usergroup usergroup = usergroupService.findUsergroupById(user.getUsergroup().getId());
		if (usergroup.getGroupType()==Const.GroupType.GRADE_TWO&&user.getTerminalNo()!=null) {
			Users parentUser=this.findUserById(user.getParentUsers().getUserId());
			if (flag==Const.UserFlag.NORMAL) {
				parentUser.setTerminalCount(parentUser.getTerminalCount()+1);
			}
			if (flag==Const.UserFlag.FORBIDDEN) {
				parentUser.setTerminalCount(parentUser.getTerminalCount()-1);
			}
			this.save(parentUser);
		}
		user.setState(flag);
		this.update(user);
	}

	@Override
	public boolean isUniqueLoginName(String loginName) {
		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
		dec.add(Restrictions.or(Restrictions.eq("loginName", loginName), Restrictions.eq("userName", loginName)));
		List<Users> list=this.findByDetachedCriteria(dec);
		return list.isEmpty();
	}

	@Override
	public List<Users> findChildUsers(String userId) {
		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
		dec.add(Restrictions.eq("parentUsers.id", userId));
		dec.add(Restrictions.eq("state", Const.UserFlag.NORMAL));
		List<Users> list=this.findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public Users getAdminUser() {
		Users user = findUserById("402882e72e6131aa012e6131f81d0001");
		return user;
	}
	
	@Override
	public Users findUserByTerminalNo(String terminalNo){
		DetachedCriteria dec = DetachedCriteria.forClass(Users.class);
		dec.add(Restrictions.eq("terminalNo", terminalNo));
		dec.add(Restrictions.eq("state", 0));
		List<Users> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateReadProtocolResult(Users user) {
		this.update(user);
	}

	@Override
	public int findExExNum() {
		List<Users> list = new ArrayList<Users>();
		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
		dec.createAlias("usergroup", "ug");
		dec.add(Restrictions.eq("ug.groupType", Const.GroupType.GRADE_TWO));
		 list=this.findByDetachedCriteria(dec);
//		 for (Object obj : list) {
//				Object[] obj2 = (Object[])obj;
//				Users user = (Users) obj2[1];
//				list.add(user);
//			}
		return list.size();
	}

	@Override
	public int findExNum() {
		List<Users> list = new ArrayList<Users>();
		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
		dec.createAlias("usergroup", "ug");
		dec.add(Restrictions.eq("ug.groupType", Const.GroupType.GRADE_ONE));
		 list=this.findByDetachedCriteria(dec);
//		 for (Object obj : list) {
//				Object[] obj2 = (Object[])obj;
//				Users user = (Users) obj2[1];
//				list.add(user);
//			}
		return list.size();
	}

	@Override
	public List<Users> findAllUsersByGroup(int groupId) {
		List<Users> list = new ArrayList<Users>();
		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
		dec.createAlias("usergroup", "ug");
		dec.add(Restrictions.eq("ug.groupType", groupId));
		 list=this.findByDetachedCriteria(dec);
		 for (Object obj : list) {
				Object[] obj2 = (Object[])obj;
				Users user = (Users) obj2[1];
				list.add(user);
			}
		return list;
	}


//	@Override
//	public Users findUserByMobile(String mobile) {
//		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
//		dec.add(Restrictions.eq("mobile", mobile));
//		List<Users> list=this.findByDetachedCriteria(dec);
//		if (!list.isEmpty()) {
//			return list.get(0);
//		}
//		return null;
//	}
	
}

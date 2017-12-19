package com.sys.volunteer.users;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.useraccount.UseraccountService;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListUserAction extends BaseListAction implements Preparable{
	@Resource
	UserService userService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	UsergroupService usergroupService;

	private int missionId;

	private String username;

	private String mobile;

	private Integer param;
	
	private Integer state;
	
	private Users users;
	
	private Users parentUser;
	
	private Useraccount useraccount;
	
	private String terminalNo;
	
	private Integer userLevel;
	
	private Integer typeId;
	
	private List<Useraccount> useraccounts;
	
	private List<Usergroup> usergroups;

	// private Users users;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMissionId() {
		return missionId;
	}

	public void setMissionId(int missionId) {
		this.missionId = missionId;
	}

	private String userId;
	
	@Override
	public void prepare() throws Exception {
		useraccounts = (List<Useraccount>)useraccountService.findAll(Useraccount.class);
		usergroups = (List<Usergroup>)usergroupService.findAll(Usergroup.class);
	}

	/**
	 * 删除User页面,实际是更新del_flag属性
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "users", privilegeAccess = Const.PrivilegeAccessConst.DEL)
	public String delUser() throws Exception {
		List<Users> childUsers = userService.findChildUsers(userId);
		if (!childUsers.isEmpty()) {
			return ShowMessage(MSG_TYPE_STOP, "请先禁用所有下级商户", "", "", 0);
		}
		this.userService.updateUserDelfalgById(getUserId(),Const.UserFlag.FORBIDDEN);
		Users user=userService.findUserById(userId);
		switch (user.getUsergroup().getGroupType()) {
		case Const.GroupType.STAFF:
			list();
			return SUCCESS;
		case Const.GroupType.GRADE_ONE:
			return this.listUserEx();
		case Const.GroupType.GRADE_TWO:
			return this.manageUserEx();
		default:
			return ShowMessage(MSG_TYPE_STOP, "禁用错误", "", "", 0);
		}
	}
	
	/**
	 * 恢复User页面,实际是更新del_flag属性
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "users", privilegeAccess = Const.PrivilegeAccessConst.DEL)
	public String recoverUser() throws Exception {
		this.userService.updateUserDelfalgById(getUserId(),Const.UserFlag.NORMAL);
		Users user=userService.findUserById(userId);
		switch (user.getUsergroup().getGroupType()) {
		case Const.GroupType.STAFF:
			list();
			return SUCCESS;
		case Const.GroupType.GRADE_ONE:
			return this.listUserEx();
		case Const.GroupType.GRADE_TWO:
			return this.manageUserEx();
		default:
			return ShowMessage(MSG_TYPE_STOP, "禁用错误", "", "", 0);
		}
		
	}

	/**
	 * 管理员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void list() throws Exception {
		// pageView=new PageView(this.getPageSize(), this.getPageIndex());
		// QueryResult
		// queryResult=userService.getScrollData(pageView.getFirstResult(),
		// pageView.getMaxresult(), DetachedCriteria.forClass(Users.class));
		// pageView.setQueryResult(queryResult);
		// System.out.println("111111111111111111");
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		DetachedCriteria userCondition = DetachedCriteria.forClass(Users.class);
		userCondition.createAlias("usergroup", "ug");
		userCondition.add(Restrictions.eq("ug.groupType", Const.GroupType.STAFF));
		if (!SysUtil.isNull(username)) {
			userCondition.add(Restrictions.eq("userName", username));
		}
		if (!SysUtil.isNull(mobile)) {
			userCondition.add(Restrictions.eq("mobile", mobile));
		}
		if (!SysUtil.isNull(state)) {
			userCondition.add(Restrictions.eq("state", state));
		}
		if (!SysUtil.isNull(userLevel)) {
			userCondition.add(Restrictions.eq("userLevel.id", userLevel));
		}
		QueryResult queryResult = userService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), userCondition);
		List<Users> list = new ArrayList<Users>();
		for (Object obj : queryResult.getResultlist()) {
			Object[] obj2 = (Object[])obj;
			Users user = (Users) obj2[1];
			list.add(user);
		}
		queryResult.getResultlist().removeAll(queryResult.getResultlist());
		queryResult.getResultlist().addAll(list);
		pageView.setQueryResult(queryResult);
	}

	/**
	 * 按名字搜索用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchUserForSupply() throws Exception {
		DetachedCriteria dec = DetachedCriteria.forClass(Users.class);
		dec.add(Restrictions.eq("state", 0));
		if (!SysUtil.isNull(username)) {
			dec.add(Restrictions.eq("userName", username));
		}
		if (!SysUtil.isNull(mobile)) {
			dec.add(Restrictions.eq("mobile", mobile));
		}
		pageView = new PageView(getPageSize(), getPageIndex());
		QueryResult queryResult = userService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listManagerByName";
	}

	/**
	 * 用户列表
	 */
	public String listUserForSupply() throws Exception {
		DetachedCriteria dec = DetachedCriteria.forClass(Users.class);
		dec.add(Restrictions.eq("state", 0));
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		QueryResult queryResult = userService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listUserForSupply";
	}

	/**
	 * 执行
	 */
	@Privilege(permissionName = "users", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	@Override
	public String execute() throws Exception {
		list();
		return SUCCESS;
	}

	/**
	 * 管理下级商户列表
	 * @throws Exception 
	 */
	@Privilege(permissionName = "gradeTwo", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	public String manageUserEx() throws Exception {
		if (getSessionUser().getUsergroup().getGroupType()<=Const.GroupType.STAFF||userId!=null) {
			users = userService.findUserById(userId);
		}else {
			users = userService.findUserById(getSessionUser().getUserId());
		}
		if (users.getParentUsers()!=null) {
			parentUser = userService.findUserById(users.getParentUsers().getUserId());
		}
		useraccount = useraccountService.findUseraccountByUseraccountId(users);
		if (users.getUsergroup().getGroupType()==Const.GroupType.GRADE_ONE
				||users.getUsergroup().getGroupType()<=Const.GroupType.STAFF) {
			DetachedCriteria dec = DetachedCriteria.forClass(Users.class);
			if (!SysUtil.isNull(state)) {
				dec.add(Restrictions.eq("state", state));
			}
			if (!SysUtil.isNull(terminalNo)) {
				dec.add(Restrictions.eq("terminalNo", terminalNo));
			}
			dec.add(Restrictions.eq("parentUsers.id", users.getUserId()));
			pageView = new PageView(this.getPageSize(), this.getPageIndex());
			QueryResult queryResult = userService.getScrollData(pageView
					.getFirstResult(), pageView.getMaxresult(), dec);
			pageView.setQueryResult(queryResult);
		}
		return "listUserExEx";
	}
	
	/**
	 * 一级商户列表
	 * @throws Exception 
	 */
	@Privilege(permissionName = "gradeOne", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	public String listUserEx() throws Exception {
		//users = userService.findUserById(userId);
		DetachedCriteria dec = DetachedCriteria.forClass(Users.class);
		if (getSessionUser().getUsergroup().getGroupType()>Const.GroupType.SUPPLY) {
			dec.add(Restrictions.eq("id", getSessionUser().getUserId()));
		}
		if (!SysUtil.isNull(state)) {
			dec.add(Restrictions.eq("state", state));
		}
		if (!SysUtil.isNull(username)) {
			dec.add(Restrictions.like("userName", username, MatchMode.ANYWHERE));
		}
		if (!SysUtil.isNull(mobile)) {
			dec.add(Restrictions.eq("mobile", mobile));
		}
		dec.createAlias("usergroup", "ug");
		dec.add(Restrictions.eq("ug.groupType", Const.GroupType.GRADE_ONE));
		if (!SysUtil.isNull(userLevel)&&userLevel>0) {
			dec.add(Restrictions.eq("userLevel.id", userLevel));
		}
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		QueryResult queryResult = userService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		List<Users> list = new ArrayList<Users>();
		for (Object obj : queryResult.getResultlist()) {
			Object[] obj2 = (Object[])obj;
			Users user = (Users) obj2[1];
			list.add(user);
		}
		queryResult.getResultlist().removeAll(queryResult.getResultlist());
		queryResult.getResultlist().addAll(list);
		pageView.setQueryResult(queryResult);
		return "listUserEx";
	}
	
	/**
	 * 二级商户列表
	 * @throws Exception 
	 */
	@Privilege(permissionName = "gradeTwo", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	public String listGradeTwoUser() throws Exception {
		//users = userService.findUserById(userId);
		DetachedCriteria dec = DetachedCriteria.forClass(Users.class);
		if (!SysUtil.isNull(state)) {
			dec.add(Restrictions.eq("state", state));
		}
		if (!SysUtil.isNull(username)) {
			dec.add(Restrictions.like("userName", username, MatchMode.ANYWHERE));
		}
		if (!SysUtil.isNull(mobile)) {
			dec.add(Restrictions.eq("mobile", mobile));
		}
		dec.createAlias("usergroup", "ug");
		dec.add(Restrictions.eq("ug.groupType", Const.GroupType.GRADE_TWO));
		if (!SysUtil.isNull(userLevel)&&userLevel>0) {
			dec.add(Restrictions.eq("userLevel.id", userLevel));
		}
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		QueryResult queryResult = userService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		List<Users> list = new ArrayList<Users>();
		for (Object obj : queryResult.getResultlist()) {
			Object[] obj2 = (Object[])obj;
			Users user = (Users) obj2[1];
			list.add(user);
		}
		queryResult.getResultlist().removeAll(queryResult.getResultlist());
		queryResult.getResultlist().addAll(list);
		return "listGradeTwoUser";
	}
	
	/**
	 * 用户组列表
	 * @return
	 * @throws Exception
	 */
	public String listUsergroup() throws Exception {
		DetachedCriteria dec = DetachedCriteria.forClass(Usergroup.class);
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		QueryResult queryResult = usergroupService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listUsergroup";
	}
	
	/**
	 * 用户组列表(分组别)
	 * @return
	 * @throws Exception
	 */
	public String listGroupForLog() throws Exception {
		DetachedCriteria dec = DetachedCriteria.forClass(Usergroup.class);
		dec.add(Restrictions.eq("groupType", typeId));
		pageView=new PageView(this.getPageSize(), this.getPageIndex());
		QueryResult queryResult=userService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listGroupForLog";
	}

	/**
	 * 验证数据
	 */
	@Override
	public void validate() {
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getParam() {
		return param;
	}

	public void setParam(Integer param) {
		this.param = param;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Useraccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Users getParentUser() {
		return parentUser;
	}

	public void setParentUser(Users parentUser) {
		this.parentUser = parentUser;
	}

	public List<Useraccount> getUseraccounts() {
		return useraccounts;
	}

	public void setUseraccounts(List<Useraccount> useraccounts) {
		this.useraccounts = useraccounts;
	}

	public List<Usergroup> getUsergroups() {
		return usergroups;
	}

	public void setUsergroups(List<Usergroup> usergroups) {
		this.usergroups = usergroups;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}

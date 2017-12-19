package com.sys.volunteer.users;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.common.MD5;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.common.RandomUtils;
import com.sys.volunteer.common.ResponseUtils;
import com.sys.volunteer.common.SDKClientUtil;
import com.sys.volunteer.customer.CustomerFeedbackService;
import com.sys.volunteer.otp.OTPService;
import com.sys.volunteer.permission.PermissionVerification;
import com.sys.volunteer.pojo.CustomerFeedback;
import com.sys.volunteer.pojo.Menutree;
import com.sys.volunteer.pojo.OTP;
import com.sys.volunteer.pojo.Permission;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.UserLevel;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.pojo.UserUtils.ModifyUsers;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.UserChargeService;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.userlevel.UserLevelService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction implements Preparable {

	@Resource
	UsergroupService usergroupService;
	@Resource
	UserService userService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	UserLevelService userLevelService;
	@Resource
	OTPService otpService;
	@Resource
	UserChargeService userChargeService;
	@Resource
	CustomerFeedbackService customerFeedbackService;
	@Resource
	PermissionVerification permissionVerification;

	private Users users;
	
	private Users parentUsers;

	private List<Usergroup> usergroups;

	private String userId;
	
	private String verificationCode;

    private String businessPassword;

	private ModifyUsers modifyUsers;

	private Users currentUser;
	
//	private Users recodeUser;

	private Double recharge;
	
	private List<UserLevel> userLevels;
	
	private Useraccount currentUseraccount;
	
	private Useraccount useraccount;
	
	private UserCharge useraccountdealdetail;
	
	private Double charge;
	
	private Usergroup usergroup;
	
	@Override
	public void prepare() throws Exception {
		// Users user=getSessionUser();
		// //根据权限获取可选择的用户组
		// this.usergroups = usergroupService.findUsergroupsByStatus(user);
		 this.usergroups = (List<Usergroup>)usergroupService.findAll(Usergroup.class);
		userLevels=(List<UserLevel>)userLevelService.findAll(UserLevel.class);
	}

	/**
	 * 增加用户页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "users", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String add() throws Exception {
		users = new Users();
		return "do";
	}

	/**
	 * 增加用户提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAdd() throws Exception {
		if (users.getUserId() == null || "".equals(users.getUserId())) {
			//useraccount.setUser(users);
			if (users.getUsergroup().getGroupType()==Const.GroupType.GRADE_TWO&&users.getTerminalNo()!=null){
				users.setParentUsers(parentUsers);
			}
			if (userService.isUniqueLoginName(users.getUserName())) {
				userService.addUser(users);
				//新增用户必须结算一次额度
				userChargeService.addUserCharge(new UserCharge(), null,
						0d, users,null,getSessionUser().getUserName(),Const.UseraccountdealdetailFlag.CHECK_OUT);
				return ShowMessage(MSG_TYPE_NORMAL, "添加成功", "", "", 0);
			}else {
				return ShowMessage(MSG_TYPE_STOP, "登陆名字重复", "","", 0);
			}
		} else {
			if (userService.isUniqueLoginName(users.getUserName())) {
				userService.update(users);
				return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "", "", 0);
			}else {
				return ShowMessage(MSG_TYPE_STOP, "登陆名字重复", "","", 0);
			}
		}
	}
	/**
	 * 增加用户提交(记录操作员:2012.10.25)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAdds() throws Exception {
		if (users.getUserId() == null || "".equals(users.getUserId())) {
			usergroup = usergroupService.findUsergroupById(users.getUsergroup().getId());
			if(usergroup.getGroupType()==Const.GroupType.GRADE_ONE){
				int code = userService.findExNum()+1;
				users.setUserCode("A"+code);
			}else if(usergroup.getGroupType()==Const.GroupType.GRADE_TWO){
				int code = userService.findExExNum()+1;
				users.setUserCode("B"+code);
			}
			if (usergroup.getGroupType()==Const.GroupType.GRADE_TWO&&users.getTerminalNo()!=null){
				users.setParentUsers(parentUsers);
			}
			if (userService.isUniqueLoginName(users.getUserName())) {
				userService.addUsers(users,getSessionUser().getUserName());
				//新增用户必须结算一次额度
				userChargeService.addUserCharge(new UserCharge(), null,
						0d, users,null,getSessionUser().getUserName(),Const.UseraccountdealdetailFlag.CHECK_OUT);
				return ShowMessage(MSG_TYPE_NORMAL, "添加成功", "", "", 0);
			}else {
				return ShowMessage(MSG_TYPE_STOP, "登陆名字重复", "","", 0);
			}
		} else {
			if (userService.isUniqueLoginName(users.getUserName())) {
				userService.update(users);
				return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "", "", 0);
			}else {
				return ShowMessage(MSG_TYPE_STOP, "登陆名字重复", "","", 0);
			}
		}
	}

	/**
	 * 增加用户页面Ex
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "gradeOne", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String addEx() throws Exception {
		users = new Users();
		//users.setUseraccount(useraccount);
		//Users parentUsers = userService.findUserById(userId);
		//users.setParentUsers(parentUsers);
		return "doEx";
	}
	
	/**
	 * 增加用户页面ExEx
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "gradeTwo", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String addExEx() throws Exception {
		users = new Users();
		//users.setUseraccount(useraccount);
		parentUsers = userService.findUserById(userId);
		return "doExEx";
	}

	/**
	 * 划额页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "udForUsers", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String recharge() throws Exception {
		users = userService.findUserById(userId);
		useraccount = useraccountService.findUseraccountByUseraccountId(users);
		if (getSessionUser().getUsergroup().getGroupType()<=Const.GroupType.STAFF) {
			currentUser = userService.getAdminUser();
		}else {
			currentUser = getSessionUser();
		}
		currentUseraccount = useraccountService.findUseraccountByUseraccountId(currentUser);
//		if (users.getUsergroup().getId()==Const.Usergroup.GRADE_TWO) {
//			currentUser = userService.findUserById(users.getParentUsers().getUserId());
//			currentUseraccount = useraccountService.findUseraccountByUseraccountId(currentUser);
//		}
		useraccountdealdetail = new UserCharge();
		return "recharge";
	}

	/**
	 * 划额提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doRecharge() throws Exception {
		currentUser = userService.findUserById(currentUser.getUserId());
		if (recharge<0) {
			return ShowMessage(MSG_TYPE_STOP, "交易金额不能小于0", "点击返回", "page!recharge.do?userId=" + currentUser.getUserId(), 0);
		}
		users = userService.findUserById(users.getUserId());
		MD5 md5 = MD5.getiInstance();
		if (getSessionUser().getUsergroup().getGroupType()>Const.GroupType.STAFF && !currentUser.getBusinessPassword().equals( md5.getMD5ofStr(businessPassword))) {
			return ShowMessage(MSG_TYPE_STOP, "交易密码有误", "点击返回", "page!recharge.do?userId=" + currentUser.getUserId(), 0);
		}
		
		
		int result=useraccountService.validatePurchase(currentUser, recharge);
		
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!recharge.do?userId=" + currentUser.getUserId(), 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "划款失败", "点击返回",
					"page!recharge.do?userId=" + currentUser.getUserId(), 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!recharge.do?userId=" + currentUser.getUserId(), 0);
		}
		UserCharge ud = new UserCharge();
		ud.setVoucherType(this.useraccountdealdetail.getVoucherType());
		ud.setVoucherCode(this.useraccountdealdetail.getVoucherCode());
		ud.setRemark(this.useraccountdealdetail.getRemark());;
		UserCharge ud2 = new UserCharge();
		ud2.setVoucherType(this.useraccountdealdetail.getVoucherType());
		ud2.setVoucherCode(this.useraccountdealdetail.getVoucherCode());
		ud2.setRemark(this.useraccountdealdetail.getRemark());
		UserCharge ud3 = new UserCharge();
		ud3.setVoucherType(this.useraccountdealdetail.getVoucherType());
		ud3.setVoucherCode(this.useraccountdealdetail.getVoucherCode());
		ud3.setRemark(this.useraccountdealdetail.getRemark());
		UserCharge ud4 = new UserCharge();
		ud4.setVoucherType(this.useraccountdealdetail.getVoucherType());
		ud4.setVoucherCode(this.useraccountdealdetail.getVoucherCode());
		ud4.setRemark(this.useraccountdealdetail.getRemark());
		// 当前账户划款
		//currentUser=userService.findUserById(currentUser.getUserId());
		//管理员--一级商户--二级商户
		if (currentUser.getUsergroup().getGroupType()<=Const.GroupType.STAFF && users.getUsergroup().getGroupType() == Const.GroupType.GRADE_TWO) {
			Users parentUser = userService.findUserById(users.getParentUsers().getUserId());
			
			//管理员--一级商户
			ICharge iCharge = new Charge4User(ud, null, recharge, currentUser, parentUser, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.REMIT);
//			useraccountService.refreshAccountes(null,currentUser,parentUser,getSessionUser().getUserName(),ud, recharge,
//					Const.UseraccountdealdetailFlag.REMIT);
			ICharge iCharge2 = new Charge4User(ud2, null, recharge, parentUser, currentUser, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//			useraccountService.refreshAccountes(null,parentUser,currentUser,getSessionUser().getUserName(),ud2,recharge,
//					Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			
			//一级商户--二级商户
			ICharge iCharge3 = new Charge4User(ud3, null, recharge, parentUser, users, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.REMIT);
//			useraccountService.refreshAccountes(null,parentUser,users,getSessionUser().getUserName(),ud3, recharge,
//					Const.UseraccountdealdetailFlag.REMIT);
			ICharge iCharge4 = new Charge4User(ud4, null, recharge, users, parentUser, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//			useraccountService.refreshAccountes(null,users,parentUser,getSessionUser().getUserName(),ud4,recharge,
//					Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
		    UserChargeEngine.getInstance().addLast(iCharge3);
		    UserChargeEngine.getInstance().addLast(iCharge4);
		}else {
			ICharge iCharge = new Charge4User(ud, null, recharge, currentUser, users, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.REMIT);
//			useraccountService.refreshAccountes(null,currentUser,users,getSessionUser().getUserName(),ud, recharge,
//					Const.UseraccountdealdetailFlag.REMIT);
			// 下级账户充值
			//users = userService.findUserById(users.getUserId());
			ICharge iCharge2 = new Charge4User(ud2, null, recharge, users, currentUser, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//			useraccountService.refreshAccountes(null,users,currentUser,getSessionUser().getUserName(),ud2,recharge,Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
			//如果一级商户只有一个下属,则这笔划款自动转向二级商户
			List<Users> children=userService.findChildUsers(users.getUserId());
			if (children.size()==1) {
				Users child=children.get(0);
				ICharge iCharge3 = new Charge4User(ud3, null, recharge, users, child, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.REMIT);
//				useraccountService.refreshAccountes(null,users,currentUser,getSessionUser().getUserName(),ud3, recharge,
//						Const.UseraccountdealdetailFlag.REMIT);
				// 下级账户充值
				ICharge iCharge4 = new Charge4User(ud4, null, recharge, child, users, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//				useraccountService.refreshAccountes(null,child,users,getSessionUser().getUserName(),ud4,recharge,Const.UseraccountdealdetailFlag.USER_DEPOSIT);
				UserChargeEngine.getInstance().addLast(iCharge3);
			    UserChargeEngine.getInstance().addLast(iCharge4);
			}
		}
		return ShowMessage(MSG_TYPE_NORMAL, "划款成功", "返回列表",
				"list!manageUserEx.do?userId=" + currentUser.getUserId(), 0);
	}
	
	/**
	 * 提款提交
	 * @return
	 * @throws Exception
	 */
	public String doDrawing() throws Exception{
		currentUser = userService.findUserById(currentUser.getUserId());
		if (recharge<0) {
			return ShowMessage(MSG_TYPE_STOP, "交易金额不能小于0", "点击返回", "page!recharge.do?userId=" + currentUser.getUserId(), 0);
		}
		users = userService.findUserById(users.getUserId());
		MD5 md5 = MD5.getiInstance();
		if (getSessionUser().getUsergroup().getGroupType()>Const.GroupType.STAFF &&!currentUser.getBusinessPassword().equals( md5.getMD5ofStr(businessPassword))) {
			return ShowMessage(MSG_TYPE_STOP, "交易密码有误", "点击返回", "page!recharge.do?userId=" + currentUser.getUserId(), 0);
		}
		
		int result=useraccountService.validatePurchase(users, recharge);
		
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!recharge.do?userId=" + currentUser.getUserId(), 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "提款/冲正失败", "点击返回",
					"page!recharge.do?userId=" + currentUser.getUserId(), 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!recharge.do?userId=" + currentUser.getUserId(), 0);
		}
		UserCharge ud = new UserCharge();
		ud.setVoucherType(this.useraccountdealdetail.getVoucherType());
		ud.setVoucherCode(this.useraccountdealdetail.getVoucherCode());
		ud.setRemark(this.useraccountdealdetail.getRemark());;
		UserCharge ud2 = new UserCharge();
		ud2.setVoucherType(this.useraccountdealdetail.getVoucherType());
		ud2.setVoucherCode(this.useraccountdealdetail.getVoucherCode());
		ud2.setRemark(this.useraccountdealdetail.getRemark());
		UserCharge ud3 = new UserCharge();
		ud3.setVoucherType(this.useraccountdealdetail.getVoucherType());
		ud3.setVoucherCode(this.useraccountdealdetail.getVoucherCode());
		ud3.setRemark(this.useraccountdealdetail.getRemark());
		UserCharge ud4 = new UserCharge();
		ud4.setVoucherType(this.useraccountdealdetail.getVoucherType());
		ud4.setVoucherCode(this.useraccountdealdetail.getVoucherCode());
		ud4.setRemark(this.useraccountdealdetail.getRemark());
		if (currentUser.getUsergroup().getGroupType()<=Const.GroupType.STAFF && users.getUsergroup().getGroupType() == Const.GroupType.GRADE_TWO) {
			Users parentUser = userService.findUserById(users.getParentUsers().getUserId());
			
			//管理员--一级商户
			ICharge iCharge = new Charge4User(ud, null, recharge, currentUser, parentUser, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAWING);
//			useraccountService.refreshAccountes(null,currentUser,parentUser,getSessionUser().getUserName(),ud, recharge,
//					Const.UseraccountdealdetailFlag.DRAWING);
			ICharge iCharge2 = new Charge4User(ud2, null, recharge, parentUser, currentUser, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAWN);
//			useraccountService.refreshAccountes(null,parentUser,currentUser,getSessionUser().getUserName(),ud2,recharge,
//					Const.UseraccountdealdetailFlag.DRAWN);
			
			//一级商户--二级商户
			ICharge iCharge3 = new Charge4User(ud3, null, recharge, parentUser, users, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAWING);
//			useraccountService.refreshAccountes(null,parentUser,users,getSessionUser().getUserName(),ud3, recharge,
//					Const.UseraccountdealdetailFlag.DRAWING);
			ICharge iCharge4 = new Charge4User(ud4, null, recharge, users, parentUser, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAWN);
//			useraccountService.refreshAccountes(null,users,parentUser,getSessionUser().getUserName(),ud4,recharge,
//					Const.UseraccountdealdetailFlag.DRAWN);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
		    UserChargeEngine.getInstance().addLast(iCharge3);
		    UserChargeEngine.getInstance().addLast(iCharge4);
		}else {
			// 当前账户提款
			ICharge iCharge = new Charge4User(ud, null, recharge, currentUser, users, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAWING);
//			useraccountService.refreshAccountes(null,currentUser,users,getSessionUser().getUserName(),ud, recharge,
//					Const.UseraccountdealdetailFlag.DRAWING);
			// 下级账户被提款
			ICharge iCharge2 = new Charge4User(ud2, null, recharge, users, currentUser, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAWN);
//			useraccountService.refreshAccountes(null,users,currentUser,getSessionUser().getUserName(),ud2, recharge,Const.UseraccountdealdetailFlag.DRAWN);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
		}
		return ShowMessage(MSG_TYPE_NORMAL, "划款成功", "返回列表",
				"list!manageUserEx.do?userId=" + currentUser.getUserId(), 0);
	}
	
	/**
	 * 提取佣金
	 * @return
	 * @throws Exception
	 */
	public String doDrawingCommission() throws Exception{
		if (userId==null) {
			users = getSessionUser();
		}else {
			users = userService.findUserById(userId);
		}
		ICharge iCharge = new Charge4User(new UserCharge(), null, recharge, users, getSessionUser(), getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAW_COMMISSION);
		UserChargeEngine.getInstance().addLast(iCharge);
//		useraccountService.refreshAccountes(null, users,getSessionUser(),getSessionUser().getUserName(), new UserCharge(), 0d, Const.UseraccountdealdetailFlag.DRAW_COMMISSION);
		//return ShowMessage(MSG_TYPE_NORMAL, "提取佣金成功", "", "", 0);
		return "success";
	}

	/**
	 * 修改User信息[页面]
	 * 
	 */
	@Privilege(permissionName = "users", privilegeAccess = Const.PrivilegeAccessConst.MODIFY)
	public String viewUser() {
		// users = (Users)getSession().get(Const.USER_SESSION_KEY);
		users = this.userService.findUserById(getUserId());
		modifyUsers = new ModifyUsers();
		copyProperties(modifyUsers, users);
		return "do";
	}

	/**
	 * 修改志愿者信息[提交]
	 * 
	 * @return
	 */
	public String modifyUser() {
		users = this.userService.findUserById(getUserId());
		String codeTemp = users.getVerificationCode();
		if(!users.getMobile().equals(modifyUsers.getMobile())){
			if(!verificationCode.equals(codeTemp)){
				ResponseUtils.renderText(getHttpServletResponse(), "error");
				return null;
			}
		}
		copyProperties(users, modifyUsers);
		userService.update(users);
		switch (users.getUsergroup().getGroupType()) {
		case Const.GroupType.STAFF:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		case Const.GroupType.GRADE_ONE:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list!listUserEx.do", 0);
		case Const.GroupType.GRADE_TWO:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list!manageUserEx.do?userId="+userId, 0);
		default:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		}
		
	}
	
	/**
	 * 发送短信
	 * @return
	 */
	public String modifyUserMesSendCode(){
		Users user = userService.findUserById(userId);
		String verifiCode = RandomUtils.getVerificationCode(6);
		user.setVerificationCode(verifiCode);
		user.setValidTime(new Date());				
		userService.update(user);
		int result = SDKClientUtil.SendSMS_(new String[]{user.getMobile()}, "【修改用户信息】您的手机短信验证码是" + " : " + verifiCode +";退订回复TD【妙鸣在线】","110", 5);
		if(result==0){
			ResponseUtils.renderText(getHttpServletResponse(), "success");
		}else{
			ResponseUtils.renderText(getHttpServletResponse(), "error");
		}
		
		return null;
	}
	/**
	 * 去掉加密key发送短信
	 * @return
	 */
	public String delKeySendCode(){
		Users user = userService.findUserById(userId);
		if(user.getOtp()==null){
			ResponseUtils.renderText(getHttpServletResponse(), "noOtp");
		}else{
			String verifiCode = RandomUtils.getVerificationCode(6);
			user.setVerificationCode(verifiCode);
			user.setValidTime(new Date());				
			userService.update(user);
			int result = SDKClientUtil.SendSMS_(new String[]{user.getMobile()}, "【取消加密key】您的手机短信验证码是" + " : " + verifiCode +";退订回复TD;【妙鸣在线】","110", 5);
			if(result==0){
				ResponseUtils.renderText(getHttpServletResponse(), "success");
			}else{
				ResponseUtils.renderText(getHttpServletResponse(), "error");
			}
		}
		return null;
	}
	/**
	 * 增加加密狗页面
	 * @return
	 */
	public String addKey(){
		users = this.userService.findUserById(userId);
		return "addKey";
	}
	/**
	 * 取消加密狗页面
	 * @return
	 */
	public String delKey(){
		users = this.getSessionUser();
		return "delKey";
	}
	
	/**
	 * 取消加密狗提交
	 * @return
	 */
	public String doDelKey(){
		users = this.userService.findUserById(users.getUserId());
		Long otpCodeTemp = 0l;
		String codeTemp = users.getVerificationCode();
			if(users.getOtp()==null){
				ResponseUtils.renderText(getHttpServletResponse(), "noOtp");
			}
//			else if(!verificationCode.equals(codeTemp)){
//				ResponseUtils.renderText(getHttpServletResponse(), "error");
//			}
			else{
				otpCodeTemp = users.getOtp().getKeyId();
				users.setOtp(null);
				userService.update(users);
				String message = users.getUserName()+"您好，您的帐号已经取消了加密key，加密key编码为: "+ otpCodeTemp+ ",如有疑问，请致电我司客服！4000307517 ;退订回复TD【妙鸣在线】";	
				SDKClientUtil.SendSMS(new String[]{users.getMobile()}, message,"110", 5);
				CustomerFeedback cfb = new CustomerFeedback();
				cfb.setUserName(users.getUserName());
				cfb.setUserPhone(users.getMobile());
				cfb.setCfTime(new Date());
				cfb.setCfMessage("用户: "+users.getUserName()+" 取消了加密key绑定，加密key编码为："+otpCodeTemp);
				cfb.setCfTitle("用户: "+users.getUserName()+" 取消了加密key绑定");
				customerFeedbackService.addcfb(cfb);
//				return ShowMessage(MSG_TYPE_NORMAL, "操作成功", "", "", 0);
				users = userService.findUserById(users.getUserId());
				//取消加密key以后重新获取user 并放入session
				this.getSession().put(Const.USER_SESSION_KEY, users);
				ResponseUtils.renderText(getHttpServletResponse(), "success");
			}
			return null;
	}
	/**
	 * 增加加密狗提交
	 * @return
	 */
	public String doAddKey(){
		Long keyId = users.getOtp().getKeyId();
		String message = "";
		users = this.userService.findUserById(users.getUserId());
		if (keyId!=null) {
			OTP otp=otpService.findById(keyId);
			if (otp==null) {
				return ShowMessage(MSG_TYPE_STOP, "没有该加密key", "点击返回", "page!addKey.do?userId="+users.getUserId(), 0);
			}
			if(users.getOtp()!=null){
				message = users.getUserName()+"您好，您的帐号已经修改了加密key，修改后的加密key编码为: "+ keyId+ " ,如有疑问，请致电我司客服！4000307517;退订回复TD【妙鸣在线】";
			}else{
				message = users.getUserName()+"您好，您的帐号已经增加了加密key，加密key编码为: "+ keyId+ " ,如有疑问，请致电我司客服！4000307517;退订回复TD【妙鸣在线】";
			}
			users.setOtp(otp);
		}else {
			message = users.getUserName()+"您好，您的帐号已经取消了加密key，加密key编码为: "+ users.getOtp().getKeyId()+ " ,如有疑问，请致电我司客服！4000307517;退订回复TD【妙鸣在线】";
			users.setOtp(null);
		}
		userService.update(users);
		SDKClientUtil.SendSMS(new String[]{users.getMobile()}, message,"110", 5);
		return ShowMessage(MSG_TYPE_NORMAL, "设置成功", "", "", 0);
	}
	
	/**
	 * 联动财付通支付页面
	 * @return
	 */
	public String liandongPayPage(){
		//users=getSessionUser();
		charge = LiandongUtil.HANDLING_CHARGE;
		return "liandongPayPage";
	}
	
	/**
	 * 更新冲正次数
	 * @return
	 */
	public String refreshReversal(){
		users = userService.findUserById(userId);
		users.setReversalCount(users.getMaxReversalCount());
		userService.update(users);
		switch (users.getUsergroup().getGroupType()) {
		case Const.GroupType.STAFF:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		case Const.GroupType.GRADE_ONE:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list!listUserEx.do", 0);
		case Const.GroupType.GRADE_TWO:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list!manageUserEx.do?userId="+userId, 0);
		default:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		}
	}
	
	/**
	 * 批量更新冲正次数
	 * @return
	 */
	public String batchRefreshReversal() {
		List<Users> list = (List<Users>) userService.findAll(Users.class);
		for (Users users : list) {
			users.setReversalCount(users.getMaxReversalCount());
		}
		userService.saveOrUpdateAll(list);
		return ShowMessage(MSG_TYPE_NORMAL, "更新冲正次数成功", "", "", 0);
	}
	
	/**
	 * 更新账户余额
	 * @return
	 */
	public String commitAccount() {
		users = userService.findUserById(userId);
		useraccountService.commitAccount(users);
		switch (users.getUsergroup().getGroupType()) {
		case Const.GroupType.STAFF:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		case Const.GroupType.GRADE_ONE:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list!listUserEx.do", 0);
		case Const.GroupType.GRADE_TWO:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list!manageUserEx.do?userId="+userId, 0);
		default:
			return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		}
	}
	
	/**
	 * 批量更新账户余额
	 * @return
	 */
	public String batchCommitAccount() {
		List<Users> list = (List<Users>) userService.findAll(Users.class);
		for (Users users : list) {
			useraccountService.commitAccount(users);
		}
		return ShowMessage(MSG_TYPE_NORMAL, "更新账户余额成功", "", "", 0);
	}
	
	/**
	 * 增加用户组(页面)
	 * @return
	 */
	public String usergroup() {
		usergroup = new Usergroup();
		return "usergroup";
	}
	
	/**
	 * 增加用户组(提交)
	 * @return
	 * @throws Exception 
	 */
	public String doAddUsergroup() throws Exception {
		usergroup = usergroupService.addUsergroup(usergroup);
		int typeId = usergroup.getGroupType();
		Usergroup usergroup2 = usergroupService.findUsergroupById(typeId);
		Set<Menutree> menutrees = usergroup2.getMenutrees();
		Set<Menutree> menutrees2 = new HashSet<Menutree>();
		menutrees2.addAll(menutrees);
		usergroup.setMenutrees(menutrees2);
		Set<Permission> permissions = usergroup2.getPermissions();
		Set<Permission> permissions2 = new HashSet<Permission>();
		permissions2.addAll(permissions);
		usergroup.setPermissions(permissions2);
		usergroupService.update(usergroup);
		 //修改后需要更新系统内存里的权限列表
		permissionVerification.ReloadDate();
		return ShowMessage(MSG_TYPE_NORMAL, "增加用户组成功", "点击返回", "list!listUsergroup.do", 0);
	}
	
	public List<Usergroup> getUsergroups() {
		return usergroups;
	}

	public void setUsergroups(List<Usergroup> usergroups) {
		this.usergroups = usergroups;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ModifyUsers getModifyUsers() {
		return modifyUsers;
	}

	public void setModifyUsers(ModifyUsers modifyUsers) {
		this.modifyUsers = modifyUsers;
	}

	public Users getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Users currentUser) {
		this.currentUser = currentUser;
	}

	public Double getRecharge() {
		return recharge;
	}

	public void setRecharge(Double recharge) {
		this.recharge = recharge;
	}

	public List<UserLevel> getUserLevels() {
		return userLevels;
	}

	public void setUserLevels(List<UserLevel> userLevels) {
		this.userLevels = userLevels;
	}

	public Users getParentUsers() {
		return parentUsers;
	}

	public void setParentUsers(Users parentUsers) {
		this.parentUsers = parentUsers;
	}

	public Useraccount getCurrentUseraccount() {
		return currentUseraccount;
	}

	public void setCurrentUseraccount(Useraccount currentUseraccount) {
		this.currentUseraccount = currentUseraccount;
	}

	public Useraccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

	public UserCharge getUseraccountdealdetail() {
		return useraccountdealdetail;
	}

	public void setUseraccountdealdetail(UserCharge useraccountdealdetail) {
		this.useraccountdealdetail = useraccountdealdetail;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}
	
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getBusinessPassword() {
		return businessPassword;
	}

	public void setBusinessPassword(String businessPassword) {
		this.businessPassword = businessPassword;
	}

	public Usergroup getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

//	public Users getRecodeUser() {
//		return recodeUser;
//	}
//
//	public void setRecodeUser(Users recodeUser) {
//		this.recodeUser = recodeUser;
//	}

}

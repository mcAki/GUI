package com.sys.volunteer.users;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.MD5;
import com.sys.volunteer.common.ResponseUtils;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.pojo.UserUtils.ModifyBusinessPassword;
import com.sys.volunteer.pojo.UserUtils.ModifyLoginPassword;
import com.sys.volunteer.pojo.UserUtils.ModifyPhonePassword;
import com.sys.volunteer.pojo.UserUtils.UserUpdateMobile;
import com.sys.volunteer.pojo.UserUtils.UserUpdateProfile;
import com.sys.volunteer.useraccount.UseraccountService;
/**
 * 处理用户信息
 * @author Administrator
 *
 */
@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class UserUtilsAction extends BaseAction implements Preparable {
	@Resource
	UserService userService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	OperationLogService operationLogService;
	
	private Integer missionId;

	private Integer teamId;

	private Users users;
	
	private Integer operateCode;

	private String userId;
	
	private Useraccount useraccount;
	
	/**
	 * 用户登录密码
	 */
	private String userPassword;
	/**
	 * 用户交易密码
	 */
	private String businessPassword;

	/**
	 * 重新输入密码
	 */
	private String retypeNewUserPassword;
	/**
	 * 重新输入密码
	 */
	private String retypeNewBusinessPassword;
	

	/**
	 * 电话操作码
	 */
	private String operationCode;
	/**
	 * 重新输入电话操作码
	 */
	private String retypeNewOperationCode;
	/**
	 * 修改电话操作码
	 */
	private ModifyPhonePassword modifyPhonePassword;
	/**
	 * 修改登录密码
	 */
	private ModifyLoginPassword modifyLoginPassword;
	/**
	 * 修改交易密码
	 */
	private ModifyBusinessPassword modifyBusinessPassword;
	

	/**
	 * 绑定手机
	 */
	private UserUpdateMobile userUpdateMobile;
	/**
	 * 修改个人信息
	 */
	private UserUpdateProfile userUpdateProfile;

	/**
	 * 手机号码
	 */
	private String mobile ;

	/**
	 * 验证码
	 */
	private String verificationCode;
	

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {

	}

	/**
	 * 验证数据
	 */
	@Override
	public void validate() {

	}

	/**
	 * 个人基本信息查询
	 */
	public String viewUserProfile() {
		if(null==operateCode){
			users = (Users) getSession().get(Const.USER_SESSION_KEY);
			useraccount=useraccountService.findUseraccountByUseraccountId(users);
			return "do";
		}else {
			users = userService.findUserById(userId);
			useraccount=useraccountService.findUseraccountByUseraccountId(users);
			return "viewUserProfile";
		}
		
	}
	
	public String showPersonalByUserId() throws Exception{
		users=userService.findUserById(userId);
		useraccount=useraccountService.findUseraccountByUseraccountId(users);
		return "showPersonalByUserId";
	}
	public String showPersonalByUserId4() throws Exception{
		users=userService.findUserById(userId);
		useraccount=useraccountService.findUseraccountByUseraccountId(users);
		return "showPersonalByUserId4";
	}

	/**
	 * 修改登陆密码[页面]
	 * 
	 * @return
	 */
	public String loginPassword() {
		users = (Users) getSession().get(Const.USER_SESSION_KEY);
		modifyLoginPassword = new ModifyLoginPassword();
		copyProperties(modifyLoginPassword, users);
		return "do";
	}
	/**
	 * 修改交易密码[页面]
	 * 
	 * @return
	 */
	public String businessPassword() {
		users = (Users) getSession().get(Const.USER_SESSION_KEY);
		modifyBusinessPassword = new ModifyBusinessPassword();
		copyProperties(modifyBusinessPassword, users);
		return "do";
	}
	
	/**
	 * 修改登陆密码[提交]
	 * 
	 * @return
	 */
	public String modifyLoginPassword() {
		// session中取得当前用户
		users = (Users) getSession().get(Const.USER_SESSION_KEY);
		// 实例化md5
		MD5 md5 = MD5.getiInstance();
		// 校验密码
		if (null==users.getUserPassword()||users.getUserPassword().equals("")) {
			if (!users.getMobile().equals(userPassword)) addFieldError("userPassword", "你原密码为空,请输入正确手机号");
		}else {
			if (!users.getUserPassword().equals(md5.getMD5ofStr(userPassword))) addFieldError(
				"userPassword", "您输入的旧密码有误!");
		}
		if ("".equals(retypeNewUserPassword) || "".equals(modifyLoginPassword.getUserPassword())) addFieldError(
			"userPassword", "新密码不能为空!");

		if (!md5.getMD5ofStr(retypeNewUserPassword).equals(
			md5.getMD5ofStr(modifyLoginPassword.getUserPassword()))) addFieldError("userPassword",
			"您输入的新密码两次不相等!");
		// 统一输出错误信息
		if (getFieldErrors().size() > 0) return INPUT;

		modifyLoginPassword.setUserPassword(md5.getMD5ofStr(modifyLoginPassword.getUserPassword()));
		copyProperties(users, modifyLoginPassword);
		userService.update(users);
		operationLogService.addOperationLog(users, null, null, Const.OperationLogType.MODIFY_PWD, Const.OperationLogResult.SUCCESS, null);
		return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
			getHttpServletRequest().getContextPath() + "/frame!defPage.do", 0);

	}
	/**
	 * 修改交易密码[提交]
	 * 
	 * @return
	 */
	public String modifyBusinessPassword() {
		// session中取得当前用户
		users = (Users) getSession().get(Const.USER_SESSION_KEY);
		// 实例化md5
		MD5 md5 = MD5.getiInstance();
		// 校验密码
		if (null==users.getBusinessPassword()||users.getBusinessPassword().equals("")) {
			if (!users.getMobile().equals(businessPassword)) addFieldError("businessPassword", "你原密码为空,请输入正确手机号");
		}else {
			if (!users.getBusinessPassword().equals(md5.getMD5ofStr(businessPassword))) addFieldError(
					"businessPassword", "您输入的旧密码有误!");
		}
		if ("".equals(retypeNewBusinessPassword) || "".equals(modifyBusinessPassword.getBusinessPassword())) addFieldError(
				"businessPassword", "新密码不能为空!");
		
		if (!md5.getMD5ofStr(retypeNewBusinessPassword).equals(
				md5.getMD5ofStr(modifyBusinessPassword.getBusinessPassword()))) addFieldError("businessPassword",
				"您输入的新密码两次不相等!");
		// 统一输出错误信息
		if (getFieldErrors().size() > 0) return INPUT;
		
		modifyBusinessPassword.setBusinessPassword(md5.getMD5ofStr(modifyBusinessPassword.getBusinessPassword()));
		copyProperties(users, modifyBusinessPassword);
		userService.update(users);
		operationLogService.addOperationLog(users, null, null, Const.OperationLogType.MODIFY_PWD, Const.OperationLogResult.SUCCESS, null);
		return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
				getHttpServletRequest().getContextPath() + "/frame!defPage.do", 0);
		
	}
	
	/**
	 * 重置密码
	 * @return
	 * @throws Exception
	 */
	public String resetPassword() throws Exception{
		users=userService.findUserById(userId);
		String codeTemp = users.getVerificationCode();
		if(!(codeTemp.equals(verificationCode))||"".equals(verificationCode)){
			ResponseUtils.renderText(getHttpServletResponse(), "error");
			return null;
		}
		//默认重置未123456
		users.setUserPassword("E10ADC3949BA59ABBE56E057F20F883E");
		userService.update(users);
		operationLogService.addOperationLog(users, null, null, Const.OperationLogType.RESET_PWD, Const.OperationLogResult.SUCCESS, null);
		switch (users.getUsergroup().getGroupType()) {
		case Const.GroupType.STAFF:
			return ShowMessage(MSG_TYPE_NORMAL, "重置密码成功", "点击返回",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		case Const.GroupType.GRADE_ONE:
			return ShowMessage(MSG_TYPE_NORMAL, "重置密码成功", "点击返回",
					getHttpServletRequest().getContextPath() + "/users/list!listUserEx.do", 0);
		case Const.GroupType.GRADE_TWO:
			return ShowMessage(MSG_TYPE_NORMAL, "重置密码成功", "点击返回",
					getHttpServletRequest().getContextPath() + "/users/list!manageUserEx.do?userId="+userId, 0);
		default:
			return ShowMessage(MSG_TYPE_NORMAL, "重置密码成功", "点击返回",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		}
	}
	/**
	 * 重置交易密码
	 * @return
	 * @throws Exception
	 */
	public String resetBusinessPassword() throws Exception{
		users=userService.findUserById(userId);
		String codeTemp = users.getVerificationCode();
		if(!(codeTemp.equals(verificationCode))||"".equals(verificationCode)){
			ResponseUtils.renderText(getHttpServletResponse(), "error");
			return null;
		}
		//默认重置未123456
		users.setBusinessPassword("E10ADC3949BA59ABBE56E057F20F883E");
		userService.update(users);
		operationLogService.addOperationLog(users, null, null, Const.OperationLogType.RESET_PWD, Const.OperationLogResult.SUCCESS, null);
		switch (users.getUsergroup().getGroupType()) {
		case Const.GroupType.STAFF:
			return ShowMessage(MSG_TYPE_NORMAL, "重置密码成功", "点击返回",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		case Const.GroupType.GRADE_ONE:
			return ShowMessage(MSG_TYPE_NORMAL, "重置密码成功", "点击返回",
					getHttpServletRequest().getContextPath() + "/users/list!listUserEx.do", 0);
		case Const.GroupType.GRADE_TWO:
			return ShowMessage(MSG_TYPE_NORMAL, "重置密码成功", "点击返回",
					getHttpServletRequest().getContextPath() + "/users/list!manageUserEx.do?userId="+userId, 0);
		default:
			return ShowMessage(MSG_TYPE_NORMAL, "重置密码成功", "点击返回",
					getHttpServletRequest().getContextPath() + "/users/list.do", 0);
		}
	}
	

	/**
	 * 修改手机号码[页面]
	 * 
	 * @return
	 */
	public String modifyMobile() {
		users = (Users) getSession().get(Const.USER_SESSION_KEY);
		mobile = users.getMobile();
		return "do";
	}
	
	/**
	 * 修改手机号码
	 * @return
	 */
	public String doModifyMobile(){
		// session中取得当前用户
		users = (Users) getSession().get(Const.USER_SESSION_KEY);
		String regex = "^[1][3-8]\\d{9}$";
		if (mobile == null || mobile.equals("")) 
			addFieldError("mobile", "手机号码不能为空");
		if (mobile.trim().length()!=11) 
			addFieldError("mobile", "手机号码必须是11位");
		if (!mobile.matches(regex)) {
			addFieldError("mobile", "填写的手机号码不正确");
			}else {
				users.setMobile(mobile);
				userService.update(users);
				operationLogService.addOperationLog(users, null, users.getMobile(), Const.OperationLogType.MODIFY_MOBILE, Const.OperationLogResult.SUCCESS, null);
				return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
						getHttpServletRequest().getContextPath() + "/frame!defPage.do", 0);
			}
		return INPUT;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Users getUsers() {
		return users;
	}

	public String getRetypeNewUserPassword() {
		return retypeNewUserPassword;
	}

	public void setRetypeNewUserPassword(String retypeNewUserPassword) {
		this.retypeNewUserPassword = retypeNewUserPassword;
	}

	public String getRetypeNewOperationCode() {
		return retypeNewOperationCode;
	}

	public void setRetypeNewOperationCode(String retypeNewOperationCode) {
		this.retypeNewOperationCode = retypeNewOperationCode;
	}

	/**
	 * @return the userUpdateMobile
	 */
	public UserUpdateMobile getUserUpdateMobile() {
		return userUpdateMobile;
	}

	/**
	 * @param userUpdateMobile
	 *            the userUpdateMobile to set
	 */
	public void setUserUpdateMobile(UserUpdateMobile userUpdateMobile) {
		this.userUpdateMobile = userUpdateMobile;
	}

	/**
	 * @return the userUpdateProfile
	 */
	public UserUpdateProfile getUserUpdateProfile() {
		return userUpdateProfile;
	}

	/**
	 * @param userUpdateProfile
	 *            the userUpdateProfile to set
	 */
	public void setUserUpdateProfile(UserUpdateProfile userUpdateProfile) {
		this.userUpdateProfile = userUpdateProfile;
	}

	public ModifyLoginPassword getModifyLoginPassword() {
		return modifyLoginPassword;
	}

	public void setModifyLoginPassword(ModifyLoginPassword modifyLoginPassword) {
		this.modifyLoginPassword = modifyLoginPassword;
	}

	public ModifyPhonePassword getModifyPhonePassword() {
		return modifyPhonePassword;
	}

	public void setModifyPhonePassword(ModifyPhonePassword modifyPhonePassword) {
		this.modifyPhonePassword = modifyPhonePassword;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getMissionId() {
		return missionId;
	}

	public void setMissionId(Integer missionId) {
		this.missionId = missionId;
	}

	public Integer getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(Integer operateCode) {
		this.operateCode = operateCode;
	}

	public Useraccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public ModifyBusinessPassword getModifyBusinessPassword() {
		return modifyBusinessPassword;
	}

	public void setModifyBusinessPassword(
			ModifyBusinessPassword modifyBusinessPassword) {
		this.modifyBusinessPassword = modifyBusinessPassword;
	}
	public String getRetypeNewBusinessPassword() {
		return retypeNewBusinessPassword;
	}

	public void setRetypeNewBusinessPassword(String retypeNewBusinessPassword) {
		this.retypeNewBusinessPassword = retypeNewBusinessPassword;
	}
	public String getBusinessPassword() {
		return businessPassword;
	}

	public void setBusinessPassword(String businessPassword) {
		this.businessPassword = businessPassword;
	}

}

package com.sys.volunteer.system;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.authority.AuthorityService;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.common.RandomUtils;
import com.sys.volunteer.common.ResponseUtils;
import com.sys.volunteer.common.SDKClientUtil;
import com.sys.volunteer.common.Const.LogConst;
import com.sys.volunteer.login.LoginService;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.otherMes.OtherMesService;
import com.sys.volunteer.otp.OTPService;
import com.sys.volunteer.permission.PermissionService;
import com.sys.volunteer.permission.PermissionVerification;
import com.sys.volunteer.pojo.Menutree;
import com.sys.volunteer.pojo.OtherMes;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.users.UserService;
import com.sys.volunteer.vo.PermissionVO;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	@Resource
	LoginService loginService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	OTPService otpService;
	@Resource
	UserService userService;
	@Resource
	private OtherMesService otherMesService;

	private Users user;

	private String verifycode;

	private int idCardType = 0;
	
	private Integer userType;
	
	private Double balance;
	
	private String keyId;
	
	private String mobile;
	
	private String loginName;
	
	List<OtherMes> otherMesRefres;
	
	@Resource
	private PermissionService permissionService;
	@Resource
	AuthorityService authorityService;
	
	@Resource
	OperationLogService operationLogService;

	@Resource
	PermissionVerification pv;

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public String admin() {
		return "admin";
	}

	public String doLoginIdCard() {
		return "loginSuccess";
	}

	@SuppressWarnings("unchecked")
	public String doLoginCard() {

		if (!getSession().containsKey(Const.VERIFY_IMAGE_SESSION_KEY)) {
			// 登陆失败
			return ShowMessage(MSG_TYPE_NORMAL, "请键入验证码", "点击返回", "login.do", 3);
		}
		String sessionVerifyCode = (String) getSession().get(Const.VERIFY_IMAGE_SESSION_KEY);

		if (!sessionVerifyCode.equals(verifycode.trim().toLowerCase())) {
			// 登陆失败
			return ShowMessage(MSG_TYPE_WARNING, "验证码错误!", "点击返回", "login.do", 3);
		}
//		if (userType==null||userType==0) {
//			return ShowMessage(MSG_TYPE_WARNING, "请选择商户类型!", "点击返回", "login.do", 3);
//		}
		if (userType==null||userType==0) {
		  return ShowMessage(MSG_TYPE_WARNING, "请选择登录方式!", "点击返回", "login.do", 3);
	    }
		
		String loginAddr = getHttpServletRequest().getRemoteAddr().toString();
		
		String enteredLoginName = user.getLoginName();
		String userEnteredPassword = user.getUserPassword();
		
		if (userEnteredPassword.equals("")) {
			return ShowMessage(MSG_TYPE_NORMAL, "请填写密码", "点击返回", "login.do", 3);
		}
		List<Users> users = loginService.checkLoginByUserName(enteredLoginName, userEnteredPassword,2);


		if (users == null || users.size() < 1) {
			// 登陆失败
			return ShowMessage(MSG_TYPE_WARNING, "登陆名称或密码错误!", "点击返回", "login.do", 3);
		} else {

			user = users.get(0);

			if (user.getUsergroup().getGroupType()<=Const.GroupType.STAFF) {
				return ShowMessage(MSG_TYPE_NORMAL, "请登陆管理员端", "点击返回", "adminLogin.do", 3);
			}
			if (user.getState()!=0) {
				return ShowMessage(MSG_TYPE_NORMAL, "你的账号已被删除或冻结,请联系管理员", "点击返回", "login.do", 3);
			}
			
			switch (userType) {
			case 2:
				keyId = keyId.split("\\,")[1].trim();
				if (user.getOtp()!=null) {
					if (!otpService.verifyOTP(user, keyId)) {
						return ShowMessage(MSG_TYPE_NORMAL, "加密key验证失败", "点击返回", "login.do", 3);
					}
				}else {
					if (!keyId.equals("000000")) {
						return ShowMessage(MSG_TYPE_NORMAL, "加密key验证失败", "点击返回", "login.do", 3);
					}
					//TODO: 短讯不行,暂时使用6个"0"
					//return ShowMessage(MSG_TYPE_NORMAL, "你还没有绑定加密Key", "点击返回", "login.do", 3);
					
				}
				break;

			case 3:
				keyId = keyId.split("\\,")[0].trim();
				if (!keyId.equals(user.getVerificationCode()) || System.currentTimeMillis() - user.getValidTime().getTime() > 3*60*1000) {
					return ShowMessage(MSG_TYPE_WARNING, "手机短信验证码有误!", "点击返回", "login.do", 3);
				}
				break;
			}
//			if (!keyId.equals(user.getVerificationCode()) || System.currentTimeMillis() - user.getValidTime().getTime() > 3*60*1000) {
//				if (user.getOtp()!=null) {
//					if (!otpService.verifyOTP(user, keyId)) {
//						return ShowMessage(MSG_TYPE_NORMAL, "加密key验证失败", "点击返回", "login.do", 3);
//					}
//				}else {
//					if (!keyId.equals("000000")) {
//						return ShowMessage(MSG_TYPE_NORMAL, "加密key验证失败", "点击返回", "login.do", 3);
//					}
//				}
////				return ShowMessage(MSG_TYPE_WARNING, "您填写手机短信验证码错误或验证码已超过有效期!", "点击返回", "login.do", 3);
//			}
			
			// 登陆成功
			// 把当前用户记录session
			this.getSession().put(Const.USER_SESSION_KEY, user);
//			getSessionUser().setUsergroup(user.getUsergroup());
			//TODO:获取余额
			//int accountId=user.getUseraccount().getUseraccountId();
			//Useraccount useraccount=(Useraccount)useraccountService.findById(Useraccount.class, accountId);
			//balance=useraccount.getBalance();
			// 透过权限组获取权限
			List<Menutree> menuTree = this.authorityService.getMenuTreeBy(this.user.getUsergroup());
			this.getSession().put(Const.USER_MENUTREE_SESSION_KEY, menuTree);

			SupplyKernel supplyKernel=SupplyKernel.getInstance();
			Map<Integer, ISupply> map=supplyKernel.getSupplyMapKeyId();
			this.getSession().put(Const.SUPPLYMAP_SESSION_KEY, map);
			log.info(LogConst.USERLOGIN + "ip:" + loginAddr 
						+ " username:" + user.getUserName() + "登入系统");
			//记录登入ip
			operationLogService.addOperationLog(user, null, null, Const.OperationLogType.LOGIN, Const.OperationLogResult.SUCCESS, loginAddr);
			//查询余额
			Useraccount ua = useraccountService.findUseraccountByUseraccountId(user);
			this.getSession().put("useraccount", ua);
			
			try {
				List<PermissionVO> permissionPageList = permissionService.query(user.getUsergroup().getId());
				this.getSession().put("permissionPageListSession", permissionPageList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			List<OtherMes>  otherMesTopNewList = otherMesService.findNewTop();
//			List<OtherMes>  otherMesCommonNewList = otherMesService.findCommonTop();
//			if(otherMesTopNewList==null||otherMesTopNewList.size()<=0) otherMesTopNewList = otherMesService.findByType(1);
//			if(otherMesTopNewList!=null&&otherMesTopNewList.size()>0){
//				this.getSession().put("otherMesTopNewFirst", otherMesTopNewList.get(0));
//				this.getSession().put("otherMesTopNewList", otherMesTopNewList);
//			}
//			if(otherMesCommonNewList!=null&&otherMesCommonNewList.size()>0){
//				this.getSession().put("otherMesCommonNewList", otherMesCommonNewList);
//			}
			if(user.getReadProtocolResult()<=0){
				return "readProtocol";
			}else{
				return "loginSuccess";
			}
			
		} 
	}

	@SuppressWarnings("unchecked")
	@Privilege(permissionName = "abc")
	public String doLogin() {

		String loginAddr = getHttpServletRequest().getRemoteAddr().toString();
		String enterUserName = user.getLoginName();
		String enterPassWord = user.getUserPassword();

		int port = getHttpServletRequest().getRemotePort();
		System.out.println(loginAddr + ":" + port);
		

		if (!getSession().containsKey(Const.VERIFY_IMAGE_SESSION_KEY)) {
			// 登陆失败
			return ShowMessage(MSG_TYPE_NORMAL, "您没法开启验证码功能,请检查你的cookies!", "点击返回",
				"adminLogin.do", 3);
		}
		String sessionVerifyCode = (String) getSession().get(Const.VERIFY_IMAGE_SESSION_KEY);

		if (!sessionVerifyCode.equals(verifycode)) {
			// 登陆失败
			log.info(LogConst.USERLOGIN + "ip:" + loginAddr + " uname:" + enterUserName
						+ "验证码错误");
			return ShowMessage(MSG_TYPE_WARNING, "验证码错误!", "点击返回", "adminLogin.do", 3);
		}
		
		if (enterPassWord.equals("")) {
			return ShowMessage(MSG_TYPE_NORMAL, "请填写密码", "点击返回", "adminLogin.do", 3);
		}
		
		List<Users> list = loginService.checkLogin(enterUserName, enterPassWord,2);
		if(!list.isEmpty()){
			user=list.get(0);
		}
		

		if (user == null) {
			// 登陆失败
			log.info(LogConst.USERLOGIN + "ip:" + loginAddr + " uname:" + enterUserName + "账号或密码错误");
			return ShowMessage(MSG_TYPE_WARNING, "账号或密码错误!", "点击返回", "adminLogin.do", 3);
		} 
		if ((user.getUserId() == null)||(user.getUserName()==null)) {
			// 登陆失败
			log.info(LogConst.USERLOGIN + "ip:" + loginAddr + " uname:" + enterUserName + "账号或密码错误");
			return ShowMessage(MSG_TYPE_WARNING, "账号或密码错误!", "点击返回", "adminLogin.do", 3);
		} 

		if (user.getOtp()!=null) {
			if (!otpService.verifyOTP(user, keyId)) {
				return ShowMessage(MSG_TYPE_NORMAL, "加密key验证失败", "点击返回", "adminLogin.do", 3);
			}
		}else {
			if (!keyId.equals("000000")) {
				return ShowMessage(MSG_TYPE_NORMAL, "加密key验证失败", "点击返回", "adminLogin.do", 3);
			}
//			return ShowMessage(MSG_TYPE_NORMAL, "你还没有绑定加密Key", "点击返回", "login.do", 3);

		}
		// 登陆成功
		this.getSession().put(Const.USER_SESSION_KEY, user);
//		getSessionUser().setUsergroup(user.getUsergroup());
		//TODO:获取余额
		//int accountId=user.getUseraccount().getUseraccountId();
		//Useraccount useraccount=(Useraccount)useraccountService.findById(Useraccount.class, accountId);
		//balance=useraccount.getBalance();
		List<Menutree> menuTree = this.authorityService.getMenuTreeBy(this.user.getUsergroup());
		this.getSession().put(Const.USER_MENUTREE_SESSION_KEY, menuTree);
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		Map<Integer, ISupply> map=supplyKernel.getSupplyMapKeyId();
		this.getSession().put(Const.SUPPLYMAP_SESSION_KEY, map);
		log.info(LogConst.USERLOGIN + "ip:" + loginAddr + " uname:" + user.getUserName()
					+ "登陆成功");
		//记录登入ip
		operationLogService.addOperationLog(user, null, null, Const.OperationLogType.LOGIN, Const.OperationLogResult.SUCCESS, loginAddr);
		//查询余额
		Useraccount ua = useraccountService.findUseraccountByUseraccountId(user);
		this.getSession().put("useraccount", ua);
		
		try {
			List<PermissionVO> permissionPageList = permissionService.query(user.getUsergroup().getId());
			this.getSession().put("permissionPageListSession", permissionPageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user.getReadProtocolResult()<=0){
			return "readProtocol";
		}else{
			return "loginSuccess";
		}
		
	}
	
	/*public String selOtherMesTopNewList(){
		
		List<OtherMes>  otherMesTopNewList = otherMesService.findNewTop();
		List<OtherMes>  otherMesCommonNewList = otherMesService.findCommonTop();
//		if(otherMesTopNewList==null||otherMesTopNewList.size()<=0){
//			otherMesTopNewList = otherMesService.findByType(1);
//		}
		if(otherMesTopNewList!=null&&otherMesTopNewList.size()>0){
			this.getSession().put("otherMesTopNewFirst", otherMesTopNewList.get(0));
			this.getSession().put("otherMesTopNewList", otherMesTopNewList);
		}
		if(otherMesCommonNewList!=null&&otherMesCommonNewList.size()>0){
			this.getSession().put("otherMesCommonNewList", otherMesCommonNewList);
		}
		
		List<OtherMes> listTemp = otherMesService.findNewTop();
		if(listTemp!=null&&listTemp.size()>0){
			otherMesRefres = listTemp;
		}else{
			otherMesRefres = otherMesService.findCommonTop();;
		}
		String json = JSONArray.fromObject(otherMesRefres).toString();
		ResponseUtils.renderJson(this.getHttpServletResponse(), json);
		return null;
	}*/
	
	public String readyProtocolAlready(){
		Users user = this.getSessionUser();
		user.setReadProtocolResult(1);
		userService.updateReadProtocolResult(user);
		return "loginSuccess";
	}
	public String toMolibe() throws Exception{
		
		/**
		 * <pre>
		 * 1.先判断用户名和密码是否存在
		 *   根据用户名和密码查找他的电话号码
		 *   
		 * 2.调用生成验证码方法(verificationCode)
		 * 3.记录生成当前系统时间(validTime)
		 * 4.保存验证码和当前系统时间到数据库
		 * 5.调用短信发送接口将验证码发送到用户手机端
		 * 6
		 * </pre>
		 */
	    
//		String loginName = user.getLoginName();
//		String enterLoginName = URLDecoder.decode(loginName, "UTF-8");		
		String enterLoginName = user.getLoginName();
		System.out.println("============================="+enterLoginName);
		List<Users> users = userService.findUserByLoginName(enterLoginName);
		if(users == null || users.size() < 1){
			return ShowMessage(MSG_TYPE_WARNING, "您输入的用户名不存在!", "点击返回", "login.do", 3);
		}else {
			Users user = users.get(0);
			if (user.getValidTime() == null) {
				sendMSM(user);
			} else {
				if (System.currentTimeMillis() - user.getValidTime().getTime() > 3*60*1000) {
					sendMSM(user);
				} else {
					return ShowMessage(MSG_TYPE_WARNING, "您过于频繁点击!请稍后再点击", "点击返回", "login.do", 3);
				}
			}
		}
        return SUCCESS;
	}
	public void toMolibeJson() throws Exception{
		
		/**
		 * <pre>
		 * 1.先判断用户名和密码是否存在
		 *   根据用户名和密码查找他的电话号码
		 *   
		 * 2.调用生成验证码方法(verificationCode)
		 * 3.记录生成当前系统时间(validTime)
		 * 4.保存验证码和当前系统时间到数据库
		 * 5.调用短信发送接口将验证码发送到用户手机端
		 * 6
		 * </pre>
		 */
		JSONObject json = new JSONObject();
//		String loginName = user.getLoginName();
		String enterLoginName = URLDecoder.decode(loginName, "UTF-8");
		if(enterLoginName == null || "".equals(enterLoginName)){
			json.put("message", "没有输入用户名");
		}else{
			System.out.println("============================="+enterLoginName);
			List<Users> users = userService.findUserByLoginName(enterLoginName);
			if(users == null || users.size() < 1){
				json.put("message", "您输入的用户名不存在");
			}else {
				int result_sendMSM = -1;
				Users user = users.get(0);
				if (user.getValidTime() == null) {
					result_sendMSM = sendMSM_02(user);
				} else {
					if (System.currentTimeMillis() - user.getValidTime().getTime() > 3*60*1000) {
						result_sendMSM = sendMSM_02(user);
					} else {
						json.put("message", "您过于频繁点击!请稍后再点击");
					}
				}
				if(result_sendMSM==0){
					json.put("message", "验证码发送成功");
				}else{
					json.put("message", "验证码发送失败");
				}
				System.out.println("------------------------------------------------------------------->"+result_sendMSM);
			}
		}
		ResponseUtils.renderJson(getHttpServletResponse(), json.toString());
	}

	
	
	private int sendMSM_02(Users user) {
		String verifiCode = RandomUtils.getVerificationCode(6);
		user.setVerificationCode(verifiCode);
		user.setValidTime(new Date());				
		userService.update(user);
		int j = SDKClientUtil.SendSMS_(new String[]{user.getMobile()}, "【登录验证】您的手机短信验证码是" + " : " + verifiCode +";退订回复TD【妙鸣在线】","110", 5);
		return j;
	}
	private void sendMSM(Users user) {
		String verifiCode = RandomUtils.getVerificationCode(6);
		user.setVerificationCode(verifiCode);
		user.setValidTime(new Date());				
		userService.update(user);
		SDKClientUtil.SendSMS(new String[]{user	.getMobile()}, "您的手机短信验证码是" + " : " + verifiCode +";退订回复TD【妙鸣在线】","110", 5);
	}
	

	/**
	 * 登出提交方法
	 * 
	 * @return
	 */
	public String doLogout() {
		this.getSession().remove(Const.USER_SESSION_KEY);
		this.getSession().remove(Const.USER_MENUTREE_SESSION_KEY);
		this.getSession().remove(Const.SUPPLYMAP_SESSION_KEY);
		return ShowMessage(MSG_TYPE_NORMAL, "你已经成功登出系统!", "确定", "login.do", 5);
	}

	@Override
	@Privilege(permissionName = "abc")
	public String execute() throws Exception {
		// return ShowMessage(MSG_TYPE_NORMAL, "跳转标题", 5, new MsgLinks("网易", "http://www.163.com"),
		// new MsgLinks("腾讯", "http://www.QQ.com"));
		return "page";
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(int idCardType) {
		this.idCardType = idCardType;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public List<OtherMes> getOtherMesRefres() {
		return otherMesRefres;
	}

	public void setOtherMesRefres(List<OtherMes> otherMesRefres) {
		this.otherMesRefres = otherMesRefres;
	}
	
}

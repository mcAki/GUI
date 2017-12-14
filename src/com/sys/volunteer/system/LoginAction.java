package com.sys.volunteer.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ages.model.Administrators;
import com.ages.model.Menutree;
import com.sys.volunteer.admin.AdminService;
import com.sys.volunteer.authority.AuthorityService;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Const.LogConst;
import com.sys.volunteer.gmmanage.GMKernel;
import com.sys.volunteer.vo.BroadcastVo;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	
	@Resource
	AdminService adminService;
	
	@Resource
	AuthorityService authorityService;

	private Administrators user;
	private String verifycode;
	public String doLogin() {

		String loginAddr = getHttpServletRequest().getRemoteAddr().toString();
		String enterUserName = user.getUserName();

		int port = getHttpServletRequest().getRemotePort();
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
		user = adminService.checkLogin(user.getUserName(), user.getPassWord());
		if (user == null) {
			// 登陆失败
			log.info(LogConst.USERLOGIN + "ip:" + loginAddr + " uname:" + enterUserName + "账号或密码错误");
			return ShowMessage(MSG_TYPE_WARNING, "账号或密码错误!", "点击返回", "adminLogin.do", 3);
		} else {
			this.getSession().put(Const.USER_SESSION_KEY, user);
			List<Menutree> menuTree = this.authorityService.getMenuTreeBy(this.user.getAdminGroup());
			this.getSession().put(Const.USER_MENUTREE_SESSION_KEY, menuTree);
			log.info(LogConst.USERLOGIN + "ip:" + loginAddr + " uname:" + user.getUserName()
						+ "登陆成功");
			return "loginSuccess";
		}
	}

	/**
	 * 登出提交方法
	 * 
	 * @return
	 */
	public String doLogout() {
		this.getSession().remove(Const.USER_SESSION_KEY);
		this.getSession().remove(Const.USER_MENUTREE_SESSION_KEY);
		return ShowMessage(MSG_TYPE_NORMAL, "你已经成功登出系统!", "确定", "adminLogin.do", 5);
	}

	@Override
	//@Privilege(permissionName = "abc")
	public String execute() throws Exception {
		// return ShowMessage(MSG_TYPE_NORMAL, "跳转标题", 5, new MsgLinks("网易", "http://www.163.com"),
		// new MsgLinks("腾讯", "http://www.QQ.com"));
		return "page";
	}

	public Administrators getUser() {
		return user;
	}

	public void setUser(Administrators user) {
		this.user = user;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
}

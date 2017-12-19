package com.sys.volunteer.interceptor;

import java.lang.reflect.Method;
import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.permission.PermissionVerification;
import com.sys.volunteer.pojo.Permission;
import com.sys.volunteer.pojo.Users;

public class PermissionInterceptor extends AbstractInterceptor {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Resource
	PermissionVerification permissionVerification;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String name = invocation.getInvocationContext().getName();
		String namespace = invocation.getProxy().getNamespace();

		// 拦截的method
		String method = invocation.getProxy().getMethod();
		String fullmethod = namespace + method;

		Users users = (Users) invocation.getInvocationContext().getSession().get(
			Const.USER_SESSION_KEY);

		System.out.println("方法拦截器: " + name + " 方法名: " + method);

		//不处理这3种
		if (name.equals("verify") /*验证码*/
				|| name.equals("login") 
				|| method.equals("doLogout")
				|| method.equals("toMobile")
				|| name.equals("adminLogin") /*登陆界面*/
				|| method.equals("attendanceRecvice")/*考勤机*/
				|| method.equals("notifyLiandong")/*联动通知页面*/
				|| method.equals("doPayRecharge")/*联动通知页面*/
				|| name.equals("pos")/*测试http页面*/
				|| namespace.equals("/alipayCharge")/*支付宝返回页面*/
				) {
			return invocation.invoke();
		}

		if (users == null) {
			if (!(name.equals("system"))) {
				ActionContext.getContext().put("isMultipleMsg", false);
				ActionContext.getContext().put("message", "登陆超时,请重新登陆!!");
				ActionContext.getContext().put("links", "<a href=\"javascript:void(0)\""
						+" onclick=\"javascript:parent.location.href='"
						+ServletActionContext.getRequest().getContextPath()
						+"/login.do'\">点击登陆</a>");
				return "showmessage";
			}
		} else {
			fullmethod = fullmethod.replaceAll("/", "");
			Method targetMethod = invocation.getAction().getClass().getMethod(method, null);
			Privilege privilege = (Privilege) targetMethod.getAnnotation(Privilege.class);
			if (privilege != null) {
				System.out.println("拦截权限:" + privilege.permissionName() + " 方法:" + method
									+ " 连命名方法：" + fullmethod);

				Permission permission = permissionVerification.getPermission(
					users.getUsergroup().getId(), privilege.permissionName(),
					privilege.privilegeAccess());
				if (permission==null||permission.getMatchReturn() == 0) {
					// 代表没有权限
					ActionContext.getContext().put("isMultipleMsg", false);
					ActionContext.getContext().put("message", "你没有权限!");
					return "showmessage";
				}
			}
		}
		
		int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		
		if (users.getUsergroup().getId()>Const.Usergroup.STAFF && currentHour > 1 && currentHour < 6) {
			log.info("维护时段拦截!");
			
			ActionContext.getContext().put("isMultipleMsg", false);
			ActionContext.getContext().put("message", "系统维护中(每天2:00-5:00),请在其他时间操作");
			return "showmessage";
		}
		
		
		return invocation.invoke();
	}

}

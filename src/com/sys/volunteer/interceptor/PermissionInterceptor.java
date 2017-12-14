package com.sys.volunteer.interceptor;

import java.lang.reflect.Method;

import com.ages.model.Administrators;
import com.ages.model.ConnectInfo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.pojo.Users;

public class PermissionInterceptor extends AbstractInterceptor {
	//@Resource
//	PermissionVerification permissionVerification;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String name = invocation.getInvocationContext().getName();
		String namespace = invocation.getProxy().getNamespace();

		// 拦截的method
		String method = invocation.getProxy().getMethod();
		String fullmethod = namespace + method;

		Administrators users = (Administrators) invocation.getInvocationContext().getSession().get(
			Const.USER_SESSION_KEY);
		
		ConnectInfo connectInfo = (ConnectInfo) invocation.getInvocationContext().getSession().get(
				Const.SERVERID_SESSION_KEY);
		Integer serverId =null;
		if (connectInfo != null) {
			serverId = connectInfo.getId();
		}

		System.out.println("方法拦截器: " + name + " 方法名: " + method);

		//不处理这3种
		if (name.equals("verify") /*验证码*/
				|| name.equals("login") 
				|| name.equals("adminLogin") /*登陆界面*/
				|| method.equals("attendanceRecvice")/*考勤机*/) {
			return invocation.invoke();
		}

		if (users == null) {
			if (!(name.equals("system"))) {
				return "index";
			}
		} else {
			fullmethod = fullmethod.replaceAll("/", "");
			Method targetMethod = invocation.getAction().getClass().getMethod(method, null);
			Privilege privilege = (Privilege) targetMethod.getAnnotation(Privilege.class);
			if (privilege != null) {
				System.out.println("拦截权限:" + privilege.permissionName() + " 方法:" + method
									+ " 连命名方法：" + fullmethod);

				
//				Permission permission = permissionVerification.getPermission(
//					users.getUsergroup().getId(), privilege.permissionName(),
//					privilege.privilegeAccess());
//				if (permission.getMatchReturn() == 0) {
//					// 代表没有权限
//
//					return "nopermission";
//				}
			}
		}
		
		//遇到以下不检查
		if (serverId == null) {
			if (!(name.equals("frame"))
					&&!(name.equals("noserver"))
					&&!(name.equals("changeServer"))
					&&!(name.equals("logout"))) {
				return "noserver";
			}
		}
		
		return invocation.invoke();
	}

}

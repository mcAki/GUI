package com.sys.volunteer.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.volunteer.common.Const;

public class SessionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String name = invocation.getInvocationContext().getName();
		String namespace = invocation.getProxy().getNamespace();
		String method = namespace + invocation.getProxy().getMethod();
		
		//不处理这3种
//		if (name.equals("verify") /*验证码*/
//				|| name.equals("login") 
//				|| name.equals("adminLogin") /*登陆界面*/
//				|| method.equals("attendanceRecvice")/*考勤机*/) {
//			return invocation.invoke();
//		}
//		
//		if(invocation.getInvocationContext().getSession().get(Const.USER_SESSION_KEY)==null){
//			ActionContext.getContext().put("isMultipleMsg", false);
//			ActionContext.getContext().put("message", "登陆超时,请重新登陆!!");
//			ActionContext.getContext().put("links", "<a href='login.do'>点击登陆</a>");
//			return "showmessage";
//		}
		
//		Method[] methods =invocation.getAction().getClass().getDeclaredMethods();
//		for(Method method1:methods){
//			if(method.endsWith(method1.getName())){
//				Privilege privilege=(Privilege)method1.getAnnotation(Privilege.class);
//				if(privilege!=null)
//					System.out.println(privilege.permissionName());
//			}
//		}
		
		/*
		method=method.replaceAll("/", "");
		Method targetMethod=invocation.getAction().getClass().getMethod(method, null);
		Privilege privilege=(Privilege)targetMethod.getAnnotation(Privilege.class);
		if(privilege!=null)
			System.out.println(privilege.permissionName());
			
			*/
		return invocation.invoke();
	}

}

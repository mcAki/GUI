package com.sys.volunteer.interceptor;

import java.lang.reflect.Method;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.volunteer.common.Privilege;

public class SessionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String name = invocation.getInvocationContext().getName();
		String namespace = invocation.getProxy().getNamespace();
		String method = namespace + invocation.getProxy().getMethod();
//		if(invocation.getInvocationContext().getSession().get(Constants.USER_SESSION_KEY)==null){
//			return "index";
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

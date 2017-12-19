package com.sys.volunteer.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.pojo.Users;

public class TimeStatisticInterceptor extends AbstractInterceptor {

	public static final ThreadLocal<Long> businessPerf = new ThreadLocal<Long>();
	public static final ThreadLocal<Long> daoPerf = new ThreadLocal<Long>();
	private static final String START_TIME = "ST_PERF_FILTER";

	private final Log log = LogFactory.getLog(getClass());

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// WebApplicationContext wac = WebApplicationContextUtils
		// .getRequiredWebApplicationContext(ServletActionContext
		// .getServletContext());
		// wac.getBean("");
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(
			StrutsStatics.HTTP_REQUEST);
		// BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		// StringBuffer buffer = new StringBuffer();
		// String line = "";
		// while ((line = in.readLine()) != null){
		// buffer.append(line);
		// }
		// System.out.println( buffer.toString());

		request.setAttribute(START_TIME, new Long(System.currentTimeMillis()));
		String name = invocation.getInvocationContext().getName();
		String namespace = invocation.getProxy().getNamespace();
		String method = invocation.getProxy().getMethod();
		businessPerf.set(new Long(0));
		daoPerf.set(new Long(0));
		try {
			return invocation.invoke();
		} finally {
			long httpTime = System.currentTimeMillis()
							- ((Long) request.getAttribute(START_TIME)).longValue();
			long businessTime = businessPerf.get().longValue();
			long businessPercent = httpTime == 0 ? 0 : businessTime * 100 / httpTime;
			long daoTime = daoPerf.get().longValue();
			long daoPercent = httpTime == 0 ? 0 : daoTime * 100 / httpTime;
			String userNameString = "";
			log.info("httpRequest [namespace{" + namespace + "}  name:{" + name + "} method{"
						+ method + "}]execution: " + httpTime + "ms.");
			if (businessTime > 100) {
				Users users = (Users) invocation.getInvocationContext().getSession().get(
					Const.USER_SESSION_KEY);
				if (users != null) {
					userNameString = users.getUserName() + "[ID:" + users.getUserId() + "]";
				}
				if (businessTime > 1000) {
					log.info("[BAL][TIME OVER 1000MS]" + userNameString);
				} else if (businessTime > 500) {
					log.info("[BAL][TIME OVER 500MS]" + userNameString);
				} else {
					log.info("[BAL][TIME OVER 100MS]" + userNameString);
				}
			}
			log.info("Business execution: " + businessTime + "ms, " + businessPercent + "%");
			log.info("Dao execution: " + daoTime + "ms, " + daoPercent + "%");
			if (daoTime > 100) {
				Users users = (Users) invocation.getInvocationContext().getSession().get(
					Const.USER_SESSION_KEY);
				if (users != null) {
					userNameString = users.getUserName() + "[ID:" + users.getUserId() + "]";
				}
				if (daoTime > 1000) {
					log.info("[DAL][TIME OVER 1000MS]" + userNameString);
				} else if (daoTime > 500) {
					log.info("[DAL][TIME OVER 500MS]" + userNameString);
				} else {
					log.info("[DAL][TIME OVER 100MS]" + userNameString);
				}
			}			
			businessPerf.remove();
			daoPerf.remove();
		}
	}

}

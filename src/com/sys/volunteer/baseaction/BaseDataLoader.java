package com.sys.volunteer.baseaction;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ages.util.DbManager;

public class BaseDataLoader implements ServletContextListener {


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
//		WebApplicationContext wac = WebApplicationContextUtils
//				.getRequiredWebApplicationContext(event.getServletContext());

		DbManager.getInstance();
	}

	public void loadMissionData(ServletContext servletContext) {
		
	}

	public void loadBaseDatas(ServletContext servletContext) {

	}


}

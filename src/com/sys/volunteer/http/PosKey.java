package com.sys.volunteer.http;

import org.springframework.context.ApplicationContext;

import com.sys.volunteer.common.DesUtil;
import com.sys.volunteer.pojo.PosSignIn;
import com.sys.volunteer.springContext.SpringContextUtil;

public class PosKey {

	/**
	 * 记录杉德当前key
	 */
	private static String key;
	
	private static PosSignInService posSignInService;

	public static String getKey() throws Exception {
		if (key==null) {
			ApplicationContext act = SpringContextUtil.getApplicationContext();
			posSignInService = (PosSignInService)act.getBean("posSignInServiceBean");
			PosSignIn posSignIn = posSignInService.findLastRecord();
			key = DesUtil.desDecode(posSignIn.getKey());
		}
		return key;
	}

	public static void setKey(String key) {
		PosKey.key = key;
	}
}

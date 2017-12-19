package com.sys.volunteer.useraccountdealdetail;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.usercharge.UserChargeService;

public class UseraccountDealDetailTimer {
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	UserChargeService userChargeService;

	/**
	 * 从sp那里获得回复短信
	 */
	public void markUseraccountDealDetail() {
		userChargeService.addCheckOut();
	}



}

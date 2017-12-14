package com.sys.volunteer.gmmanage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ages.model.ConnectInfo;
import com.ages.util.DbManager;

public class GmTimer {
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	GMManageService gmManageService;

	/**
	 * 从sp那里获得回复短信
	 * @throws Exception 
	 */
	public void mark() {
		List<ConnectInfo> connectInfos = DbManager.getInstance().getConnectInfos();
		for (ConnectInfo connectInfo : connectInfos) {
			if (connectInfo.getIsUsed()==1) {
				gmManageService.checkOut(connectInfo.getId());
			}
		}
	}



}

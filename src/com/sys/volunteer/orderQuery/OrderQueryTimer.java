package com.sys.volunteer.orderQuery;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.liandong.LiandongService;
import com.sys.volunteer.zhongrong.ZhongrongService;

public class OrderQueryTimer {
	protected Log log = LogFactory.getLog(this.getClass());
	@Resource
	OrderQueryResponseNewService orderQueryResponseNewService;
	@Resource
	LiandongService liandongService;
	@Resource
	ZhongrongService zhongrongService;
	
	public void setOrderQueryResponseNewService(
			OrderQueryResponseNewService orderQueryResponseNewService) {
		this.orderQueryResponseNewService = orderQueryResponseNewService;
	}

	/**
	 * 从sp那里获得回复短信
	 */
	public void queryOrder() throws Exception {
//		System.out.println("==============================================");
//		orderQueryResponseNewService.refreshOrderState();
//		liandongService.updateDealingOrders();
//		zhongrongService.updateDealingOrders();
	}



}

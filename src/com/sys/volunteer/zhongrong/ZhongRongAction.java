package com.sys.volunteer.zhongrong;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.ZhongRongUtil;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ZhongRongAction extends BaseAction {
	
	private String returnUrl;

	/**
	 * 查询余额
	 * @return
	 * @throws Exception
	 */
	public String searchBalance() throws Exception{
		returnUrl = ZhongRongUtil.searchBalance();
		return SUCCESS;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
}

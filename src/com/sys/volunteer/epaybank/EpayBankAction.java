package com.sys.volunteer.epaybank;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.EpayBankUtil;
import com.sys.volunteer.pojo.EpayBankpayRequest;
import com.sys.volunteer.pojo.EpayBankpayResponse;
import com.sys.volunteer.pojo.Users;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class EpayBankAction extends BaseAction{

	@Resource
	EpayBankService epayBankService;
	
	private String payCardNo;
	private Double amount;
	private String summary;
	
	public String charge() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * epaybank支付响应流程
	 * @return
	 * @throws Exception
	 */
	public String authCharge() throws Exception {
		log.info("进入epaybank支付!");
		Users user = getSessionUser();
		EpayBankpayRequest request = epayBankService.initEpayBankpayRequest(payCardNo, amount, user, summary);
		EpayBankpayResponse response = epayBankService.initEpayBankpayResponse(request);
		String reXml = EpayBankUtil.authCharge(request.getXmlText());
		response = EpayBankUtil.getEpayBankpayResponse(response,reXml);
		int result = epayBankService.refreshEpayBank(response);
		if (result == 1) {
			//成功
			return ShowMessage(MSG_TYPE_NORMAL, "支付成功", "", "", 0);
		}
		return ShowMessage(MSG_TYPE_NORMAL, "支付失败,"+response.getRespMsg(), "", "", 0);
	}


	public String getPayCardNo() {
		return payCardNo;
	}


	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}
}

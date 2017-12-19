package com.sys.volunteer.customer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.ResponseUtils;
import com.sys.volunteer.pojo.CustomerFeedback;
import com.sys.volunteer.pojo.Users;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
public class CustomerFeedbackAct extends BaseAction{

	
	private CustomerFeedback customerFeedback;
	private int cfId;

	@Resource
	public CustomerFeedbackService customerFeedbackService;
	
	public String saveCfbPage(){
		return "saveCfbPage";
	}
	
	public String savecfb(){
		Users users = (Users)getSession().get(Const.USER_SESSION_KEY);
		customerFeedback.setUserName(users.getUserName());
		customerFeedback.setUserPhone(users.getMobile());
		customerFeedbackService.addcfb(customerFeedback);
		if(customerFeedback.getCfId()>0){
			ResponseUtils.renderText(this.getHttpServletResponse(), "success");
		}else{
			ResponseUtils.renderText(this.getHttpServletResponse(), "error");
		}
		return null;
	}
	
	public String findAll(){
		List<CustomerFeedback> list = new ArrayList<CustomerFeedback>();
		list = customerFeedbackService.findAllcfb();
		this.getSession().put("customerFeedbackList", list);
		return SUCCESS;
	}
	
	public String findOneById(){
		CustomerFeedback cfb = customerFeedbackService.findById(cfId);
		this.getSession().put("customerFeedback", cfb);
		return SUCCESS;
	}
	
	public String delCfb(){
		customerFeedbackService.delcfbById(cfId);
		return SUCCESS;
	}
	
	
	public CustomerFeedback getCustomerFeedback() {
		return customerFeedback;
	}
	
	public void setCustomerFeedback(CustomerFeedback customerFeedback) {
		this.customerFeedback = customerFeedback;
	}
	public int getCfId() {
		return cfId;
	}

	public void setCfId(int cfId) {
		this.cfId = cfId;
	}
}

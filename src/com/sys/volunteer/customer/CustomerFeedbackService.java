package com.sys.volunteer.customer;

import java.util.List;

import com.sys.volunteer.pojo.CustomerFeedback;

public interface CustomerFeedbackService{

	public CustomerFeedback addcfb(CustomerFeedback customerFeedback);
	
	public List<CustomerFeedback> findAllcfb();
	
	public CustomerFeedback findById(int id);
	
	public CustomerFeedback updatecfb(CustomerFeedback customerFeedback);
	
	public CustomerFeedback delcfbById(int id);
}

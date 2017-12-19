package com.sys.volunteer.useraccountdealdetail;

import java.util.Date;
import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;

public interface UseraccountDealDetailService extends IDao {

	public Useraccountdealdetail findUseraccountDealDetailByOrderId(String orderId);
	
	public double sumAllExDateCount(int logFor,Date starDate,Date endDate);
	
	public List<Useraccountdealdetail> selSupplyAllExDateCountList(int logFor,Date starDate,Date endDate);
	public List<Useraccountdealdetail> selUserAllExDateCountList(int logFor,Date starDate,Date endDate);
	
//	public Map<String,Double> selSupplyAllExDateCountMap(int logFor,String starDate,String endDate);
//	public Map<String,Double> selUserAllExDateCountMap(int logFor,String starDate,String endDate);

	public void addUseraccountdealdetail(UserCharge userCharge);
}

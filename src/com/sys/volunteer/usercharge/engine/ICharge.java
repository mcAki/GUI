package com.sys.volunteer.usercharge.engine;

import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;

public interface ICharge {

	/**
	 * 增加UserCharge
	 */
	public void addCharge();
	
	public UserCharge getUserCharge();

	public void setUserCharge(UserCharge userCharge);

	public Mainorder getMainorder();

	public void setMainorder(Mainorder mainorder);

	public Double getDealMoney();

	public void setDealMoney(Double dealMoney);

	public Users getRecodeUsers();

	public void setRecodeUsers(Users recodeUsers);

	public String getRecodeOperator();

	public void setRecodeOperator(String recodeOperator);

	public int getFlag();

	public void setFlag(int flag);

	public Users getUser();

	public void setUser(Users user);

	public Supply getSupply();

	public void setSupply(Supply supply);
}

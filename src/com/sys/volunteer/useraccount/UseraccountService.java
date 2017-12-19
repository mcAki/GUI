package com.sys.volunteer.useraccount;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;

public interface UseraccountService extends IDao {

	public Useraccount addUseraccount();
	
	/**
	 * 更新账户余额,佣金
	 * @param useraccount
	 * @param deposit
	 */
	public void refreshAccountes(Mainorder mainorder,Users users,Users recodeUsers,String recodeOperator,UserCharge userCharge,Double deposit,int flag);
	
	public Useraccount findUseraccountByUseraccountId(Users users);
	
	public Integer validatePurchase(Users user,Double purchase);

	public void commitAccount(Users users);
	
}

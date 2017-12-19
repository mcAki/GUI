package com.sys.volunteer.usercharge;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;

public interface UserChargeService extends IDao {

	public UserCharge addUserCharge(UserCharge userCharge, Mainorder mainorder,
			Double dealMoney, Users user, Users recodeUsers, String recodeOperator, int flag);

	public UserCharge addUserChargeForSupply(UserCharge userCharge, Mainorder mainorder,
			Double dealMoney, Supply supply, int flag);

	public double sumBalance(String userId);

	public double sumBalance(int supplyId);

	public double sumCommission(String userId);

	public void addCheckOut();
	
}

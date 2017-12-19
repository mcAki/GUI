package com.sys.volunteer.balance;

import com.sys.volunteer.dao.IDao;

public interface BalanceService extends IDao {

	public double addBalance(String userId,Double dealMoney,int flag);
	
	public double addBalanceForSupply(int supplyId,Double dealMoney,int flag);
	
	
}

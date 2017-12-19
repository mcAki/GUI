package com.sys.volunteer.supply;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.usage.ISupply;

public interface SupplyService extends IDao {

	public Supply findSupplyById(int supplyId);
	
	public Supply addSupply(Supply supply);
	
	public void updateAvgScore(ISupply supply,Integer score);
	
	public void purchaseBalance(Mainorder mainorder);
	
	public void depositBalance(Supply supply,Double money,UserCharge userCharge);
	
	public void reverseBalance(Mainorder mainorder);
	
	public List<Supply> getSupplyList();
	
	public void saveSupply(ISupply supply);
	
	public void updateSupply(ISupply supply);

	public void drawBalance(Supply supply, Double money,
			UserCharge userCharge);

	public void cancelBalance(Mainorder mainorder,Supply supply, Double money,
			UserCharge userCharge);

	public void refreshSupplyAccounts(Mainorder mainorder, Supply supply,
			Users recodeUsers, String recodeOperator, UserCharge userCharge,
			Double deposit, int flag);
}

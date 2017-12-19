package com.sys.volunteer.useraccount;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.balance.BalanceService;
import com.sys.volunteer.commission.CommissionService;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.usercharge.UserChargeService;
import com.sys.volunteer.users.UserService;
@Service
@Transactional
public class UseraccountServiceBean extends CommonDao implements
		UseraccountService {

	@Resource
	UseraccountDealDetailService useraccountDealDetailService;
	@Resource
	OperationLogService operationLogService;
	@Resource
	UserService userService;
	@Resource
	UserChargeService userChargeService;
	@Resource
	BalanceService balanceService;
	@Resource
	CommissionService commissionService;
	
	@Override
	public Useraccount addUseraccount() {
		Useraccount useraccount=new Useraccount();
		useraccount.setBalance(new Double(0));
		useraccount.setState(1);
		useraccount.setCreditLimit(new Double(0));
		useraccount.setFreezeMoney(new Double(0));
		useraccount.setCommission(new Double(0));
		this.save(useraccount);
		return useraccount;
	}

	@Override
	//2012.10.23
	public void refreshAccountes(Mainorder mainorder, Users users, Users recodeUsers,
			String recodeOperator,UserCharge userCharge,
			Double deposit, int flag) {
		//增加账户交易明细
		userCharge = userChargeService.addUserCharge(userCharge,mainorder, deposit, users,recodeUsers,recodeOperator,flag);
		commitAccount(users, userCharge);
	}
	

	@Override
	public Useraccount findUseraccountByUseraccountId(Users users) {
		Useraccount useraccount=(Useraccount)this.findById(Useraccount.class, users.getUseraccount().getUseraccountId());
		return useraccount;
	}

	@Override
	public Integer validatePurchase(Users users,Double purchase){
		Useraccount useraccount=this.findUseraccountByUseraccountId(users);
		if (null==useraccount) {
			//返回1表示空账户,返回0表示充值成功,2表示充值失败
			return 1;
		}else{
			if (useraccount.getBalance()>=purchase) {
				return 0;
			}else {
				return 2;
			}
		}
	}
	
	@Override
	public void commitAccount(Users users){
		Useraccount useraccount=this.findUseraccountByUseraccountId(users);
		//结算commission
		double commission = userChargeService.sumCommission(users.getUserId());
		//结算balance
		double balance = userChargeService.sumBalance(users.getUserId());
		useraccount.setBalance(balance);
		useraccount.setCommission(commission);
		this.update(useraccount);
	}
	
	private void commitAccount(Users users, UserCharge userCharge) {
		Useraccount useraccount=this.findUseraccountByUseraccountId(users);
		//结算commission
		double commission = userCharge.getCurrentCommission();
		//结算balance
		double balance = userCharge.getCurrentBalance();
		useraccount.setBalance(balance);
		useraccount.setCommission(commission);
		this.update(useraccount);
	}

}

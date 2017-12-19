package com.sys.volunteer.usercharge.engine.charge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.usercharge.engine.BaseCharge;

public class Charge4Supply extends BaseCharge {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	public Charge4Supply(UserCharge userCharge, Mainorder mainorder,
			Double dealMoney, Supply supply, Users recodeUsers,
			String recodeOperator, int flag) {
		super(userCharge, mainorder, dealMoney, supply, recodeUsers,
				recodeOperator, flag);
	}

	@Override
	public void addCharge() {
		SupplyKernel supplyKernel = SupplyKernel.getInstance();
		supplyKernel.refreshAccounts(getMainorder(), getSupply(), getRecodeUsers(), getRecodeOperator(), getUserCharge(), getDealMoney(), getFlag());
		if (getMainorder() != null) {
			logger.info("添加"+getMainorder().getMainOrderId()+"了一条额度记录,"+getDealMoney()+"元,标记为"+getFlag());
		}else {
			logger.info("添加了一条额度记录,"+getDealMoney()+"元,标记为"+getFlag());
		}
	}

}

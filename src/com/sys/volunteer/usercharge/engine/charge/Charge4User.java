package com.sys.volunteer.usercharge.engine.charge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeService;
import com.sys.volunteer.usercharge.engine.BaseCharge;

public class Charge4User extends BaseCharge {
	
	protected Log logger = LogFactory.getLog(this.getClass());

	public Charge4User(UserCharge userCharge, Mainorder mainorder,
			Double dealMoney, Users users, Users recodeUsers,
			String recodeOperator, int flag) {
		super(userCharge, mainorder, dealMoney, users, recodeUsers,
				recodeOperator, flag);
	}

	private UseraccountService useraccountService;
	
	@Override
	public void addCharge() {
		ApplicationContext act = SpringContextUtil
			.getApplicationContext();
		useraccountService = (UseraccountService) act.getBean("useraccountServiceBean");
		useraccountService.refreshAccountes(getMainorder(), getUser(), getRecodeUsers(), getRecodeOperator(), getUserCharge(), getDealMoney(), getFlag());
		if (getMainorder() != null) {
			logger.info("添加"+getMainorder().getMainOrderId()+"了一条额度记录,"+getDealMoney()+"元,标记为"+getFlag());
		}else {
			logger.info("添加了一条额度记录,"+getDealMoney()+"元,标记为"+getFlag());
		}
	}

}

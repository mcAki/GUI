package com.sys.volunteer.supply.usage;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;

import com.sys.volunteer.area.AreaCodeService;
import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.FeiyueUtil;
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.feiyue.FeiyueService;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.liandong.LiandongService;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.AreaCode;
import com.sys.volunteer.pojo.Feiyue;
import com.sys.volunteer.pojo.Liandong;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

public class FeiyueSupply extends Supply implements ISupply {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	private FeiyueService feiyueService;
	private AreaCodeService areaCodeService;
	private OrderService orderService;
	private UserService userService;
	private CardLibService cardLibService;
	private OperationLogService operationLogService;
	private SupplyService supplyService;
	private UseraccountService useraccountService;
	private IMainorderDao mainorderDao;

	@Override
	public int recharge(Mainorder mainorder,String mobile,SupplyInterface supplyInterface) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		feiyueService=(FeiyueService)act.getBean("feiyueServiceBean");
		Feiyue feiyue = feiyueService.findFeiyueByOrderId(mainorder.getMainOrderId());
		if (feiyue != null) {
			logger.info("该订单已经进行了一次充值,请检查线程重复下单....");
			return Const.OperationLogResult.FAILED;
		}
		feiyue = feiyueService.initFeiyue(mainorder, supplyInterface);
		logger.info("飞跃下了一条订单,订单号:"+feiyue.getOrderId()+",手机号码是:"+feiyue.getPhone()+",金额:"+feiyue.getMoney());
		String url=FeiyueUtil.rechargeOrder(feiyue);
		if (url==null) {
			return Const.OperationLogResult.FAILED;
		}
		// 这里只能调用一次
		//String xmlText = LiandongUtil.getRotmePage(url, "utf-8");
		//System.out.println(xmlText);
		Feiyue reFy = FeiyueUtil.getFeiyue(url,1);
		if (reFy==null) {
			logger.error("飞跃充值接口返回了空对象!");
			return Const.OperationLogResult.FAILED;
		}
		//把远程xml转换为字符串保存
		//liandong.setXmlText(xmlText);
		feiyue = feiyueService.updateFeiyue(mainorder,reFy);
		logger.info("飞跃返回订单信息,流水号是:"+feiyue.getStreamid());
		return Const.OperationLogResult.PROCESSING;
	}

	@Override
	public int reverse(Mainorder mainorder) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		feiyueService=(FeiyueService)act.getBean("feiyueServiceBean");
		Feiyue feiyue = feiyueService.findFeiyueByOrderId(mainorder.getMainOrderId());
		int result = -1;
		//如果联动冲正时间大于订单当天最大时间,则需要联系客服
//		if (DateUtil.getMaxDate(mainorder.getCreateTime()).getTime()<System.currentTimeMillis()) {
//			return Const.reverseResult.CONTACT_US;
//		}
		feiyueService.reverseOrder(feiyue);
		return result;
	}

	@Override
	public int refreshState(Mainorder mainorder) throws Exception{
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		feiyueService=(FeiyueService)act.getBean("feiyueServiceBean");
		return feiyueService.updateDealingOrders(mainorder);
	}
	
	@Override
	public void cancelOrder(Mainorder mainorder) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		feiyueService=(FeiyueService)act.getBean("feiyueServiceBean");
		DetachedCriteria dec = DetachedCriteria.forClass(Feiyue.class);
		dec.add(Restrictions.eq("orderId", mainorder.getMainOrderId()));
		//dec.add(Restrictions.eq("isDeal", 0));
		List<Feiyue> list = feiyueService.findByDetachedCriteria(dec);
		Feiyue feiyue = null;
		if (list.size()>0) {
			feiyue = list.get(0);
		}
		if (feiyue != null) {
			//feiyue.setIsDeal(1);
			feiyueService.update(feiyue);
		}
	}
}

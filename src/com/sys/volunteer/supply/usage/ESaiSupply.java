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
import com.sys.volunteer.common.ESaiUtil;
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.esai.ESaiService;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.liandong.LiandongService;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.ESaiCharge;
import com.sys.volunteer.pojo.Liandong;
import com.sys.volunteer.pojo.LiandongQQ;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

public class ESaiSupply extends Supply implements ISupply {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	private ESaiService eSaiService;
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
		eSaiService=(ESaiService)act.getBean("ESaiServiceBean");
		ESaiCharge eSaiCharge = eSaiService.findESaiChargeByOrderId(mainorder.getMainOrderId());
		if (eSaiCharge != null) {
			logger.info("该订单已经进行了一次充值,请检查线程重复下单....");
			return Const.OperationLogResult.FAILED;
		}
		eSaiCharge = eSaiService.initESaiCharge(mainorder);
		logger.info("易赛下了一条订单,订单号:"+eSaiCharge.getOutOrderNumber()+",号码是:"+eSaiCharge.getPhoneNumber());
		String url=ESaiUtil.rechargeOrder(eSaiCharge);
		if (url==null) {
			return Const.OperationLogResult.FAILED;
		}
		// 这里只能调用一次
		//String xmlText = LiandongUtil.getRotmePage(url, "utf-8");
		//System.out.println(xmlText);
		ESaiCharge reESaiCharge = ESaiUtil.getESaiCharge(url);
		if (reESaiCharge==null) {
			logger.error("易赛充值接口返回了空对象!");
			return Const.OperationLogResult.FAILED;
		}
		//把远程xml转换为字符串保存
		//liandong.setXmlText(xmlText);
		eSaiCharge = eSaiService.updateESaiCharge(mainorder, reESaiCharge);
		//创建易赛查询记录
		eSaiService.initESaiQuery(eSaiCharge);
		logger.info("易赛返回订单信息,流水号是:"+eSaiCharge.getOutOrderNumber());
		if (eSaiCharge.getResult()!=null&&!eSaiCharge.getResult().equals("success")) {
			return Const.OperationLogResult.PROCESSING;
		}else {
			return Const.OperationLogResult.FAILED;
		}
	}

	@Override
	public int refreshState(Mainorder mainorder) throws Exception{
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		eSaiService=(ESaiService)act.getBean("ESaiServiceBean");
		return eSaiService.updateDealingOrders(mainorder);
	}
	
}

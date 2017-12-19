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
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.liandong.LiandongService;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.AreaCode;
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

public class LiandongSupply extends Supply implements ISupply {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	private LiandongService liandongService;
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
		liandongService=(LiandongService)act.getBean("liandongServiceBean");
		Liandong liandong = liandongService.findLiandongByOrderId(mainorder.getMainOrderId());
		if (liandong != null) {
			logger.info("该订单已经进行了一次充值,请检查线程重复下单....");
			return Const.OperationLogResult.FAILED;
		}
		liandong = liandongService.initLiandong(mainorder, mobile, supplyInterface);
		logger.info("联动下了一条订单,订单号:"+liandong.getOrderID()+",手机号码是:"+liandong.getMobile()+",金额:"+liandong.getMoney());
		String url=LiandongUtil.rechargeOrder(mobile, mainorder, supplyInterface);
		if (url==null) {
			return Const.OperationLogResult.FAILED;
		}
		// 这里只能调用一次
		//String xmlText = LiandongUtil.getRotmePage(url, "utf-8");
		//System.out.println(xmlText);
		Liandong reLd = LiandongUtil.getLiandong(url,1);
		if (reLd==null) {
			logger.error("联动充值接口返回了空对象!");
			return Const.OperationLogResult.FAILED;
		}
		//把远程xml转换为字符串保存
		//liandong.setXmlText(xmlText);
		liandong = liandongService.updateLiandong(mainorder,reLd);
		logger.info("联动返回订单信息,流水号是:"+liandong.getCodeSN());
		if (liandong.getStatus()!=null&&!liandong.getStatus().equals("1")) {
			return Const.OperationLogResult.FAILED;
		}
		if (liandong.getStatus().equals("0")) {
			return Const.OperationLogResult.PROCESSING;
		}
		if (liandong.getErrorID()!=null&&liandong.getStatus()==null) {
			return Const.OperationLogResult.FAILED;
		}
		return Const.OperationLogResult.SUCCESS;
	}

	@Override
	public int reverse(Mainorder mainorder) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		liandongService=(LiandongService)act.getBean("liandongServiceBean");
		areaCodeService = (AreaCodeService) act.getBean("areaCodeServiceBean");
		orderService = (OrderService) act.getBean("orderServiceBean");
		userService = (UserService) act.getBean("userServiceBean");
		cardLibService = (CardLibService) act.getBean("cardLibServiceBean");
		operationLogService = (OperationLogService) act.getBean("operationLogServiceBean");
		supplyService = (SupplyService) act.getBean("supplyServiceBean");
		useraccountService = (UseraccountService) act.getBean("useraccountServiceBean");
		mainorderDao = (IMainorderDao) act.getBean("mainorderDao");
		Liandong liandong = liandongService.findLiandongByOrderId(mainorder.getMainOrderId());
		int result = -1;
		//如果联动冲正时间大于订单当天最大时间,则需要联系客服
//		if (DateUtil.getMaxDate(mainorder.getCreateTime()).getTime()<System.currentTimeMillis()) {
//			return Const.reverseResult.CONTACT_US;
//		}
		Users user = userService.findUserById(mainorder.getUsers().getUserId());
		switch (liandongService.reverseOrder(liandong)) {
		//成功
		case 1:
		//已冲正
		case 2:
			result = Const.reverseResult.SUCCESS;
			orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_SUCCESS);
			//冲正次数-1
			user.setReversalCount(user.getReversalCount()-1);
			userService.update(user);
			if (mainorder.getMoneyBack().equals(1)) {
				//转换冲正的卡密状态
				if (mainorder.getCardLibIds()!=null) {
					cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
				}
				operationLogService.addOperationLog(user, mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.SUCCESS,null);
				//商户自动返还额度
//				useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.REVERSAL);
				//供货商自动返还额度
				Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
//				SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
				ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.REVERSAL);
				ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.REVERSAL);
				UserChargeEngine.getInstance().addLast(iCharge);
			    UserChargeEngine.getInstance().addLast(iCharge2);
				//订单回水标记0
				orderService.updateMoneyBack(mainorder, 0);
			}
			break;
		default:
			result = Const.reverseResult.FAILED;
			orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_FAILED);
			operationLogService.addOperationLog(user, mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.FAILED,null);
			break;
		}
		
		return result;
	}

	@Override
	public int refreshState(Mainorder mainorder) throws Exception{
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		liandongService=(LiandongService)act.getBean("liandongServiceBean");
		return liandongService.updateDealingOrders(mainorder);
	}
	
	@Override
	public void cancelOrder(Mainorder mainorder) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		liandongService=(LiandongService)act.getBean("liandongServiceBean");
		DetachedCriteria dec = DetachedCriteria.forClass(Liandong.class);
		dec.add(Restrictions.eq("orderID", mainorder.getMainOrderId()));
		dec.add(Restrictions.eq("isDeal", 0));
		List<Liandong> list = liandongService.findByDetachedCriteria(dec);
		Liandong liandong = null;
		if (list.size()>0) {
			liandong = list.get(0);
		}
		if (liandong != null) {
			liandong.setIsDeal(1);
			liandongService.update(liandong);
		}
	}
}

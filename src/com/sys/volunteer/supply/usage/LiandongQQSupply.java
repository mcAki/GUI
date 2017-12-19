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
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.liandong.LiandongService;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
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

public class LiandongQQSupply extends Supply implements ISupply {

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
		LiandongQQ liandongQQ = liandongService.findLiandongQQByOrderId(mainorder.getMainOrderId());
		if (liandongQQ != null) {
			logger.info("该订单已经进行了一次充值,请检查线程重复下单....");
			return Const.OperationLogResult.FAILED;
		}
		liandongQQ = liandongService.initLiandongQQ(mainorder, mobile, supplyInterface);
		logger.info("联动QQ下了一条订单,订单号:"+liandongQQ.getOrderID()+",QQ号码是:"+liandongQQ.getQQnum()+",数量:"+liandongQQ.getBuyCount());
		String url=LiandongUtil.rechargeQQOrder(liandongQQ);
		if (url==null) {
			return Const.OperationLogResult.FAILED;
		}
		// 这里只能调用一次
		//String xmlText = LiandongUtil.getRotmePage(url, "utf-8");
		//System.out.println(xmlText);
		LiandongQQ reLdQQ = LiandongUtil.getLiandongQQ(url,1);
		if (reLdQQ==null) {
			logger.error("联动QQ充值接口返回了空对象!");
			return Const.OperationLogResult.FAILED;
		}
		//把远程xml转换为字符串保存
		//liandong.setXmlText(xmlText);
		liandongQQ = liandongService.updateLiandongQQ(mainorder,reLdQQ);
		logger.info("联动QQ返回订单信息,流水号是:"+liandongQQ.getDemo());
		if (liandongQQ.getStatus()!=null&&!liandongQQ.getStatus().equals("1")) {
			return Const.OperationLogResult.FAILED;
		}
		if (liandongQQ.getStatus().equals("0")) {
			return Const.OperationLogResult.PROCESSING;
		}
		return Const.OperationLogResult.SUCCESS;
	}

	@Override
	public int refreshState(Mainorder mainorder) throws Exception{
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		liandongService=(LiandongService)act.getBean("liandongServiceBean");
		return liandongService.updateQQDealingOrders(mainorder);
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

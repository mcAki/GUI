package com.sys.volunteer.liandong;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.order.RefreshBatchOrderEngine;
import com.sys.volunteer.order.engine.charge.BatchOrderRefresh;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.Liandong;
import com.sys.volunteer.pojo.LiandongPay;
import com.sys.volunteer.pojo.LiandongQQ;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

@Service
@Transactional
public class LiandongServiceBean extends CommonDao implements LiandongService {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	OrderService orderService;
	@Resource
	UserService userService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	UseraccountDealDetailService useraccountDealDetailService;
	@Resource
	OperationLogService operationLogService;
	@Resource
	SupplyService supplyService;
	@Resource
	CardLibService cardLibService;
	@Resource
	IMainorderDao mainorderDao;
	
	@Override
	public int updateDealingOrders(Mainorder mainorder) throws Exception {
		Liandong liandong = findLiandongByOrderId(mainorder.getMainOrderId());
		String url=LiandongUtil.searchOrder(liandong);
		if (url==null) {
			logger.error(mainorder.getMainOrderId()+"联动url为空!");
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			return Const.OperationLogResult.NONE_PROCESS;
		}
		//String queryXmlText = LiandongUtil.getRotmePage(url, "utf-8");
		//System.out.println(queryXmlText);
		Liandong ld=LiandongUtil.getLiandong(url,2);
		//liandong.setQueryXmlText(queryXmlText);
		if (ld==null) {
			logger.error(mainorder.getMainOrderId()+"联动返回空");
//			return Const.OperationLogResult.NONE_PROCESS;
			//联动没有返回数据,继续查询
			liandong.setIsDeal(1);
			liandong.setStatus("0");
			orderService.updateOrderRefreshTime(mainorder, 300*1000);
			//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
//			orderService.updateOrderRespTime(mainorder);
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
		}else {
			if (ld.getErrorID()!=null && !ld.getErrorID().equals(2006) && ld.getStatus() == null) {
				//查询有错误
				liandong.setIsDeal(1);
				liandong.setStatus("2");
				liandong.setErrorID(ld.getErrorID());
				liandong.setErrorMsg(ld.getErrorMsg());
				logger.info(mainorder.getMainOrderId()+"查询联动订单,订单状态是ERROR:"+liandong.getErrorMsg());
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			}else if (ld.getErrorID()!=null && ld.getErrorID().equals(2006) && ld.getStatus() == null) {
				liandong.setErrorID(ld.getErrorID());
				liandong.setErrorMsg(ld.getErrorMsg());
				liandong.setQueryXmlText(ld.getQueryXmlText());
				logger.info(mainorder.getMainOrderId()+"查询联动订单,订单状态是ERROR:"+liandong.getErrorMsg());
				this.update(liandong);
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
				return Const.OperationLogResult.NONE_PROCESS;
			}
			if (ld.getOrderID()!=null&&ld.getOrderID().equals(liandong.getOrderID())&&!ld.getStatus().equals("0")) {
				liandong.setIsDeal(1);
				liandong.setStatus(ld.getStatus());
			}
			liandong.setQueryXmlText(ld.getQueryXmlText());
		}
		logger.info(mainorder.getMainOrderId()+"查询联动订单,订单状态是:"+liandong.getStatus());
		this.update(liandong);
		return this.updateOrderState(liandong);
	}

	
	public int updateOrderState(Liandong liandong) throws Exception{
		int result = Const.OperationLogResult.NONE_PROCESS;
		Mainorder mainorder=orderService.findOrderById(liandong.getOrderID());
		//Users user=userService.findUserByMobile(orderQueryResponseNew.getUserMobileNum());
		Users user=userService.findUserById(mainorder.getUsers().getUserId());
		if (liandong.getStatus().equals("1")) {
			//订单状态为成功
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
			operationLogService.addOperationLog(user,mainorder, liandong.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
			// 更新供货商对应商品错误次数
//			SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//			SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, 0);
			result = Const.OperationLogResult.SUCCESS; 
			//如果是批量单,刷新总单
			if (mainorder.getIsBatch()==1) {
				orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
				BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
				//orderService.updateBatchOrder(batchOrder);
				com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
				RefreshBatchOrderEngine.getInstance().addLast(iCharge);
			}
		}else if (liandong.getStatus().equals("0")) {
			//处理中,继续等待
			//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
//			mainorder.setQueryTimes(mainorder.getQueryTimes()+1);
//			orderService.update(mainorder);
			//刷新时间5秒后
//			mainorderDao.updateRefreshTime(System.currentTimeMillis()+5000,mainorder.getMainOrderId());
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			result = Const.OperationLogResult.PROCESSING; 
		}else{
			//订单状态为失败
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_FAILED);
			//销售商品返回未销售状态
			if (mainorder.getCardLibIds()!=null) {
				cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
			}
			if (mainorder.getMoneyBack().equals(1)) {
				operationLogService.addOperationLog(user,mainorder, liandong.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.FAILED,null);
				//商户自动返还额度
//				useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
				//供货商自动返还额度
				System.out.println("++++++++++++++++++++++++++++++"+mainorder.getSupply().getId());
				Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
				System.out.println("=================================="+supply);
				//转换冲正的卡密状态
				//cardLibService.reverseCardLib(mainorder.getCardLibIds());
//				SupplyKernel.getInstance().cancelBalance(mainorder,supply,mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
				ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
				ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
				UserChargeEngine.getInstance().addLast(iCharge);
			    UserChargeEngine.getInstance().addLast(iCharge2);
				//订单回水标记0
				orderService.updateMoneyBack(mainorder, 0);
			}
			
			// 更新供货商对应商品错误次数
//			SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//			SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, supplyInterface.getFailedCount()+1);
//			//连续出现5次错误就禁用
//			if (supplyInterface.getFailedCount()>5 && supplyInterface.getFailedCount()<10) {
//				//SupplyKernel.getInstance().updateSupplyInterfaceState(String.valueOf(supplyInterface.getId()), 0);
//				String[] mobiles = new String[]{"13560192965"};
//				String content = "你好,"+supplyInterface.getSupplyName()+supplyInterface.getGoodsName()+"已经连续5次返回失败,请登陆网站查看!【妙明在线】";
//				SDKClientUtil.SendSMS(mobiles, content, "", 0);
//			}
			result = Const.OperationLogResult.FAILED;
			//如果是批量单,刷新总单
			if (mainorder.getIsBatch()==1) {
				orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
				BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
				//orderService.updateBatchOrder(batchOrder);
				com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
				RefreshBatchOrderEngine.getInstance().addLast(iCharge);
			}
		}
		return result;
//		SupplyKernel.getInstance().purchaseBalance(mainorder);
//		useraccountService.purchaseAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
	}
	
	@Override
	public int updateQQDealingOrders(Mainorder mainorder) throws Exception {
		LiandongQQ liandongQQ = findLiandongQQByOrderId(mainorder.getMainOrderId());
		String url=LiandongUtil.searchQQOrder(liandongQQ);
		if (url==null) {
			logger.error(mainorder.getMainOrderId()+"联动QQ url为空!");
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			return Const.OperationLogResult.NONE_PROCESS;
		}
		//String queryXmlText = LiandongUtil.getRotmePage(url, "utf-8");
		//System.out.println(queryXmlText);
		LiandongQQ ldQQ=LiandongUtil.getLiandongQQ(url,2);
		//liandong.setQueryXmlText(queryXmlText);
		if (ldQQ==null) {
			logger.error(mainorder.getMainOrderId()+"联动QQ返回空");
//			return Const.OperationLogResult.NONE_PROCESS;
			//联动没有返回数据,继续查询
			liandongQQ.setIsDeal(1);
			liandongQQ.setStatus("-1");
			orderService.updateOrderRefreshTime(mainorder, 300*1000);
			//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
//			orderService.updateOrderRespTime(mainorder);
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
		}else {
			if (ldQQ.getStatus() == null) {
				//查询有错误
				liandongQQ.setIsDeal(1);
				liandongQQ.setStatus("2");
				liandongQQ.setDemo(ldQQ.getDemo());
				logger.info(mainorder.getMainOrderId()+"查询联动QQ订单,订单状态是ERROR:"+liandongQQ.getDemo());
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			}
			if (ldQQ.getOrderID()!=null&&ldQQ.getOrderID().equals(liandongQQ.getOrderID())&&!ldQQ.getStatus().equals("0")) {
				liandongQQ.setIsDeal(1);
				liandongQQ.setStatus(ldQQ.getStatus());
			}
			liandongQQ.setQueryXmlText(ldQQ.getQueryXmlText());
		}
		logger.info(mainorder.getMainOrderId()+"查询联动QQ订单,订单状态是:"+liandongQQ.getStatus());
		this.update(liandongQQ);
		return this.updateQQOrderState(liandongQQ);
	}

	
	public int updateQQOrderState(LiandongQQ liandongQQ) throws Exception{
		int result = Const.OperationLogResult.NONE_PROCESS;
		Mainorder mainorder=orderService.findOrderById(liandongQQ.getOrderID());
		//Users user=userService.findUserByMobile(orderQueryResponseNew.getUserMobileNum());
		Users user=userService.findUserById(mainorder.getUsers().getUserId());
		if (liandongQQ.getStatus().equals("0")) {
			//订单状态为成功
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
			operationLogService.addOperationLog(user,mainorder, liandongQQ.getQQnum(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
			// 更新供货商对应商品错误次数
//			SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//			SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, 0);
			result = Const.OperationLogResult.SUCCESS; 
			//如果是批量单,刷新总单
			if (mainorder.getIsBatch()==1) {
				orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
				BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
				//orderService.updateBatchOrder(batchOrder);
				com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
				RefreshBatchOrderEngine.getInstance().addLast(iCharge);
			}
		}else if (liandongQQ.getStatus().equals("-1")) {
			//处理中,继续等待
			//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
//			mainorder.setQueryTimes(mainorder.getQueryTimes()+1);
//			orderService.update(mainorder);
			//刷新时间5秒后
//			mainorderDao.updateRefreshTime(System.currentTimeMillis()+5000,mainorder.getMainOrderId());
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			result = Const.OperationLogResult.PROCESSING; 
		}else{
			//订单状态为失败
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_FAILED);
			//销售商品返回未销售状态
			if (mainorder.getCardLibIds()!=null) {
				cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
			}
			if (mainorder.getMoneyBack().equals(1)) {
				operationLogService.addOperationLog(user,mainorder, liandongQQ.getQQnum(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.FAILED,null);
				//商户自动返还额度
//				useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
				//供货商自动返还额度
				System.out.println("++++++++++++++++++++++++++++++"+mainorder.getSupply().getId());
				Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
				System.out.println("=================================="+supply);
				//转换冲正的卡密状态
				//cardLibService.reverseCardLib(mainorder.getCardLibIds());
//				SupplyKernel.getInstance().cancelBalance(mainorder,supply,mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
				ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
				ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
				UserChargeEngine.getInstance().addLast(iCharge);
			    UserChargeEngine.getInstance().addLast(iCharge2);
				//订单回水标记0
				orderService.updateMoneyBack(mainorder, 0);
			}
			
			// 更新供货商对应商品错误次数
//			SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//			SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, supplyInterface.getFailedCount()+1);
//			//连续出现5次错误就禁用
//			if (supplyInterface.getFailedCount()>5 && supplyInterface.getFailedCount()<10) {
//				//SupplyKernel.getInstance().updateSupplyInterfaceState(String.valueOf(supplyInterface.getId()), 0);
//				String[] mobiles = new String[]{"13560192965"};
//				String content = "你好,"+supplyInterface.getSupplyName()+supplyInterface.getGoodsName()+"已经连续5次返回失败,请登陆网站查看!【妙明在线】";
//				SDKClientUtil.SendSMS(mobiles, content, "", 0);
//			}
			result = Const.OperationLogResult.FAILED;
			//如果是批量单,刷新总单
			if (mainorder.getIsBatch()==1) {
				orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
				BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
				//orderService.updateBatchOrder(batchOrder);
				com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
				RefreshBatchOrderEngine.getInstance().addLast(iCharge);
			}
		}
		return result;
//		SupplyKernel.getInstance().purchaseBalance(mainorder);
//		useraccountService.purchaseAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
	}
	
	
	@Override
	public int reverseOrder(Liandong liandong){
		String url = LiandongUtil.reverseOrder(liandong);
		Liandong ld = LiandongUtil.getLiandong(url, 3);
		liandong.setReverseStatus(ld.getReverseStatus());
		liandong.setReverseXmlText(ld.getReverseXmlText());
		this.update(liandong);
		return Integer.parseInt(liandong.getReverseStatus());
	}

	@Override
	public LiandongPay findLiandongPayById(String id) {
		LiandongPay liandongPay=(LiandongPay)findById(LiandongPay.class, id);
		return liandongPay;
	}

	@Override
	public LiandongPay updateLiandongPayStatus(LiandongPay liandongPay, int status) {
		liandongPay.setIsDeal(1);
		liandongPay.setStatus(status);
		this.update(liandongPay);
		return liandongPay;
	}

	@Override
	public LiandongPay addLiandongPay(LiandongPay liandongPay) {
		liandongPay.setCreateTime(new Date());
		liandongPay.setStatus(0);
		liandongPay.setIsDeal(0);
		save(liandongPay);
		return liandongPay;
	}
	
	@Override
	public Liandong findLiandongByOrderId(String orderId){
		DetachedCriteria dec = DetachedCriteria.forClass(Liandong.class);
		dec.add(Restrictions.eq("orderID", orderId));
		List<Liandong> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public LiandongQQ findLiandongQQByOrderId(String orderId){
		DetachedCriteria dec = DetachedCriteria.forClass(LiandongQQ.class);
		dec.add(Restrictions.eq("orderID", orderId));
		List<LiandongQQ> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Liandong updateLiandong(Mainorder mainorder,Liandong reLd){
		Liandong liandong = this.findLiandongByOrderId(mainorder.getMainOrderId());
		if (reLd.getAccount() != null) {
			liandong.setAccount(reLd.getAccount());
		}
		if (reLd.getCodeSN() != null) {
			liandong.setCodeSN(reLd.getCodeSN());
		}
		if (reLd.getErrorID() != null) {
			liandong.setErrorID(reLd.getErrorID());
		}
		if (reLd.getErrorMsg() != null) {
			liandong.setErrorMsg(reLd.getErrorMsg());
		}
		if (reLd.getXmlText() != null) {
			liandong.setXmlText(reLd.getXmlText());
		}
		if (reLd.getUserName() != null) {
			liandong.setUserName(reLd.getUserName());
		}
		//更新liandong
		this.update(liandong);
		return liandong;
	}
	
	@Override
	public LiandongQQ updateLiandongQQ(Mainorder mainorder,LiandongQQ reLdQQ){
		LiandongQQ liandongQQ = this.findLiandongQQByOrderId(mainorder.getMainOrderId());
		if (reLdQQ.getStatus() != null) {
			liandongQQ.setStatus(reLdQQ.getStatus());
		}
		if (reLdQQ.getDemo() != null) {
			liandongQQ.setDemo(reLdQQ.getDemo());
		}
		if (reLdQQ.getXmlText() != null) {
			liandongQQ.setXmlText(reLdQQ.getXmlText());
		}
		if (reLdQQ.getUserName() != null) {
			liandongQQ.setUserName(reLdQQ.getUserName());
		}
		//更新liandong
		this.update(liandongQQ);
		return liandongQQ;
	}
	
	@Override
	public Liandong initLiandong(Mainorder mainorder, String mobile,SupplyInterface supplyInterface){
		Liandong liandong = new Liandong();
		liandong.setOrderID(mainorder.getMainOrderId());
		liandong.setMobile(mobile);
		liandong.setMoney(supplyInterface.getValue()+"00");
		liandong.setStatus("0");
		liandong.setReverseStatus("-1");
		liandong.setIsDeal(0);
		//这里把基础信息先保存一次
		this.save(liandong);
		return liandong;
	}
	
	@Override
	public LiandongQQ initLiandongQQ(Mainorder mainorder, String QQnum,SupplyInterface supplyInterface){
		LiandongQQ liandongQQ = new LiandongQQ();
		String type = "";
		switch (mainorder.getGoodsFlag()) {
		case Const.GoodsFlag.GAME_RECHARGE:
			type = "Q001";
			break;
			//TODO: 其他类型需要时再设置
		default:
			//TODO: 其他类型需要时再设置
			break;
		}
		liandongQQ.setUserName(LiandongUtil.qq_userName);
		liandongQQ.setType(type);
		liandongQQ.setOrderID(mainorder.getMainOrderId());
		liandongQQ.setQQnum(QQnum);
		liandongQQ.setBuyCount(mainorder.getGoodsNo());
		liandongQQ.setOrderSN(mainorder.getMainOrderId());
		liandongQQ.setComm1("1");
		liandongQQ.setStatus("-1");
		liandongQQ.setIsDeal(0);
		//这里把基础信息先保存一次
		this.save(liandongQQ);
		return liandongQQ;
	}
}

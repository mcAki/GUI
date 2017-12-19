package com.sys.volunteer.orderQuery;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.epaylinks.www.TerminalAirOrderNew;
import cn.epaylinks.www.TerminalAirOrderQueryResponseNew;
import cn.epaylinks.www.TerminalAirReversalRequestNew;
import cn.epaylinks.www.TerminalAirReversalResponseNew;

import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.SDKClientUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.muticharge.ChargeMutithreadManager;
import com.sys.volunteer.muticharge.engine.OrderVO;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.order.RefreshBatchOrderEngine;
import com.sys.volunteer.order.engine.charge.BatchOrderRefresh;
import com.sys.volunteer.pojo.AirDepositResponseNew;
import com.sys.volunteer.pojo.AirReversalRequestNew;
import com.sys.volunteer.pojo.AirReversalResponseNew;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.OrderQueryNew;
import com.sys.volunteer.pojo.OrderQueryResponseNew;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.terminalAirDepositResponseNew.DepositResponseService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;
import com.sys.volunteer.ws.client.WsFacade;
@Service
@Transactional
public class OrderQueryResponseNewServiceBean extends CommonDao implements
		OrderQueryResponseNewService {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	DepositResponseService depositResponseService;
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
	public OrderQueryResponseNew initOrderQueryResponseNew(
			AirDepositResponseNew airDepositResponseNew,Mainorder mainorder,String userMobile) {
		OrderQueryResponseNew orderQueryResponseNew=new OrderQueryResponseNew();
		orderQueryResponseNew.setDepositId(0);
		orderQueryResponseNew.setDepositState(-100);
		orderQueryResponseNew.setOrderId(mainorder.getMainOrderId());
		orderQueryResponseNew.setOrderNo(airDepositResponseNew.getOrderNo());
		orderQueryResponseNew.setRespCode(airDepositResponseNew.getRespCode());
		orderQueryResponseNew.setRespDisc(airDepositResponseNew.getRespDisc());
		orderQueryResponseNew.setResponseTime(airDepositResponseNew.getResponseTime());
		orderQueryResponseNew.setReversalState(-100);
		orderQueryResponseNew.setSign(airDepositResponseNew.getSign());
		orderQueryResponseNew.setStoreSeq(airDepositResponseNew.getStoreSeq());
		orderQueryResponseNew.setTerminalNo(airDepositResponseNew.getTerminalNo());
		orderQueryResponseNew.setUserMobileNum(userMobile);
		orderQueryResponseNew.setBeginDate(DateUtil.getMaxDate(new Date()));
		orderQueryResponseNew.setEndDate(DateUtil.getMinDate(new Date()));
		orderQueryResponseNew.setResponseTime(new Date());
		orderQueryResponseNew.setLogFor(1);
		orderQueryResponseNew.setIsDeal(0);
		this.save(orderQueryResponseNew);
		return orderQueryResponseNew;
	}
	
	@Override
	public OrderQueryResponseNew initOrderQueryResponseNew(
			AirReversalResponseNew airReversalResponseNew,Mainorder mainorder,String userMobile) {
		OrderQueryResponseNew orderQueryResponseNew=new OrderQueryResponseNew();
		orderQueryResponseNew.setDepositId(0);
		orderQueryResponseNew.setDepositState(-100);
		orderQueryResponseNew.setOrderId(mainorder.getMainOrderId());
		orderQueryResponseNew.setOrderNo(airReversalResponseNew.getOrderNo());
		orderQueryResponseNew.setRespCode(airReversalResponseNew.getRespCode());
		orderQueryResponseNew.setRespDisc(airReversalResponseNew.getRespDisc());
		orderQueryResponseNew.setResponseTime(airReversalResponseNew.getResponseTime());
		orderQueryResponseNew.setReversalState(-100);
		orderQueryResponseNew.setSign(airReversalResponseNew.getSign());
		orderQueryResponseNew.setStoreSeq(airReversalResponseNew.getStoreSeq());
		orderQueryResponseNew.setTerminalNo(airReversalResponseNew.getTerminalNo());
		orderQueryResponseNew.setUserMobileNum(userMobile);
		orderQueryResponseNew.setBeginDate(DateUtil.getMaxDate(new Date()));
		orderQueryResponseNew.setEndDate(DateUtil.getMinDate(new Date()));
		orderQueryResponseNew.setResponseTime(new Date());
		orderQueryResponseNew.setLogFor(2);
		orderQueryResponseNew.setIsDeal(0);
		this.save(orderQueryResponseNew);
		return orderQueryResponseNew;
	}
	
	@Override
	public AirReversalResponseNew initAirReversalResponseNew(
			String mainOrderId, String mobileNum, double amount, String storeSeq) throws MalformedURLException, ServiceException, RemoteException {
		TerminalAirReversalRequestNew terminalAirReversalRequestNew = WsFacade.initAirReversalRequestNew(mainOrderId, mobileNum, amount, storeSeq);
		AirReversalRequestNew airReversalRequestNew = new AirReversalRequestNew();
		airReversalRequestNew.setAmount(terminalAirReversalRequestNew.getAmount());
		airReversalRequestNew.setMobileNum(terminalAirReversalRequestNew.getMobileNum());
		airReversalRequestNew.setRequestTime(terminalAirReversalRequestNew.getRequestTime().getTime());
		airReversalRequestNew.setReversalStoreSeq(terminalAirReversalRequestNew.getStoreSeq());
		airReversalRequestNew.setStoreSeq(terminalAirReversalRequestNew.getStoreSeq());
		airReversalRequestNew.setTerminalNo(terminalAirReversalRequestNew.getTerminalNo());
		airReversalRequestNew.setSign(terminalAirReversalRequestNew.getSign());
		this.save(airReversalRequestNew);
		
		logger.info("这里插入了一条airReversalRequestNew,orderId是:"+airReversalRequestNew.getStoreSeq());
		
		TerminalAirReversalResponseNew terminalAirOrderQueryResponseNew = WsFacade.terminalAirReversalNew(
				terminalAirReversalRequestNew);
		
		AirReversalResponseNew airReversalResponseNew = null;
		if (terminalAirOrderQueryResponseNew != null) {
			airReversalResponseNew = new AirReversalResponseNew();
			airReversalResponseNew.setAmount(terminalAirOrderQueryResponseNew.getAmount());
			airReversalResponseNew.setMobileNum(terminalAirOrderQueryResponseNew.getMobileNum());
			airReversalResponseNew.setOrderNo(terminalAirOrderQueryResponseNew.getOrderNo());
			airReversalResponseNew.setRespCode(terminalAirOrderQueryResponseNew.getRespCode());
			airReversalResponseNew.setRespDisc(terminalAirOrderQueryResponseNew.getRespDisc());
			airReversalResponseNew.setResponseTime(terminalAirOrderQueryResponseNew.getResponseTime().getTime());
			airReversalResponseNew.setSign(terminalAirOrderQueryResponseNew.getSign());
			airReversalResponseNew.setStoreSeq(terminalAirOrderQueryResponseNew.getStoreSeq());
			airReversalResponseNew.setTerminalNo(terminalAirOrderQueryResponseNew.getTerminalNo());
			this.save(airReversalResponseNew);
			
			logger.info("这里返回了一条airReversalResponseNew,respCode是:"+airReversalResponseNew.getRespCode());
			
			System.out.println("++++++++++++++++++++++++"+airReversalResponseNew);
		}else {
			logger.error("e票联充正接口返回了空对象!");
		}
		
		
		return airReversalResponseNew;
	}
	
	private void initOrderQueryNew(TerminalAirOrderNew terminalAirOrderNew,OrderQueryResponseNew orderQueryResponseNew){
		OrderQueryNew orderQueryNew = new OrderQueryNew();
		orderQueryNew.setAggenMobileNum(terminalAirOrderNew.getAggenMobileNum());
		orderQueryNew.setAmount(terminalAirOrderNew.getAmount());
		if (terminalAirOrderNew.getApplyTime()!=null) {
			orderQueryNew.setApplyTime(terminalAirOrderNew.getApplyTime().getTime());
		}
		orderQueryNew.setDepositId(terminalAirOrderNew.getDepositId());
		orderQueryNew.setDepositState(terminalAirOrderNew.getDepositState());
		if (terminalAirOrderNew.getDepositTime()!=null) {
			orderQueryNew.setDepositTime(terminalAirOrderNew.getDepositTime().getTime());
		}
		orderQueryNew.setOrderNo(terminalAirOrderNew.getOrderNo());
		orderQueryNew.setOrderqueryresponseid(orderQueryResponseNew.getOrderQueryId());
		orderQueryNew.setReversalId(terminalAirOrderNew.getReversalId());
		orderQueryNew.setReversalState(terminalAirOrderNew.getReversalState());
		if (terminalAirOrderNew.getReversalTime()!=null) {
			orderQueryNew.setReversalTime(terminalAirOrderNew.getReversalTime().getTime());
		}
		orderQueryNew.setStoreSeq(terminalAirOrderNew.getStoreSeq());
		orderQueryNew.setTerminalNo(terminalAirOrderNew.getTerminalNo());
		orderQueryNew.setUserMobileBalance(terminalAirOrderNew.getUserMobileBalance());
		orderQueryNew.setUserMobileNum(terminalAirOrderNew.getUserMobileNum());
		this.save(orderQueryNew);
	}

	@Override
	public List<OrderQueryResponseNew> findOrderQueryResponseNewsByDepositState() {
		DetachedCriteria dec=DetachedCriteria.forClass(OrderQueryResponseNew.class);
		dec.add(Restrictions.eq("depositState", 0));
		List<OrderQueryResponseNew> orderQueryResponseNews=this.findByDetachedCriteria(dec);
		return orderQueryResponseNews;
	}

	@Override
	public List<OrderQueryResponseNew> findOrderQueryResponseNewByOrderId(
			String orderId,int logFor) {
		DetachedCriteria dec=DetachedCriteria.forClass(OrderQueryResponseNew.class);
		dec.add(Restrictions.eq("orderId", orderId));
		dec.add(Restrictions.eq("logFor", logFor));
		dec.add(Restrictions.eq("isDeal", 0));
		List<OrderQueryResponseNew> list=this.findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public int refreshOrderState(Mainorder mainorder) throws Exception {
		//System.out.println("==========执行1次========:    "+System.currentTimeMillis());
		int logFor = 0;
		if (mainorder.getState().equals(3)) {
			logFor = 1;
		}else if (mainorder.getReversalState().equals(0)){
			logFor = 2;
		}else {
			System.out.println("定单状态不在刷新范围!");
		}
		List<OrderQueryResponseNew> orderQueryResponseNews=this.findOrderQueryResponseNewByOrderId(mainorder.getMainOrderId(),logFor);
		if (orderQueryResponseNews == null || orderQueryResponseNews.isEmpty()) {
			logger.info("=======查找订单为空!======");
			//查找单为空,则返回失败
			Users user=userService.findUserById(mainorder.getUsers().getUserId());
			if (mainorder.getState().equals(3)) {
				//订单状态为失败
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_FAILED);
				//销售商品返回未销售状态
				if (mainorder.getCardLibIds()!=null) {
					cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
				}
				if (mainorder.getMoneyBack().equals(1)) {
					operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.FAILED,null);
					//商户自动返还额度
//					useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
					//供货商自动返还额度
					Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
//					SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
					ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
					ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), supply, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
					UserChargeEngine.getInstance().addLast(iCharge);
				    UserChargeEngine.getInstance().addLast(iCharge2);
					//订单回水标记0
					orderService.updateMoneyBack(mainorder, 0);
				}
				//转换冲正的卡密状态
				//cardLibService.reverseCardLib(mainorder.getCardLibIds());
				// 更新供货商对应商品错误次数
//				SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//				SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, supplyInterface.getFailedCount()+1);
//				//连续出现5次错误就禁用
//				if (supplyInterface.getFailedCount()>5 && supplyInterface.getFailedCount()<10) {
//					//SupplyKernel.getInstance().updateSupplyInterfaceState(String.valueOf(supplyInterface.getId()), 0);
//					String[] mobiles = new String[]{"13560192965"};
//					String content = "你好,"+supplyInterface.getSupplyName()+supplyInterface.getGoodsName()+"已经连续5次返回失败,请登陆网站查看!【妙明在线】";
//					SDKClientUtil.SendSMS(mobiles, content, "", 0);
//				}
				//如果是批量单,刷新总单
				if (mainorder.getIsBatch()==1) {
					orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
					BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
					//orderService.updateBatchOrder(batchOrder);
					com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
					RefreshBatchOrderEngine.getInstance().addLast(iCharge);
				}
			}else if (mainorder.getReversalState().equals(0)) {
				//冲正失败
				orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_FAILED);
				operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.FAILED,null);
			}
			return Const.OperationLogResult.FAILED;
		}else {
			TerminalAirOrderQueryResponseNew terminalAirOrderQueryResponseNew=null;
			OrderQueryResponseNew orderQueryResponseNew = orderQueryResponseNews.get(0);
			try {
				terminalAirOrderQueryResponseNew=WsFacade.terminalAirOrderQueryNew(orderQueryResponseNew);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null!=terminalAirOrderQueryResponseNew) {
//					if (orderQueryResponseNew==null) {
//						Mainorder mainorder = airDepositResponseNew.getMainorder();
//						orderQueryResponseNew = initOrderQueryResponseNew(airDepositResponseNew, mainorder, airDepositResponseNew.getMobileNum());
//					}
				orderQueryResponseNew = this.updateOrderQuery(orderQueryResponseNew, terminalAirOrderQueryResponseNew);
//				if ((terminalAirOrderQueryResponseNew.getTerminalAirOrders()==null||terminalAirOrderQueryResponseNew.getTerminalAirOrders().length==0)) {
					//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
//					orderService.updateOrderRespTime(mainorder);
//				}
				int result = this.updateOrderState(terminalAirOrderQueryResponseNew,logFor);
				return result;
			}
		}
		return Const.OperationLogResult.NONE_PROCESS;
	}
	
	public OrderQueryResponseNew updateOrderQuery(OrderQueryResponseNew orderQueryResponseNew,TerminalAirOrderQueryResponseNew terminalAirOrderQueryResponseNew){
		orderQueryResponseNew.setBeginDate(terminalAirOrderQueryResponseNew.getBeginDate().getTime());
		orderQueryResponseNew.setDepositId(terminalAirOrderQueryResponseNew.getDepositId());
		orderQueryResponseNew.setDepositState(terminalAirOrderQueryResponseNew.getDepositState());
		orderQueryResponseNew.setEndDate(terminalAirOrderQueryResponseNew.getEndDate().getTime());
		orderQueryResponseNew.setOrderNo(terminalAirOrderQueryResponseNew.getOrderNo());
		orderQueryResponseNew.setRespCode(terminalAirOrderQueryResponseNew.getRespCode());
		orderQueryResponseNew.setRespDisc(terminalAirOrderQueryResponseNew.getRespDisc());
		orderQueryResponseNew.setResponseTime(terminalAirOrderQueryResponseNew.getResponseTime().getTime());
		orderQueryResponseNew.setReversalState(terminalAirOrderQueryResponseNew.getReversalState());
		orderQueryResponseNew.setStoreSeq(terminalAirOrderQueryResponseNew.getStoreSeq());
		orderQueryResponseNew.setTerminalNo(terminalAirOrderQueryResponseNew.getTerminalNo());
		TerminalAirOrderNew[] terminalAirOrderNews = terminalAirOrderQueryResponseNew.getTerminalAirOrders();
		if (terminalAirOrderNews!=null&&terminalAirOrderNews.length>0) {
			for (TerminalAirOrderNew terminalAirOrderNew : terminalAirOrderNews) {
				if (terminalAirOrderNew.getDepositState()!=0&&terminalAirOrderNew.getReversalState()!=0) {
					initOrderQueryNew(terminalAirOrderNew, orderQueryResponseNew);
				}
			}
		}
		this.update(orderQueryResponseNew);
		return orderQueryResponseNew;
	}

	@Override
	public int updateOrderState(TerminalAirOrderQueryResponseNew terminalAirOrderQueryResponseNew,int logFor) throws Exception{
		int result = Const.OperationLogResult.NONE_PROCESS;
		List<OrderQueryResponseNew> orderQueryResponseNews = findOrderQueryResponseNewByStoreSeq(terminalAirOrderQueryResponseNew.getStoreSeq(),logFor);
		OrderQueryResponseNew orderQueryResponseNew = orderQueryResponseNews.get(0);
		Mainorder mainorder = orderService.findOrderById(orderQueryResponseNew.getOrderId());
		//Users user=userService.findUserByMobile(orderQueryResponseNew.getUserMobileNum());
		Users user=userService.findUserById(mainorder.getUsers().getUserId());
		if (orderQueryResponseNew.getLogFor().equals(1)) {
			if (terminalAirOrderQueryResponseNew.getTerminalAirOrders()!=null&&terminalAirOrderQueryResponseNew.getTerminalAirOrders().length>0&&terminalAirOrderQueryResponseNew.getTerminalAirOrders()[0].getDepositState()==1) {
				//成功
				//已处理
				orderQueryResponseNew.setIsDeal(1);
				this.update(orderQueryResponseNew);
				//订单状态为成功
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
				operationLogService.addOperationLog(user,mainorder, orderQueryResponseNew.getUserMobileNum(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
//				SupplyKernel.getInstance().purchaseBalance(mainorder);
//				useraccountService.purchaseAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//						mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
				result = Const.OperationLogResult.SUCCESS;
				// 更新供货商对应商品错误次数
//				SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//				SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, 0);
				//如果是批量单,刷新总单
				if (mainorder.getIsBatch()==1) {
					orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
					BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
					//orderService.updateBatchOrder(batchOrder);
					com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
					RefreshBatchOrderEngine.getInstance().addLast(iCharge);
				}
			}else if (terminalAirOrderQueryResponseNew.getTerminalAirOrders()!=null&&terminalAirOrderQueryResponseNew.getTerminalAirOrders().length>0&&terminalAirOrderQueryResponseNew.getTerminalAirOrders()[0].getDepositState()==0) {
				//处理中,继续刷
//				mainorderDao.updateRefreshTime(System.currentTimeMillis()+25000, mainorder.getMainOrderId());
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
				result = Const.OperationLogResult.PROCESSING;
			}else if ((terminalAirOrderQueryResponseNew.getTerminalAirOrders()==null||terminalAirOrderQueryResponseNew.getTerminalAirOrders().length==0)) {
				//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
				//mainorderDao.updateQueryTimes(mainorder.getRespTime()+1,mainorder.getMainOrderId());
				//刷新时间5秒后
//				mainorderDao.updateRefreshTime(System.currentTimeMillis()+25000, mainorder.getMainOrderId());
				//处理中,继续刷
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
				result = Const.OperationLogResult.PROCESSING;
			}else {
				//失败
				//已处理
				orderQueryResponseNew.setIsDeal(1);
				this.update(orderQueryResponseNew);
				//订单状态为失败
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_FAILED);
				//销售商品返回未销售状态
				if (mainorder.getCardLibIds()!=null) {
					cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
				}
				if (mainorder.getMoneyBack().equals(1)) {
					operationLogService.addOperationLog(user,mainorder, orderQueryResponseNew.getUserMobileNum(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.FAILED,null);
					//商户自动返还额度
//					useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
					//供货商自动返还额度
					Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
//					SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
					ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
					ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), supply, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
					UserChargeEngine.getInstance().addLast(iCharge);
				    UserChargeEngine.getInstance().addLast(iCharge2);
					//订单回水标记0
					orderService.updateMoneyBack(mainorder, 0);
				}
				
				//转换冲正的卡密状态
				//cardLibService.reverseCardLib(mainorder.getCardLibIds());
				// 更新供货商对应商品错误次数
//				SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//				SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, supplyInterface.getFailedCount()+1);
//				//连续出现5次错误就禁用
//				if (supplyInterface.getFailedCount()>5 && supplyInterface.getFailedCount()<10) {
//					//SupplyKernel.getInstance().updateSupplyInterfaceState(String.valueOf(supplyInterface.getId()), 0);
//					String[] mobiles = new String[]{"13560192965"};
//					String content = "你好,"+supplyInterface.getSupplyName()+supplyInterface.getGoodsName()+"已经连续5次返回失败,请登陆网站查看!【妙明在线】";
//					SDKClientUtil.SendSMS(mobiles, content, "", 0);
//				}
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
		}else {
			if (terminalAirOrderQueryResponseNew.getTerminalAirOrders()!=null&&terminalAirOrderQueryResponseNew.getTerminalAirOrders().length>0&&terminalAirOrderQueryResponseNew.getTerminalAirOrders()[0].getReversalState()==1) {
				//成功
				//冲正次数-1
				user.setReversalCount(user.getReversalCount()-1);
				userService.update(user);
				//已处理
				orderQueryResponseNew.setIsDeal(1);
				this.update(orderQueryResponseNew);
				//订单状态为冲正
				orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_SUCCESS);
				if (mainorder.getMoneyBack().equals(1)) {
					operationLogService.addOperationLog(user,mainorder, orderQueryResponseNew.getUserMobileNum(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.SUCCESS,null);
					//商户自动返还额度
//					useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.REVERSAL);
					//供货商自动返还额度
					Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
//					SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
					ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.REVERSAL);
					ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), supply, null, null, Const.UseraccountdealdetailFlag.REVERSAL);
					UserChargeEngine.getInstance().addLast(iCharge);
				    UserChargeEngine.getInstance().addLast(iCharge2);
					//订单回水标记0
					orderService.updateMoneyBack(mainorder, 0);
				}
				
//				SupplyKernel.getInstance().purchaseBalance(mainorder);
//				useraccountService.purchaseAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//						mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
				result = Const.OperationLogResult.SUCCESS;
			}else if (terminalAirOrderQueryResponseNew.getTerminalAirOrders()!=null&&terminalAirOrderQueryResponseNew.getTerminalAirOrders().length>0&&terminalAirOrderQueryResponseNew.getTerminalAirOrders()[0].getReversalState()==0) {
				//处理中,继续刷
				//刷新时间5秒后
//				mainorderDao.updateRefreshTime(System.currentTimeMillis()+25000,mainorder.getMainOrderId());
				orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESSING);
				result = Const.OperationLogResult.PROCESSING;
			}else if ((terminalAirOrderQueryResponseNew.getTerminalAirOrders()==null||terminalAirOrderQueryResponseNew.getTerminalAirOrders().length==0)) {
				//刷新时间5秒后
//				mainorderDao.updateRefreshTime(System.currentTimeMillis()+25000,mainorder.getMainOrderId());
				//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
//				int time = mainorder.getRespTime();
//				mainorderDao.updateQueryTimes(time+1 ,mainorder.getMainOrderId());
//				mainorder = orderService.findOrderById(mainorder.getMainOrderId());
//				System.out.println(mainorder.getRespTime());
				//处理中,继续刷
				result = Const.OperationLogResult.PROCESSING;
			}else {
				//失败
				//已处理
				orderQueryResponseNew.setIsDeal(1);
				this.update(orderQueryResponseNew);
				//订单状态为冲正失败
				orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_FAILED);
				operationLogService.addOperationLog(user,mainorder, orderQueryResponseNew.getUserMobileNum(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.FAILED,null);
				result = Const.OperationLogResult.FAILED;
			}
		}
		return result;
	}

	@Override
	public List<OrderQueryResponseNew> findOrderQueryResponseNewByStoreSeq(
			String orderId,int logFor) {
		DetachedCriteria dec=DetachedCriteria.forClass(OrderQueryResponseNew.class);
		dec.add(Restrictions.eq("storeSeq", orderId));
		dec.add(Restrictions.eq("logFor", logFor));
		dec.add(Restrictions.eq("isDeal", 0));
		List<OrderQueryResponseNew> list=this.findByDetachedCriteria(dec);
		return list;
	}

}

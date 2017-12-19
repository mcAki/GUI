package com.sys.volunteer.feiyue;

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
import com.sys.volunteer.common.FeiyueUtil;
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.order.RefreshBatchOrderEngine;
import com.sys.volunteer.order.engine.charge.BatchOrderRefresh;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.Feiyue;
import com.sys.volunteer.pojo.Liandong;
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
public class FeiyueServiceBean extends CommonDao implements FeiyueService {

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
	public Feiyue initFeiyue(Mainorder mainorder, SupplyInterface supplyInterface) {
		//TODO: 补空格
		//TODO: agtte1,pwd,sn
		Feiyue feiyue = new Feiyue();
		feiyue.setOrderId(mainorder.getMainOrderId());
		feiyue.setAgtte1(FeiyueUtil.agtte1);
		feiyue.setMoney(supplyInterface.getStockPrice()+"");
		feiyue.setPhone(mainorder.getMobile());
		feiyue.setSn("");
		feiyue.setPwd(FeiyueUtil.pwd);
		feiyue.setState("-1");
		save(feiyue);
		return feiyue;
	}

	@Override
	public int updateDealingOrders(Mainorder mainorder) throws Exception {
		Feiyue feiyue = findFeiyueByOrderId(mainorder.getMainOrderId());
		String url=FeiyueUtil.queryOrder(feiyue);
		if (url==null) {
			logger.error(mainorder.getMainOrderId()+"飞跃url为空!");
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			return Const.OperationLogResult.NONE_PROCESS;
		}
		//String queryXmlText = LiandongUtil.getRotmePage(url, "utf-8");
		//System.out.println(queryXmlText);
		Feiyue fy=FeiyueUtil.getFeiyue(url,2);
		//liandong.setQueryXmlText(queryXmlText);
		if (fy==null) {
//			return Const.OperationLogResult.NONE_PROCESS;
			//联动没有返回数据,继续查询
			//liandong.setIsDeal(1);
			logger.error("飞跃返回空参数!订单号为"+mainorder.getMainOrderId());
			feiyue.setState("-1");
			//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
//			orderService.updateOrderRespTime(mainorder);
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
		}else {
			if (fy.getState() == null) {
				//查询有错误
				//feiyue.setIsDeal(1);
				feiyue.setState("2");
			}
			if (fy.getPhone()!=null&&fy.getPhone().equals(feiyue.getStreamid())) {
				//liandong.setIsDeal(1);
				feiyue.setState(fy.getState());
			}
			feiyue.setQueryXmlText(fy.getQueryXmlText());
		}
		logger.info(mainorder.getMainOrderId()+"飞跃联动订单,订单状态是:"+feiyue.getState());
		this.update(feiyue);
		return this.updateOrderState(feiyue);
	}

	
	public int updateOrderState(Feiyue feiyue) throws Exception{
		int result = Const.OperationLogResult.NONE_PROCESS;
		Mainorder mainorder=orderService.findOrderById(feiyue.getOrderId());
		//Users user=userService.findUserByMobile(orderQueryResponseNew.getUserMobileNum());
		Users user=userService.findUserById(mainorder.getUsers().getUserId());
		if (feiyue.getState().equals("0")) {
			//订单状态为成功
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
			operationLogService.addOperationLog(user,mainorder, feiyue.getPhone(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
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
		}else if (feiyue.getState().equals("9")||feiyue.getState().equals("2")) {
			//9处理中,2冲正已受理
			//处理中,继续等待
			//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
//			mainorder.setQueryTimes(mainorder.getQueryTimes()+1);
//			orderService.update(mainorder);
			//刷新时间5秒后
//			mainorderDao.updateRefreshTime(System.currentTimeMillis()+5000,mainorder.getMainOrderId());
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			result = Const.OperationLogResult.PROCESSING; 
		}else if(feiyue.getState().equals("1")) {
			//订单状态为失败
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_FAILED);
			//销售商品返回未销售状态
			if (mainorder.getCardLibIds()!=null) {
				cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
			}
			if (mainorder.getMoneyBack().equals(1)) {
				operationLogService.addOperationLog(user,mainorder, feiyue.getPhone(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.FAILED,null);
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
		}else if (feiyue.getState().equals("3")) {
			//冲正成功
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
		}else if (feiyue.getState().equals("4")&&feiyue.getState().equals("8")) {
			//冲正失败
			result = Const.reverseResult.FAILED;
			orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_FAILED);
			operationLogService.addOperationLog(user, mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.FAILED,null);
		}
		return result;
//		SupplyKernel.getInstance().purchaseBalance(mainorder);
//		useraccountService.purchaseAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
	}
	
	@Override
	public int reverseOrder(Feiyue feiyue){
		String url = FeiyueUtil.reverseOrder(feiyue);
		Feiyue fy = FeiyueUtil.getFeiyue(url, 3);
		//feiyue.setState(fy.getState());
		feiyue.setReverseXmlText(fy.getReverseXmlText());
		this.update(feiyue);
		return Integer.parseInt(feiyue.getState());
	}
	
	@Override
	public Feiyue findFeiyueByOrderId(String orderId){
		DetachedCriteria dec = DetachedCriteria.forClass(Feiyue.class);
		dec.add(Restrictions.eq("orderId", orderId));
		List<Feiyue> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Feiyue updateFeiyue(Mainorder mainorder,Feiyue reFy){
		Feiyue feiyue = this.findFeiyueByOrderId(mainorder.getMainOrderId());
		if (reFy.getStreamid() != null) {
			feiyue.setStreamid(reFy.getStreamid());
		}
		if (reFy.getOpmoney() != null) {
			feiyue.setOpmoney(reFy.getOpmoney());
		}
		if (reFy.getOptime() != null) {
			feiyue.setOptime(reFy.getOptime());
		}
		if (reFy.getXmlText() != null) {
			feiyue.setXmlText(reFy.getXmlText());
		}
		if (reFy.getState() != null) {
			feiyue.setState(reFy.getState());
		}
		if (reFy.getBalance() != null) {
			feiyue.setBalance(reFy.getBalance());
		}
		if (reFy.getMutid() != null) {
			feiyue.setMutid(reFy.getMutid());
		}
		if (reFy.getRetcode() != null) {
			feiyue.setRetcode(reFy.getRetcode());
		}
		if (reFy.getRetmessage() != null) {
			feiyue.setRetmessage(reFy.getRetmessage());
		}
		//更新liandong
		this.update(feiyue);
		return feiyue;
	}
}

package com.sys.volunteer.zhongrong;

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
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.RandomUtils;
import com.sys.volunteer.common.SDKClientUtil;
import com.sys.volunteer.common.ZhongRongUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.order.RefreshBatchOrderEngine;
import com.sys.volunteer.order.engine.charge.BatchOrderRefresh;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.pojo.Zhongrong;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;

@Transactional
@Service
public class ZhongrongServiceBean extends CommonDao implements ZhongrongService {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	OrderService orderService;
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
	public Zhongrong initZhongrong(Mainorder mainorder, String mobile,
			SupplyInterface supplyInterface, String productType, int isGame, String productId) {
		Zhongrong zhongrong = new Zhongrong();
		zhongrong.setOrderid(mainorder.getMainOrderId());
		zhongrong.setMobile(mobile);
		zhongrong.setPrice(supplyInterface.getValue()+"");
		zhongrong.setNum(mainorder.getGoodsNo()+"");
		String spordertime = DateUtil.DateToString(mainorder.getCreateTime(), "yyyyMMddHHmmss");
		zhongrong.setSpordertime(spordertime);
		zhongrong.setSporderid(System.currentTimeMillis()+RandomUtils.getVerificationCode(6));
		zhongrong.setIsDeal(0);
		zhongrong.setProductType(productType);
		zhongrong.setResultno("0");
		zhongrong.setIsGame(isGame);
		zhongrong.setProductid(productId);
		this.save(zhongrong);
		return zhongrong;
	}

	@Override
	public Zhongrong findZhongrongByOrderId(String orderId){
		DetachedCriteria dec = DetachedCriteria.forClass(Zhongrong.class);
		dec.add(Restrictions.eq("orderid", orderId));
		List<Zhongrong> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	
	@Override
	public Zhongrong updateZhongrong(Mainorder mainorder,Zhongrong reZr){
		Zhongrong zhongrong = this.findZhongrongByOrderId(mainorder.getMainOrderId());
		if (reZr.getOrdercash() != null) {
			zhongrong.setOrdercash(reZr.getOrdercash());
		}
		if (reZr.getProductid() != null) {
			zhongrong.setProductid(reZr.getProductid());
		}
		if (reZr.getProductName() != null) {
			zhongrong.setProductName(reZr.getProductName());
		}
		if (reZr.getXmlText() != null) {
			zhongrong.setXmlText(reZr.getXmlText());
		}
		//更新zhongrong
		this.update(zhongrong);
		return zhongrong;
	}
	
	@Override
	public int updateDealingOrders(Mainorder mainorder) throws Exception{
		Zhongrong zhongrong = findZhongrongByOrderId(mainorder.getMainOrderId());
		String url = ZhongRongUtil.orderQuery(zhongrong);
		if (url==null) {
			logger.error(mainorder.getMainOrderId()+"中融url为空!");
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			return Const.OperationLogResult.NONE_PROCESS;
		}
		Zhongrong zr = ZhongRongUtil.getZhongrong(url, 2);
		if (zr == null) {
			logger.error(mainorder.getMainOrderId()+"中融返回空");
			zhongrong.setIsDeal(1);
			zhongrong.setResultno("2");
			orderService.updateOrderRefreshTime(mainorder, 300*1000);
//			orderService.updateOrderRespTime(mainorder);
		}else {
			if (!(zr.getResultno().equals("0") || zr.getResultno().equals("2"))) {
				zhongrong.setIsDeal(1);
				zhongrong.setResultno(zr.getResultno());
			}
			zhongrong.setQueryXmlText(zr.getQueryXmlText());
		}
		logger.info(mainorder.getMainOrderId()+"查询中融订单,订单状态是:"+zhongrong.getResultno());
		this.update(zhongrong);
		return this.updateOrderState(zhongrong);
	}

	
	
	public int updateOrderState(Zhongrong zhongrong) {
		int result = Const.OperationLogResult.NONE_PROCESS;
		Mainorder mainorder=orderService.findOrderById(zhongrong.getOrderid());
//		List<Orderdetail> list=orderService.findOrderdetailsByOrderId(mainorder.getMainOrderId());
		//Users user=userService.findUserByMobile(orderQueryResponseNew.getUserMobileNum());
		Users user=mainorder.getUsers();
		if (zhongrong.getResultno().equals("1")) {
			//订单状态为成功
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
			operationLogService.addOperationLog(user,mainorder, zhongrong.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
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
		}else if (zhongrong.getResultno().equals("0")||zhongrong.getResultno().equals("2")||zhongrong.getResultno().equals("5007")) {
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
				operationLogService.addOperationLog(user,mainorder, zhongrong.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.FAILED,null);
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
			//SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
			//SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, supplyInterface.getFailedCount()+1);
			//连续出现5次错误就禁用
			//if (supplyInterface.getFailedCount()>5 && supplyInterface.getFailedCount()<10) {
				//SupplyKernel.getInstance().updateSupplyInterfaceState(String.valueOf(supplyInterface.getId()), 0);
			//	String[] mobiles = new String[]{"13560192965"};
			//	String content = "你好,"+supplyInterface.getSupplyName()+supplyInterface.getGoodsName()+"已经连续5次返回失败,请登陆网站查看!【妙明在线】";
			//	SDKClientUtil.SendSMS(mobiles, content, "", 0);
			//}
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
	}
	
	
	
	
}

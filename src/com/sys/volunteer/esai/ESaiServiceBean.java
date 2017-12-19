package com.sys.volunteer.esai;

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
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.ESaiUtil;
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.common.MD5Ex;
import com.sys.volunteer.common.RandomUtils;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.order.RefreshBatchOrderEngine;
import com.sys.volunteer.order.engine.charge.BatchOrderRefresh;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.ESaiCharge;
import com.sys.volunteer.pojo.ESaiQuery;
import com.sys.volunteer.pojo.Liandong;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

@Service
@Transactional
public class ESaiServiceBean extends CommonDao implements ESaiService {

	protected Log logger = LogFactory.getLog(this.getClass());

	@Resource
	OrderService orderService;
	@Resource
	UserService userService;
	@Resource
	OperationLogService operationLogService;
	@Resource
	CardLibService cardLibService;
	@Resource
	SupplyService supplyService;
	
	@Override
	public ESaiCharge initESaiCharge(Mainorder mainorder) {
		ESaiCharge eSaiCharge = new ESaiCharge();
		eSaiCharge.setUserNumber(ESaiUtil.USERNUMBER);
		String inOrderNumber = "IP";
		inOrderNumber += eSaiCharge.getUserNumber();
		inOrderNumber += DateUtil.DateToString(new Date(), DateUtil.CM_LONG_DATE_FORMAT_NO_HIPHEN);
		inOrderNumber += RandomUtils.getVerificationCode(4);
		eSaiCharge.setInOrderNumber(inOrderNumber);
		eSaiCharge.setOutOrderNumber(mainorder.getMainOrderId());
		eSaiCharge.setPhoneNumber(mainorder.getMobile());
		eSaiCharge.setProvince("Auto");
		eSaiCharge.setCity("Auto");
		eSaiCharge.setPhoneClass("Auto");
		eSaiCharge.setPhoneMoney(mainorder.getTotalMoney().toString());
		eSaiCharge.setSellPrice("None");
		eSaiCharge.setStartTime(DateUtil.DateToString(new Date(), DateUtil.CM_LONG_DATE_FORMAT));
		eSaiCharge.setTimeOut("600");
		String recordKey = eSaiCharge.getUserNumber() + eSaiCharge.getInOrderNumber() + eSaiCharge.getOutOrderNumber()
						+ eSaiCharge.getPhoneNumber() + eSaiCharge.getProvince() + eSaiCharge.getCity()
						+ eSaiCharge.getPhoneClass() + eSaiCharge.getPhoneMoney() + eSaiCharge.getSellPrice()
						+ eSaiCharge.getStartTime() + eSaiCharge.getTimeOut() + ESaiUtil.SIGN;
		logger.info("EsaiCharge param:"+recordKey);
		recordKey = MD5Ex.getMD5Str(recordKey, "GB2312").substring(0, 16).toUpperCase();
		eSaiCharge.setRecordKey(recordKey);
		eSaiCharge.setRemark("--");
		save(eSaiCharge);
		return eSaiCharge;
	}

	@Override
	public ESaiQuery initESaiQuery(ESaiCharge eSaiCharge) {
		ESaiQuery eSaiQuery = new ESaiQuery();
		eSaiQuery.setUserNumber(eSaiCharge.getUserNumber());
		eSaiQuery.setInOrderNumber(eSaiCharge.getInOrderNumber());
		eSaiQuery.setOutOrderNumber(eSaiCharge.getOutOrderNumber());
		eSaiQuery.setQueryType("P");
		String recordKey = eSaiQuery.getUserNumber() + eSaiQuery.getInOrderNumber() + eSaiQuery.getOutOrderNumber()
					+ eSaiQuery.getQueryType() + ESaiUtil.SIGN;
		logger.info("EsaiQuery param:"+recordKey);
		recordKey = MD5Ex.getMD5Str(recordKey, "GB2312").substring(0, 16).toUpperCase();
		eSaiQuery.setRecordKey(recordKey);
		eSaiQuery.setRemark("--");
		save(eSaiQuery);
		return eSaiQuery;
	}
	
	@Override
	public int updateDealingOrders(Mainorder mainorder) {
		int result = Const.OperationLogResult.NONE_PROCESS;
		ESaiQuery eSaiQuery = findESaiQueryByOrderId(mainorder.getMainOrderId());
		String url=ESaiUtil.queryOrder(eSaiQuery);
		if (url==null) {
			logger.error(mainorder.getMainOrderId()+"易赛url为空!");
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			return Const.OperationLogResult.NONE_PROCESS;
		}
		ESaiQuery reESaiQuery = ESaiUtil.getESaiQuery(url);
		if (reESaiQuery==null) {
			logger.error(mainorder.getMainOrderId()+"易赛返回空");
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
		} else {
			eSaiQuery = this.updateESaiQuery(mainorder,reESaiQuery);
			Users user=userService.findUserById(mainorder.getUsers().getUserId());
			if (eSaiQuery.getPayResult().equals("4")) {
				//成功充值
				//订单状态为成功
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
				operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
				result = Const.OperationLogResult.SUCCESS;
				//如果是批量单,刷新总单
				if (mainorder.getIsBatch()==1) {
					orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
					BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
					//orderService.updateBatchOrder(batchOrder);
					com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
					RefreshBatchOrderEngine.getInstance().addLast(iCharge);
				}
			}else if (eSaiQuery.getPayResult().equals("5")) {
				//失败
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
					System.out.println("++++++++++++++++++++++++++++++"+mainorder.getSupply().getId());
					Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
					System.out.println("=================================="+supply);
					//转换冲正的卡密状态
					//cardLibService.reverseCardLib(mainorder.getCardLibIds());
//					SupplyKernel.getInstance().cancelBalance(mainorder,supply,mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
					ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
					ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
					UserChargeEngine.getInstance().addLast(iCharge);
				    UserChargeEngine.getInstance().addLast(iCharge2);
					//订单回水标记0
					orderService.updateMoneyBack(mainorder, 0);
				}
				
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
			} else {
				//处理中,继续等待
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
				result = Const.OperationLogResult.PROCESSING; 
			}
		}
		return result;
	}
	
	@Override
	public ESaiQuery findESaiQueryByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(ESaiQuery.class);
		dec.add(Restrictions.eq("outOrderNumber", orderId));
		List<ESaiQuery> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public ESaiQuery updateESaiQuery(Mainorder mainorder,ESaiQuery reESaiQuery){
		ESaiQuery eSaiQuery = this.findESaiQueryByOrderId(mainorder.getMainOrderId());
		if (reESaiQuery.getResult() != null) {
			eSaiQuery.setResult(reESaiQuery.getResult());
		}
		if (reESaiQuery.getInOrderNumber() != null) {
			eSaiQuery.setInOrderNumber(reESaiQuery.getInOrderNumber());
		}
		if (reESaiQuery.getOutOrderNumber() != null) {
			eSaiQuery.setOutOrderNumber(reESaiQuery.getOutOrderNumber());
		}
		if (reESaiQuery.getQueryType() != null) {
			eSaiQuery.setQueryType(reESaiQuery.getQueryType());
		}
		if (reESaiQuery.getPayResult() != null) {
			eSaiQuery.setPayResult(reESaiQuery.getPayResult());
		}
		if (reESaiQuery.getRemark() != null) {
			eSaiQuery.setRemark(reESaiQuery.getRemark());
		}
		if (reESaiQuery.getRecordKey() != null) {
			eSaiQuery.setRecordKey(reESaiQuery.getRecordKey());
		}
		eSaiQuery.setXmlText(reESaiQuery.getXmlText());
		//更新eSaiQuery
		this.update(eSaiQuery);
		return eSaiQuery;
	}
	
	@Override
	public ESaiCharge updateESaiCharge(Mainorder mainorder,ESaiCharge reESaiCharge){
		ESaiCharge eSaiCharge = this.findESaiChargeByOrderId(mainorder.getMainOrderId());
		if (reESaiCharge.getResult() != null) {
			eSaiCharge.setResult(reESaiCharge.getResult());
		}
		if (reESaiCharge.getInOrderNumber() != null) {
			eSaiCharge.setInOrderNumber(reESaiCharge.getInOrderNumber());
		}
		if (reESaiCharge.getOutOrderNumber() != null) {
			eSaiCharge.setOutOrderNumber(reESaiCharge.getOutOrderNumber());
		}
		if (reESaiCharge.getRemark() != null) {
			eSaiCharge.setRemark(reESaiCharge.getRemark());
		}
		if (reESaiCharge.getRecordKey() != null) {
			eSaiCharge.setRecordKey(reESaiCharge.getRecordKey());
		}
		eSaiCharge.setXmlText(reESaiCharge.getXmlText());
		//更新eSaiQuery
		this.update(eSaiCharge);
		return eSaiCharge;
	}

	@Override
	public ESaiCharge findESaiChargeByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(ESaiCharge.class);
		dec.add(Restrictions.eq("outOrderNumber", orderId));
		List<ESaiCharge> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}

package com.sys.volunteer.xunyuan;

import java.text.DecimalFormat;
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
import com.sys.volunteer.common.RandomUtils;
import com.sys.volunteer.common.SDKClientUtil;
import com.sys.volunteer.common.StringUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.order.RefreshBatchOrderEngine;
import com.sys.volunteer.order.engine.charge.BatchOrderRefresh;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.pojo.XunyuanChargeReq;
import com.sys.volunteer.pojo.XunyuanChargeResp;
import com.sys.volunteer.pojo.XunyuanChargeResultNotifyResp;
import com.sys.volunteer.pojo.XunyuanQueryBalanceReq;
import com.sys.volunteer.pojo.XunyuanQueryBalanceResp;
import com.sys.volunteer.pojo.XunyuanQueryReq;
import com.sys.volunteer.pojo.XunyuanQueryResp;
import com.sys.volunteer.pojo.XunyuanReverseReq;
import com.sys.volunteer.pojo.XunyuanReverseResp;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;
import com.sys.volunteer.xunyuan.protocol.charge.ChargePo_Rp;
import com.sys.volunteer.xunyuan.protocol.charge.ChargeResultNotifyPo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryPo_Rp;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo_Rp;

@Service
@Transactional
public class XunYuanServiceBean extends CommonDao implements XunYuanService {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	GoodsService goodsService;
	@Resource
	IMainorderDao mainorderDao;
	@Resource
	OrderService orderService;
	@Resource
	OperationLogService operationLogService;
	@Resource
	UserService userService;
	@Resource
	CardLibService cardLibService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	SupplyService supplyService;

	@Override
	public XunyuanChargeResultNotifyResp initChargeResultNotifyResp(XunyuanChargeResp chargeResp) {
		XunyuanChargeResultNotifyResp resp = new XunyuanChargeResultNotifyResp();
		resp.setOrderId(chargeResp.getOrderId());
		resp.setStoreSeq(chargeResp.getStoreSeq());
		save(resp);
		return resp;
	}
	
	@Override
	public XunyuanChargeResultNotifyResp updateChargeResultNotifyResp(XunyuanChargeResultNotifyResp resp,ChargeResultNotifyPo_Rp chargeResultNotifyPoRp) {
		resp.setChargeResultRespCode(chargeResultNotifyPoRp.getRespCode());
		resp.setChargeTime(chargeResultNotifyPoRp.getChargeTime());
		resp.setRecSeq(chargeResultNotifyPoRp.getXunyuanSeq());
		resp.setStoreSeq(chargeResultNotifyPoRp.getStoreSeq());
		update(resp);
		return resp;
	}
	
	@Override
	public XunyuanChargeReq initChargeReq(Mainorder mainorder) {
		XunyuanChargeReq xunyuanChargeReq = new XunyuanChargeReq();
		String amount = SysUtil.formatDouble(mainorder.getGoodsValue()*100, "0000000000");
		Goods goods = goodsService.findById(mainorder.getGoods().getGoodsId());
		String tradeType = "";
		switch (goods.getGoodsFlag()) {
		//移动
		case 10:
			if (goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300) {
				//广东
				tradeType = "999999";
			}else {
				//全国
				tradeType = "999991";
			}
			break;
		//联通
		case 12:
			if (goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300) {
				tradeType = "999998";
			}else {
				tradeType = "999992";
			}
			
			break;
		//电信
		case 11:
			if (goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300) {
				tradeType = "000001";
			}else {
				tradeType = "999993";
			}
			break;
		//电信单一交费
		case 30:
			tradeType = "000001";
			break;
		//电信宽带交费
		case 31:
			tradeType = "000003";
			break;
		//电信综合交费
		case 32:
			tradeType = "000004";
		default:
			break;
		}
		xunyuanChargeReq.setTradeType(tradeType);
		xunyuanChargeReq.setAmount(amount);
		xunyuanChargeReq.setCreateTime(new Date());
		xunyuanChargeReq.setMobile(mainorder.getMobile());
		String storeSeq = System.currentTimeMillis()+RandomUtils.getVerificationCode(7);//SysUtil.formatLong(System.currentTimeMillis(), "00000000000000000000");
		xunyuanChargeReq.setStoreSeq(storeSeq);
		xunyuanChargeReq.setOrderId(mainorder.getMainOrderId());
		this.save(xunyuanChargeReq);
		return xunyuanChargeReq;
	}
	
	@Override
	public XunyuanChargeResp initChargeResp(XunyuanChargeReq xunyuanChargeReq) {
		XunyuanChargeResp xunyuanChargeResp = new XunyuanChargeResp();
		xunyuanChargeResp.setOrderId(xunyuanChargeReq.getOrderId());
		xunyuanChargeResp.setStoreSeq(xunyuanChargeReq.getStoreSeq());
		this.save(xunyuanChargeResp);
		return xunyuanChargeResp;
	}

	@Override
	public void updateXunYuanChargeResp(XunyuanChargeResp xunyuanChargeResp,ChargePo_Rp chargePoRp) {
		xunyuanChargeResp.setcBalance(chargePoRp.getBalance());
		xunyuanChargeResp.setChargeRespCode(chargePoRp.getRespCode());
		xunyuanChargeResp.setChargeTime(chargePoRp.getChargeTime());
		xunyuanChargeResp.setRecSeq(chargePoRp.getXunyuanSeq());
		xunyuanChargeResp.setSpBalance(chargePoRp.getCorpBalance());
		xunyuanChargeResp.setStoreSeq(chargePoRp.getStoreSeq());
		this.update(xunyuanChargeResp);
	}

	@Override
	public XunyuanChargeResp findChargeRespByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanChargeResp.class);
		dec.add(Restrictions.eq("orderId", orderId));
		List<XunyuanChargeResp> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public XunyuanChargeResp findChargeRespByStoreSeq(String storeSeq) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanChargeResp.class);
		dec.add(Restrictions.eq("storeSeq", storeSeq));
		List<XunyuanChargeResp> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public XunyuanQueryReq initXunyuanQueryReq(Mainorder mainorder, XunyuanChargeResp resp) {
		XunyuanQueryReq xunyuanQueryReq = new XunyuanQueryReq();
		String amount = SysUtil.formatDouble(mainorder.getGoodsValue()*100, "0000000000");
		xunyuanQueryReq.setAmount(amount);
		xunyuanQueryReq.setMobile(mainorder.getMobile());
		xunyuanQueryReq.setQueryStoreSeq(resp.getStoreSeq());
		String storeSeq = SysUtil.formatLong(System.currentTimeMillis(), "00000000000000000000");
		xunyuanQueryReq.setStoreSeq(storeSeq);
		xunyuanQueryReq.setCreateTime(new Date());
		Goods goods = goodsService.findById(mainorder.getGoods().getGoodsId());
		String tradeType = "";
		switch (goods.getGoodsFlag()) {
		//移动
		case 10:
			if (goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300) {
				//广东
				tradeType = "999999";
			}else {
				//全国
				tradeType = "999991";
			}
			break;
		//联通
		case 12:
			if (goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300) {
				tradeType = "999998";
			}else {
				tradeType = "999992";
			}
			
			break;
		//电信
		case 11:
			if (goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300) {
				tradeType = "000001";
			}else {
				tradeType = "999993";
			}
			break;
		//电信单一交费
		case 30:
			tradeType = "000001";
			break;
		//电信宽带交费
		case 31:
			tradeType = "000003";
			break;
		//电信综合交费
		case 32:
			tradeType = "000004";
		default:
			break;
		}
		xunyuanQueryReq.setTradeType(tradeType);
		xunyuanQueryReq.setOrderId(mainorder.getMainOrderId());
		xunyuanQueryReq.setLogFor(1);
		this.save(xunyuanQueryReq);
		return xunyuanQueryReq;
	}
	
//	@Override
//	public XunyuanQueryReq initXunyuanQueryReq(Mainorder mainorder, XunyuanReverseResp resp) {
//		XunyuanQueryReq xunyuanQueryReq = new XunyuanQueryReq();
//		String amount = SysUtil.formatDouble(mainorder.getGoodsValue()*100, "0000000000");
//		xunyuanQueryReq.setAmount(amount);
//		xunyuanQueryReq.setMobile(mainorder.getMobile());
//		xunyuanQueryReq.setQueryStoreSeq(resp.getReverseSeq());
//		String storeSeq = SysUtil.formatLong(System.currentTimeMillis(), "00000000000000000000");
//		xunyuanQueryReq.setStoreSeq(storeSeq);
//		xunyuanQueryReq.setCreateTime(new Date());
//		Goods goods = goodsService.findById(mainorder.getGoods().getGoodsId());
//		String tradeType = "";
//		switch (goods.getGoodsFlag()) {
//		case 10:
//			tradeType = "999999";
//			break;
//		case 12:
//			tradeType = "999998";
//			break;
//		case 11:
//			tradeType = "000001";
//			break;
//		default:
//			break;
//		}
//		xunyuanQueryReq.setTradeType(tradeType);
//		xunyuanQueryReq.setOrderId(mainorder.getMainOrderId());
//		xunyuanQueryReq.setLogFor(2);
//		this.save(xunyuanQueryReq);
//		return xunyuanQueryReq;
//	}
	
	@Override
	public XunyuanQueryResp initXunyuanQueryResp(XunyuanQueryReq xunyuanQueryReq, Mainorder mainorder) {
		XunyuanQueryResp xunyuanQueryResp = new XunyuanQueryResp();
		xunyuanQueryResp.setOrderId(mainorder.getMainOrderId());
		xunyuanQueryResp.setStoreSeq(xunyuanQueryReq.getStoreSeq());
		xunyuanQueryResp.setLogFor(xunyuanQueryReq.getLogFor());
		this.save(xunyuanQueryResp);
		return xunyuanQueryResp;
	}

	@Override
	public XunyuanQueryResp updateXunyuanQueryResp(XunyuanQueryResp xunyuanQueryResp,
			QueryPo_Rp queryPoRp) {
		xunyuanQueryResp.setQueryRespCode(queryPoRp.getRespCode());
		xunyuanQueryResp.setcBalance(queryPoRp.getBalance());
		xunyuanQueryResp.setTradeTime(queryPoRp.getTradeTime());
		xunyuanQueryResp.setRecSeq(queryPoRp.getXunyuanSeq());
		xunyuanQueryResp.setSpBalance(queryPoRp.getCorpBalance());
		this.update(xunyuanQueryResp);
		return xunyuanQueryResp;
	}

	@Override
	public XunyuanQueryReq findQueryReqByOrderId(String orderId, int logFor) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanQueryReq.class);
		dec.add(Restrictions.eq("orderId", orderId));
		dec.add(Restrictions.eq("logFor", logFor));
		List<XunyuanQueryReq> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public XunyuanQueryResp findQueryRespByStoreSeq(String storeSeq) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanQueryResp.class);
		dec.add(Restrictions.eq("storeSeq", storeSeq));
		List<XunyuanQueryResp> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public XunyuanQueryResp findQueryRespByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanQueryResp.class);
		dec.add(Restrictions.eq("orderId", orderId));
		List<XunyuanQueryResp> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int refreshOrderState(Mainorder mainorder,XunyuanQueryResp resp) {
		int result = Const.OperationLogResult.NONE_PROCESS;
		Users user=userService.findUserById(mainorder.getUsers().getUserId());
		if (resp.getLogFor() == 1) {
			if (resp.getQueryRespCode() != null) {
				if (resp.getQueryRespCode().equals("0000")) {
					//处理成功
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
					DecimalFormat df5 = new DecimalFormat("#.00");
					double cBalance = Double.parseDouble(resp.getcBalance())/100;
					orderService.updateCBalance(mainorder, Double.parseDouble(df5.format(cBalance)));
					operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
//					SupplyKernel.getInstance().purchaseBalance(mainorder);
//					useraccountService.purchaseAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//							mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
					result = Const.OperationLogResult.SUCCESS;
					// 更新供货商对应商品错误次数
//					SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//					SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, 0);
					//如果是批量单,刷新总单
					if (mainorder.getIsBatch()==1) {
						orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
						BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
						//orderService.updateBatchOrder(batchOrder);
						com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
						RefreshBatchOrderEngine.getInstance().addLast(iCharge);
					}
				}else if (resp.getQueryRespCode().equals("3027")) {
					//处理中
//					mainorderDao.updateRefreshTime(System.currentTimeMillis()+25000, mainorder.getMainOrderId());
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
					result = Const.OperationLogResult.PROCESSING;
				}else if (resp.getQueryRespCode().equals("3028")) {
					//没有找到订单
//					orderService.updateOrderRespTime(mainorder);
//					mainorderDao.updateRefreshTime(System.currentTimeMillis()+25000, mainorder.getMainOrderId());
					//处理中,继续刷
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
					result = Const.OperationLogResult.PROCESSING;
				}else if (resp.getQueryRespCode().equals("3002")) {
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
					result = Const.OperationLogResult.PROCESSING;
				}
				else if (resp.getQueryRespCode().equals("3024")) {
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
					result = Const.OperationLogResult.PROCESSING;
				}else {
					//失败
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_FAILED);
					//销售商品返回未销售状态
					if (mainorder.getCardLibIds()!=null) {
						cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
					}
					if (mainorder.getMoneyBack().equals(1)) {
						operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.FAILED,null);
						//商户自动返还额度
//						useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
						//供货商自动返还额度
						Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
//						SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
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
//					SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//					SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, supplyInterface.getFailedCount()+1);
//					//连续出现5次错误就禁用
//					if (supplyInterface.getFailedCount()>5 && supplyInterface.getFailedCount()<10) {
//						//SupplyKernel.getInstance().updateSupplyInterfaceState(String.valueOf(supplyInterface.getId()), 0);
//						String[] mobiles = new String[]{"13560192965"};
//						String content = "你好,"+supplyInterface.getSupplyName()+supplyInterface.getGoodsName()+"已经连续5次返回失败,请登陆网站查看!【妙明在线】";
//						SDKClientUtil.SendSMS(mobiles, content, "", 0);
//					}
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
			}
		
		}
		return result;
	}
	
	@Override
	public int refreshOrderState(Mainorder mainorder,XunyuanChargeResp resp){
		int result = Const.OperationLogResult.NONE_PROCESS;
		Users user=userService.findUserById(mainorder.getUsers().getUserId());
		if (resp.getChargeRespCode() != null) {
			if (resp.getChargeRespCode().equals("0000")) {
				//处理成功
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
				DecimalFormat df5 = new DecimalFormat("#.00");
				double cBalance = Double.parseDouble(resp.getcBalance())/100;
				orderService.updateCBalance(mainorder, Double.parseDouble(df5.format(cBalance)));
				operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
//					SupplyKernel.getInstance().purchaseBalance(mainorder);
//					useraccountService.purchaseAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//							mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
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
			}else if (resp.getChargeRespCode().equals("0001")) {
				//下单成功，等通知
				XunyuanChargeResultNotifyResp chargeResultNotifyResp = findChargeResultNotifyRespByOrderId(mainorder.getMainOrderId());
				if (chargeResultNotifyResp.getChargeResultRespCode()!=null) {
					if (chargeResultNotifyResp.getChargeResultRespCode().equals("0000")) {
						//处理成功
						orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
						DecimalFormat df5 = new DecimalFormat("#.00");
						double cBalance = Double.parseDouble(resp.getcBalance())/100;
						orderService.updateCBalance(mainorder, Double.parseDouble(df5.format(cBalance)));
						operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
//							SupplyKernel.getInstance().purchaseBalance(mainorder);
//							useraccountService.purchaseAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//									mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
						result = Const.OperationLogResult.SUCCESS;
						// 更新供货商对应商品错误次数
//						SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//						SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, 0);
						//如果是批量单,刷新总单
						if (mainorder.getIsBatch()==1) {
							orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
							BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
							//orderService.updateBatchOrder(batchOrder);
							com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
							RefreshBatchOrderEngine.getInstance().addLast(iCharge);
						}
					}else if (chargeResultNotifyResp.getChargeResultRespCode().equals("3001")) {
						//失败
						orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_FAILED);
						//销售商品返回未销售状态
						if (mainorder.getCardLibIds()!=null) {
							cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
						}
						if (mainorder.getMoneyBack().equals(1)) {
							operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.FAILED,null);
							//商户自动返还额度
							//useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
							//供货商自动返还额度
							Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
							//SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
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
//						SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
//						SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface, supplyInterface.getFailedCount()+1);
//						//连续出现5次错误就禁用
//						if (supplyInterface.getFailedCount()>5 && supplyInterface.getFailedCount()<10) {
//							//SupplyKernel.getInstance().updateSupplyInterfaceState(String.valueOf(supplyInterface.getId()), 0);
//							String[] mobiles = new String[]{"13560192965"};
//							String content = "你好,"+supplyInterface.getSupplyName()+supplyInterface.getGoodsName()+"已经连续5次返回失败,请登陆网站查看!【妙明在线】";
//							SDKClientUtil.SendSMS(mobiles, content, "", 0);
//						}
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
				}
			}else {
				//失败
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
					ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
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
		}
		return result;
	}
	
	@Override
	public int refreshOrderState(Mainorder mainorder,XunyuanReverseResp resp){
		int result = Const.OperationLogResult.NONE_PROCESS;
		Users user=userService.findUserById(mainorder.getUsers().getUserId());
		if (resp.getReverseRespCode() != null) {
			if (resp.getReverseRespCode().equals("0000") || resp.getReverseRespCode().equals("3026")) {
				//处理成功
				//成功
				//冲正次数-1
				user.setReversalCount(user.getReversalCount()-1);
				userService.update(user);
				
				//订单状态为冲正
				orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_SUCCESS);
				if (mainorder.getMoneyBack().equals(1)) {
					operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.SUCCESS,null);
					//商户自动返还额度
					//useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.REVERSAL);
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
			}else {
				//失败
				//订单状态为冲正失败
				orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_FAILED);
				operationLogService.addOperationLog(user,mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.FAILED,null);
				result = Const.OperationLogResult.FAILED;
			}
		}
		
		return result;
	}

	@Override
	public XunyuanReverseReq initReverseReq(Mainorder mainorder) {
		XunyuanReverseReq req = new XunyuanReverseReq();
		String amount = SysUtil.formatDouble(mainorder.getGoodsValue()*100, "0000000000");
		XunyuanChargeResp chargeResp = findChargeRespByOrderId(mainorder.getMainOrderId());
		req.setReverseSeq(chargeResp.getStoreSeq());
		req.setAmount(amount);
		req.setCreateTime(new Date());
		req.setMobile(mainorder.getMobile());
		req.setOrderId(mainorder.getMainOrderId());
		String storeSeq = SysUtil.formatLong(System.currentTimeMillis(), "00000000000000000000");
		req.setStoreSeq(storeSeq);
		this.save(req);
		return req;
	}

	@Override
	public XunyuanReverseResp initReverseResp(
			XunyuanReverseReq xunyuanReverseReq) {
		XunyuanReverseResp resp = new XunyuanReverseResp();
		resp.setOrderId(xunyuanReverseReq.getOrderId());
		resp.setStoreSeq(xunyuanReverseReq.getStoreSeq());
		resp.setReverseSeq(xunyuanReverseReq.getReverseSeq());
		this.save(resp);
		return resp;
	}

	@Override
	public void updateXunyuanReverseResp(XunyuanReverseResp xunyuanReverseResp,
			ReversePo_Rp reversePoRp) {
		xunyuanReverseResp.setRecSeq(reversePoRp.getXunyuanSeq());
		xunyuanReverseResp.setReverseRespCode(reversePoRp.getRespCode());
		xunyuanReverseResp.setReverseTime(reversePoRp.getReverseTime());
		this.update(xunyuanReverseResp);
	}
	
	@Override
	public XunyuanReverseResp findReverseRespByStoreSeq(String storeSeq) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanReverseResp.class);
		dec.add(Restrictions.eq("storeSeq", storeSeq));
		List<XunyuanReverseResp> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public XunyuanReverseResp findReverseRespByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanReverseResp.class);
		dec.add(Restrictions.eq("orderId", orderId));
		List<XunyuanReverseResp> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public XunyuanQueryBalanceReq initQueryBalanceReq(String mobile,int tradeType) {
		XunyuanQueryBalanceReq req = new XunyuanQueryBalanceReq();
		req.setCreateTime(new Date());
		req.setMobile(mobile);
		String tradeTypeString = SysUtil.formatInt(tradeType, "000000");
		req.setTradeType(tradeTypeString);
		String storeSeq = SysUtil.formatLong(System.currentTimeMillis(), "00000000000000000000");
		req.setStoreSeq(storeSeq);
		this.save(req);
		return req;
	}
	
	@Override
	public XunyuanQueryBalanceResp initQueryBalanceResp(QueryBalancePo_Rp rp) {
		XunyuanQueryBalanceResp resp = new XunyuanQueryBalanceResp();
		resp.setcBalance(rp.getBalance());
		resp.setcName(rp.getcName());
		resp.setPayType(rp.getPayType());
		resp.setStoreSeq(rp.getStoreSeq());
		XunyuanQueryBalanceReq req = this.findQueryBalanceReqByStoreSeq(rp.getStoreSeq());
		resp.setMobile(req.getMobile());
		resp.setRespCode(rp.getRespCode());
		this.save(resp);
		return resp;
	}
	
	@Override
	public XunyuanQueryBalanceReq findQueryBalanceReqByStoreSeq(String storeSeq) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanQueryBalanceReq.class);
		dec.add(Restrictions.eq("storeSeq", storeSeq));
		List<XunyuanQueryBalanceReq> list = this.findByDetachedCriteria(dec);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public XunyuanQueryBalanceResp findQueryBalanceRespByStoreSeq(String storeSeq) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanQueryBalanceResp.class);
		dec.add(Restrictions.eq("storeSeq", storeSeq));
		List<XunyuanQueryBalanceResp> list = this.findByDetachedCriteria(dec);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public XunyuanChargeResultNotifyResp findChargeResultNotifyRespByStoreSeq(String storeSeq) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanChargeResultNotifyResp.class);
		dec.add(Restrictions.eq("storeSeq", storeSeq));
		List<XunyuanChargeResultNotifyResp> list = this.findByDetachedCriteria(dec);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public XunyuanChargeResultNotifyResp findChargeResultNotifyRespByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanChargeResultNotifyResp.class);
		dec.add(Restrictions.eq("orderId", orderId));
		List<XunyuanChargeResultNotifyResp> list = this.findByDetachedCriteria(dec);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}

package com.sys.volunteer.ka;

import java.io.UnsupportedEncodingException;
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
import com.sys.volunteer.common.KAUtil;
import com.sys.volunteer.common.MD5;
import com.sys.volunteer.common.MD5Ex;
import com.sys.volunteer.common.RandomUtils;
import com.sys.volunteer.common.StringUtil;
import com.sys.volunteer.common.UtilDate;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.GameRechargeRequest;
import com.sys.volunteer.pojo.GameRechargeResponse;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Query91KAOrder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

@Service
@Transactional
public class KaServiceBean extends CommonDao implements KaService {

	protected Log logger = LogFactory.getLog(this.getClass());

	@Resource
	OrderService orderService;
	@Resource
	OperationLogService operationLogService;
	@Resource
	SupplyService supplyService;
	@Resource
	CardLibService cardLibService;
	@Resource
	UserService userService;

	@Override
	public GameRechargeRequest addGameRechargeRequest(
			GameRechargeRequest request, Mainorder mainorder,
			SupplyInterface supplyInterface, String at, String gameId,
			String autoGameId, String atVerify, String clientIp) {
		request.setAt(at);
		request.setGameId(gameId);
		request.setAutogameId(autoGameId);
		request.setAtVerify(atVerify);
		request.setClientIp(clientIp);
		request.setOrderId(mainorder.getMainOrderId());
		request.setCpOrderNo(System.currentTimeMillis()+RandomUtils.getVerificationCode(6));
		String date = DateUtil.DateToString(new Date(), UtilDate.dtLong);
		request.setCreateTime(date);
		request.setVersion("2.10");
		// TODO:单价*数量
		request.setAmount((int) (supplyInterface.getStockPrice()
				* mainorder.getGoodsNo() * 10000)
				+ "");
		// TODO:商户
		request.setCpid(KAUtil.cpid);
		request.setRetPara("");
		String para = "cpid=" + request.getCpid() + "&game_id="
				+ request.getGameId() + "&autogame_id="
				+ request.getAutogameId() + "&create_time="
				+ request.getCreateTime() + "&version=" + request.getVersion()
				+ "&cp_order_no=" + request.getCpOrderNo() + "&amount="
				+ request.getAmount() + "&" + request.getAt() + "ret_para="
				+ request.getRetPara() + "&client_ip=" + request.getClientIp();
		logger.info("91ka充值参数"+para);
		String parakey;
		parakey = para + KAUtil.MD5KEY;
		logger.info("91ka充值参数+key"+parakey);
		//MD5 md5 = MD5.getiInstance();
		//这里必须用MD5Ex,可能跟MD5类的算法不一样
		String sign = MD5Ex.getMD5Str(parakey,"gb2312");
		logger.info("91ka充值参数sign"+sign);
		//logger.info("91ka测试...."+md5.getMD5ofStr("第三方斯蒂芬"));
		request.setSign(sign);
		save(request);
		return request;
	}
	
	@Override
	public Query91KAOrder initQueryOrder(GameRechargeRequest request) {
		Query91KAOrder query = new Query91KAOrder();
		query.setCpid(request.getCpid());
		query.setCpOrderNo(request.getCpOrderNo());
		query.setOrderId(request.getOrderId());
		save(query);
		return query;
	}

	@Override
	public GameRechargeRequest findRequestByCpOrderNo(String cpOrderNo) {
		DetachedCriteria dec = DetachedCriteria
				.forClass(GameRechargeRequest.class);
		dec.add(Restrictions.eq("cpOrderNo", cpOrderNo));
		List<GameRechargeRequest> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public GameRechargeRequest findRequestByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria
				.forClass(GameRechargeRequest.class);
		dec.add(Restrictions.eq("orderId", orderId));
		List<GameRechargeRequest> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int refreshGameRechargeOrder(Mainorder mainorder) {
		GameRechargeResponse response = findqGameRechargeResponseByOrderId(mainorder.getMainOrderId());
		Users user = userService.findUserById(mainorder.getUsers().getUserId());
		if (response == null) {
			//query = new Query91KAOrder();
			logger.error("91ka直充没有找到对应response!订单号:"+mainorder.getMainOrderId());
			return Const.OperationLogResult.PROCESSING;
		}else {
			if (response.getRetResult().equals("9000")) {
				//充值中,需查询订单结果
				Query91KAOrder query = findqQuery91kaOrderByOrderId(mainorder
						.getMainOrderId());
				if (query == null) {
					//query = new Query91KAOrder();
					logger.error("91ka直充没有找到对应query!订单号:"+mainorder.getMainOrderId());
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
					return Const.OperationLogResult.PROCESSING;
				}
				
				String backUrl = KAUtil.queryOrder(query);
				if (backUrl == null || backUrl.equals("")) {
					logger.error("91ka直充返回空参数,订单号" + mainorder.getMainOrderId());
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
					return Const.OperationLogResult.PROCESSING;
				}
				query = query.init(backUrl);
				update(query);
				if (query.getRetResult() == null || query.getRetResult().equals("")) {
					// 没有返回结果,处理中
					logger.error("91ka直充返回空retResult,订单号" + mainorder.getMainOrderId());
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
					return Const.OperationLogResult.PROCESSING;
				}
				if (query.getRetResult().equals("9001")
						|| query.getRetResult().equals("9999")) {
					// 成功
					orderService.updateOrderState(mainorder,
							Const.MainOrderState.PROCESS_SUCCESS);
					operationLogService.addOperationLog(user, mainorder, "",
							Const.OperationLogType.MOBILE_RECHARGE,
							Const.OperationLogResult.SUCCESS, null);
					logger.info("91ka" + mainorder.getMainOrderId() + "充值成功");
					return Const.OperationLogResult.SUCCESS;
				} else if (query.getRetResult().equals("9000")
						|| query.getRetResult().equals("1000")
						|| query.getRetResult().equals("8888")) {
					// 充值中
					logger.info("91ka" + mainorder.getMainOrderId() + "充值中");
					orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
					return Const.OperationLogResult.PROCESSING;
				} else {
					// 失败
					orderService.updateOrderState(mainorder,
							Const.MainOrderState.PROCESS_FAILED);
					// 销售商品返回未销售状态
					if (mainorder.getCardLibIds() != null) {
						cardLibService.updateCardLibState(mainorder.getCardLibIds(), 1);
					}
					if (mainorder.getMoneyBack().equals(1)) {
						operationLogService.addOperationLog(user, mainorder, "",
								Const.OperationLogType.MOBILE_RECHARGE,
								Const.OperationLogResult.FAILED, null);
						// 商户自动返还额度
						// useraccountService.refreshAccountes(mainorder,user,null,null,new
						// UserCharge(),
						// mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
						// 供货商自动返还额度
						System.out.println("++++++++++++++++++++++++++++++"
								+ mainorder.getSupply().getId());
						Supply supply = supplyService.findSupplyById(mainorder
								.getSupply().getId());
						System.out.println("=================================="
								+ supply);
						// 转换冲正的卡密状态
						// cardLibService.reverseCardLib(mainorder.getCardLibIds());
						// SupplyKernel.getInstance().cancelBalance(mainorder,supply,mainorder.getStockPrice()*mainorder.getGoodsNo(),
						// new UserCharge());
						ICharge iCharge = new Charge4User(new UserCharge(), mainorder,
								mainorder.getTotalMoney(), user, null, null,
								Const.UseraccountdealdetailFlag.USER_CANCEL);
						SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
						ICharge iCharge2 = new Charge4Supply(new UserCharge(),
								mainorder, supplyInterface.getStockPrice()
										* mainorder.getGoodsNo(), (Supply) supply,
								null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
						UserChargeEngine.getInstance().addLast(iCharge);
						UserChargeEngine.getInstance().addLast(iCharge2);
						// 订单回水标记0
						orderService.updateMoneyBack(mainorder, 0);
						return Const.OperationLogResult.FAILED;
					}

					// 更新供货商对应商品错误次数
					// SupplyInterface supplyInterface =
					// SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
					// SupplyKernel.getInstance().updateSupplyInterfaceFailedCount(supplyInterface,
					// supplyInterface.getFailedCount()+1);
					// //连续出现5次错误就禁用
					// if (supplyInterface.getFailedCount()>5 &&
					// supplyInterface.getFailedCount()<10) {
					// //SupplyKernel.getInstance().updateSupplyInterfaceState(String.valueOf(supplyInterface.getId()),
					// 0);
					// String[] mobiles = new String[]{"13560192965"};
					// String content =
					// "你好,"+supplyInterface.getSupplyName()+supplyInterface.getGoodsName()+"已经连续5次返回失败,请登陆网站查看!【妙明在线】";
					// SDKClientUtil.SendSMS(mobiles, content, "", 0);
					// }
				}
			}else if (response.getRetResult().equals("9001")) {
				// 成功
				orderService.updateOrderState(mainorder,
						Const.MainOrderState.PROCESS_SUCCESS);
				operationLogService.addOperationLog(user, mainorder, "",
						Const.OperationLogType.MOBILE_RECHARGE,
						Const.OperationLogResult.SUCCESS, null);
				logger.info("91ka" + mainorder.getMainOrderId() + "充值成功");
				return Const.OperationLogResult.SUCCESS;
			}else {
				// 失败
				orderService.updateOrderState(mainorder,
						Const.MainOrderState.PROCESS_FAILED);
				// 销售商品返回未销售状态
				if (mainorder.getCardLibIds() != null) {
					cardLibService.updateCardLibState(mainorder.getCardLibIds(), 1);
				}
				if (mainorder.getMoneyBack().equals(1)) {
					operationLogService.addOperationLog(user, mainorder, "",
							Const.OperationLogType.MOBILE_RECHARGE,
							Const.OperationLogResult.FAILED, null);
					// 商户自动返还额度
					// useraccountService.refreshAccountes(mainorder,user,null,null,new
					// UserCharge(),
					// mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
					// 供货商自动返还额度
					System.out.println("++++++++++++++++++++++++++++++"
							+ mainorder.getSupply().getId());
					Supply supply = supplyService.findSupplyById(mainorder
							.getSupply().getId());
					System.out.println("=================================="
							+ supply);
					// 转换冲正的卡密状态
					// cardLibService.reverseCardLib(mainorder.getCardLibIds());
					// SupplyKernel.getInstance().cancelBalance(mainorder,supply,mainorder.getStockPrice()*mainorder.getGoodsNo(),
					// new UserCharge());
					ICharge iCharge = new Charge4User(new UserCharge(), mainorder,
							mainorder.getTotalMoney(), user, null, null,
							Const.UseraccountdealdetailFlag.USER_CANCEL);
					SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
					ICharge iCharge2 = new Charge4Supply(new UserCharge(),
							mainorder, supplyInterface.getStockPrice()
									* mainorder.getGoodsNo(), (Supply) supply,
							null, null, Const.UseraccountdealdetailFlag.USER_CANCEL);
					UserChargeEngine.getInstance().addLast(iCharge);
					UserChargeEngine.getInstance().addLast(iCharge2);
					// 订单回水标记0
					orderService.updateMoneyBack(mainorder, 0);
					return Const.OperationLogResult.FAILED;
				}
			}
		}
		return Const.OperationLogResult.FAILED;
	}

	@Override
	public Query91KAOrder findqQuery91kaOrderByCpOrderNo(String cpOrderNo) {
		DetachedCriteria dec = DetachedCriteria.forClass(Query91KAOrder.class);
		dec.add(Restrictions.eq("cpOrderNo", cpOrderNo));
		List<Query91KAOrder> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Query91KAOrder findqQuery91kaOrderByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(Query91KAOrder.class);
		dec.add(Restrictions.eq("orderId", orderId));
		List<Query91KAOrder> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public GameRechargeResponse findqGameRechargeResponseByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(GameRechargeResponse.class);
		dec.add(Restrictions.eq("orderId", orderId));
		List<GameRechargeResponse> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}

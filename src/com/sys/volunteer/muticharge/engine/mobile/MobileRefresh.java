package com.sys.volunteer.muticharge.engine.mobile;

import org.springframework.context.ApplicationContext;

import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.SDKClientUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.jdbc.dao.orderDao.MainorderDao;
import com.sys.volunteer.muticharge.ChargeMutithreadManager;
import com.sys.volunteer.muticharge.engine.BaseCharge;
import com.sys.volunteer.muticharge.engine.BaseRefresh;
import com.sys.volunteer.muticharge.engine.OrderVO;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.supplyinterface.SupplyInterfaceService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

public class MobileRefresh extends BaseRefresh {
	
	private static OrderService orderService;
	private static GoodsService goodsService;
	private static SupplyInterfaceService supplyInterfaceService;
	private static UserService userService;
	private static UseraccountService useraccountService;
	private static CardLibService cardLibService;
	private static OperationLogService operationLogService;
	private static SupplyService supplyService;
	
	public MobileRefresh(Integer supplyInterfaceId, Integer supplyId,
			String mainorderId,OrderVO orderVO,EnumOrderState state) {
		super(supplyInterfaceId, supplyId, mainorderId,orderVO,state);
	}
	

	/**
	 * 刷新订单
	 */
	@Override
	public int refreshOrder(OrderVO orderVO) {
		logger.info("这里刷新"+getMainorderId()+"订单...");
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		orderService = (OrderService)act.getBean("orderServiceBean");
		userService = (UserService)act.getBean("userServiceBean");
		cardLibService = (CardLibService)act.getBean("cardLibServiceBean");
		supplyService = (SupplyService)act.getBean("supplyServiceBean");
		operationLogService = (OperationLogService)act.getBean("operationLogServiceBean");
		useraccountService = (UseraccountService)act.getBean("useraccountServiceBean");
		int result = 0;
		ISupply supply = SupplyKernel.getInstance().getSupplyMapKeyId().get(orderVO.getSupplyId());
		Mainorder mainorder;
		try {
			mainorder = orderService.findOrderById(orderVO.getMainOrderId());
			
			result = supply.refreshState(mainorder);
			
			if (result == -1 || result == 0) {
				//处理结果为没有处理
				orderService.updateOrderRefreshTime(mainorder, 25000);
				orderService.updateOrderRespTime(mainorder);
			}
			logger.info("处理完成...."+getMainorderId()+"处理结果是:"+result);
			//查看订单状态
			mainorder = orderService.findOrderById(orderVO.getMainOrderId());
			logger.info(mainorder.getMainOrderId()+"订单当前状态是"+mainorder.getState());
			if (mainorder.getState()==5) {
				orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESSING);
			}
			if (mainorder.getReversalState()==5) {
				orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESSING);
			}
			// pos机处理失败需要重新落单
			if (mainorder.getIsTerminal() != null && mainorder.getIsTerminal().equals(1) && result == Const.OperationLogResult.FAILED) {
				logger.info("处理失败,pos机重新下单");
				
				goodsService = (GoodsService) act.getBean("goodsServiceBean");
				supplyInterfaceService = (SupplyInterfaceService) act.getBean("supplyInterfaceServiceBean");
				useraccountService = (UseraccountService) act.getBean("useraccountServiceBean");
				userService = (UserService) act.getBean("userServiceBean");
				String lastSupplyIds = "";
				if (mainorder.getLastSupplyIds()!= null && !mainorder.getLastSupplyIds().equals("")) {
					lastSupplyIds = mainorder.getLastSupplyIds()+","+mainorder.getSupply().getId();
				}else {
					lastSupplyIds = mainorder.getSupply().getId()+"";
				}
				Goods goods = goodsService.findById(mainorder.getGoods().getGoodsId());
				SupplyKernel supplyKernel = SupplyKernel.getInstance();
				supply = supplyKernel.getISupply(supplyKernel
						.getListBySellType(goods,lastSupplyIds,3), 1, mainorder.getGoodsValue());
				//如果没有找到供货商,则需要人工用卡号充值
				if (supply == null) {
					//这里需要刷新一次(因为这里的定单状态未刷新)
					mainorder = orderService.findOrderById(orderVO.getMainOrderId());
					mainorder.setIsNeedManual(1);
					orderService.update(mainorder);
					return result;
				}
				SupplyInterface supplyInterface = supplyInterfaceService.findBySupplyAndGoods(supply.getId(), goods.getGoodsId());
				Mainorder newMainorder = orderService.addMainOrderRecharge(mainorder, supplyInterface);
				orderService.addOrderDetail(new Orderdetail(), newMainorder, null);
				Users user = userService.findUserById(newMainorder.getUsers().getUserId());
				//扣费
				ICharge iCharge = new Charge4User(new UserCharge(), newMainorder, newMainorder.getTotalMoney(), user, null, newMainorder.getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
				ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, newMainorder.getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
				UserChargeEngine.getInstance().addLast(iCharge);
				UserChargeEngine.getInstance().addLast(iCharge2);
				//supplyKernel.purchaseBalance(newMainorder);
				//记录操作员(2012.10.24)
				//Users user = userService.findUserById(newMainorder.getUsers().getUserId());
				//useraccountService.refreshAccountes(newMainorder,user,null,null,new UserCharge(),
				//		newMainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
}

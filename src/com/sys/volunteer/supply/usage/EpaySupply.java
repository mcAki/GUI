package com.sys.volunteer.supply.usage;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;

import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.SDKClientUtil;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.jdbc.dao.orderDao.MainorderDao;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.orderQuery.OrderQueryResponseNewService;
import com.sys.volunteer.pojo.AirDepositResponseNew;
import com.sys.volunteer.pojo.AirReversalResponseNew;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.OrderQueryResponseNew;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supplyinterface.SupplyInterfaceService;
import com.sys.volunteer.terminalAirDepositResponseNew.DepositResponseService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

public class EpaySupply extends Supply implements ISupply {

	private DepositResponseService depositResponseService;
	private OrderQueryResponseNewService orderQueryResponseNewService;
	private OrderService orderService;
	private CardLibService cardLibService;
	private OperationLogService operationLogService;
	private UseraccountService useraccountService;
	private UserService userService;
	private SupplyService supplyService;
	private static GoodsService goodsService;
	private static SupplyInterfaceService supplyInterfaceService;

	@Override
	public int recharge(Mainorder mainorder, String mobile, SupplyInterface supplyInterface) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		depositResponseService = (DepositResponseService) act
				.getBean("depositResponseServiceBean");
		orderQueryResponseNewService = (OrderQueryResponseNewService) act
				.getBean("orderQueryResponseNewServiceBean");
		orderService = (OrderService) act.getBean("orderServiceBean");
		cardLibService = (CardLibService) act.getBean("cardLibServiceBean");
		operationLogService = (OperationLogService) act.getBean("operationLogServiceBean");
		useraccountService = (UseraccountService) act.getBean("useraccountServiceBean");
		userService = (UserService) act.getBean("userServiceBean");
		supplyService = (SupplyService) act.getBean("supplyServiceBean");
		AirDepositResponseNew airDepositResponseNew = null;
		try {
			airDepositResponseNew = depositResponseService
					.addTerminalAirDepositResponseNew(mainorder, mobile, supplyInterface
							.getValue(), 0);
			//返回失败不查
			if (airDepositResponseNew != null && airDepositResponseNew.getRespCode().equals("0")) {
				orderQueryResponseNewService.initOrderQueryResponseNew(
						airDepositResponseNew, mainorder, mobile);
				return Const.OperationLogResult.SUCCESS;
			} else if (airDepositResponseNew != null && airDepositResponseNew.getRespCode().equals("-1")) {
				orderQueryResponseNewService.initOrderQueryResponseNew(
						airDepositResponseNew, mainorder, mobile);
				return Const.OperationLogResult.PROCESSING;
			} else {
				Users user = userService.findUserById(mainorder.getUsers().getUserId());
				// pos机处理失败需要重新落单
				if (mainorder.getIsTerminal() != null && mainorder.getIsTerminal().equals(1) ) {
					goodsService = (GoodsService) act.getBean("goodsServiceBean");
					supplyInterfaceService = (SupplyInterfaceService) act.getBean("supplyInterfaceServiceBean");
					String lastSupplyIds = "";
					if (mainorder.getLastSupplyIds()!= null && !mainorder.getLastSupplyIds().equals("")) {
						lastSupplyIds = mainorder.getLastSupplyIds()+","+mainorder.getSupply().getId();
					}else {
						lastSupplyIds = mainorder.getSupply().getId()+"";
					}
					Goods goods = goodsService.findById(mainorder.getGoods().getGoodsId());
					SupplyKernel supplyKernel = SupplyKernel.getInstance();
					ISupply supply2 = supplyKernel.getISupply(supplyKernel
							.getListBySellType(goods,lastSupplyIds,3), 1, mainorder.getGoodsValue());
					//如果没有找到供货商,则需要人工用卡号充值
					if (supply2 == null) {
						mainorder.setIsNeedManual(1);
						orderService.update(mainorder);
						return Const.OperationLogResult.FAILED;
					}
					supplyInterface = supplyInterfaceService.findBySupplyAndGoods(supply2.getId(), goods.getGoodsId());
					Mainorder newMainorder = orderService.addMainOrderRecharge(mainorder, supplyInterface);
					orderService.addOrderDetail(new Orderdetail(), newMainorder, null);
					//扣费
					ICharge iCharge = new Charge4User(new UserCharge(), newMainorder, newMainorder.getTotalMoney(), user, null, newMainorder.getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
					ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), this, null, newMainorder.getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
					UserChargeEngine.getInstance().addLast(iCharge);
					UserChargeEngine.getInstance().addLast(iCharge2);
//					supplyKernel.purchaseBalance(newMainorder);
//					//记录操作员(2012.10.24)
//					useraccountService.refreshAccountes(newMainorder,user,null,newMainorder.getUserName(),new UserCharge(),
//							newMainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
					return Const.OperationLogResult.PROCESSING;
				}
				
				
			}
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
		return Const.OperationLogResult.FAILED;
	}

	@Override
	public int reverse(Mainorder mainorder) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		depositResponseService = (DepositResponseService) act
				.getBean("depositResponseServiceBean");
		orderQueryResponseNewService = (OrderQueryResponseNewService) act
		.getBean("orderQueryResponseNewServiceBean");
		AirDepositResponseNew airDepositResponseNew = depositResponseService
				.finDepositResponseNewByOrderId(mainorder.getMainOrderId());
		if (null != airDepositResponseNew) {
			try {
				AirReversalResponseNew airReversalResponseNew = orderQueryResponseNewService.initAirReversalResponseNew
					(airDepositResponseNew.getMainorder().getMainOrderId(),airDepositResponseNew.getMobileNum(),
						airDepositResponseNew.getAmount(), airDepositResponseNew.getStoreSeq());
				//记录冲正返回数据
				if (airReversalResponseNew!=null) {
					orderQueryResponseNewService.initOrderQueryResponseNew(airReversalResponseNew, mainorder, airReversalResponseNew.getMobileNum());
					return Const.reverseResult.PROCESSING;
				}else {
					System.out.println("===================terminalAirReversalResponseNew is null!===================");
					return Const.reverseResult.FAILED;
				}
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
		}
		return Const.reverseResult.CONTACT_US;
	}

	@Override
	public int refreshState(Mainorder mainorder) throws Exception{
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		orderQueryResponseNewService = (OrderQueryResponseNewService) act
				.getBean("orderQueryResponseNewServiceBean");
		return orderQueryResponseNewService.refreshOrderState(mainorder);
	}
	
	@Override
	public void cancelOrder(Mainorder mainorder) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		orderQueryResponseNewService = (OrderQueryResponseNewService) act
		.getBean("orderQueryResponseNewServiceBean");
		DetachedCriteria dec = DetachedCriteria.forClass(OrderQueryResponseNew.class);
		dec.add(Restrictions.eq("orderId", mainorder.getMainOrderId()));
		dec.add(Restrictions.eq("isDeal", 0));
		dec.add(Restrictions.eq("logFor", 1));
		List<OrderQueryResponseNew> list = orderQueryResponseNewService.findByDetachedCriteria(dec);
		OrderQueryResponseNew orderQueryResponseNew = null;
		if (list.size()>0) {
			orderQueryResponseNew = list.get(0); 
		}
		if (orderQueryResponseNew != null) {
			orderQueryResponseNew.setIsDeal(1);
			orderQueryResponseNewService.update(orderQueryResponseNew);
		}
	}
}

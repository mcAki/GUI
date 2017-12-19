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
import com.sys.volunteer.users.UserService;

public class MobileCharge extends BaseCharge {
	
	private static OrderService orderService;
	private static GoodsService goodsService;
	private static SupplyInterfaceService supplyInterfaceService;
	private static UserService userService;
	private static UseraccountService useraccountService;
	private static CardLibService cardLibService;
	private static OperationLogService operationLogService;
	private static SupplyService supplyService;
	
	public MobileCharge(Integer supplyInterfaceId, Integer supplyId,
			String mainorderId,OrderVO orderVO,EnumOrderState state) {
		super(supplyInterfaceId, supplyId, mainorderId,orderVO,state);
	}
	
	/**
	 * 充值
	 * @param orderVO
	 */
	@Override
	public void recharge(OrderVO orderVO){
		logger.info("开始处理移动充值订单...");
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		orderService = (OrderService)act.getBean("orderServiceBean");
		
		ISupply supply = SupplyKernel.getInstance().getSupplyMapKeyId().get(orderVO.getSupplyId());
		SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(orderVO.getSupplyInterfaceId());
		Mainorder mainorder;
		try {
			mainorder = orderService.findOrderById(orderVO.getMainOrderId());
			
			logger.info("线程开始对应供货商进行充值");
			supply.recharge(mainorder, mainorder.getMobile(), supplyInterface);
			if (mainorder.getIsBatch() == 1) {
				//更新批量充值刷新时间
				orderService.updateOrderRefreshTime(mainorder, 30*1000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 冲正
	 * @param orderVO
	 */
	@Override
	public void reverse(OrderVO orderVO){
		logger.info("开始处理移动冲正订单...");
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		orderService = (OrderService)act.getBean("orderServiceBean");
		
		ISupply supply = SupplyKernel.getInstance().getSupplyMapKeyId().get(orderVO.getSupplyId());
		Mainorder mainorder;
		try {
			mainorder = orderService.findOrderById(orderVO.getMainOrderId());
			
			logger.info("线程开始对应供货商进行冲正");
			supply.reverse(mainorder);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

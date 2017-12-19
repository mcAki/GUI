package com.sys.volunteer.supply.usage;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.sys.volunteer.common.Const;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.order.RefreshBatchOrderEngine;
import com.sys.volunteer.order.engine.charge.BatchOrderRefresh;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.users.UserService;

public class TestSupply extends Supply implements ISupply {
	
	private static OrderService orderService;
	private static OperationLogService operationLogService;
	private static UseraccountService useraccountService;
	private static UserService userService;

	@Override
	public int recharge(Mainorder mainorder,String mobile,SupplyInterface supplyInterface) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		orderService = (OrderService)act.getBean("orderServiceBean");
		operationLogService = (OperationLogService) act.getBean("operationLogServiceBean");
		useraccountService = (UseraccountService) act.getBean("useraccountServiceBean");
		userService = (UserService) act.getBean("userServiceBean");
		
		List<Orderdetail> list=orderService.findOrderdetailsByOrderId(mainorder.getMainOrderId());
		//Users user=userService.findUserByMobile(orderQueryResponseNew.getUserMobileNum());
		Users user=userService.findUserById(mainorder.getUsers().getUserId());
		//订单状态为成功
		mainorder.setState(Const.MainOrderState.PROCESS_SUCCESS);
		orderService.update(mainorder);
		for (Orderdetail orderdetail : list) {
			orderdetail.setState(Const.MainOrderState.PROCESS_SUCCESS);
			orderService.update(orderdetail);
		}
		operationLogService.addOperationLog(user,mainorder, mobile,Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.SUCCESS,null);
		//如果是批量单,刷新总单
		if (mainorder.getIsBatch()==1) {
			orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
			BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
			//orderService.updateBatchOrder(batchOrder);
			com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
			RefreshBatchOrderEngine.getInstance().addLast(iCharge);
		}
		return Const.OperationLogResult.SUCCESS;
	}

	@Override
	public int reverse(Mainorder mainorder) {
		return Const.reverseResult.SUCCESS;
	}


	
}

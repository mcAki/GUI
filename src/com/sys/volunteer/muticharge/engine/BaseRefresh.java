package com.sys.volunteer.muticharge.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.springContext.SpringContextUtil;


public abstract class BaseRefresh implements IRefreshable {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	private Integer supplyInterfaceId;
	
	private Integer supplyId;
	
	private String mainorderId;
	
	private OrderVO orderVO;
	
	protected EnumOrderState state;
	
	private OrderService orderService;
	
//继承这个类的来实现
//	@Override 
//	public void run() {
//		// TODO Auto-generated method stub
// 
//	}

	@Override
	public void setState(EnumOrderState state) {
		this.state = state;
	}

	/**
	 * 创建延时订单列表
	 * @throws Exception 
	 */
	public BaseRefresh(Integer supplyInterfaceId, Integer supplyId,
			String mainorderId,OrderVO orderVO,EnumOrderState state) {
		this.supplyInterfaceId = supplyInterfaceId;
		this.supplyId = supplyId;
		this.mainorderId = mainorderId;
		this.orderVO = orderVO;
		this.state = state;
	}

	public void run() {
		try {
			logger.info("这里开始处理订单");
			//处理订单
			//TODO:recharge方法需要返回int
			if (state == EnumOrderState.OrderProcessing || state == EnumOrderState.ReversalProcessing) {
				this.refreshOrder(orderVO);
			}//else if (state == EnumOrderState.OrderCancelProcessing) {
//				this.cancelOrder(orderVO);
//			}
			//TODO:充值状态在这里更新
			
			logger.info("处理订单完成");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public EnumOrderState getState() {
		return state;
	}
	
	@Override
	public Integer getSupplyInterfaceId() {
		return supplyInterfaceId;
	}

	@Override
	public Integer getSupplyId() {
		return supplyId;
	}

	
	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	@Override
	public String getMainorderId() {
		return mainorderId;
	}

	public void setMainorderId(String mainorderId) {
		this.mainorderId = mainorderId;
	}

	public void setSupplyInterfaceId(Integer supplyInterfaceId) {
		this.supplyInterfaceId = supplyInterfaceId;
	}
	

}

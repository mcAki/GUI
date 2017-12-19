package com.sys.volunteer.order.engine.charge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.order.engine.BaseCharge;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.springContext.SpringContextUtil;

public class BatchOrderRefresh extends BaseCharge {
	
	protected Log logger = LogFactory.getLog(this.getClass());

	public BatchOrderRefresh(BatchOrder batchOrder) {
		super(batchOrder);
	}

	private OrderService orderService;
	
	@Override
	public void addCharge() {
		ApplicationContext act = SpringContextUtil
			.getApplicationContext();
		orderService = (OrderService) act.getBean("orderServiceBean");
		//useraccountService.refreshAccountes(getMainorder(), getUser(), getRecodeUsers(), getRecodeOperator(), getUserCharge(), getDealMoney(), getFlag());
		orderService.updateBatchOrder(getBatchOrder());
		logger.info("更新"+getBatchOrder().getBatchOrderId()+"了批量订单记录,已处理"+
				getBatchOrder().getCurrentProcessNum()+",还有"+getBatchOrder().getRestProcessNum());
	}

}

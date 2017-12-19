package com.sys.volunteer.order.engine;

import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;

public interface ICharge {

	/**
	 * 增加UserCharge
	 */
	public void addCharge();
	
	public void setBatchOrder(BatchOrder batchOrder);
	
	public BatchOrder getBatchOrder();
	
}

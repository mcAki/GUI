package com.sys.volunteer.order.engine;

import java.io.Serializable;

import com.sys.volunteer.pojo.BatchOrder;

public abstract class BaseCharge implements Serializable,ICharge {
	

	private BatchOrder batchOrder;
	
	public BaseCharge(BatchOrder batchOrder) {
		super();
		this.batchOrder = batchOrder;
	}

	public BatchOrder getBatchOrder() {
		return batchOrder;
	}

	public void setBatchOrder(BatchOrder batchOrder) {
		this.batchOrder = batchOrder;
	}
	
	
	
}

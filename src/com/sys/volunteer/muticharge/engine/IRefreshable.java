package com.sys.volunteer.muticharge.engine;


public interface IRefreshable extends Runnable{
	
	/**
	 * 执行结果
	 * @author Seraph
	 */
	public enum ExecuteResult{
		/**
		 * 空闲线程
		 */
		leisure,
		/**
		 * 执行中的线程
		 */
		runed,
		/**
		 * 死亡线程
		 */
		isDead
	}
	
	/**
	 * 订单状态
	 * @author admin
	 *
	 */
	public enum EnumOrderState{
		//0:申请处理 1:处理成功 2:处理失败 3:处理中
		/**
		 * 申请处理
		 */
		OrderApply,
		/**
		 * 处理成功
		 */
		OrderSuccess,
		/**
		 * 处理失败
		 */
		OrderFailed,
		/**
		 * 处理中
		 */
		OrderProcessing,
		/**
		 * 取消申请
		 */
		OrderCancelApply,
		/**
		 * 取消处理中
		 */
		OrderCancelProcessing,
		/**
		 * 已取消
		 */
		OrderCanceled,
		/**
		 * 申请冲正
		 */
		ReversalApply,
		/**
		 * 冲正处理中
		 */
		ReversalProcessing,
		/**
		 * 冲正成功
		 */
		ReversalSuccess,
		/**
		 * 冲正失败
		 */
		ReversalFailed
	};
	
	
	/**
	 * 充值接口运作状态
	 * 
	 * @return
	 */
	public void setState(EnumOrderState state);

	/**
	 * 充值接口运作状态
	 * 
	 * @return
	 */
	public EnumOrderState getState();
	
	/**
	 * 充值接口id
	 * @return
	 */
	public Integer getSupplyInterfaceId();
	
	
	/**
	 * 供货商id
	 * @return
	 */
	public Integer getSupplyId();

	/**
	 * 订单id
	 * @return
	 */
	public String getMainorderId();
	
	public int refreshOrder(OrderVO orderVO);
	
}

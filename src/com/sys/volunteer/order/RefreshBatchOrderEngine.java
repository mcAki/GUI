package com.sys.volunteer.order;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.order.engine.ICharge;

public class RefreshBatchOrderEngine extends Thread{

	
		protected Log logger = LogFactory.getLog(this.getClass());

		private OrderService orderService;
		
		/**
		 * 执行任务休眠间隔（毫秒）
		 */
		private int executeTaskSleepInterval = 1000;
		
		private static RefreshBatchOrderEngine instance = null;

		/**
		 * 线程必须刷新时间
		 */
		private long refreshTime = 0;

		private static LinkedList<ICharge> taskList;
		
		static boolean isLogin = false;
		
		private EnumState state;
		
		public enum EnumState{
			/**
			 * 空闲
			 */
			leisure,
			
			/**
			 * 处理中
			 */
			processing
		}
		
		/**
		 * 获取实例化管理器
		 * 
		 * @return
		 */
		public static RefreshBatchOrderEngine getInstance() {
			if (instance == null) {
				instance = new RefreshBatchOrderEngine();
			}
			return instance;
		}

		/**
		 * 构造器
		 * 
		 * @param producerPool
		 */
		private RefreshBatchOrderEngine() {
			taskList = new LinkedList<ICharge>();
			state = EnumState.leisure;
		}

		@Override
		public void destroy() {

		}

		@Override
		public void run() {
			while (true) {
				//logger.info("usercharge运行中...");
				
				if (System.currentTimeMillis() > refreshTime) {
					refreshTime = System.currentTimeMillis()
							+ executeTaskSleepInterval;
				} else {
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}
				
				try {
					//检测是否正在处理
					if (state == EnumState.processing) {
						continue;
					}else {
						synchronized (taskList) {
							while (taskList.size()>0) {
								logger.info("进入处理refreshBatchOrder流程,现有"+taskList.size()+"条记录需要处理");
								
								state = EnumState.processing;
								
								ICharge charge = taskList.getFirst();
								//先进先出
								taskList.removeFirst();
								
								charge.addCharge();
							}
							//处理完设置为空闲
							state = EnumState.leisure;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		@Override
		public synchronized void start() {
			this.setName("RefreshBatchOrderEngine");
			super.start();
			logger.info("RefreshBatchOrderEngine接口");
		}
		
		/**
		 * 强制刷新
		 */
		public synchronized void forceRefreshOrder() {
			refreshTime = 0;
		}

		/**
		 * 执行任务休眠间隔（毫秒）
		 * 
		 * @param executeTaskSleepInterval
		 */
		public void setExecuteTaskSleepInterval(int executeTaskSleepInterval) {
			this.executeTaskSleepInterval = executeTaskSleepInterval;
		}

		public LinkedList<ICharge> getTaskList() {
			return taskList;
		}
		
		public void addFirst(ICharge iCharge) {
			logger.info("增加了一条更新批量订单记录在队列最前!");
			taskList.addFirst(iCharge);
		}
		
		public void addLast(ICharge iCharge) {
			logger.info("增加了一条更新批量订单记录在队列最后!");
			taskList.addLast(iCharge);
		}
}

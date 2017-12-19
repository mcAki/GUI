package com.sys.volunteer.usercharge;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.engine.BaseCharge;
import com.sys.volunteer.usercharge.engine.ICharge;

public class UserChargeEngine extends Thread{

	
		protected Log logger = LogFactory.getLog(this.getClass());

		private UseraccountService useraccountService;
		
		/**
		 * 执行任务休眠间隔（毫秒）
		 */
		private int executeTaskSleepInterval = 1000;
		
		private static UserChargeEngine instance = null;

		/**
		 * 线程必须刷新时间
		 */
		private long refreshTime = 0;
		
		/**
		 * 处理中线程超时时间(20s)
		 */
		private long timeout = 20000;
		
		private static long lastProcessTime = 0;

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
		public static UserChargeEngine getInstance() {
			if (instance == null) {
				instance = new UserChargeEngine();
			}
			return instance;
		}

		/**
		 * 构造器
		 * 
		 * @param producerPool
		 */
		private UserChargeEngine() {
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
				ICharge charge;
				try {
					//检测是否正在处理
					if (state == EnumState.processing) {
						logger.info("usercharge流程还有处理中的任务");
						if (System.currentTimeMillis() - lastProcessTime > timeout) {
							logger.info("usercharge流程处理已超过20秒!");
							logger.info("usercharge流程lastProcessTime清0");
							lastProcessTime = 0;
							logger.info("usercharge流程状态设置为空闲");
							state = EnumState.leisure;
						}
						continue;
					}else {
						synchronized (taskList) {
							while (taskList.size()>0) {
								logger.info("进入处理usercharge流程,现有"+taskList.size()+"条记录需要处理");
								
								state = EnumState.processing;
								
								//设置lastProcessTime
								lastProcessTime = System.currentTimeMillis();
								
								charge = taskList.getFirst();
								//先进先出
								taskList.removeFirst();
								
								charge.addCharge();
								
								//处理完设置为空闲
								logger.info("usercharge流程已更新为空闲");
								state = EnumState.leisure;
							}
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		@Override
		public synchronized void start() {
			this.setName("UserChargeEngine");
			super.start();
			logger.info("usercharge接口");
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
			logger.info("增加了一条额度记录在队列最前!");
			taskList.addFirst(iCharge);
		}
		
		public void addLast(ICharge iCharge) {
			logger.info("增加了一条额度记录在队列最后!");
			taskList.addLast(iCharge);
		}
}

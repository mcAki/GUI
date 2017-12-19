package com.sys.volunteer.muticharge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.sys.volunteer.common.Const;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.muticharge.engine.IRefreshable;
import com.sys.volunteer.muticharge.engine.OrderVO;
import com.sys.volunteer.muticharge.engine.IRefreshable.EnumOrderState;
import com.sys.volunteer.muticharge.engine.mobile.MobileRefresh;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.order.RefreshBatchOrderEngine;
import com.sys.volunteer.order.engine.charge.BatchOrderRefresh;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.springContext.SpringContextUtil;

public class RefreshMutithreadEngine extends Thread {

	protected Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 执行任务休眠间隔（毫秒）
	 */
	private int executeTaskSleepInterval = 15000;

	private ThreadPoolExecutor producerPool;

	private static List<OrderVO> orderVOs = new ArrayList<OrderVO>();

	private static String dealingOrderId = "";

	/**
	 * 线程必须刷新时间
	 */
	private long refreshTime = 0;

	@Override
	public void destroy() {

	}

	@Override
	public void run() {
		// boolean isRun;
		// ExecuteResult exeresult;
		while (true) {
			// try {
			// System.out.println("AI线程执行(调试用)");
			// isRun = false;

			// for (int i = gameAIPool.size() - 1; i >= 0; i--) {
			// // for (Iterator<IGameAI> iterator = gameAIPool.iterator();
			// iterator.hasNext();)
			// // {
			// // IGameAI gameAI = iterator.next();
			// IGameAI gameAI = gameAIPool.get(i);
			// if (gameAI.getState() == EnumAIState.AISEnumIsDead) {
			// // AI已经没用了
			// gameAIPool.remove(i);
			// // gameAIPool.remove(gameAI);
			// gameAI = null;
			// System.out.println("消灭了一个AI,现在AI总数:" + gameAIPool.size());
			// } else {
			// // 执行AI预处理状态逻辑
			// if (gameAI.excuteStateLogical()) {
			// // 线程池执行AI内容
			// if (gameAI.isNeedThreadExcute()) {
			// AIManager.getHinstance().getProducerPool().execute(gameAI);
			// }
			// }
			// }
			// }
			// 如果没执行过计划任务则线程休眠一下，可以更节省系统资源
			// if (!isRun)

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

			// 这里是刷单的
			try {
				logger.info("这里刷新刷单线程!");
				ApplicationContext act = SpringContextUtil
						.getApplicationContext();
				IMainorderDao mainorderDao = (IMainorderDao) act
						.getBean("mainorderDao");
				int rs = 0;
				if (orderVOs.size() == 0) {
					rs = mainorderDao.countProcessingOrder();
					logger.info("订单充值流程找到:"+rs+"条需要充值的订单");
					if (rs > 0) {
						orderVOs = mainorderDao.findProcessingOrder();
					}
				}

				synchronized (orderVOs) {
					while (orderVOs.size() > 0) {
						logger.info("进入刷新订单处理流程");
						
						OrderVO order = orderVOs.get(0);
						// 先进先出
						orderVOs.remove(order);

						if (dealingOrderId.equals(order.getMainOrderId())) {
							logger.error("处理中的订单," + order.getMainOrderId());
							break;
						} else {
							dealingOrderId = order.getMainOrderId();
						}
						logger.info("orderVo的orderId是:"
								+ order.getMainOrderId() + ",唯一码是:"
								+ order.getUniqueCode());

						// 处理取消
						if (order.getState().equals(
								Const.MainOrderState.USER_CANCEL_PROCESSING)) {
							for (OrderVO orderVO : orderVOs) {
								if (orderVO.getMainOrderId().equals(
										order.getMainOrderId())
										&& !orderVO
												.getState()
												.equals(
														Const.MainOrderState.USER_CANCEL_PROCESSING)) {
									orderVOs.remove(orderVO);
								}
							}
						}
						IRefreshable refreshable = null;
						switch (order.getSupplyId()) {
						case 17:
						case 19:
						case 20:
						case 24:
						case 28:
						case 25:
						case 27:
						case 26:
						case 33:
						case 29:
						case 34:
						case 35:
						case 31:
							if (order.getState().equals(
									Const.MainOrderState.PROCESSING)) {
								refreshable = new MobileRefresh(order
										.getSupplyInterfaceId(), order
										.getSupplyId(), order.getMainOrderId(),
										order, EnumOrderState.OrderProcessing);
								mainorderDao.updateOrderState(Const.MainOrderState.THREAD_EXECUTING, order
										.getMainOrderId());
							} else if (order.getReversalState().equals(
									Const.OrderReversalState.PROCESSING)) {
								refreshable = new MobileRefresh(order
										.getSupplyInterfaceId(), order
										.getSupplyId(), order.getMainOrderId(),
										order,
										EnumOrderState.ReversalProcessing);
								mainorderDao.updateOrderState(Const.OrderReversalState.THREAD_EXECUTING, order
										.getMainOrderId());
							}else if (order.getState().equals(
									Const.MainOrderState.USER_CANCEL_PROCESSING)) {
//								chargeable = new MobileCharge(order
//										.getSupplyInterfaceId(), order
//										.getSupplyId(), order.getMainOrderId(),
//										order, EnumOrderState.OrderCancelProcessing);
								mainorderDao.updateOrderState(255, order
										.getMainOrderId());
								//取消批量定单需更新运行状态
								OrderService orderService = (OrderService) act.getBean("orderServiceBean");
								Mainorder mainorder = orderService.findOrderById(order.getMainOrderId());
								//如果是批量单,刷新总单
								if (mainorder.getIsBatch()==1) {
									orderService.updateBatchOrderState(mainorder, Const.BatchOrderState.PROCESSED);
									BatchOrder batchOrder = orderService.findBatchOrderById(mainorder.getBatchOrderId());
									//orderService.updateBatchOrder(batchOrder);
									com.sys.volunteer.order.engine.ICharge iCharge = new BatchOrderRefresh(batchOrder);
									RefreshBatchOrderEngine.getInstance().addLast(iCharge);
								}
							}
							break;

						default:
							break;
						}
						// TODO:这里需要逻辑判断是否执行
						if (refreshable != null) {
							ChargeMutithreadManager.getInstance().getProducerPool()
							.execute(refreshable);
						}

						// 还原处理中标记
						dealingOrderId = "";
						logger.info("处理完成,处理中标记还原" + dealingOrderId);
					}
				}
			} catch (Exception e) {
				logger.error("jdbc错误!");
				e.printStackTrace();
			}

		}

	}

	@Override
	public synchronized void start() {
		this.setName("RefreshMutithreadEngine");
		super.start();
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

	/**
	 * 执行任务休眠间隔（毫秒）
	 * 
	 * @return
	 */
	public int getExecuteTaskSleepInterval() {
		return executeTaskSleepInterval;
	}

	public void setProducerPool(ThreadPoolExecutor producerPool) {
		this.producerPool = producerPool;
	}
}

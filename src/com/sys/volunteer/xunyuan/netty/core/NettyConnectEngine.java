package com.sys.volunteer.xunyuan.netty.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.xunyuan.charge.XunYuanNettyThreadEngine;
import com.sys.volunteer.xunyuan.netty.client.NettyClient;

public class NettyConnectEngine extends Thread {

	protected Log logger = LogFactory.getLog(this.getClass());

	protected static EnumConnectState connectState = EnumConnectState.disconnected;
	
	/**
	 * 连接状态
	 * @author Administrator
	 *
	 */
	public enum EnumConnectState{
		/**
		 * 已断开
		 */
		disconnected,
		/**
		 * 已连接
		 */
		connected,
		/**
		 * 关闭channel
		 */
		channelClosed
	}
	
	private static NettyConnectEngine instance = null;
	
	/**
	 * 执行任务休眠间隔（毫秒）
	 */
	private int executeTaskSleepInterval = 15000;

	/**
	 * 线程必须刷新时间
	 */
	private long refreshTime = 0;
	
	/**
	 * 获取实例化管理器
	 * 
	 * @return
	 */
	public static NettyConnectEngine getInstance() {
		if (instance == null) {
			instance = new NettyConnectEngine();
		}
		return instance;
	}

	/**
	 * 构造器
	 * 
	 * @param producerPool
	 */
	private NettyConnectEngine() {
		
	}
	
	@Override
	public void destroy() {

	}
	
	@Override
	public void run() {
		while(true){
			
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
			logger.info("ip:"+XunyuanKernel.XUNYUANIP+"port:"+XunyuanKernel.XUNYUANPORT);
			if (connectState.equals(EnumConnectState.disconnected)) {
				//断开
				//重连
				NettyClient.getInstance().connect(XunyuanKernel.XUNYUANIP, XunyuanKernel.XUNYUANPORT);
			}else if (connectState.equals(EnumConnectState.connected)) {
				//连接成功,不处理
			}else {
				//登陆状态设置为false
				XunYuanNettyThreadEngine.setLogin(false);
				//chanelCLosed
				//释放线程
				NettyClient.getInstance().close();
				//重新连接
				NettyClient.getInstance().connect(XunyuanKernel.XUNYUANIP, XunyuanKernel.XUNYUANPORT);
			}
			
		}
		
	}
	
	

	public synchronized void start() {
		this.setName("NettyConnectEngine");
		super.start();
		logger.info("Netty连接监控接口");
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

	public static EnumConnectState getConnectState() {
		return connectState;
	}

	public static void setConnectState(EnumConnectState connectState) {
		NettyConnectEngine.connectState = connectState;
	}


}


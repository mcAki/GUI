package com.sys.volunteer.muticharge;

import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.muticharge.engine.IChargeable;

public class ChargeMutithreadManager {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	private static ChargeMutithreadManager instance = null;

	/**
	 * 多线程处理池
	 */
	private ThreadPoolExecutor producerPool;
	
	/**
	 * 获取实例化管理器
	 * @return
	 */
	public static ChargeMutithreadManager getInstance() {
		if (instance == null) {
			instance = new ChargeMutithreadManager();
		}
		return instance;
	}
	
	/**
	 * 下单线程
	 */
	private ChargeMutithreadEngine chargeMutithreadEngine;
	
	/**
	 * 刷单线程
	 */
	private RefreshMutithreadEngine refreshMutithreadEngine;
	
	/**
	 * 私有化构造函数
	 */
	private ChargeMutithreadManager(){
		
		logger.info("初始化manager");
		
		
		//从配置文件读取配置，设置最大AI与最小AI线程
		int minAiThread = 5;//Context.getInstance().getCatanAiConfig().getMinAiThread();
		int maxAiThread = 100;//Context.getInstance().getCatanAiConfig().getMaxAiThread();
		//空闲销毁时间
		int keepAliveSeconds = 120;//Context.getInstance().getCatanAiConfig().getKeepAliveSeconds();
		
		//开启线程池
		producerPool = new ThreadPoolExecutor(minAiThread, maxAiThread, keepAliveSeconds,  
			TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),  
			new ThreadPoolExecutor.DiscardOldestPolicy());  
		
		chargeMutithreadEngine = new ChargeMutithreadEngine();
		chargeMutithreadEngine.setProducerPool(producerPool);
		
		setChargeMutithreadEngine(chargeMutithreadEngine);
		
		logger.info("chargeMutithreadEngine初始化成功!");
		
		refreshMutithreadEngine = new RefreshMutithreadEngine();
		refreshMutithreadEngine.setProducerPool(producerPool);
		
		setRefreshMutithreadEngine(refreshMutithreadEngine);
		
		logger.info("refreshMutithreadEngine初始化成功!");
		
	}
	
	
	/**
	 * 正式开始处理核心
	 */
	public void startManager(){
		chargeMutithreadEngine.start();
		refreshMutithreadEngine.start();
		logger.info("=============正式开始处理核心!============");
	}
	
	public ThreadPoolExecutor getProducerPool() {
		return producerPool;
	}

	public void setProducerPool(ThreadPoolExecutor producerPool) {
		this.producerPool = producerPool;
	}

	/**
	 * 多线程充值引擎
	 * @param mutithreadEngine the mutithreadEngine to set
	 */
	public void setChargeMutithreadEngine(ChargeMutithreadEngine chargeMutithreadEngine) {
		this.chargeMutithreadEngine = chargeMutithreadEngine;
	}

	/**
	 * @return the mutithreadEngine
	 */
	public ChargeMutithreadEngine getChargeMutithreadEngine() {
		return chargeMutithreadEngine;
	}


	public RefreshMutithreadEngine getRefreshMutithreadEngine() {
		return refreshMutithreadEngine;
	}


	public void setRefreshMutithreadEngine(
			RefreshMutithreadEngine refreshMutithreadEngine) {
		this.refreshMutithreadEngine = refreshMutithreadEngine;
	}



}

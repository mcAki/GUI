package com.sys.volunteer.gmmanage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sys.volunteer.vo.BroadcastVo;

public class GMKernel {

	private Map<Integer, Map<String, BroadcastVo>> serverBroadcast;
	
	private ExecutorService executorService;
	
	private static GMKernel instance;

	private GMKernel() {
		serverBroadcast = new HashMap<Integer, Map<String, BroadcastVo>>();
		executorService = Executors.newSingleThreadExecutor();
	}
	
	/**
	 * 单例
	 * @return
	 */
	public static GMKernel getInstance() {
		if (instance==null) init();
		return instance;
	}
	
	/**
	 * 初始化
	 */
	public static void init() {
		instance = new GMKernel();
	}

	/**
	 * 获取广播map
	 * @return
	 */
	public Map<String, BroadcastVo> getBroadcastMap(Integer key) {
		return serverBroadcast.get(key);
	}
	
	/**
	 * 消除广播map
	 * @return
	 */
	public void removeBroadcastMap(Integer key,String uuid) {
		serverBroadcast.get(key).remove(uuid);
	}

	public Map<Integer, Map<String, BroadcastVo>> getServerBroadcast() {
		return serverBroadcast;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}
}

package com.ages.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用计算执行时间检测类
 * @author admin
 *
 */
public class ExecuteTimeStats {
	private static Map<String, Long> timesContainer = new ConcurrentHashMap<String, Long>();
	
	/**
	 * 执行时间统计开始
	 * @param keyWord 关键字，用此关键字来标记与区分
	 */
	public static void executeStatsBegin(String keyWord){
		if(timesContainer.containsKey(keyWord)){
			timesContainer.remove(keyWord);
		}
		Long time = new Long(System.currentTimeMillis());
		timesContainer.put(keyWord, time);
	}
	
	
	/**
	 * 执行时间统计结束
	 * @param keyWord 关键字，用此关键字来标记与区分
	 * @param isAutoSystemOutDebugMessage 是否自动输出执行时间到控制台
	 * @return 返回执行时间(单位:毫秒)
	 */
	public static int executeStatsEnd(String keyWord){
		if(timesContainer.containsKey(keyWord)){
			Long oldTime = timesContainer.get(keyWord);
			timesContainer.remove(keyWord);
			int result = (int) (System.currentTimeMillis() - oldTime);
			System.out.println("[TimeStats]: [" + keyWord + "] raise " + result + " Mills." );
			return result;			
		}else {
			return -1;
		}
	}
	
	
	
}

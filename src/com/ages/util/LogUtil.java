package com.ages.util;

import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * @author guoxi
 * @version 创建时间：2010-9-7 下午05:10:45
 * 类说明
 */
public class LogUtil {
	public final static String SPLIT = "|";
	public static Logger controllerLog =Logger.getLogger("controller.log");
	public static Logger sqlLog =Logger.getLogger("sqlLog.log");
	public static Logger lockThreadLog =Logger.getLogger("lockThreadLog.log");
	public static Logger gameLog =Logger.getLogger("gameLog.log");
	public static Logger matchingLog =Logger.getLogger("matchingLog.log");
	
	public static void sqlLog(String key,long howLong){
//		System.out.println(">>>>>>>>>>>sqlkey:"+key);
		//if(Constants.sqlLog){
			StringBuilder logBuf = new StringBuilder();
			logBuf.append(SPLIT).append(UUID.randomUUID().toString()).append(SPLIT);
			logBuf.append(key).append(SPLIT);
			logBuf.append(howLong);
			if(howLong>2000)
				logBuf.append(SPLIT).append("slow");
			else
				logBuf.append(SPLIT).append("fast");
			
			sqlLog.info(logBuf.toString());		
			logBuf =null;
		//}
	}
	
	public static void controllerLog(String playerId,String controllerName,String methodName,long howLong){
		StringBuilder logBuf = new StringBuilder();
		logBuf.append(SPLIT).append(UUID.randomUUID().toString()).append(SPLIT);
		logBuf.append(playerId).append(SPLIT);
		logBuf.append(controllerName).append(SPLIT);
		logBuf.append(methodName).append(SPLIT);
		logBuf.append(howLong);
		controllerLog.info(logBuf.toString());		
		logBuf=null;
	}	
	


	/**
	 * 线程死锁检查日志
	 * @param info
	 */
	public static void lockThreadLog(String info){
		StringBuilder logBuf = new StringBuilder();
		logBuf.append(SPLIT).append(UUID.randomUUID().toString()).append(SPLIT);
		lockThreadLog.info(info.toString());		
	}
	
	/**
	 * 匹配系统日志
	 * @param info
	 */
	public  static void matchingLog(String msg){
		StringBuilder logBuf = new StringBuilder();
		logBuf.append(SPLIT).append(UUID.randomUUID().toString()).append(SPLIT);
		logBuf.append(msg);
		matchingLog.info(logBuf.toString());		
	}
	
	/**
	 * 游戏逻辑日志
	 * @param playerId
	 * @param msg 信息
	 * @param args 自选参数
	 */
	public  static void gameLog(String Id,String msg,String args){
		StringBuilder logBuf = new StringBuilder();
		logBuf.append(SPLIT).append(UUID.randomUUID().toString()).append(SPLIT);
		logBuf.append(Id).append(SPLIT);
		logBuf.append(msg).append(SPLIT);
		logBuf.append(args);
		gameLog.info(logBuf.toString());		
	}
}

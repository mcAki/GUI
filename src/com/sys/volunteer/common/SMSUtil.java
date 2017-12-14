package com.sys.volunteer.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jasson.im.api.APIClient;
import com.jasson.im.api.MOItem;

public class SMSUtil {
	private static String host = "210.72.2.213";
	private static String dbName = "mas";
	private static String apiId = "006";
	private static String name = "zys";
	private static String pwd = "tsw@0823";
	private static APIClient handler = new APIClient();
	private static Log log=LogFactory.getLog(SMSUtil.class);
	static{init();}
	public static void init(){
		try {
			int connectRe = handler.init(host, name, pwd, apiId,dbName);
	        if(connectRe == APIClient.IMAPI_SUCC)
	        	log.info("SP连接成功");
	        else if(connectRe == APIClient.IMAPI_CONN_ERR)
	        	log.error("SP连接失败");
	        else if(connectRe == APIClient.IMAPI_API_ERR)
	        	log.error("SP连接失败,apiID不存在");
	        if(connectRe != APIClient.IMAPI_SUCC)
	        {
	        	log.error("SP连接失败");
	        }
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public static int sendSMS(String[] mobileArr,String content,long smId){
		int result = handler.sendSM(mobileArr, content, smId);
		if(result == APIClient.IMAPI_SUCC)
        {            
			log.info("短信发送成功\n");
        }
        else if(result == APIClient.IMAPI_INIT_ERR)
        	log.error("SP连接未初始化");
        else if(result == APIClient.IMAPI_CONN_ERR)
        	log.error("SP数据库连接失败");
        else if(result == APIClient.IMAPI_DATA_ERR)
        	log.error("SP连接参数错误");
        else if(result == APIClient.IMAPI_DATA_TOOLONG)
        	log.error("短信消息内容太长");
        else if(result == APIClient.IMAPI_INS_ERR)
        	log.error("SP数据库插入错误");
        else
        	log.error("出现其他错误");
		
		if(result != APIClient.IMAPI_SUCC){
			init();//重新连接一次
			result = handler.sendSM(mobileArr, content, smId);
		}
		return result;
	}
	
	public static MOItem[] recvSM()
	{
		MOItem[] mos = handler.receiveSM();
        if(mos == null)
        {
        	log.error("未初始化或接收失败");
        	init();//再连接一次
        	mos = handler.receiveSM();
        	if(mos == null){
        		log.error("未初始化或接收失败");
        		return null;
        	}
        	else {
				return mos;
			}
        }
        else if(mos.length == 0)
        {
        	log.info("没有MO短信");
        	return null;
        }
        else
        {
        	return mos;
        }
	}
}

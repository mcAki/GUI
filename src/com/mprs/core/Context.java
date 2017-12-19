package com.mprs.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mprs.protocol.ProtocolContainer;
import com.mprs.protocol.GenerateProtocolPackage.ProtocolSetting;
import com.mprs.util.XmlConfigReader;

/**
 * 贯穿全局的上下文环境，为单例
 * @author guoxi
 *
 */
public class Context {

	private Random random;
	
	private XmlConfigReader xmlConfigReader;
	
	/**
	 * 构造器
	 */
	private Context(){
		random = new Random(System.currentTimeMillis());
	}
	
	public Log log = LogFactory.getLog(this.getClass());
	
	private static Context context=null;
	
	/**
	 * 协议解析的XML配置文件转化后的map
	 */
	private Map<Integer,ProtocolContainer> protocolMap=new HashMap<Integer,ProtocolContainer>();
	
	/**
	 * 传输包设置
	 */
	private ProtocolSetting protocolSetting;
	
	/**
	 * 获得Context实例
	 * @return
	 */
	public static Context getInstance(){
		if(context==null){
			context=new Context();
		}
		return context;
	}

	
	public void reloadConfig(){
		try {
			//xmlConfigReader = new XmlConfigReader("serverConfig.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	
	public Map<Integer, ProtocolContainer> getProtocolMap() {
		return protocolMap;
	}
	public void setParseMap(Map<Integer, ProtocolContainer> parseMap) {
		this.protocolMap = parseMap;
	}
	
	public ProtocolSetting getProtocolSetting() {
		return protocolSetting;
	}

	public void setProtocolSetting(ProtocolSetting protocolSetting) {
		this.protocolSetting = protocolSetting;
	}

	public Random getRandom() {
		return random;
	}


	

	
}

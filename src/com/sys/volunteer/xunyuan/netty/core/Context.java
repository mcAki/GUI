package com.sys.volunteer.xunyuan.netty.core;

import java.util.HashMap;
import java.util.Map;

import com.sys.volunteer.xunyuan.protocol.ResponeClass;
import com.sys.volunteer.xunyuan.protocol.charge.ChargePo_Rp;
import com.sys.volunteer.xunyuan.protocol.charge.ChargeResultNotifyPo_Rp;
import com.sys.volunteer.xunyuan.protocol.heartbeat.HeartBeatPo_Rp;
import com.sys.volunteer.xunyuan.protocol.login.LoginPo_Rp;
import com.sys.volunteer.xunyuan.protocol.login.LogoutPo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryPo_Rp;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo_Rp;
import com.sys.volunteer.xunyuan.service.charge.ChargeService;
import com.sys.volunteer.xunyuan.service.heartbeat.HeartBeatService;
import com.sys.volunteer.xunyuan.service.login.LoginService;
import com.sys.volunteer.xunyuan.service.query.QueryService;
import com.sys.volunteer.xunyuan.service.reverse.ReverseService;

public class Context {

	public static Map<String, ResponeClass> parseMap = new HashMap<String, ResponeClass>();//响应用
	public static Map<String, String> protocolMap = new HashMap<String, String>();//反射类用
	
	public static void initParseMap(){
		//parseMap.put(LoginBattleRoomPo_Rp.cmd, new ResponeClass(BattleService.class.getName(), "loginBattleRoomInvoke"));
		parseMap.put(LoginPo_Rp.cmd, new ResponeClass(LoginService.class.getName(), "loginReturnInvoke"));
		parseMap.put(LogoutPo_Rp.cmd, new ResponeClass(LoginService.class.getName(), "logoutReturnInvoke"));
		parseMap.put(HeartBeatPo_Rp.cmd, new ResponeClass(HeartBeatService.class.getName(), "heartbeatReturnInvoke"));
		parseMap.put(ChargePo_Rp.cmd, new ResponeClass(ChargeService.class.getName(), "chargeReturnInvoke"));
		parseMap.put(ChargeResultNotifyPo_Rp.cmd, new ResponeClass(ChargeService.class.getName(), "chargeResultNotifyInvoke"));
		parseMap.put(ReversePo_Rp.cmd, new ResponeClass(ReverseService.class.getName(), "reverseReturnInvoke"));
		parseMap.put(QueryPo_Rp.cmd, new ResponeClass(QueryService.class.getName(), "queryReturnInvoke"));
		parseMap.put(QueryBalancePo_Rp.cmd, new ResponeClass(QueryService.class.getName(), "queryBalanceReturnInvoke"));
		
	}
	
	public static void initProtocolMap(){
//		protocolMap.put(LoginPo_Rp.serialId, LoginPo_Rp.reflectClassName);
		//protocolMap.put(LoginBattleRoomPo_Rp.cmd, LoginBattleRoomPo_Rp.reflectClassName);
	}
}

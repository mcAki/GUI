package com.sys.volunteer.xunyuan.netty.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.Log4JLoggerFactory;

import com.sys.volunteer.xunyuan.netty.client.NettyClient;
import com.sys.volunteer.xunyuan.netty.core.Context;
import com.sys.volunteer.xunyuan.netty.core.NettyConnectEngine;
import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.xunyuan.netty.core.NettyConnectEngine.EnumConnectState;
import com.sys.volunteer.xunyuan.protocol.BasePo;
import com.sys.volunteer.xunyuan.protocol.charge.ChargePo_Rp;
import com.sys.volunteer.xunyuan.protocol.error.ErrorPo_Rp;
import com.sys.volunteer.xunyuan.protocol.heartbeat.HeartBeatPo_Rp;
import com.sys.volunteer.xunyuan.protocol.login.LoginPo_Rp;
import com.sys.volunteer.xunyuan.protocol.login.LogoutPo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryPo_Rp;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo_Rp;
import com.sys.volunteer.xunyuan.service.BaseService;
import com.sys.volunteer.xunyuan.util.PackageUtil;

public class NettyClientHandler extends SimpleChannelHandler{
	
	InternalLogger log = Log4JLoggerFactory.getInstance(NettyClientHandler.class);
	
	byte[] packageHeader = {'r','y','$','d'};
	
	private ChannelBuffer recBuffer=ChannelBuffers.dynamicBuffer();
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		log.info("连接到服务器!");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		log.error(e.getCause().getMessage());
		//ctx.getChannel().close();
		//重新打开channel
		//NettyClient.getInstance().reConnect();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		
		ChannelBuffer buffer1 = (ChannelBuffer) e.getMessage();
		ChannelBuffer buffer=ChannelBuffers.dynamicBuffer();
		if(recBuffer.writerIndex()>0){
			synchronized (recBuffer) {
				buffer.writeBytes(recBuffer);
				if(buffer1!=null && buffer1.readableBytes()>0)
					buffer.writeBytes(buffer1);
			}
		}else{
			if(buffer1!=null && buffer1.readableBytes()>0)
				buffer.writeBytes(buffer1);
		}
		recBuffer.clear();
		
		byte[] lookforByte = new byte[1];
		byte[] lenByte = new byte[4];
		
		//获取长度
		buffer.getBytes(buffer.readerIndex(), lenByte);
		buffer.skipBytes(4);
		
		boolean isFoundHeader = false;
		while(buffer.readableBytes()>0 && (!isFoundHeader)) {
			
			if (buffer.readableBytes() < 10 + 4 + 6) {
				log.info("接收的数据长度小于 最少规范长度,所以不处理");
				return ;
			}
			
			buffer.getBytes(buffer.readerIndex(), lookforByte);
			if(lookforByte[0]!=packageHeader[0]){
				buffer.skipBytes(1);
				continue;
			}
				
			
			buffer.getBytes(buffer.readerIndex()+1, lookforByte);
			if(lookforByte[0]!=packageHeader[1]){
				buffer.skipBytes(2);
				continue;
			}
			
			buffer.getBytes(buffer.readerIndex()+2, lookforByte);
			if(lookforByte[0]!=packageHeader[2]){
				buffer.skipBytes(3);
				continue;
			}
			
			buffer.getBytes(buffer.readerIndex()+3, lookforByte);
			if(lookforByte[0]!=packageHeader[3]){
				buffer.skipBytes(4);
				continue;
			}
			
			
			isFoundHeader=true;

		}
		
		if(!isFoundHeader){
			return ;
		}
		
		
		
		//int dataLength = PackageUtil.DecodeInteger(lenByte, 0);
		String length = new String(lenByte);
		int dataLength = Integer.parseInt(length);
		
		byte[] lastSerialId = new byte[6];
		buffer.getBytes(buffer.readerIndex()+6, lastSerialId);
		String serialId = new String(packageHeader);
		serialId = serialId + new String(lastSerialId);
		
		byte[] businessTypeByte = new byte[4];
		buffer.getBytes(buffer.readerIndex()+10,businessTypeByte);
		String businessType = new String(businessTypeByte);
		byte[] msgCodeByte = new byte[6];
		buffer.getBytes(buffer.readerIndex()+14,msgCodeByte);
		String msgCode = new String(msgCodeByte);
		
		if(buffer.readableBytes() >=dataLength-4 ){
			buffer.skipBytes(10 + 4 + 6);
		}else{
			synchronized (recBuffer) {
				recBuffer.clear();
				recBuffer.writeBytes(buffer);
			}
			return ;
		}
		
		BasePo vo = null;
		System.out.println("========================="+msgCode);
		if (msgCode.equals(LoginPo_Rp.cmd)) {
			//登陆报文
			LoginPo_Rp rp = new LoginPo_Rp();
			rp = rp.decode(buffer,length,serialId,businessType,msgCode);
			System.out.println("响应代码为:"+rp.getRespCode());
			vo = rp;
		}else if (msgCode.equals(LogoutPo_Rp.cmd)) {
			LogoutPo_Rp rp = new LogoutPo_Rp();
			rp = rp.decode(buffer, length, serialId,businessType,msgCode);
			System.out.println(rp.getBusinessType());
			vo = rp;
		}else if (msgCode.equals(HeartBeatPo_Rp.cmd)) {
			//心跳报文
			HeartBeatPo_Rp rp = new HeartBeatPo_Rp();
			rp = rp.decode(buffer, length, serialId,businessType,msgCode);
			vo = rp;
		}else if (msgCode.equals(ChargePo_Rp.cmd)) {
			//充值报文
			ChargePo_Rp rp = new ChargePo_Rp();
			rp = rp.decode(buffer, length, serialId,businessType,msgCode);
			System.out.println("充值完成!返回码:"+rp.getRespCode());
			vo = rp;
		}else if (msgCode.equals(ErrorPo_Rp.cmd)) {
			//通用报错报文
			ErrorPo_Rp rp = new ErrorPo_Rp();
			rp = rp.decode(buffer, length, serialId,businessType,msgCode);
			System.out.println("报错代码:"+rp.getMsgCode());
			vo = rp;
		}else if (msgCode.equals(QueryPo_Rp.cmd)) {
			QueryPo_Rp rp = new QueryPo_Rp();
			rp = rp.decode(buffer, length, serialId, businessType, msgCode);
			System.out.println("查询订单,返回码:"+rp.getRespCode());
			vo = rp;
		}else if (msgCode.equals(ReversePo_Rp.cmd)) {
			ReversePo_Rp rp = new ReversePo_Rp();
			rp = rp.decode(buffer, length, serialId, businessType, msgCode);
			System.out.println("冲正返回:"+rp.getRespCode());
			vo = rp;
		}else if (msgCode.equals(QueryBalancePo_Rp.cmd)) {
			System.out.println("-------------收到查询余额包返回!-------");
			QueryBalancePo_Rp rp = new QueryBalancePo_Rp();
			rp = rp.decode(buffer, length, serialId, businessType, msgCode);
			vo = rp;
		}
		
		// 长度缓冲
		if (buffer.readableBytes() > 0) {
			System.out.println("@@有剩流：" + buffer.readableBytes());
			synchronized (recBuffer) {
				recBuffer.clear();
				recBuffer.writeBytes(buffer);
			}
		}else {
			
		}
		
		long runningTimeMillis = System.currentTimeMillis();
		
		
		String serviceName = Context.parseMap.get(msgCode).getServiceName();
		String invokeName = Context.parseMap.get(msgCode).getMethodName();
		
		try{
			Class<?> serviceClass = Class.forName(serviceName);
			BaseService baseService = (BaseService) serviceClass.newInstance();
			//log.info("进入" + baseService.getClass().getSimpleName() + "类！！！！");
			baseService.deal(ctx, e, vo);
		}catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			runningTimeMillis = System.currentTimeMillis() - runningTimeMillis;
			
			log.info("[" + serviceName + "的" + invokeName + "方法] 完毕.  耗时:" + runningTimeMillis + "毫秒");
			if(runningTimeMillis>500){
				if(runningTimeMillis>1000){
					log.info("[WAR:TimeHigh] 耗时超1秒");
				}else if(runningTimeMillis>2000){
					log.info("[WAR:TimeHigh] 耗时超2秒");
				}else if(runningTimeMillis>5000){
					log.info("[WAR:TimeHigh] 耗时超5秒");
				}else if(runningTimeMillis>10000){
					log.info("[WAR:TimeHigh] 耗时超10秒");
				}else {
					log.info("[WAR:TimeHigh] 耗时超500毫秒");
				}
			}
			
			if(recBuffer.readableBytes()>0){
				this.messageReceived(ctx, e);
			}
		}
		System.out.println("收到包!");
//		switch (commandId) {
//		case -574289:
//			LoginBattleRoomPo_Rp loginBattleRoomPo_Rp = (LoginBattleRoomPo_Rp) vo;
//			int result = loginBattleRoomPo_Rp.getResult();
//			if(result == 1){
//				System.out.println("LoginBattleRoomPo_Rp：登陆成功!");
//				EnterBattleRoomPo enterBattleRoomPo = new EnterBattleRoomPo();
//				enterBattleRoomPo.setRoomId("1");
//				e.getChannel().write(enterBattleRoomPo);
//			}
//			break;
//		case -574288:
//			EnterBattleRoomPo_Rp enterBattleRoomPo_Rp = (EnterBattleRoomPo_Rp) vo;
//			int success = enterBattleRoomPo_Rp.getSuccess();
//			List<BattleRoomPlayerInfoPo> list = enterBattleRoomPo_Rp.getBattleRoomPlayerList();
//			if(success == 1){
//				myseatId = enterBattleRoomPo_Rp.getSeatId();
//				System.out.println("enterBattleRoomPo_Rp：进入房间成功!seatID：" + enterBattleRoomPo_Rp.getSeatId() + "房间人数：" + list.size());
//			}
//			break;
//		case 574291:
//			BattleRoomPlayerListPo battleRoomPlayerListPo = (BattleRoomPlayerListPo) vo;
//			int size = battleRoomPlayerListPo.getBattleRoomPlayerList().size();
//			System.out.println("battleRoomPlayerListPo：当前房间人数：" + size);
//			if(battleRoomPlayerListPo.getRoomState() == 6 && size == 2){
//				//System.out.println("开始加载资源：40%");
//				LoadingResourceProcessPo loadingResourceProcessPo = new LoadingResourceProcessPo();
//				loadingResourceProcessPo.setProcess(40);
//				e.getChannel().write(loadingResourceProcessPo);
//				
//				Thread.sleep(1000);
//				LoadingResourceProcessPo loadingResourceProcessPo2 = new LoadingResourceProcessPo();
//				loadingResourceProcessPo2.setProcess(100);
//				e.getChannel().write(loadingResourceProcessPo2);
//			}
//			break;
//		case -574292:
//			LoadingResourceProcessPo_Rp loadingResourceProcessPo_Rp = (LoadingResourceProcessPo_Rp) vo;
//			int successLoading = loadingResourceProcessPo_Rp.getSuccess();
//			if(successLoading == 0){
//				System.out.println("资源加载出错.....");
//			}
//			break;
//		case 999:
//			ExcptionAdvicePo excptionAdvicePo_Rp = (ExcptionAdvicePo) vo;
//			System.out.println("ExcptionAdvicePo_Rp：异常信息--" + excptionAdvicePo_Rp.getExcptionMsg());
//			break;
//		case 574299:
//			BattleBeginPlayerListPo battleBeginPlayerListPo = (BattleBeginPlayerListPo)vo;
//			if(battleBeginPlayerListPo.getRoomState() == 10){
//				System.out.println("游戏开始!");
//			}else{
//				break;
//			}
//			List<BattleBeginPlayerInfoPo> beginPlayerInfoPos = battleBeginPlayerListPo.getBattleRoomPlayerList();
//			for (BattleBeginPlayerInfoPo battleBeginPlayerInfoPo : beginPlayerInfoPos) {
//				System.out.println("开战玩家：" + battleBeginPlayerInfoPo.getPlayerName());
//			}
//			break;
//		case 574293:
//			AtbInfoList atbInfoList = (AtbInfoList)vo;
//			int seatId = atbInfoList.getActionSeatId();
//			List<AtbInfo> atbInfos = atbInfoList.getAtbList();
//			for (AtbInfo atbInfo : atbInfos) {
//				System.out.println("Atb广播..");
//				System.out.println("座位号:" + atbInfo.getSeatId() + "  开始百分比:" + atbInfo.getAtbBeginPercent() 
//						+ "  结束百分比:" + atbInfo.getAtbEndPercent());
//			}
//			System.out.println("当前活动座位" + seatId);
//			if(seatId == myseatId){
//				System.out.println("我的回合");
//				AttackPo attackPo = new AttackPo();
//				attackPo.setIsHit(RandomUtil.nextRandomInt(0, 1));
//				attackPo.setDamage(RandomUtil.nextRandomInt(1, 50));
//				attackPo.setTargetSeatId(myseatId == 0 ? 1 : 0);
//				e.getChannel().write(attackPo);
//			}
//			break;
//		case 574300:
//			ResultPo resultPo = (ResultPo) vo;
//			if(resultPo.getResult() == 1){
//				System.out.println("胜利!");
//			}else{
//				System.out.println("失败!");
//			}
//			break;
//		case -574296:
//			//EndTurnPo_Rp endTurnPo_Rp = (EndTurnPo_Rp) vo;
//			System.out.println("End Turn!");
//			break;
//		case 574295:
//			BattleWalkInfoPo battleWalkInfoPo = (BattleWalkInfoPo) vo;
//		 	System.out.println("x坐标:" + battleWalkInfoPo.getX() + ",y坐标:" + battleWalkInfoPo.getY());
//			break;
//		case 574297:
//			AttackPo attackPo = (AttackPo) vo;
//			if(attackPo.getIsHit() == 1){
//				int seatState = attackPo.getSeatState();
//				System.out.println("座位:" + attackPo.getTargetSeatId() + "玩家受到" + attackPo.getDamage() + "伤害!" 
//						+ (seatState == 0 ? "玩家死亡" : ""));
//			}else{
//				System.out.println("未击中" + "座位:" + attackPo.getTargetSeatId() + "玩家");
//			}
//			break;
//		case -574297:
//			AttackPo_Rp attackPo_Rp = (AttackPo_Rp) vo;
//			int isSuccess = attackPo_Rp.getIsSuccess();
//			if(isSuccess == 1){
//				EndTurnPo endTurnPo = new EndTurnPo();
//				endTurnPo.setIsEnd(1);
//				e.getChannel().write(endTurnPo);
//			}
//			break;
//		case 574302:
//			LoadingProcessListPo loadingProcessListPo = (LoadingProcessListPo) vo;
//			List<LoadingProcessInfo> loadingProcessListPos = loadingProcessListPo.getLoadingProcessList();
//			for (LoadingProcessInfo loadingProcessInfo : loadingProcessListPos) {
//				System.out.println("广播进度条..");
//				System.out.println("座位号:" + loadingProcessInfo.getSeatId() + "  进度条:" + loadingProcessInfo.getProcess());
//			}
//		case 9001:
//			System.out.println("刷新房间");
//			break;
//		default:
//			System.out.println("未知传输包" + commandId);
//		}
//		log.info("返回的vo:"+object.getClass().getSimpleName()+",SerialId:"+vo.getSerialId());
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		log.info("客户端关闭!");
		NettyConnectEngine.setConnectState(EnumConnectState.channelClosed);
		//ctx.getChannel().close();
		//重新打开channel
		//NettyClient.getInstance().reConnect();
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		log.info("channel打开");
	}
	
	
}

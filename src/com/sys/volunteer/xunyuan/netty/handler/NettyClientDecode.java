package com.sys.volunteer.xunyuan.netty.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.Log4JLoggerFactory;

import com.sys.volunteer.xunyuan.protocol.charge.ChargePo_Rp;
import com.sys.volunteer.xunyuan.protocol.error.ErrorPo_Rp;
import com.sys.volunteer.xunyuan.protocol.heartbeat.HeartBeatPo_Rp;
import com.sys.volunteer.xunyuan.protocol.login.LoginPo_Rp;
import com.sys.volunteer.xunyuan.protocol.login.LogoutPo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryPo_Rp;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo_Rp;

public class NettyClientDecode extends FrameDecoder {

	InternalLogger logger = Log4JLoggerFactory
			.getInstance(NettyClientDecode.class);

	byte[] packagetHeader = { 'a', 'p', 'v', 'o' };// 包头

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {

		 logger.info("进入ClientDecode!");

		// if (buffer.readableBytes() < 4 + 4 + 4 + 4 + 8) {
		// logger.warn("接收的数据还没够!");
		// return null;
		// }
		//	
		// //判断包头是否吻合
		// boolean isHeader = false;
		//
		// if(buffer.readByte() == packagetHeader[0]){
		// if(buffer.readByte() == packagetHeader[1]){
		// if(buffer.readByte() == packagetHeader[2]){
		// if(buffer.readByte() == packagetHeader[3]){
		// isHeader = true;
		// }
		// }
		// }
		// }
		//		
		// if(!isHeader){
		// return null;
		// }
		//
		//		
		// buffer.readByte();
		// buffer.readByte();
		// buffer.readByte();
		// buffer.readByte();
		// int originalLen = buffer.readInt();
		// buffer.readInt();
		// buffer.readBytes(8).array();

		// if(buffer.readableBytes() < originalLen){
		// System.out.println("不符合长度");
		// return null;
		// }
		//		
		// byte[] msg = buffer.readBytes(originalLen).array();
		//		
		// ByteArrayInputStream inputStream = new ByteArrayInputStream(msg);
		//		
		// //TODO:这里需要转化为serialId
		// int commandId = PackageUtil.DecodeInteger(inputStream);
		//
		// String requestClass = Context.protocolMap.get(commandId);
		//
		// if(requestClass == null){
		// logger.error("未处理协议："+commandId);
		// return null;
		// }
		//		
		// Class<?> request = Class.forName(requestClass);
		// Constructor<?> constructor = request
		// .getConstructor(ByteArrayInputStream.class);
		// Object object = constructor.newInstance(inputStream);
		//		
		// System.out.println(object);
		//		
		// return object;

		// 测试数据
		String length = new String(buffer.readBytes(4).array());
		System.out.println("长度为:"+length);
		// String serialId = new String(buffer.readBytes(10).array());
		// System.out.println("序列号为:"+serialId);
		// String businiessType = new String(buffer.readBytes(4).array());
		// System.out.println("消息类型:"+businiessType);
		// String msgCode = new String(buffer.readBytes(6).array());
		// System.out.println("消息码为:"+msgCode);
		// String respCode = new String(buffer.readBytes(4).array());
		// System.out.println("响应码为:"+respCode);
		// return null;
		//		
		// if (serialId.equals("800001")) {
		// //登陆报文
		//			
		// }
		String serialId = new String(buffer.readBytes(10).array());
		String businessType = new String(buffer.readBytes(4).array());
		String msgCode = new String(buffer.readBytes(6).array());
		Object object = null;
		if (msgCode.equals(LoginPo_Rp.cmd)) {
			//登陆报文
			LoginPo_Rp rp = new LoginPo_Rp();
			rp = rp.decode(buffer,length,serialId,businessType,msgCode);
			System.out.println("响应代码为:"+rp.getRespCode());
			object = rp;
		}else if (msgCode.equals(LogoutPo_Rp.cmd)) {
			LogoutPo_Rp rp = new LogoutPo_Rp();
			rp = rp.decode(buffer, length, serialId,businessType,msgCode);
			System.out.println(rp.getBusinessType());
			object = rp;
		}else if (msgCode.equals(HeartBeatPo_Rp.cmd)) {
			//心跳报文
			HeartBeatPo_Rp rp = new HeartBeatPo_Rp();
			rp = rp.decode(buffer, length, serialId,businessType,msgCode);
			object = rp;
		}else if (msgCode.equals(ChargePo_Rp.cmd)) {
			//充值报文
			ChargePo_Rp rp = new ChargePo_Rp();
			rp = rp.decode(buffer, length, serialId,businessType,msgCode);
			System.out.println("充值完成!返回码:"+rp.getRespCode());
			object = rp;
		}else if (msgCode.equals(ErrorPo_Rp.cmd)) {
			//通用报错报文
			ErrorPo_Rp rp = new ErrorPo_Rp();
			rp = rp.decode(buffer, length, serialId,businessType,msgCode);
			System.out.println("报错代码:"+rp.getMsgCode());
			object = rp;
		}else if (msgCode.equals(QueryPo_Rp.cmd)) {
			QueryPo_Rp rp = new QueryPo_Rp();
			rp = rp.decode(buffer, length, serialId, businessType, msgCode);
			System.out.println("查询订单,返回码:"+rp.getRespCode());
			object = rp;
		}else if (msgCode.equals(ReversePo_Rp.cmd)) {
			ReversePo_Rp rp = new ReversePo_Rp();
			rp = rp.decode(buffer, length, serialId, businessType, msgCode);
			System.out.println("冲正返回:"+rp.getRespCode());
			object = rp;
		}else if (msgCode.equals(QueryBalancePo_Rp.cmd)) {
			QueryBalancePo_Rp rp = new QueryBalancePo_Rp();
			rp = rp.decode(buffer, length, serialId, businessType, msgCode);
			object = rp;
		}
		return object;
	}

}

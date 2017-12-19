package com.sys.volunteer.xunyuan.netty.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.Log4JLoggerFactory;

import com.sys.volunteer.xunyuan.protocol.BasePo;

public class NettyClientEncode extends OneToOneEncoder{
	
	InternalLogger log = Log4JLoggerFactory.getInstance(NettyClientEncode.class);
	
	byte[] packagetHeader = {'a','p','v','o'};//包头
	
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object object) throws Exception {
		
		log.info("进入ClientEncode!");
		
//		if(!(object instanceof BasePo)){
//			return object;
//		}
//		
		BasePo vo = (BasePo)object;
//		
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				
//		vo.encodeVo(baos);
		
		//int msgType=3;//测试类型
//		
//		byte[] msgBytes = baos.toByteArray();//vo
//		
		ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
		
		//验证报文长度
		if (vo.validatePoLength()) {
			channelBuffer = vo.encodeVo(channelBuffer);
		}else {
			log.error("报文长度不对!请检查");
		}
		
		/**
		 * 预留字段	+	终端类型 +   加密方式      +  消息类型  		1byte+1byte+1byte+1byte
				0		手机到服务器 1	0		现在使用协议=3
						Flash到服务器 2			AMF =1
						服务器到客户�?
		 */		
//		byte[] arr = new byte[4];
//		arr[0] = (byte)(0);
//		arr[1] = (byte)(3);
//		arr[2] = (byte)(0);
//		arr[3] = (byte)(3);
//		channelBuffer.writeBytes(arr);
//		
//		channelBuffer.writeInt(msgBytes.length);//写长度
//		
//		byte[] verifyBuf = new byte[8];
//		channelBuffer.writeBytes(verifyBuf);//写包验证
		
//		channelBuffer.writeBytes(msgBytes);//写vo
		
		//测试数据
//		channelBuffer.writeBytes("0076".getBytes());
//		channelBuffer.writeBytes("0000000001".getBytes());
//		channelBuffer.writeBytes("    ".getBytes());
//		channelBuffer.writeBytes("000001".getBytes());
//		channelBuffer.writeBytes("00000000".getBytes());
//		channelBuffer.writeBytes("02000500".getBytes());
//		MD5 md5 = MD5.getiInstance();
//		String pwd = md5.getMD5ofStr("123");
//		channelBuffer.writeBytes(pwd.getBytes());
//		channelBuffer.writeBytes("0010".getBytes());]
		
		log.info("压包");
		
		return channelBuffer;
	}

}

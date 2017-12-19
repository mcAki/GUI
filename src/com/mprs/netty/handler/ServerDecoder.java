package com.mprs.netty.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.mprs.protocol.BasePo;
import com.mprs.util.PackageUtil;

/**
 * 消息解码，实际上主要是封装成IsoMessage类
 * 
 * @author 郭熹
 * 
 */
public class ServerDecoder extends FrameDecoder {
	protected Log logger = LogFactory.getLog(this.getClass());

	byte[] packagetHeader = {'a','p','v','o'};
	
	/**
	 * 解开1个包
	 * @param buffer
	 * @param basePo
	 * @return
	 */
	private BasePo decodeProtocol( ChannelBuffer buffer){
		
		// 消息类型缓冲
//		byte[] msgTypeBuf = new byte[4];
//		// 长度缓冲
//		byte[] lenBuf = new byte[4];
//		
//		buffer.readBytes(msgTypeBuf);
//		buffer.readBytes(lenBuf);
//				
//		// 包长度
//		int dataLength = PackageUtil.DecodeInteger(lenBuf, 0);
//		int msgType = PackageUtil.DecodeInteger(msgTypeBuf, 0);
//
//		//TODO：这里写校验包内容是否正确
//		// 包验证缓冲
////		byte[] verifyBuf = new byte[8];
////		buffer.readBytes(verifyBuf);
////		
////		if (buffer.readableBytes() < dataLength) {
////			logger.info("接收的totalLength长度还不够:" + dataLength);
////			return null;
////		}
//		
//		
//		//buffer.skipBytes(4 + 4 + 4 + 8);
//		
//		byte[] data = new byte[dataLength];
//		buffer.readBytes(data, 0, dataLength);
//		
//		// 长度缓冲
//		if (buffer.readableBytes() > 0) {
//			System.out.println("@@有剩流：" + buffer.readableBytes());
//		}
//
//		// 测试用打印出包
//		// SysUtil.debugData(data);
//
//		BasePo incoming = null;
//		
//		try {
//			// incoming = PackageUtil.read(data);
//			if (msgType == PackageUtil.msgType_AMF) {
//				incoming = (BasePo) PackageUtil.decodeAMFObj(data);
//			} else if (msgType == PackageUtil.msgType_Ages_Protocol) {
//				incoming = PackageUtil.read(data);
//			}
////			ProtocolContainer pc = Context.getInstance().getProtocolMap().get(
////				incoming.getCommandId());
//
//			// 打印收到的协议
////			logger.info("SLOG!!! ReceiveProtocol Id：" + incoming.getCommandId() + " Class:[ "
////						+ pc.getClassName() + " ] Fn:[ " + pc.getComment() + " ]");
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.info("解AMF包错误");
//			return null;
//		}
//		return incoming;
		return null;
	
	}
	
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) {
		logger.info("进入ServerDecoder");

 		
 		
//		byte[] lookforByte = new byte[1];
//			
//		boolean isFoundHeader = false;
//		while(buffer.readableBytes()>0 && (!isFoundHeader)) {
//			
//			if (buffer.readableBytes() < 4 + 4 + 4 + 8) {
//				logger.info("接收的数据长度小于 最少规范长度,所以不处理");
//				return null;
//			}				
//			
//			buffer.readBytes(lookforByte);			
//			if(lookforByte[0]==packagetHeader[0]){
//				
//				buffer.readBytes(lookforByte);
//				if(lookforByte[0]==packagetHeader[1]){
//					
//					buffer.readBytes(lookforByte);
//					if(lookforByte[0]==packagetHeader[2]){
//						
//						buffer.readBytes(lookforByte);
//						if(lookforByte[0]==packagetHeader[3]){
//							
//							//包头吻合，可以处理
//							isFoundHeader=true;
//						}
//					}
//				}
//			}
//			
//		}
//		
//		if(!isFoundHeader){
//			return null;
//		}

		//return decodeProtocol(buffer);
		if (buffer.readableBytes()<4) {
			return null;
		}
		return buffer.readBytes(4);

/*
		// 包头缓冲
		byte[] headBuf = new byte[4];
		// 消息类型缓冲
		byte[] msgTypeBuf = new byte[4];
		// 长度缓冲
		byte[] lenBuf = new byte[4];
		// 包验证缓冲
		byte[] verifyBuf = new byte[8];

		buffer.readBytes(headBuf);

		// 比对包头字节
		if (!PackageUtil.compareByte(
			Context.getInstance().getProtocolSetting().getPackageHeaderBytes(), headBuf)) {
			// 如果包头不同应该不处理直接退出的
			logger.info("AMF包头不对");
		}

		buffer.readBytes(msgTypeBuf);
		buffer.readBytes(lenBuf);
		buffer.readBytes(verifyBuf);

		// getBytes 不会移动指针，readBytes会移动读取指针
		// buffer.getBytes(buffer.readerIndex(), lenBuf);
		// buffer.getBytes(buffer.readerIndex() + 4, verifyBuf);

		// 包长度
		int dataLength = PackageUtil.DecodeInteger(lenBuf, 0);
		int msgType = PackageUtil.DecodeInteger(msgTypeBuf, 0);
		// int totalLength = dataLength + 4 + 8;

		// logger.info("需要读取的数据长度为：" + dataLength + ",总长度：" + totalLength);

		if (buffer.readableBytes() < dataLength) {
			logger.info("接收的totalLength长度还不够:" + dataLength);
			return null;
		}
		// buffer.skipBytes(4 + 8);
		byte[] data = new byte[dataLength];
		buffer.readBytes(data, 0, dataLength);
		// 长度缓冲
//		byte[] endBuf = new byte[4];
//		buffer.readBytes(endBuf);
		if (buffer.readableBytes() > 0) {
			System.out.println("@@@ 有剩包  @@@：" + buffer.readableBytes());
		}

		// TODO:测试用打印出包
		// SysUtil.debugData(data);

		BasePo incoming = null;
		try {
			// incoming = PackageUtil.read(data);
			if (msgType == PackageUtil.msgType_AMF) {
				incoming = (BasePo) PackageUtil.decodeAMFObj(data);
			} else if (msgType == PackageUtil.msgType_Ages_Protocol) {
				incoming = PackageUtil.read(data);
			}
			ProtocolContainer pc = Context.getInstance().getProtocolMap().get(
				incoming.getCommandId());

			// 打印收到的协议
			logger.info("SLOG!!! ReceiveProtocol Id：" + incoming.getCommandId() + " Class:[ "
						+ pc.getClassName() + " ] Fn:[ " + pc.getComment() + " ]");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return incoming;
		*/
	}
}
// if (buffer.readableBytes() < 8) {
// logger.info("接收的数据长度小于8");
// return null;
// }
// int commandId=buffer.getInt(buffer.readerIndex());
// if(commandId==Const.CommandConstant.MESS_PING){//客户端发送长连接的PING消息
// channel.write(Const.CommandConstant.MESS_PING);
// logger.info("返回PING消息给客户端");
// return null;
// }else{
// int dataLength=buffer.getInt(buffer.readerIndex()+4);
//			
// if (buffer.readableBytes() < dataLength + 8) {
// logger.info("接收的dataLength长度还不够:"+dataLength);
// return null;//(2)
// }
// buffer.skipBytes(8);
// ChannelBuffer dst=ChannelBuffers.buffer(dataLength);
// buffer.readBytes(dst,0,dataLength);
//	        
// dst.setIndex(0,
// dataLength);//这个非常重要，重设readIndex,writeIndex,使ChannelBuffer重新计算readableBytes(),不然后续使用会抛出异常，这是阅读源码才发现的
// BaseVo vo=new BaseVo();
// vo.setCommandId(commandId);
// vo.setDataLength(dataLength);
// vo.setChannelBuffer(dst);
//			
// logger.info("解码结束,进入消息处理流程");
// return vo;
// }

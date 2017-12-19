package com.sys.volunteer.xunyuan.protocol.error;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.xunyuan.protocol.BasePo;

public class ErrorPo_Rp extends BasePo {

	public final static String cmd = "000110";
	public final static String reflectClassName = "ErrorPo_Rp";
	
	
	public ErrorPo_Rp decode(ChannelBuffer channelBuffer, String length,
			String serialId,String businessType, String msgCode) throws Exception {
		setLength(length);
		setSerialId(serialId);
		setBusinessType(businessType);
		setMsgCode(msgCode);
		setRespCode(new String(channelBuffer.readBytes(4).array()));
		return this;
	}
	
	@Override
	public ChannelBuffer encodeVo(ChannelBuffer channelBuffer) throws Exception {
		return null;
	}

	@Override
	public String getCommandId() {
		return cmd;
	}

	@Override
	public boolean validatePoLength() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}

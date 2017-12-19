package com.sys.volunteer.xunyuan.protocol.login;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.xunyuan.protocol.BasePo;

public class LogoutPo_Rp extends BasePo {
	
	public final static String cmd = "800002";
	public final static String reflectClassName = "LogoutPo_Rp";

	public LogoutPo_Rp decode(ChannelBuffer channelBuffer, String length,
			String serialId,String businessType, String msgCode) throws Exception {
		setLength(length);
		setSerialId(serialId);
		setBusinessType(businessType);
		setMsgCode(msgCode);
		return this;
	}

	@Override
	public ChannelBuffer encodeVo(ChannelBuffer channelBuffer) throws Exception {
		return channelBuffer;

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

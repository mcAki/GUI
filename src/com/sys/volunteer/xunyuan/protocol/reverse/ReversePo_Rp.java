package com.sys.volunteer.xunyuan.protocol.reverse;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.xunyuan.protocol.BasePo;

public class ReversePo_Rp extends BasePo {
	
	public final static String cmd = "800004";
	public final static String reflectClassName = "ReversePo_Rp";
	
	private String xunyuanSeq;
	private String reverseStoreSeq;
	private String reverseTime;
	private String storeSeq;

	public ReversePo_Rp decode(ChannelBuffer channelBuffer, String length,
			String serialId,String businessType, String msgCode) throws Exception {
		setLength(length);
		setSerialId(serialId);
		setBusinessType(businessType);
		setMsgCode(msgCode);
		setRespCode(new String(channelBuffer.readBytes(4).array()));
		setXunyuanSeq(new String(channelBuffer.readBytes(32).array()));
		setReverseStoreSeq(new String(channelBuffer.readBytes(32).array()));
		setReverseTime(new String(channelBuffer.readBytes(14).array()));
		setStoreSeq(new String(channelBuffer.readBytes(20).array()));
		return this;
	}

	@Override
	public ChannelBuffer encodeVo(ChannelBuffer channelBuffer) throws Exception {
		return null;
	}

	public String getXunyuanSeq() {
		return xunyuanSeq;
	}

	public void setXunyuanSeq(String xunyuanSeq) {
		this.xunyuanSeq = xunyuanSeq;
	}

	public String getStoreSeq() {
		return storeSeq;
	}

	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}

	public String getReverseStoreSeq() {
		return reverseStoreSeq;
	}

	public void setReverseStoreSeq(String reverseStoreSeq) {
		this.reverseStoreSeq = reverseStoreSeq;
	}

	public String getReverseTime() {
		return reverseTime;
	}

	public void setReverseTime(String reverseTime) {
		this.reverseTime = reverseTime;
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

package com.sys.volunteer.xunyuan.protocol.charge;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.xunyuan.protocol.BasePo;

public class ChargePo_Rp extends BasePo {
	
	public final static String cmd = "800005";
	public final static String reflectClassName = "ChargePo_Rp";
	
	private String xunyuanSeq;
	private String chargeTime;
	private String corpBalance;
	private String balance;
	private String storeSeq;

	public ChargePo_Rp decode(ChannelBuffer channelBuffer, String length,
			String serialId,String businessType, String msgCode) throws Exception {
		setLength(length);
		setSerialId(serialId);
		setBusinessType(businessType);
		setMsgCode(msgCode);
		setRespCode(new String(channelBuffer.readBytes(4).array()));
		setXunyuanSeq(new String(channelBuffer.readBytes(32).array()));
		setChargeTime(new String(channelBuffer.readBytes(14).array()));
		setCorpBalance(new String(channelBuffer.readBytes(16).array()));
		setBalance(new String(channelBuffer.readBytes(16).array()));
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

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	public String getCorpBalance() {
		return corpBalance;
	}

	public void setCorpBalance(String corpBalance) {
		this.corpBalance = corpBalance;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getStoreSeq() {
		return storeSeq;
	}

	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
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

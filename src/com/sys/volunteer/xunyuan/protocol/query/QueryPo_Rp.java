package com.sys.volunteer.xunyuan.protocol.query;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.xunyuan.protocol.BasePo;

public class QueryPo_Rp extends BasePo{
	
	public final static String cmd = "800007";
	public final static String reflectClassName = "QueryPo_Rp";

	private String xunyuanSeq;
	private String tradeTime;
	private String corpBalance;
	private String balance;
	private String storeSeq;

	public QueryPo_Rp decode(ChannelBuffer channelBuffer, String length,
			String serialId,String businessType, String msgCode) throws Exception {
		setLength(length);
		setSerialId(serialId);
		setBusinessType(businessType);
		setMsgCode(msgCode);
		setRespCode(new String(channelBuffer.readBytes(4).array()));
		setXunyuanSeq(new String(channelBuffer.readBytes(32).array()));
		setTradeTime(new String(channelBuffer.readBytes(14).array()));
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

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
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

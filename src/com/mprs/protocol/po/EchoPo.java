package com.mprs.protocol.po;

import com.mprs.protocol.BasePo;

/**
 *Echo测试包
 */
@SuppressWarnings("serial")
public class EchoPo extends BasePo{

	public EchoPo() {
		super();
		commandId=1;
	}

	/**
	 *测试字符串
	 */
	private String echoStr; 

	/**
	 *测试数字
	 */
	private int echoInt; 

	/**
	 *测试集合整型
	 */
	private java.util.List<Integer> echoList; 

	/**
	 *测试字符串
	 */
	public void setEchoStr(String echoStr){
		this.echoStr=echoStr;
	}

	/**
	 *测试字符串
	 */
	public String  getEchoStr(){  
		return echoStr; 
	}
	/**
	 *测试数字
	 */
	public void setEchoInt(int echoInt){
		this.echoInt=echoInt;
	}

	/**
	 *测试数字
	 */
	public int  getEchoInt(){  
		return echoInt; 
	}
	/**
	 *测试集合整型
	 */
	public void setEchoList(java.util.List<Integer> echoList){
		this.echoList=echoList;
	}

	/**
	 *测试集合整型
	 */
	public java.util.List<Integer> getEchoList(){  
		return echoList; 
	}

}
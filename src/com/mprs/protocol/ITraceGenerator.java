package com.mprs.protocol;

/** 
 * 交易
 * @author admin
 */
public interface ITraceGenerator {

	/** Returns the next trace number. */
	public int nextTrace();

	/** Returns the last number that was generated. */
	public int getLastTrace();

}

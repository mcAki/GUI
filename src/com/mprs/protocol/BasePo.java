package com.mprs.protocol;

import java.io.Serializable;

/**
 * 基础网络VO对象
 * 
 * @author Seraph
 * 
 */
public abstract class BasePo implements Serializable, IPackageObject {

	private static final long serialVersionUID = -2503482652061267695L;
	/**
	 * 跟踪ID，随机生成
	 */
	protected int trackId = 0; 
	
	/**
	 * 命令号，让传输包继承时重载
	 */
	protected int commandId = 0;

	protected int terminalType=1;
	
	

	/**
	 * 获取命令号
	 */
	public int getCommandId() {
		return commandId;
	}

	/**
	 * 跟踪ID
	 * @return
	 */
	public int getTrackId() {
		return trackId;
	}

	/**
	 * 跟踪ID
	 * @param trackId
	 */
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public int getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(int terminalType) {
		this.terminalType = terminalType;
	}
	

}

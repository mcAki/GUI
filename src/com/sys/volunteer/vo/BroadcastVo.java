package com.sys.volunteer.vo;


public class BroadcastVo{

	/**
	 * 广播唯一uuid
	 */
	private String uuid;
	/**
	 * 广播次数
	 */
	private Integer times;
	/**
	 * 间隔(秒)
	 */
	private Integer interval;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 类型:1.滚屏 2.聊天栏 3.所有
	 */
	private Integer type;
	/**
	 * 返回
	 */
	private Integer result;
	
	public Integer getTimes() {
		return times;
	}
	
	public void setTimes(Integer times) {
		this.times = times;
	}
	
	public Integer getInterval() {
		return interval;
	}
	
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

}

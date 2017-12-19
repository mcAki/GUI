package com.mprs.util;

/**
 * 常量定义
 * @author 郭熹
 *
 */
public class Const {

	public static final class CommandConstant{//定义各种从客户端到达的消息类型
		public static final int MESS_PING=1000;//PING消息
		public static final int MESS_LOGNI=1001;//登录消息
		public static final int MESS_REGISTER=1002;//注册消息
	}

	public static final int BUFFER_SIZE = 1024;
	
	/**
	 * 游戏记录
	 * @author admin
	 *
	 */
	public class GameLog{
		/**
		 * 未知
		 */
		public static final int UNKNOWN = 0;
		/**
		 * 开房
		 */
		public static final int CREATE_ROOM = 10;
		/**
		 * 开始游戏
		 */
		public static final int START_GAME = 20;
		/**
		 * 充值
		 */
		public static final int RECHARGE = 30;
		/**
		 * 购买道具
		 */
		public static final int BUY_ITEM = 40;
	}
}

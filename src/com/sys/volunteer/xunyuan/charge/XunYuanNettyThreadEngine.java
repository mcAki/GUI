package com.sys.volunteer.xunyuan.charge;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;

import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.xunyuan.XunYuanService;
import com.sys.volunteer.xunyuan.netty.client.NettyClient;
import com.sys.volunteer.xunyuan.netty.core.NettyConnectEngine;
import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.xunyuan.netty.core.NettyConnectEngine.EnumConnectState;
import com.sys.volunteer.xunyuan.protocol.BasePo;
import com.sys.volunteer.xunyuan.protocol.charge.ChargePo;
import com.sys.volunteer.xunyuan.protocol.heartbeat.HeartBeatPo;
import com.sys.volunteer.xunyuan.protocol.login.LoginPo;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo;
import com.sys.volunteer.xunyuan.protocol.query.QueryPo;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo;

public class XunYuanNettyThreadEngine extends Thread {

	protected Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 执行任务休眠间隔（毫秒）
	 */
	private int executeTaskSleepInterval = 10000;

	/**
	 * 线程必须刷新时间
	 */
	private long refreshTime = 0;
	
	/**
	 * 最近心跳时间
	 */
	private long heartbeatTime = 0;

	private static XunYuanNettyThreadEngine instance = null;
	
	private static LinkedList<BasePo> sendList;
	
	private LinkedList<BasePo> recList;
	
	private LinkedList<Mainorder> chargeList;
	
	private LinkedList<Mainorder> queryList;
	
	private LinkedList<Mainorder> reverseList;
	
	protected static EnumThreadState threadState;
	
	protected Channel channel;
	
	protected ClientBootstrap bootstrap;
	
	protected NettyClient client;
	
	private static XunYuanService xunYuanService;
	
	private static OrderService orderService;
	
	private static boolean isLogin;
	
	/**
	 * 线程状态
	 * @author Administrator
	 *
	 */
	public enum EnumThreadState{
		/**
		 * 登陆
		 */
		login,
		
		loginRp,
		/**
		 * 心跳
		 */
		heartbeat,
		
		heartbeatRp,
		/**
		 * 充值
		 */
		charge,
		
		chargeRp,
		/**
		 * 冲正
		 */
		reverse,
		
		reverseRp,
		/**
		 * 查询订单
		 */
		queryOrder,
		
		queryOrderRp,
		/**
		 * 查询余额
		 */
		queryBalance,
		
		queryBalanceRp,
		/**
		 * 注销
		 */
		logout,
		
		logoutRp,
		/**
		 * 错误
		 */
		error
	}
	
	/**
	 * 获取实例化管理器
	 * 
	 * @return
	 */
	public static XunYuanNettyThreadEngine getInstance() {
		if (instance == null) {
			instance = new XunYuanNettyThreadEngine();
		}
		return instance;
	}

	/**
	 * 构造器
	 * 
	 * @param producerPool
	 */
	private XunYuanNettyThreadEngine() {
//		threadState = EnumThreadState.heartbeat;
//		chargeList = new LinkedList<Mainorder>();
//		queryList = new LinkedList<Mainorder>();
//		reverseList = new LinkedList<Mainorder>();
//		rpContainer = RpContainer.getInstance();
		sendList = new LinkedList<BasePo>();
		recList = new LinkedList<BasePo>();
		isLogin = false;
	}

	
	
	
	@Override
	public void destroy() {

	}
	
	@Override
	public void run() {
		while(true){
			
			if (System.currentTimeMillis() > refreshTime) {
				refreshTime = System.currentTimeMillis()
						+ executeTaskSleepInterval;
			} else {
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			
			if (NettyConnectEngine.getConnectState().equals(EnumConnectState.connected)) {
				channel = NettyClient.getInstance().getChannel();
				
				if (!isLogin) {
					channel.write(new LoginPo());
				}else {
					if (System.currentTimeMillis() - heartbeatTime >= 10000) {
						channel.write(new HeartBeatPo());
					}
					
				}
				
				
				try {
					//1.检测是否连接
					//2.发送心跳包
					//3.组成请求VO,发送
					
					//changeState();
					System.out.println("发送列表size================"+sendList.size()+"=====");
					while (sendList.size()>0) {
						logger.info("进入讯源线程");
						BasePo po = sendList.getFirst();
						sendList.removeFirst();
						sendPo(po);
					}
				} catch (Exception e) {
					logger.error("讯源netty流程出现错误!");
					e.printStackTrace();
				}
			}
		}
		
	}
	
//	private void changeState(){
//		ApplicationContext act = SpringContextUtil.getApplicationContext();
//		xunYuanService = (XunYuanService) act.getBean("xunYuanServiceBean");
//		orderService = (OrderService) act.getBean("orderServiceBean");
//		if (threadState == EnumThreadState.login) {
//			channel.write(new LoginPo());
//			threadState = EnumThreadState.loginRp;
//		}else if (threadState == EnumThreadState.loginRp) {
//			LoginPo_Rp rp = rpContainer.getLoginPoRp();
//			if (rp == null) {
//				logger.error("登陆包没有返回!");
//				threadState = EnumThreadState.heartbeat;
//				return;
//			}
//			rpContainer.setLoginPoRp(null);
//			if (rp.getRespCode().equals("0000")) {
//				//登陆了成功
//				threadState = EnumThreadState.heartbeat;
//			}else {
//				//登陆失败,重登
//				logger.error("讯源登陆失败,返回码:"+rp.getRespCode());
//				threadState = EnumThreadState.login;
//			}
//		}else if (threadState == EnumThreadState.heartbeat) {
//			channel.write(new HeartBeatPo());
//			threadState = EnumThreadState.heartbeatRp;
//		}else if (threadState == EnumThreadState.heartbeatRp) {
//			HeartBeatPo_Rp rp = rpContainer.getHeartBeatPoRp();
//			if (rp == null) {
//				logger.error("心跳包没有返回!重新连接..");
//				threadState = EnumThreadState.login;
//				return;
//			}
//			rpContainer.setHeartBeatPoRp(null);
//			if (rp.getMsgCode().equals("800119")) {
//				//有心跳包返回
//				logger.info("讯源返回心跳");
//				//TODO:更新心跳时间
//				if (chargeList.size()>0) {
//					threadState = EnumThreadState.charge;
//					return;
//				}
//				if (queryList.size()>0) {
//					threadState = EnumThreadState.queryOrder;
//					return;
//				}
//				if (queryList.size()>0) {
//					threadState = EnumThreadState.queryOrder;
//					return;
//				}
//				threadState = EnumThreadState.heartbeat;
//			}else {
//				//没心跳了,重连
//				logger.error("心跳包返回了???重新连接..");
//				threadState = EnumThreadState.login;
//			}
//		}else if (threadState == EnumThreadState.charge) {
//			Mainorder mainorder = chargeList.getFirst();
//			chargeList.removeFirst();
//			//生成req
//			XunyuanChargeReq req = xunYuanService.initChargeReq(mainorder);
//			//初始化resp
//			xunYuanService.initChargeResp(req);
//			ChargePo chargePo = new ChargePo();
//			chargePo.setAmount(req.getAmount());
//			chargePo.setMobile(req.getMobile());
//			chargePo.setStoreSeq(req.getStoreSeq());
//			channel.write(chargePo);
//			threadState = EnumThreadState.chargeRp;
//		}else if (threadState == EnumThreadState.chargeRp) {
//			ChargePo_Rp chargePoRp = rpContainer.getChargePoRp();
//			if (chargePoRp == null) {
//				logger.error("充值包没有返回!");
//				return;
//			}
//			//重置rp
//			rpContainer.setChargePoRp(null);
//			//找到resp
//			XunyuanChargeResp resp = xunYuanService.findChargeRespByStoreSeq(chargePoRp.getStoreSeq());
//			xunYuanService.updateXunYuanChargeResp(resp, chargePoRp);
//			//生成查询
//			Mainorder mainorder = orderService.findOrderById(resp.getOrderId());
//			XunyuanQueryReq req = xunYuanService.initXunyuanQueryReq(mainorder, resp);
//			xunYuanService.initXunyuanQueryResp(req, mainorder);
//			threadState = EnumThreadState.heartbeat;
//		}else if (threadState == EnumThreadState.queryOrder) {
//			Mainorder mainorder = queryList.getFirst();
//			queryList.removeFirst();
//			XunyuanQueryReq xunyuanQueryReq = xunYuanService.findQueryReqByOrderId(mainorder.getMainOrderId());
//			QueryPo queryPo = new QueryPo();
//			queryPo.setAmount(xunyuanQueryReq.getAmount());
//			queryPo.setMobile(xunyuanQueryReq.getMobile());
//			queryPo.setQueryStoreSeq(xunyuanQueryReq.getQueryStoreSeq());
//			queryPo.setStoreSeq(xunyuanQueryReq.getStoreSeq());
//			channel.write(queryPo);
//			threadState = EnumThreadState.queryOrderRp;
//		}else if (threadState == EnumThreadState.queryOrderRp) {
//			QueryPo_Rp queryPoRp = rpContainer.getQueryPoRp();
//			if (queryPoRp == null) {
//				logger.error("查询订单包没有返回!");
//				return;
//			}
//			//重置
//			rpContainer.setQueryPoRp(null);
//			XunyuanQueryResp resp = xunYuanService.findQueryRespByStoreSeq(queryPoRp.getStoreSeq());
//			Mainorder mainorder = orderService.findOrderById(resp.getOrderId());
//			resp = xunYuanService.updateXunyuanQueryResp(resp, queryPoRp);
//			//刷新订单状态
//			xunYuanService.refreshOrderState(mainorder, resp);
//			threadState = EnumThreadState.heartbeat;
//		}else if (threadState == EnumThreadState.reverse) {
//			Mainorder mainorder = reverseList.getFirst();
//			chargeList.removeFirst();
//			//生成冲正req
//			XunyuanReverseReq req = xunYuanService.initReverseReq(mainorder);
//			//生成冲正resp
//			XunyuanReverseResp resp = xunYuanService.initReverseResp(req);
//			ReversePo reversePo = new ReversePo();
//			reversePo.setAmount(req.getAmount());
//			reversePo.setMobile(req.getMobile());
//			reversePo.setReverseStoreSeq(req.getReverseSeq());
//			reversePo.setStoreSeq(req.getStoreSeq());
//			channel.write(reversePo);
//		}else if (threadState == EnumThreadState.reverseRp) {
//			ReversePo_Rp rp = rpContainer.getReversePoRp();
//			if (rp == null) {
//				logger.error("讯源冲正订单包没有返回!");
//				return;
//			}
//			//重置
//			rpContainer.setReversePoRp(null);
//			XunyuanReverseResp resp = xunYuanService.findReverseRespByStoreSeq(rp.getStoreSeq());
//			xunYuanService.updateXunyuanReverseResp(resp, rp);
//		}
//	}
	
	public void sendPo(BasePo po) {
		if (po.getCommandId().equals(ChargePo.cmd)) {
			//充值报文
			ChargePo chargePo = (ChargePo)po;
			channel.write(chargePo);
		}else if (po.getCommandId().equals(ReversePo.cmd)) {
			//冲正报文
			ReversePo reversePo = (ReversePo)po;
			channel.write(reversePo);
		}else if (po.getCommandId().equals(QueryPo.cmd)) {
			//查询充值报文
			QueryPo queryPo = (QueryPo)po;
			channel.write(queryPo);
		}else if (po.getCommandId().equals(QueryBalancePo.cmd)) {
			System.out.println("==============发送查询报文===========");
			QueryBalancePo queryBalancePo = (QueryBalancePo) po;
			channel.write(queryBalancePo);
		}
	}
	
	
	@Override
	public synchronized void start() {
		this.setName("XunYuanNettyThreadEngine");
		super.start();
		logger.info("启动讯源接口");
	}

	/**
	 * 强制刷新
	 */
	public synchronized void forceRefreshOrder() {
		refreshTime = 0;
	}

	/**
	 * 执行任务休眠间隔（毫秒）
	 * 
	 * @param executeTaskSleepInterval
	 */
	public void setExecuteTaskSleepInterval(int executeTaskSleepInterval) {
		this.executeTaskSleepInterval = executeTaskSleepInterval;
	}

	public LinkedList<Mainorder> getChargeList() {
		return chargeList;
	}

	public void setThreadState(EnumThreadState threadState) {
		XunYuanNettyThreadEngine.threadState = threadState;
	}

	public LinkedList<Mainorder> getQueryList() {
		return queryList;
	}

	public LinkedList<Mainorder> getReverseList() {
		return reverseList;
	}

	public LinkedList<BasePo> getSendList() {
		return sendList;
	}

	public LinkedList<BasePo> getRecList() {
		return recList;
	}

	public long getHeartbeatTime() {
		return heartbeatTime;
	}

	public void setHeartbeatTime(long heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
	}

	public static boolean isLogin() {
		return isLogin;
	}

	public static void setLogin(boolean isLogin) {
		XunYuanNettyThreadEngine.isLogin = isLogin;
	}

	/**
	 * 增加队列
	 * @param po
	 */
	public void addSendList(BasePo po) {
		sendList.addLast(po);
	}

	public Channel getChannel() {
		return channel;
	}

	public ClientBootstrap getBootstrap() {
		return bootstrap;
	}
	
}


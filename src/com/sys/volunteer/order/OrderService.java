package com.sys.volunteer.order;

import java.util.Date;
import java.util.List;

import com.sys.volunteer.common.Const.BatchOrderState;
import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.muticharge.engine.OrderVO;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.vo.MainorderVO;
import com.sys.volunteer.vo.StatisticsOrderVO;

public interface OrderService extends IDao {

	public Mainorder addMainOrder(Mainorder mainorder, String mobile, Users user,
			SupplyInterface supplyInterface, int goodsNo,
			int isTerminal, String terminalNo, int orderType);

	public void addOrderDetail(Orderdetail orderdetail, Mainorder mainorder,
			List<CardLib> cardLibs);

	public Mainorder findOrderById(String orderId);

	public List<Orderdetail> findOrderdetailsByOrderId(String orderId);

	public void cancelOrder(Mainorder mainorder);

	public int reversalOrder(Mainorder mainorder);

	public void updateOrderState(Mainorder mainorder, int state);
	
	public void updateOrderCardLibs(Mainorder mainorder,List<CardLib> cardLibs);

	public List<MainorderVO> listMainorderVOs(Date startTime, Date endTime, String userId)
			throws Exception;

	public List<Mainorder> findProcessingOrder();
	
//	public List<Mainorder> findLocalOrder();

	public void updateReversalState(Mainorder mainorder, int reversalState);
	
	public void cancelOrderApply(Mainorder mainorder);
	
	public Mainorder addMainOrderRecharge(Mainorder lastMainorder,
			SupplyInterface supplyInterface);

	public void updateOrderRefreshTime(Mainorder mainorder, long time);

	public void updateOrderRespTime(Mainorder mainorder);
	public void updateOrderComments(Mainorder mainorder);

	public void updateMoneyBack(Mainorder mainorder, int moneyBack);

	public void updateCBalance(Mainorder mainorder, double cBalance);

	public Mainorder addMainOrderForTelecom(Mainorder mainorder, String mobile,
			Double value, Users currentUser, SupplyInterface supplyInterface,
			int goodsNo, int isTerminal, String terminalNo, int orderType);
	
	public List<OrderVO> findLimitFive(String userId );

	public List<Mainorder> findReversalOrderByMobileAndAmount(String mobile,
			Double amount);
	
	public List<StatisticsOrderVO> findStatisticsOrderVOEx(String userId,int isLocal,Date beginTime,Date endTime);
	public List<StatisticsOrderVO> findStatisticsOrderVOExEx(String userId,int isLocal,Date beginTime,Date endTime);

	public Mainorder addMainOrderForBatch(Mainorder mainorder, String mobile,
			Users currentUser, SupplyInterface supplyInterface, int goodsNo,
			int isTerminal, String terminalNo, int orderType,
			String batchOrderId, double totalMoney);

	public BatchOrder updateBatchOrder(BatchOrder batchOrder);

	BatchOrder addBatchOrder(BatchOrder batchOrder, String mobile,
			String mainorderId, double totalMoney, Users users,
			String userName, int goodsNo);

	public BatchOrder findBatchOrderById(String orderId);

	public List<Mainorder> findBatchOrderByOrderId(String orderId);

	public void updateBatchOrderState(BatchOrder batchOrder, int batchOrderState);

	public void updateBatchOrderState(Mainorder mainorder, int batchOrderState);

	public void updateBatchOrderState(List<Mainorder> mainorder, int batchOrderState);

	public BatchOrder updateBatchOrderMobileAndMainorderId(BatchOrder batchOrder,
			String mobile, String mainorderId, double value);

	public List<Mainorder> findBatchOrderByOrderIdAndState(String orderId,
			int batchOrderState);

	public Mainorder addMainOrderForGameRecharge(Mainorder mainorder, String mobile,
			Double value, Users currentUser, SupplyInterface supplyInterface,
			int goodsNo, int isTerminal, String terminalNo, int orderType, String at, String gameId, 
			String autoGameId, String atVerify, String clientIp);

	public Mainorder addMainOrderForQQ(Mainorder mainorder, String mobile,
			Double value, Users currentUser, SupplyInterface supplyInterface,
			int goodsNo, int isTerminal, String terminalNo, int orderType);
	
	
}

package com.sys.volunteer.jdbc.dao.orderDao;

import java.util.List;

import com.sys.volunteer.jdbc.dao.JdbcIDao;
import com.sys.volunteer.muticharge.engine.OrderVO;

public interface IMainorderDao extends JdbcIDao {

	public int countProcessingOrder();

	public List<OrderVO> findProcessingOrder();

	public OrderVO findById(String mainOrderId);

	public void updateReversalState(int state, String mainOrderId);

	public void updateOrderState(int state, String mainOrderId);

	public void updateRefreshTime(long refreshTime, String mainOrderId);

	public List<OrderVO> findApplyProcessOrder();
	
	public void updateQueryTimes(int times, String mainOrderId);

	public int countApplyProcessOrder();

	public void updateBatchOrderState(int state, String mainOrderId);

}

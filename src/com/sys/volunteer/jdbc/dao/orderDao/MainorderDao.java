package com.sys.volunteer.jdbc.dao.orderDao;

import java.sql.Types;
import java.util.List;

import javax.persistence.Version;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.jdbc.dao.JdbcCommonDao;
import com.sys.volunteer.muticharge.engine.OrderVO;

@Service
@Transactional
public class MainorderDao extends JdbcCommonDao implements IMainorderDao {

	/**
	 * 查找行数
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@Override
	public int countProcessingOrder() {
		String sql = "SELECT COUNT(*) FROM mainorder WHERE state in (3,254) OR reversal_state = 0";
		Object[] obj = new Object[]{};
		int rs = queryCounts(sql, obj); 
		return rs;
	}
	
	@Override
	public int countApplyProcessOrder() {
		String sql = "SELECT COUNT(*) FROM mainorder WHERE (state in (0,253) OR reversal_state = -2) AND batch_order_state = 2";
		Object[] obj = new Object[]{};
		int rs = queryCounts(sql, obj); 
		return rs;
	}
	
	/**
	 * 查找处理中的订单
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<OrderVO> findProcessingOrder() {
		String sql = "SELECT mainOrderId,user_id AS userId,user_name AS userName,goods_type_id AS goodsTypeId,goods_type_name AS goodsTypeName, "
				+ " \'desc\',goods_id AS goodsId,goods_name AS goodsName,goods_no AS goodsNo, "
				+ " goods_value AS goodsValue,card_lib_ids AS cardLibIds,totalMoney,retail_price AS retailPrice, "
				+ " stock_price AS stockPrice,commission,createTime,state,supply_id AS supplyId,supplyInterface_id AS supplyInterfaceId, "
				+ " supply_name AS supplyName,sign,mobile,terminal_no AS terminalNo,is_terminal AS isTerminal, "
				+ " order_type AS orderType,can_reverse AS canReverse,reversal_state AS reversalState"
				+ " FROM mainorder WHERE ((state in (3,254) OR reversal_state = 0) AND batch_order_state = 2 ) AND refresh_time <= ? "
				+ " ORDER BY refresh_time ASC "
				+ " LIMIT 10";
		Object[] obj = new Object[]{System.currentTimeMillis()};
		int[] types = new int[]{Types.BIGINT};
		List<OrderVO> list = (List<OrderVO>) queryList(sql,obj,types, OrderVO.class);
		return list;
	}
	
	/**
	 * 查找申请处理的订单
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<OrderVO> findApplyProcessOrder() {
		String sql = "SELECT mainOrderId,user_id AS userId,user_name AS userName,goods_type_id AS goodsTypeId,goods_type_name AS goodsTypeName, "
				+ " \'desc\',goods_id AS goodsId,goods_name AS goodsName,goods_no AS goodsNo, "
				+ " goods_value AS goodsValue,card_lib_ids AS cardLibIds,totalMoney,retail_price AS retailPrice, "
				+ " stock_price AS stockPrice,commission,createTime,state,supply_id AS supplyId,supplyInterface_id AS supplyInterfaceId, "
				+ " supply_name AS supplyName,sign,mobile,terminal_no AS terminalNo,is_terminal AS isTerminal, "
				+ " order_type AS orderType,can_reverse AS canReverse,reversal_state AS reversalState"
				+ " FROM mainorder WHERE (state in (0,253) OR reversal_state = -2) AND batch_order_state =2 "
				+ " LIMIT 6";
		Object[] obj = new Object[]{};
		List<OrderVO> list = (List<OrderVO>) queryList(sql, OrderVO.class);
		return list;
	}
	
	/**
	 * 查找对应mainorder
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@Override
	public OrderVO findById(String mainOrderId) {
		String sql = "SELECT mainOrderId,user_id AS userId,user_name AS userName,goods_type_id AS goodsTypeId,goods_type_name AS goodsTypeName, "
				+ " \'desc\',goods_id AS goodsId,goods_name AS goodsName,goods_no AS goodsNo, "
				+ " goods_value AS goodsValue,card_lib_ids AS cardLibIds,totalMoney,retail_price AS retailPrice, "
				+ " stock_price AS stockPrice,commission,createTime,state,supply_id AS supplyId,supplyInterface_id AS supplyInterfaceId, "
				+ " supply_name AS supplyName,sign,mobile,terminal_no AS terminalNo,is_terminal AS isTerminal, "
				+ " order_type AS orderType,can_reverse AS canReverse,reversal_state AS reversalState,resp_time AS respTime "
				+ " FROM mainorder WHERE mainOrderId = ?";
		Object[] obj = new Object[]{mainOrderId};
		OrderVO orderVO = (OrderVO) queryObject(sql, obj, OrderVO.class);
		return orderVO;
	}
	
	/**
	 * 更新订单状态
	 * @throws Exception
	 */
	@Override
	public void updateOrderState(int state,String mainOrderId) {
		String sql = "UPDATE mainorder SET state = ? WHERE mainOrderId = ?";
		Object[] obj = new Object[]{state,mainOrderId};
		update(sql, obj);
	}
	
	/**
	 * 更新订单处理状态
	 * @throws Exception
	 */
	@Override
	public void updateBatchOrderState(int state,String mainOrderId) {
		String sql = "UPDATE mainorder SET batch_order_state = ? WHERE mainOrderId = ?";
		Object[] obj = new Object[]{state,mainOrderId};
		update(sql, obj);
	}
	
	/**
	 * 更新订单冲正状态
	 * @throws Exception
	 */
	@Override
	public void updateReversalState(int state,String mainOrderId) {
		String sql = "UPDATE mainorder SET reversal_state = ? WHERE mainOrderId = ?";
		Object[] obj = new Object[]{state,mainOrderId};
		update(sql, obj);
	}
	
	@Override
	public void updateRefreshTime(long refreshTime,String mainOrderId) {
		String sql = "UPDATE mainorder SET refresh_time = ? WHERE mainOrderId = ?";
		Object[] obj = new Object[]{refreshTime,mainOrderId};
		update(sql, obj);
	}

	@Override
	public void updateQueryTimes(int times,String mainOrderId) {
		String sql = "UPDATE mainorder SET resp_time = ? WHERE mainOrderId = ?";
		Object[] obj = new Object[]{times,mainOrderId};
		update(sql, obj);
	}
}

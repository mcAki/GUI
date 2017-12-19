package com.sys.volunteer.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.vo.StatisticsVO;

@Service
@Transactional
public class StatisticsServiceBean extends CommonDao implements StatisticsService {

	@Override
	public List<StatisticsVO> getStatisticsVOs( String startDate, String endDate,String supplyName,String goodsName,String userName) throws Exception {
		Date maxDate = DateUtil.getMaxDate(endDate);
		Date minDate = DateUtil.getMinDate(startDate);
		StringBuffer sb=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		list.add(minDate);
		list.add(maxDate);
		sb.append("SELECT	goods_name AS goodsName,supply_name AS supplyName,stock_price AS stockPrice,retail_price AS retailPrice, "
				+"SUM(goods_no) AS goodsNo,SUM(stock_price) AS stockTotal,SUM(retail_price) AS retailTotal, "
				+"retail_price-stock_price AS profits,SUM(retail_price)-SUM(stock_price) AS profitsTotal "
				+"FROM mainorder ");
		sb.append("WHERE createTime BETWEEN ? AND ? ");
		if (!SysUtil.isNull(supplyName)) {
			sb.append("AND supply_name like ? ");
			list.add(supplyName);
		}
		if (!SysUtil.isNull(goodsName)) {
			sb.append("AND goods_name like ? ");
			list.add(goodsName);
		}
		if (!SysUtil.isNull(userName)) {
			sb.append("AND user_name like ? ");
			list.add(userName);
		}
		sb.append("GROUP BY goods_id");
		PageView pageView = new PageView(Integer.MAX_VALUE, 1);
		Object[] objects = list.toArray();
		QueryResult queryResult = this.getScrollDataBySQLQuery(sb.toString(), objects,
			pageView.getFirstResult(), pageView.getMaxresult(), null, null, StatisticsVO.class);
		return queryResult.getResultlist();
	}
	
	/**
	 * 获取赛会总人数
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<StatisticsVO> getCompetitionCount() throws Exception{
		String sqlString="select count(userid) as countByUserid from mission_personal as missionPersonal "
								+ " where mission_subject like '[赛]%'";
		PageView pageView = new PageView(Integer.MAX_VALUE, 1);
		Object[] objects = new Object[] { };
		QueryResult queryResult = this.getScrollDataBySQLQuery(sqlString, objects,
			pageView.getFirstResult(), pageView.getMaxresult(), null, null, StatisticsVO.class);
		return queryResult.getResultlist();
	}
	
}

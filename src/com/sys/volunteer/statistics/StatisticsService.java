package com.sys.volunteer.statistics;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.vo.StatisticsVO;

public interface StatisticsService extends IDao {

	public List<StatisticsVO> getStatisticsVOs(String startDate,String endDate,String supplyName,String goodsName,String userName) throws Exception;
	
	public List<StatisticsVO> getCompetitionCount() throws Exception;
}

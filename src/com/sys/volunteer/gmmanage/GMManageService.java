package com.sys.volunteer.gmmanage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.vo.CalFirstChargeAvgVo;
import com.sys.volunteer.vo.CalLoginEverydayVo;
import com.sys.volunteer.vo.CalOnlinePlayerCountVo;
import com.sys.volunteer.vo.CalPlayerChurnRateVo;
import com.sys.volunteer.vo.GMMail;
import com.sys.volunteer.vo.ServerInfoUserInfoVo;
import com.sys.volunteer.vo.UserInfo;

public interface GMManageService extends IDao {

	public QueryResult findUserInfo(int serverId,int firstindex,int maxresult) throws Exception;

	public UserInfo findUserInfoById(int serverId,String id) throws Exception;

	public QueryResult findItemLogVo(int serverId, String userId,
			Integer itemTemplateId, Long startTime, Long EndTime,int firstindex,int maxresult)
			throws Exception;

	public UserInfo findUserInfoByRolename(int serverId, String rolename)
			throws Exception;

	public QueryResult findUserInfosByIds(int serverId, String[] playerIds,String rolename,
			int firstindex, int maxresult) throws Exception;

	public QueryResult calLoginByEveryday(int serverId,Date startDate,Date endDate,
			int firstindex,int maxresult) throws Exception;

	public List<CalLoginEverydayVo> findLivenessPlayer(int serverId, Date startDate,
			Date endDate) throws Exception;

	public List<CalLoginEverydayVo> calLoginByEveryday(int serverId, Date startDate,
			Date endDate) throws Exception;

	public List<String> findUserInfoByRolenames(int serverId, String rolenames)
			throws Exception;

	public List<String> findUserInfoByLevelAndLastloginTime(int serverId,
			String level, Integer lastDays) throws Exception;

	public List<String> findUserInfoAll(int serverId) throws Exception;

	public QueryResult findUserCharge(int serverId, Date startDate, Date endDate,
			int firstindex, int maxresult) throws Exception;

	public QueryResult calUserChargeForPlayer(int serverId, Date startDate,
			Date endDate, int type, int firstindex, int maxresult)
			throws Exception;

	public QueryResult findUserConsume(int serverId, Date startDate, Date endDate,
			int firstindex, int maxresult) throws Exception;

	public QueryResult calOnlinePlayerCount(int serverId, Date startDate,
			Date endDate, int type, int firstindex, int maxresult)
			throws Exception;

	public List<CalPlayerChurnRateVo> calPlayerChurnRate(int serverId, Date startDate,
			Date endDate) throws Exception;

	public List<CalPlayerChurnRateVo> calPlayerChurnCountByLevel(int serverId, Date startDate, 
			Date endDate) throws Exception;

	public QueryResult countPlayerByLevel(int serverId, int firstindex,
			int maxresult) throws Exception;

	public ServerInfoUserInfoVo findServerInfoUserInfoVo(int serverId)
			throws Exception;

	public QueryResult findLogintimeInfo(int serverId, Date startDate, Date endDate,
			int firstindex, int maxresult) throws Exception;

	public CalFirstChargeAvgVo calFirstChargeAvg(int serverId) throws Exception;

	public QueryResult findUserLevel(int serverId, Date startDate, Date endDate,
			int firstindex, int maxresult) throws Exception;

	public void insertChurnRate(int serverId, CalPlayerChurnRateVo vo)
			throws SQLException;

	public void insertChurnByLevel(int serverId, CalPlayerChurnRateVo vo)
			throws SQLException;

	public void insertLivenessPlayer(int serverId, CalLoginEverydayVo vo)
			throws SQLException;

	public void checkOut(int serverId);

	public QueryResult findLivenessPlayerLocal(int serverId, Date startDate,
			Date endDate, int firstindex, int maxresult) throws Exception;

	public QueryResult findChurnRateLocal(int serverId, Date startDate, Date endDate,
			int firstindex, int maxresult) throws Exception;

	public QueryResult findChurnByLevelLocal(int serverId, Date startDate,
			Date endDate, int firstindex, int maxresult) throws Exception;

	public void insertOpenidLog(String openid, String name,int serverid) throws SQLException;

	public QueryResult findGMMails(int serverId, Date startDate, Date endDate,
			int firstindex, int maxresult) throws Exception;

	public GMMail findGMMail(int serverId, String id) throws Exception;

	void insertStay(int serverId, CalPlayerChurnRateVo vo) throws SQLException;

	QueryResult findStayLocal(int serverId, Date startDate, Date endDate,
			int firstindex, int maxresult) throws Exception;

	CalPlayerChurnRateVo calStayYesterday(int serverId, Date endDate)
			throws Exception;

	CalPlayerChurnRateVo calStay7Day(int serverId, Date endDate)
			throws Exception;

	void insertStay7days(int serverId, CalPlayerChurnRateVo vo)
			throws SQLException;

	QueryResult findStay7daysLocal(int serverId, Date startDate, Date endDate,
			int firstindex, int maxresult) throws Exception;

	QueryResult findRegisterEveryday(int serverId, Date startDate,
			Date endDate, int firstindex, int maxresult) throws Exception;

	void insertAnnouncemnet(int serverId, String announcement) throws Exception;

}

package com.sys.volunteer.gmmanage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ages.util.DbManager;
import com.ages.util.SysUtil;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.vo.CalFirstChargeAvgVo;
import com.sys.volunteer.vo.CalLoginEverydayVo;
import com.sys.volunteer.vo.CalOnlinePlayerCountVo;
import com.sys.volunteer.vo.CalPlayerChurnRateVo;
import com.sys.volunteer.vo.GMMail;
import com.sys.volunteer.vo.GmUserChargeVo;
import com.sys.volunteer.vo.GmUserConsumeVo;
import com.sys.volunteer.vo.GmUserLevelVo;
import com.sys.volunteer.vo.ItemLogVo;
import com.sys.volunteer.vo.LogintimeInfoVo;
import com.sys.volunteer.vo.ServerInfoUserInfoVo;
import com.sys.volunteer.vo.UserInfo;

@Service
@Transactional
public class GMManageServiceBean extends CommonDao implements GMManageService {

	@Override
	public QueryResult findUserInfo(int serverId, int firstindex, int maxresult)
			throws Exception {
		String sql = "select * from user_info";
		Object[] params = new Object[] {};
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, params,
				UserInfo.class);
		return queryResult;
	}

	@Override
	public UserInfo findUserInfoById(int serverId, String id) throws Exception {
		String sql = "select * from user_info where id = ?";
		Object[] params = new Object[] { id };
		UserInfo userInfo = findObjectBySql(DbManager.getBackUpCon(serverId),
				UserInfo.class, sql, params);
		return userInfo;
	}

	@Override
	public UserInfo findUserInfoByRolename(int serverId, String rolename)
			throws Exception {
		String sql = "select * from user_info where rolename = ?";
		Object[] params = new Object[] { rolename };
		UserInfo userInfo = findObjectBySql(DbManager.getBackUpCon(serverId),
				UserInfo.class, sql, params);
		return userInfo;
	}

	@Override
	public List<String> findUserInfoByRolenames(int serverId, String rolenames)
			throws Exception {
		String sql = "select id from user_info where rolename in (" + rolenames
				+ ")";
		List<String> list = findSingleRowListBySql(DbManager
				.getBackUpCon(serverId), String.class, sql);
		return list;
	}

	@Override
	public List<String> findUserInfoAll(int serverId) throws Exception {
		String sql = "select id from user_info ";
		List<String> list = findSingleRowListBySql(DbManager
				.getBackUpCon(serverId), String.class, sql);
		return list;
	}

	@Override
	public List<String> findUserInfoByLevelAndLastloginTime(int serverId,
			String level, Integer lastDays) throws Exception {
		String startDate = "";
		String endDate = "";
		String sql = "SELECT id FROM user_info WHERE ";
		if (!level.equals("")) {
			sql += level;
			if (lastDays != null) {
				endDate = DateUtil.DateToString(new Date(),
						DateUtil.CM_SHORT_DATE_FORMAT);
				startDate = DateUtil.DateToString(new Date(new Date().getTime()
						- lastDays * 60 * 60 * 24 * 1000),
						DateUtil.CM_SHORT_DATE_FORMAT);
				sql += " AND lastloginTime BETWEEN \'" + startDate
						+ "\' AND \'" + endDate + "\' ";
			}
		} else {
			if (lastDays != null) {
				sql += " lastloginTime BETWEEN \'" + startDate + "\' AND \'"
						+ endDate + "\' ";
			}
		}
		sql += " GROUP BY id ";
		List<String> list = findSingleRowListBySql(DbManager
				.getBackUpCon(serverId), String.class, sql);
		return list;
	}

	@Override
	public QueryResult findItemLogVo(int serverId, String rolename,
			Integer itemTemplateId, Long startTime, Long endTime,
			int firstindex, int maxresult) throws Exception {
		String sql = "SELECT il.id AS id,il.itemid AS itemid,il.itemTemplateId AS itemTemplateId,il.userid AS userid,"
				+ " il.time AS `time`,il.handler AS `handler`,il.remarks AS remarks,ui.rolename AS rolename,it.name AS itemname "
				+ " from item_log il,item_template it,user_info ui WHERE il.itemTemplateId = it.id "
				+ "	AND il.userid = ui.id ";
		if (rolename != null && !rolename.equals("")) {
			sql += " and ui.rolename='" + rolename + "'";
		}
		if (itemTemplateId != null) {
			sql += " and it.id=" + itemTemplateId;
		}
		if (startTime != null && endTime != null) {
			sql += " and il.time between " + startTime + " and " + endTime;
		}
		//Object[] params = new Object[] {};
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, 
				ItemLogVo.class);
		return queryResult;
	}

	@Override
	public QueryResult findUserInfosByIds(int serverId, String[] playerIds,String rolename,
			int firstindex, int maxresult) throws Exception {
		String playerIdString = "";
		for (int i = 0; i < playerIds.length; i++) {
			if (i == playerIds.length - 1) {
				playerIdString += "'" + playerIds[i] + "'";
			} else {
				playerIdString += "'" + playerIds[i] + "',";
			}
		}
		String sql = "select * from user_info where id in (" + playerIdString
				+ ")";
		if (rolename!=null&&!rolename.equals("")) {
			sql += " and rolename= '"+rolename+"'";
		}
		Object[] obj = new Object[] {};
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj,
				UserInfo.class);
		return queryResult;
	}

	@Override
	public QueryResult calLoginByEveryday(int serverId, Date startDate,
			Date endDate, int firstindex, int maxresult) throws Exception {
		startDate = DateUtil.getMinDate(startDate);
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		endDate = DateUtil.getMaxDate(endDate);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from "
				+ " (SELECT COUNT(t.userId) AS number,DATE_FORMAT(t.loginTime,'%Y-%m-%d') AS countDate from ("
				+ " SELECT userId,loginTime from logintime_info"
				+ " WHERE loginTime BETWEEN ? AND ?"
				+ " GROUP BY DATE(loginTime),userId) t"
				+ " GROUP BY DATE(t.loginTime)) s";
		Object[] obj = new Object[] { sDate, eDate };
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj,
				CalLoginEverydayVo.class);
		return queryResult;
	}

	@Override
	public List<CalLoginEverydayVo> calLoginByEveryday(int serverId,
			Date startDate, Date endDate) throws Exception {
		startDate = DateUtil.getMinDate(startDate);
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		endDate = DateUtil.getMaxDate(endDate);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from "
				+ " (SELECT COUNT(t.userId) AS number,DATE_FORMAT(t.loginTime,'%Y-%m-%d') AS countDate from ("
				+ " SELECT userId,loginTime from logintime_info"
				+ " WHERE loginTime BETWEEN ? AND ?"
				+ " GROUP BY DATE(loginTime),userId) t"
				+ " GROUP BY DATE(t.loginTime)) s";
		Object[] obj = new Object[] { sDate, eDate };
		List<CalLoginEverydayVo> list = findListBySql(DbManager
				.getBackUpCon(serverId), CalLoginEverydayVo.class, sql, obj);
		return list;
	}

	@Override
	public List<CalLoginEverydayVo> findLivenessPlayer(int serverId, Date startDate,
			Date endDate) throws Exception {
		// startDate = DateUtil.getMinDate(startDate);
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		// endDate = DateUtil.getMaxDate(endDate);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "SELECT COUNT(userId) AS number,DATE(loginTime) AS countDate from"
				+ " (SELECT s3.userId AS userId,s3.loginTime AS loginTime from "
				+ " (SELECT userId,loginTime FROM logintime_info "
				+ " GROUP BY DATE(loginTime),userid) s1,"
				+ " (SELECT userId,loginTime FROM logintime_info "
				+ " GROUP BY DATE(loginTime),userid) s2,"
				+ " (SELECT userId,loginTime FROM logintime_info "
				+ " GROUP BY DATE(loginTime),userid) s3 "
				+ " WHERE s1.userId=s2.userId AND s2.userId = s3.userId AND s1.userId = s3.userId "
				+ " AND DATEDIFF(s2.loginTime,s1.loginTime)=1 AND DATEDIFF(s3.loginTime,s2.loginTime)=1 "
				+ " AND DATEDIFF(s3.loginTime,s1.loginTime)=2 "
				+ " AND s3.loginTime BETWEEN ? AND ?) t "
				+ " GROUP BY DATE(loginTime) ";
		Object[] obj = new Object[] { sDate, eDate };
		List<CalLoginEverydayVo> list = this.findListBySql(DbManager
				.getBackUpCon(serverId), CalLoginEverydayVo.class, sql, obj);
		return list;
	}

	@Override
	public QueryResult findUserCharge(int serverId, Date startDate,
			Date endDate, int firstindex, int maxresult) throws Exception {
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "SELECT id,player_id AS playerId,amount,createTime,`type`,original_price "
				+ " AS originalPrice ,discount_price AS discountPrice "
				+ " from gm_user_charge WHERE createTime BETWEEN ? AND ?";
		Object[] obj = new Object[] { sDate, eDate };
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj,
				GmUserChargeVo.class);
		return queryResult;
	}

	@Override
	public QueryResult calUserChargeForPlayer(int serverId, Date startDate,
			Date endDate, int type, int firstindex, int maxresult)
			throws Exception {
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "SELECT player_id AS playerId,SUM(amount) AS sumAmount "
				+ " from gm_user_charge "
				+ " WHERE createTime BETWEEN ? AND ? " + " AND type = ? "
				+ " GROUP BY player_id " + " ORDER BY SUM(amount) DESC ";
		Object[] obj = new Object[] { sDate, eDate, type };
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj,
				GmUserChargeVo.class);
		return queryResult;
	}

	@Override
	public QueryResult findUserConsume(int serverId, Date startDate,
			Date endDate, int firstindex, int maxresult) throws Exception {
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "SELECT id,player_id AS playerId,number,createTime,`type`,price, "
				+ " item_template_id AS itemTemplateId "
				+ " from gm_user_consume WHERE createTime BETWEEN ? AND ?";
		Object[] obj = new Object[] { sDate, eDate };
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj,
				GmUserConsumeVo.class);
		return queryResult;
	}

	@Override
	public QueryResult calOnlinePlayerCount(int serverId, Date startDate,
			Date endDate, int type, int firstindex, int maxresult)
			throws Exception {
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "SELECT createTime,MAX(playerCount) AS maxCount from gm_online_player_count "
				+ " WHERE createTime BETWEEN ? AND ? GROUP BY ";
		switch (type) {
		case 1:
			sql += "MINUTE(createTime)";
			break;
		case 2:
			sql += "HOUR(createTime)";
			break;
		case 3:
			sql += "DAY(createTime)";
			break;
		default:
			sql += "DAY(createTime)";
			break;
		}
		sql += " ORDER BY createTime DESC ";
		Object[] obj = new Object[] { sDate, eDate };
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj,
				CalOnlinePlayerCountVo.class);
		return queryResult;
	}
	

	@Override
	public CalPlayerChurnRateVo calStayYesterday(int serverId, 
			Date endDate)
			throws Exception {
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "SELECT count(DISTINCT(u.id)) FROM user_info u,logintime_info l WHERE DATEDIFF(DATE(?),u.registerTime)=2 AND DATEDIFF(DATE(?),l.loginTime)=1 AND u.id = l.userId";
		Object[] obj = new Object[] { eDate, eDate };
		Integer count = findCountBySql(DbManager
				.getBackUpCon(serverId), sql, obj);
		String sql2 = "SELECT count(*) FROM user_info WHERE DATEDIFF(DATE(?),registerTime)=2";
		Object[] obj2 = {eDate};
		Integer countRegister = findCountBySql(DbManager
				.getBackUpCon(serverId), sql2, obj2);
		Double rate = (double)count/(double)countRegister;
		if (rate == Double.NaN) {
			rate = 0d;
		}
		CalPlayerChurnRateVo vo = new CalPlayerChurnRateVo();
		vo.setCount(count);
		vo.setCountAll(countRegister);
		vo.setCountDate(eDate);
		vo.setRate(rate);
		return vo;
	}
	
	@Override
	public CalPlayerChurnRateVo calStay7Day(int serverId, 
			Date endDate)
			throws Exception {
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "SELECT count(DISTINCT(u.id)) FROM user_info u,logintime_info l WHERE DATEDIFF(DATE(?),u.registerTime)=7 AND DATEDIFF(DATE(?),l.loginTime)=6 AND u.id = l.userId";
		Object[] obj = new Object[] { eDate, eDate };
		Integer count = findCountBySql(DbManager
				.getBackUpCon(serverId), sql, obj);
		String sql2 = "SELECT count(*) FROM user_info WHERE DATEDIFF(DATE(?),registerTime)=7";
		Object[] obj2 = {eDate};
		Integer countRegister = findCountBySql(DbManager
				.getBackUpCon(serverId), sql2, obj2);
		Double rate = (double)count/(double)countRegister;
		if (rate == Double.NaN) {
			rate = 0d;
		}
		CalPlayerChurnRateVo vo = new CalPlayerChurnRateVo();
		vo.setCount(count);
		vo.setCountAll(countRegister);
		vo.setCountDate(eDate);
		vo.setRate(rate);
		return vo;
	}
	
	@Override
	public List<CalPlayerChurnRateVo> calPlayerChurnRate(int serverId, Date startDate,
			Date endDate) throws Exception {
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from (" 
				+ " SELECT id AS countDate,IF(uid IS NULL,countAll,COUNT(uid)) AS `count`, countAll,IF(uid IS NULL,countAll,COUNT(uid))/countAll AS rate FROM (SELECT *"
				+ " FROM (SELECT gm_date_temp.id AS id,user_info.id AS uid FROM gm_date_temp, user_info "
				+ " WHERE gm_date_temp.id BETWEEN ? AND ? "
				+ " AND user_info.registerTime<=gm_date_temp.id) u "
				+ " LEFT JOIN (SELECT userId,loginTime FROM logintime_info " 
				+ " WHERE loginTime BETWEEN ? AND ? " 
				+ " GROUP BY DATE(loginTime),userid) l "
				+ " ON u.id = DATE(l.loginTime) AND u.uid = l.userId " 
				+ " WHERE NOT EXISTS(SELECT * FROM (SELECT userId,loginTime FROM logintime_info " 
				+ " GROUP BY DATE(loginTime),userid) s2 "
				+ " WHERE u.uid=s2.userId AND DATEDIFF(u.id,s2.loginTime)=1) "
				+ " AND NOT EXISTS(SELECT * FROM (SELECT userId,loginTime FROM logintime_info " 
				+ " GROUP BY DATE(loginTime),userid) s3 "
				+ " WHERE u.uid=s3.userId AND DATEDIFF(u.id,s3.loginTime)=2) "
				+ " ) m LEFT JOIN "
				+ " (SELECT COUNT(user_info.id) AS countAll,gm_date_temp.id AS tempDate FROM user_info,gm_date_temp " 
				+ " WHERE user_info.registerTime<=gm_date_temp.id AND gm_date_temp.id "
				+ " BETWEEN ? AND ? GROUP BY gm_date_temp.id) p "
				+ " ON m.id=p.tempDate "
				+ " GROUP BY m.id "
				+ " ) t";
		Object[] obj = new Object[] { sDate, eDate, sDate, eDate, sDate, eDate };
		List<CalPlayerChurnRateVo> list = this.findListBySql(DbManager
				.getBackUpCon(serverId), CalPlayerChurnRateVo.class, sql, obj);
		return list;
	}
	
	@Override
	public List<CalPlayerChurnRateVo> calPlayerChurnCountByLevel(int serverId, Date startDate, 
			Date endDate) throws Exception {
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from (SELECT id AS countDate,`level`,COUNT(uid) AS `count` FROM (SELECT * "
				 + " FROM (SELECT gm_date_temp.id AS id,user_info.id AS uid,`level` FROM gm_date_temp, user_info "
				 + " WHERE gm_date_temp.id BETWEEN ? AND ? "
				 + " AND user_info.registerTime<=gm_date_temp.id) u "
				 + " LEFT JOIN (SELECT userId,loginTime FROM logintime_info " 
				 + " WHERE loginTime BETWEEN ? AND ? " 
				 + " GROUP BY DATE(loginTime),userid) l "
				 + " ON u.id = DATE(l.loginTime) AND u.uid = l.userId " 
				 + " WHERE NOT EXISTS(SELECT * FROM (SELECT userId,loginTime FROM logintime_info " 
				 + " GROUP BY DATE(loginTime),userid) s2 "
				 + " 	 WHERE u.uid=s2.userId AND DATEDIFF(u.id,s2.loginTime)=1) " 
				 + " AND NOT EXISTS(SELECT * FROM (SELECT userId,loginTime FROM logintime_info " 
				 + " GROUP BY DATE(loginTime),userid) s3 "
				 + " WHERE u.uid=s3.userId AND DATEDIFF(u.id,s3.loginTime)=2)) t "
				 + " GROUP BY id,level) t";
		Object[] obj = new Object[] { sDate, eDate, sDate, eDate };
		List<CalPlayerChurnRateVo> list = this.findListBySql(DbManager
				.getBackUpCon(serverId), CalPlayerChurnRateVo.class, sql, obj);
		return list;
	}
	
	@Override
	public QueryResult countPlayerByLevel(int serverId,
			int firstindex, int maxresult) throws Exception {
		String sql = "select * from (SELECT COUNT(id) AS count,`level` from user_info GROUP BY `level`) t";
		Object[] obj = new Object[] { };
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj,
				CalPlayerChurnRateVo.class);
		return queryResult;
	}
	
	@Override
	public ServerInfoUserInfoVo findServerInfoUserInfoVo(int serverId) throws Exception {
		String sql = "select * from (select count(id) as registerCount,max(level) as maxLevel from user_info) u";
		ServerInfoUserInfoVo vo = findObjectBySql(DbManager
				.getBackUpCon(serverId), ServerInfoUserInfoVo.class, sql);
		return vo;
	}
	
	@Override
	public QueryResult findLogintimeInfo(int serverId, Date startDate, 
			Date endDate, int firstindex, int maxresult) throws Exception {
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from logintime_info where loginTime between ? and ?";
		Object[] obj = new Object[] { sDate, eDate };
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj,
				LogintimeInfoVo.class);
		return queryResult;
	}
	
	@Override
	public CalFirstChargeAvgVo calFirstChargeAvg(int serverId) throws Exception {
		String sql = "SELECT SUM(amount) as sumAll,COUNT(player_id) as count,"
				+ " SUM(amount)/COUNT(player_id) as avg FROM "
				+ "(SELECT * FROM gm_user_charge where type = 1 GROUP BY player_id ORDER BY createTime ) a";
		CalFirstChargeAvgVo vo = findObjectBySql(DbManager
				.getBackUpCon(serverId), CalFirstChargeAvgVo.class, sql);
		return vo;
	}
	
	@Override
	public QueryResult findUserLevel(int serverId, Date startDate, 
			Date endDate, int firstindex, int maxresult) throws Exception {
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select id,player_id AS playerId,username," +
				"createTime,current_level AS currentLevel" +
				" from gm_user_level where createTime between ? and ?";
		Object[] obj = new Object[] { sDate, eDate };
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj,
				GmUserLevelVo.class);
		return queryResult;
	}
	
	@Override
	public void insertChurnRate(int serverId,CalPlayerChurnRateVo vo) throws SQLException{
		String sql = "insert into churnrate (id,countDate,count,countAll,rate) values (?,?,?,?,?)";
		Date countDate = DateUtil.formatDate(vo.getCountDate());
		Object[] obj = {SysUtil.getUUID(),countDate,vo.getCount(),vo.getCountAll(),vo.getRate()};
		this.executeSQL(DbManager.getGmRunner(), sql, obj);
	}
	
	@Override
	public void insertStay(int serverId,CalPlayerChurnRateVo vo) throws SQLException{
		String sql = "insert into stay (id,countDate,count,countAll,rate) values (?,?,?,?,?)";
		Date countDate = DateUtil.formatDate(vo.getCountDate());
		countDate = DateUtil.getSpecifiedDayBefore(countDate);
		Object[] obj = {SysUtil.getUUID(),countDate,vo.getCount(),vo.getCountAll(),vo.getRate()};
		this.executeSQL(DbManager.getGmRunner(), sql, obj);
	}
	
	@Override
	public void insertStay7days(int serverId,CalPlayerChurnRateVo vo) throws SQLException{
		String sql = "insert into stay7days (id,countDate,count,countAll,rate) values (?,?,?,?,?)";
		Date countDate = DateUtil.formatDate(vo.getCountDate());
		countDate = DateUtil.getSpecifiedDayBefore(countDate,7);
		Object[] obj = {SysUtil.getUUID(),countDate,vo.getCount(),vo.getCountAll(),vo.getRate()};
		this.executeSQL(DbManager.getGmRunner(), sql, obj);
	}
	
	@Override
	public void insertChurnByLevel(int serverId,CalPlayerChurnRateVo vo) throws SQLException{
		String sql = "insert into churnbylevel (id,countDate,level,count) values (?,?,?,?)";
		Date countDate = DateUtil.formatDate(vo.getCountDate());
		Object[] obj = {SysUtil.getUUID(),countDate,vo.getLevel(),vo.getCount()};
		this.executeSQL(DbManager.getGmRunner(), sql, obj);
	}
	
	@Override
	public void insertLivenessPlayer(int serverId,CalLoginEverydayVo vo) throws SQLException{
		String sql = "insert into livenessplayer (id,countDate,number) values (?,?,?)";
		Date countDate = DateUtil.formatDate(vo.getCountDate());
		Object[] obj = {SysUtil.getUUID(),countDate,vo.getNumber()};
		this.executeSQL(DbManager.getGmRunner(), sql, obj);
	}
	
	@Override
	public void checkOut(int serverId){
		Date calDate = DateUtil.getSpecifiedDayBefore(new Date());
		Date sDate = DateUtil.getMinDate(calDate);
		Date eDate = DateUtil.getMaxDate(calDate);
		try {
			List<CalLoginEverydayVo> logins = findLivenessPlayer(serverId, sDate, eDate);
			for (CalLoginEverydayVo calLoginEverydayVo : logins) {
				insertLivenessPlayer(serverId, calLoginEverydayVo);
			}
//			List<CalPlayerChurnRateVo> churnRates = calPlayerChurnRate(serverId, calDate, calDate);
//			for (CalPlayerChurnRateVo calPlayerChurnRateVo : churnRates) {
//				insertChurnRate(serverId, calPlayerChurnRateVo);
//			}
			List<CalPlayerChurnRateVo> churnLevels = calPlayerChurnCountByLevel(serverId, sDate, eDate);
			for (CalPlayerChurnRateVo calPlayerChurnRateVo : churnLevels) {
				insertChurnByLevel(serverId, calPlayerChurnRateVo);
			}
			CalPlayerChurnRateVo vo = calStayYesterday(serverId, eDate);
			insertStay(serverId, vo);
			CalPlayerChurnRateVo vo2 = calStay7Day(serverId, eDate);
			insertStay7days(serverId, vo2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public QueryResult findLivenessPlayerLocal(int serverId, Date startDate, 
			Date endDate, int firstindex, int maxresult) throws Exception{
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from livenessplayer where countDate between ? and ? order by countDate";
		Object[] obj = {sDate,eDate};
		QueryResult queryResult = getScrollData(DbManager
				.getGmRunner(), firstindex, maxresult, sql, obj,
				CalLoginEverydayVo.class);
		if (queryResult.getTotalrecord()==0) {
			List<CalLoginEverydayVo> logins = findLivenessPlayer(serverId, startDate, endDate);
			for (CalLoginEverydayVo calLoginEverydayVo : logins) {
				insertLivenessPlayer(serverId, calLoginEverydayVo);
			}
			queryResult = getScrollData(DbManager
					.getGmRunner(), firstindex, maxresult, sql, obj,
					CalLoginEverydayVo.class);
		}
		return queryResult;
	}
	
	@Override
	public QueryResult findChurnRateLocal(int serverId, Date startDate, 
			Date endDate, int firstindex, int maxresult) throws Exception{
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from churnrate where countDate between ? and ? order by countDate";
		Object[] obj = {sDate,eDate};
		QueryResult queryResult = getScrollData(DbManager
				.getGmRunner(), firstindex, maxresult, sql, obj,
				CalPlayerChurnRateVo.class);
		if (queryResult.getTotalrecord()==0) {
			List<CalPlayerChurnRateVo> churnRates = calPlayerChurnRate(serverId, startDate, endDate);
			for (CalPlayerChurnRateVo calPlayerChurnRateVo : churnRates) {
				insertChurnRate(serverId, calPlayerChurnRateVo);
			}
			queryResult = getScrollData(DbManager
					.getGmRunner(), firstindex, maxresult, sql, obj,
					CalPlayerChurnRateVo.class);
		}
		return queryResult;
	}
	
	@Override
	public QueryResult findStayLocal(int serverId, Date startDate, 
			Date endDate, int firstindex, int maxresult) throws Exception{
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from stay where countDate between ? and ? group by countDate order by countDate";
		Object[] obj = {sDate,eDate};
		QueryResult queryResult = getScrollData(DbManager
				.getGmRunner(), firstindex, maxresult, sql, obj,
				CalPlayerChurnRateVo.class);
		return queryResult;
	}
	
	@Override
	public QueryResult findStay7daysLocal(int serverId, Date startDate, 
			Date endDate, int firstindex, int maxresult) throws Exception{
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from stay7days where countDate between ? and ? group by countDate order by countDate";
		Object[] obj = {sDate,eDate};
		QueryResult queryResult = getScrollData(DbManager
				.getGmRunner(), firstindex, maxresult, sql, obj,
				CalPlayerChurnRateVo.class);
		return queryResult;
	}
	
	@Override
	public QueryResult findChurnByLevelLocal(int serverId, Date startDate, 
			Date endDate, int firstindex, int maxresult) throws Exception{
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from churnbylevel where countDate between ? and ? order by level,countDate";
		Object[] obj = {sDate,eDate};
		QueryResult queryResult = getScrollData(DbManager
				.getGmRunner(), firstindex, maxresult, sql, obj,
				CalPlayerChurnRateVo.class);
		if (queryResult.getTotalrecord()==0) {
			List<CalPlayerChurnRateVo> churnLevels = calPlayerChurnCountByLevel(serverId, startDate, endDate);
			for (CalPlayerChurnRateVo calPlayerChurnRateVo : churnLevels) {
				insertChurnByLevel(serverId, calPlayerChurnRateVo);
			}
			queryResult = getScrollData(DbManager
					.getGmRunner(), firstindex, maxresult, sql, obj,
					CalPlayerChurnRateVo.class);
		}
		return queryResult;
	}
	
	@Override
	public void insertOpenidLog(String openid,String name,int serverid) throws SQLException {
		String sql = "insert into openid_login_log values(?,?,?,?,?)";
		String uuid = SysUtil.getUUID();
		Object[] obj = {uuid,openid,name,new Date(),serverid};
		this.executeSQL(DbManager
					.getGmRunner(), sql, obj);
	}
	
	@Override
	public QueryResult findGMMails(int serverId,Date startDate, 
			Date endDate,int firstindex, int maxresult) throws Exception{
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from mail where type = 100 and sendDatetime between ? and ? order by sendDatetime desc";
		Object[] obj = {sDate,eDate};
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj, GMMail.class);
		return queryResult;
	}
	
	@Override
	public GMMail findGMMail(int serverId,String id) throws Exception{
		String sql = "select * from mail where id = ?";
		Object[] obj = {id};
		GMMail gmMail = findObjectBySql(DbManager
				.getBackUpCon(serverId), GMMail.class, sql, obj);
		return gmMail;
	}
	
	@Override
	public QueryResult findRegisterEveryday(int serverId,Date startDate, 
			Date endDate,int firstindex, int maxresult) throws Exception {
		String sDate = DateUtil.DateToString(startDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String eDate = DateUtil.DateToString(endDate,
				DateUtil.CM_LONG_DATE_FORMAT);
		String sql = "select * from (select count(*) as countAll,registerTime from user_info where registerTime between ? and ? group by Date(registerTime)) t";
		Object[] obj = {sDate,eDate};
		QueryResult queryResult = getScrollData(DbManager
				.getBackUpCon(serverId), firstindex, maxresult, sql, obj, CalPlayerChurnRateVo.class);
		return queryResult;
	}
	
	@Override
	public void insertAnnouncemnet(int serverId,String announcement) throws Exception {
		String sql = "insert into announcement values (?,?)";
		Object[] obj = new Object[]{new Date(),announcement};
		this.executeSQL(DbManager
				.getBackUpCon(serverId), sql, obj);
	}
}

package com.ages.util;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

import com.ages.model.CommonCountMapper;
import com.ages.model.CommonRowMapper;
import com.ages.model.CommonSingleRowMapper;
import com.ages.model.ConnectInfo;
import com.ages.model.ServerInfoBean;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sys.volunteer.gmmanage.GMKernel;
import com.sys.volunteer.vo.BroadcastVo;
import com.sys.volunteer.vo.UserInfo;


public class DbManager {
	private XmlConfigReader xmlConfigReader;
	public DatabaseConfig databaseConfig;
	private static QueryRunner gmRunner;
	private static List<ServerInfoBean> servers=new ArrayList<ServerInfoBean>();
	private static DbManager instance=new DbManager();
	private DbManager(){
		init();
	}
	public static DbManager getInstance(){
		return instance;
	}

	private void init(){
		try {
			
			xmlConfigReader = new XmlConfigReader("serverConfig.xml");
			databaseConfig = (DatabaseConfig)xmlConfigReader.loadConfig(DatabaseConfig.class);
			gmRunner=constructRunner(databaseConfig.getJdbcURL(),databaseConfig.getUserName(),databaseConfig.getPassword(),
					databaseConfig.getMaxStatements(),databaseConfig.getMinPoolSize(),databaseConfig.getAcquireIncrement(),databaseConfig.getMaxPoolSize(),databaseConfig.getNumHelperThreads());
			initServerInfo();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 构建runner
	 */
	private  QueryRunner constructRunner(String jdbcUrl,String username,String password,int maxStatements,int minPoolSize,int acquireIncrement,int maxPoolSize,int numHelperThreads) {
		ComboPooledDataSource datasource = new ComboPooledDataSource("seraphDatabasePool");
		try {
			datasource.setDriverClass(databaseConfig.getDriverClass());
			datasource.setJdbcUrl(jdbcUrl);
			datasource.setUser(username);
			datasource.setPassword(password);
			datasource.setMaxStatements(maxStatements);
			datasource.setMinPoolSize(minPoolSize);
			datasource.setAcquireIncrement(acquireIncrement);
			datasource.setMaxPoolSize(maxPoolSize);
			datasource.setNumHelperThreads(numHelperThreads);
			QueryRunner tmpRunner = new QueryRunner(datasource);
			return tmpRunner;
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("static-access")
	private void initServerInfo() throws SQLException{
		List<ConnectInfo> connectInfos=DbManager.queryList(gmRunner,"select * from servers_info",
				null,ConnectInfo.class);
		for(ConnectInfo connectInfo:connectInfos){
			ServerInfoBean serverInfoBean=new ServerInfoBean();
			serverInfoBean.setConnectInfo(connectInfo);
			//jdbc:mysql://202.104.70.203:3306/td3guo_online_tencent?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8
			
			String jdbcURL="jdbc:mysql://"+connectInfo.getGame_serverip()+connectInfo.getGame_dbname()+"?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
			QueryRunner gameServerConnectRunner=constructRunner(jdbcURL,connectInfo.getGame_username(),connectInfo.getGame_password(),
					databaseConfig.getMaxStatements(),databaseConfig.getMinPoolSize(),databaseConfig.getAcquireIncrement(),databaseConfig.getMaxPoolSize(),databaseConfig.getNumHelperThreads());
			
			String backUpJdbcURL="jdbc:mysql://"+connectInfo.getServerip()+connectInfo.getDbname()+"?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
			QueryRunner backupConnectRunner=constructRunner(backUpJdbcURL,connectInfo.getUsername(),connectInfo.getPassword(),
					databaseConfig.getMaxStatements(),databaseConfig.getMinPoolSize(),databaseConfig.getAcquireIncrement(),databaseConfig.getMaxPoolSize(),databaseConfig.getNumHelperThreads());
			
			serverInfoBean.setBackupConnectRunner(backupConnectRunner);
			serverInfoBean.setGameServerConnectRunner(gameServerConnectRunner);
			servers.add(serverInfoBean);
			Map<Integer, Map<String, BroadcastVo>> map = GMKernel.getInstance().getServerBroadcast();
			map.put(connectInfo.getId(), new HashMap<String, BroadcastVo>());
			
		}
	}
	
	/**
	 * 获得gm数据库链接
	 * @return
	 */
	public static QueryRunner getGmRunner() {
		return gmRunner;
	}

	/**
	 * 获取服务器信息列表
	 * @return
	 */
	public List<ConnectInfo> getConnectInfos() {
		List<ConnectInfo> list = new ArrayList<ConnectInfo>();
		for (ServerInfoBean serverInfoBean : servers) {
			list.add(serverInfoBean.getConnectInfo());
		}
		return list;
	}
	
	/**
	 * 获得游戏数据库的链接
	 * @param serverId
	 * @return
	 */
	public static QueryRunner getGameCon(int serverId){
		return servers.get(serverId-1).getGameServerConnectRunner();
	}
	
	/**
	 * 获得备份库的链接
	 * @param serverId
	 * @return
	 */
	public static QueryRunner getBackUpCon(int serverId){
		return servers.get(serverId-1).getBackupConnectRunner();
	}
	
	/**
	 * 获得游戏http管理链接路径
	 * @param serverId
	 * @return
	 */
	public static String getRequestUrl(int serverId){
		return servers.get(serverId-1).getConnectInfo().getRequesturl();
	}
	
	public static <T> List<T> queryList(QueryRunner runner,String sql,Object[] params,Class<T> model) throws SQLException{
		 return (List<T>) runner.query(sql, new CommonRowMapper<T>(model), params);
	}
	
	public static <T> List<T> querySingleRowList(QueryRunner runner,String sql,Object[] params,Class<T> model) throws SQLException{
		 return (List<T>) runner.query(sql, new CommonSingleRowMapper<T>(model), params);
	}
	
	public static Integer getCount(QueryRunner runner,String sql,Object[] params) throws SQLException{
		return  (Integer) runner.query(sql, new CommonCountMapper<Integer>(), params);
	}
	
	public static <T> T queryObject(QueryRunner runner,String sql,Object[] params,Class<T> model) throws SQLException{
		 List<T> list =(List<T>)runner.query(sql, new CommonRowMapper<T>(model), params);
		 if (!list.isEmpty()) {
			 return (T)list.get(0);
		 }
		 return null;
	}
	
	public static void executeSQL(QueryRunner runner,String sql,Object[] params) throws SQLException{
		runner.update(sql, params);
	}
	
	public static void executeSQL(QueryRunner runner,String sql) throws SQLException{
		runner.update(sql);
	}
	
	public static void main(String args[]){
		try {
//			Administrators administrators=DbManager.queryObject(DbManager.getGmRunner(), "select * from administrators where userName= ? and passWord= ?", new Object[]{"admin1"	,"admin"} ,Administrators.class);
//			System.out.println(administrators.getRealName());
			String sql = "select * from user_info where name = ? and password = ?";
			Object[] obj = new Object[]{"tao1","123456"};
			UserInfo userInfo = DbManager.queryObject(DbManager.getBackUpCon(1),sql, obj, UserInfo.class);
			System.out.println(userInfo.getId()+" "+userInfo.getRoleName());
			//String url = DbManager.getRequestUrl(1);
			//System.out.println(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		DbManager.getInstance().queryList(DbManager.getInstance().getGmRunner(), sql, params, model);
//		
//		
//		DbManager.getInstance().queryList(DbManager.getInstance().getBackUpCon(1), sql, params, model);
//		DbManager.getInstance().queryList(DbManager.getInstance().getGameCon(1), sql, params, model);
	}
	
}

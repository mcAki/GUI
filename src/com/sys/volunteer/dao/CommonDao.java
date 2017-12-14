package com.sys.volunteer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ages.util.DbManager;
import com.sys.volunteer.exception.SystemException;
import com.sys.volunteer.pagemodel.QueryResult;

public abstract class CommonDao implements IDao {
	protected Log log = LogFactory.getLog(this.getClass());

	public <T> T findObjectBySql(QueryRunner runner,Class<?> entityClass, String sql) throws SQLException{
		return (T) DbManager.queryObject(runner, sql, null, entityClass);
	}
	
	public <T> T findObjectBySql(QueryRunner runner,Class<?> entityClass, String sql,Object[] queryParams)  throws SQLException{
		return (T)DbManager.queryObject(runner, sql, queryParams, entityClass);
	}
	
	public <T> List<T> findListBySql(QueryRunner runner,Class<?> entityClass, String sql,Object[] queryParams)  throws SQLException{
		return (List<T>) DbManager.queryList(runner, sql, queryParams, entityClass);
	}
	
	public <T> List<T> findListBySql(QueryRunner runner,Class<?> entityClass, String sql)  throws SQLException{
		return (List<T>) DbManager.queryList(runner, sql, null,entityClass);
	}
	
	public <T> List<T> findSingleRowListBySql(QueryRunner runner,Class<?> entityClass, String sql,Object[] queryParams)  throws SQLException{
		return (List<T>) DbManager.querySingleRowList(runner, sql, queryParams, entityClass);
	}
	
	public <T> List<T> findSingleRowListBySql(QueryRunner runner,Class<?> entityClass, String sql)  throws SQLException{
		return (List<T>) DbManager.querySingleRowList(runner, sql, null,entityClass);
	}
	
	public Integer findCountBySql(QueryRunner runner, String sql, Object[] queryParams)  throws SQLException{
		return DbManager.getCount(runner, sql, queryParams);
	}
	
	public  void executeSQL(QueryRunner runner,String sql) throws SQLException{
		DbManager.executeSQL(runner, sql);
	}
	
	public  void executeSQL(QueryRunner runner,String sql,Object[] params) throws SQLException{
		DbManager.executeSQL(runner, sql, params);
	}
	
	public <T> QueryResult getScrollData(QueryRunner runner,int firstindex, int maxresult, String sql,Class<T> model) throws Exception {
		return this.getScrollData(runner,firstindex, maxresult, sql, null,model);
	}

	/**
	 * 分页 firstindex maxresult都等于-1可以获得全部数据
	 * @param firstindex
	 * @param maxresult
	 * @param hql
	 * @param queryParams
	 * @return
	 * @throws Exception
	 */
	public <T> QueryResult getScrollData(QueryRunner runner,int firstindex, int maxresult, String sql, Object[] queryParams,Class<T> model)
			throws Exception {
		QueryResult qr = new QueryResult();

		String countHql = getCountQuery(sql);

		int total = DbManager.getCount(runner, countHql, queryParams);

		if (firstindex != -1 && maxresult != -1) {
			sql= sql+ " limit "+firstindex +", "+ maxresult;
		}else{
			
		}
		List list = DbManager.queryList(runner, sql, queryParams, model);
		qr.setResultlist(list);
		qr.setTotalrecord(total);
		return qr;
	}

	
	private String getCountQuery(String hql) throws Exception {
		int index = hql.indexOf("from");
		if (index != -1) {
			return "select count(*) " + hql.substring(index);
		}

		throw new SystemException("HQL error！");
	}
}

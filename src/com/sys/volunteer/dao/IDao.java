package com.sys.volunteer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.sys.volunteer.pagemodel.QueryResult;

public interface IDao {
	
	public abstract <T> T findObjectBySql(QueryRunner runner,Class<?> entityClass, String sql) throws SQLException;
	
	public abstract <T> T findObjectBySql(QueryRunner runner,Class<?> entityClass, String sql,Object[] queryParams)  throws SQLException;
	
	public abstract <T> List<T> findListBySql(QueryRunner runner,Class<?> entityClass, String sql,Object[] queryParams)  throws SQLException;
	
	public abstract <T> List<T> findListBySql(QueryRunner runner,Class<?> entityClass, String sql)  throws SQLException;
	
public abstract <T> List<T> findSingleRowListBySql(QueryRunner runner,Class<?> entityClass, String sql,Object[] queryParams)  throws SQLException;
	
	public abstract <T> List<T> findSingleRowListBySql(QueryRunner runner,Class<?> entityClass, String sql)  throws SQLException;
	
	public abstract void executeSQL(QueryRunner runner,String sql) throws SQLException;
	
	public abstract void executeSQL(QueryRunner runner,String sql,Object[] params) throws SQLException;
	
	
	public abstract <T> QueryResult getScrollData(QueryRunner runner,int firstindex, int maxresult
			, String sql,Class<T> model) throws Exception;
	
	public abstract <T> QueryResult getScrollData(QueryRunner runner,int firstindex,
			int maxresult, String sql, Object[] queryParams,Class<T> model) throws Exception ;
	
	public abstract Integer findCountBySql(QueryRunner runner, String sql, Object[] queryParams) throws SQLException;

	
}
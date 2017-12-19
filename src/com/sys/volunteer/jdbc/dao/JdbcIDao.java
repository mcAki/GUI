package com.sys.volunteer.jdbc.dao;

import java.util.List;

public interface JdbcIDao {

	/**
	 * 
	 * @param sql
	 * @param args 查询值
	 * @param argTypes 查询参数类型
	 * @param model 反射的Class
	 * @return
	 */
	public List<?> queryList(String sql,Object[] args,int[] argTypes,Class<?> model);
	
	public List<?> queryList(String sql,Class<?> model);
	
	public void executeSql(String sql) ;
	
	public boolean save(String sql,Object[] obj,int[] objTypes);
	
	public boolean update(String sql,Object[] obj);
	
	/**
	 * 条件查询单列字段
	 * @param sql
	 * @param args
	 * @param model
	 * @return
	 */
	public Object queryObject(String sql,Object[] obj,Class<?> model);
	
	/**
	 * 查询记录数
	 * @param sql
	 * @param args
	 * @return
	 */
	public Integer queryCounts(String sql,Object[] obj);

	/**
	 * 批量处理增加,删除,更新
	 * @param sql
	 * @return
	 */
	public void batchExecute(String sql,Object[] objects);
}

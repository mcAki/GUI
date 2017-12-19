package com.sys.volunteer.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.pagemodel.QueryResult;

public interface IDao {

	public abstract Object getObject(Class<?> clazz, Serializable id);

	public abstract void removeObject(Class<?> clazz, Serializable id);
	public abstract void removeObject(Object obj);

	public abstract void init(final Object obj);

	public abstract void removeObjects(Collection<?> objects);

	public abstract void saveOrUpdateAll(Collection<?> objects);

	public abstract void saveOrUpdate(Object object);
	
	public abstract void save(Object obj) ;

	public abstract void update(Object obj) ;
	
	public abstract List<?> findByExample(Object obj);

	public abstract Object findById(Class<?> entityClass, Serializable id);
	
	public abstract List<?> findAll(Class<?> entityClass);
	
	public int executSql( String hql,Object[] queryParams);
	/**
	 * 单实体分页
	 * @param firstindex
	 * @param maxresult
	 * @param entityClass
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult getScrollData(int firstindex, int maxresult
			, Class<?> entityClass) throws Exception;

	/**
	 * Hql分页
	 * @param firstindex
	 * @param maxresult
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult getScrollData(int firstindex, int maxresult
			, String hql) throws Exception;
	
	/**
	 * HQL分页
	 * @param firstindex
	 * @param maxresult
	 * @param hql
	 * @param queryParams
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult getScrollData(int firstindex, int maxresult
			, String hql, Object[] queryParams) throws Exception;
	/**
	 * Criteria分页
	 * @param firstindex
	 * @param maxresult
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public QueryResult getScrollData(int firstindex, int maxresult
			, Criteria criteria) throws Exception;
	
	/**
	 * DetachedCriteria分页
	 * @param firstindex
	 * @param maxresult
	 * @param detachedCriteria
	 * @return
	 * @throws Exception
	 */
	public QueryResult getScrollData(int firstindex, int maxresult
			, DetachedCriteria detachedCriteria) throws Exception;
	
	/**
	 * DetachedCriteria获得所有数据
	 * @param detachedCriteria
	 * @return
	 */
	public List findByDetachedCriteria(final DetachedCriteria detachedCriteria);
	
	/**
	 * 通过HQL获得单一数据
	 * @param hql
	 * @param queryParams
	 * @return
	 */
	public Object getSingleValueByHql(String hql,Object[] queryParams);
	public QueryResult getScrollDataAndGetRoot(int firstindex, int maxresult,
			DetachedCriteria detachedCriteria) throws Exception ;
	
	public QueryResult getScrollDataBySQLQuery(String hql,
			Object queryParams[], int firstindex, int maxresult,
			String[] objName, Class[] cls, Class vo) throws Exception ;
	
	public Object findSingleObjectByDetachedCriteria(final DetachedCriteria detachedCriteria);
	
	public void flushSession();
	public void evit(Object obj);
	public void commitTransaction();
	
	/**
	 * 通过HQL获得所有记录
	 * @param hql
	 * @param queryParams
	 * @return
	 */
	public List findByHql(String hql, Object queryParams[]);

	/**
	 * 执行sql获取一个返回值的
	 * @param sql
	 * @return
	 */
	Object uniqueResultBySql(String sql);
	
	/**
	 * 执行sql获取一个返List
	 * @param sql
	 * @return
	 */
	List listBySqlQuery(String sql);

	/**
	 * 直接执行SQL语句的结果集(unsafe)
	 * @param sql
	 * @return
	 */
	SQLQuery SqlQuery(String sqlStr);
	
	/**
	 * 直接执行SQL语句的结果集(unsafe)
	 * @param sql
	 * @return
	 */
	SQLQuery SqlQuery(String sqlStr, Object[] queryParams);
}
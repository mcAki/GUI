package com.sys.volunteer.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.exception.SystemException;
import com.sys.volunteer.pagemodel.QueryResult;

@Transactional
public abstract class CommonDao implements IDao {
	protected Log log = LogFactory.getLog(this.getClass());

	@Resource
	protected HibernateTemplate hibernateTemplate;

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Object getObject(Class<?> clazz, Serializable id) {
		return hibernateTemplate.get(clazz, id);

	}

	public void removeObject(Class<?> clazz, Serializable id) {
		Object obj = getObject(clazz, id);
		if (obj != null) hibernateTemplate.delete(obj);

	}

	public void removeObject(Object obj) {
		this.hibernateTemplate.delete(obj);
	}

	public void init(final Object obj) {
		if (!Hibernate.isInitialized(obj)) {
			hibernateTemplate.initialize(obj);
		}
	}

	public void removeObjects(Collection<?> objects) {
		hibernateTemplate.deleteAll(objects);
	}

	public void saveOrUpdateAll(Collection<?> objects) {
		hibernateTemplate.saveOrUpdateAll(objects);
	}

	public void saveOrUpdate(Object object) {
		hibernateTemplate.saveOrUpdate(object);
	}

	public void save(Object obj) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(obj);
	}

	public void update(Object obj) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(obj);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<?> findByExample(Object obj) {
		return this.hibernateTemplate.findByExample(obj);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Object findById(Class<?> entityClass, Serializable id) {
		return this.hibernateTemplate.get(entityClass.getName(), id);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<?> findAll(Class<?> entityClass) {
		return this.hibernateTemplate.find(" from " + entityClass.getName());
	}

	//
	public int executSql(String hql, Object[] queryParams) {

		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			hql);
		query = setParams(queryParams, query);
		return query.executeUpdate();
	}
	
	/**
	 * 执行一条sql语句
	 * @param sql
	 * @return
	 */
	public int executSql(String sql) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			sql);
		return query.executeUpdate();
	}
	
	public List listBySql(String hql, Object[] queryParams) {

		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			hql);
		query = setParams(queryParams, query);
		return query.list();
	}

	/**
	 * 单实体分页
	 * 
	 * @param firstindex
	 * @param maxresult
	 * @param entityClass
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult getScrollData(int firstindex, int maxresult, Class<?> entityClass)
			throws Exception {
		return this.getScrollData(firstindex, maxresult, " from " + entityClass.getName(), null);
	}

	/**
	 * Hql分页
	 * 
	 * @param firstindex
	 * @param maxresult
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult getScrollData(int firstindex, int maxresult, String hql) throws Exception {
		return this.getScrollData(firstindex, maxresult, hql, null);
	}

	/**
	 * HQL分页 firstindex maxresult都等于-1可以获得全部数据
	 * 
	 * @param firstindex
	 * @param maxresult
	 * @param hql
	 * @param queryParams
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult getScrollData(int firstindex, int maxresult, String hql, Object[] queryParams)
			throws Exception {
		QueryResult qr = new QueryResult();

		String countHql = getCountQuery(hql);
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(
			countHql);
		query = setParams(queryParams, query);
		int total = ((Long) query.uniqueResult()).intValue();

		query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		query = setParams(queryParams, query);
		if (firstindex != -1 && maxresult != -1) query.setFirstResult(firstindex).setMaxResults(
			maxresult);

		qr.setResultlist(query.list());
		qr.setTotalrecord(total);
		return qr;
	}


	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult getScrollDataBySQLQuery(String hql, Object queryParams[], int firstindex, int maxresult, String[] objName, Class[] cls, Class vo)
			throws Exception {
		QueryResult qr = new QueryResult();

		SQLQuery query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			hql);
		setParams(queryParams, query);
		if (objName != null && objName.length > 0) {
			for (int i = 0; i < objName.length; i++) {
				query.addEntity(objName[i], cls[i]);
			}
		}
		if (vo != null) {
			query.setResultTransformer(Transformers.aliasToBean(vo));
		}
		List totallist = query.list();
		// if (firstindex != -1 && maxresult != -1)
		// query.setFirstResult(firstindex).setMaxResults(maxresult);
		if (totallist != null && totallist.size() > 0) {
			if (firstindex != -1 && maxresult != -1) {
				List resultList = totallist.subList(firstindex,
					totallist.size() > firstindex + maxresult ? maxresult : totallist.size());
				qr.setResultlist(resultList);
			} else {
				qr.setResultlist(totallist);
			}
		} else {
			qr.setResultlist(totallist);
		}
		qr.setTotalrecord(totallist.size());
		return qr;
	}

	/**
	 * DetachedCriteria分页
	 * 
	 * @param firstindex
	 * @param maxresult
	 * @param detachedCriteria
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult getScrollData(int firstindex, int maxresult, DetachedCriteria detachedCriteria)
			throws Exception {
		QueryResult qr = new QueryResult();

		int total = this.getCountQuery(detachedCriteria);
		List list = this.findPage(detachedCriteria, firstindex, maxresult);
		qr.setResultlist(list);
		qr.setTotalrecord(total);
		return qr;
	}

	/**
	 * DetachedCriteria分页
	 * 
	 * @param firstindex
	 * @param maxresult
	 * @param detachedCriteria
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult getScrollDataAndGetRoot(int firstindex, int maxresult, DetachedCriteria detachedCriteria)
			throws Exception {
		QueryResult qr = new QueryResult();

		int total = this.getCountQuery(detachedCriteria);
		List list = this.findPageAndGetRoot(detachedCriteria, firstindex, maxresult);
		qr.setResultlist(list);
		qr.setTotalrecord(total);
		return qr;
	}

	/**
	 * Criteria分页
	 * 
	 * @param firstindex
	 * @param maxresult
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult getScrollData(int firstindex, int maxresult, Criteria criteria)
			throws Exception {
		QueryResult qr = new QueryResult();
		int total = this.getCountQuery(criteria);
		List list = this.findPage(criteria, firstindex, maxresult);
		qr.setResultlist(list);
		qr.setTotalrecord(total);
		return qr;
	}

	private Query setParams(Object[] queryParams, Query query) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i, queryParams[i]);
			}
		}
		return query;
	}

	/**
	 * 根据HQL语句，获得查找总记录数的HQL语句 如： select ... from Orgnization o where o.parent is null 经过转换，可以得到：
	 * select count(*) from Orgnization o where o.parent is null
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	private String getCountQuery(String hql) throws Exception {
		int index = hql.indexOf("from");
		if (index != -1) {
			return "select count(*) " + hql.substring(index);
		}

		throw new SystemException("HQL error！");
	}

	/**
	 * 用DetachedCriteria分页时计算总记录数
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	private int getCountQuery(final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(this.hibernateTemplate.getSessionFactory().getCurrentSession());
		Integer tempCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		return tempCount;
	}

	/**
	 * 用Criteria分页时计算总记录数
	 * 
	 * @param criteria
	 * @return
	 */
	private int getCountQuery(final Criteria criteria) {
		Integer tempCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		return tempCount;
	}

	/**
	 * 使用DetachedCriteria分页获得列表
	 * 
	 * @param detachedCriteria
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	private List findPage(final DetachedCriteria detachedCriteria, final int firstindex, final int maxresult) {

		Criteria criteria = detachedCriteria.getExecutableCriteria(this.hibernateTemplate.getSessionFactory().getCurrentSession());

		List items = criteria.setFirstResult(firstindex).setMaxResults(maxresult).list();
		return items;

	}

	/**
	 * 使用DetachedCriteria分页获得列表
	 * 
	 * @param detachedCriteria
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	private List findPageAndGetRoot(final DetachedCriteria detachedCriteria, final int firstindex, final int maxresult) {

		Criteria criteria = detachedCriteria.getExecutableCriteria(this.hibernateTemplate.getSessionFactory().getCurrentSession());
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		List items = criteria.setFirstResult(firstindex).setMaxResults(maxresult).list();
		return items;

	}

	public List findByDetachedCriteria(final DetachedCriteria detachedCriteria) {

		Criteria criteria = detachedCriteria.getExecutableCriteria(this.hibernateTemplate.getSessionFactory().getCurrentSession());
		List items = criteria.list();
		return items;

	}

	public Object findSingleObjectByDetachedCriteria(final DetachedCriteria detachedCriteria) {

		Criteria criteria = detachedCriteria.getExecutableCriteria(this.hibernateTemplate.getSessionFactory().getCurrentSession());
		return criteria.uniqueResult();

	}

	/**
	 * 使用Criteria分页获得列表
	 * 
	 * @param criteria
	 * @param firstindex
	 * @param maxresult
	 * @return
	 */
	private List findPage(final Criteria criteria, final int firstindex, final int maxresult) {

		List items = criteria.setFirstResult(firstindex).setMaxResults(maxresult).list();
		return items;

	}

	public Object getSingleValueByHql(String hql, Object[] queryParams) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(
			hql);
		query = setParams(queryParams, query);
		return query.uniqueResult();
	}

	/**
	 * 本地sql查询
	 * 
	 * @param sql
	 * @param values
	 * @param maxSize
	 * @param objName
	 * @param cls
	 * @return
	 */
	public List listBySqlQuery(String sql, Object values[], int firstindex, int maxSize, String[] objName, Class[] cls, Class vo) {
		SQLQuery query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			sql);
		// .addEntity(objName,cls);
		if (objName != null && objName.length > 0) {
			for (int i = 0; i < objName.length; i++) {
				query.addEntity(objName[i], cls[i]);
			}
		}
		if (vo != null) {
			query.setResultTransformer(Transformers.aliasToBean(vo));
		}
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		if (query == null) {
			return null;
		}
		query.setFirstResult(firstindex);
		query.setMaxResults(maxSize);
		List list = query.list();
		return list;
	}

	/**
	 * 本地sql查询
	 * 
	 * @param sql
	 * @param values
	 * @param maxSize
	 * @param objName
	 * @param cls
	 * @return
	 */
	public List listBySqlQuery(String sql, Object values[], String[] objName, Class[] cls, Class vo) {
		SQLQuery query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			sql);
		// .addEntity(objName,cls);
		if (objName != null && objName.length > 0) {
			for (int i = 0; i < objName.length; i++) {
				query.addEntity(objName[i], cls[i]);
			}
		}
		if (vo != null) {
			query.setResultTransformer(Transformers.aliasToBean(vo));
		}
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		if (query == null) {
			return null;
		}
		List list = query.list();
		return list;
	}

	/**
	 * 执行sql获取一个返回表的
	 * 
	 * @param sql
	 * @return
	 */
	@Override
	public List listBySqlQuery(String sql) {
		SQLQuery query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			sql);
		return query.list();
	}

	/**
	 * 直接执行SQL语句的结果集(unsafe)
	 * 
	 * @param sqlStr
	 * @return
	 */
	@Override
	public SQLQuery SqlQuery(String sqlStr) {
		SQLQuery query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			sqlStr);
		return query;
	}


	/**
	 * 获取sql语句
	 * 
	 * @param sqlStr
	 * @param queryParams
	 * @return
	 */
	@Override
	public SQLQuery SqlQuery(String sqlStr, Object queryParams[]) {
		SQLQuery query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			sqlStr);
		setParams(queryParams, query);
		return query;
	}

	/**
	 * 执行sql获取一个返回值的
	 * 
	 * @param sql
	 * @return
	 */
	@Override
	public Object uniqueResultBySql(String sql) {
		SQLQuery query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(
			sql);
		return query.uniqueResult();
	}

	/**
	 * 通过HQL获得所有记录
	 * 
	 * @param hql
	 * @param queryParams
	 * @return
	 */
	public List findByHql(String hql, Object queryParams[]) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(
			hql);
		query = setParams(queryParams, query);
		return query.list();
	}

	/**
	 * 通过HQL获得所有记录
	 * 
	 * @param hql
	 * @param queryParams
	 * @return
	 */
	public List findByHql(String hql) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(
			hql);
		return query.list();
	}

	public void commitTransaction() {
		hibernateTemplate.getSessionFactory().getCurrentSession().getTransaction().commit();
	}

	public void flushSession() {
		hibernateTemplate.getSessionFactory().getCurrentSession().flush();
	}

	/**
	 * 使用对象由持久化状态转换为游离状态
	 */
	public void evit(Object obj) {
		hibernateTemplate.getSessionFactory().getCurrentSession().evict(obj);
	}

}

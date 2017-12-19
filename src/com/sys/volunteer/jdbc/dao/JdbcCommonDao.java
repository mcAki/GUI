package com.sys.volunteer.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mprs.util.SysUtil;


@Transactional
public abstract class JdbcCommonDao implements JdbcIDao {

	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	protected JdbcTemplate jdbcTemplate;
	
	/**
	 * 内部类，用来通用查询，返回每条记录对应的对象
	 * @author guoxi
	 *
	 */
	private  class CommonRowMapper implements RowMapper {   
	    //默认已经执行rs.next(),可以直接取数据   
		private Class<?>  model;
		public CommonRowMapper(Class<?>  model){
			this.model=model;
			
		}
	    public Object mapRow(ResultSet rs, int index) throws SQLException {   
	    	Object obj=SysUtil.getModel(rs, model);
	        return obj;   
	    }   
	}  

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<?> queryList(String sql,Object[] args,int[] argTypes,Class<?> model){
		List<?> list=this.jdbcTemplate.query(sql, args, argTypes, new CommonRowMapper(model));
		return list;
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<?> queryList(String sql,Class<?> model){
		List<?> list=this.jdbcTemplate.query(sql, new CommonRowMapper(model));
		return list;
	}

	@Override
	public void batchExecute(String sql,final Object[] objects) {
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				for (int j = 0; j < getBatchSize(); j++) {
					ps.setObject(j+1, objects[j]);
				}
				
			}
			
			@Override
			public int getBatchSize() {
				return objects.length;
			}
		});
		
	}

	@Override
	public void executeSql(String sql) {
		this.jdbcTemplate.execute(sql);
	}

	@Override
	public boolean save(String sql, Object[] obj, int[] objTypes) {
		int result=this.jdbcTemplate.update(sql, obj, objTypes);
		return result>0;
	}

	@Override
	public boolean update(String sql, Object[] obj) {
		int result=this.jdbcTemplate.update(sql, obj);
		return result>0;
	}

	@Override
	public Integer queryCounts(String sql, Object[] obj) {
		int counts=this.jdbcTemplate.queryForInt(sql,obj);
		return counts;
	}

	@Override
	public Object queryObject(String sql, Object[] obj, Class<?> model) {
		Object object=this.jdbcTemplate.queryForObject(sql, obj, new CommonRowMapper(model));
		return object;
	}


	
	
}

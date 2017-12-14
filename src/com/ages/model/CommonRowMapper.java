package com.ages.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import com.ages.util.SysUtil;

@SuppressWarnings("unchecked")
public class CommonRowMapper<T> implements ResultSetHandler{

	private Class<?>  model;
	public CommonRowMapper(Class<?>  model){
		this.model=model;
		
	}
	@Override
	public Object handle(ResultSet rs) throws SQLException {
//		 if (!rs.next()) {
//	         return null;
//	       }
		 List list=new ArrayList();
		
		 while(rs.next()){
			 list.add(SysUtil.getModel(rs, model));
		 }
		 return list;
	}
	
}
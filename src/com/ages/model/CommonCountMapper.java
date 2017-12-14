package com.ages.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

@SuppressWarnings("unchecked")
public class CommonCountMapper<Integer> implements ResultSetHandler{

	public CommonCountMapper(){
	}
	@Override
	public Object handle(ResultSet rs) throws SQLException {
		
		 if(rs.next()){
			 return rs.getInt(1);
		 }else{
			 return 0;
		 }
		 
	}
	
}
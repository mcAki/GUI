package com.sys.volunteer.jdbc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.pojo.Users;

@Repository
@Transactional
public class EchoDao extends JdbcCommonDao implements IEchoDao{

	@SuppressWarnings("unchecked")
	@Override
	public void testQuery() {
		List<Users> list=(List<Users>) this.queryList("select user_id as userId from users", Users.class);
		for(Users person:list){
			System.out.println(person);
			
		}
//		list=(List<Users>) this.queryList("select user_id as userId from users where name like '%s%'", Users.class);
//		for(Users person:list){
//			System.out.println(person);
//		}
//		
//		list=(List<Users>) this.queryList("select user_id as userId from users where id=?",new Object[]{1},new int[]{Types.INTEGER}, Users.class);
//		for(Users person:list){
//			System.out.println(person);
//		
//		}
	}
	
}

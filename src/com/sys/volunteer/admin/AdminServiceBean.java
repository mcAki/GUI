package com.sys.volunteer.admin;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ages.model.Administrators;
import com.ages.model.Administratorsgroup;
import com.ages.util.DbManager;
import com.sys.volunteer.dao.CommonDao;
/**
 * 
 * @author Administrator
 *	管理员业务
 */
@Service
public class AdminServiceBean extends CommonDao implements AdminService {

	/**
	 * 获取admin包
	 * @param id 主键id
	 * @return
	 */
	public Administrators findAdminById(int id) {		
		try {
			return (Administrators)this.findObjectBySql(DbManager.getGmRunner(), Administrators.class, "select * from administrators where id= " + id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 删除admin记录
	 * @param id
	 */
	public void delAdminById(int id){
		try {
			this.executeSQL(DbManager.getGmRunner(), "delete from administrators where id="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save(Administrators administrators){
		
	}
	
	public void update(Administrators administrators){
		
	}
	
	public Administrators checkLogin(String userName,String passWord){
		try {
			Administrators administrator = this.findObjectBySql(DbManager.getGmRunner(), Administrators.class, "select * from administrators where userName= ? and passWord= ?",new Object[]{userName,passWord});
			if (administrator==null) {
				return null;
			}
			Administratorsgroup group=this.findObjectBySql(DbManager.getGmRunner(), Administratorsgroup.class, "select * from administratorsgroup where id=?", new Object[]{administrator.getAdministratorsgroup()});
			administrator.setAdminGroup(group);
			return administrator;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

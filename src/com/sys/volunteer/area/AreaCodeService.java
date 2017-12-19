package com.sys.volunteer.area;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.AreaCode;

public interface AreaCodeService extends IDao {

	
	public List<AreaCode> findGroupByCities();
	
	public AreaCode findByMobile(String mobile);
	
}

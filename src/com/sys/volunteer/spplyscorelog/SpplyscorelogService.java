package com.sys.volunteer.spplyscorelog;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Spplyscorelog;
import com.sys.volunteer.pojo.Users;

public interface SpplyscorelogService extends IDao {

	public void addLog(Spplyscorelog spplyscorelog,Users manager);
}

package com.sys.volunteer.spplyscorelog;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Spplyscorelog;
import com.sys.volunteer.pojo.Users;
@Service
@Transactional
public class SpplyscorelogServiceBean extends CommonDao implements
		SpplyscorelogService {

	@Override
	public void addLog(Spplyscorelog spplyscorelog, Users manager) {
		spplyscorelog.setCreateTime(new Date());
		spplyscorelog.setUsers(manager);
		this.save(spplyscorelog);
	}

}

package com.sys.volunteer.http;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.PosMsg;
import com.sys.volunteer.pojo.PosSignIn;

public interface PosSignInService extends IDao {

	public PosSignIn findLastRecord();
}

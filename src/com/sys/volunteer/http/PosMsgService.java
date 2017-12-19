package com.sys.volunteer.http;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.PosMsg;

public interface PosMsgService extends IDao {

	public List<PosMsg> findByOrderId(String orderId, String msgType);
	
	public PosMsg addPosMsg(PosMsg posMsg);
}

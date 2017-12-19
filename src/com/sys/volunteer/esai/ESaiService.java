package com.sys.volunteer.esai;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.ESaiCharge;
import com.sys.volunteer.pojo.ESaiQuery;
import com.sys.volunteer.pojo.Mainorder;

public interface ESaiService extends IDao {

	
	public ESaiCharge initESaiCharge(Mainorder mainorder);
	
	public ESaiQuery initESaiQuery(ESaiCharge eSaiCharge);

	public int updateDealingOrders(Mainorder mainorder);

	public ESaiQuery findESaiQueryByOrderId(String orderId);

	public ESaiQuery updateESaiQuery(Mainorder mainorder, ESaiQuery reESaiQuery);

	public ESaiCharge findESaiChargeByOrderId(String orderId);

	public ESaiCharge updateESaiCharge(Mainorder mainorder, ESaiCharge reESaiCharge);
}

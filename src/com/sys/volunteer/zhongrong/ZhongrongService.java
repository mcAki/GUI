package com.sys.volunteer.zhongrong;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Zhongrong;

public interface ZhongrongService extends IDao {

	
	public Zhongrong initZhongrong(Mainorder mainorder, String mobile,SupplyInterface supplyInterface,String productType, int isGame,String productId);

	public Zhongrong findZhongrongByOrderId(String orderId);

	public Zhongrong updateZhongrong(Mainorder mainorder, Zhongrong reZr);

	public int updateDealingOrders(Mainorder mainorder) throws Exception;
}

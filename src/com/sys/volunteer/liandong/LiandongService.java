package com.sys.volunteer.liandong;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Liandong;
import com.sys.volunteer.pojo.LiandongPay;
import com.sys.volunteer.pojo.LiandongQQ;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.SupplyInterface;

public interface LiandongService extends IDao {

	public int updateDealingOrders(Mainorder mainorder) throws Exception;
	
	public LiandongPay findLiandongPayById(String id);
	
	public LiandongPay addLiandongPay(LiandongPay liandongPay);
	
	public LiandongPay updateLiandongPayStatus(LiandongPay liandongPay,int status);

	public Liandong findLiandongByOrderId(String orderId);

	public int reverseOrder(Liandong liandong);

	public Liandong initLiandong(Mainorder mainorder, String mobile,
			SupplyInterface supplyInterface);

	public Liandong  updateLiandong(Mainorder mainorder,Liandong reLd);

	public LiandongQQ findLiandongQQByOrderId(String orderId);

	public LiandongQQ initLiandongQQ(Mainorder mainorder, String QQnum,
			SupplyInterface supplyInterface);

	public LiandongQQ updateLiandongQQ(Mainorder mainorder, LiandongQQ reLdQQ);

	public int updateQQDealingOrders(Mainorder mainorder) throws Exception;
}

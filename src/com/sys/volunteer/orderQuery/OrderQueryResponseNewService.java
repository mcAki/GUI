package com.sys.volunteer.orderQuery;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import cn.epaylinks.www.TerminalAirOrderQueryResponseNew;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.AirDepositResponseNew;
import com.sys.volunteer.pojo.AirReversalResponseNew;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.OrderQueryResponseNew;

public interface OrderQueryResponseNewService extends IDao {

	public OrderQueryResponseNew initOrderQueryResponseNew(AirDepositResponseNew airDepositResponseNew,Mainorder mainorder,String userMobile);
	
	public OrderQueryResponseNew initOrderQueryResponseNew(
			AirReversalResponseNew airReversalResponseNew,Mainorder mainorder,String userMobile);
	
	public AirReversalResponseNew initAirReversalResponseNew(
			String mainOrderId, String mobileNum, double amount, String storeSeq) throws MalformedURLException, ServiceException, RemoteException;
	
	public List<OrderQueryResponseNew> findOrderQueryResponseNewsByDepositState();
	
	public List<OrderQueryResponseNew> findOrderQueryResponseNewByOrderId(String storeSeq,int logFor);
	
	public int refreshOrderState(Mainorder mainorder) throws Exception;
	
	public List<OrderQueryResponseNew> findOrderQueryResponseNewByStoreSeq(String orderId,int logFor);

	public int updateOrderState(
			TerminalAirOrderQueryResponseNew terminalAirOrderQueryResponseNew,int logFor)
			throws Exception;
	
}

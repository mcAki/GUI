package com.sys.volunteer.terminalAirDepositResponseNew;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import cn.epaylinks.www.TerminalAirDepositResponseNew;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.AirDepositResponseNew;
import com.sys.volunteer.pojo.Mainorder;

public interface DepositResponseService extends IDao {

	public AirDepositResponseNew addTerminalAirDepositResponseNew(Mainorder mainorder,String userMobile,Double money,int payType) throws MalformedURLException, RemoteException, ServiceException;
	
	public TerminalAirDepositResponseNew findTerminalAirDepositResponseNew(AirDepositResponseNew airDepositResponseNew);
	
	public AirDepositResponseNew finDepositResponseNewByStoreSeq(String storeSeq);

	public AirDepositResponseNew finDepositResponseNewByOrderId(String orderId);
}

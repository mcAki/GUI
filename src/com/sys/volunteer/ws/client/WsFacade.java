package com.sys.volunteer.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.rpc.ServiceException;

import cn.epaylinks.www.TerminalAirDepositRequestNew;
import cn.epaylinks.www.TerminalAirDepositResponseNew;
import cn.epaylinks.www.TerminalAirOrderQueryRequestNew;
import cn.epaylinks.www.TerminalAirOrderQueryResponseNew;
import cn.epaylinks.www.TerminalAirReversalRequestNew;
import cn.epaylinks.www.TerminalAirReversalResponseNew;

import com.sys.volunteer.common.FileTool;
import com.sys.volunteer.common.MD5;
import com.sys.volunteer.pojo.AirDepositRequestNew;
import com.sys.volunteer.pojo.OrderQueryResponseNew;

public class WsFacade {

	private static String password = "21218cca77804d2ba1922c33e0151105";
	
	private static String terminalNo;
	
	private static String useAccount = (String) FileTool.loadConfig("useAccount",	"/wsconfig.properties");

	public static TerminalAirDepositRequestNew initTerminalAirDepositRequestNew(
			String mobileNum, double amount, int payType)
			throws MalformedURLException, ServiceException, RemoteException {
		if (useAccount.equals("211")) {
			 terminalNo = (String) FileTool.loadConfig("terminalRealNo211",
					 "/wsconfig.properties");
		}else {
			terminalNo = (String) FileTool.loadConfig("terminalRealNo183",
					"/wsconfig.properties");
			password = MD5.getiInstance().getMD5ofStr(
					(String) FileTool.loadConfig("terminalRealPwd183",
							"/wsconfig.properties")).toLowerCase();
		}
		String areaCode = (String) FileTool.loadConfig("areaCode",
				"/wsconfig.properties");
		System.out.println("经过加密的password是:" + password);
		TerminalAirDepositRequestNew terminalAirDepositRequestNew = new TerminalAirDepositRequestNew();
		terminalAirDepositRequestNew.setAmount(amount);
		terminalAirDepositRequestNew.setMobileNum(mobileNum);
		terminalAirDepositRequestNew.setPassword("000000");
		terminalAirDepositRequestNew.setTerminalNo(terminalNo);
		String seqNo = String.valueOf(Calendar.getInstance().getTimeInMillis());
		terminalAirDepositRequestNew.setStoreSeq(seqNo);
		terminalAirDepositRequestNew.setPayType(payType);

		Calendar now = GregorianCalendar.getInstance();
		Calendar before = new GregorianCalendar(1970, 0, 1, 8, 0);
		long result = now.getTimeInMillis() - before.getTimeInMillis();

		// System.out.println(DateUtil.DateToString(new
		// Date(now.getTimeInMillis()), DateUtil.CM_LONG_DATE_FORMAT) );
		// System.out.println(DateUtil.DateToString(new
		// Date(before.getTimeInMillis()), DateUtil.CM_LONG_DATE_FORMAT) );

		terminalAirDepositRequestNew.setRequestTime(now);
		terminalAirDepositRequestNew.setDepositType("000001");

		terminalAirDepositRequestNew.setAreaCode(areaCode);
		String sign = MD5.MD5String(terminalAirDepositRequestNew
				.getTerminalNo()
				+ terminalAirDepositRequestNew.getPassword()
				+ terminalAirDepositRequestNew.getMobileNum()
				+ Double.toString(terminalAirDepositRequestNew.getAmount())
				+ terminalAirDepositRequestNew.getAreaCode()
				+ terminalAirDepositRequestNew.getStoreSeq()
				+ result
				+ password);
		terminalAirDepositRequestNew.setSign(sign);

		return terminalAirDepositRequestNew;
	}

	public static TerminalAirDepositResponseNew terminalAirDeposit3(
			TerminalAirDepositRequestNew terminalAirDepositRequestNew)
			throws MalformedURLException, ServiceException, RemoteException {
		String url = (String) FileTool.loadConfig("iphoneRealService",
				"/wsconfig.properties");
		TerminalService terminalService = new TerminalServiceServiceLocator()
				.getTerminalService(new URL(url));
		TerminalAirDepositResponseNew terminalAirDepositResponseNew = terminalService
				.terminalAirDeposit3(terminalAirDepositRequestNew);
		System.out.println(terminalAirDepositResponseNew.getRespDisc());
		System.out.println(terminalAirDepositResponseNew.getOrderNo());
		System.out.println(terminalAirDepositResponseNew.getStoreSeq());
		System.out.println(terminalAirDepositResponseNew.getRespCode());

		return terminalAirDepositResponseNew;
	}

	public static TerminalAirOrderQueryResponseNew terminalAirOrderQueryNew(
			OrderQueryResponseNew orderQueryResponseNew)
			throws MalformedURLException, ServiceException, RemoteException {
		String url = (String) FileTool.loadConfig("iphoneRealService",
				"/wsconfig.properties");
		if (useAccount.equals("211")) {
			 terminalNo = (String) FileTool.loadConfig("terminalRealNo211",
					 "/wsconfig.properties");
		}else {
			terminalNo = (String) FileTool.loadConfig("terminalRealNo183",
					"/wsconfig.properties");
			password = MD5.getiInstance().getMD5ofStr(
					(String) FileTool.loadConfig("terminalRealPwd183",
							"/wsconfig.properties")).toLowerCase();
		}
		String areaCode = (String) FileTool.loadConfig("areaCode",
				"/wsconfig.properties");
		System.out.println("经过加密的password是:" + password);
		TerminalService terminalService = new TerminalServiceServiceLocator()
				.getTerminalService(new URL(url));
		TerminalAirOrderQueryRequestNew terminalAirOrderQueryRequestNew = new TerminalAirOrderQueryRequestNew();
		terminalAirOrderQueryRequestNew.setTerminalNo(terminalNo);
		terminalAirOrderQueryRequestNew.setStoreSeq(orderQueryResponseNew
				.getStoreSeq());
		terminalAirOrderQueryRequestNew.setUserMobileNum(orderQueryResponseNew
				.getUserMobileNum());
		terminalAirOrderQueryRequestNew.setOrderNo(orderQueryResponseNew
				.getOrderNo());
		Calendar now = GregorianCalendar.getInstance();
		Calendar before = new GregorianCalendar(1970, 0, 1, 8, 0);
		long result = now.getTimeInMillis() - before.getTimeInMillis();
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.add(Calendar.DATE, -3);
		Calendar endCalendar = Calendar.getInstance();
		Date beginDate = orderQueryResponseNew.getBeginDate();// DateUtil.getMinDate(new
		// Date());
		Date endDate = orderQueryResponseNew.getEndDate();// DateUtil.getMaxDate(new
		// Date());
		beginCalendar.setTime(beginDate);
		endCalendar.setTime(endDate);

		// System.out.println(DateUtil.DateToString(new
		// Date(beginCalendar.getTimeInMillis()), DateUtil.CM_LONG_DATE_FORMAT)
		// );
		// System.out.println(DateUtil.DateToString(new
		// Date(endCalendar.getTimeInMillis()), DateUtil.CM_LONG_DATE_FORMAT) );

		terminalAirOrderQueryRequestNew.setBeginDate(beginCalendar);
		terminalAirOrderQueryRequestNew.setEndDate(endCalendar);

		if (orderQueryResponseNew.getLogFor() == 1) {
			terminalAirOrderQueryRequestNew.setDepositState(1);
			terminalAirOrderQueryRequestNew.setReversalState(-100);
		} else {
			terminalAirOrderQueryRequestNew.setDepositState(-100);
			terminalAirOrderQueryRequestNew.setReversalState(1);
		}

		terminalAirOrderQueryRequestNew.setRequestTime(now);
		terminalAirOrderQueryRequestNew.setDepositId(0);
		// Sign=md5(terminalNo + orderNo + depositId +
		// userMobileNum+depositState+reversalState+
		// storeSeq+beginDate+endDate+requestTime+md5(password))
		String signString = terminalAirOrderQueryRequestNew.getTerminalNo()
				+ terminalAirOrderQueryRequestNew.getOrderNo()
				+ terminalAirOrderQueryRequestNew.getDepositId()
				+ terminalAirOrderQueryRequestNew.getUserMobileNum()
				+ terminalAirOrderQueryRequestNew.getDepositState()
				+ terminalAirOrderQueryRequestNew.getReversalState()
				+ terminalAirOrderQueryRequestNew.getStoreSeq()
				+ String.valueOf(terminalAirOrderQueryRequestNew.getBeginDate()
						.getTimeInMillis())
				+ String.valueOf(terminalAirOrderQueryRequestNew.getEndDate()
						.getTimeInMillis()) + result + password;
		System.out.println(signString);
		String sign = MD5.MD5String(signString);
		System.out.println(sign);
		terminalAirOrderQueryRequestNew.setSign(sign);

		TerminalAirOrderQueryResponseNew terminalAirOrderQueryResponseNew = terminalService
				.terminalAirOrderQueryNew(terminalAirOrderQueryRequestNew);
		System.out.println(terminalAirOrderQueryResponseNew.getRespDisc());
		System.out.println(terminalAirOrderQueryResponseNew.getOrderNo());
		System.out.println(terminalAirOrderQueryResponseNew.getStoreSeq());
		System.out.println(terminalAirOrderQueryResponseNew.getRespCode());

		return terminalAirOrderQueryResponseNew;
	}

	public static TerminalAirReversalRequestNew initAirReversalRequestNew(
			String mainOrderId, String mobileNum, double amount, String storeSeq)
			throws MalformedURLException, ServiceException, RemoteException {
		if (useAccount.equals("211")) {
			 terminalNo = (String) FileTool.loadConfig("terminalRealNo211",
					 "/wsconfig.properties");
		}else {
			terminalNo = (String) FileTool.loadConfig("terminalRealNo183",
					"/wsconfig.properties");
			password = MD5.getiInstance().getMD5ofStr(
					(String) FileTool.loadConfig("terminalRealPwd183",
							"/wsconfig.properties")).toLowerCase();
		}
		System.out.println("经过加密的password是:" + password);
		TerminalAirReversalRequestNew terminalAirReversalRequestNew = new TerminalAirReversalRequestNew();
		terminalAirReversalRequestNew.setTerminalNo(terminalNo);
		terminalAirReversalRequestNew.setPassword("000000");
		terminalAirReversalRequestNew.setReversalStoreSeq(storeSeq);
		terminalAirReversalRequestNew.setStoreSeq(storeSeq);
		terminalAirReversalRequestNew.setMobileNum(mobileNum);
		terminalAirReversalRequestNew.setAmount(amount);

		Calendar now = GregorianCalendar.getInstance();
		Calendar before = new GregorianCalendar(1970, 0, 1, 8, 0);
		long result = now.getTimeInMillis() - before.getTimeInMillis();

		terminalAirReversalRequestNew.setRequestTime(now);

		// Sign=md5(terminalNo+password+mobileNum+amount+storeSeq+reversalStoreSeq+requestTime+md5(password))

		String sign = MD5.MD5String(terminalAirReversalRequestNew
				.getTerminalNo()
				+ terminalAirReversalRequestNew.getPassword()
				+ terminalAirReversalRequestNew.getMobileNum()
				+ Double.toString(terminalAirReversalRequestNew.getAmount())
				+ terminalAirReversalRequestNew.getStoreSeq()
				+ terminalAirReversalRequestNew.getReversalStoreSeq()
				+ result
				+ password);

		terminalAirReversalRequestNew.setSign(sign);
		
		return terminalAirReversalRequestNew;
	}

	public static TerminalAirReversalResponseNew terminalAirReversalNew(
			TerminalAirReversalRequestNew terminalAirReversalRequestNew)
			throws MalformedURLException, ServiceException, RemoteException {
		String url = (String) FileTool.loadConfig("iphoneRealService",
				"/wsconfig.properties");
		TerminalService terminalService = new TerminalServiceServiceLocator()
				.getTerminalService(new URL(url));
		TerminalAirReversalResponseNew terminalAirReversalResponseNew = terminalService
				.terminalAirReversalNew(terminalAirReversalRequestNew);
		return terminalAirReversalResponseNew;
	}

	public static void main(String args[]) {
		// try {
		// terminalAirDeposit3("13710234090",30,0);
		// TerminalAirDepositResponseNew terminalAirDepositResponseNew =
		// terminalAirDeposit3(
		// "13710234090", 30, 0);
		// terminalAirOrderQueryNew(terminalAirDepositResponseNew);
		// TerminalAirReversalResponseNew
		// terminalAirReversalResponseNew=terminalAirReversalNew("13710234090",30);
		// } catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (RemoteException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ServiceException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}

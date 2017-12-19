package com.sys.volunteer.terminalAirDepositResponseNew;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.epaylinks.www.TerminalAirDepositRequestNew;
import cn.epaylinks.www.TerminalAirDepositResponseNew;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.AirDepositRequestNew;
import com.sys.volunteer.pojo.AirDepositResponseNew;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.ws.client.WsFacade;
@Service
@Transactional
public class DepositResponseServiceBean extends CommonDao implements
		DepositResponseService {
	
	protected Log logger = LogFactory.getLog(this.getClass());

	@Override
	public AirDepositResponseNew addTerminalAirDepositResponseNew(
			Mainorder mainorder,String userMobile, Double money, int payType) throws MalformedURLException, RemoteException, ServiceException {
		AirDepositResponseNew airDepositResponseNew = null;
		airDepositResponseNew = this.finDepositResponseNewByOrderId(mainorder.getMainOrderId());
		if (airDepositResponseNew != null) {
			logger.info("该订单已经进行了一次充值,请检查线程重复下单....");
			return null;
		}
		TerminalAirDepositRequestNew terminalAirDepositRequestNew = WsFacade.initTerminalAirDepositRequestNew(userMobile, money, 0);
		AirDepositRequestNew airDepositRequestNew = new AirDepositRequestNew();
		airDepositRequestNew.setAmount(terminalAirDepositRequestNew.getAmount());
		airDepositRequestNew.setAreaCode(terminalAirDepositRequestNew.getAreaCode());
		airDepositRequestNew.setMainorder(mainorder);
		airDepositRequestNew.setMobileNum(userMobile);
		airDepositRequestNew.setRequestTime(terminalAirDepositRequestNew.getRequestTime().getTime());
		airDepositRequestNew.setSign(terminalAirDepositRequestNew.getSign());
		airDepositRequestNew.setStoreSeq(terminalAirDepositRequestNew.getStoreSeq());
		airDepositRequestNew.setTerminalNo(terminalAirDepositRequestNew.getTerminalNo());
		airDepositRequestNew.setPayType(terminalAirDepositRequestNew.getPayType());
		airDepositRequestNew.setDepositType(terminalAirDepositRequestNew.getDepositType());
		this.save(airDepositRequestNew);
		
		logger.info("这里插入了一条airDepositRequest,orderId是:"+airDepositRequestNew.getMainorder().getMainOrderId());
		
		TerminalAirDepositResponseNew terminalAirDepositResponseNew=WsFacade.terminalAirDeposit3(terminalAirDepositRequestNew);
		
		if (terminalAirDepositResponseNew != null) {
			airDepositResponseNew = new AirDepositResponseNew();
			airDepositResponseNew.setAmount(terminalAirDepositResponseNew.getAmount());
			airDepositResponseNew.setAreaCode(terminalAirDepositResponseNew.getAreaCode());
			airDepositResponseNew.setMainorder(mainorder);
			airDepositResponseNew.setMobileNum(userMobile);
			airDepositResponseNew.setOrderNo(terminalAirDepositResponseNew.getOrderNo());
			airDepositResponseNew.setRespCode(terminalAirDepositResponseNew.getRespCode());
			airDepositResponseNew.setRespDisc(terminalAirDepositResponseNew.getRespDisc());
			airDepositResponseNew.setResponseTime(terminalAirDepositResponseNew.getResponseTime().getTime());
			airDepositResponseNew.setSign(terminalAirDepositResponseNew.getSign());
			airDepositResponseNew.setStoreSeq(terminalAirDepositResponseNew.getStoreSeq());
			airDepositResponseNew.setTerminalNo(terminalAirDepositResponseNew.getTerminalNo());
			this.save(airDepositResponseNew);
			logger.info("这里返回了一条airDepositResponse,respCode是:"+airDepositResponseNew.getRespCode());
		}else {
			logger.error("e票联充值接口返回了空对象!");
		}
		
		
		return airDepositResponseNew;
	}

	@Override
	public TerminalAirDepositResponseNew findTerminalAirDepositResponseNew(
			AirDepositResponseNew airDepositResponseNew) {
		TerminalAirDepositResponseNew terminalAirDepositResponseNew=new TerminalAirDepositResponseNew();
		terminalAirDepositResponseNew.setAmount(airDepositResponseNew.getAmount());
		terminalAirDepositResponseNew.setAreaCode(airDepositResponseNew.getAreaCode());
		terminalAirDepositResponseNew.setMobileNum(airDepositResponseNew.getMobileNum());
		terminalAirDepositResponseNew.setOrderNo(airDepositResponseNew.getOrderNo());
		terminalAirDepositResponseNew.setRespCode(airDepositResponseNew.getRespCode());
		terminalAirDepositResponseNew.setRespDisc(airDepositResponseNew.getRespDisc());
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(airDepositResponseNew.getResponseTime());
		terminalAirDepositResponseNew.setResponseTime(calendar);
		terminalAirDepositResponseNew.setSign(airDepositResponseNew.getSign());
		terminalAirDepositResponseNew.setStoreSeq(airDepositResponseNew.getStoreSeq());
		terminalAirDepositResponseNew.setTerminalNo(airDepositResponseNew.getTerminalNo());
		return terminalAirDepositResponseNew;
	}

	@Override
	public AirDepositResponseNew finDepositResponseNewByStoreSeq(String storeSeq) {
		AirDepositResponseNew airDepositResponseNew=null;
		DetachedCriteria dec=DetachedCriteria.forClass(AirDepositResponseNew.class);
		dec.add(Restrictions.eq("storeSeq", storeSeq));
		List<AirDepositResponseNew> list=this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			airDepositResponseNew=list.get(0);
		}
		return airDepositResponseNew;
	}
	
	@Override
	public AirDepositResponseNew finDepositResponseNewByOrderId(String orderId) {
		AirDepositResponseNew airDepositResponseNew=null;
		DetachedCriteria dec=DetachedCriteria.forClass(AirDepositResponseNew.class);
		dec.add(Restrictions.eq("mainorder.id", orderId));
		List<AirDepositResponseNew> list=this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			airDepositResponseNew=list.get(0);
		}
		return airDepositResponseNew;
	}

}

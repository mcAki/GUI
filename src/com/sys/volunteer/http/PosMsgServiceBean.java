package com.sys.volunteer.http;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.PosMsg;

@Service
@Transactional
public class PosMsgServiceBean extends CommonDao implements PosMsgService {

	protected static final Log logger = LogFactory.getLog(PosMsgServiceBean.class);
	
	@Override
	public List<PosMsg> findByOrderId(String orderId ,String msgType) {
		DetachedCriteria dec = DetachedCriteria.forClass(PosMsg.class);
		dec.add(Restrictions.eq("mainorder", orderId));
		dec.add(Restrictions.eq("msgType", msgType));
		dec.add(Restrictions.eq("retCode", "0000"));
		List<PosMsg> list = this.findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public PosMsg addPosMsg(PosMsg posMsg) {
		logger.info("增加一条posMsg记录");
		this.save(posMsg);
		return posMsg;
	}
}

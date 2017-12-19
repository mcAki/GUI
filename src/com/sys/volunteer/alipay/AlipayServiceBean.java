/**
 * 
 */
package com.sys.volunteer.alipay;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.Alipay;
import com.sys.volunteer.pojo.AlipayBack;

/**
 * @author hanwen
 *
 * @version 创建时间：2013-1-9  下午05:55:57
 */

@Service
@Transactional
public class AlipayServiceBean extends CommonDao implements AlipayService {

	/* (non-Javadoc)
	 * @see com.sys.volunteer.alipay.AlipayService#addAlipay(com.sys.volunteer.pojo.Alipay)
	 */
	@Override
	public Alipay addAlipay(Alipay alipay) {
		alipay.setTradeTime(new Date());
		alipay.setStatus("0");
		this.save(alipay);
		return alipay;
	}

	/* (non-Javadoc)
	 * @see com.sys.volunteer.alipay.AlipayService#findAll()
	 */
	@Override
	public List<Alipay> findAll() {
		return (List<Alipay>)this.findAll(Alipay.class);
	}

	/* (non-Javadoc)
	 * @see com.sys.volunteer.alipay.AlipayService#findByTradeNo()
	 */
	@Override
	public Alipay findByTradeNo(String tradeNo) {
		DetachedCriteria dec = DetachedCriteria.forClass(Alipay.class);
		dec.add(Restrictions.eq("tradeNo", tradeNo));
		List<Alipay> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public AlipayBack findByTradeNoBack(String tradeNo) {
		DetachedCriteria dec = DetachedCriteria.forClass(AlipayBack.class);
		dec.add(Restrictions.eq("tradeNo", tradeNo));
		List<AlipayBack> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sys.volunteer.alipay.AlipayService#findByUserId()
	 */
	@Override
	public List<Alipay> findByUserId(String userId) {
		List<Alipay> list = (List<Alipay>)this.findById(Alipay.class, userId);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sys.volunteer.alipay.AlipayService#updateAlipay(com.sys.volunteer.pojo.Alipay)
	 */
	@Override
	public Alipay updateAlipay(Alipay alipay) {
		this.update(alipay);
		return alipay;
	}

	@Override
	public AlipayBack addAlipayBack(AlipayBack alipayBack) {
		alipayBack.setTradeTime(new Date());
		this.save(alipayBack);
		return alipayBack;
	}

	@Override
	public List<AlipayBack> findByUserIdBack(String userId) {
		List<AlipayBack> list = (List<AlipayBack>)this.findById(AlipayBack.class, userId);
		return list;
	}

	
}

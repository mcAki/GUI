package com.sys.volunteer.http;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.PosSignIn;

@Service
@Transactional
public class PosSignInServiceBean extends CommonDao implements PosSignInService {

	@Override
	public PosSignIn findLastRecord() {
		DetachedCriteria dec = DetachedCriteria.forClass(PosSignIn.class);
		dec.addOrder(Order.desc("id"));
		List<PosSignIn> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	

}

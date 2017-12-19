package com.sys.volunteer.customer;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Alipay;
import com.sys.volunteer.pojo.CustomerFeedback;

@Service
@Transactional
public class CustomerFeedbackServiceBean extends CommonDao implements CustomerFeedbackService {

	@Override
	public CustomerFeedback addcfb(CustomerFeedback customerFeedback) {
		customerFeedback.setCfTime(new Date());
		this.save(customerFeedback);
		return customerFeedback;
	}

	@Override
	public CustomerFeedback delcfbById(int id) {
		this.removeObject(CustomerFeedback.class, id);
         return null;
	}

	@Override
	public List<CustomerFeedback> findAllcfb() {
		List<CustomerFeedback> list = (List<CustomerFeedback>)this.findAll(CustomerFeedback.class);
		return list;
	}

	@Override
	public CustomerFeedback findById(int id) {
		DetachedCriteria dec = DetachedCriteria.forClass(CustomerFeedback.class);
		dec.add(Restrictions.eq("cfId", id));
		List<CustomerFeedback> list = findByDetachedCriteria(dec);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public CustomerFeedback updatecfb(CustomerFeedback customerFeedback) {
		this.update(customerFeedback);
		return null;
	}

	
}

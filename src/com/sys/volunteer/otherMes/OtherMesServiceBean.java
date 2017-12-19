package com.sys.volunteer.otherMes;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.OtherMes;

@Service
@Transactional
public class OtherMesServiceBean extends CommonDao implements OtherMesService {

	@Override
	public List<OtherMes> findAll() {
		List<OtherMes> list = (List<OtherMes>)this.findAll(OtherMes.class);
		return list;
	}

	@Override
	public OtherMes findById(int id) {
		OtherMes list = (OtherMes)this.findById(OtherMes.class, id);
		return list;
	}

	@Override
	public List<OtherMes> findByType(int typeId) {
		DetachedCriteria dec = DetachedCriteria.forClass(OtherMes.class);
		dec.add(Restrictions.eq("typeId", typeId));
		List<OtherMes> list = this.findByDetachedCriteria(dec);
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public OtherMes addOtherMes(OtherMes om) {
	    this.save(om);
		return om ;
	}

	@Override
	public OtherMes updateOtherMes(OtherMes om) {
        this.update(om);
 		return om;
	}

	@Override
	public List<OtherMes> findNewTop() {
		DetachedCriteria dec = DetachedCriteria.forClass(OtherMes.class);
		dec.add(Restrictions.eq("newTop", 1));
		dec.add(Restrictions.eq("typeId", 1));
		dec.addOrder(Order.desc("createTime"));
		List<OtherMes> list = this.findByDetachedCriteria(dec);
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public OtherMes delOtherMes(OtherMes om) {
		this.removeObject(om);
		return om;
	}

	@Override
	public List<OtherMes> findCommonTop() {
		List<OtherMes> list = new ArrayList<OtherMes>();
		DetachedCriteria dec = DetachedCriteria.forClass(OtherMes.class);
		dec.add(Restrictions.eq("newTop", 0));
		dec.add(Restrictions.eq("typeId", 1));
		dec.addOrder(Order.desc("createTime"));
		 list = this.findByDetachedCriteria(dec);
			return list;
	}

}

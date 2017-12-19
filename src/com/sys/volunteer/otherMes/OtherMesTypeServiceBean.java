package com.sys.volunteer.otherMes;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.OtherMes;
import com.sys.volunteer.pojo.OtherMesType;

@Service
@Transactional
public class OtherMesTypeServiceBean extends CommonDao implements OtherMesTypeService {

	@Override
	public List<OtherMesType> findAll() {
		List<OtherMesType> list = (List<OtherMesType>)this.findAll(OtherMesType.class);
		return list;
	}

	@Override
	public OtherMesType findById(int id) {
		OtherMesType list = (OtherMesType)this.findById(OtherMesType.class, id);
		if(list.getTypeId()>0){
			return list;
		}
		return null;
	}

	@Override
	public OtherMesType addOtherMesType(OtherMesType om) {
	    this.save(om);
		return om ;
	}

	@Override
	public OtherMesType updateOtherMesType(OtherMesType om) {
        this.update(om);
 		return om;
	}

}

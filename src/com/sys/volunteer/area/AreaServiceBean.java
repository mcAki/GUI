package com.sys.volunteer.area;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Area;
import com.sys.volunteer.pojo.AreaCode;

@Service
@Transactional
public class AreaServiceBean extends CommonDao implements AreaService {

	@Override
	public Area findById(Integer provinceCode) {
		Area area = (Area)this.findById(Area.class, provinceCode);
		return area;
	}

	@Override
	public Area findByAreaCode(String areaCode) {
		DetachedCriteria dec = DetachedCriteria.forClass(Area.class);
		dec.add(Restrictions.eq("areaCode", areaCode));
		List<Area> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}

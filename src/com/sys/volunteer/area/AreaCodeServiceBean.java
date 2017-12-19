package com.sys.volunteer.area;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Area;
import com.sys.volunteer.pojo.AreaCode;

@Service
@Transactional
public class AreaCodeServiceBean extends CommonDao implements AreaCodeService {

	@Override
	public List<AreaCode> findGroupByCities() {
		DetachedCriteria dec = DetachedCriteria.forClass(AreaCode.class);
		dec.setProjection(Projections.groupProperty("city"));
		List<AreaCode> list = this.findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public AreaCode findByMobile(String mobile) {
		String number = mobile.substring(0, 7);
		AreaCode areaCode = (AreaCode) this.findById(AreaCode.class, number);
		return areaCode;
	}

}

package com.sys.volunteer.area;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Area;
import com.sys.volunteer.pojo.AreaCode;

public interface AreaService extends IDao {

	public Area findById(Integer provinceCode);

	public Area findByAreaCode(String areaCode);
}

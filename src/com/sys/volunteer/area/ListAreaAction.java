package com.sys.volunteer.area;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.pojo.Area;
import com.sys.volunteer.pojo.AreaCode;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListAreaAction extends BaseListAction {

	@Resource
	private AreaService areaService;
	
	private List<Area> areas;
	
	private String province;
	
	/**
	 * 地区列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		DetachedCriteria dec = DetachedCriteria.forClass(Area.class);
		if (!SysUtil.isNull(province)) {
			dec.add(Restrictions.like("province", province, MatchMode.ANYWHERE));
		}
		areas = areaService.findByDetachedCriteria(dec);
		return SUCCESS;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}


	
}

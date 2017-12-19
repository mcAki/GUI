package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MissionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "area", catalog = "mprs")
public class Area implements java.io.Serializable {

	private String province;
	private Integer provinceCode;
	private String areaCode;
	
	@Column(name="province")
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "province_code", unique = true, nullable = false)
	public Integer getProvinceCode() {
		return provinceCode;
	}
	
	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Column(name="area_code")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	

}
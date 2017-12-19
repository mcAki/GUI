package com.sys.volunteer.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * MissionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "area_code", catalog = "mprs")
public class AreaCode implements java.io.Serializable {

	private String mobileNum;
	private String business;
	private String areaCode;
	private String city;
	private String province;
	private Integer businessType;
	private Integer provinceCode;
	private Integer isGuangdong;
	private String supplyIds;
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "mobile_num", unique = true, nullable = false)
	public String getMobileNum() {
		return mobileNum;
	}
	
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	@Column(name="business")
	public String getBusiness() {
		return business;
	}
	
	public void setBusiness(String business) {
		this.business = business;
	}
	
	@Column(name="area_code")
	public String getAreaCode() {
		return areaCode;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Column(name="city")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="province")
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column(name="business_type")
	public Integer getBusinessType() {
		return businessType;
	}
	
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	
	@Column(name="province_code")
	public Integer getProvinceCode() {
		return provinceCode;
	}
	
	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Column(name="is_guangdong")
	public Integer getIsGuangdong() {
		return isGuangdong;
	}

	public void setIsGuangdong(Integer isGuangdong) {
		this.isGuangdong = isGuangdong;
	}

	@Column(name="supply_ids")
	public String getSupplyIds() {
		return supplyIds;
	}

	public void setSupplyIds(String supplyIds) {
		this.supplyIds = supplyIds;
	}
	
	
	
	

}
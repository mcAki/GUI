package com.sys.volunteer.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DistrictVo implements Serializable{

	private String districtId;
	private Integer districtLevel;
	private String districtName;
	private String districtCode;
	private String districtPid;
	private Integer sort;
	private Integer haveChild;
	private String remark;
	private String selflevCode;
	private List<DistrictVo> childrenId;
	
	public DistrictVo(){
		childrenId = new ArrayList<DistrictVo>();
	}
	
	/**
	 * @return the districtId
	 */
	public String getDistrictId() {
		return districtId;
	}
	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	/**
	 * @return the districtLevel
	 */
	public Integer getDistrictLevel() {
		return districtLevel;
	}
	/**
	 * @param districtLevel the districtLevel to set
	 */
	public void setDistrictLevel(Integer districtLevel) {
		this.districtLevel = districtLevel;
	}
	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * @param districtName the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**
	 * @return the districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}
	/**
	 * @param districtCode the districtCode to set
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * @return the districtPid
	 */
	public String getDistrictPid() {
		return districtPid;
	}
	/**
	 * @param districtPid the districtPid to set
	 */
	public void setDistrictPid(String districtPid) {
		this.districtPid = districtPid;
	}
	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * @return the haveChild
	 */
	public Integer getHaveChild() {
		return haveChild;
	}
	/**
	 * @param haveChild the haveChild to set
	 */
	public void setHaveChild(Integer haveChild) {
		this.haveChild = haveChild;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the selflevCode
	 */
	public String getSelflevCode() {
		return selflevCode;
	}
	/**
	 * @param selflevCode the selflevCode to set
	 */
	public void setSelflevCode(String selflevCode) {
		this.selflevCode = selflevCode;
	}
	/**
	 * @return the delFlag
	 */

	/**
	 * @return the childrenId
	 */
	public List<DistrictVo> getChildrenId() {
		return childrenId;
	}
	/**
	 * @param childrenId the childrenId to set
	 */
	public void setChildrenId(List<DistrictVo> childrenId) {
		this.childrenId = childrenId;
	}
	
	/**
	 * 是否包含自己
	 * @param includeSelf
	 * @return
	 */
	public String getChildrenIdStr(boolean includeSelf){
		String s="";
		if(includeSelf){
			s+="'" + getDistrictId() + "',";
		}
		for (DistrictVo districtVo : childrenId) {
			s += "'" + districtVo.getDistrictId() + "',";
		}
		if(s.endsWith(",")){
			s = s.substring(0, s.length()-1);
		}
		return s;
	}
	
}

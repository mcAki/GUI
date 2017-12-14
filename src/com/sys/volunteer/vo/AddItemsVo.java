package com.sys.volunteer.vo;

public class AddItemsVo {

	/**
	 * 搜索账号类型:1.账号名,2角色名
	 */
	private Integer searchAccountType;
	/**
	 * 搜索账号
	 */
	private String searchAccount;
	/**
	 * 道具id
	 */
	private Integer addItemId;
	/**
	 * 数量
	 */
	private Integer count;
	
	public Integer getSearchAccountType() {
		return searchAccountType;
	}
	
	public void setSearchAccountType(Integer searchAccountType) {
		this.searchAccountType = searchAccountType;
	}
	
	public String getSearchAccount() {
		return searchAccount;
	}
	
	public void setSearchAccount(String searchAccount) {
		this.searchAccount = searchAccount;
	}
	
	public Integer getAddItemId() {
		return addItemId;
	}
	
	public void setAddItemId(Integer addItemId) {
		this.addItemId = addItemId;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
}

package com.ages.model;

import java.util.ArrayList;
import java.util.List;


public class Administratorsgroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String adminGroupName;
	private Integer status;
	private Integer count;

	private List<Menutree> menutrees = new ArrayList<Menutree>();
	// Constructors

	/** default constructor */
	public Administratorsgroup() {
	}

	/** full constructor */
	public Administratorsgroup(String adminGroupName, Integer status,
			Integer count) {
		this.adminGroupName = adminGroupName;
		this.status = status;
		this.count = count;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminGroupName() {
		return this.adminGroupName;
	}

	public void setAdminGroupName(String adminGroupName) {
		this.adminGroupName = adminGroupName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Menutree> getMenutrees() {
		return menutrees;
	}

	public void setMenutrees(List<Menutree> menutrees) {
		this.menutrees = menutrees;
	}


}
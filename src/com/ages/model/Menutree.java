package com.ages.model;

import java.util.ArrayList;
import java.util.List;

public class Menutree implements java.io.Serializable {


	private static final long serialVersionUID = 730179427026030537L;
	private Integer id;
	private Integer parentId;
	private Menutree parentMenu;
	private String name;
	private String url;
	private Integer orderNo;
	private List<Menutree> subtrees=new ArrayList<Menutree>();
	// Constructors

	public List<Menutree> getSubtrees() {
		return subtrees;
	}

	public void setSubtrees(List<Menutree> subtrees) {
		this.subtrees = subtrees;
	}

	/** default constructor */
	public Menutree() {
		
	}

	/** minimal constructor */
	public Menutree(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public Menutree(Integer id, Menutree parentMenu, String name, String url,
			Integer orderNo) {
		this.id = id;
		this.parentMenu = parentMenu;
		this.name = name;
		this.url = url;
		this.orderNo = orderNo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menutree getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menutree parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
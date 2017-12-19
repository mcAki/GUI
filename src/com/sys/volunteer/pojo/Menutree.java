package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Menutree entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "menutree", catalog = "mprs", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Menutree implements java.io.Serializable {

	// Fields

	private Integer id;
	private Menutree parentMenu;
	private String name;
	private String url;
	private Integer orderNo;
	private Set<Usergroup> usergroups = new HashSet<Usergroup>(0);
	private Set<Menutree> menutrees = new HashSet<Menutree>(0);
	private List<Menutree> subtrees=new ArrayList<Menutree>();
	// Constructors
	
	@Transient
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
			Integer orderNo, Set<Usergroup> usergroups, Set<Menutree> menutrees) {
		this.id = id;
		this.parentMenu = parentMenu;
		this.name = name;
		this.url = url;
		this.orderNo = orderNo;
		this.usergroups = usergroups;
		this.menutrees = menutrees;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	public Menutree getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menutree parentMenu) {
		this.parentMenu = parentMenu;
	}

	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "orderNo")
	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menutrees")
	public Set<Usergroup> getUsergroups() {
		return this.usergroups;
	}

	public void setUsergroups(Set<Usergroup> usergroups) {
		this.usergroups = usergroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentMenu")
	public Set<Menutree> getMenutrees() {
		return this.menutrees;
	}

	public void setMenutrees(Set<Menutree> menutrees) {
		this.menutrees = menutrees;
	}

}
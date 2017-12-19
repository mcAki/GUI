package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usergroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usergroup", catalog = "mprs")
public class Usergroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String groupName;
	private Integer status;
	private Integer count;
	private Integer groupType;
	private Set<Users> userses = new HashSet<Users>(0);
	private Set<Menutree> menutrees = new HashSet<Menutree>(0);
	private Set<Permission> permissions = new HashSet<Permission>(0);
	private Set<SupplyInterface> supplyInterfaces = new HashSet<SupplyInterface>(0);
	// Constructors

	/** default constructor */
	public Usergroup() {
	}

	/** full constructor */
	public Usergroup(String groupName, Integer status, Integer count,
			Set<Users> userses, Set<Menutree> menutrees,
			Set<Permission> permissions) {
		this.groupName = groupName;
		this.status = status;
		this.count = count;
		this.userses = userses;
		this.menutrees = menutrees;
		this.permissions = permissions;
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

	@Column(name = "groupName", length = 50)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usergroup")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "menu_group", catalog = "mprs", joinColumns = { @JoinColumn(name = "groupId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "menuTreeId", nullable = false, updatable = false) })
	public Set<Menutree> getMenutrees() {
		return this.menutrees;
	}

	public void setMenutrees(Set<Menutree> menutrees) {
		this.menutrees = menutrees;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "permission_usergroup", catalog = "mprs", joinColumns = { @JoinColumn(name = "usergroup_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "permission_id", nullable = false, updatable = false) })
	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "sppin_usergroup", catalog = "mprs", joinColumns = { @JoinColumn(name = "usergroup_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "spplyin_id", nullable = false, updatable = false) })
	public Set<SupplyInterface> getSupplyInterfaces() {
		return supplyInterfaces;
	}

	public void setSupplyInterfaces(Set<SupplyInterface> supplyInterfaces) {
		this.supplyInterfaces = supplyInterfaces;
	}

	@Column(name="groupType")
	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

}
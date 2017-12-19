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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Permission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "permission", catalog = "mprs")
public class Permission implements java.io.Serializable {

	// Fields

	private Integer id;
	private PermissionType permissionType;
	private Integer privilegeAccess;
	private Integer matchReturn;
	private String description;
	private Set<Usergroup> usergroups = new HashSet<Usergroup>(0);

	// Constructors

	/** default constructor */
	public Permission() {
	}

	/** minimal constructor */
	public Permission(PermissionType permissionType, Integer matchReturn) {
		this.permissionType = permissionType;
		this.matchReturn = matchReturn;
	}

	/** full constructor */
	public Permission(PermissionType permissionType, Integer privilegeAccess, Integer matchReturn,String description,
						Set<Usergroup> usergroups) {
		this.permissionType = permissionType;
		this.privilegeAccess = privilegeAccess;
		this.matchReturn = matchReturn;
		this.description=description;
		this.usergroups = usergroups;
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
	@JoinColumn(name = "permission_type_id", nullable = false)
	public PermissionType getPermissionType() {
		return this.permissionType;
	}

	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
	}

	@Column(name = "privilege_access")
	public Integer getPrivilegeAccess() {
		return this.privilegeAccess;
	}

	public void setPrivilegeAccess(Integer privilegeAccess) {
		this.privilegeAccess = privilegeAccess;
	}

	@Column(name = "match_return", nullable = false)
	public Integer getMatchReturn() {
		return this.matchReturn;
	}

	public void setMatchReturn(Integer matchReturn) {
		this.matchReturn = matchReturn;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permissions")
	public Set<Usergroup> getUsergroups() {
		return this.usergroups;
	}

	public void setUsergroups(Set<Usergroup> usergroups) {
		this.usergroups = usergroups;
	}

}

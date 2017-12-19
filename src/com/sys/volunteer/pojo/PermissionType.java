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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PermissionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "permission_type", catalog = "mprs")
public class PermissionType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String key;
	private String condition;
	private String name;
	private Set<Permission> permissions = new HashSet<Permission>(0);

	// Constructors

	/** default constructor */
	public PermissionType() {
	}

	/** minimal constructor */
	public PermissionType(String key) {
		this.key = key;
	}

	/** full constructor */
	public PermissionType(String key, String condition, String name, Set<Permission> permissions) {
		this.key = key;
		this.condition = condition;
		this.name = name;
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

	@Column(name = "key", nullable = false, length = 100)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "condition", length = 300)
	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permissionType")
	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}

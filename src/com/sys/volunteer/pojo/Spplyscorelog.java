package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Spplyscorelog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "spplyscorelog", catalog = "mprs")
public class Spplyscorelog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Integer score;
	private Date createTime;
	private String desc;

	// Constructors

	/** default constructor */
	public Spplyscorelog() {
	}

	/** minimal constructor */
	public Spplyscorelog( Users users, Integer score,
			Date createTime) {
		this.users = users;
		this.score = score;
		this.createTime = createTime;
	}

	/** full constructor */
	public Spplyscorelog( Users users, Integer score,
			Date createTime, String desc) {
		this.users = users;
		this.score = score;
		this.createTime = createTime;
		this.desc = desc;
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
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "score", nullable = false)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "[desc]", length = 100)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
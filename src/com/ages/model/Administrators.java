package com.ages.model;


public class Administrators implements java.io.Serializable {

	// Fields

	private Integer id;
	private int administratorsgroup;
	private String realName;
	private Integer status;
	private String adminComment;
	private Integer loginCounts;
	private String loginIp;
	private String lastLoginIp;
	private String userName;
	private String passWord;
	private Administratorsgroup adminGroup;
	// Constructors

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/** default constructor */
	public Administrators() {
	}

	/** minimal constructor */
	public Administrators(
			Integer status, Integer loginCounts) {
		this.status = status;
		this.loginCounts = loginCounts;
	}

	/** full constructor */
	public Administrators(int administratorsgroup,
			 String realName,
			Integer status, String adminComment, Integer loginCounts,
			String loginIp, String lastLoginIp) {
		this.administratorsgroup = administratorsgroup;
		this.realName = realName;
		this.status = status;
		this.adminComment = adminComment;
		this.loginCounts = loginCounts;
		this.loginIp = loginIp;
		this.lastLoginIp = lastLoginIp;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public int getAdministratorsgroup() {
		return this.administratorsgroup;
	}

	public void setAdministratorsgroup(int administratorsgroup) {
		this.administratorsgroup = administratorsgroup;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAdminComment() {
		return this.adminComment;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}
	
	public Integer getLoginCounts() {
		return this.loginCounts;
	}

	public void setLoginCounts(Integer loginCounts) {
		this.loginCounts = loginCounts;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Administratorsgroup getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(Administratorsgroup adminGroup) {
		this.adminGroup = adminGroup;
	}

}
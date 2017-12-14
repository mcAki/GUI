package com.ages.util;

public class DatabaseConfig {
	private String driverClass = "com.mysql.jdbc.Driver";

	private String jdbcURL = "";

	private String userName = "";

	private String password = "";

	private int maxStatements = 100;

	private int minPoolSize = 5;

	private int acquireIncrement = 5;

	private int maxPoolSize = 10;
	
	private int numHelperThreads = 10;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxStatements() {
		return maxStatements;
	}

	public void setMaxStatements(int maxStatements) {
		this.maxStatements = maxStatements;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public int getAcquireIncrement() {
		return acquireIncrement;
	}

	public void setAcquireIncrement(int acquireIncrement) {
		this.acquireIncrement = acquireIncrement;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}


	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setJdbcURL(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}

	public String getJdbcURL() {
		return jdbcURL;
	}

	public int getNumHelperThreads() {
		return numHelperThreads;
	}

	public void setNumHelperThreads(int numHelperThreads) {
		this.numHelperThreads = numHelperThreads;
	}

}
package com.sys.volunteer.pagemodel;

import java.util.List;

public class QueryResult {
	private List resultlist;
	private long totalrecord;
	
	public List getResultlist() {
		return resultlist;
	}
	public void setResultlist(List resultlist) {
		this.resultlist = resultlist;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
}

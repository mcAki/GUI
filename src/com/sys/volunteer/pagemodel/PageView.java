package com.sys.volunteer.pagemodel;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/**
 * 分页数据
 * @author Administrator
 */
public class PageView implements PaginatedList {
	/**
	 * 分页数据
	 */
	private List records;
	/**
	 * 页码开始索引和结束索引 
	 */
	private PageIndex pageIndex;
	/**
	 * 总页数 
	 */
	private long totalPage = 1;
	/**
	 * 每页显示记录数
	 */
	private int pageSize = 10;
	/**
	 * 当前页
	 */
	private int currentPage = 1;
	/**
	 * 总记录数
	 */
	private long totalrecord;
	/**
	 * 页码数量
	 */
	private int pagecode = 10;
	/**
	 * 要获取记录的开始索引
	 * @return
	 */
	public int getFirstResult() {
		return (this.currentPage-1)*this.pageSize;
	}
	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	/**
	 * 构造器 
	 * @param maxresult 每页显示多少个
	 * @param currentpage 当前第几页
	 */
	public PageView(int maxresult, int currentpage) {
		this.pageSize = maxresult;
		this.currentPage = currentpage;
	}
	
	public void setQueryResult(QueryResult qr){
		setTotalrecord(qr.getTotalrecord());
		setRecords(qr.getResultlist());
	}
	
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		setTotalpage(this.totalrecord%this.pageSize==0? this.totalrecord/this.pageSize : this.totalrecord/this.pageSize+1);
	}
	public List getRecords() {
		return records;
	}
	public void setRecords(List records) {
		this.records = records;
	}
	public PageIndex getPageindex() {
		return pageIndex;
	}
	public long getTotalpage() {
		return totalPage;
	}
	public void setTotalpage(long totalpage) {
		this.totalPage = totalpage;
		this.pageIndex = PageIndex.getPageIndex(pagecode, currentPage, totalpage);
	}
	public int getMaxresult() {
		return pageSize;
	}
	public int getCurrentpage() {
		return currentPage;
	}
	
	
	
	
	@Override
	public int getFullListSize() {
		// TODO Auto-generated method stub
		return (int)this.totalrecord;
	}
	
	/**
	 * 获取数据表
	 */
	@Override
	public List getList() {
		return this.records;
	}
	
	/**
	 * 每页显示多少个记录
	 */
	@Override
	public int getObjectsPerPage() {
		return this.pageSize;
	}
	/**
	 * 当前第几页
	 */
	@Override
	public int getPageNumber() {
		return this.currentPage;
	}
	/**
	 * 列表查找用id (todo)
	 */
	@Override
	public String getSearchId() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 排序有关属性
	 */
	@Override
	public String getSortCriterion() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 排序方向
	 */
	@Override
	public SortOrderEnum getSortDirection() {
		// TODO Auto-generated method stub
		return null;
	}
}

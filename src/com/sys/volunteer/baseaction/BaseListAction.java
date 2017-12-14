package com.sys.volunteer.baseaction;

import com.sys.volunteer.pagemodel.PageView;

/**
 * 列表专用Action基础类
 * @author Seraph.Yang
 *
 */
public class BaseListAction extends BaseAction {
	
	private static final long serialVersionUID = -2250818067297346900L;

	/**
	 * 分页每页显示多少个
	 */
	private int pageSize = 20;

	/**
	 * 当前第几页
	 */
	private int pageIndex;

	/**
	 * 获取当前第几页
	 * 
	 * @return
	 */
	public int getP() {
		if (pageIndex <= 0)
			pageIndex = 1;
		return pageIndex;
	}

	/**
	 * 设置当前第几页
	 * 
	 * @param pageIndex
	 */
	public void setP(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return 分页每页显示多少个
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param 分页每页显示多少个
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取分页类xi
	 * 
	 * @return
	 */
	public PageView getPageView() {
		return pageView;
	}

	/**
	 * 分页类xi
	 * 
	 * @param pageView
	 */
	public void setPageView(PageView pageView) {
		this.pageView = pageView;
	}
	
	/**
	 * 获取当前页面
	 * 
	 * @return
	 */
	public int getPageIndex() {
		if (pageIndex <= 0)
			pageIndex = 1;
		return pageIndex;
	}

	/**
	 * 当前第几页
	 * @param pageIndex
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
}

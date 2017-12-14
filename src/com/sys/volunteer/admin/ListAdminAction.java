package com.sys.volunteer.admin;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ages.model.Administrators;
import com.ages.util.DbManager;
import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListAdminAction extends BaseListAction {
	@Resource
	AdminService adminService;

	/**
	 * 删除管理员页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		adminService.delAdminById(getId());		
		//ShowMessagePage("删除成功", "返回列表", "admin/list.do?p="+getP());
		list();
		return "success";
	}


	/**
	 * 管理员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void list() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());

		QueryResult queryResult = adminService.getScrollData(DbManager.getGmRunner(),pageView
				.getFirstResult(), pageView.getMaxresult(), "select * from Administrators",Administrators.class);
		pageView.setQueryResult(queryResult);
	}


	/**
	 * 执行
	 */
	@Override
	public String execute() throws Exception {
		list();
		return SUCCESS;
	}

	/**
	 * 验证数据
	 */
	@Override
	public void validate() {
	}

}

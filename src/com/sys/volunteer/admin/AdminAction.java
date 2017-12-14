package com.sys.volunteer.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ages.model.Administrators;
import com.ages.model.Administratorsgroup;
import com.ages.util.DbManager;
import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseAction;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class AdminAction extends BaseAction implements Preparable {
	@Resource
	AdminService adminService;

	@Resource
	AdminGroupService adminGroupService;

	/**
	 * 管理员的Bean
	 */
	private Administrators admin;
	/**
	 * 测试JQuery日期控件
	 */
	private Date myDate;

	/**
	 * 管理员组的Bean
	 */
	private List<Administratorsgroup> administratorsgroups;

	/**
	 * 利用接口Preparable重载出支持struts的预载 在validation前行为并初始化控件
	 */
	// @SuppressWarnings("unchecked")
	@Override
	public void prepare() throws Exception {
		// 获取管理员组元素 (用户绑定Select)
		this.administratorsgroups =this.adminGroupService.findListBySql(DbManager.getGmRunner(), Administratorsgroup.class, "select * from Administratorsgroup ");
		myDate = new Date();
	}

	/**
	 * 增加管理员页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		admin=new Administrators();
		admin.setId(0);
		return "do";
	}

	/**
	 * 修改管理员页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 获取管理员组元素 (用户绑定Select)
		admin = adminService.findAdminById(getId());
		return "do";
	}

	/**
	 * 提交更新
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doUpdate() throws Exception {

		if (admin.getId()<1) {

			// 初始化管理员数据...页面没设置表单
			admin.setLoginIp("127.0.0.1");
			admin.setLastLoginIp("127.0.0.1");
			admin.setStatus(0);
			admin.setLoginCounts(0);
			// 提交Hibernate
			adminService.save(admin);
			// 输出页面成功
			return ShowMessage(MSG_TYPE_NORMAL,"添加成功", "返回列表", "list.do?",0);
			//addActionMessage("actmsg:添加成功");
		} else {
			// 更新管理员
			admin.setLoginIp("127.0.0.1");
			admin.setLastLoginIp("127.0.0.1");
			admin.setStatus(0);
			admin.setLoginCounts(0);
			
			adminService.update(admin);
			return ShowMessage(MSG_TYPE_NORMAL,"修改成功", "返回列表", "list.do?",0);
			//addActionMessage("actmsg:更新成功");			
		}

	}

	public Administrators getAdmin() {
		return admin;
	}

	public void setAdmin(Administrators admin) {
		this.admin = admin;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public void setAdminGroupService(AdminGroupService adminGroupService) {
		this.adminGroupService = adminGroupService;
	}

	public List<Administratorsgroup> getAdministratorsgroups() {
		return administratorsgroups;
	}

	public void setAdministratorsgroups(
			List<Administratorsgroup> administratorsgroups) {
		this.administratorsgroups = administratorsgroups;
	}

	/**
	 * 执行
	 */
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 验证数据
	 */
	@Override
	public void validate() {
	}

	/**
	 * @return the myDate
	 */
	public Date getMyDate() {
		return myDate;
	}

	/**
	 * @param myDate the myDate to set
	 */
	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

}

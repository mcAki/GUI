package com.sys.volunteer.permission;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.users.UsergroupService;
import com.sys.volunteer.vo.PermissionVO;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
@SuppressWarnings("unchecked")
public class PermissionOperateAct extends BaseAction {

	@Resource
	private UsergroupService usergroupService;
	@Resource
	private PermissionService permissionService;
	@Resource
	PermissionVerification permissionVerification;
	
	List<Usergroup> listUsergroup;
	
	private int usergroupId;
	
	private String removeIds;
	
	private String addId;
	
	List<PermissionVO> permissionPageList;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String setPermissionPage() {
       listUsergroup = usergroupService.findAllUsergroup();
		return "setPermissionPage";
	}

	public String modifyPermissionPage(){
		try {
			  permissionPageList = permissionService.query(usergroupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modifyPermissionPage";
	}
	
	public String addUserGroupByPermissionId(){
		//if(addId!=null&&removeIds!=null){
			try {
				 permissionService.addUserGroupByPermissionId(addId, removeIds, usergroupId);
				 //修改后需要更新系统内存里的权限列表
				 permissionVerification.ReloadDate();
				permissionPageList = permissionService.query(usergroupId);
				this.getSession().put("permissionPageListSession", permissionPageList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//}
		return "addUserGroupByPermissionId";
	}
	
	
	public List<Usergroup> getListUsergroup() {
		return listUsergroup;
	}

	public void setListUsergroup(List<Usergroup> listUsergroup) {
		this.listUsergroup = listUsergroup;
	}

	public int getUsergroupId() {
		return usergroupId;
	}

	public void setUsergroupId(int usergroupId) {
		this.usergroupId = usergroupId;
	}

	public List<PermissionVO> getPermissionPageList() {
		return permissionPageList;
	}

	public void setPermissionPageList(List<PermissionVO> permissionPageList) {
		this.permissionPageList = permissionPageList;
	}

	public String getAddId() {
		return addId;
	}

	public void setAddId(String addId) {
		this.addId = addId;
	}

	public String getRemoveIds() {
		return removeIds;
	}

	public void setRemoveIds(String removeIds) {
		this.removeIds = removeIds;
	}

	

}

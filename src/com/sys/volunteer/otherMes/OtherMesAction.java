package com.sys.volunteer.otherMes;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.ResponseUtils;
import com.sys.volunteer.pojo.OtherMes;
import com.sys.volunteer.pojo.OtherMesType;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
public class OtherMesAction extends BaseAction{

	@Resource
	private OtherMesService otherMesService;
	@Resource
	private OtherMesTypeService otherMesTypeService;
	
	private String typeName;

	private String message;
	
	private int typeId ;
	
	private int id;
	
	private String url;
	
	private String title;
	
    private int newTop;
	

	

	public void getTypeList(){
		List<OtherMesType> listType = otherMesTypeService.findAll();
		getSession().put("listType", listType);
	}
	
	public String addOtherMesPage(){
		List<OtherMesType> listType = otherMesTypeService.findAll();
		Map<Integer,String> map = new HashMap<Integer,String>();
		for (int i = 0; i < listType.size(); i++) {
			map.put(listType.get(i).getTypeId(), listType.get(i).getTypeName());
		}
		getSession().put("listType", map);
		return "page";
	}
	public String updateOtherMesPage(){
		getTypeList();
		OtherMes om = otherMesService.findById(id);
		this.getSession().put("otherMes", om);
		return "page";
	}
	
	public String addType(){
		OtherMesType omt = new OtherMesType();
		omt.setTypeName(typeName);
		otherMesTypeService.addOtherMesType(omt);
		if(omt.getTypeId()>0){
			OtherMesType listType = otherMesTypeService.findById(omt.getTypeId());
		  JSONObject json = new JSONObject();
		  json.put("typeId", listType.getTypeId());
		  json.put("typeName", listType.getTypeName());
           ResponseUtils.renderJson(getHttpServletResponse(), json.toString());		
           return null;
		}else{
			ResponseUtils.renderText(getHttpServletResponse(), "error");	
			return null;
		}
	}
	
	public String delOtherMes(){
		OtherMes om = otherMesService.findById(id);
		otherMesService.delOtherMes(om);
		return SUCCESS;
	}
	/**
	 *  保存一条公告记录
	 * @return
	 */
	public String addOtherMes(){
		OtherMes om = new OtherMes();
		om.setTypeId(typeId);
		om.setMessage(message);
		om.setUrl(url);
		om.setCreateTime(new Date());
		om.setTitle(title);
		om.setNewTop(newTop);
		otherMesService.addOtherMes(om);
		if(om.getId()>0){
			return "success";
		}
		return "error";
	}
	
	/**
	 * 查看所有公告
	 * @return
	 */
	public String findAll(){
		getTypeList();
		List<OtherMes> list = otherMesService.findAll();
		this.getSession().put("otherMesList", list);
		return "success";
	}
	
	public String findOneResult(){
		getTypeList();
		OtherMes om = otherMesService.findById(id);
		this.getSession().put("otherMes", om);
		return "success";
	}
	
	public String findByType(){
		List<OtherMes> list = otherMesService.findByType(typeId);
		getSession().put("listOtherMes", list);
		return "success";
	}
	
	/**
	 * 更新
	 * @return
	 */
	public String updateOtherMes(){
		OtherMes om = otherMesService.findById(id);
		om.setTypeId(typeId);
		//om.setCreateTime(new Date());
		om.setMessage(message);
		om.setUrl(url);
		om.setTitle(title);
		om.setNewTop(newTop);
		otherMesService.updateOtherMes(om);
		return "success";
	}
	/**
	 * 显示公告
	 * @return
	 */
	public String showOtherMes(){
		OtherMes om = otherMesService.findById(id);
		this.getSession().put("otherMes", om);
		return "success";
	}
	
	/**
	 * 通过id查询出来一条公告
	 */
	public void selBackOneNews(){
		OtherMes om = otherMesService.findById(id);
	    JSONObject json = new JSONObject();
	    json.put("title", om.getTitle());
	    json.put("message", om.getMessage());
	    json.put("createTime", om.getCreateTime());
	    ResponseUtils.renderJson(getHttpServletResponse(), json.toString());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getNewTop() {
		return newTop;
	}

	public void setNewTop(int newTop) {
		this.newTop = newTop;
	}
}

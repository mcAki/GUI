package com.sys.volunteer.supplyinterface;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.goods.GoodsTypeService;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.GoodsType;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.vo.SppinUsergroupVo;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
@SuppressWarnings("unchecked")
public class SupplyInterfaceAction extends BaseAction implements Preparable {

	@Resource
	GoodsTypeService goodsTypeService;
	@Resource
	GoodsService goodsService;
	@Resource
	SupplyService supplyService;
	@Resource
	SupplyInterfaceService supplyInterfaceService;
	
	private List<GoodsType> goodsTypes;
	
	private List<Goods> goodsList;
	
	private Goods goods;
	
	private Integer province;
	
	private String goodsId;
	
	private SupplyInterface supplyInterface;
	
	private String supplyInterfaceId;

	public Integer usergroupId;
	
	private String addId;
	
	private String removeId;
	
	public List<SppinUsergroupVo> sppinUsergroupList;
	
	public Integer supplyId;
	
	public Integer state;
	
	public Double value;
	
	public Integer canReverse;
	
	private List<ISupply> supplyList;
	
	private String spid;
	
	private String sdpid;
	
	@Override
	public void prepare() throws Exception {
		
	    DetachedCriteria dec=DetachedCriteria.forClass(GoodsType.class);
		dec.add(Restrictions.ge("id", 0));
		goodsTypes = (List<GoodsType>)goodsTypeService.findByDetachedCriteria(dec);
		
	}

	
	public void goodsList() throws Exception {
		
		if (province==0){
			goodsList = new ArrayList<Goods>();
		}else {
			DetachedCriteria dec=DetachedCriteria.forClass(Goods.class);
			dec.add(Restrictions.eq("goodsType.id", province));
			goodsList = (List<Goods>)goodsService.findByDetachedCriteria(dec);
		}
		
		
		
		//TODO 要记得加解释?????
        JsonConfig cfg = new JsonConfig();   
        cfg.setExcludes(new String[]{"handler","hibernateLazyInitializer"});  
		JSONArray json = JSONArray.fromObject(goodsList,cfg);
		sendMsg(json.toString());
	}
	
	 public void sendMsg(String content) throws Exception{       
	     HttpServletResponse response = ServletActionContext.getResponse();       
	     response.setCharacterEncoding("UTF-8");       
	     response.getWriter().write(content);       
	 }    
	
	
	
	/**
	 * 增加商品页面
	 * @return
	 * @throws Exception
	 */
	//@Privilege(permissionName = "goods", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String add() throws Exception{
		supplyInterface = new SupplyInterface();
		return "do";
	}
	
	/**
	 * 增加商品提交
	 * @return
	 * @throws Exception
	 */
	public String doAdd() throws Exception{
		//Supply supply=supplyService.findSupplyById(goods.getSupply().getId());
		//goods.setSupplyName(supply.getSupplyName());
		if (supplyInterface.getDefaultAlarm()==null) {
			supplyInterface.setDefaultAlarm(0);
		}
		if(goodsId == null){
			return ShowMessage(MSG_TYPE_NORMAL, "商品不能为空!请选择商品", "点击返回", "page!add.do", 0);
		}
		goods = goodsService.findById(Integer.parseInt(goodsId));
		supplyInterface.setGoods(goods);
		boolean result=SupplyKernel.getInstance().addSupplyInterface(supplyInterface, supplyInterface.getSupply(), goods);
		if (result) {
			return ShowMessage(MSG_TYPE_NORMAL, "增加成功", "点击返回", "page!add.do", 0);
		}else {
			return ShowMessage(MSG_TYPE_NORMAL, "请勿重复添加", "点击返回", "page!add.do", 0);
		}
		
	}
	
	/**
	 * 修改商品页面
	 * @return
	 * @throws Exception
	 */
	//@Privilege(permissionName = "goods", privilegeAccess = Const.PrivilegeAccessConst.MODIFY)
	public String update() throws Exception{
		
		supplyInterface=SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(Integer.parseInt(supplyInterfaceId));
		return "update";
	}
	
	/**
	 * 修改商品提交
	 * @return
	 * @throws Exception
	 */
	public String doUpdate() throws Exception{
		//Supply supply=supplyService.findSupplyById(goods.getSupply().getId());
		//goods.setSupplyName(supply.getSupplyName());
		SupplyKernel.getInstance().updateSupplyInterface(supplyInterface.getId(), supplyInterface);
		return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "点击返回", "list!listInterface.do", 0);
	}
	
	/**
	 * 禁用商品
	 * @return
	 * @throws Exception
	 */
	public String doDel() throws Exception{
		//TODO 明天搞分解id就可以了
		if (supplyInterfaceId.trim().length()>0) {
			SupplyKernel.getInstance().updateSupplyInterfaceState(supplyInterfaceId, 0);
			return ShowMessage(MSG_TYPE_NORMAL, "禁用成功", "点击返回", "list!listInterface.do", 0);
		} else {
			return ShowMessage(MSG_TYPE_NORMAL, "请选择操作", "点击返回", "list!listInterface.do", 0);
		}
	}
	
	/**
	 * 恢复商品
	 * @return
	 * @throws Exception
	 */
	public String doRecover() throws Exception{
		//TODO 明天搞分解id就可以了
		if (supplyInterfaceId.trim().length()>0) {
			SupplyKernel.getInstance().updateSupplyInterfaceState(supplyInterfaceId, 1);
			return ShowMessage(MSG_TYPE_NORMAL, "恢复成功", "点击返回", "list!listInterface.do", 0);
		} else {
			return ShowMessage(MSG_TYPE_NORMAL, "请选择操作", "点击返回", "list!listInterface.do", 0);
		}
	}

	/**
	 * spi对应usergroup(提交)
	 * @return
	 */
	public String addSupplyUsergroup(){
		supplyInterfaceService.addSppinUsergroupId(sdpid, spid, usergroupId);
		return "addSupplyUsergroup";
	}
	
	
	public List<GoodsType> getGoodsTypes() {
		return goodsTypes;
	}

	public void setGoodsTypes(List<GoodsType> goodsTypes) {
		this.goodsTypes = goodsTypes;
	}

	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	
	public List<Goods> getGoodsList() {
		return goodsList;
	}


	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}


	public SupplyInterface getSupplyInterface() {
		return supplyInterface;
	}


	public void setSupplyInterface(SupplyInterface supplyInterface) {
		this.supplyInterface = supplyInterface;
	}


	public String getSupplyInterfaceId() {
		return supplyInterfaceId;
	}


	public void setSupplyInterfaceId(String supplyInterfaceId) {
		this.supplyInterfaceId = supplyInterfaceId;
	}


	public Integer getProvince() {
		return province;
	}


	public void setProvince(Integer province) {
		this.province = province;
	}


	public String getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}


	public Integer getUsergroupId() {
		return usergroupId;
	}


	public void setUsergroupId(Integer usergroupId) {
		this.usergroupId = usergroupId;
	}


	public List<SppinUsergroupVo> getSppinUsergroupList() {
		return sppinUsergroupList;
	}


	public void setSppinUsergroupList(List<SppinUsergroupVo> sppinUsergroupList) {
		this.sppinUsergroupList = sppinUsergroupList;
	}


	public String getAddId() {
		return addId;
	}


	public void setAddId(String addId) {
		this.addId = addId;
	}


	public String getRemoveId() {
		return removeId;
	}


	public void setRemoveId(String removeId) {
		this.removeId = removeId;
	}


	public Integer getSupplyId() {
		return supplyId;
	}


	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	public Integer getCanReverse() {
		return canReverse;
	}


	public void setCanReverse(Integer canReverse) {
		this.canReverse = canReverse;
	}


	public List<ISupply> getSupplyList() {
		return supplyList;
	}


	public void setSupplyList(List<ISupply> supplyList) {
		this.supplyList = supplyList;
	}


	public String getSpid() {
		return spid;
	}


	public void setSpid(String spid) {
		this.spid = spid;
	}


	public String getSdpid() {
		return sdpid;
	}


	public void setSdpid(String sdpid) {
		this.sdpid = sdpid;
	}


//	public Integer getGoodsId() {
//		return goodsId;
//	}
//
//
//	public void setGoodsId(Integer goodsId) {
//		this.goodsId = goodsId;
//	}





//	public Integer getSupplyInterfaceId() {
//		return supplyInterfaceId;
//	}
//	public void setSupplyInterfaceId(Integer supplyInterfaceId) {
//		this.supplyInterfaceId = supplyInterfaceId;
//	}

}

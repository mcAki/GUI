package com.sys.volunteer.supply.usage;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Users;

public interface ISupply {

	/**
	 * 获取ID
	 * @return
	 */
	public Integer getId(); 
	
	/**
	 * 获取供货商名字
	 * @return
	 */
	public String getSupplyName();
	
	/**
	 * 获取DESC
	 * @return
	 */
	public String getDesc();
	
	/**
	 * 获取创建时间
	 * @return
	 */
	public Date getCreateTime();
	
	/**
	 * 保留字段1
	 * @return
	 */
	public String getReserve1();
	
	/**
	 * 保留字段2
	 * @return
	 */
	public String getReserve2();
	
	/**
	 * 设置平均评分(优先级)
	 * @param avgScore
	 */
	public void setAvgScore(Long avgScore);
	
	/**
	 * 平均评分(优先级)
	 * @return
	 */
	public Long getAvgScore();
	
	/**
	 * 设置余额
	 * @param balance
	 */
	public void setBalance(Double balance);
	
	/**
	 * 余额
	 * @return
	 */
	public Double getBalance();
	
	/**
	 * 类型(1.自由,2.在线)
	 * @return
	 */
	public Integer getSupplyType();
	
	/**
	 * 联系人
	 * @return
	 */
	public String getContactPeople();
	
	/**
	 * 地址
	 * @return
	 */
	public String getAddress();
	
	/**
	 * 手机
	 * @return
	 */
	public String getMobile();
	
	/**
	 * 可用:1.可用,0.不可用
	 * @return
	 */
	public Integer getIsUsed();
	
	/**
	 * 销售类型:1.空中充值,2.卡密
	 * @return
	 */
	public Integer getSellType();
	
	/**
	 * 对应反射类
	 * @return
	 */
	public String getClazzName();
	
	public void assign(Supply supply);
	
	/**
	 * 空中充值
	 */
	public int recharge(Mainorder mainorder,String mobile,SupplyInterface supplyInterface);
	
	/**
	 * 选择供货商时,检查可否买卡(库存)
	 * @param goods
	 * @return
	 */
	public boolean isLeftCard(Goods goods,int goodsNo);
	
	/**
	 * 购买卡密
	 */
	public List<CardLib> buyCard(Mainorder mainorder,Orderdetail orderdetail,SupplyInterface supplyInterface,Users user,int goodsNo);
	
	/**
	 * 冲正
	 */
	public int reverse(Mainorder mainorder);
	
	/**
	 * 查询状态
	 * @param mainorder
	 * @return
	 */
	public int refreshState(Mainorder mainorder) throws Exception;
	
	/**
	 * 取消订单
	 * @param mainorder
	 */
	public void cancelOrder(Mainorder mainorder); 
	
}

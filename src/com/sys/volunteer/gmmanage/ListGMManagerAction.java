package com.sys.volunteer.gmmanage;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.transaction.SystemException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ages.util.DbManager;
import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.RemotePageUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.json.JsonUtil;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.vo.BroadcastVo;
import com.sys.volunteer.vo.CalFirstChargeAvgVo;
import com.sys.volunteer.vo.CalLoginEverydayVo;
import com.sys.volunteer.vo.GMMail;
import com.sys.volunteer.vo.ItemLogVo;
import com.sys.volunteer.vo.UserInfo;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListGMManagerAction extends BaseListAction {

	@Resource
	GMManageService gmManageService;

	private Integer serverId;
	private List<UserInfo> userInfos;
	private Map<String, BroadcastVo> broadcastVos;
	private List<ItemLogVo> itemLogVos;
	private String rolename;
	private Integer itemTemplateId;
	private Date startDate;
	private Date endDate;
	private String graphURL;
	private String filename;
	private Integer type;
	private CalFirstChargeAvgVo calFirstChargeAvgVo;

	/**
	 * 玩家信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listUserInfo() throws Exception {
		Integer serverId = getSessionServer().getId();
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		QueryResult queryResult = gmManageService.findUserInfo(serverId,pageView.getFirstResult(),pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "listUserInfo";
	}

	/**
	 * 广播信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listBroadcastVo() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		broadcastVos = GMKernel.getInstance().getServerBroadcast()
				.get(serverId);
		List list = new ArrayList();
		Iterator iterator = broadcastVos.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			list.add(entry.getValue());
		}
		QueryResult queryResult = new QueryResult();
		queryResult.setResultlist(list);
		queryResult.setTotalrecord(broadcastVos.size());
		pageView.setQueryResult(queryResult);
		return "listBroadcastVo";
	}

	/**
	 * 道具使用列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listItemLogVo() throws Exception {
		Integer serverId = getSessionServer().getId();
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Long startT = null;
		Long endT = null;
		if (!SysUtil.isNull(startDate)) {
			startT = startDate.getTime();
		}
		if (!SysUtil.isNull(endDate)) {
			endT = endDate.getTime();
		}
		QueryResult queryResult = gmManageService.findItemLogVo(serverId, rolename,
				itemTemplateId,
				startT, endT,pageView.getFirstResult(),pageView.getMaxresult());
		List<ItemLogVo> resultlist = (List<ItemLogVo>)queryResult.getResultlist();
		for (ItemLogVo itemLogVo : resultlist) {
			itemLogVo.setTimeShow(new Date(itemLogVo.getTime()));
			itemLogVo.setRemarksShow();
		}
		pageView.setQueryResult(queryResult);
		return "listItemLogVo";
	}
	
	/**
	 * 在线玩家列表
	 * @return
	 * @throws Exception
	 */
	public String listOnlinePlayer() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		String url = DbManager.getRequestUrl(serverId)//1--servierId
				+"ajax/onlineplayer.php?userId=1";
		String result="";
		try {
			result = RemotePageUtil.getRotmePage(url, "utf-8");
		} catch (SystemException e) {
			return ShowMessage(MSG_TYPE_NORMAL, "获取失败,请检查是否已连接HTTP服务器", "", "", 0);
		}
		if (result == null || result.equals("")) {
			return ShowMessage(MSG_TYPE_NORMAL, "获取失败,请检查是否已连接HTTP服务器", "", "", 0);
		}
		String[] playerIds = JsonUtil.getStringArray4Json(result);
		QueryResult queryResult = gmManageService.findUserInfosByIds(serverId, playerIds, rolename, 
				pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "listOnlinePlayer";
	}
	
	/**
	 * 统计每天登陆玩家数
	 * @return
	 * @throws Exception
	 */
	public String calLoginByEveryday() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.calLoginByEveryday(serverId, 
				startDate, endDate, pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "calLoginByEveryday";
	}
	
	/**
	 * 统计每天登陆玩家数(柱状图)
	 * @return
	 * @throws Exception
	 */
	public String calLoginByEverydayBar() throws Exception {
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		List<CalLoginEverydayVo> list = gmManageService.calLoginByEveryday(serverId, 
				startDate, endDate);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (CalLoginEverydayVo calLoginEverydayVo : list) {
			dataset.addValue(calLoginEverydayVo.getNumber(), "日期", calLoginEverydayVo.getCountDate());
		}
		JFreeChart chart = ChartFactory.createBarChart3D("每日登陆数", "日期", "人数", 
				dataset, PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		//设置字体
		CategoryAxis domainAxis=plot.getDomainAxis();    
		//水平底部列表   
		domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));    
		//水平底部标题   
		domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));   
		//垂直标题    
		ValueAxis rangeAxis=plot.getRangeAxis();
		//获取柱状   
		rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));     
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		//标题
		chart.getTitle().setFont(new Font("黑体", Font.BOLD, 15));
		//设置网格背景颜色
		plot.setBackgroundPaint(Color.white);
		//设置网格竖线颜色
		plot.setDomainGridlinePaint(Color.pink);
		//设置网格横线颜色
		plot.setRangeGridlinePaint(Color.pink);
		//显示每个柱的数值，并修改该数值的字体属性
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		//默认的数字显示在柱子中，通过如下两句可调整数字的显示
		//注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);
		//设置每个地区所包含的平行柱的之间距离
		//renderer.setItemMargin(0.3);
		plot.setRenderer(renderer);
		//设置地区、销量的显示位置
		//将下方的“肉类”放到上方
		plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
		//将默认放在左边的“销量”放到右方
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		filename = ServletUtilities.saveChartAsPNG(chart, 700, 400, null, getHttpServletRequest().getSession());
		graphURL = getHttpServletRequest().getContextPath() + "/DisplayChart?filename=" + filename;
		return "jfc";
	}
	
	/**
	 * 统计每天活跃玩家数
	 * @return
	 * @throws Exception
	 */
	public String calLivenessPlayer() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findLivenessPlayerLocal(serverId, 
				startDate, endDate, pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "calLivenessPlayer";
	}
	
	/**
	 * 玩家充值列表
	 * @return
	 * @throws Exception
	 */
	public String listUserChargeVo() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findUserCharge(serverId, 
				startDate, endDate, pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "listUserChargeVo";
	}
	
	/**
	 * 玩家充值排行
	 * @return
	 * @throws Exception
	 */
	public String calUserChargeForPlayer() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		if (SysUtil.isNull(type)) {
			type = 1;
		}
		QueryResult queryResult = gmManageService.calUserChargeForPlayer(serverId, 
				startDate, endDate,type, pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "calUserChargeForPlayer";
	}
	
	/**
	 * 玩家消费列表
	 * @return
	 * @throws Exception
	 */
	public String listUserConsumeVo() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findUserConsume(serverId, 
				startDate, endDate, pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "listUserConsumeVo";
	}
	
	/**
	 * 统计玩家最高在线
	 * @return
	 * @throws Exception
	 */
	public String calOnlinePlayerCount() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		if (SysUtil.isNull(type)) {
			type = 1;
		}
		QueryResult queryResult = gmManageService.calOnlinePlayerCount(serverId, 
				startDate, endDate, type, pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "calOnlinePlayerCount";
	}
	
	/**
	 * 统计玩家流失率
	 * @return
	 * @throws Exception
	 */
	public String calPlayerChurnRate() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findChurnRateLocal(serverId, 
				startDate, endDate,  pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "calPlayerChurnRate";
	}
	
	/**
	 * 统计玩家次日留存率
	 * @return
	 * @throws Exception
	 */
	public String calPlayerStay() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findStayLocal(serverId, 
				startDate, endDate,  pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "calPlayerStay";
	}
	
	/**
	 * 统计玩家7日留存率
	 * @return
	 * @throws Exception
	 */
	public String calPlayerStay7Days() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findStay7daysLocal(serverId, 
				startDate, endDate,  pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "calPlayerStay7Days";
	}
	
	public String calRegister() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findRegisterEveryday(serverId, 
				startDate, endDate,  pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "calRegister";
	}
	
	/**
	 * 统计等级流失人数
	 * @return
	 * @throws Exception
	 */
	public String calPlayerChurnCountByLevel() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findChurnByLevelLocal(serverId, 
				startDate, endDate,  pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "calPlayerChurnCountByLevel";
	}
	
	/**
	 * 统计当前各等级人数
	 * @return
	 * @throws Exception
	 */
	public String listPlayerCountByLevel() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		QueryResult queryResult = gmManageService.countPlayerByLevel(serverId, 
				 pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "listPlayerCountByLevel";
	}
	
	/**
	 * 玩家上下线时间列表
	 * @return
	 * @throws Exception
	 */
	public String listLogintimeInfo() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findLogintimeInfo(serverId, 
				startDate, endDate,  pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "listLogintimeInfo";
	}
	
	/**
	 * 统计第一次平均充值
	 * @return
	 * @throws Exception
	 */
	public String calFirstChargeAvg() throws Exception {
		Integer serverId = getSessionServer().getId();
		calFirstChargeAvgVo = gmManageService.calFirstChargeAvg(serverId);
		return "calFirstChargeAvg";
	}
	
	/**
	 * 玩家升级日志列表
	 * @return
	 * @throws Exception
	 */
	public String listUserLevel() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findUserLevel(serverId, 
				startDate, endDate,  pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(queryResult);
		return "listUserLevel";
	}
	
	/**
	 * 游戏反馈
	 * @return
	 * @throws Exception
	 */
	public String listFeedback() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		Integer serverId = getSessionServer().getId();
		if (SysUtil.isNull(startDate)) {
			startDate = DateUtil.getMinDate(new Date());
		}else {
			startDate = DateUtil.getMinDate(startDate);
		}
		if (SysUtil.isNull(endDate)) {
			endDate = DateUtil.getMaxDate(new Date());
		}else {
			endDate = DateUtil.getMaxDate(endDate);
		}
		QueryResult queryResult = gmManageService.findGMMails(serverId, 
				startDate, endDate,  pageView.getFirstResult(), pageView.getMaxresult());
		List<GMMail> list = queryResult.getResultlist();
		for (GMMail gmMail : list) {
			if (gmMail.getContent().length()>20) {
				String content = gmMail.getContent().substring(0, 20)+"...";
				gmMail.setContent(content);
			}
		}
		pageView.setQueryResult(queryResult);
		return "listFeedback";
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public Map<String, BroadcastVo> getBroadcastVos() {
		return broadcastVos;
	}

	public void setBroadcastVos(Map<String, BroadcastVo> broadcastVos) {
		this.broadcastVos = broadcastVos;
	}

	public List<ItemLogVo> getItemLogVos() {
		return itemLogVos;
	}

	public void setItemLogVos(List<ItemLogVo> itemLogVos) {
		this.itemLogVos = itemLogVos;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getItemTemplateId() {
		return itemTemplateId;
	}

	public void setItemTemplateId(Integer itemTemplateId) {
		this.itemTemplateId = itemTemplateId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getGraphURL() {
		return graphURL;
	}

	public void setGraphURL(String graphURL) {
		this.graphURL = graphURL;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public CalFirstChargeAvgVo getCalFirstChargeAvgVo() {
		return calFirstChargeAvgVo;
	}

	public void setCalFirstChargeAvgVo(CalFirstChargeAvgVo calFirstChargeAvgVo) {
		this.calFirstChargeAvgVo = calFirstChargeAvgVo;
	}

}

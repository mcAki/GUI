package com.sys.volunteer.supply.usage;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;

import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.ZhongRongUtil;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Zhongrong;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.zhongrong.ZhongrongService;

public class ZhongrongSupply extends Supply implements ISupply {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	private ZhongrongService zhongrongService;
	private GoodsService goodsService;

	@Override
	public int recharge(Mainorder mainorder,String mobile,SupplyInterface supplyInterface) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		zhongrongService=(ZhongrongService)act.getBean("zhongrongServiceBean");
		goodsService=(GoodsService)act.getBean("goodsServiceBean");
		Zhongrong zhongrong = zhongrongService.findZhongrongByOrderId(mainorder.getMainOrderId());
		if (zhongrong != null) {
			if (zhongrong.getIsDeal()==0 && zhongrong.getIsGame()==1) {
				//已在orderAction生成request
			}else {
				logger.info("该订单已经进行了一次充值,请检查线程重复下单....");
				return Const.OperationLogResult.FAILED;
			}
		}else {
			Goods goods = goodsService.findById(mainorder.getGoods().getGoodsId());
			if (goods.getGoodsFlag()==13) {
				//Q币直充
				zhongrong = zhongrongService.initZhongrong(mainorder, mobile, supplyInterface, "2", 1, "100040");
			}else {
				//话费直充
				zhongrong = zhongrongService.initZhongrong(mainorder, mobile, supplyInterface,"1",0,"");
			}
		}
		
		logger.info("中融下了一条订单,订单号:"+zhongrong.getOrderid()+",手机号码是:"+zhongrong.getMobile()+",金额:"+zhongrong.getPrice());
		String url=ZhongRongUtil.orderCharge(zhongrong);
		if (url==null) {
			return Const.OperationLogResult.FAILED;
		}
		// 这里只能调用一次
		//String xmlText = LiandongUtil.getRotmePage(url, "utf-8");
		//System.out.println(xmlText);
		Zhongrong reZr = ZhongRongUtil.getZhongrong(url,1);
		if (reZr==null) {
			logger.error("中融充值接口返回了空对象!");
			return Const.OperationLogResult.FAILED;
		}
		//把远程xml转换为字符串保存
		//liandong.setXmlText(xmlText);
		zhongrong = zhongrongService.updateZhongrong(mainorder,reZr);
		logger.info("中融返回订单信息,流水号是:"+zhongrong.getSporderid());
		if (zhongrong.getResultno().equals("0")||zhongrong.getResultno().equals("2")) {
			return Const.OperationLogResult.PROCESSING;
		}else if (zhongrong.getResultno().equals("1")) {
			return Const.OperationLogResult.SUCCESS;
		}else {
			return Const.OperationLogResult.FAILED;
		}
		
		
	}

//	@Override
//	public int reverse(Mainorder mainorder) {
//		ApplicationContext act = SpringContextUtil.getApplicationContext();
//		liandongService=(LiandongService)act.getBean("liandongServiceBean");
//		areaCodeService = (AreaCodeService) act.getBean("areaCodeServiceBean");
//		orderService = (OrderService) act.getBean("orderServiceBean");
//		userService = (UserService) act.getBean("userServiceBean");
//		cardLibService = (CardLibService) act.getBean("cardLibServiceBean");
//		operationLogService = (OperationLogService) act.getBean("operationLogServiceBean");
//		supplyService = (SupplyService) act.getBean("supplyServiceBean");
//		useraccountService = (UseraccountService) act.getBean("useraccountServiceBean");
//		mainorderDao = (IMainorderDao) act.getBean("mainorderDao");
//		Liandong liandong = liandongService.findLiandongByOrderId(mainorder.getMainOrderId());
//		int result = -1;
//		//如果联动冲正时间大于订单当天最大时间,则需要联系客服
//		if (DateUtil.getMaxDate(mainorder.getCreateTime()).getTime()<System.currentTimeMillis()) {
//			return Const.reverseResult.CONTACT_US;
//		}
//		AreaCode areaCode = areaCodeService.findByMobile(mainorder.getMobile());
//		if (areaCode.getBusinessType()==1) {
//			switch (liandongService.reverseOrder(liandong)) {
//			case 1:
//				result = Const.reverseResult.SUCCESS;
//				orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_SUCCESS);
//				Users user = userService.findUserById(mainorder.getUsers().getUserId());
//				//冲正次数-1
//				user.setReversalCount(user.getReversalCount()-1);
//				userService.update(user);
//				//转换冲正的卡密状态
//				if (mainorder.getCardLibIds()!=null) {
//					cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
//				}
//				operationLogService.addOperationLog(mainorder.getUsers(), mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.SUCCESS);
//				//商户自动返还额度
//				useraccountService.refreshAccount(mainorder,user,new Useraccountdealdetail(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.REVERSAL);
//				//供货商自动返还额度
//				Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
//				SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new Useraccountdealdetail());
//				break;
//			case 2:
//				//没有找到订单,查询次数少于5次,继续刷,否则判断为失败
//				mainorder.setQueryTimes(mainorder.getQueryTimes()+1);
//				orderService.update(mainorder);
//				//刷新时间5秒后
//				mainorderDao.updateRefreshTime(System.currentTimeMillis()+5000,mainorder.getMainOrderId());
//				result = Const.reverseResult.PROCESSED;
//				break;
//			default:
//				result = Const.reverseResult.FAILED;
//				operationLogService.addOperationLog(mainorder.getUsers(), mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.FAILED);
//				orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_FAILED);
//				break;
//			}
//		}else {
//			result = Const.reverseResult.FAILED;
//		}
//		return result;
//	}

	@Override
	public int refreshState(Mainorder mainorder) throws Exception{
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		zhongrongService=(ZhongrongService)act.getBean("zhongrongServiceBean");
		return zhongrongService.updateDealingOrders(mainorder);
	}
	
	@Override
	public void cancelOrder(Mainorder mainorder) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		zhongrongService=(ZhongrongService)act.getBean("zhongrongServiceBean");
		DetachedCriteria dec = DetachedCriteria.forClass(Zhongrong.class);
		dec.add(Restrictions.eq("orderid", mainorder.getMainOrderId()));
		dec.add(Restrictions.eq("isDeal", 0));
		List<Zhongrong> list = zhongrongService.findByDetachedCriteria(dec);
		Zhongrong zhongrong = null;
		if (list.size()>0) {
			zhongrong = list.get(0);
		}
		if (zhongrong != null) {
			zhongrong.setIsDeal(1);
			zhongrongService.update(zhongrong);
		}
	}
}

package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.volunteer.authority.AuthorityService;
import com.sys.volunteer.balance.BalanceService;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.goods.GoodsTypeService;
import com.sys.volunteer.jdbc.dao.IEchoDao;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.liandong.LiandongService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.orderQuery.OrderQueryResponseNewService;
import com.sys.volunteer.pojo.Menutree;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.usercharge.UserChargeService;
import com.sys.volunteer.users.UserService;
import com.sys.volunteer.users.UsergroupService;
import com.sys.volunteer.xunyuan.XunYuanService;
import com.sys.volunteer.zhongrong.ZhongrongService;

public class TestDB {
	
	private static GoodsService goodsService;
	private static GoodsTypeService goodsTypeService;
	private static LiandongService liandongService;
	private static OrderService orderService;
	private static SupplyService supplyService;
	private static UsergroupService usergroupService;
	private static AuthorityService authorityService;
	private static BalanceService balanceService;
	private static IEchoDao echoDao;
	private static IMainorderDao mainorderDao;
	private static ZhongrongService zhongrongService;
	private static OrderQueryResponseNewService orderQueryResponseNewService;
	private static XunYuanService xunYuanService;
	private static UseraccountDealDetailService useraccountDealDetailService;
	private static UserService userService;
	private static UserChargeService userChargeService;
//	private static SpiUgService spiUgService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext act = new ClassPathXmlApplicationContext(
					new String[] { "applicationSpringContext-main.xml" });
//			goodsService = (GoodsService)act.getBean("goodsServiceBean");
//			goodsTypeService = (GoodsTypeService) act.getBean("goodsTypeServiceBean");
			liandongService = (LiandongService) act.getBean("liandongServiceBean");
//			orderService = (OrderService) act.getBean("orderServiceBean");
//			supplyService = (SupplyService) act.getBean("supplyServiceBean");
			usergroupService = (UsergroupService) act.getBean("usergroupServiceBean");
//			authorityService = (AuthorityService) act.getBean("authorityServiceBean");
//			balanceService = (BalanceService) act.getBean("balanceServiceBean");
//			echoDao = (IEchoDao) act.getBean("echoDao");
//			mainorderDao = (IMainorderDao) act.getBean("mainorderDao");
//			zhongrongService = (ZhongrongService) act.getBean("zhongrongServiceBean");
//			orderQueryResponseNewService = (OrderQueryResponseNewService) act.getBean("orderQueryResponseNewServiceBean");
//			xunYuanService = (XunYuanService) act.getBean("xunYuanServiceBean");
//			useraccountDealDetailService = (UseraccountDealDetailService) act.getBean("useraccountDealDetailServiceBean");
//			userService = (UserService) act.getBean("userServiceBean");
			userChargeService = (UserChargeService) act.getBean("userChargeServiceBean");
//			spiUgService = (SpiUgService) act.getBean("spiUgServiceBean");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	public static void info(Object obj)
	{
		System.out.println(obj);
	}
	
	@Test
	public void testCreateDB(){

	}
	
	@Test
	public void testSupplyKernel() throws Exception{
//		SupplyKernel supplyKernel=SupplyKernel.getInstance();
//		Goods goods=goodsService.findById(4);
//		ISupply supply = supplyKernel.getISupplyForCard(supplyKernel.getListBySellType(goods), goods, 1);
//		Supply supply=supplyService.findSupplyById(17);//supplyKernel.getISupply(supplyKernel.getListBySellType(Const.GoodsFlag.MOBILE_RECHARGE),1,30);
//		Set<Goods> goodses=supply.getGoodses();
//		Goods gds=goodsService.findById(1);
//		goodses.add(gds);
		
//		Liandong liandong = new Liandong();
//		liandongService.save(liandong);
//		Goods goods=goodsService.findById(1);
//		ISupply supply=supplyKernel.getISupply(supplies, 1, goods);
//		System.out.println(supply.getSupplyName());
//		DetachedCriteria dec=DetachedCriteria.forClass(Mainorder.class);
//		dec.add(Restrictions.lt("createTime", new Date()));
//		List<Mainorder> list=orderService.findByDetachedCriteria(dec);
//		for (Mainorder mainorder : list) {
//			System.out.println(mainorder.getCreateTime());
//		}
//		Liandong liandong = liandongService.findLiandongByOrderId("402880ea37f059ff0137f05b6bf30001");
//		System.out.println(liandong.getMobile());
//		Date startTime = DateUtil.getMinDate(DateUtil.formatDate("2012-05-01 12:12:12"));
//		Date endTime = DateUtil.getMaxDate(new Date());
//		List<MainorderVO> list = orderService.listMainorderVOs(startTime, endTime);
//		for (MainorderVO mainorderVO : list) {
//			System.out.println(mainorderVO.getSumValue());
//		}
//		double d = balanceService.addBalance("402882e72e6131aa012e6131f81d0001",0d,255);
//		System.out.println("s:"+d);
//		DetachedCriteria dec = DetachedCriteria.forClass(Balance.class);
//		dec.add(Restrictions.eq("userId", "402882e72e6341aa012e6131f81d0001"));
//		dec.add(Restrictions.eq("isDeal", 255));
//		dec.addOrder(Order.desc("createTime"));
//		List<Balance> list = balanceService.findByDetachedCriteria(dec);
//		if (!list.isEmpty()) {
//			System.out.println(list.get(0).getBalance());
//		}
//		echoDao.testQuery();
//		OrderVO orderVO = mainorderDao.findById("402880e439b99a1c0139b9a640c70003");
//		System.out.println(orderVO.getSupplyInterfaceId());
//		Liandong liandong = new Liandong();
//		liandongService.save(liandong);
//		OrderVO orderVO = mainorderDao.findById("402880833924db2f013924dbd89f0001");
//		System.out.println(orderVO);
//		System.out.println(System.currentTimeMillis());
//		List<OrderVO> orderVOs = mainorderDao.findApplyProcessOrder();
//		System.out.println(orderVOs);
//		Zhongrong zhongrong = new Zhongrong();//zhongrongService.findZhongrongByOrderId("402880ef3a6e5308013a6e8b4b55000b");
//		zhongrong.setSporderid(System.currentTimeMillis()+"");
//		zhongrongService.save(zhongrong);
//		DetachedCriteria dec = DetachedCriteria.forClass(Liandong.class);
//		dec.add(Restrictions.eq("orderID", "402882f1374952000137498a932c0001"));
//		dec.add(Restrictions.eq("isDeal", 0));
//		List<Liandong> list = liandongService.findByDetachedCriteria(dec);
//		System.out.println(list);
//		List<OrderQueryResponseNew> list = orderQueryResponseNewService.findOrderQueryResponseNewByOrderId("402880e439b99a1c0139b9a640c70003");
//		System.out.println(list);
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(new LoginPo());
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(new HeartBeatPo());
//		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanChargeReq.class);
//		dec.add(Restrictions.eq("orderId", "402880e53bb264ab013bb28027e40009"));
//		XunyuanChargeReq req = (XunyuanChargeReq) xunYuanService.findByDetachedCriteria(dec).get(0);
//		ChargePo chargePo = new ChargePo(req.getTradeType(),req.getAmount(),req.getMobile(),req.getStoreSeq());
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(chargePo);
//		XunyuanQueryReq req = xunYuanService.findQueryReqByOrderId("402880e53bb2d212013bb2d611cb0002",1);
//		System.out.println("1        "+req.getCreateTime());
//		req.setCreateTime(new Date());
//		xunYuanService.update(req);
//		System.out.println("2     "+req.getCreateTime());
//		double i = useraccountDealDetailService.sumBalance("402882e72e6131aa012e6131f81d0001");
//		System.out.println("11111111111111"+i);
//		List<Users> list = (List<Users>) userService.findAll(Users.class);
//		for (Users users : list) {
//			users.setReversalCount(users.getMaxReversalCount());
//		}
//		userService.saveOrUpdateAll(list);
//		Users users = new Users();
//		users.setUserName("yiyiyi");
//		for (int i = 0; i < 10; i++) {
//			userService.save(users);
//		}
//		UserCharge ud = new UserCharge();
//		ud.setVoucherType(6);
//		ud.setBalance(0d);
//		ud.setCurrentBalance(0d);
//		ud.setFlag(100);
//		ud.setDealMoney(0d);
//		for (int i = 0; i < 5; i++) {
//			userChargeService.save(ud);
//		}
//		String url = "http://219.153.41.158:8077/soft/searchpay.do?userid=10014360&sporderid=1370179502366";
//		Zhongrong zr = ZhongRongUtil.getZhongrong(url, 2);
//		if (zr == null) {
//			//logger.error("中融返回空");
//			//zhongrong.setIsDeal(1);
//			//zhongrong.setResultno("2");
////			orderService.updateOrderRespTime(mainorder);
//		}else {
//			if (!(zr.getResultno().equals("0") || zr.getResultno().equals("2"))) {
//				System.out.println(zr.getResultno());
//				//zhongrong.setIsDeal(1);
//				//zhongrong.setResultno(zr.getResultno());
//			}
//			//zhongrong.setQueryXmlText(zr.getQueryXmlText());
//		}
//		SupplyInterface_Usergroup supplyInterfaceUsergroup = new SupplyInterface_Usergroup();
//		SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyEnableId().get(60);
//		Usergroup usergroup = usergroupService.findUsergroupById(1);
//		supplyInterfaceUsergroup.setSupplyInterface(supplyInterface);
//		supplyInterfaceUsergroup.setUsergroup(usergroup);
//		supplyInterfaceUsergroup.setStockPrice(0.945d);
//		supplyInterfaceUsergroup.setRetailPrice(0.95d);
//		supplyInterfaceUsergroup.setValue(10d);
//		spiUgService.save(supplyInterfaceUsergroup);
//		SupplyInterface_Usergroup supplyInterfaceUsergroup2 = spiUgService.findByInterfaceIdAndUsergroupId(63, 1);
//		System.out.println("=========="+supplyInterfaceUsergroup2.getStockPrice());
		Usergroup usergroup = usergroupService.findUsergroupById(1);
		Set<Menutree> menutrees = usergroup.getMenutrees();
//		Set<Menutree> menutrees2 = new HashSet<Menutree>();
//		for (Menutree menutree : menutrees) {
//			Menutree menutree2 = (Menutree) usergroupService.findById(Menutree.class, menutree.getId());
//			menutrees2.add(menutree2);
//		}
//		Usergroup usergroup2 = usergroupService.findUsergroupById(8);
//		usergroup2.setMenutrees(menutrees2);
//		usergroupService.update(usergroup2);
		for (Menutree menutree : menutrees) {
			System.out.println(menutree.getName());
		}
	}
}

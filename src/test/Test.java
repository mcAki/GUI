package test;

import java.util.Calendar;

import com.sys.volunteer.common.KAUtil;
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.common.MD5;
import com.sys.volunteer.common.MD5Ex;
import com.sys.volunteer.common.SDKClientUtil;
import com.sys.volunteer.common.StringUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.xunyuan.util.GenPackHeader;

public class Test {

	private static GoodsService goodsService;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// ApplicationContext act = new ClassPathXmlApplicationContext(
		// new String[] { "applicationSpringContext-main.xml" });
		// goodsService = (GoodsService)act.getBean("goodsServiceBean");
		// Goods goods = goodsService.findById(46);
		// SupplyKernel supplyKernel = SupplyKernel.getInstance();
		// for (int i = 0; i < 100000; i++) {
		// ISupply supply =
		// supplyKernel.getISupply(supplyKernel.getListBySellType(goods), 1,
		// 30);
		// if (supply!=null) {
		// System.out.println("=====第"+i+"次======="+supply.getSupplyName());
		// }else {
		// System.out.println("=====第"+i+"次supply为空=======");
		// }
		// }
		// String key = "B0F910E98C606F17";
		// String key2de = DesUtil.desDecode(key);
		// System.out.println(System.currentTimeMillis());
		// String s = "";
		// s += System.currentTimeMillis();
		// String encoder = DesUtil.desEncode(DesUtil.toHexString(key2de), s);
		// System.out.println(encoder.length());

		// double i = 2521;
		// DecimalFormat df5 = new DecimalFormat("#.00");
		// System.out.println(df5.format(i/100));
		//		
		// SDKClientUtil.serialPwdUpd("290688", "192965");

		// System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		// SDKClientUtil.testGetBalance();
		// byte[] b = {'r','y','$','d'};
		// byte[] c = GenPackHeader.header.getBytes();
		// System.out.println(b.equals(c));
		// for (int i = 0; i < 65539; i++) {
		// String header = GenPackHeader.genHeader();
		// System.out.println("第"+i+"次包头:"+header);
		// }
		// MD5 md5 = MD5.getiInstance();
		// String str1 =
		// "cpid=6827&game_id=2030&autogame_id=10002001&create_time=20130717171548&version=2.10&cp_order_no=402880e63febb112013febea01070011&amount=46500&at_ele10=5&at_ele6=5&at_addition=充值QQ号:342641827;充值数量:5个;&at_num=5&at_ele1c=342641827&at_ele10t=5个Q币&at_ele1=342641827&at_par=1&ret_para=&client_ip=";
		// String str2 =
		// "cpid=6827&game_id=2030&autogame_id=10002001&create_time=20130717190309&version=2.10&cp_order_no=402880e63fec4bb1013fec4c49390003&amount=46500&at_ele10=5&at_ele6=5&at_addition=充值QQ号:342641827;充值数量:5个;&at_num=5&at_ele1c=342641827&at_ele10t=5个Q币&at_ele1=342641827&at_par=1&ret_para=&client_ip=";
		// String str3 =
		// "cpid=6827&game_id=2030&autogame_id=10002001&create_time=20130717191049&version=2.10&cp_order_no=402880e63fec51af013fec534bd80006&amount=46500&at_ele10=5&at_ele6=5&at_addition=充值QQ号:342641827;充值数量:5个;&at_num=5&at_ele1c=342641827&at_ele10t=5个Q币&at_ele1=342641827&at_par=1&ret_para=&client_ip=";
		// String str4 =
		// "cpid=6827&game_id=2030&autogame_id=10002001&create_time=20130718171006&version=2.10&cp_order_no=402880e63ff10a8d013ff10b24a00002&amount=46500&at_ele10=5&at_ele6=5&at_addition=充值QQ号:342641827;充值数量:5个;&at_num=5&at_ele1c=342641827&at_ele10t=5个Q币&at_ele1=342641827&at_par=1&ret_para=&client_ip=";
		// String sign =
		// md5.getMD5ofStr(str1+KAUtil.TESTMD5KEY,"gb2312").toLowerCase();
		// System.out.println("sign="+sign);
		// String sign2 =
		// md5.getMD5ofStr(str2+KAUtil.TESTMD5KEY,"gb2312").toLowerCase();
		// System.out.println("sign2="+sign2);
		// String sign3 =
		// md5.getMD5ofStr(str3+KAUtil.TESTMD5KEY,"gb2312").toLowerCase();
		// System.out.println("sign3="+sign3);
		// //str4 = StringUtil.changeCharset(str4, "gb2312");
		// System.out.println("str4="+str4);
		// String sign4 =
		// md5.getMD5ofStr(str4+KAUtil.TESTMD5KEY,"gb2312").toLowerCase();
		// System.out.println("sign4="+sign4);
		// sign4 = MD5Ex.getMD5Str(str4+KAUtil.TESTMD5KEY,"gb2312");
		// System.out.println("sign4="+sign4);
		// Double d = 5.000;
		// System.out.println(d.toString());
		// String i = StringUtil.subZeroAndDot(d+"");
		// System.out.println(i);
//		String param = "Action=" + LiandongUtil.ACTION_QUERY_ORDER + "&UserName=" + LiandongUtil.userName
//				+ "&OrderID=" + "37bf9fa34095ecb4014095fdb5330025";
		//MD5 md5 = MD5.getiInstance();
//		String Key = MD5Ex.getMD5Str(param + LiandongUtil.sign,"utf-8");
//		String Key2 = MD5Ex.getMD5Str(param + LiandongUtil.sign,"gbk");
//		System.out.println(Key);
//		System.out.println(Key2);
//		param = param + "&Key=" + Key.toLowerCase();
//		String url = LiandongUtil.LINK_URL + "?" + param;
//		System.out.println(url);
//		String key = "869590602177";
//		String md5Ex = MD5Ex.getMD5Str(key);
//		System.out.println(md5Ex);
//		double d = 93.895d;
//		System.out.println(SysUtil.formatDouble(d*1000, "000000000"));
//		SDKClientUtil.testRegistDetailInfo();
		String str = "0002342304560";
		System.out.println(str.replaceFirst("^0*", ""));
	}

}

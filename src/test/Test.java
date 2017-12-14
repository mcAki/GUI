package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.util.List2d;

import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.vo.LivenessVo;

public class Test {

	//private static GoodsService goodsService;

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
//		MD5 md5 = MD5.getiInstance();
//		String Key = md5.getMD5ofStr(param + LiandongUtil.sign);
//		param = param + "&Key=" + Key.toLowerCase();
//		String url = LiandongUtil.LINK_URL + "?" + param;
//		System.out.println(url);
//		String recordKey = "8000023IP8000023201401101603197646531372b74370eebc01437b2d26cf0124Pf3cb38955e4efc2ab61b9815381b881a";
//		recordKey = MD5Ex.getMD5Str(recordKey, "GB2312").substring(0, 16).toUpperCase();
//		System.out.println(recordKey);
//		String d1 = "2010-01-01 06:54:33";
//		String d2 = "2010-01-02 05:45:18";
//		Date date1 = DateUtil.formatDate(d1,DateUtil.CM_LONG_DATE_FORMAT);
//		Date date2 = DateUtil.formatDate(d2,DateUtil.CM_LONG_DATE_FORMAT);
//		long l = DateUtil.subtractTime(date2, date1, 1);
//		System.out.println(l);
//		String a="tgw_l7_forward\r\nHost:app12345.qzoneapp.com:80\r\n\r\n";
//		byte[] arr=a.getBytes();
//		System.out.println();
//
//		String a1="tgw_l7_forward\\r\\nHost:app12345.qzoneapp.com:80\\r\\n\\r\\n";
//		a1 = a1.replaceAll("\\\\r", "\r").replaceAll("\\\\n", "\n");
//		byte[] arr1=a1.getBytes();
//		System.out.println();
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		list.add("11");
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (i%5==0) {
				list2.removeAll(list2);
			}
			list2.add(list.get(i));
		}
		System.out.println(list2);
	}

}

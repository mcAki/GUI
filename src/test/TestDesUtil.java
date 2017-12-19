package test;

import java.net.URLDecoder;

import sun.misc.BASE64Encoder;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sys.volunteer.common.ByteUtil;
import com.sys.volunteer.common.DesUtil;
import com.sys.volunteer.common.GenRamdomUtil;
import com.sys.volunteer.common.KAUtil;
import com.sys.volunteer.common.MD5;

public class TestDesUtil {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String key = "1234567890123456";
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < hexStr2ByteArr(key).length && i < arrB.length; i++) {
			arrB[i] = hexStr2ByteArr(key)[i];
		}
//		value = 123456 encoder = [64][6b][66][86][04][ff][13][e3]
		//String value = "65981742";
		//String value = "1111111";
		//String value = "12345678";
//		String value = "22222222";
		//byte[] encoder = DesUtil.endes(arrB, value.getBytes());
		//System.out.println(byteArr2HexStr(encoder));
		
		//byte[] arr = hexStr2ByteArr(byteArr2HexStr(encoder));
		//byte[] decoder = DesUtil.dedes(arrB, arr);
		//System.out.println(new String(decoder));
		//System.out.println(byteArr2HexStr(decoder));
		// String strMi = "";
		// BASE64Encoder base64en = new BASE64Encoder();
		// try {
		// strMi = base64en.encode(encoder);
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// base64en = null;
		// }
		// System.out.println(strMi);
//		String value = GenRamdomUtil.genRandomNum(8);
		//String encode = DesUtil.desEncode("43865089");
		//String msg = "b061c7623e3155d7c0c3578cf692f8b8a7cff8309f3bd7631494ced6569b5916eba8250a49050a3a7fe16db96a9c513f13116e2c933b9275517c6018fa0e19e6f1efe9312ff5ea7c98126041949eedb6b039d77f9c152d69779e04111ab39fee22a1d2ee5dce34ddf3c328a83cebe56bb57edf27bfdd3e12";
		
//		String value2 = GenRamdomUtil.genRandomNum(8);
//		String en3code = DesUtil.des3Encode(value2);
//		String de3code = DesUtil.des3Decode(en3code);
		
		String key2 = "A6FED88D163D60F0";
		String key2de = DesUtil.desDecode(key2);
//		String hex = DesUtil.toHexString(key2de);
//		System.out.println(hex);
//		String b = "G001|01|00|13917022174|10086|000000003000||999440148120000|03011789|000001|000001|144845879735||||||";
//		String encode = DesUtil.desEncode(DesUtil.toHexString(key2de),b).toUpperCase().substring(0,8);
//		b += encode;
//		String msg = DesUtil.desEncode(DesUtil.toHexString(key2de), b);
//		//c4d7d720c8248afee4d2e9f6637aedfd5fee017a5bcdb2dcda29d506b65e57d2106a34e7f4a0b68c67e120e1c3aec5e67bc0e22b2039de8539fb610bb20955996ba375db836cfb4b2512acce4cf3031243defee1f85789d3e14a9e29ec0738106dd7e6a39c83788308be733d82b927f4ee25e09d9aba17bb
//		String c = DesUtil.desDecode(DesUtil.toHexString(key2de), msg);
//		System.out.println(DesUtil.toStringHex("4341353200000000"));
		String msg2 = "04f1fe5235a500386c8b939ffe87b4c52246e444156589e5a153605403f0e85f612384f532ad7ad3fe843651f6e03e127131576df2b18a72e41cb79581ff98b406ffb8e65bdbf5b8107c9362c25a487a4f7662b4da51397a4765f62f418b21ad9d39c1f897704bf784830deb7296b399e14517d455218f5ce23a81de3725aa47";
		//String decode = DesUtil.desDecode(DesUtil.toHexString(key2de), msg2);
		String msg3 = "G001|01|00|13818076559|                    |     |000000010000||999440148120000|21023399|000002|000012|140341873634||||120823001054|94D12421";
		//String msgMain = msg3.substring(0, msg3.lastIndexOf("|")+1);
		String encode = DesUtil.desEncode(DesUtil.toHexString(key2de), msg3);
		//System.out.println(msgMain);
		//String mac = DesUtil.calcMac(DesUtil.toHexString(key2de), msgMain);
		//System.out.println(mac);
		MD5 md5 = MD5.getiInstance();
		String str = "cpid=6827&game_id=2030&autogame_id=10002001&create_time=20130717190309&version=2.10&cp_order_no=402880e63fec4bb1013fec4c49390003&amount=46500&at_ele10=5&at_ele6=5&at_addition=充值QQ号:342641827;充值数量:5个;&at_num=5&at_ele1c=342641827&at_ele10t=5个Q币&at_ele1=342641827&at_par=1&ret_para=&client_ip=";
		String sign = md5.getMD5ofStr(str+KAUtil.TESTMD5KEY).toLowerCase();
		System.out.println("sign="+sign);
		System.out.println(md5.getMD5ofStr("第三方斯蒂芬"));
		
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

}

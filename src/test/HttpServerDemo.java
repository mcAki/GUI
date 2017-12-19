package test;

import java.io.IOException;
import java.util.Date;

import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.DesUtil;
import com.sys.volunteer.http.HttpServer;

public class HttpServerDemo {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		String s = "0000004c00000100";
//		Integer i = Integer.valueOf(s);
//		System.out.println(i);
//		String date = DateUtil.DateToString(new Date(), "yyyyMMddHHmmss");
//		System.out.println(date);
		String key = DesUtil.desDecode("C20035578F4E0E14");
					//G001|01|00|13917022174|10086|000000003000||999440148120000|03011789|000001|000001|144845879735||||||
		String msg = "G001|01|00|13917022174|10086|000000003000||999440148120000|03011789|000001|000001|144845879735||||||F573723E";
		String msgMain = msg.substring(0, msg.lastIndexOf("|")+1);
		String msgmac = msg.substring(msg.lastIndexOf("|")+1,msg.length());
		System.out.println(msgMain);
		System.out.println(msgmac);
		String desmac = DesUtil.desEncode(key, msgMain);
		String des = DesUtil.desDecode(key, desmac);
		System.out.println(desmac.toUpperCase().substring(0,8));
	}

}

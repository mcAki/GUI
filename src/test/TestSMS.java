package test;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jasson.im.api.APIClient;
import com.jasson.im.api.MOItem;
import com.jasson.im.api.RPTItem;

public class TestSMS {
	private static String cmd = "11";
	//private static String mobileStr = "13728066550";
	//private static String mobileStr = "13824468956";13728066550
	private static String mobileStr = "15915823215,15920280949";
	//private static String mobileStr = "13632457726";
	//private static String mobileStr = "13710234090";
	private static String content = "   我QQ被盗啦，如果我QQ上讲都唔好相信啊！！如果比人厄唔关我事哦，我同你讲左拉";
	private static long smId = 12345678;
	private static int smType = 0;
	private static String url = "wap.sohu.com";
	private static String host = "210.72.2.213";
	private static String dbName = "mas";
	private static String apiId = "006";
	private static String name = "zys";
	private static String pwd = "tsw@0823";
	private static APIClient handler = new APIClient();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			info(new Date().getTime());
			int connectRe = handler.init(host, name, pwd, apiId,dbName);
	        if(connectRe == APIClient.IMAPI_SUCC)
	        	info("初始化成功");
	        else if(connectRe == APIClient.IMAPI_CONN_ERR)
	        	info("连接失败");
	        else if(connectRe == APIClient.IMAPI_API_ERR)
	        	info("apiID不存在");
	        if(connectRe != APIClient.IMAPI_SUCC)
	        {
	        	System.exit(-1);
	        }
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	public static void info(Object obj)
	{
		System.out.println(obj);
	}
	
	@Test
	public void testSendSMS(){
		int result = handler.sendSM(mobileStr, content, smId);
		if(result == APIClient.IMAPI_SUCC)
        {            
            info("发送成功\n");
        }
        else if(result == APIClient.IMAPI_INIT_ERR)
            info("未初始化");
        else if(result == APIClient.IMAPI_CONN_ERR)
            info("数据库连接失败");
        else if(result == APIClient.IMAPI_DATA_ERR)
            info("参数错误");
        else if(result == APIClient.IMAPI_DATA_TOOLONG)
            info("消息内容太长");
        else if(result == APIClient.IMAPI_INS_ERR)
            info("数据库插入错误");
        else
            info("出现其他错误");
	}
	
	@Test
	public void recvRPT()
	{
		RPTItem[] rpts = handler.receiveRPT();
        int len = 0, i = 0;
        StringBuffer sb = new StringBuffer("");
        if(rpts == null)
        {
            info("未初始化或接收失败");
            return;
        }
        else if(rpts.length == 0)
        {
            info("没有回执");
        }
        else
        {
            len = rpts.length;
            while(i < len)
            {
                sb.append("手机: ");
                sb.append(rpts[i].getMobile() + " ");
                sb.append("回执编码: ");
                sb.append(rpts[i].getCode() + " ");
                sb.append("回执描述: ");
                sb.append(rpts[i].getDesc() + " ");
                sb.append("回执时间: ");
                sb.append(rpts[i].getRptTime() + " ");
                sb.append("\n");
                i++;
            }
            info(sb.toString());
        }
	}
	@Test
	public void recvSM()
	{
		MOItem[] mos = handler.receiveSM();
        int len = 0, i = 0;
        StringBuffer sb = new StringBuffer("");
        if(mos == null)
        {
            info("未初始化或接收失败");
            return;
        }
        else if(mos.length == 0)
        {
            info("没有MO短信");
        }
        else
        {
            len = mos.length;
            while(i < len)
            {
                sb.append("手机号码: ");
                sb.append(mos[i].getMobile() + " ");
                sb.append("短信内容: ");
                sb.append(mos[i].getContent());
                sb.append("smid: ");
                sb.append(mos[i].getSmID()+" ");
                sb.append("MO时间: ");
                sb.append(mos[i].getMoTime());
                sb.append("\n");
                i++;
            }
             
            info(sb.toString() );
        }
	}
}

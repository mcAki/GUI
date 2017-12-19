package com.sys.volunteer.common;

import cn.emay.sdk.client.api.Client;

public class SDKClientUtil {

	public static String softwareSerialNo = "3SDK-EMY-0130-OBRST";// 软件序列号,请通过亿美销售人员获取
	public static String key = "192965";// 序列号首次激活时自己设定
	public static String password = "537473";// 密码,请通过亿美销售人员获取

	public static void init() throws Exception {

	}

	/**
	 * <pre>
	 * 发送短信、可以发送定时和即时短信
	 * sendSMS(String[] mobiles,String smsContent, String addSerial, int smsPriority)
	 * 1、mobiles 手机数组长度不能超过1000
	 * 2、smsContent 最多500个汉字或1000个纯英文、请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内 
	 * 3、addSerial 附加码(长度小于15的字符串) 用户可通过附加码自定义短信类别,或添加自定义主叫号码( 联系亿美索取主叫号码列表)
	 * 4、smsPriority 优先级范围1~5，数值越高优先级越高(相对于同一序列号)
	 * 5、其它短信发送请参考使用手册自己尝试使用
	 * </pre>
	 */
	public static void SendSMS(String[] mobiles, String smsContent,
			String addSerial, int smsPriority) {
		try {
			// int i=SDKClient.getClient().sendSMS(new String[] {"13436506141"},
			// "同步内容","110",3);//带扩展码
			// System.out.println("testSendSMS====="+i);
			// 注册先再发信息
			int i = SDKClient.getClient().registEx(password);
			System.out.println("testTegistEx:" + i);
			Client sdkclient = new Client(softwareSerialNo, key);
			int j = sdkclient.sendSMSEx(mobiles, smsContent,
					addSerial, smsPriority);
			System.out.println("testSendSMS=====" + j);
			double a = sdkclient.getBalance();
			System.out.println("获得序列号的剩余金额:" + a);

			/**
			 * J返回值的情况
			 * 
			 * 0:短信发送成功 17:发送信息失败 101:客户端网络故障 305:服务器端返回错误，错误的返回值（返回值不是数字字符串）
			 * 307:目标电话号码不符合规则，电话号码必须是以0、1开头 997:平台返回找不到超时的短信，该信息是否成功无法确定
			 * 998:由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定
			 * 
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int SendSMS_(String[] mobiles, String smsContent,
			String addSerial, int smsPriority) {
		int j = 0;
		try {
			// int i=SDKClient.getClient().sendSMS(new String[] {"13436506141"},
			// "同步内容","110",3);//带扩展码
			// System.out.println("testSendSMS====="+i);
			// 注册先再发信息
			int i = SDKClient.getClient().registEx(password);
			System.out.println("testTegistEx:" + i);
			Client sdkclient = new Client(softwareSerialNo, key);
			j = sdkclient.sendSMSEx(mobiles, smsContent, addSerial,
					smsPriority);
			System.out.println("testSendSMS=====" + j);
			double a = sdkclient.getBalance();
			System.out.println("获得序列号的剩余金额:" + a);

			/**
			 * J返回值的情况
			 * 
			 * 0:短信发送成功 17:发送信息失败 101:客户端网络故障 305:服务器端返回错误，错误的返回值（返回值不是数字字符串）
			 * 307:目标电话号码不符合规则，电话号码必须是以0、1开头 997:平台返回找不到超时的短信，该信息是否成功无法确定
			 * 998:由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定
			 * 
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 亿美短信平台密码修改
	 */
	public static void serialPwdUpd(String origPwd, String newPwd) {
		try {
			Client sdkclient = new Client("3SDK-EMY-0130-OBRST", origPwd);
			int a = sdkclient.serialPwdUpd(origPwd, newPwd);
			System.out.println("短信发送结果:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询月额
	 */
	public static void testGetBalance() {
		try {
			Client sdkclient = new Client("3SDK-EMY-0130-OBRST", password);
			double a = sdkclient.getBalance();
			System.out.println("余额:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注销
	 */
	public static void logout() {
		try {
			Client sdkclient = new Client("3SDK-EMY-0130-OBRST", password);
			int a = sdkclient.logout();
			System.out.println("注销结果:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改企业信息
	 */
	public static void testRegistDetailInfo() {
		try {
			Client sdkclient = new Client("3SDK-EMY-0130-OBRST", password);
			int a = sdkclient.registDetailInfo("广州市妙鸣贸易有限公司", "邱彩霞", "02083279165", "13632290688", "1318037665@qq.com",
					"02083279165", "广州市越秀区惠福东路455号1205", "510000");
			System.out.println("短信发送结果:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

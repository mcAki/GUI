package com.mprs.protocol.GenerateProtocolPackage;

import java.util.Map;

import com.mprs.protocol.ConfigParser;
import com.mprs.protocol.ProtocolContainer;

public class GenerateAll {

	public static String GenerateDir;

	private static final String ConfigFileName = "protocol_main.xml";

	private static final String JavaPackageName = "com.mprs.netty.vo";

	// private static final String FlashPackageName = "com.ages.netty.vo";

	/**
	 * 入口，生成VO的
	 * 
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		// 获取当前执行java目录路径
		GenerateDir = System.getProperty("user.dir");
		String JavaDir = GenerateDir + "\\src\\";
		JavaDir += JavaPackageName.replace(".", "\\") + "\\";
		System.out.println(JavaDir);
		IGenerateCode genJava = null;
		IGenerateCode genAS3 = null;

		ProtocolSetting ps = ProtocolSetting.loadFromXml(ConfigFileName);

		genJava = new GenerateCode4JAVA();
		genJava.GenerateCode(ConfigFileName, "", 0, ps, "C:\\vo\\java");

		genAS3 = new GenerateCode4ActionScript3();
		genAS3.GenerateCode(ConfigFileName, "", 0, ps, "C:\\vo\\as3");

		// 加载协议解析的xml配置
		Map<Integer, ProtocolContainer> parseMap = ConfigParser.readFromProtocolConfig(ConfigFileName,
			"", ps, 0);
		((IGenerateConstTable) genAS3).GenerateConstTable(parseMap, ps, "C:\\vo\\as3");

	}

}

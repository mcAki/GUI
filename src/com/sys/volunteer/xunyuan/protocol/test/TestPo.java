package com.sys.volunteer.xunyuan.protocol.test;

import com.sys.volunteer.common.MD5;

public class TestPo {

	String length = "0076";
	String serialId = "0000000001";
	String businessType = "    ";
	String msgCode = "000001";
	String spId = "00000000";
	String comId = "02000500";
	MD5 md5 = MD5.getiInstance();
	String pwd = md5.getMD5ofStr("123");
	String protocolVersion = "0010";
}

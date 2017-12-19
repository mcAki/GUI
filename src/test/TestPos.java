package test;

import com.sys.volunteer.common.DesUtil;

public class TestPos {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		rechargeTest();

	}

	public static void rechargeTest() throws Exception {
		//http://localhost:8080/MPRS/http/pos!recharge.do?msg=ba4d8df0d7de2429b241254ae617bf987e554ad0731ca6b77c6c5ad3481ddf5cc17ee8ea035b5c0a414613324a7af131f69b80f3e1e389a633b505efb7412df7e7d6455e7ef22f69d87a5fb37efd53c7a4d3088d2fc33cb1adf939135e14ce0f8cd633e0acb25bbc7c6c5ad3481ddf5cc17ee8ea035b5c0acd75d897dfae09ad3e10d9959fc2f58ab11d090ed781ed25c9264279d9cfad64
		String key2 = "73A90BC2FA07B983";
		String key2de = DesUtil.desDecode(key2);
		String rechargeMsg = "G001|02|00|13802412056|828004bb48ec8dee303c|     |000000003000||105440148149917|47218460|000002|000031|828004bb48ec8dee303c||||130510000563|FC7D3C6B";
		String encode = DesUtil.desEncode(DesUtil.toHexString(key2de), rechargeMsg);
	}
}

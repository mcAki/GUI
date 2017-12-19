package test;


public class TestJsonUtil {


	/*public void testJsonStrToJsonArray(){
		String jsonStr="[{\"adminComment\":\"234\",\"administrator\":\"guoxi\",\"id\":4,\"lastLoginIp\":\"127.0.0.1\",\"loginCounts\":0,\"loginIp\":\"127.0.0.1\",\"passcode\":\"54\",\"realName\":\"guoxi\",\"status\":0},{\"adminComment\":\"234\",\"administrator\":\"guoxi\",\"id\":4,\"lastLoginIp\":\"127.0.0.1\",\"loginCounts\":0,\"loginIp\":\"127.0.0.1\",\"passcode\":\"54\",\"realName\":\"guoxi\",\"status\":0}]";
		List<Administrators> list=JsonUtil.getList4Json(jsonStr,Administrators.class);
		for(Administrators admin:list){
			System.out.println(admin.getAdministrator()+"==="+admin.getLastLoginIp());
			
		}
	}
	
	@Test
	public void testJsonMulStrToJsonObj(){
		String jsonStr="{\"test1\":\"test1\",\"test2\":\"test2\",\"admin\":{\"adminComment\":\"234\",\"administrator\":\"guoxi\",\"id\":4,\"lastLoginIp\":\"127.0.0.1\",\"loginCounts\":0,\"loginIp\":\"127.0.0.1\",\"passcode\":\"54\",\"realName\":\"guoxi\",\"status\":0},\"adminList\":[{\"adminComment\":\"234\",\"administrator\":\"guoxi\",\"id\":4,\"lastLoginIp\":\"127.0.0.1\",\"loginCounts\":0,\"loginIp\":\"127.0.0.1\",\"passcode\":\"54\",\"realName\":\"guoxi\",\"status\":0},{\"adminComment\":\"234\",\"administrator\":\"guoxi\",\"id\":4,\"lastLoginIp\":\"127.0.0.1\",\"loginCounts\":0,\"loginIp\":\"127.0.0.1\",\"passcode\":\"54\",\"realName\":\"guoxi\",\"status\":0}]}";
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		System.out.println(jsonObject.getString("test1"));
		System.out.println(jsonObject.getString("test2"));
		
		String jsonObjStr=jsonObject.getString("admin");
		Administrators admin1=(Administrators)JsonUtil.getObject4JsonString(jsonObjStr, Administrators.class);
		System.out.println(admin1.getAdministrator()+"==="+admin1.getLastLoginIp());
		
		String jsonListStr=jsonObject.getString("adminList");
		List<Administrators> list=JsonUtil.getList4Json(jsonListStr,Administrators.class);
		for(Administrators admin:list){
			System.out.println(admin.getAdministrator()+"==="+admin.getLastLoginIp());
			
		}

	}*/
}

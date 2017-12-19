package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sys.volunteer.pojo.Area;
import com.sys.volunteer.pojo.AreaCode;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Users;

public class SaveAreaCode {

	
	private  final String URL="jdbc:mysql://localhost:3306/mprs?characterEncoding=UTF-8";
	private  final String DRIVER="com.mysql.jdbc.Driver";
	private  final String USER="root";
	private  final String PASSWORD="root";
	
	private static Connection conn = null;
	
	public SaveAreaCode(){
		getCon();
	}
	
	public  Connection getCon(){
		try {
			Class.forName(DRIVER);
			if(conn ==null) conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public int noSelect(String sql){
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSet select(String sql){
		ResultSet rs = null;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void main(String[] args) throws Exception{
//		Mainorder mo = new Mainorder();
//        Users user = new Users();
//        user.setUserId("402882e72e6131aa012e6131f81d0001");
//		user.setUserName("hawen");
//		mo.setMainOrderId("37bf9fa337cb3e220137ceafafbb0049");
//		mo.setUsers(user);
//	    mo.setTotalMoney(11211111111.4);
//	    mo.setCreateTime(new Date());
//	    mo.setOrderType(1);
	    SaveAreaCode sac = new SaveAreaCode();
	    for(int i = 0;i<=100000;i++){
	    	String sql= "INSERT INTO mainorder(mainOrderId,user_id,totalMoney,createTime,order_type) VALUES ('wergwerh45y345yhethe5twqo"+i+"','402882e72e6131aa012e6131f81d0001',456743.4,'"+
	    	       new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"',1)";
	    	int j = sac.noSelect(sql);
	    	System.out.println("----------------------------------------------->"+j);
	    }
	}
	
	public void saveArea() throws Exception{
		AreaCode ac = new AreaCode();
		   SaveAreaCode sac = new SaveAreaCode();
			File file = new File("d:\\20121229_141732.txt");
			BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
			String temp = null;
			String imsi[] = null;
			while ((temp = read.readLine()) != null){
				imsi = temp.split("\\,");
				System.out.println(imsi[0]+"   "+imsi[1]+"   "+imsi[2]+"   "+imsi[3]+"   "+imsi[4]+"   ");
				ac.setAreaCode(imsi[4]);
				ac.setProvince(imsi[1]);
				ac.setCity(imsi[2]);
				ac.setMobileNum(imsi[0]);
				String type_temp = imsi[3].substring(imsi[1].length(), imsi[1].length()+2);
				
				if(type_temp.equals("联通")){
					ac.setBusinessType(3);
					ac.setBusiness("联通GSM");
				}
				if(type_temp.equals("移动")){
					ac.setBusinessType(1);
					ac.setBusiness("移动GSM");
				}
				if(type_temp.equals("电信")){
					ac.setBusinessType(2);
					ac.setBusiness("电信CDMA2000");
				}
				
				ac.setProvinceCode(sac.findbyP(imsi[1]).get(0).getProvinceCode());
				int guangd = 0;
				if(imsi[1].equals("广东")){
					guangd=1;
				}else{
					guangd=0;
				}
				ac.setIsGuangdong(guangd);
	            String sql = "insert into area_code(mobile_num,business,area_code,city,province,business_type,province_code,is_guangdong) " +
	            		"values('"+ac.getMobileNum()+"','"+ac.getBusiness()+"','"+ac.getAreaCode()+"','"+ac.getCity()+"','"+ac.getProvince()+"',"+ac.getBusinessType()
	            		  +","+ac.getProvinceCode()+","+ac.getIsGuangdong()+")";
				sac.noSelect(sql);
				
			}
	}
	public  List<Area> findbyP(String p) {
		
		List<Area> list = new ArrayList<Area>();
		ResultSet rs = this.select("select * from area where province = '"+p+"'");
		try {
			while(rs.next()){
				Area a = new Area();
				a.setProvince(rs.getString("province"));
				a.setProvinceCode(rs.getInt("province_code"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

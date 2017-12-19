package com.sys.volunteer.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.pojo.Mainorder;
@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
@SuppressWarnings("unchecked")
public class ExportHssfPoiExcel extends BaseListAction{

	private String orderListUser[] = {"充值号码","充值日期","号码余额","商品名称","订单状态","冲正状态","消费金额","流水号"};
	
	private String orderListSupply[] ={"号码 ","销售日期","商品名称","供货商","订单状态","冲正状态","冲正订单日期","交易总金额","进货价","销售价","交易佣金","号码余额","订单流水号","经销商","终端机号","面额","购买数量","是否终端操作","订单类型","人工补充","补充卡号","补充人"};
	
	private String useraccountdealdetailUserU[] = {"号码 ","日期", "交易金额","交易佣金", "当前余额","当前佣金余额","记录类型","凭证类型","凭证号"};

	private String useraccountdealdetailUserS[] = {"订单流水号","商户","对应商户","号码","日期","交易金额","交易佣金","当前余额","当前佣金余额","记录类型","凭证类型","凭证号","操作员"};

	private String useraccountdealdetailSupply[] = {"订单流水号","充值日期","经销商","供货商","号码","记录类型","凭证类型","凭证号","交易金额","当前余额","操作员"};
	
	private HSSFWorkbook wb = new HSSFWorkbook();    //建立新HSSFWorkbook对象
	
	private Integer count_sheet = 0; //递增 用作sheet名字
	
	private double cellCount = 5000; //每个sheet 多少条数据
	
	private int tempOrderList = 1;//标记列出了多少条；
	
	private FileOutputStream out ;
	
	List<Mainorder> listMainOrder;

	private int flagArray ; 
	
	
	public void exportExcel(){
		System.out.println("****************************");
		try {
			out = new FileOutputStream("d:\\RangoTestExcel"+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+".xls");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		switch(flagArray){
		case 0:
			createExcel(orderListUser);
			break;
		case 1:
			createExcel(orderListSupply);
			break;
		case 2:
			createExcel(useraccountdealdetailUserU);
			break;
		case 3:
			createExcel(useraccountdealdetailUserS);
			break;
		case 4:
			createExcel(useraccountdealdetailSupply);
			break;
		}
	}
	
	public void createExcel_001Temp(String str[],List<Mainorder> list){
		tempOrderList = 1;
		count_sheet++;
		HSSFSheet sheet = wb.createSheet("sheet"+count_sheet);  //建立新的sheet对象
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
//		List<Mainorder> list = (List<Mainorder>)this.getSession().get("listMainOrder");
		//{"充值号码","充值日期","号码余额","商品名称","	订单状态","冲正状态","消费金额","流水号"};
		for (int i = 0; i <list.size(); i++) {
			HSSFRow row2 = sheet.createRow(i+1);
			String orderResultTemp = orderResult_(list.get(i).getState());
			String reversalStateTemp = reversalState_(list.get(i).getReversalState());
			
			createCell(wb,row2,0,list.get(i).getMobile());
			createCell(wb,row2,1,list.get(i).getCreateTime());
			createCell(wb,row2,2,list.get(i).getcBalance());
			createCell(wb,row2,3,list.get(i).getGoodsName());
			createCell(wb,row2,4,orderResultTemp);
			createCell(wb,row2,5,reversalStateTemp);
			createCell(wb,row2,6,list.get(i).getTotalMoney());
			createCell(wb,row2,7,list.get(i).getMainOrderId());
			tempOrderList++;
         	if(tempOrderList ==cellCount){
         		createExcel_001Temp(str,list);
         		return;
         	}
		}
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	public void createExcel_002Temp(String str[],List<Mainorder> list1){
		tempOrderList = 1;
		count_sheet++;
		HSSFSheet sheet = wb.createSheet("sheet"+count_sheet);  //建立新的sheet对象
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
//		List<Mainorder> list = (List<Mainorder>)this.getSession().get("listMainOrder");
		//{"充值号码","充值日期","号码余额","商品名称","	订单状态","冲正状态","消费金额","流水号"};
		for (int i = 0; i <list1.size(); i++) {
			HSSFRow row2 = sheet.createRow(i+1);
			String orderResultTemp = orderResult_(list1.get(i).getState());
			String reversalStateTemp = reversalState_(list1.get(i).getReversalState());
			String isTerminalTemp = isTerminal_(list1.get(i).getIsTerminal());
			String orderTypeTemp = orderType_(list1.get(i).getOrderType());
			String isNeedManualTemp = isNeedManual_(list1.get(i).getIsNeedManual());
			createCell(wb,row2,0,list1.get(i).getMobile());
			createCell(wb,row2,1,list1.get(i).getCreateTime());
			createCell(wb,row2,2,list1.get(i).getGoodsName());
			createCell(wb,row2,3,list1.get(i).getSupplyName());
			createCell(wb,row2,4,orderResultTemp);
			createCell(wb,row2,5,reversalStateTemp);
			createCell(wb,row2,6,list1.get(i).getReversalTime());
			createCell(wb,row2,7,list1.get(i).getTotalMoney());
			createCell(wb,row2,8,list1.get(i).getStockPrice());
			createCell(wb,row2,9,list1.get(i).getRetailPrice());
			createCell(wb,row2,10,list1.get(i).getCommission());
			createCell(wb,row2,11,list1.get(i).getcBalance());
			createCell(wb,row2,12,list1.get(i).getMainOrderId());
			createCell(wb,row2,13,list1.get(i).getUserName());
			createCell(wb,row2,14,list1.get(i).getTerminalNo());
			createCell(wb,row2,15,list1.get(i).getGoodsValue());
			createCell(wb,row2,16,list1.get(i).getGoodsNo());
			createCell(wb,row2,17,isTerminalTemp);
			createCell(wb,row2,18,orderTypeTemp);
			createCell(wb,row2,19,isNeedManualTemp);
			createCell(wb,row2,20,list1.get(i).getCardNo());
			createCell(wb,row2,21,list1.get(i).getManualUserName());
			tempOrderList++;
			if(tempOrderList ==cellCount){
				createExcel_002Temp(str,list1);
				return;
			}
		}
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public void createExcel(String str[]){
		HSSFSheet sheet = wb.createSheet("sheet"+count_sheet);  //建立新的sheet对象
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
		
		switch(flagArray){
		case 0:
			//查询订单，商户端
			List<Mainorder> list = listMainOrder;
			for (int i = 0; i <list.size(); i++) {
				HSSFRow row2 = sheet.createRow(i+1);
				String orderResultTemp = orderResult_(list.get(i).getState());
				String reversalStateTemp = reversalState_(list.get(i).getReversalState());
				
				createCell(wb,row2,0,list.get(i).getMobile());
				createCell(wb,row2,1,list.get(i).getCreateTime());
				createCell(wb,row2,2,list.get(i).getcBalance());
				createCell(wb,row2,3,list.get(i).getGoodsName());
				createCell(wb,row2,4,orderResultTemp);
				createCell(wb,row2,5,reversalStateTemp);
				createCell(wb,row2,6,list.get(i).getTotalMoney());
				createCell(wb,row2,7,list.get(i).getMainOrderId());
				tempOrderList++;
	             	if(tempOrderList ==cellCount){
	             		createExcel_001Temp(str,list);
	             		break;
	             	}
	             list.remove(i);	
			}
		    break;
		case 1:
			//查询订单， 管理员端
			List<Mainorder> list1 = listMainOrder;
			
			for (int i = 0; i <list1.size(); i++) {
				HSSFRow row2 = sheet.createRow(i+1);
				String orderResultTemp = orderResult_(list1.get(i).getState());
				String reversalStateTemp = reversalState_(list1.get(i).getReversalState());
				String isTerminalTemp = isTerminal_(list1.get(i).getIsTerminal());
				String orderTypeTemp = orderType_(list1.get(i).getOrderType());
				String isNeedManualTemp = isNeedManual_(list1.get(i).getIsNeedManual());
				createCell(wb,row2,0,list1.get(i).getMobile());
				createCell(wb,row2,1,list1.get(i).getCreateTime());
				createCell(wb,row2,2,list1.get(i).getGoodsName());
				createCell(wb,row2,3,list1.get(i).getSupplyName());
				createCell(wb,row2,4,orderResultTemp);
				createCell(wb,row2,5,reversalStateTemp);
				createCell(wb,row2,6,list1.get(i).getReversalTime());
				createCell(wb,row2,7,list1.get(i).getTotalMoney());
				createCell(wb,row2,8,list1.get(i).getStockPrice());
				createCell(wb,row2,9,list1.get(i).getRetailPrice());
				createCell(wb,row2,10,list1.get(i).getCommission());
				createCell(wb,row2,11,list1.get(i).getcBalance());
				createCell(wb,row2,12,list1.get(i).getMainOrderId());
				createCell(wb,row2,13,list1.get(i).getUserName());
				createCell(wb,row2,14,list1.get(i).getTerminalNo());
				createCell(wb,row2,15,list1.get(i).getGoodsValue());
				createCell(wb,row2,16,list1.get(i).getGoodsNo());
				createCell(wb,row2,17,isTerminalTemp);
				createCell(wb,row2,18,orderTypeTemp);
				createCell(wb,row2,19,isNeedManualTemp);
				createCell(wb,row2,20,list1.get(i).getCardNo());
				createCell(wb,row2,21,list1.get(i).getManualUserName());
				tempOrderList++;
             	if(tempOrderList ==cellCount){
             		createExcel_002Temp(str,list1);
             		break;
             	}
             list1.remove(i);
             
			}
			break;
		case 2:
			List<Mainorder> list2 = (List<Mainorder>)this.getSession().get("");
			
			for (int i = 0; i <list2.size(); i++) {
				
				HSSFRow row2 = sheet.createRow(i+1);
				createCell(wb,row2,0,list2.get(i).getTerminalNo());
			}
			break;
		case 3:
			List<Mainorder> list3 = (List<Mainorder>)this.getSession().get("");
			
			for (int i = 0; i <list3.size(); i++) {
				
				HSSFRow row2 = sheet.createRow(i+1);
				createCell(wb,row2,0,list3.get(i).getTerminalNo());
			}
			break;
		case 4:
			List<Mainorder> list4 = (List<Mainorder>)this.getSession().get("");
			
			for (int i = 0; i <list4.size(); i++) {
				HSSFRow row2 = sheet.createRow(i+1);
				createCell(wb,row2,0,list4.get(i).getTerminalNo());
			}
			break;
		}
		
		
			try {
				wb.write(out);
			} catch (IOException e) {
				e.printStackTrace();
			}  
		
		  
	}
	
	/**
	 * 订单状态
	 * @param state
	 * @return
	 */
	public String orderResult_(int state){
		String orderResult = "";
		switch(state){
		case 0:
			orderResult = "申请处理";
			break;
		case 1:
			orderResult = "处理成功";
			break;
		case 2:
			orderResult = "处理失败";
			break;
		case 3:
			orderResult = "处理失败";
			break;
		case 253:
			orderResult = "处理中";
			break;
		case 254:
			orderResult = "取消中";
			break;
		case 255:
			orderResult = "已取消";
			break;
		}
		return orderResult;
	}
	
	/**
	 * 冲正状态
	 * @param state
	 * @return
	 */
	public String reversalState_(int state){
		String reversalStateTemp = "";
		
		switch(state){
		case 0:
			reversalStateTemp = "冲正中";
			break;
		case 1:
			reversalStateTemp = "冲正成功";
			break;
		case 2:
			reversalStateTemp = "冲正失败";
			break;
		case -1:
			reversalStateTemp = "未冲正";
			break;
		case -2:
			reversalStateTemp = "申请冲正";
			break;
		default:
			reversalStateTemp = "申请冲正";
			break;
		}
		
		return reversalStateTemp;
	}
	
	/**
	 * 是否终端操作
	 * @param isTerminal
	 * @return
	 */
	public String isTerminal_(int isTerminal){
		String isTerminalTemp = "";
		switch(isTerminal){
		case 0:
			isTerminalTemp = "否";
			break;
		case 1:
			isTerminalTemp = "是";
			break;
		}
		return isTerminalTemp;
	}
	/**
	 * 订单类型
	 * @param isTerminal
	 * @return
	 */
	public String orderType_(int orderType){
		String orderTypeTemp = "";
		switch(orderType){
		case 0:
			orderTypeTemp = "直冲";
			break;
		case 1:
			orderTypeTemp = "卡密";
			break;
		default:
			orderTypeTemp = "未知";
			break;
		}
		return orderTypeTemp;
	}
	/**
	 * 是否需要人工补充
	 * @param orderType
	 * @return
	 */
	public String isNeedManual_(int isNeedManual){
		String isNeedManualTemp = "";
		switch(isNeedManual){
		case 1:
			isNeedManualTemp = "需要";
			break;
		case 2:
			isNeedManualTemp = "不需要";
			break;
		case 255:
			isNeedManualTemp = "已补充";
			break;
		default:
			isNeedManualTemp = "未知";
			break;
		}
		return isNeedManualTemp;
	}
	
	
	public  void createCell(HSSFWorkbook wb,HSSFRow row,int s,Object value){
	     HSSFCell cell = row.createCell(s);
	     if (value instanceof Number)
	        {
	            Number num = (Number) value;
	            cell.setCellValue(num.doubleValue());
	        }
	        else if (value instanceof Date)
	        {
	            cell.setCellValue((Date) value);
	        }
	        else if (value instanceof Calendar)
	        {
	            cell.setCellValue((Calendar) value);
	        }
	        else
	        {
	            cell.setCellValue(new HSSFRichTextString(escapeColumnValue(value)));
	        }
	}
	 protected String escapeColumnValue(Object rawValue)
	    {
	        if (rawValue == null)
	        {
	            return null;
	        }
	        String returnString = ObjectUtils.toString(rawValue);
	        // escape the String to get the tabs, returns, newline explicit as \t \r \n
	        returnString = StringEscapeUtils.escapeJava(StringUtils.trimToEmpty(returnString));
	        // remove tabs, insert four whitespaces instead
	        returnString = StringUtils.replace(StringUtils.trim(returnString), "\\t", "    ");
	        // remove the return, only newline valid in excel
	        returnString = StringUtils.replace(StringUtils.trim(returnString), "\\r", " ");
	        // unescape so that \n gets back to newline
	        returnString = StringEscapeUtils.unescapeJava(returnString);
	        return returnString;
	    }
	 
	    public int getFlagArray() {
			return flagArray;
		}
		public void setFlagArray(int flagArray) {
			this.flagArray = flagArray;
		}
		public List<Mainorder> getListMainOrder() {
			return listMainOrder;
		}
		public void setListMainOrder(List<Mainorder> listMainOrder) {
			this.listMainOrder = listMainOrder;
		}
}

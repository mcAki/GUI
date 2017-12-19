package com.sys.volunteer.ws.client;

/*
 * @作者:Mac Kwan , 创建日期:Mar 25, 2011
 *
 * 汕头大学03计算机本科
 * 
 */

import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import cn.epaylinks.www.TerminalAirDepositRequestNew;
import cn.epaylinks.www.TerminalAirDepositResponseNew;

import com.sys.volunteer.common.FileTool;

public class QAWSClient
{
    public static Calendar BASE_CAL=new GregorianCalendar(1970,0,1,8,0);

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String endPoint = "http://219.136.207.190:8081/webservice/services/TerminalService?wsdl";

            // call
            String terminalNo = "35170001";
            String hexPassword = "21218cca77804d2ba1922c33e0151105";
            String password = "000000";
            String mobileNum = "13632311322";
            double amount = 100.0;
            String storeSeq = "gz711-00003";
            String areaCode = "020";
            Calendar requestTime = GregorianCalendar.getInstance();
            int payType = 1;
            String depositType = "000001";
            long timeSpan=requestTime.getTimeInMillis()-BASE_CAL.getTimeInMillis();
            
            String sign = MD5String(terminalNo + password + mobileNum + Double.toString(amount) + areaCode
                    + storeSeq + timeSpan+ hexPassword);
            
            System.out.println(dateFormat.format(requestTime.getTime()));
            System.out.println(Long.toString(timeSpan));
            System.out.println(Double.toString(amount));
            
            TerminalAirDepositRequestNew request = new TerminalAirDepositRequestNew(
                    amount, areaCode, depositType, mobileNum, password,
                    payType, requestTime, sign, storeSeq, terminalNo);

            //TerminalServiceProxy proxy = new TerminalServiceProxy(endPoint);
            
            
            String url = (String) FileTool.loadConfig("iphoneService",
			"/wsconfig.properties");
	

            TerminalService terminalService = new TerminalServiceServiceLocator().getTerminalService(new URL(url));
            TerminalAirDepositResponseNew response = terminalService.terminalAirDepositNew(request);
            
            System.out.println(dateFormat.format(response.getResponseTime().getTime()));
            System.out.println(response.getRespCode());
            System.out.println(response.getRespDisc());
            System.out.println(ReflectionToStringBuilder.toString(response));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String MD5String(String plainText)
    {
        String str="";
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        }
        catch (Exception e)
        {
            str="";
        }
        return str;
    }

}

package com.ages.util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 检查有没有死锁线程的类
 * @author admin
 *
 */
public class CheckThread extends TimerTask{
    public static void main(String[] argc){
//        new Thread(new CheckThread()).start();
//        new Timer().schedule(new CheckThread(), 0,10*60*1000);
        Init();
    }
    public static void Init(){
//        new Thread(new CheckThread()).start();
        new Timer().schedule(new CheckThread(), 0,10*3000);//30秒
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        ThreadMXBean th=(ThreadMXBean)ManagementFactory.getThreadMXBean() ;
        long[] ll=th.findMonitorDeadlockedThreads();
        //System.out.println("CheckThread run");
        if(ll==null){
        	
        }else {
        	ThreadInfo[] tia=th.getThreadInfo(ll,Integer.MAX_VALUE);
            StringBuffer sb=new StringBuffer();
            sb.append("findMonitorDeadlockedThreads Info :\r\n");
            for(int i=0;i<tia.length;i++){
                ThreadInfo ti=tia[i];
                StackTraceElement[] ste=ti.getStackTrace();
                for(int j=0;j<ste.length;j++){
                    sb.append(ste[j].toString());
                    sb.append("\r\n");
                }
                sb.append("\r\n");
                sb.append("\r\n");
            }
            System.err.println(sb.toString());
		}
        
    }
}


package com.httpPush;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台监控锁屏时间与自动退出时间
 * @author lujun
 * 思路，就是把前台setIntevar()方法替换成现在这种方式
 * 一些特定请求任然需要记录其请求时间与当前时间进行对比，
 * 只是判断的时候需要使用这种使用线程休眠长轮训检测方式返回
 * 如果到在规定时间内有到达锁屏时间则返回0进行锁屏
 * 否则返回超时
 * 如果到在规定时间内有到达退出时间则返回1进行退出
 * 否则返回超时
 * 
 * 【注意】需要使用tomcat7.0或者以上，jdk1.6或者以上
 */
public class AutoMonitor extends HttpServlet {

	private static final long serialVersionUID = -3981794330055840248L;  
	  
    private String interval = "1";  
    private static final int autoLock = 3;		//自动锁屏时间
    private static final int autoLogout = 5;	//自动退出时间
  
    public void init(ServletConfig config) throws ServletException {  
        this.interval = config.getInitParameter("interval");  
    }  
  
    public void destroy() {  
        this.interval = null;  
    }  
    
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	this.doPost(request,response);
    }
  
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{  
    	Random random = new Random();
    	long time = Long.parseLong(request.getParameter("time").toString());
    	while(true){
    		try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		int i = random.nextInt(100);
    		if(i > 25 && i < 56){
    			long responseTime = System.currentTimeMillis();
    			String str = "result:"+i+" requestTime:"+time+" responseTime:"+responseTime+" userTime:"+(responseTime-time);
    			writerResponse(str,response);
    			break;
    		}else{
    			try {
					Thread.sleep(1300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
    }  
  
    protected void writerResponse(String str,HttpServletResponse response) throws IOException {  
        response.getWriter().write(str.toString());  
        response.flushBuffer();  
    }  
  
    public String getInterval() {  
        return interval;  
    }  
  
    public void setInterval(String interval) {  
        this.interval = interval;  
    }  
}

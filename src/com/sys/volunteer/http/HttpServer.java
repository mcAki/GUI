package com.sys.volunteer.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * 没用的
 * @author Administrator
 *
 */
public class HttpServer {

	private HttpURLConnection httpUrlConnection;

	public HttpServer(String address) {
		try {
			URL url = new URL(address);
			
			httpUrlConnection = (HttpURLConnection)url.openConnection();
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false
			httpUrlConnection.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpUrlConnection.setDoInput(true);
			// Post 请求不能使用缓存
			httpUrlConnection.setUseCaches(false);
			// 设定传送的内容类型是可序列化的java对象
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			httpUrlConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
			// 设定请求的方法为"POST"，默认是GET
			httpUrlConnection.setRequestMethod("POST");
			// 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，
			httpUrlConnection.connect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HttpURLConnection getHttpUrlConnection() {
		return httpUrlConnection;
	}
	
	public void testReadWrite(String content) throws IOException{
		OutputStream outStrm = httpUrlConnection.getOutputStream();
		ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);
		objOutputStrm.writeObject(content);
		objOutputStrm.flush();
		objOutputStrm.close();
		System.out.println("写完了.....");
	}
}

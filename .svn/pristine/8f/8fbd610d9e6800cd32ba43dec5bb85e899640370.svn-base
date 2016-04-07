package com.rjkx.sk.itf.weixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class WeiXinHttpUtils 
{
	private static final Log log = LogFactory.getLog(WeiXinHttpUtils.class);
	
	/**
	 * GET
	 * @param url
	 * @return
	 * @throws IOException 
	 */
	public static String get(String url) throws IOException {
		String str="";
		URL urlGet = new URL(url);
		InputStream is=null;
		HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		try{
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			http.connect();
			if(http.getResponseCode()!=200){
				http.disconnect();
				http=null;
				return str;
			}
			is = http.getInputStream();
			/*int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			is.close();*/
			
			BufferedReader breader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = "";
			while ((line = breader.readLine()) != null) {
				str += line;
			}
			breader.close();			
		}catch(Exception e){
		}finally{
			if(is!=null){
				is.close();
				is=null;
			}
			if(http!=null){
				http.disconnect();
				http=null;
			}
		}
		return str;//new String(jsonBytes, "UTF-8");
	}
	
	/**
	 * POST
	 * @param url
	 * @return
	 * @throws IOException 
	 */
	public static String post(String serviceUrl,String content) {
		String ret = "-1";
		HttpURLConnection httpurlconnection = null;
		try {
			URL url = new URL(serviceUrl);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
			httpurlconnection.setConnectTimeout(30000);
			httpurlconnection.setReadTimeout(30000);
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setDoInput(true);
			httpurlconnection.setUseCaches(false);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.getOutputStream().write(content.getBytes("UTF-8"));
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			int code = httpurlconnection.getResponseCode();
			if (code == 200) {
				InputStream in = httpurlconnection.getInputStream();
				BufferedReader breader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String str = "", line = "";
				while ((line = breader.readLine()) != null) {
					str += line;
				}
				breader.close();
				in.close();
				in=null;
				ret=str;
			}
		} catch (Exception e) {
			log.error("Post Error!");
		} finally {
			if (httpurlconnection != null){
				httpurlconnection.disconnect();
				httpurlconnection=null;
			}
		}
		log.info(ret);
		return ret;
	}
	
	/***
	 * Https Post
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月21日 下午7:46:43
	  * @param requestUrl
	  * @param outputStr
	  * @return
	 */
	public static String httpsRequest(String requestUrl, String outputStr) {
		String sRet="";
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod("POST");

			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			sRet=buffer.toString();
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}
		return sRet;
	}
}

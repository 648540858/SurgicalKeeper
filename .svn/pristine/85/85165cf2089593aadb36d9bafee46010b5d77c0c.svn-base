package com.rjkx.sk.system.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SmsUtil {
	private static Log	log	= LogFactory.getLog(SmsUtil.class);
	private static final String smsAccount="ruijkx";
	private static final String smsPwd="123456";
	
	private SmsUtil(){
	}
	
	/***
	 * 一通道短信提交
	 * 
	 * @param dstmobile
	 *            :手机号
	 * @param content
	 *            :内容
	 * @return 0-成功
	 */
	public static String SendWebSMS(String dstmobile, String content) {
		String ret = "-1";
		try {
			content = URLEncoder.encode(content, "GB2312").toLowerCase();
		} catch (Exception e2) {
		}
		String serviceUrl = "http://120.24.77.129:8888/smsGBK.aspx";
		String param = "userid=638&account=" + smsAccount + "&password=" + smsPwd + "&mobile=" + dstmobile + "&action=send&extno=&content=" + content + "&sendTime=";
		log.info("websms---begin");
		HttpURLConnection httpurlconnection = null;
		try {
			URL url = new URL(serviceUrl);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setConnectTimeout(30000);//设置http连接主机超时,单位是毫秒
			httpurlconnection.setReadTimeout(30000);//设置http连接的读超时,单位是毫秒
			//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			//http正文内，因此需要设为true, 默认情况下是false;
			httpurlconnection.setDoOutput(true);
			//设置是否从httpUrlConnection读入，默认情况下是true;
			httpurlconnection.setDoInput(true);
			// Post 请求不能使用缓存
			httpurlconnection.setUseCaches(false);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.getOutputStream().write(param.getBytes());
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			//获取响应代码     
			int code = httpurlconnection.getResponseCode();
			//获取页面内容
			if (code == 200) {
				InputStream in = httpurlconnection.getInputStream();
				BufferedReader breader = new BufferedReader(new InputStreamReader(in, "GBK"));
				String str = "", line = "";
				while ((line = breader.readLine()) != null) {
					str += line;
				}
				breader.close();
				str = str.replace(" ", "");
				str = str.replace("\r\n", "");
				str = str.replaceAll("\\s*", "");
//				System.out.println(str);
				if (str.length() > 2 && str.toLowerCase().indexOf("ok") >= 0) {
					ret = "0";
				} else {
					int i = str.indexOf("包含屏蔽词：");
					int j = str.indexOf("</message>");
					if (j > i)
						ret = str.substring(i + 6, j);
					else
						ret = "1";
				}
			}
		} catch (Exception e) {
			log.info("websms-sendsms:" + e.getMessage());
		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();
		}
		log.info("websms---" + ret);
		return ret;
	}

	/***
	 * 一通道短信接收
	 * 
	 * @param account
	 *            :帐号
	 * @param pwd
	 *            :密码
	 */
	public static void MoWebSMS() {
		String ret = "-1";
		String serviceUrl = "http://120.24.77.129:8888/callApi.aspx";
		String param = "userid=638&account=" + smsAccount + "&password=" + smsPwd+"&action=query";
		log.info("websms-mo---begin");
		HttpURLConnection httpurlconnection = null;
		try {
			URL url = new URL(serviceUrl);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setConnectTimeout(30000);//设置http连接主机超时,单位是毫秒
			httpurlconnection.setReadTimeout(30000);//设置http连接的读超时,单位是毫秒
			//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			//http正文内，因此需要设为true, 默认情况下是false;
			httpurlconnection.setDoOutput(true);
			//设置是否从httpUrlConnection读入，默认情况下是true;
			httpurlconnection.setDoInput(true);
			// Post 请求不能使用缓存
			httpurlconnection.setUseCaches(false);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.getOutputStream().write(param.getBytes());
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			//获取响应代码     
			int code = httpurlconnection.getResponseCode();
			//获取页面内容
			if (code == 200) {
				InputStream in = httpurlconnection.getInputStream();
				BufferedReader breader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String str = "", line = "";
				while ((line = breader.readLine()) != null) {
					str += line;
				}
				breader.close();
				str = str.replace(" ", "");
				str = str.replace("\r\n", "");
				str = str.replaceAll("\\s*", "");
				ret = str;
			}
		} catch (Exception e) {
			log.info("websms-mo:" + e.getMessage());
		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();
		}
		log.info("websms-mo---" + ret);
	}

	/***
	 * 返回一通道帐号信息
	 * 
	 * @param account
	 *            :帐号
	 * @param pwd
	 *            :密码
	 * @return 帐号信息
	 */
	public static String getWebSms() {
		String ret = "-1";
		String serviceUrl = "http://120.24.77.129:8888/sms.aspx";
		String param = "userid=638&account="+smsAccount+"&password="+smsPwd+"&action=overage";
		log.info("websms-overage---begin");
		HttpURLConnection httpurlconnection = null;
		try {
			URL url = new URL(serviceUrl);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setConnectTimeout(30000);//设置http连接主机超时,单位是毫秒
			httpurlconnection.setReadTimeout(30000);//设置http连接的读超时,单位是毫秒
			//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			//http正文内，因此需要设为true, 默认情况下是false;
			httpurlconnection.setDoOutput(true);
			//设置是否从httpUrlConnection读入，默认情况下是true;
			httpurlconnection.setDoInput(true);
			// Post 请求不能使用缓存
			httpurlconnection.setUseCaches(false);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.getOutputStream().write(param.getBytes());
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			//获取响应代码     
			int code = httpurlconnection.getResponseCode();
			//获取页面内容
			if (code == 200) {
				InputStream in = httpurlconnection.getInputStream();
				BufferedReader breader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String str = "", line = "";
				while ((line = breader.readLine()) != null) {
					str += line;
				}
				breader.close();
				str = str.replace(" ", "");
				str = str.replace("\r\n", "");
				str = str.replaceAll("\\s*", "");
				ret = str;
			}
		} catch (Exception e) {
			log.info("websms-overage:" + e.getMessage());
		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();
		}
		log.info("websms-overage---"+ret);
		return ret;
	}
}

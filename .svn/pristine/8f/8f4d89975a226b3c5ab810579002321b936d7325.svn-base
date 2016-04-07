package com.rjkx.sk.system.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

public class SmsSendUtils {
	private static final Log log = LogFactory.getLog(SmsSendUtils.class);
	
	private final String Url="http://222.73.117.158/msg/";
	
	private static SmsSendUtils instance=null;

	/***
	 * 一通道短信提交
	 * 
	 * @param account
	 *            :帐号
	 * @param pwd
	 *            :密码
	 * @param dstmobile
	 *            :手机号
	 * @param content
	 *            :内容
	 * @return 0-成功
	 */
	public String SendWebSMS(String account, String pwd, String dstmobile, String content) {
		String ret = "-1";
		try {
			content = URLEncoder.encode(content, "GB2312").toLowerCase();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		String serviceUrl = "http://120.24.77.129:8888/smsGBK.aspx";
		String param = "userid=638&account=" + account + "&password=" + pwd + "&mobile=" + dstmobile + "&action=send&extno=&content=" + content + "&sendTime=";
		log.info("websms---begin");
		HttpURLConnection httpurlconnection = null;
		try {
			URL url = new URL(serviceUrl);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setConnectTimeout(30000);// 设置http连接主机超时,单位是毫秒
			httpurlconnection.setReadTimeout(30000);// 设置http连接的读超时,单位是毫秒
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			httpurlconnection.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpurlconnection.setDoInput(true);
			// Post 请求不能使用缓存
			httpurlconnection.setUseCaches(false);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.getOutputStream().write(param.getBytes());
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			// 获取响应代码
			int code = httpurlconnection.getResponseCode();
			// 获取页面内容
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
				// System.out.println(str);
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
	public void MoWebSMS(String account, String pwd) {
		String ret = "-1";
		String serviceUrl = "http://120.24.77.129:8888/callApi.aspx";
		String param = "userid=638&account=" + account + "&password=" + pwd + "&action=query";
		log.info("websms-mo---begin");
		HttpURLConnection httpurlconnection = null;
		try {
			URL url = new URL(serviceUrl);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setConnectTimeout(30000);// 设置http连接主机超时,单位是毫秒
			httpurlconnection.setReadTimeout(30000);// 设置http连接的读超时,单位是毫秒
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			httpurlconnection.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpurlconnection.setDoInput(true);
			// Post 请求不能使用缓存
			httpurlconnection.setUseCaches(false);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.getOutputStream().write(param.getBytes());
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			// 获取响应代码
			int code = httpurlconnection.getResponseCode();
			// 获取页面内容
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
	public String getWebSms(String account, String pwd) {
		String ret = "-1";
		String serviceUrl = "http://120.24.77.129:8888/sms.aspx";
		String param = "userid=638&account=" + account + "&password=" + pwd + "&action=overage";
		log.info("websms-overage---begin");
		HttpURLConnection httpurlconnection = null;
		try {
			URL url = new URL(serviceUrl);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setConnectTimeout(30000);// 设置http连接主机超时,单位是毫秒
			httpurlconnection.setReadTimeout(30000);// 设置http连接的读超时,单位是毫秒
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			httpurlconnection.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpurlconnection.setDoInput(true);
			// Post 请求不能使用缓存
			httpurlconnection.setUseCaches(false);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.getOutputStream().write(param.getBytes());
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			// 获取响应代码
			int code = httpurlconnection.getResponseCode();
			// 获取页面内容
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
		log.info("websms-overage---" + ret);
		return ret;
	}

	/**
	 * 创蓝-短信
	 * 
	 * @param url
	 *            应用地址，类似于http://ip:port/msg/
	 * @param account
	 *            账号
	 * @param pswd
	 *            密码
	 * @param mobile
	 *            手机号码，多个号码使用","分割
	 * @param msg
	 *            短信内容
	 * @param needstatus
	 *            是否需要状态报告，需要true，不需要false
	 * @return 返回值定义参见HTTP协议文档
	 * @throws Exception
	 */
	public String batchSend(String account, String pswd, String mobile, String msg, boolean needstatus, String product, String extno) {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		String sRet="-1";
		try {
			URI base = new URI(this.Url, false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("account", account), 
					new NameValuePair("pswd", pswd), 
					new NameValuePair("mobile", mobile), 
					new NameValuePair("needstatus", String.valueOf(needstatus)), 
					new NameValuePair("msg", msg), 
					new NameValuePair("product", product), 
					new NameValuePair("extno", extno)});
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				sRet=URLDecoder.decode(baos.toString(), "UTF-8");
			} else {
				sRet="HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText();
			}
		}catch(Exception e){
			sRet="cl-batchSmsSend:"+e.getMessage();
		}finally {
			method.releaseConnection();
		}
		return sRet;
	}

	/**
	 * 单例获取实例
	 * 
	 * @author yiyuan-Rally
	 * @date 2015年11月3日 上午10:31:16
	 * @return
	 */
	public static SmsSendUtils getInstance() {
		synchronized (SmsSendUtils.class) {
			if (instance == null) {
				log.info("[SmsSendUtils]单例模式实例化!");
				instance = new SmsSendUtils();
			}
		}
		return instance;
	}
}

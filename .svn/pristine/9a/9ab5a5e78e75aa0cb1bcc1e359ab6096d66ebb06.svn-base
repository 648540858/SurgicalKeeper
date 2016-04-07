package com.rjkx.sk.itf.weixin.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rjkx.sk.itf.weixin.pojo.Token;

/**
 * 定时获取TOKEN与JS_TICKET线程
 * 
 * @author rally
 *
 */
public final class WeiXinTokenThread implements Runnable {

	private static final Log log = LogFactory.getLog(WeiXinTokenThread.class);

	/**
	 * TOKEN
	 */
	public static String ACCESS_TOKEN = null;
	/**
	 * JS_TICKET
	 */
	public static String JS_TICKET = null;
	/* 控制线程是否退出 */
	public static int stop = 0;

	@Override
	public void run() {
		while (stop == 0) {
			try {
				Token accessToken=WeiXinUtils.getToken(WeiXinCons.WX_APP_ID, WeiXinCons.WX_APP_SECRET);
				if(accessToken!=null){
					ACCESS_TOKEN = accessToken.getAccess_token();
				}else{
					ACCESS_TOKEN=null;
				}

				if (ACCESS_TOKEN != null) {
					log.info("access_token获取成功! access_token : " + ACCESS_TOKEN);
					while (stop == 0) {
						JS_TICKET = WeiXinUtils.getJsTicket(ACCESS_TOKEN);
						if (JS_TICKET != null) {
							log.info("js_ticket获取成功! js_ticket : " + JS_TICKET);
							break;
						} else {
							for (int i = 0; i < 20; i++) {
								Thread.sleep(1000);
								if (stop == 1)
									break;
							}
						}
					}
					for (int i = 0; i < 7000; i++) {
						Thread.sleep(1000);
						if (stop == 1)
							break;
					}
				} else {
					for (int i = 0; i < 60; i++) {
						Thread.sleep(1000);
						if (stop == 1)
							break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("获取失败!");
			}
		}
	}
}
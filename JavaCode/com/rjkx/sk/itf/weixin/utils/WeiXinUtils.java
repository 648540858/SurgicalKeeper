package com.rjkx.sk.itf.weixin.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.rjkx.sk.itf.weixin.pojo.BaseMenu;
import com.rjkx.sk.itf.weixin.pojo.ButtonMenu;
import com.rjkx.sk.itf.weixin.pojo.ComplexMenu;
import com.rjkx.sk.itf.weixin.pojo.Media;
import com.rjkx.sk.itf.weixin.pojo.Menu;
import com.rjkx.sk.itf.weixin.pojo.QRCode;
import com.rjkx.sk.itf.weixin.pojo.Token;
import com.rjkx.sk.itf.weixin.pojo.UserInfo;
import com.rjkx.sk.itf.weixin.pojo.UserList;
import com.rjkx.sk.itf.weixin.pojo.ViewMenu;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.FredaUtils;

/**
 * 微信公众平台工具类
 * 
 * @author LiChun
 *
 */
public final class WeiXinUtils {
	private static final Log log = LogFactory.getLog(WeiXinUtils.class);
	/* 创建菜单 */
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	/* 删除菜单 */
	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	/* 获取Token */
	private final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/* 根据code换取accessToken */
	private final static String GET_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/* 根据refreshToken换取accessToken */
	private final static String GET_REFRESH_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	/* 微信获取用户列表发送URL */
	public static final String GET_USERLIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	/* 微信模板消息发送URL */
	public static final String TEMPLATEMESSAGE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	/* 获取用户基本信息 */
	public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/* 发送客服消息 */
	public static final String KF_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	// 扫描医生二维码模板消息id
	public static final String TEMPLATEMESSAGE_ID_DOCSCAN = "lMl4RFLf2z0zC2KPMJQzX_RXxaKcg-V-Dj5Ckd8LdHk";// "2K53Ic00l-6NT9IZHNTZTPnPprN_NxDtaOjSRQQZHEw";
	// 支付提醒模板消息id
	public static final String TEMPLATEMESSAGE_ID_PAY = "H1szV94LdKqA9guk9L_Gbz8NfRjq6izeOyEtMp4AYYk";// "CRFAPVN3BTacIlA3Y1w_NDv0oXYmxPa26Kqi0g9uWGw";
	// 评价提醒模板消息id
	public static final String TEMPLATEMESSAGE_ID_PJ = "7z4g2vUzknPICI7xKtIYY_p9h_6sowAjruGqBTize4Y";// "K337EQLk0gkORjJUStGofzTJZP2JdMVm7QxM-l9vrWY";
	// 下载多媒体文件
	public static final String DOWNLOAD_MEDIA_URL="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	// 上传多媒体文件
	public static final String UPLOAD_MEDIA_URL="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	/**
	 * 微信token校验
	 * 
	 * @param request
	 * @param token
	 * @return
	 */
	public static String checkToken(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

//		log.info("signature : " + signature);
//		log.info("timestamp : " + timestamp);
//		log.info("nonce : " + nonce);
//		log.info("echostr : " + echostr);

		return checkToken(signature, timestamp, nonce, echostr, WeiXinCons.WX_TOKEN);
	}

	/**
	 * 微信token校验
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param token
	 * @return
	 */
	public static String checkToken(String signature, String timestamp, String nonce, String echostr, String token) {
		if (FredaUtils.isNotEmpty(signature) && FredaUtils.isNotEmpty(timestamp) && FredaUtils.isNotEmpty(nonce) && FredaUtils.isNotEmpty(echostr) && FredaUtils.isNotEmpty(token)) {
			StringBuilder sb = new StringBuilder();
			String[] tmp = { token, timestamp, nonce };
			Arrays.sort(tmp);
			for (String s : tmp) {
				sb.append(s);
			}
			String pwd = WeiXinMessageDigestUtils.getInstance().encipher(sb.toString());
			if (signature.equals(pwd)) {
				log.info("验证成功..允许接入!");
				return echostr;
			}
		}
		log.info("验证失败..拒绝接入!");
		return null;
	}

	/**
	 * 获取Js_ticket
	 * 
	 * @return
	 */
	public static String getJsTicket(String token) {
		String js_ticket = null;

		try {
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi";
			js_ticket = JsonHelper.parseSingleJson2Dto(WeiXinHttpUtils.get(url)).getAsString("ticket");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("获取JS_TICKET成功!");

		return js_ticket;
	}

	/**
	 * 获取jsapi相关map
	 * 
	 * @param jsapi_ticket
	 * @param url
	 * @return
	 */
	public static Map<String, String> getJsSignMap(String jsapi_ticket, String url) {
		Map<String, String> map = new HashMap<String, String>();

		String nonce_str = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String s = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		String signature = WeiXinMessageDigestUtils.getInstance().encipher(s);

		map.put("url", url);
		map.put("jsapi_ticket", jsapi_ticket);
		map.put("nonceStr", nonce_str);
		map.put("timestamp", timestamp);
		map.put("signature", signature);

		log.info("MAP中的参数:");
		log.info("url : " + url);
		log.info("jsapi_ticket : " + jsapi_ticket);
		log.info("nonceStr : " + nonce_str);
		log.info("timestamp : " + timestamp);
		log.info("signature : " + signature);

		return map;
	}

	/***
	 * 网页授权获取token
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年3月31日 上午11:04:44
	  * @param appid
	  * @param appsecret
	  * @param code
	  * @return
	 */
	public static Token getAccessTokenByCode(String appid,String appsecret,String code){
		Token token = null;
		String requestUrl = GET_ACCESS_TOKEN_URL.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
		// 发起GET请求获取凭证
		JSONObject jsonObject = JSONObject.parseObject(WeiXinHttpUtils.httpsRequest(requestUrl, null));
		if (null != jsonObject) {
			if(jsonObject.get("openid")==null||jsonObject.getString("openid").length()<1){
				requestUrl = GET_REFRESH_TOKEN_URL.replace("APPID", appid).replace("REFRESH_TOKEN", jsonObject.getString("refresh_token"));
				// 发起GET请求刷新凭证
				jsonObject = JSONObject.parseObject(WeiXinHttpUtils.httpsRequest(requestUrl, null));
			}
			if(jsonObject!=null){
				try {
					token = new Token();
					token.setAccess_token(jsonObject.getString("access_token"));
					token.setExpires_id(jsonObject.getIntValue("expires_in"));
					token.setRefresh_token(jsonObject.getString("refresh_token"));
					token.setOpenid(jsonObject.getString("openid"));
					token.setScope(jsonObject.getString("scope"));
				} catch (JSONException e) {
					token = null;
					// 获取token失败
					Logger log1 = LoggerFactory.getLogger(WeiXinUtils.class);
					log1.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
				}
			}
		}
		return token;
	}
	
	/***
	 * 创建带参数的二维码ticket
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月20日 下午3:56:42
	 * @param accessToken
	 * @param action_name
	 * @param sceneId
	 * @return
	 */
	public static QRCode createQRCodeTicket(String accessToken, String action_name, int sceneId) {
		QRCode qrcode=null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
		// QR_LIMIT_SCENE为永久,QR_SCENE有效期7天
		String param = "{\"expire_seconds\":604800,\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\": " + sceneId + "}}}}";
		String jsonString=WeiXinHttpUtils.post(requestUrl, param);
		if(jsonString!=null&&jsonString.length()>0){
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			if(jsonObject!=null){
				qrcode=JSON.toJavaObject(jsonObject, QRCode.class);
			}
		}
		return qrcode;
	}

	/***
	 * 根据ticket获取二维码文件
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月20日 下午4:14:33
	 * @param ticket
	 * @param saveFilePath
	 * @return
	 */
	public static boolean getQRCodeForTicket(String ticket, String saveFilePath, String fileName) {
		boolean bRet = false;
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
		URL url = null;
		HttpsURLConnection conn = null;
		try {
			url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!saveFilePath.endsWith(File.separatorChar+""))
				saveFilePath += File.separatorChar;
			String filePath = saveFilePath + fileName;

			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();
			fos = null;
			bis = null;
			bRet = true;
		} catch (Exception e) {
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
			if (url != null) {
				url = null;
			}
		}
		return bRet;
	}

	/***
	 * 创建菜单
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月21日 下午8:05:47
	 * @param accessToken
	 * @return
	 */
	public static boolean createMenu(String accessToken) {
		if (!deleteMenu(accessToken)) {
			return false;
		}
		ViewMenu vm1 = new ViewMenu();
		vm1.setType(BaseMenu.VIEW_TYPE);
		vm1.setName("骨科医愿");
		vm1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeiXinCons.WX_APP_ID + "&redirect_uri=" + WeiXinCons.WX_SAFE_DOMAIN + "/WxPro/wx/gkyy_home.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");

		ViewMenu vm21 = new ViewMenu();
		vm21.setType(BaseMenu.VIEW_TYPE);
		vm21.setName("精准预约");
		vm21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeiXinCons.WX_APP_ID + "&redirect_uri=" + WeiXinCons.WX_SAFE_DOMAIN + "/WxPro/wx/ghyy_doclist.jsp?schType=1&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
		ViewMenu vm22 = new ViewMenu();
		vm22.setType(BaseMenu.VIEW_TYPE);
		vm22.setName("VIP会诊");
		vm22.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeiXinCons.WX_APP_ID + "&redirect_uri=" + WeiXinCons.WX_SAFE_DOMAIN + "/WxPro/wx/ghyy_vipcon.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
		ComplexMenu vm2 = new ComplexMenu();
		vm2.setName("找对医生");
		vm2.setSub_button(new BaseMenu[] { vm21, vm22 });

		ViewMenu vm31 = new ViewMenu();
		vm31.setType(BaseMenu.VIEW_TYPE);
		vm31.setName("健康课堂");
		vm31.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeiXinCons.WX_APP_ID + "&redirect_uri=" + WeiXinCons.WX_SAFE_DOMAIN + "/WxPro/wx/myjt_list.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
		ViewMenu vm32 = new ViewMenu();
		vm32.setType(BaseMenu.VIEW_TYPE);
		vm32.setName("骨科动态");
		vm32.setUrl("http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzI0ODExOTU1NA==#wechat_webview_type=1&wechat_redirect");
		ViewMenu vm33 = new ViewMenu();
		vm33.setType(BaseMenu.VIEW_TYPE);
		vm33.setName("个人中心");
		vm33.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeiXinCons.WX_APP_ID + "&redirect_uri=" + WeiXinCons.WX_SAFE_DOMAIN + "/WxPro/wx/grzx_home.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
		ViewMenu vm35 = new ViewMenu();
		vm35.setType(BaseMenu.VIEW_TYPE);
		vm35.setName("医生专用");
		vm35.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeiXinCons.WX_APP_ID + "&redirect_uri=" + WeiXinCons.WX_SAFE_DOMAIN + "/WxPro/wx/doctor_home.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
		ButtonMenu vm34 = new ButtonMenu();
		vm34.setType(BaseMenu.BUTTON_TYPE);
		vm34.setName("在线客服");
		vm34.setKey("online");
		ComplexMenu vm3 = new ComplexMenu();
		vm3.setName("医愿服务");
		vm3.setSub_button(new BaseMenu[] { vm31, vm32, vm33, vm35, vm34 });

		Menu menu = new Menu();
		menu.setButton(new BaseMenu[] { vm1, vm2, vm3 });

		String jsonString = JsonHelper.encodeObject2Json(menu);
		String requestUrl = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		String sRet = WeiXinHttpUtils.httpsRequest(requestUrl, jsonString);
		JSONObject jsonObject = JSONObject.parseObject(sRet);
		if (jsonObject != null) {
			if (jsonObject.getIntValue("errcode") == 0)
				return true;
			else {
				log.error("创建菜单失败：errcode:" + jsonObject.getIntValue("errcode") + ",errmsg:" + jsonObject.getString("errmsg"));
				return false;
			}
		} else {
			return false;
		}
	}

	/***
	 * 删除菜单
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月21日 下午8:27:04
	 * @param accessToken
	 * @return
	 */
	public static boolean deleteMenu(String accessToken) {
		String requestUrl = DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		String sRet = WeiXinHttpUtils.httpsRequest(requestUrl, null);
		JSONObject jsonObject = JSONObject.parseObject(sRet);
		if (jsonObject != null) {
			if (jsonObject.getIntValue("errcode") == 0)
				return true;
			else {
				log.error("删除菜单失败：errcode:" + jsonObject.getIntValue("errcode") + ",errmsg:" + jsonObject.getString("errmsg"));
				return false;
			}
		} else {
			return false;
		}
	}

	/***
	 * 根据经纬度获取地市
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月20日 下午4:15:05
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws IOException
	 */
	public static String getLocationCityId(String latitude, String longitude) throws IOException {
		String url = "http://api.map.baidu.com/geocoder/v2/?ak=t5rMjAYUtTma2RbFinDnG07t&callback=renderReverse&location=" + latitude + "," + longitude + "&output=json&pois=1";
		String jsonstring = WeiXinHttpUtils.get(url);
		JSONObject jsonobj = JSONObject.parseObject(jsonstring);
		String city = jsonobj.getJSONObject("addressComponent").get("city").toString();

		return city;
	}

	/***
	 * 根据手机号获取归属地城市
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月20日 下午4:15:43
	 * @param mobile
	 * @return
	 * @throws IOException
	 */
	public static String getMobileInfo(String mobile) throws IOException {
		String url = "http://apis.juhe.cn/mobile/get?phone=" + mobile + "&key=67b74243fab5fe138f81f0487e8e0685";
		String jsonstring = WeiXinHttpUtils.get(url);
		JSONObject jsonobj = JSONObject.parseObject(jsonstring);
		String city = jsonobj.getJSONObject("result").get("city").toString();
		if (city.indexOf("市") < 0) {
			city += "市";
		}
		return city;
	}

	/**
	 * 获取普通接口访问凭证
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static Token getToken(String appid, String appsecret) {
		Token token = null;
		String requestUrl = TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		// 发起GET请求获取凭证
		JSONObject jsonObject = JSONObject.parseObject(WeiXinHttpUtils.httpsRequest(requestUrl, null));

		if (null != jsonObject) {
			try {
				token = new Token();
				token.setAccess_token(jsonObject.getString("access_token"));
				token.setExpires_id(jsonObject.getIntValue("expires_in"));
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				Logger log1 = LoggerFactory.getLogger(WeiXinUtils.class);
				log1.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return token;
	}

	/***
	 * 获取用户列表
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月22日 下午7:17:37
	 * @param accessToken
	 * @param nextOpenId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static UserList getUserList(String accessToken, String nextOpenId) {
		UserList userList=null;
		String jsonString=WeiXinHttpUtils.httpsRequest(WeiXinUtils.GET_USERLIST_URL.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenId), null);
		if(jsonString!=null&&jsonString.length()>0){
			JSONObject jsonObject=JSON.parseObject(jsonString);
			if(jsonObject!=null){
				userList=new UserList();
				userList.setTotal(jsonObject.getIntValue("total"));
				userList.setCount(jsonObject.getIntValue("count"));
				userList.setNextOpenId(jsonObject.getString("next_openid"));
				JSONObject dataObject=(JSONObject)jsonObject.get("data");
				if(dataObject!=null&&dataObject.getJSONArray("openid")!=null){
					userList.setOpenIdList(JSON.toJavaObject(dataObject.getJSONArray("openid"), List.class));
				}
			}
		}
		return userList;
	}

	/***
	 * 获取用户基本信息
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月22日 下午7:18:00
	 * @param accessToken
	 * @param nextOpenId
	 * @return
	 */
	public static UserInfo getUserInfo(String accessToken, String openId) {
		UserInfo userInfo=null;
		String jsonString=WeiXinHttpUtils.httpsRequest(WeiXinUtils.GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId), null);;
		if(jsonString!=null&&jsonString.length()>0){
			JSONObject jsonObject=JSON.parseObject(jsonString);
			if(jsonObject!=null){
				userInfo=new UserInfo();
				userInfo=JSON.toJavaObject(jsonObject, UserInfo.class);
			}
		}
		return userInfo;
	}

	/***
	 * 发送客服消息
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月25日 下午12:38:27
	 * @param accessToken
	 * @param msg
	 * @return
	 */
	public static boolean sendKfMsg(String accessToken, String msg) {
		String requestUrl = KF_SEND_URL.replace("ACCESS_TOKEN", accessToken);
		String sRet = WeiXinHttpUtils.httpsRequest(requestUrl, msg);
		JSONObject jsonObject = JSONObject.parseObject(sRet);
		if (jsonObject != null) {
			if (jsonObject.getIntValue("errcode") == 0)
				return true;
			else {
				log.error("发送客服失败：errcode:" + jsonObject.getIntValue("errcode") + ",errmsg:" + jsonObject.getString("errmsg"));
				return false;
			}
		} else {
			return false;
		}
	}

	/***
	 * 发送模板消息
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月25日 下午12:38:44
	 * @param accessToken
	 * @param msg
	 * @return
	 */
	public static boolean sendTemplateMsg(String accessToken, String msg) {
		String requestUrl = TEMPLATEMESSAGE_SEND_URL.replace("ACCESS_TOKEN", accessToken);
		String sRet = WeiXinHttpUtils.httpsRequest(requestUrl, msg);
		JSONObject jsonObject = JSONObject.parseObject(sRet);
		if (jsonObject != null) {
			if (jsonObject.getIntValue("errcode") == 0)
				return true;
			else {
				log.error("发送模板消息失败：errcode:" + jsonObject.getIntValue("errcode") + ",errmsg:" + jsonObject.getString("errmsg"));
				return false;
			}
		} else {
			return false;
		}
	}
	
	/***
	 * 下载多媒体文件
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年3月25日 下午2:00:21
	  * @param accessToken
	  * @param mediaId媒体id
	  * @param savePath存储路径
	  * @param saveUrlPath存储的WEB-URL路径
	  * @return
	 */
	public static String downloadMedia(String accessToken,String mediaId,String savePath,String saveUrlPath){
		String dateDir = "/" + FredaUtils.getCurDate();
		File filePath = new File(savePath + dateDir);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		String requestUrl=DOWNLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		try{
			URL url=new URL(requestUrl);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			
			String fileType=conn.getHeaderField("Content-Type");
			String fileExt = "";
			if(fileType.equals("image/jpeg")){
				fileExt=".jpg";
			}else if(fileType.equals("audio/mpeg")){
				fileExt=".mp3";
			}else if(fileType.equals("audio/amr")){
				fileExt=".amr";
			}else if(fileType.equals("video/mp4")){
				fileExt=".mp4";
			}else if(fileType.equals("video/mpeg")){
				fileExt=".mp4";
			}
			String newFileName = "/" + System.nanoTime();
			File uploadFile = new File(savePath + dateDir + newFileName + fileExt);
			BufferedInputStream bis=new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos=new FileOutputStream(uploadFile);
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();
			fos = null;
			bis = null;
			conn.disconnect();
			conn=null;
			
			return saveUrlPath+ dateDir + newFileName + fileExt;
		}catch(Exception e){
		}
		return null;
	}
	
	/***
	 * 上传多媒体文件
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年3月25日 下午3:38:17
	  * @param accessToken
	  * @param type类型
	  * @param mediaFileUrl媒体web路径
	  * @return
	 */
	public static Media uploadMedia(String accessToken,String type,String mediaFileUrl){
		String requestUrl=UPLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		try{
			String boundary="------------7da2e536604c8";
			URL uploadUrl=new URL(requestUrl);
			HttpURLConnection uploadConn=(HttpURLConnection)uploadUrl.openConnection();
			uploadConn.setDoInput(true);
			uploadConn.setDoOutput(true);
			uploadConn.setRequestMethod("POST");
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;bounddary="+boundary);
			OutputStream outputStream=uploadConn.getOutputStream();
			
			URL mediaUrl=new URL(mediaFileUrl);
			HttpURLConnection mediaConn=(HttpURLConnection)mediaUrl.openConnection();
			mediaConn.setDoOutput(true);
			mediaConn.setRequestMethod("GET");
			String fileType=mediaConn.getHeaderField("Content-Type");
			String fileExt = "";
			if(fileType.equals("image/jpeg")){
				fileExt=".jpg";
			}else if(fileType.equals("audio/mpeg")){
				fileExt=".mp3";
			}else if(fileType.equals("audio/amr")){
				fileExt=".amr";
			}else if(fileType.equals("video/mp4")){
				fileExt=".mp4";
			}else if(fileType.equals("video/mpeg")){
				fileExt=".mp4";
			}
			
			outputStream.write(("--"+boundary+"\r\n").getBytes());
			outputStream.write(String.format("Content-Disposition:form-data;name=\"media\";filename=\"file1%s\"\r\n", fileExt).getBytes());
			outputStream.write(String.format("Content-Type:%s\r\n\r\n", fileType).getBytes());
			
			BufferedInputStream bis=new BufferedInputStream(mediaConn.getInputStream());
			byte[] buf=new byte[8096];
			int size=0;
			while((size=bis.read(buf))!=-1){
				outputStream.write(buf,0,size);
			}
			bis.close();
			bis=null;
			mediaConn.disconnect();
			mediaConn=null;
			
			outputStream.write(("\r\n--"+boundary+"--\r\n").getBytes());
			outputStream.close();
			outputStream=null;
			
			InputStream inputStream=uploadConn.getInputStream();
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
			StringBuffer sb=new StringBuffer();
			String str=null;
			while((str=bufferedReader.readLine())!=null){
				sb.append(str);
			}
			bufferedReader.close();
			bufferedReader=null;
			inputStreamReader.close();
			inputStreamReader=null;
			inputStream.close();
			inputStream=null;
			
			uploadConn.disconnect();
			uploadConn=null;
			
			if(sb.toString().trim().length()<1){
				return null;
			}
			JSONObject jsonObject=JSONObject.parseObject(sb.toString());
			Media media=new Media();
			media.setType(jsonObject.getString("type"));
			if(type.equals("thumb"))
				media.setMediaId(jsonObject.getString("thumb_media_id"));
			else
				media.setMediaId(jsonObject.getString("media_id"));
			media.setCreateAt(jsonObject.getInteger("created_at"));
			return media;
		}catch(Exception e){
		}
		return null;
	}
}

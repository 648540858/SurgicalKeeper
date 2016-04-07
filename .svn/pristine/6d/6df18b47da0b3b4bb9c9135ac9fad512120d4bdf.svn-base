package com.rjkx.sk.system.utils;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;

/**`
 * WEB 
 * @author Rally
 *
 */
public class WebUtil {

	/**
	 * 获取一个Session属性对象
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	public static Object getSessionAttribute(HttpServletRequest request,String sessionKey) 
	{
		Object objSessionAttribute = null;
		
		HttpSession session = request.getSession(false);
		
		if (session != null) 
		{
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}

	/**
	 * 设置一个Session属性对象
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	public static void setSessionAttribute(HttpServletRequest request,String sessionKey, Object objSessionAttribute) 
	{
		HttpSession session = request.getSession();
		
		if (session != null)
		{
			session.setAttribute(sessionKey, objSessionAttribute);			
		}
	}

	/**
	 * 移除Session对象属性值
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	public static void removeSessionAttribute(HttpServletRequest request,String sessionKey) 
	{
		HttpSession session = request.getSession();
		
		if (session != null)
		{
			session.removeAttribute(sessionKey);			
		}
	}

	/**
	 * 将请求参数封装为Dto
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Dto getPraramsAsDto(HttpServletRequest request) 
	{
		Dto dto = new BaseDto();
		Map map = request.getParameterMap();
		Iterator keyIterator = map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = ((String[]) (map.get(key)))[0];
			dto.put(key, value);
		}
		return dto;
	}
	
	public static String getIpAddr(ServletRequest httpServletRequest) {
		HttpServletRequest request=(HttpServletRequest)httpServletRequest;
		String ip = request.getHeader("HTTP_CLIENT_IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}

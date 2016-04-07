package com.rjkx.sk.system.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {

	private static Log log = LogFactory.getLog(SessionFilter.class);

	private final static String[] needfilterExt = { ".do", ".freda" };

	private final static String[] notNeedfilterExt = { "/index/indexPageInit.freda", "/index/login.freda", "/loginIn.do", "/loginOut.do", "/appItf/", "/wxItfV2/", "/appAuth/", "/pingpp/", "/pushwx/", "/commonUpload", "/commonAdminTree" };

	public SessionFilter()
	{
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession();

		Object obj = session.getAttribute(SystemCons.ADMIN_SESSION_USER_VAR);

		String uri = httpRequest.getRequestURI();

		if (isNeedFilter(uri)) {
			if (session == null || obj == null) {
				if (httpRequest.getHeader("x-requested-with") != null && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					httpResponse.setHeader("sessionstatus", "timeout");
					return;
				} else {
					log.info("Session过期重定向至首页!");
					httpResponse.sendRedirect(httpRequest.getContextPath() + SystemCons.MANAGER_INDEX_URL);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	/**
	 * 判断是否需要
	 * 
	 * @param uri
	 * @return
	 */
	private Boolean isNeedFilter(String uri) {
		for (String s : needfilterExt) {
			if (uri.endsWith(s)) {
				for (String s2 : notNeedfilterExt) {
					if (uri.indexOf(s2) != -1) {
						return false;
					}
				}
			}
		}
		return true;
	}

}

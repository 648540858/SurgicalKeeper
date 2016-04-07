package com.rjkx.sk.system.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetCharacterEncodingFilter implements Filter {

	protected String encoding = null;
	
	protected FilterConfig filterConfig = null;

	@Override
	public void destroy() 
	{
		this.encoding = null;
		
		this.filterConfig = null;
	}
	
	/**
	 *设置接收和响应编码
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		String encoding = selectEncoding(request);
		
		if (encoding != null)
		{
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}
	
	/**
	 *初始化参数
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		
		this.filterConfig = filterConfig;
		
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	protected String selectEncoding(ServletRequest request) 
	{
		return (this.encoding);
	}

}

package com.rjkx.sk.admin.core.web;

import org.springframework.stereotype.Service;

import com.rjkx.sk.admin.common.context.FredaContext;
import com.rjkx.sk.system.web.base.BaseServiceImpl;

/**
 * 
 * @author Rally
 *
 */

@Service(value="adminBaseServiceImpl")
public class AdminBaseServiceImpl extends BaseServiceImpl
{
	/**
	 * 获取接口上下文
	 * @return
	 */
	public FredaContext getFredaContext()
	{
		return FredaContext.getInstance();
	}
}

package com.rjkx.sk.admin.core.web;
/**
 * @author Rally
 */
import org.springframework.stereotype.Controller;

import com.rjkx.sk.admin.common.context.FredaContext;
import com.rjkx.sk.system.web.base.BaseController;

@Controller
public class AdminBaseController extends BaseController
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

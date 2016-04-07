package com.rjkx.sk.manager.base;
/**
 * @author Rally
 */
import org.springframework.stereotype.Controller;

import com.rjkx.sk.admin.common.context.FredaContext;
import com.rjkx.sk.system.web.base.BaseController;

@Controller
public class ManagerBaseController extends BaseController
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

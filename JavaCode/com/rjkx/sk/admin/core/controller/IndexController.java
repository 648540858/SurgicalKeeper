package com.rjkx.sk.admin.core.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.admin.core.service.IndexServiceItf;
import com.rjkx.sk.admin.core.utils.IndexCons;
import com.rjkx.sk.admin.core.vo.AdminUserVo;
import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;
import com.rjkx.sk.system.utils.WebUtil;

/**
 * 
 * @author Rally
 *
 */
@Controller
@RequestMapping(value="/index")
public class IndexController extends AdminBaseController
{
	private IndexServiceItf indexService;
	
	/**
	 * index页面初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/indexPageInit")
	public String indexPageInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		return SystemCons.SYSTEM_INDEX_PAGE_PATH;
	}
	/**
	 * 主页面初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mainPageInit")
	public String mainPageInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		return SystemCons.SYSTEM_MAIN_PAGE_PATH;
	}
	/**
	 * tab页面初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/tabPageInit")
	public String tabPageInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto rDto = (Dto) super.getFredaReader().queryForObject("Index.getMenuRequest", super.getParamsAsDto(request));
		
		String reqUrl = rDto.getAsString("mUrl");
		
		if(FredaUtils.isNotEmpty(reqUrl) && reqUrl.indexOf(".js",reqUrl.length()-3) != -1 )
		{
			request.setAttribute(SystemCons.REQUEST_TAB_URL_VAR, reqUrl);
			
			return SystemCons.SYSTEM_TAB_PAGE_PATH;
		}
		else
		{
			return SystemCons.TO_CONTROLLER_HEAD + reqUrl;
		}
		
	}
	/**
	 * 工作台页面初始化
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myBenchPageInit")
	public String myBenchPageInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		return SystemCons.SYSTEM_BENCH_PAGE_PATH;
	}
	
	/**
	 * 根据权限加载菜单选项
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/menuPanelInit")
	public String menuPanelInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		pDto.put(systemProperties.getValue("sys.var.user.id"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());
		
		pDto.put(systemProperties.getValue("sys.var.user.type"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuType());
		
		super.write(JsonHelper.encodeObject2Json(indexService.getMenuPanel(pDto)), response);
		
		return null;
	}
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		AdminUserVo user = indexService.queryUser(super.getParamsAsDto(request));
		
		if(FredaUtils.isEmpty(user))
		{
			super.setErrTipMsg(IndexCons.TIPS_LOGIN_USER_EMPTY, response);
		}
		else if(FredaUtils.isNotEmpty(user) && user.getuState() == 0)
		{
			super.setErrTipMsg(IndexCons.TIPS_LOGIN_STATE_OFF, response);
		}
		else if(FredaUtils.isNotEmpty(user) && user.getuLocked() == 0)
		{
			super.setErrTipMsg(IndexCons.TIPS_LOGIN_LOCKED_OFF, response);
		}
		else
		{
			user.setIpAddr(WebUtil.getIpAddr(request));
			
			user.setExplorerType(FredaUtils.getClientExplorerType(request));
			
			super.setSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR, user);
			
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		}
		
		return null;
	}
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.removeSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR);
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * 更改密码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/changePwd")
	public String changePwd(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		pDto.put(systemProperties.getValue("sys.var.user.id"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());
		
		if(indexService.editPwd(pDto))
		{
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);			
		}
		else
		{
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);;
		}
		return null;
	}
	
	
	public IndexServiceItf getIndexService() 
	{
		return indexService;
	}
	@Resource(name="indexServiceImpl")
	public void setIndexService(IndexServiceItf indexService) 
	{
		this.indexService = indexService;
	}
	
	
}

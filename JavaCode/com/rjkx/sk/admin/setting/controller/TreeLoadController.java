package com.rjkx.sk.admin.setting.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.admin.setting.service.TreeLoadServiceItf;
import com.rjkx.sk.system.json.JsonHelper;

/**
 * TREE
  * @ClassName: TreeLoadController
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年9月22日 下午2:59:42 
  * @version V1.0
 */
@Controller
@RequestMapping(value="/treeLoad")
public class TreeLoadController extends AdminBaseController
{
	private TreeLoadServiceItf treeLoadService;
	
	/**
	 * 菜单查看树
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:03:49
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/menuTreeInit")
	public String menuTreeInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		 super.write(JsonHelper.encodeObject2Json(treeLoadService.menuTreeInit(super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * 部门树
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:04:08
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/deptTreeInit")
	public String deptTreeInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(treeLoadService.deptTreeInit(super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * 菜单树附带选择框(绑定Role情况)
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:04:22
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/menuTreeWithSelectedInit")
	public String menuTreeWithSelectedInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(treeLoadService.menuTreeWithSelectedInit(super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * 角色一层树附带选择框(绑定User情况)
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:04:56
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/roleTreeWithSelectedInit")
	public String roleTreeWithSelectedInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(treeLoadService.roleTreeWithSelectedInit(super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * 用户树部门+用户附带选择框(绑定Role情况)
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:05:33
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/userTreeWithSelectedInit")
	public String userTreeWithSelectedInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(treeLoadService.userTreeWithSelectedInit(super.getParamsAsDto(request))), response);
		
		return null;
	}
	
	
	public TreeLoadServiceItf getTreeLoadService() 
	{
		return treeLoadService;
	}
	@Resource(name="treeLoadServiceImpl")
	public void setTreeLoadService(TreeLoadServiceItf treeLoadService) 
	{
		this.treeLoadService = treeLoadService;
	}
}

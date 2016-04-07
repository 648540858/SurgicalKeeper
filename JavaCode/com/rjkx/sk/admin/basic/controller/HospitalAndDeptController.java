package com.rjkx.sk.admin.basic.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rjkx.sk.admin.basic.service.HospitalAndDeptServiceItf;
import com.rjkx.sk.admin.common.utils.BasicUtils;
import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;
/**
 * 医院.科室
 *
  * @ClassName: HospitalController
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月14日 下午8:20:10 
  * @version V1.0
 */
@Controller
@RequestMapping(value="/hosAndDept")
public class HospitalAndDeptController extends AdminBaseController
{
	
	private HospitalAndDeptServiceItf hosAndDeptService;
	/**
	 * LIST HOS
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月14日 下午9:02:58
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listHospital")
	public String listHospital(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("HospitalAndDept.listHospital", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("HospitalAndDept.listHospitalCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * ADD HOS
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月14日 下午9:03:04
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addHospital")
	public String addHospital(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		hosAndDeptService.addHospital(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT HOS
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月14日 下午9:03:12
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editHospital")
	public String editHospital(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		hosAndDeptService.editHospital(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE HOS
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月14日 下午9:03:18
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delHospital")
	public String delHospital(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		hosAndDeptService.delHospital(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * LIST HOS DEPT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月14日 下午9:03:27
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listHosDept")
	public String listHosDept(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("HospitalAndDept.listHosDept", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("HospitalAndDept.listHosDeptCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);
		
		
		return null;
	}
	/**
	 * ADD HOS DEPT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月14日 下午9:03:36
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addHosDept")
	public String addHosDept(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		hosAndDeptService.addHosDept(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT HOS DEPT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月14日 下午9:03:45
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editHosDept")
	public String editHosDept(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		hosAndDeptService.editHosDept(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE HOS DEPT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月14日 下午9:03:58
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delHosDept")
	public String delHosDept(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		hosAndDeptService.delHosDept(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DEPT TO HOS
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月14日 下午9:04:13
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/deptToHospital")
	public String deptToHospital(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		hosAndDeptService.deptToHos(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * CITY TREE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月15日 下午2:06:49
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/cityTreeInit")
	public String cityTreeInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(super.getFredaContext().getBasicDataService().cityTreeInit(super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * 上传LOGO
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月15日 下午3:53:58
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/upLoadHosLogo")
	public String upLoadHosLogo(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("upLoadFile");
		
		String fileLastName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());

		if(BasicUtils.isImg(fileLastName))
		{
			String newFileName = "/HOS_LOG_" + System.nanoTime();
			
			String realPath = appProperties.getValue("app.hos.log.dir");
			
			File filePath = new File(realPath);
			
			if(!filePath.exists())
			{
				filePath.mkdirs();
			}
			File outFile = new File(realPath + newFileName + fileLastName);
			
			FileCopyUtils.copy(file.getBytes(), outFile);
			
			super.setOkTipMsg(appProperties.getValue("app.hos.log.saveDir") + newFileName + fileLastName, response);
		}
		else
		{
			super.setErrTipMsg(SystemCons.TIPS_ERROR_FILE_TYPE_MSG, response);;
		}
		return null;
	}

	public HospitalAndDeptServiceItf getHosAndDeptService() 
	{
		return hosAndDeptService;
	}
	@Resource(name="hospitalAndDeptServiceImpl")
	public void setHosAndDeptService(HospitalAndDeptServiceItf hosAndDeptService) 
	{
		this.hosAndDeptService = hosAndDeptService;
	}
	
	
}

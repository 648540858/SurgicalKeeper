package com.rjkx.sk.admin.cms.controller;

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

import com.rjkx.sk.admin.cms.service.LectureServiceItf;
import com.rjkx.sk.admin.common.utils.BasicUtils;
import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;
/**
 * LECTURE
  * @ClassName: LectureController
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月22日 上午9:20:39 
  * @version V1.0
 */
@Controller
@RequestMapping(value="/lecture")
public class LectureController extends AdminBaseController
{
	
	private LectureServiceItf lectureService;
	
	/**
	 * LIST
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月22日 上午9:20:53
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listLecture")
	public String listLecture(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Lecture.listLecture", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("Lecture.listLectureCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * ADD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月22日 上午9:20:59
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addLecture")
	public String addLecture(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		lectureService.addLecture(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月22日 上午9:21:07
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editLecture")
	public String editLecture(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		lectureService.editLecture(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月22日 上午9:21:18
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delLecture")
	public String delLecture(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		lectureService.delLecture(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
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
	@RequestMapping(value="/upLoadLecturePav")
	public String upLoadLecturePav(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("upLoadFile");
		
		String fileLastName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());

		if(BasicUtils.isImg(fileLastName))
		{
			String newFileName = "/LECTURE_PAV_" + System.nanoTime();
			
			String realPath = appProperties.getValue("app.lecture.pav.dir");
			
			File filePath = new File(realPath);
			
			if(!filePath.exists())
			{
				filePath.mkdirs();
			}
			File outFile = new File(realPath + newFileName + fileLastName);
			
			FileCopyUtils.copy(file.getBytes(), outFile);
			
			super.setOkTipMsg(appProperties.getValue("app.lecture.pav.saveDir") + newFileName + fileLastName, response);
		}
		else
		{
			super.setErrTipMsg(SystemCons.TIPS_ERROR_FILE_TYPE_MSG, response);;
		}
		return null;
	}
	
	public LectureServiceItf getLectureService() 
	{
		return lectureService;
	}
	@Resource(name="lectureServiceImpl")
	public void setLectureService(LectureServiceItf lectureService)
	{
		this.lectureService = lectureService;
	}
	
	
}

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

import com.rjkx.sk.admin.basic.service.DoctorServiceItf;
import com.rjkx.sk.admin.common.utils.BasicUtils;
import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;
/**
 * DOCTOR
  * @ClassName: DoctorController
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月19日 上午10:13:52 
  * @version V1.0
 */
@Controller
@RequestMapping(value="/doctor")
public class DoctorController extends AdminBaseController
{
	private DoctorServiceItf docService;
	
	/**
	 * CITY TREE UNTIL HOS
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午1:30:55
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/cityTreeWithHosInit")
	public String cityTreeWithHosInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(super.getFredaContext().getBasicDataService().cityTreeUntilHosInit(super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * LIST DOCTOR
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午1:32:01
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listAllDoctor")
	public String listAllDoctor(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Doctor.listDoctor", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("Doctor.listDoctorCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * LOAD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午3:13:47
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/loadDoctor")
	public String loadDoctor(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeDto2FormLoadJson((Dto)super.getFredaReader().queryForObject("Doctor.loadDoctorInfo", super.getParamsAsDto(request)), null), response);
		
		return null;
	}
	/**
	 * ADD DOCTOR
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午1:32:19
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addDoctor")
	public String addDoctor(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		docService.addDoctor(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT DOCTOR
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午1:32:30
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editDoctor")
	public String editDoctor(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		docService.editDoctor(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE DOCTOR
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午1:32:40
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delDoctor")
	public String delDoctor(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		docService.delDoctor(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * 科室COMBO
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午2:46:09
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listAllHosDeptForComb")
	public String listAllHosDeptForComb(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(super.getFredaReader().queryForList("Doctor.listHosDeptByHos", super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * UPLOAD PHOTO
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午7:15:26
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/uploadPhoto")
	public String uploadPhoto(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("upLoadFile");
		
		String fileLastName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());

		if(BasicUtils.isImg(fileLastName))
		{
			String newFileName = "/DOCTOR_" + System.nanoTime();
			
			String dateDir = "/" +  FredaUtils.getCurDate();
			
			String realPath = appProperties.getValue("app.doctor.photo.dir");
			
			File filePath = new File(realPath + dateDir);
			
			if(!filePath.exists())
			{
				filePath.mkdirs();
			}
			File outFile = new File(realPath + dateDir + newFileName + fileLastName);
			
			FileCopyUtils.copy(file.getBytes(), outFile);
			
			super.setOkTipMsg(appProperties.getValue("app.doctor.photo.saveDir") + dateDir + newFileName + fileLastName, response);
		}
		else
		{
			super.setErrTipMsg(SystemCons.TIPS_ERROR_FILE_TYPE_MSG, response);;
		}
		return null;
	}
	/**
	 * 获取排期信息..根据医生信息与时间
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月20日 下午3:25:30
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listSchByDocAndDate")
	public String listSchByDocAndDate(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(docService.listSchByDocAndDate(super.getParamsAsDto(request)),SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * ADD SCH
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月20日 下午3:27:09
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addSch")
	public String addSch(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		docService.addSch(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT SCH
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月20日 下午3:27:16
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editSch")
	public String editSch(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		docService.editSch(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE SCH
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月20日 下午3:27:32
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delSch")
	public String delSch(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		docService.delSch(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * ADD SCH FOR MONTH
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月4日 下午2:28:55
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/quickAddSchForMonth")
	public String quickAddSchForMonth(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		docService.addSchForMonth(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	
	public DoctorServiceItf getDocService() 
	{
		return docService;
	}
	@Resource(name="doctorServiceImpl")
	public void setDocService(DoctorServiceItf docService)
	{
		this.docService = docService;
	}
	
	
}

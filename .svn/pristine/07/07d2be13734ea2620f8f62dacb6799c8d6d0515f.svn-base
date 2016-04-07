package com.rjkx.sk.manager.cms.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.cms.service.LectureService;
import com.rjkx.sk.manager.common.utils.BasicUtils;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;

/***
 * 专家视频管理
  * @ClassName: LectureV2Controller
  * @Description: 
  * @author yiyuan-LiChun
  * @date 2016年2月11日 上午9:30:40 
  * @version V2.0
 */
@Controller
@RequestMapping(value="/lecturev2")
public class LectureV2Controller extends ManagerBaseController {
	@Autowired
	@Resource(name="lectureV2ServiceImpl")
	private LectureService lectureService;

	/**
	 * LIST 视频
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listLecture")
	public String listLecture(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("LectureV2.listLecture", pDto);

		Integer count = (Integer) super.getFredaReader().queryForObject("LectureV2.listLectureCount", pDto);

		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}

	/**
	 * 添加 视频
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addLecture")
	public String addLecture(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.lectureService.addLecture(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT 视频
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editLecture")
	public String editLecture(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.lectureService.editLecture(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE 视频
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delLecture")
	public String delLecture(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.lectureService.delLecture(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 查询单条信息
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getLecture")
	public String getLecture(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("LectureV2.getLecture", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}

	/**
	 * 上传LOGO
	 * 
	 * @author 潘林林
	 * @date 2015年10月15日 下午3:53:58
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadPhoto")
	public String uploadPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("fileIcon");
		String fileLastName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
		if (BasicUtils.isImg(fileLastName)) {
			String newFileName = "lec_" + System.nanoTime();
			String dateDir = FredaUtils.getCurDate();
			String realPath = appProperties.getValue("app.lecture.pav.dir");
			File filePath = new File(realPath +File.separatorChar+ dateDir);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File outFile = new File(realPath +File.separatorChar+ dateDir+File.separatorChar + newFileName + fileLastName);

			FileCopyUtils.copy(file.getBytes(), outFile);
			super.setOkTipMsg(appProperties.getValue("app.lecture.pav.saveDir") +"/"+ dateDir +"/"+ newFileName + fileLastName, response);
		} else {
			super.setErrTipMsg(SystemCons.TIPS_ERROR_FILE_TYPE_MSG, response);
		}
		return null;
	}

}

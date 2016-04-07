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
import com.rjkx.sk.manager.cms.service.AdService;
import com.rjkx.sk.manager.common.utils.BasicUtils;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.FileUtil;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 广告内容管理
 * 
 * @ClassName: ADmanagerController
 * @author:panlinlin
 * @data:2016年3月2日
 * @Description:
 * @author: pan
 * @date: 2016年3月2日 下午4:13:32
 */
@Controller
@RequestMapping(value = "/aDmanager")
public class ADmanagerController extends ManagerBaseController {
	@Autowired
	@Resource(name = "adServiceImpl")
	private AdService adService;

	/**
	 * 查询广告内容列表
	 * 
	 * @Title: listAd
	 * @author:panlinlin
	 * @data:2016年3月2日
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/listAd")
	public String listAd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("ad.listAdAll", pDto);

		Integer count = (Integer) super.getFredaReader().queryForObject("ad.listAdAllCount", pDto);

		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}

	/**
	 * 查询单条信息
	 * 
	 * @Title: getLecture
	 * @author:panlinlin
	 * @data:2016年3月2日
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/queryAD")
	public String queryAD(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("ad.queryAD", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}

	/**
	 * 图片上传
	 * 
	 * @Title: uploadPhoto
	 * @author:panlinlin
	 * @data:2016年3月2日
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/uploadAdImg")
	public String uploadAdImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("fileIcon");
		String fileLastName = FileUtil.getExtension(file.getOriginalFilename());
		if (BasicUtils.isImg(fileLastName)) {
			String newFileName = "ad_" + System.nanoTime();
			String dateDir = FredaUtils.getCurDate();
			String realPath = appProperties.getValue("app.ad.photo.dir");
			File filePath = new File(realPath +File.separatorChar+ dateDir);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File outFile = new File(realPath + File.separatorChar+dateDir +File.separatorChar+ newFileName + "." +fileLastName);

			FileCopyUtils.copy(file.getBytes(), outFile);
			super.setOkTipMsg(appProperties.getValue("app.ad.photo.saveDir") +"/"+ dateDir +"/"+ newFileName + fileLastName, response);
		} else {
			super.setErrTipMsg(SystemCons.TIPS_ERROR_FILE_TYPE_MSG, response);
		}
		return null;
	}

	/**
	 * 添加广告
	 * 
	 * @Title: addHospital
	 * @author:panlinlin
	 * @data:2016年3月2日
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/addAd")
	public String addAd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			adService.addAd(super.getParamsAsDto(request));
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		} catch (Exception e) {
			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 编辑广告
	 * 
	 * @Title: editAd
	 * @author:panlinlin
	 * @data:2016年3月2日
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/editAd")
	public String editAd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			adService.editAd(super.getParamsAsDto(request));
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		} catch (Exception e) {
			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除广告
	 * 
	 * @Title: deletAd
	 * @author:panlinlin
	 * @data:2016年3月2日
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/deletAd")
	public String deletAd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			adService.deletAd(super.getParamsAsDto(request));
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		} catch (Exception e) {
			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);
			e.printStackTrace();
		}
		return null;
	}
}

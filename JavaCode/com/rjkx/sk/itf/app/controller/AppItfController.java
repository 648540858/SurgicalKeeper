package com.rjkx.sk.itf.app.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rjkx.sk.admin.common.utils.BasicUtils;
import com.rjkx.sk.itf.app.pojo.ErrorMsg;
import com.rjkx.sk.itf.app.pojo.ResultMsg;
import com.rjkx.sk.itf.app.service.AppItfService;
import com.rjkx.sk.itf.common.web.AppBaseController;
import com.rjkx.sk.manager.common.context.FredaContext;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.FileUtil;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;

/***
 * APP接口
 * 
 * @ClassName: AppItfController
 * @Description:
 * @author yiyuan-LiChun
 * @date 2016年3月1日 下午3:49:29
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/appItf")
public final class AppItfController extends AppBaseController {
	@Autowired
	@Resource(name = "appItfServiceImpl")
	private AppItfService appItfService;

	/***
	 * 登录
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月1日 下午3:35:01
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/loginIn")
	public String loginIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (FredaUtils.isEmpty(pDto.getAsString("mobile")) || FredaUtils.isEmpty(pDto.getAsString("pwd")) || FredaUtils.isEmpty(pDto.getAsString("usertype")) || FredaUtils.isEmpty(pDto.getAsString("clientid")) || FredaUtils.isEmpty(pDto.getAsString("imeiStr"))) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsInteger("usertype") != 3 && pDto.getAsInteger("usertype") != 4) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Dto rDto = (Dto) super.getFredaReader().queryForObject("AppV1.doctorLogin", pDto);
		if (FredaUtils.isEmpty(rDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-5);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		pDto.put("userid", rDto.getAsLong("docId"));
		this.appItfService.appRegClient(pDto);
		// 添加app信息
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 退出
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月2日 上午9:41:46
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/loginOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (FredaUtils.isEmpty(pDto.getAsString("imeiStr")) || FredaUtils.isEmpty(pDto.getAsString("clientid"))) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		// 删除app信息
		this.appItfService.appUnRegClient(pDto);

		ResultMsg result = new ResultMsg();
		result.setCode(0);
		Dto rDto = new BaseDto();
		rDto.put("msg", "OK");
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 获取验证码
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月9日 下午3:41:38
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAuthCode")
	public String getAuthCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("mobile").length() != 11) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		String code = FredaUtils.createRandom(true,6);
		// 发送短信
		FredaContext.getInstance().getSmsService().sendVerCodeSMS(pDto.getAsString("mobile"), code);

		ResultMsg result = new ResultMsg();
		result.setCode(0);
		Dto reDto = new BaseDto();
		reDto.put("msg", "OK");
		reDto.put("code", code);
		result.setData(reDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 找回密码
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月2日 上午9:55:48
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fogotPwd")
	public String fogotPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("mobile").length() != 11 || pDto.getAsString("usertype").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Dto rDto = null;
		if (pDto.getAsInteger("usertype") == 3) {
			rDto = (Dto) super.getFredaReader().queryForObject("AppV1.getDoctorPwd", pDto);
		}
		// if(pDto.getAsInteger("usertype")==4){
		// rDto=(Dto)super.getFredaReader().queryForObject("AppV1.getPatientPwd",pDto);
		// }
		if (FredaUtils.isEmpty(rDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		// 发送短信
		FredaContext.getInstance().getSmsService().sendPwdSMS(pDto.getAsString("mobile"), rDto.getAsString("pwd"));

		ResultMsg result = new ResultMsg();
		result.setCode(0);
		Dto reDto = new BaseDto();
		reDto.put("msg", "OK");
		result.setData(reDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 更新头像
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月10日 下午3:20:36
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateHeadPhoto")
	public String updateHeadPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1||multipartRequest==null) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("fileIcon");
		if(file==null||file.getOriginalFilename().length()<1){
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		
		String imgUrl = "";
		String fileExt = FileUtil.getExtension(file.getOriginalFilename());// file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
		if (BasicUtils.isImg(fileExt)) {
			String newFileName = "doctor_" + System.nanoTime();
			String dateDir = FredaUtils.getCurDate();
			String realPath = appProperties.getValue("app.doctor.photo.dir");
			File filePath = new File(realPath +File.separatorChar+ dateDir);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File outFile = new File(realPath +File.separatorChar+ dateDir +File.separatorChar+ newFileName + fileExt);

			FileCopyUtils.copy(file.getBytes(), outFile);
			imgUrl = appProperties.getValue("app.doctor.photo.saveDir") +"/"+ dateDir +"/"+ newFileName +fileExt;
		}
		if (imgUrl.length() > 0) {
			pDto.put("imgUrl", imgUrl);
			this.appItfService.updateHeadPhoto(pDto);
			
			Dto rDto = new BaseDto();
			rDto.put("imgUrl", imgUrl);
			ResultMsg result = new ResultMsg();
			result.setCode(0);
			result.setData(rDto);
			super.write(JsonHelper.encodeObject2Json(result), response);
		} else {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-2);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
		}
		return null;
	}

	/***
	 * 查询广告条
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月2日 上午11:19:34
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryNewsAdvert")
	public String queryNewsAdvert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1||pDto.getAsString("usertype").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		List<?> data = super.getFredaReader().queryForList("AppV1.queryNewsAdvert", pDto);
		if (FredaUtils.isEmpty(data)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Dto rDto = new BaseDto();
		rDto.put("adv", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 查询医生订单
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月3日 下午1:17:23
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryDoctorOrder")
	public String queryDoctorOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("page").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		pDto.put("limit", 20);
		if(pDto.getAsInteger("page")<1){
			pDto.put("page", 1);
		}
		pDto.put("start", (pDto.getAsInteger("page")-1)*20);
		List<?> data = super.getFredaReader().queryForList("AppV1.queryDoctorOrder", pDto);
		if (data.size() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Integer count = (Integer) super.getFredaReader().queryForObject("AppV1.queryDoctorOrderCount", pDto);

		Dto rDto = new BaseDto();
		rDto.put("totalcount", count);
		rDto.put("order", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result,SystemCons.DATE_TIME_FORMART_1), response);
		return null;
	}

	/***
	 * 查询医生当天订单
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月3日 下午1:17:23
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryDoctorTodayOrder")
	public String queryDoctorTodayOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		List<?> data = super.getFredaReader().queryForList("AppV1.queryDoctorTodayOrder", pDto);
		if (data.size() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Dto rDto = new BaseDto();
		rDto.put("totalcount", data.size());
		rDto.put("order", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result,SystemCons.DATE_TIME_FORMART_2), response);
		return null;
	}

	/***
	 * 获取热门内容
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年3月11日 上午10:05:45
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryHotsNews")
	public String queryHotsNews(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("msgtype").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		List<?> data = super.getFredaReader().queryForList("AppV1.queryHotsNewsList", pDto);
		if (data.size() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Dto rDto = new BaseDto();
		rDto.put("list", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result,SystemCons.DATE_TIME_FORMART_1), response);
		return null;
	}
	
	/***
	 * 查询圈子内容列表
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月3日 下午3:43:26
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryNewsList")
	public String queryNewsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("msgtype").length() < 1 || pDto.getAsString("page").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		pDto.put("limit", 20);
		if(pDto.getAsInteger("page")<1){
			pDto.put("page", 1);
		}
		pDto.put("start", (pDto.getAsInteger("page")-1)*20);
		List<?> data = super.getFredaReader().queryForList("AppV1.queryNewsList", pDto);
		if (data.size() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Integer count = (Integer) super.getFredaReader().queryForObject("AppV1.queryNewsListCount", pDto);

		Dto rDto = new BaseDto();
		rDto.put("totalcount", count);
		rDto.put("list", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result,SystemCons.DATE_TIME_FORMART_1), response);
		return null;
	}

	/***
	 * 删除圈子内容
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 上午10:30:18
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delNews")
	public String delNews(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("msgId").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		int ret=this.appItfService.delNews(pDto);
		if(ret>0){
			Dto rDto = new BaseDto();
			rDto.put("msg", "OK");
			ResultMsg result = new ResultMsg();
			result.setCode(0);
			result.setData(rDto);
			super.write(JsonHelper.encodeObject2Json(result), response);
		}else{
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-2);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
		}
		return null;
	}

	/***
	 * 查询某人发布的圈子内容列表
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月3日 下午3:43:26
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryNewsListForUser")
	public String queryNewsListForUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("msgtype").length() < 1 || pDto.getAsString("page").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		pDto.put("limit", 20);
		if(pDto.getAsInteger("page")<1){
			pDto.put("page", 1);
		}
		pDto.put("start", (pDto.getAsInteger("page")-1)*20);
		List<?> data = super.getFredaReader().queryForList("AppV1.queryNewsListForUserid", pDto);
		if (data.size() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Integer count = (Integer) super.getFredaReader().queryForObject("AppV1.queryNewsListCountForUserid", pDto);

		Dto rDto = new BaseDto();
		rDto.put("totalcount", count);
		rDto.put("list", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result,SystemCons.DATE_TIME_FORMART_1), response);
		return null;
	}
	
	/***
	 * 查询单条圈子内容
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年3月15日 下午3:28:47
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getNews")
	public String getNews(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("msgId").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		List<?> data = super.getFredaReader().queryForList("AppV1.queryNews", pDto);
		if (data.size() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}

		Dto rDto = new BaseDto();
		rDto.put("list", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result,SystemCons.DATE_TIME_FORMART_1), response);
		return null;
	}
	
	/***
	 * 查询金币量
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月3日 下午4:45:34
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryMoney")
	public String queryMoney(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Dto rDto = (Dto) super.getFredaReader().queryForObject("AppV1.queryMoneyForUserid", pDto);
		if (FredaUtils.isEmpty(rDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}

		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 查询圈子收藏列表
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月3日 下午3:43:26
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryNewsFavList")
	public String queryNewsFavList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("page").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		pDto.put("limit", 20);
		if(pDto.getAsInteger("page")<1){
			pDto.put("page", 1);
		}
		pDto.put("start", (pDto.getAsInteger("page")-1)*20);
		List<?> data = super.getFredaReader().queryForList("AppV1.queryNewsFavList", pDto);
		if (data.size() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-10);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		Integer count = (Integer) super.getFredaReader().queryForObject("AppV1.queryNewsFavListCount", pDto);

		Dto rDto = new BaseDto();
		rDto.put("totalcount", count);
		rDto.put("list", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result,SystemCons.DATE_TIME_FORMART_1), response);
		return null;
	}

	/***
	 * 删除收藏
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 上午10:23:27
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delNewsFav")
	public String delNewsFav(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("favId").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		int ret=this.appItfService.delFavNews(pDto);
		
		if(ret>0){
			ResultMsg result = new ResultMsg();
			Dto rDto = new BaseDto();
			rDto.put("msg", "OK");
			result.setCode(0);
			result.setData(rDto);
			super.write(JsonHelper.encodeObject2Json(result), response);
		}else{
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-2);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
		}		
		return null;
	}

	/***
	 * 添加意见反馈
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月3日 下午3:04:24
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addFeedBack")
	public String addFeedBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("content").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		this.appItfService.addFeedBack(pDto);

		Dto rDto = new BaseDto();
		rDto.put("msg", "OK");
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 上传文件返回文件路径
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月3日 下午4:14:13
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadFile")
	public String uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		List<MultipartFile> files=multipartRequest.getFiles("fileup");
		if(files==null||files.isEmpty()){
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		
		List<Dto> data = new ArrayList<Dto>();
		for(MultipartFile file:files){
			if(file!=null&&file.getOriginalFilename().length()>0){
				String fileExt = FileUtil.getExtension(file.getOriginalFilename());
	
				String newFileName = System.nanoTime()+"";
				String dateDir = FredaUtils.getCurDate();
				String realPath = appProperties.getValue("app.upload.other.dir");
				File filePath = new File(realPath +File.separatorChar+ dateDir);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				File uploadFile = new File(realPath +File.separatorChar+ dateDir +File.separatorChar+ newFileName + fileExt);
				file.transferTo(uploadFile); 
				
				Dto dto = new BaseDto();
				dto.put("fileUrl", appProperties.getValue("app.upload.other.saveDir") +"/"+dateDir +"/"+ newFileName + fileExt);
				data.add(dto);
			}
		}
		Dto rDto = new BaseDto();
		rDto.put("filelist", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);

		return null;
	}

	/***
	 * 圈子内容点赞
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 上午10:55:39
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addNewsZan")
	public String addNewsZan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("msgId").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		this.appItfService.addNewsZan(pDto);

		Dto rDto = new BaseDto();
		rDto.put("msg", "OK");
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 圈子点收藏
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 上午11:00:15
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addNewsFav")
	public String addNewsFav(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("msgId").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		this.appItfService.addNewsFav(pDto);

		Dto rDto = new BaseDto();
		rDto.put("msg", "OK");
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 更新圈子内容
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 下午2:53:05
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateNews")
	public String updateNews(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("msgId").length() < 1 || pDto.getAsString("title").length() < 1 || pDto.getAsString("content").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		this.appItfService.updateNews(pDto);

		Dto rDto = new BaseDto();
		rDto.put("msg", "OK");
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 添加圈子内容
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 下午3:11:08
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addNews")
	public String addNews(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("msgType").length() < 1 || pDto.getAsString("title").length() < 1 || pDto.getAsString("content").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		this.appItfService.addNews(pDto);

		Dto rDto = new BaseDto();
		rDto.put("msg", "OK");
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 增加圈子内容阅读量
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 下午3:21:07
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addNewsRead")
	public String addNewsRead(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("msgId").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		long readCount = this.appItfService.addNewsRead(pDto);

		Dto rDto = new BaseDto();
		rDto.put("msg", "OK");
		rDto.put("readCount", readCount);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 添加圈子回复
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 下午4:49:29
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addNewsReply")
	public String addNewsReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("msgId").length() < 1 || pDto.getAsString("content").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		this.appItfService.addNewsReply(pDto);

		Dto rDto = new BaseDto();
		rDto.put("msg", "OK");
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result), response);
		return null;
	}

	/***
	 * 删除圈子回复
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 下午4:50:21
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delNewsReply")
	public String delNewsReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("msgId").length() < 1 || pDto.getAsString("replyId").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		int ret=this.appItfService.delNewsReply(pDto);
		if(ret>0){
			Dto rDto = new BaseDto();
			rDto.put("msg", "OK");
			ResultMsg result = new ResultMsg();
			result.setCode(0);
			result.setData(rDto);
			super.write(JsonHelper.encodeObject2Json(result), response);
		}else{
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-2);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
		}
		return null;
	}

	/***
	 * 获取圈子回复列表
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年3月7日 下午4:54:37
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getNewsReplyList")
	public String getNewsReplyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (FredaUtils.isEmpty(pDto)) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-6);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		if (pDto.getAsString("userid").length() < 1 || pDto.getAsString("usertype").length() < 1 || pDto.getAsString("msgId").length() < 1) {
			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setCode(-4);
			super.write(JsonHelper.encodeObject2Json(errMsg), response);
			return null;
		}
		List<?> data = super.getFredaReader().queryForList("AppV1.findNewsReplyList", pDto);

		Dto rDto = new BaseDto();
		rDto.put("replyList", data);
		ResultMsg result = new ResultMsg();
		result.setCode(0);
		result.setData(rDto);
		super.write(JsonHelper.encodeObject2Json(result, SystemCons.DATE_TIME_FORMART_1), response);
		return null;
	}
}

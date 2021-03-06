package com.rjkx.sk.manager.basedata.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.basedata.service.SicknessManagerService;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 
 *
 * @ClassName: 
 * @Description: 疾病.属性
 * @author lichun
 * @date 2016年2月7日 下午8:20:10
 * @version V2
 */

@Controller
@RequestMapping(value = "/SicknessAndattr")
public class SicknessManagerController extends ManagerBaseController {
	@Autowired
	@Resource(name="sicknessManagerServiceImpl")
	private SicknessManagerService sicknessManagerService;

	/**
	 * LIST 疾病属性
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listSicknessAttr")
	public String listSicknessAttr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("SicknessAttr.listSicknessAttr", pDto);

		super.write("{\"ROOT\":"+JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART)+"}", response);

		return null;
	}

	/**
	 * 添加疾病属性
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSicknessAttr")
	public String addSicknessAttr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.addSicknessAttr(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT 疾病属性
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editSicknessAttr")
	public String editSicknessAttr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.editSicknessAttr(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE 疾病属性
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delSicknessAttr")
	public String delSicknessAttr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.delSicknessAttr(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 查询单条疾病属性信息
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSicknessAttr")
	public String getSicknessAttr(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("SicknessAttr.getSicknessAttr", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}
	
	/**
	 * LIST 疾病
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listSickness")
	public String listSickness(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("SicknessAttr.listSickness", pDto);

		super.write("{\"ROOT\":"+JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART)+"}", response);

		return null;
	}

	/**
	 * 添加疾病
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSickness")
	public String addSickness(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.addSickness(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT 疾病
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editSickness")
	public String editSickness(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.editSickness(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE 疾病
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delSickness")
	public String delSickness(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.delSickness(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 查询单条疾病信息
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSickness")
	public String getSickness(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("SicknessAttr.getSickness", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}
	
	/**
	 * LIST 疾病子类
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listSicknessSub")
	public String listSicknessSub(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("SicknessAttr.listSicknessSub", pDto);

		super.write("{\"ROOT\":"+JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART)+"}", response);

		return null;
	}

	/**
	 * add 疾病子类
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSicknessSub")
	public String addSicknessSub(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.addSicknessSub(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT 疾病子类
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editSicknessSub")
	public String editSicknessSub(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.editSicknessSub(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE 疾病子类
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delSicknessSub")
	public String delSicknessSub(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.delSicknessSub(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 查询单条疾病子类信息
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSicknessSub")
	public String getSicknessSub(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("SicknessAttr.getSicknessSub", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}
	
	/**
	 * 查询推荐医生
	 * @Title: listYyDoc 
	 * @author:panlinlin
	 * @data:2016年3月28日
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/listYyDoc")
	public String listYyDoc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		List<?> data =null;
		//0表示子类疾病推荐医生
		if(pDto.getAsInteger("type")==0){
			data = super.getFredaReader().queryForList("SicknessAttr.listSubYyDoc", pDto);
		}else{
			data = super.getFredaReader().queryForList("SicknessAttr.listYyDoc", pDto);
		}
		super.write("{\"ROOT\":"+JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART)+"}", response);

		return null;
	}
	
	/**
	 * 删除推荐医生
	 * @Title: deleteYyDoc 
	 * @author:panlinlin
	 * @data:2016年3月28日
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/deleteYyDoc")
	public String deleteYyDoc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		sicknessManagerService.deleteYyDoc(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}
	
	@RequestMapping(value = "/addYyDoc")
	public String addYyDoc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sicknessManagerService.addYyDoc(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}
}

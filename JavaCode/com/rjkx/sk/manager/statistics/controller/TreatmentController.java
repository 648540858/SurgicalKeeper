package com.rjkx.sk.manager.statistics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 就诊信息统计
 * @ClassName: TreatmentController
 * @author:panlinlin
 * @data:2016年3月11日
 * @Description: TODO
 * @author: pan
 * @date: 2016年3月11日 上午9:55:23
 */
@Controller
@RequestMapping(value="/treatment")
public class TreatmentController extends ManagerBaseController{
	
	@RequestMapping(value = "/listTreatment")
	public String listApptOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("Treatment.listTreatment", pDto);

		Integer count = (Integer) super.getFredaReader().queryForObject("Treatment.listTreatmentCount", pDto);

		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}

	
	@RequestMapping(value = "/getCity")
	public String getCity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("Treatment.queryCity", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}
	
	@RequestMapping(value = "/listTreatmentDetail")
	public String listTreatmentDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
//		int type = pDto.getAsInteger("type");
		
		List<?> data = super.getFredaReader().queryForList("Treatment.listDetail", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, pDto.getAsInteger("count"), SystemCons.DATE_TIME_FORMART), response);

		return null;
	}
}

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
 * 微信用户信息统计
 * @ClassName: WxUserController
 * @author:panlinlin
 * @data:2016年3月14日
 * @Description: TODO
 * @author: pan
 * @date: 2016年3月14日 下午2:09:48
 */
@Controller
@RequestMapping(value="/wxUser")
public class WxUserController extends ManagerBaseController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listwxUser")
	public String listwxUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		String province = pDto.getAsString("province");
		pDto.put("province", province.substring(0, province.length()-1));
		
		String city = pDto.getAsString("city");
		if(city!=""&&city!=null){
			pDto.put("city", city.substring(0, city.length()-1));
		}
		
		List<?> data = super.getFredaReader().queryForList("WxUser.listwxUser", pDto);

		Integer count = (Integer) super.getFredaReader().queryForObject("WxUser.listwxUserCount", pDto);

		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}
	
}

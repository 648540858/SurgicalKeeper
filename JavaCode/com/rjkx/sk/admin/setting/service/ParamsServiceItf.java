package com.rjkx.sk.admin.setting.service;

import java.util.List;

import com.rjkx.sk.system.datastructure.Dto;

public interface ParamsServiceItf {

	/**
	 * ADD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午3:41:05
	  * @param pDto
	 */
	void addParams(Dto pDto);

	/**
	 * EDIT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午3:41:12
	  * @param pDto
	 */
	void editParams(Dto pDto);

	/**
	 * DELETE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午3:41:18
	  * @param pDto
	 */
	void delParams(Dto pDto);

	/**
	 * 查询全部
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午3:41:26
	  * @return
	 */
	List<?> listAllParams();

}
package com.rjkx.sk.admin.setting.service;

import com.rjkx.sk.system.datastructure.Dto;

public interface MenuServiceItf {

	/**
	 * ADD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午2:38:55
	  * @param pDto
	 */
	void addMenu(Dto pDto);

	/**
	 * EDIT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午2:39:01
	  * @param pDto
	 */
	void editMenu(Dto pDto);

	/**
	 * DELETE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午2:39:07
	  * @param pDto
	 */
	void delMenu(Dto pDto);

}
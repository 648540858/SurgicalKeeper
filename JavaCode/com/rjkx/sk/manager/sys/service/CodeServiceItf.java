package com.rjkx.sk.manager.sys.service;

import java.util.List;

import com.rjkx.sk.system.datastructure.Dto;

public interface CodeServiceItf {

	/**
	 * ADD
	  * 
	  * @author yiyuan-LiChun
	  * @date 2015年9月21日 下午2:28:29
	  * @param pDto
	 */
	void addCode(Dto pDto);

	/**
	 * EDIT
	  * 
	  * @author yiyuan-LiChun
	  * @date 2015年9月21日 下午2:28:37
	  * @param pDto
	 */
	void editCode(Dto pDto);

	/**
	 * DELETE
	  * 
	  * @author yiyuan-LiChun
	  * @date 2015年9月21日 下午2:28:45
	  * @param pDto
	 */
	void delCode(Dto pDto);

	/**
	 * LIST ALL (用于系统启动监听加载所有)
	  * 
	  * @author yiyuan-LiChun
	  * @date 2015年9月21日 下午2:28:54
	  * @return
	 */
	List<?> listAllCode();

}
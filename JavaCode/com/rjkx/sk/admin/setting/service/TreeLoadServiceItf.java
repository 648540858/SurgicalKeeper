package com.rjkx.sk.admin.setting.service;

import java.util.List;

import com.rjkx.sk.system.datastructure.Dto;

public interface TreeLoadServiceItf {

	/**
	 * MENU TREE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:14:02
	  * @param pDto
	  * @return
	 */
	List<?> menuTreeInit(Dto pDto);

	/**
	 * DEPT TREE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:14:33
	  * @param pDto
	  * @return
	 */
	List<?> deptTreeInit(Dto pDto);

	/**
	 * MENU WITH ROLE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:14:42
	  * @param pDto
	  * @return
	 */
	List<?> menuTreeWithSelectedInit(Dto pDto);

	/**
	 * ROLE TREE WITH USER
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:14:58
	  * @param pDto
	  * @return
	 */
	List<?> roleTreeWithSelectedInit(Dto pDto);

	/**
	 * USER TREE WITH ROLE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月22日 下午3:15:20
	  * @param pDto
	  * @return
	 */
	List<?> userTreeWithSelectedInit(Dto pDto);

}
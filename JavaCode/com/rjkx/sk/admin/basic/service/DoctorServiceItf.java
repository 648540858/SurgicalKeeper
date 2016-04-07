package com.rjkx.sk.admin.basic.service;

import java.text.ParseException;

import com.rjkx.sk.system.datastructure.Dto;

public interface DoctorServiceItf {

	/**
	 * ADD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午1:45:44
	  * @param pDto
	 */
	void addDoctor(Dto pDto);

	/**
	 * EDIT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午1:45:49
	  * @param pDto
	 */
	void editDoctor(Dto pDto);

	/**
	 * DELETE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月19日 下午1:45:56
	  * @param pDto
	 */
	void delDoctor(Dto pDto);
	/**
	 * 
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月21日 上午9:15:38
	  * @param pDto
	  * @return
	 */
	Dto listSchByDocAndDate(Dto pDto);
	/**
	 * ADD SCH
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月21日 上午9:16:20
	  * @param pDto
	 */
	void addSch(Dto pDto)throws ParseException;
	/**
	 * ADD SCH FOR MONTH
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月4日 下午1:17:09
	  * @param pDto
	  * @throws ParseException
	 */
	void addSchForMonth(Dto pDto)throws ParseException;
	/**
	 * EDIT SCH
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月21日 上午9:16:27
	  * @param pDto
	 */
	void editSch(Dto pDto)throws ParseException;
	/**
	 * DEL SCH
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月21日 上午9:16:37
	  * @param pDto
	 */
	void delSch(Dto pDto);

}
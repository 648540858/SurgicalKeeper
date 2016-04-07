package com.rjkx.sk.manager.common.service;

import java.util.List;

import com.rjkx.sk.system.datastructure.Dto;

public interface CommonTreeServiceItf {
	
	/**
	 * 获得下级城市树
	 * 
	 * @param pDto
	 * @return
	 */
	public abstract List<?> getSubCityTree(Dto pDto);
	
	/***
	 * 获取疾病子类树
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月10日 下午1:57:29
	  * @param pDto
	  * @return
	 */
	public abstract List<?> getSickSubTree(Dto pDto);
	
	/***
	 * 获取城市医院树
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月10日 下午10:25:46
	  * @param pDto
	  * @return
	 */
	public abstract List<?> getCityHospTree(Dto pDto);
	
	/***
	 * 获取疾病分类树
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月10日 下午10:25:46
	  * @param pDto
	  * @return
	 */
	public abstract List<?> getSickTree(Dto pDto);
	
	/**
	 * 获得部门树
	 * 
	 * @param pDto
	 * @return
	 */
	public abstract List<?> getDeptTree(Dto pDto);
	
	/**
	 * 获得菜单树
	 * 
	 * @param pDto
	 * @return
	 */
	public abstract List<?> getMenuTree(Dto pDto);
	
	/***
	 * 获取城市医院医生树
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月10日 下午10:25:46
	  * @param pDto
	  * @return
	 */
	public abstract List<?> getDoctorTree(Dto pDto);
}
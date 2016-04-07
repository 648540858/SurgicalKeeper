package com.rjkx.sk.system.datastructure.impl;

import java.io.Serializable;

import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;

public abstract class BaseVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2125043150413834797L;

	/**
     * 将值对象转换为Dto对象
     * 
     * @return dto 返回的Dto对象
     */
	public Dto toDto(){
		Dto dto = new BaseDto();
		FredaUtils.copyPropFromBean2Dto(this, dto);
		return dto;
	}
	
	/**
	 * 将值对象转换为JSON字符串
	 * @return String 返回的JSON格式字符串
	 */
    public String toJson(){
    	Dto dto = toDto();
    	return dto.toJson();
    }

}

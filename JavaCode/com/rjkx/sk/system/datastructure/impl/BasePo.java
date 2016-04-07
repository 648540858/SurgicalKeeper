package com.rjkx.sk.system.datastructure.impl;
/**
 * po父类
 * @author Rally
 */
import java.io.Serializable;

import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;

public abstract class BasePo implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8308241580260450141L;

	/**
     * 将实体领域对象转换为Dto对象
     * @return dto 返回的Dto对象
     */
	public Dto toDto(){
		Dto dto = new BaseDto();
		FredaUtils.copyPropFromBean2Dto(this, dto);
	   	//BeanUtils自动将getPk()方法认为是Bean属性拷贝到了Dto对象中,故将其移除.
    	dto.remove("pk");
		return dto;
	}
	
	/**
	 * 将实体领域对象转换为JSON字符串
	 * @return String 返回的JSON格式字符串
	 */
    public String toJson(){
    	Dto dto = toDto();
    	return dto.toJson();
    }
    
    /**
     * 获取主键或强制非空键值组合
     */
    public abstract int getPk();

}

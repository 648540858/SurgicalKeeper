package com.rjkx.sk.admin.setting.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.admin.core.web.AdminBaseServiceImpl;
import com.rjkx.sk.admin.setting.service.ParamsServiceItf;
import com.rjkx.sk.system.datastructure.Dto;

/**
 * 
 * @author Rally
 *
 */
@Service(value="paramsServiceImpl")
public class ParamsServiceImpl extends AdminBaseServiceImpl implements ParamsServiceItf 
{
	/*
	  * <p>Title: addParams</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.ParamsServiceItf#addParams(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void addParams(Dto pDto)
	{
		super.getFredaDao().insert("Params.addParams", pDto);
	}
	/*
	  * <p>Title: editParams</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.ParamsServiceItf#editParams(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void editParams(Dto pDto)
	{
		super.getFredaDao().update("Params.editParams", pDto);
	}
	/*
	  * <p>Title: delParams</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.ParamsServiceItf#delParams(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delParams(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		for(String id : ids)
		{
			pDto.put("id", id);
			
			super.getFredaDao().delete("Params.delParams", pDto);
		}
	}
	/*
	  * <p>Title: listAllParams</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.rjkx.sk.admin.setting.service.impl.ParamsServiceItf#listAllParams()
	  */
	
	
	@Override
	public List<?> listAllParams()
	{
		return super.getFredaDao().queryForList("Params.listParams");
	}
}

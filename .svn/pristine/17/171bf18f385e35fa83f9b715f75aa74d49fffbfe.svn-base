package com.rjkx.sk.admin.setting.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.admin.core.web.AdminBaseServiceImpl;
import com.rjkx.sk.admin.setting.service.CodeServiceItf;
import com.rjkx.sk.system.datastructure.Dto;

/**
 * 
 * @author Rally
 *
 */
@Service(value="codeServiceImpl")
public class CodeServiceImpl extends AdminBaseServiceImpl implements CodeServiceItf
{
	/*
	  * <p>Title: addCode</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.CodeServiceItf#addCode(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void addCode(Dto pDto)
	{
		super.getFredaDao().insert("Code.addCode", pDto);
	}
	/*
	  * <p>Title: editCode</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.CodeServiceItf#editCode(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void editCode(Dto pDto)
	{
		super.getFredaDao().update("Code.editCode", pDto);
	}
	/*
	  * <p>Title: delCode</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.CodeServiceItf#delCode(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delCode(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		for(String id : ids)
		{
			pDto.put("id", id);
			
			super.getFredaDao().delete("Code.delCode", pDto);
		}
	}
	/*
	  * <p>Title: listAllCode</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.rjkx.sk.admin.setting.service.impl.CodeServiceItf#listAllCode()
	  */
	
	
	@Override
	public List<?> listAllCode()
	{
		return super.getFredaDao().queryForList("Code.listCode");
	}
}

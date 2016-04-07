package com.rjkx.sk.admin.setting.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.admin.core.web.AdminBaseServiceImpl;
import com.rjkx.sk.admin.setting.service.AuthorityServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;

/**
 * Authority
 * @author Rally
 *
 */
@Service(value="authorityServiceImpl")
public class AuthorityServiceImpl extends AdminBaseServiceImpl implements AuthorityServiceItf
{
	/*
	  * <p>Title: setMenuForRole</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.AuthorityServiceItf#setMenuForRole(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void setMenuForRole(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		super.getFredaDao().delete("Authority.delMenuToRoleByRole", pDto);
		
		for(String id : ids)
		{
			if(FredaUtils.isNotEmpty(id))
			{
				pDto.put("mId", id);
				
				super.getFredaDao().insert("Authority.addMenuToRole", pDto);
			}
		}
	}
	/*
	  * <p>Title: setUserForRole</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.AuthorityServiceItf#setUserForRole(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void setUserForRole(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		super.getFredaDao().delete("Authority.delUserToRoleByRole", pDto);
		
		for(String id : ids)
		{
			if(FredaUtils.isNotEmpty(id))
			{
				pDto.put("uId", id);
				
				super.getFredaDao().insert("Authority.addUserToRole", pDto);
			}
		}
	}
	/*
	  * <p>Title: setRoleForUser</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.AuthorityServiceItf#setRoleForUser(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void setRoleForUser(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		super.getFredaDao().delete("Authority.delUserToRoleByUser", pDto);
		
		for(String id : ids)
		{
			if(FredaUtils.isNotEmpty(id))
			{
				pDto.put("rId", id);
				
				super.getFredaDao().insert("Authority.addUserToRole", pDto);				
			}
		}
	}
}

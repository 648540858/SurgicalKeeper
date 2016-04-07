package com.rjkx.sk.admin.setting.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.admin.core.web.AdminBaseServiceImpl;
import com.rjkx.sk.admin.setting.service.OrganizationServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;

/**
 * Organization
 * @author Rally
 *
 */
@Service(value="organizationServiceImpl")
public class OrganizationServiceImpl extends AdminBaseServiceImpl implements OrganizationServiceItf
{
	
	/*
	  * <p>Title: addDept</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.OrganizationServiceItf#addDept(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addDept(Dto pDto)
	{
		pDto.put("leaf", 0);
		
		super.getFredaDao().insert("Organization.addDept", pDto);
		
		super.getFredaDao().update("Organization.editDeptLeaf", pDto);
	}
	/*
	  * <p>Title: addRole</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.OrganizationServiceItf#addRole(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void addRole(Dto pDto)
	{
		super.getFredaDao().insert("Organization.addRole", pDto);
	}
	/*
	  * <p>Title: addUser</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.OrganizationServiceItf#addUser(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void addUser(Dto pDto)
	{
		pDto.put("uPwd", FredaUtils.encryptBasedDes(pDto.getAsString("uPwd")));
		
		super.getFredaDao().insert("Organization.addUser", pDto);
	}
	/*
	  * <p>Title: editDept</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.OrganizationServiceItf#editDept(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void editDept(Dto pDto)
	{
		super.getFredaDao().update("Organization.editDept", pDto);
	}
	/*
	  * <p>Title: editRole</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.OrganizationServiceItf#editRole(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void editRole(Dto pDto)
	{
		super.getFredaDao().update("Organization.editRole", pDto);
	}
	/*
	  * <p>Title: editUser</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.OrganizationServiceItf#editUser(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void editUser(Dto pDto)
	{
		pDto.put("uPwd", FredaUtils.encryptBasedDes(pDto.getAsString("uPwd")));
		
		super.getFredaDao().update("Organization.editUser", pDto);
	}
	/*
	  * <p>Title: delDept</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.OrganizationServiceItf#delDept(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delDept(Dto pDto)
	{
		if((Integer)super.getFredaDao().queryForObject("Organization.listDeptCount", pDto) <= 2)
		{
			pDto.put("leaf", 1);
			
			super.getFredaDao().update("Organization.editDeptLeaf", pDto);			
		}
		super.getFredaDao().delete("Organization.delDept", pDto);
	}
	/*
	  * <p>Title: delRole</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.OrganizationServiceItf#delRole(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delRole(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		for(String id : ids)
		{
			pDto.put("rId", id);
			
			super.getFredaDao().delete("Organization.delRole", pDto);
			
			super.getFredaDao().delete("Authority.delMenuToRoleByRole", pDto);
			
			super.getFredaDao().delete("Authority.delUserToRoleByRole", pDto);
		}
	}
	/*
	  * <p>Title: delUser</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.setting.service.impl.OrganizationServiceItf#delUser(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delUser(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		for(String id : ids)
		{
			pDto.put("uId", id);
			
			super.getFredaDao().delete("Organization.delUser", pDto);
			
			super.getFredaDao().delete("Authority.delUserToRoleByUser", pDto);
		}
	}
}

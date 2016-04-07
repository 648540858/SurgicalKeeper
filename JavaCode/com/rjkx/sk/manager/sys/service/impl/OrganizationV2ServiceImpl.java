package com.rjkx.sk.manager.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.sys.service.OrganizationServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;

/**
 * Organization
 * 
 * @author LiChun
 *
 */
@Service(value = "organizationV2ServiceImpl")
public class OrganizationV2ServiceImpl extends ManagerBaseServiceImpl implements OrganizationServiceItf {
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addDept(Dto pDto) {
		pDto.put("leaf", 0);
		super.getFredaDao().insert("OrganizationV2.addDept", pDto);
		super.getFredaDao().update("OrganizationV2.editDeptLeaf", pDto);
	}

	@Override
	public void addRole(Dto pDto) {
		super.getFredaDao().insert("OrganizationV2.addRole", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addUser(Dto pDto) {
		pDto.put("uPwd", FredaUtils.encryptBasedDes(pDto.getAsString("uPwd")));

		super.getFredaDao().insert("OrganizationV2.addUser", pDto);
	}

	@Override
	public void editDept(Dto pDto) {
		super.getFredaDao().update("OrganizationV2.editDept", pDto);
	}

	@Override
	public void editRole(Dto pDto) {
		super.getFredaDao().update("OrganizationV2.editRole", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void editUser(Dto pDto) {
		if(FredaUtils.isNotEmpty(pDto.getAsString("uPwd"))){
			pDto.put("uPwd", FredaUtils.encryptBasedDes(pDto.getAsString("uPwd")));
		}else{
			pDto.remove("uPwd");
		}
		super.getFredaDao().update("OrganizationV2.editUser", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delDept(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("deptId", id);
			super.getFredaDao().delete("OrganizationV2.delDept", pDto);
		}
		if ((Integer) super.getFredaDao().queryForObject("OrganizationV2.listDeptCount", pDto) < 1) {
			pDto.put("leaf", 1);
			super.getFredaDao().update("OrganizationV2.editDeptLeaf", pDto);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delRole(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("roleId", id);
			super.getFredaDao().delete("OrganizationV2.delRole", pDto);
			super.getFredaDao().delete("OrganizationV2.delRoleMenu", pDto);
			super.getFredaDao().delete("OrganizationV2.delRoleUser", pDto);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delUser(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("uId", id);

			super.getFredaDao().delete("OrganizationV2.delUser", pDto);
			super.getFredaDao().delete("OrganizationV2.delUserRole", pDto);
		}
	}
}

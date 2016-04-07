package com.rjkx.sk.manager.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.sys.service.CodeServiceItf;
import com.rjkx.sk.system.datastructure.Dto;

/**
 * 
 * @author LiChun
 *
 */
@Service(value = "codeV2ServiceImpl")
public class CodeServiceImpl extends ManagerBaseServiceImpl implements CodeServiceItf {

	@Override
	public void addCode(Dto pDto) {
		super.getFredaDao().insert("CodeV2.addCode", pDto);
	}

	@Override
	public void editCode(Dto pDto) {
		super.getFredaDao().update("CodeV2.editCode", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delCode(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("cId", id);

			super.getFredaDao().delete("CodeV2.delCode", pDto);
		}
	}

	@Override
	public List<?> listAllCode() {
		return super.getFredaDao().queryForList("CodeV2.listCode");
	}
}

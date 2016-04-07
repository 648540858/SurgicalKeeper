package com.rjkx.sk.manager.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.cms.service.SickAttrContentService;
import com.rjkx.sk.system.datastructure.Dto;

@Service(value = "sickAttrContentServiceImpl")
public class SickAttrContentServiceImpl extends ManagerBaseServiceImpl implements SickAttrContentService {

	@Override
	public void addSickAttrContent(Dto pDto) {
		super.getFredaDao().insert("SickAttrContent.addSickAttrContent", pDto);
	}

	@Override
	public void editSickAttrContent(Dto pDto) {
		super.getFredaDao().update("SickAttrContent.editSickAttrContent", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delSickAttrContent(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("sickContentId", id);

			super.getFredaDao().delete("SickAttrContent.delSickAttrContent", pDto);
		}
	}

}

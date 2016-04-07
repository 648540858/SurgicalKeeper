package com.rjkx.sk.manager.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.sys.service.ParamsServiceItf;
import com.rjkx.sk.system.datastructure.Dto;

/**
 * 
 * @author LiChun
 *
 */
@Service(value = "paramsV2ServiceImpl")
public class ParamsServiceImpl extends ManagerBaseServiceImpl implements ParamsServiceItf {
	@Override
	public void addParams(Dto pDto) {
		super.getFredaDao().insert("ParamsV2.addParams", pDto);
	}

	@Override
	public void editParams(Dto pDto) {
		super.getFredaDao().update("ParamsV2.editParams", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delParams(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("pId", id);

			super.getFredaDao().delete("ParamsV2.delParams", pDto);
		}
	}

	@Override
	public List<?> listAllParams() {
		return super.getFredaDao().queryForList("ParamsV2.listParams");
	}
}

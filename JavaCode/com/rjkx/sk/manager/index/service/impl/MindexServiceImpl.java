package com.rjkx.sk.manager.index.service.impl;

import org.springframework.stereotype.Service;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.index.service.MindexService;
import com.rjkx.sk.manager.index.vo.ManagerUserVo;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;

@Service(value="mindexServiceImpl")
public class MindexServiceImpl extends ManagerBaseServiceImpl implements MindexService {

	@Override
	public boolean editPwd(Dto pDto) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ManagerUserVo queryUser(Dto pDto) {
		pDto.put("pwd", FredaUtils.encryptBasedDes(pDto.getAsString("pwd")));

		Object object = super.getFredaDao().queryForObject("IndexV2.queryUser", pDto);

		return (ManagerUserVo) object;
	}

}

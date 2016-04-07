package com.rjkx.sk.manager.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.sys.service.MenuServiceItf;
import com.rjkx.sk.system.datastructure.Dto;

/**
 * MENU
 * 
 * @ClassName: MenuServiceImpl
 * @Description:
 * @author yiyuan-LiChun
 * @date 2015年9月22日 下午2:36:38
 * @version V1.0
 */
@Service(value = "menuV2ServiceImpl")
public class MenuServiceImpl extends ManagerBaseServiceImpl implements MenuServiceItf {
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addMenu(Dto pDto) {
		pDto.put("leaf", 0);

		super.getFredaDao().insert("MenuV2.addMenu", pDto);

		super.getFredaDao().update("MenuV2.editLeaf", pDto);
	}

	@Override
	public void editMenu(Dto pDto) {
		super.getFredaDao().update("MenuV2.editMenu", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delMenu(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("mId", id);
			super.getFredaDao().delete("MenuV2.delMenu", pDto);
		}
		if (((Integer) super.getFredaDao().queryForObject("MenuV2.listMenuCount", pDto)) < 1) {
			pDto.put("leaf", 1);
			super.getFredaDao().update("MenuV2.editLeaf", pDto);
		}
	}
}

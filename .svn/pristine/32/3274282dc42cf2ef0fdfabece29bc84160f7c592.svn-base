package com.rjkx.sk.manager.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.cms.service.LectureService;
import com.rjkx.sk.system.datastructure.Dto;

@Service(value = "lectureV2ServiceImpl")
public class LectureServiceImpl extends ManagerBaseServiceImpl implements LectureService {

	@Override
	public void addLecture(Dto pDto) {
		super.getFredaDao().insert("LectureV2.addLecture", pDto);
	}

	@Override
	public void editLecture(Dto pDto) {
		super.getFredaDao().update("LectureV2.editLecture", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delLecture(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("lecId", id);

			super.getFredaDao().delete("LectureV2.delLecture", pDto);
		}
	}

}

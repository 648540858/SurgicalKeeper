package com.rjkx.sk.manager.basedata.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.basedata.service.HospDepartmentService;
import com.rjkx.sk.system.datastructure.Dto;

/**
 * 医院与科室
 * 
 * @ClassName: HospitalAndDeptServiceImpl
 * @Description: 
 * @author yiyuan-panlinlin
 * @date 2015年10月14日 下午8:52:56
 * @version V1.0
 */
@Service(value="hospDepartmentServiceImpl")
public class HospDepartmentServiceImpl extends ManagerBaseServiceImpl implements HospDepartmentService {

	@Override
	public void addHospital(Dto pDto) {
		super.getFredaDao().insert("Hospital_Dept.addHospital", pDto);
	}

	@Override
	public void editHospital(Dto pDto) {
		super.getFredaDao().update("Hospital_Dept.editHospital", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delHospital(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("hId", id);

			super.getFredaDao().delete("Hospital_Dept.delHospital", pDto);
			super.getFredaDao().delete("Hospital_Dept.delDeptToHosByHos", pDto);
		}
	}

	@Override
	public void addHosDept(Dto pDto) {
		super.getFredaDao().insert("Hospital_Dept.addHosDept", pDto);
	}

	@Override
	public void editHosDept(Dto pDto) {
		super.getFredaDao().update("Hospital_Dept.editHosDept", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delHosDept(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("id", id);

			super.getFredaDao().delete("Hospital_Dept.delHosDept", pDto);
		}
	}

}

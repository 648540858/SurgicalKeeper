package com.rjkx.sk.manager.basedata.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.basedata.service.InterrelationService;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 关联处理
 * 
 * @ClassName: CorrelationServiceImpl
 * @Description:
 * @author yiyuan-Rally
 * @date 2016年2月1日 上午10:44:41
 * @version V1.0
 */
@Service(value = "interrelationServiceImpl")
public class InterrelationServiceImpl extends ManagerBaseServiceImpl implements InterrelationService {

	@Override
	public List<?> sicknessTreeWithHosDeptForComb(Dto pDto) {
		return this.setListChecked(super.getFredaDao().queryForList("Interrelation.listAllSicknessWithHosDept", pDto), "hdId");
	}

	@Override
	public List<?> hosDeptTreeWithHosForCombo(Dto pDto) {
		return this.setListChecked(super.getFredaDao().queryForList("Interrelation.listAllHosDeptWithHos", pDto), "hospId");
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addSicknessToHosDept(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		super.getFredaDao().delete("Interrelation.delSicknessToHosDeptByHosDept", pDto);

		for (String id : ids) {
			if (FredaUtils.isNotEmpty(id)) {
				pDto.put("id", id);

				super.getFredaDao().insert("Interrelation.addSicknessToHosDept", pDto);
			}

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addHosDeptToHos(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		super.getFredaDao().delete("Interrelation.delHosDeptToHosByHos", pDto);

		for (String id : ids) {
			if (FredaUtils.isNotEmpty(id)) {
				pDto.put("id", id);

				super.getFredaDao().insert("Interrelation.addHosDeptToHos", pDto);
			}
		}
	}

	/**
	 * 添加checked
	 * 
	 * @param data
	 * @param falg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<?> setListChecked(List<?> data, final String falg) {
		for (int i = 0; i < data.size(); i++) {
			Dto rowDto = (Dto) data.get(i);

			if (FredaUtils.isNotEmpty(rowDto.getAsString(falg))) {
				rowDto.put("checked", SystemCons.TRUE);
			} else {
				rowDto.put("checked", SystemCons.FALSE);
			}
		}
		return data;
	}

	@Override
	public List<?> sicknessTreeWithDoctorForCombo(Dto pDto) {

		return this.setListChecked(super.getFredaDao().queryForList("Interrelation.listSicknessWithDoctor", pDto), "docId");
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addSicknessToDoctor(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		super.getFredaDao().delete("Interrelation.delSicknessToDoctorByDoc", pDto);

		for (String id : ids) {
			if (FredaUtils.isNotEmpty(id)) {
				pDto.put("id", id);

				super.getFredaDao().delete("Interrelation.addSicknessToDoctor", pDto);
			}
		}
	}
}

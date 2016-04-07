package com.rjkx.sk.admin.basic.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.admin.basic.service.HospitalAndDeptServiceItf;
import com.rjkx.sk.admin.core.web.AdminBaseServiceImpl;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;
/**
 * 医院与科室
  * @ClassName: HospitalAndDeptServiceImpl
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月14日 下午8:52:56 
  * @version V1.0
 */
@Service(value="hospitalAndDeptServiceImpl")
public class HospitalAndDeptServiceImpl extends AdminBaseServiceImpl implements HospitalAndDeptServiceItf
{
	
	/*
	  * <p>Title: addHospital</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.HospitalAndDeptServiceItf#addHospital(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void addHospital(Dto pDto)
	{
		super.getFredaDao().insert("HospitalAndDept.addHospital", pDto);
	}
	/*
	  * <p>Title: editHospital</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.HospitalAndDeptServiceItf#editHospital(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void editHospital(Dto pDto)
	{
		super.getFredaDao().update("HospitalAndDept.editHospital", pDto);
	}
	/*
	  * <p>Title: delHospital</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.HospitalAndDeptServiceItf#delHospital(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delHospital(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		for(String id : ids)
		{
			pDto.put("hId", id);
			
			super.getFredaDao().delete("HospitalAndDept.delHospital", pDto);
		}
	}
	/*
	  * <p>Title: addHosDept</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.HospitalAndDeptServiceItf#addHosDept(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void addHosDept(Dto pDto)
	{
		super.getFredaDao().insert("HospitalAndDept.addHosDept", pDto);
	}
	/*
	  * <p>Title: editHosDept</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.HospitalAndDeptServiceItf#editHosDept(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void editHosDept(Dto pDto)
	{
		super.getFredaDao().update("HospitalAndDept.editHosDept", pDto);
	}
	/*
	  * <p>Title: delHosDept</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.HospitalAndDeptServiceItf#delHosDept(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delHosDept(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		for(String id :ids)
		{
			pDto.put("id", id);
			
			super.getFredaDao().delete("HospitalAndDept.delHosDept", pDto);
		}
	}
	/*
	  * <p>Title: deptToHos</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.HospitalAndDeptServiceItf#deptToHos(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deptToHos(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		super.getFredaDao().delete("HospitalAndDept.delDeptToHosByHos", pDto);
		
		for(String id : ids)
		{
			if(FredaUtils.isNotEmpty(id))
			{
				pDto.put("id", id);
				
				super.getFredaDao().insert("HospitalAndDept.addDeptToHos", pDto);
			}
		}
	}
}

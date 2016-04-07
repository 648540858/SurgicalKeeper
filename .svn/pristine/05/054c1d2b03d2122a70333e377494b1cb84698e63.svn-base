package com.rjkx.sk.admin.basic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.admin.basic.service.CorrelationServiceItf;
import com.rjkx.sk.admin.core.web.AdminBaseServiceImpl;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 关联处理
  * @ClassName: CorrelationServiceImpl
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月16日 上午10:44:41 
  * @version V1.0
 */
@Service(value="correlationServiceImpl")
public class CorrelationServiceImpl extends AdminBaseServiceImpl implements CorrelationServiceItf
{
	/*
	  * <p>Title: sicknessTreeWithHosDeptForComb</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @return
	  * @see com.rjkx.sk.admin.basic.service.impl.CorrelationServiceItf#sicknessTreeWithHosDeptForComb(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public List<?> sicknessTreeWithHosDeptForComb(Dto pDto)
	{
		
		return this.setListChecked(super.getFredaDao().queryForList("Correlation.listAllSicknessWithHosDept", pDto), "hdId");
	}
	/*
	  * <p>Title: hosDeptTreeWithHosForCombo</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @return
	  * @see com.rjkx.sk.admin.basic.service.impl.CorrelationServiceItf#hosDeptTreeWithHosForCombo(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public List<?> hosDeptTreeWithHosForCombo(Dto pDto)
	{
		return this.setListChecked(super.getFredaDao().queryForList("Correlation.listAllHosDeptWithHos", pDto), "hId");
	}
	/*
	  * <p>Title: addSicknessToHosDept</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.CorrelationServiceItf#addSicknessToHosDept(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addSicknessToHosDept(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		super.getFredaDao().delete("Correlation.delSicknessToHosDeptByHosDept", pDto);
		
		for(String id : ids)
		{
			if(FredaUtils.isNotEmpty(id))
			{
				pDto.put("id", id);
				
				super.getFredaDao().insert("Correlation.addSicknessToHosDept", pDto);
			}
			
			
		}
	}
	/*
	  * <p>Title: addHosDeptToHos</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.CorrelationServiceItf#addHosDeptToHos(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addHosDeptToHos(Dto pDto)
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		super.getFredaDao().delete("Correlation.delHosDeptToHosByHos", pDto);
		
		for(String id : ids)
		{
			if(FredaUtils.isNotEmpty(id))
			{
				pDto.put("id", id);
				
				super.getFredaDao().insert("Correlation.addHosDeptToHos", pDto);
			}
		}
	}
	
	/**
	 * 添加checked
	 * @param data
	 * @param falg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<?> setListChecked(List<?> data,final String falg)
	{
		for(int i=0;i<data.size();i++)
		{
			Dto rowDto = (Dto) data.get(i);
			
			if(FredaUtils.isNotEmpty(rowDto.getAsString(falg)))
			{
				rowDto.put("checked", SystemCons.TRUE);
			}
			else
			{
				rowDto.put("checked", SystemCons.FALSE);
			}
		}
		return data;
	}

	/*
	  * <p>Title: addHosDeptToHos</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.CorrelationServiceItf#sicknessTreeWithDoctorForCombo(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	@Override
	public List<?> sicknessTreeWithDoctorForCombo(Dto pDto) 
	{
		
		return this.setListChecked(super.getFredaDao().queryForList("Correlation.listSicknessWithDoctor", pDto), "docId");
	}

	/*
	  * <p>Title: addHosDeptToHos</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.basic.service.impl.CorrelationServiceItf#addSicknessToDoctor(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addSicknessToDoctor(Dto pDto) 
	{
		String[] ids = pDto.getAsString("ids").split(",");
		
		super.getFredaDao().delete("Correlation.delSicknessToDoctorByDoc", pDto);
		
		for(String id : ids)
		{
			if(FredaUtils.isNotEmpty(id))
			{
				pDto.put("id", id);
				
				super.getFredaDao().delete("Correlation.addSicknessToDoctor", pDto);				
			}
		}
	}
}

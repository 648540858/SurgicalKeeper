package com.rjkx.sk.admin.setting.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rjkx.sk.admin.core.web.AdminBaseServiceImpl;
import com.rjkx.sk.admin.setting.service.TreeLoadServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * Tree Load
  * @ClassName: TreeLoadServiceImpl
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年9月22日 下午3:08:28 
  * @version V1.0
 */
@Service(value="treeLoadServiceImpl")
public class TreeLoadServiceImpl extends AdminBaseServiceImpl implements TreeLoadServiceItf 
{
	/*
	  * <p>Title: menuTreeInit</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @return
	  * @see com.rjkx.sk.admin.setting.service.impl.TreeLoadServiceItf#menuTreeInit(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> menuTreeInit(Dto pDto)
	{
		pDto.put("pId", pDto.getAsString("node"));
		
		return super.getFredaDao().queryForList("TreeLoad.menuTreeInit", pDto);
	}
	/*
	  * <p>Title: deptTreeInit</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @return
	  * @see com.rjkx.sk.admin.setting.service.impl.TreeLoadServiceItf#deptTreeInit(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> deptTreeInit(Dto pDto)
	{
		pDto.put("pId", pDto.getAsString("node"));
		
		return super.getFredaDao().queryForList("TreeLoad.deptTreeInit", pDto);
	}
	/*
	  * <p>Title: menuTreeWithSelectedInit</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @return
	  * @see com.rjkx.sk.admin.setting.service.impl.TreeLoadServiceItf#menuTreeWithSelectedInit(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> menuTreeWithSelectedInit(Dto pDto)
	{
		pDto.put("pId", pDto.getAsString("node"));
		
		return this.setListChecked(super.getFredaDao().queryForList("TreeLoad.menuTreeWithRole", pDto), "rId");
	}
	/*
	  * <p>Title: roleTreeWithSelectedInit</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @return
	  * @see com.rjkx.sk.admin.setting.service.impl.TreeLoadServiceItf#roleTreeWithSelectedInit(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> roleTreeWithSelectedInit(Dto pDto)
	{
		pDto.put("pId", pDto.getAsString("node"));
		
		return this.setListChecked(super.getFredaDao().queryForList("TreeLoad.roleTreeWithUser", pDto), "uId");
	}
	/*
	  * <p>Title: userTreeWithSelectedInit</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @return
	  * @see com.rjkx.sk.admin.setting.service.impl.TreeLoadServiceItf#userTreeWithSelectedInit(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> userTreeWithSelectedInit(Dto pDto)
	{
		pDto.put("pId", pDto.getAsString("node"));
		
		List<?> data = super.getFredaDao().queryForList("TreeLoad.deptTreeInit", pDto);
		
		if(FredaUtils.isEmpty(data))
		{
			data = super.getFredaDao().queryForList("TreeLoad.userTreeWithRole", pDto);
			
			this.setListChecked(data, "rId");
		}
		else
		{
			this.setListLeaf(data, 0);
		}
		
		return data;
	}
	
	/**
	 * 设置节点LEAF
	 * @param data
	 * @param leaf
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<?> setListLeaf(List<?> data,final int leaf)
	{
		for(int i=0;i<data.size();i++)
		{
			Dto rowDto = (Dto) data.get(i);
			
			rowDto.put("leaf", leaf);
		}
		return data;
	}
	/**
	 * 添加checked
	 * @param data
	 * @param falg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<?> setListChecked(List<?> data,final String falg)
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
}

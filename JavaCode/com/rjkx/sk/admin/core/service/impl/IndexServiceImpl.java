package com.rjkx.sk.admin.core.service.impl;
/**
 * @author Rally
 */
import java.util.List;

import org.springframework.stereotype.Service;

import com.rjkx.sk.admin.core.service.IndexServiceItf;
import com.rjkx.sk.admin.core.vo.AdminUserVo;
/**
 * @author Rally
 */
import com.rjkx.sk.admin.core.web.AdminBaseServiceImpl;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;
@Service(value="indexServiceImpl")
public class IndexServiceImpl extends AdminBaseServiceImpl implements IndexServiceItf
{
	/* (non-Javadoc)
	 * @see com.rjkx.sk.admin.core.service.impl.IndexServiceItf#getMenuPanel(com.rjkx.sk.system.datastructure.Dto)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<?> getMenuPanel(Dto pDto)
	{
		if(FredaUtils.isEmpty(pDto.getAsString("pId")))
		{
			pDto.put("pId", pDto.getAsString("node"));
		}
		return super.getFredaDao().queryForList("Index.queryMenuItems", pDto);
	}
		
	/* (non-Javadoc)
	 * @see com.rjkx.sk.admin.core.service.impl.IndexServiceItf#editPwd(com.rjkx.sk.system.datastructure.Dto)
	 */
	@Override
	public boolean editPwd(Dto pDto)
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see com.rjkx.sk.admin.core.service.impl.IndexServiceItf#queryUser(com.rjkx.sk.system.datastructure.Dto)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AdminUserVo queryUser(Dto pDto) 
	{
		pDto.put("pwd", FredaUtils.encryptBasedDes(pDto.getAsString("pwd")));
		
		return (AdminUserVo)super.getFredaDao().queryForObject("Index.queryUser", pDto);
	}
}

package com.rjkx.sk.admin.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjkx.sk.admin.core.service.SystemInitServiceItf;
import com.rjkx.sk.admin.setting.service.CodeServiceItf;
import com.rjkx.sk.admin.setting.service.ParamsServiceItf;

@Service(value="systemInitServiceImpl")
public class SystemInitServiceImpl implements SystemInitServiceItf 
{
	private CodeServiceItf codeService;
	
	private ParamsServiceItf paramsService;

	/*
	  * <p>Title: queryAllCode</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.rjkx.sk.admin.core.service.impl.SystemInitServiceItf#queryAllCode()
	  */
	
	
	@Override
	public List<?> queryAllCode()
	{
		return codeService.listAllCode();
	}
	/*
	  * <p>Title: queryAllParams</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.rjkx.sk.admin.core.service.impl.SystemInitServiceItf#queryAllParams()
	  */
	
	
	@Override
	public List<?> queryAllParams()
	{
		return paramsService.listAllParams();
	}
	
	
	/*
	  * <p>Title: getCodeService</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.rjkx.sk.admin.core.service.impl.SystemInitServiceItf#getCodeService()
	  */
	
	
	@Override
	public CodeServiceItf getCodeService() 
	{
		return codeService;
	}
	@Resource(name="codeServiceImpl")
	public void setCodeService(CodeServiceItf codeService) 
	{
		this.codeService = codeService;
	}

	/*
	  * <p>Title: getParamsService</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.rjkx.sk.admin.core.service.impl.SystemInitServiceItf#getParamsService()
	  */
	
	
	@Override
	public ParamsServiceItf getParamsService() 
	{
		return paramsService;
	}
	@Resource(name="paramsServiceImpl")
	public void setParamsService(ParamsServiceItf paramsService) 
	{
		this.paramsService = paramsService;
	}
	
	
}

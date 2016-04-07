package com.rjkx.sk.manager.cms.service.impl;

import org.springframework.stereotype.Service;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.cms.service.AdService;
import com.rjkx.sk.system.datastructure.Dto;

@Service(value = "adServiceImpl")
public class AdServiceImpl extends ManagerBaseServiceImpl implements AdService {

	@Override
	public void addAd(Dto pDto) throws Exception {
		
		if(super.getFredaDao().insert("ad.addAd", pDto)<1){
			throw new Exception();
		};
	}

	@Override
	public void editAd(Dto pDto) throws Exception {
		
		super.getFredaDao().update("ad.editAd", pDto);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deletAd(Dto pDto) throws Exception {
		
		String[] ids = pDto.getAsString("ids").split(",");
		
		for(String id : ids){
			pDto.put("adId", id);
			if(super.getFredaDao().delete("ad.delLeAd", pDto)<1){
				throw new Exception();
			};
		}
		
		
	}
}

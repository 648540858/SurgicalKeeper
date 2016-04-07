package com.rjkx.sk.manager.cms.service;

import com.rjkx.sk.system.datastructure.Dto;

public interface AdService {

	void addAd(Dto paramsAsDto) throws Exception;

	void editAd(Dto paramsAsDto)throws Exception;

	void deletAd(Dto pDto) throws Exception;
}

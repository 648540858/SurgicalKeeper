package com.rjkx.sk.manager.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.common.service.CommonTreeServiceItf;
import com.rjkx.sk.system.datastructure.Dto;

/**
 * 树形service
 * 
 * @author LiChun
 * 
 */
@Service(value = "commonAdminTreeServiceImpl")
public class CommonTreeServiceImpl extends ManagerBaseServiceImpl implements CommonTreeServiceItf {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> getSubCityTree(Dto pDto) {
		List<?> data = super.getFredaDao().queryForList("CommonAdminTree.getCityTree", pDto);

		for (int i = 0; i < data.size(); i++) {
			Dto dataDto = (Dto) data.get(i);
			if (dataDto.getAsInteger("leaf")!=1) {
				dataDto.put("isParent", new Boolean(true));
			} else {
				dataDto.put("isParent", new Boolean(false));
			}
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getSickSubTree(Dto pDto) {
		List<?> data = super.getFredaDao().queryForList("CommonAdminTree.sicknessSubTree", pDto);

		for (int i = 0; i < data.size(); i++) {
			Dto dataDto = (Dto) data.get(i);
			if (dataDto.getAsInteger("leaf")!=1) {
				dataDto.put("isParent", new Boolean(true));
			} else {
				dataDto.put("isParent", new Boolean(false));
			}
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getCityHospTree(Dto pDto) {
		String oldId=pDto.getAsString("pId");
		pDto.put("pId", oldId.substring(2));
		List<?> data = super.getFredaDao().queryForList("CommonAdminTree.getCityTree", pDto);
		if(data.size()>0){//查下级城市
			for (int i = 0; i < data.size(); i++) {
				Dto dataDto = (Dto) data.get(i);
				dataDto.put("id", "c-"+dataDto.getAsLong("id"));
				dataDto.put("isParent", new Boolean(true));
			}
		}else{//查医院
			data = super.getFredaDao().queryForList("CommonAdminTree.getHospByCityTree", pDto);
			for (int i = 0; i < data.size(); i++) {
				Dto dataDto = (Dto) data.get(i);
				dataDto.put("id", "h-"+dataDto.getAsLong("id"));
				dataDto.put("isParent", new Boolean(false));
			}
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getSickTree(Dto pDto) {
		List<?> data=null;
		String oldId=pDto.getAsString("pId");
		pDto.put("pId", oldId.substring(2));
		if(pDto.getAsLong("sId")==0){
			data = super.getFredaDao().queryForList("CommonAdminTree.getSickness", pDto);
			if(data.size()>0){//查大类
				for (int i = 0; i < data.size(); i++) {
					Dto dataDto = (Dto) data.get(i);
					dataDto.put("id", "c-"+dataDto.getAsLong("id"));
					dataDto.put("pId", "c-0");
					dataDto.put("isParent", new Boolean(true));
				}
			}
		}else{//查子类
			data = super.getFredaDao().queryForList("CommonAdminTree.sicknessSubTree", pDto);
			for (int i = 0; i < data.size(); i++) {
				Dto dataDto = (Dto) data.get(i);
				dataDto.put("id", "s-"+dataDto.getAsLong("id"));
				dataDto.put("sId", pDto.getAsLong("sId"));
				if(dataDto.getAsInteger("leaf")==1){
					dataDto.put("isParent", new Boolean(false));
				}else{
					dataDto.put("isParent", new Boolean(true));
				}
			}
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getDeptTree(Dto pDto) {
		List<?> data = super.getFredaDao().queryForList("CommonAdminTree.getDeptTree", pDto);

		for (int i = 0; i < data.size(); i++) {
			Dto dataDto = (Dto) data.get(i);
			if (dataDto.getAsInteger("leaf")!=1) {
				dataDto.put("isParent", new Boolean(true));
			} else {
				dataDto.put("isParent", new Boolean(false));
			}
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getMenuTree(Dto pDto) {
		List<?> data = super.getFredaDao().queryForList("CommonAdminTree.getMenuTree", pDto);

		for (int i = 0; i < data.size(); i++) {
			Dto dataDto = (Dto) data.get(i);
			if (dataDto.getAsInteger("leaf")!=1) {
				dataDto.put("isParent", new Boolean(true));
			} else {
				dataDto.put("isParent", new Boolean(false));
			}
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getDoctorTree(Dto pDto) {
		String oldId=pDto.getAsString("pId");
		pDto.put("pId", oldId.substring(2));
		List<?> data = null;
		if(oldId.substring(0,1).equals("c")){
			data=super.getFredaDao().queryForList("CommonAdminTree.getCityTree", pDto);
			if(data.size()>0){//查下级城市
				for (int i = 0; i < data.size(); i++) {
					Dto dataDto = (Dto) data.get(i);
					dataDto.put("id", "c-"+dataDto.getAsLong("id"));
					dataDto.put("isParent", new Boolean(true));
				}
			}else{//查医院
				data = super.getFredaDao().queryForList("CommonAdminTree.getHospByCityTree", pDto);
				for (int i = 0; i < data.size(); i++) {
					Dto dataDto = (Dto) data.get(i);
					dataDto.put("id", "h-"+dataDto.getAsLong("id"));
					dataDto.put("leaf", 0);
					dataDto.put("isParent", new Boolean(true));
				}
			}
		}
		if(oldId.substring(0,1).equals("h")){//查医生
			data=super.getFredaDao().queryForList("CommonAdminTree.getDoctorTree", pDto);
			for (int i = 0; i < data.size(); i++) {
				Dto dataDto = (Dto) data.get(i);
				dataDto.put("id", "d-"+dataDto.getAsLong("id"));
				dataDto.put("isParent", new Boolean(false));
			}
		}
		return data;
	}
}

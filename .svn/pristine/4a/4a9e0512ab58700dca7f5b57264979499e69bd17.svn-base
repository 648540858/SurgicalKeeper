package com.rjkx.sk.manager.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.sys.service.AuthorityServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * Authority
 * 
 * @author LiChun
 *
 */
@Service(value = "authorityV2ServiceImpl")
public class AuthorityV2ServiceImpl extends ManagerBaseServiceImpl implements AuthorityServiceItf {
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void setMenuForRole(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		super.getFredaDao().delete("AuthorityV2.delMenuToRoleByRole", pDto);

		for (String id : ids) {
			if (FredaUtils.isNotEmpty(id)) {
				pDto.put("mId", id);

				super.getFredaDao().insert("AuthorityV2.addMenuToRole", pDto);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void setUserForRole(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		super.getFredaDao().delete("AuthorityV2.delUserToRoleByUser", pDto);

		for (String id : ids) {
			if (FredaUtils.isNotEmpty(id)) {
				pDto.put("roleId", id);

				super.getFredaDao().insert("AuthorityV2.addUserToRole", pDto);
			}
		}
	}

	@Override
	public List<?> roleTreeWithUserForCombo(Dto pDto) {
		return this.setListChecked(super.getFredaDao().queryForList("AuthorityV2.listRoleWithUser", pDto), "uId");
	}

	/**
	 * 添加checked
	 * 
	 * @param data
	 * @param flag
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<?> setListChecked(List<?> data, final String flag) {
		for (int i = 0; i < data.size(); i++) {
			Dto rowDto = (Dto) data.get(i);

			if (FredaUtils.isNotEmpty(rowDto.getAsString(flag))) {
				rowDto.put("checked", SystemCons.TRUE);
			} else {
				rowDto.put("checked", SystemCons.FALSE);
			}
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> menuTreeWithUserForCombo(Dto pDto) {
		pDto.put("pId", pDto.getAsString("node"));
		List<?> menuList = super.getFredaDao().queryForList("AuthorityV2.listMenuWithRole", pDto);
		for (int i = 0; i < menuList.size(); i++) {
			Dto dataDto = (Dto) menuList.get(i);
			if (dataDto.getAsInteger("leaf") == 0) {
				dataDto.put("isParent", new Boolean(true));
			} else {
				dataDto.put("isParent", new Boolean(false));
			}
		}
		return this.setListChecked(menuList, "rId");
	}
}

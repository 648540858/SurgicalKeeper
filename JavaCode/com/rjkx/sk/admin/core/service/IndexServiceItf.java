package com.rjkx.sk.admin.core.service;

import java.util.List;

import com.rjkx.sk.admin.core.vo.AdminUserVo;
import com.rjkx.sk.system.datastructure.Dto;

public interface IndexServiceItf {

	/**
	 * 获取菜单
	 * @param pDto
	 * @return
	 */
	public List<?> getMenuPanel(Dto pDto);

	/**
	 * 修改用户密码
	 * @param pDto
	 */
	public boolean editPwd(Dto pDto);
	/**
	 * 查询用户
	 * @param pDto
	 * @return
	 */
	public AdminUserVo queryUser(Dto pDto);

}
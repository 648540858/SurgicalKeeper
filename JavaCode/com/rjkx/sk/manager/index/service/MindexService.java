package com.rjkx.sk.manager.index.service;

import com.rjkx.sk.manager.index.vo.ManagerUserVo;
import com.rjkx.sk.system.datastructure.Dto;

public interface MindexService {
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
	public ManagerUserVo queryUser(Dto pDto);

}

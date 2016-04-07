package com.rjkx.sk.manager.cms.service;

import com.rjkx.sk.system.datastructure.Dto;

public interface LectureService {

	/***
	 * 添加视频
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月11日 上午10:04:20
	  * @param pDto
	 */
	void addLecture(Dto pDto);

	/***
	 * 修改视频
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月11日 上午10:03:57
	  * @param pDto
	 */
	void editLecture(Dto pDto);

	/***
	 * 删除视频
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月11日 上午10:03:40
	  * @param pDto
	 */
	void delLecture(Dto pDto);

}

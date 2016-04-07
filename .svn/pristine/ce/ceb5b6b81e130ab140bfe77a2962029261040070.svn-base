package com.rjkx.sk.system.datastructure.impl;

import java.util.List;

import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.ResultDataBean;
import com.rjkx.sk.system.json.JsonHelper;

/**
 * 页面专用:返回分页DATA BEAN
 * 
 * @ClassName: ResultDataBean
 * @Description:
 * @author yiyuan-Rally
 * @date 2015年10月9日 下午4:08:44
 * @version V1.0
 */
public class BaseResultDataBean implements ResultDataBean {
	private static final int DEF_LIMIT = 20;
	/**
	 * 开始条数
	 */
	private int start;
	/**
	 * 每页长度
	 */
	private int limit;
	/**
	 * 总条数
	 */
	private int totalCount;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 当前页数
	 */
	private int page;
	/**
	 * 附加参数
	 */
	private Dto oDto;
	/**
	 * 数据
	 */
	private List<?> data;

	public BaseResultDataBean()
	{
		this.limit = this.getDefLimit();

		this.start = 0;

		this.page = 1;

		this.totalCount = 0;

		this.totalPage = 0;

		this.data = null;
	}

	public BaseResultDataBean(List<?> data, int totalCount, int sPage, int sLimit)
	{
		this.data = data;

		this.totalCount = totalCount;

		if (sLimit <= 0) {
			this.limit = this.getDefLimit();
		} else {
			this.limit = sLimit;
		}
		if (sPage <= 0) {
			this.page = 1;
		} else {
			this.page = sPage;
		}
		if (totalCount <= 0) {
			this.totalPage = 0;
		} else {
			this.totalPage = totalCount % this.limit == 0 ? this.totalCount / this.limit : this.totalCount / this.limit + 1;
		}
		this.start = (sPage - 1) * this.limit;
	}

	private int getDefLimit() {
		return DEF_LIMIT;
	}

	@Override
	public int getStart() {
		return start;
	}

	@Override
	public void setStart(int start) {
		this.start = start;
	}

	@Override
	public int getLimit() {
		return limit;
	}

	@Override
	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if (totalCount > 0)
			this.totalPage = totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1;
		else
			this.totalPage = 0;
	}

	@Override
	public int getTotalPage() {
		return totalPage;
	}

	@Override
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public Dto getoDto() {
		return oDto;
	}

	@Override
	public void setoDto(Dto oDto) {
		this.oDto = oDto;
	}

	@Override
	public List<?> getData() {
		return data;
	}

	@Override
	public void setData(List<?> data) {
		this.data = data;
	}

	@Override
	public String toJson(String dFormatString) {
		if (this.getTotalCount() >= 0) {
			return JsonHelper.encodeList2PageJson(this.getData(), this.getTotalCount(), dFormatString);
		} else {
			return null;
		}
	}

}

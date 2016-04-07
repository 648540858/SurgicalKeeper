package com.rjkx.sk.system.datastructure;

import java.util.List;

public class PageInfo {
	private static int DEFAULT_PAGESIZE = 20;
	private int pageNo;
	private int pageSize;
	private int totalRecord;
	private int totalPage;
	@SuppressWarnings("rawtypes")
	private List data = null;

	public PageInfo() {
		pageNo = 1;
		pageSize = DEFAULT_PAGESIZE;
		totalRecord = 0;
		totalPage = 0;
		data = null;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		if (totalRecord > 0)
			totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : (totalRecord / pageSize + 1);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@SuppressWarnings("rawtypes")
	public List getData() {
		return data;
	}

	@SuppressWarnings("rawtypes")
	public void setData(List data) {
		this.data = data;
	}
}

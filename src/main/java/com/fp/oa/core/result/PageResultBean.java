package com.fp.oa.core.result;

public class PageResultBean<T> extends ResultBean<T> {
	
	private static final long serialVersionUID = 1L;
	
	private int total; //data count
	private int pageNum;
	private int pageSize;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}

package com.tiantian.core.beans;

import java.io.Serializable;
import java.util.List;

public class PageResult<T>  implements  Serializable {
	private static final long serialVersionUID = -9189972887768326731L;
	public PageResult() {

	}
	public PageResult(Long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public PageResult(int pageNum, int pageSize) {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}

	//当前页
	private int pageNum;
	//每页的数量
	private int pageSize;

	//总记录数
	private long total;
	//总页数
	private int pages;

	//第一页
	private int firstPage;
	//前一页
	private int prePage;
	//下一页
	private int nextPage;
	//最后一页
	private int lastPage;
/*
	//是否为第一页
	private boolean isFirstPage = false;
	//是否为最后一页
	private boolean isLastPage = false;
	//是否有前一页
	private boolean hasPreviousPage = false;
	//是否有下一页
	private boolean hasNextPage = false;*/
	//导航页码数
	private int navigatePages;
	//所有导航页号
	private int[] navigatepageNums;
	//结果集
	private List<T> rows;
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
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	public int getNavigatePages() {
		return navigatePages;
	}
	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}
	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}
	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}

	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}

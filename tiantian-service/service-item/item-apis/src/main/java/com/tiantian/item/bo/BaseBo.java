package com.tiantian.item.bo;

import java.io.Serializable;

public abstract class BaseBo implements  Serializable {

	private static final long serialVersionUID = 323191529643127904L;

	private int pageIndex;

	private int pageSize;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



}

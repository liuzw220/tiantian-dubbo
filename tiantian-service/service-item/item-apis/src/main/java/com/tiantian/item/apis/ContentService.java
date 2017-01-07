package com.tiantian.item.apis;

import com.tiantian.core.beans.PageResult;
import com.tiantian.item.bo.ContentBo;
import com.tiantian.item.vo.ContentVo;

public interface ContentService {
	public PageResult<ContentVo> queryListPage(ContentBo content);

	public void save(ContentBo content);

	public void update(ContentBo content);

	public void deleteByIds(Long[] ids);
}
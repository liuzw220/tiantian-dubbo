package com.tiantian.item.apis;

import com.github.pagehelper.PageInfo;
import com.tiantian.item.bo.ContentBo;
import com.tiantian.item.vo.ContentVo;

public interface ContentService {
	public PageInfo<ContentVo> queryListPage(ContentBo content, Integer page, Integer rows);

	public void save(ContentBo content);

	public void update(ContentBo content);

	public void deleteByIds(Long[] ids);
}
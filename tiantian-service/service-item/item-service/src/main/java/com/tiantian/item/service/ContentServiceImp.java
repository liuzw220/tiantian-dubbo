package com.tiantian.item.service;

import org.springframework.stereotype.Service;

import com.tiantian.core.beans.PageResult;
import com.tiantian.item.apis.ContentService;
import com.tiantian.item.bo.ContentBo;
import com.tiantian.item.vo.ContentVo;

@Service("contentService")
public class ContentServiceImp implements ContentService {

	

	@Override
	public void deleteByIds(Long[] ids) {
		
	}

	@Override
	public PageResult<ContentVo> queryListPage(ContentBo content) {
		return null;
	}

	@Override
	public void save(ContentBo content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ContentBo content) {
		// TODO Auto-generated method stub
		
	}

}

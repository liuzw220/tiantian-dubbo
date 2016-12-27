package com.tiantian.item.service;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.tiantian.item.apis.ContentService;
import com.tiantian.item.bo.ContentBo;
import com.tiantian.item.vo.ContentVo;

@Service("contentService")
public class ContentServiceImp implements ContentService {

	

	@Override
	public void deleteByIds(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageInfo<ContentVo> queryListPage(ContentBo content, Integer page,
			Integer rows) {
		// TODO Auto-generated method stub
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

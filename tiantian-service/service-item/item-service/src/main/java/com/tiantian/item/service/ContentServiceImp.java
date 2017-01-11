package com.tiantian.item.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.tiantian.core.beans.PageResult;
import com.tiantian.item.apis.ContentService;
import com.tiantian.item.bo.ContentBo;
import com.tiantian.item.business.ContentBusiness;
import com.tiantian.item.pojo.Content;
import com.tiantian.item.vo.ContentVo;

@Service("contentService")
public class ContentServiceImp implements ContentService {

	@Autowired
	private ContentBusiness contentBusiness;
	@Autowired
	private Mapper dozerMapper;

	@Override
	public void deleteByIds(Long[] ids) {
		contentBusiness.deleteById(ids);
	}

	@Override
	public PageResult<ContentVo> queryListPage(ContentBo contentBo) {
		Content content=dozerMapper.map(contentBo, Content.class);
		PageInfo<Content> pageInfo= contentBusiness.queryListPage(content);
		@SuppressWarnings("unchecked")
		PageResult<ContentVo> result=dozerMapper.map(pageInfo, PageResult.class);
		result.setRows(pojoToVo(pageInfo.getList()));
		return result;
	}

	@Override
	public void save(ContentBo contentBo) {
		Content content=dozerMapper.map(contentBo, Content.class);
		contentBusiness.save(content);
		
	}

	@Override
	public void update(ContentBo contentBo) {
		Content content=dozerMapper.map(contentBo, Content.class);
		contentBusiness.update(content);
	}
	
	
	/**
	 * pojo转vo对象
	 * @param contentCategorys
	 * @return
	 */
	private List<ContentVo>  pojoToVo(List<Content>  contents){
		List<ContentVo> list=new ArrayList<ContentVo>();
		for(Content cc:contents){
			ContentVo vo=dozerMapper.map(cc, ContentVo.class);
			list.add(vo);
		}
		return list;
	}

}

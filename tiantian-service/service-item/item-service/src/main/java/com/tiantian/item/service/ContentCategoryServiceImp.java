package com.tiantian.item.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.item.apis.ContentCategoryService;
import com.tiantian.item.bo.ContentCategoryBo;
import com.tiantian.item.business.ContentCategoryBusiness;
import com.tiantian.item.pojo.ContentCategory;
import com.tiantian.item.vo.ContentCategoryVo;

@Service("contentCategoryService")
public class ContentCategoryServiceImp implements ContentCategoryService {

	@Autowired
	private Mapper dozerMapper;
	@Autowired
	ContentCategoryBusiness contentCategoryBusiness;

	@Override
	public Integer save(ContentCategoryBo contentCategory) {
		//bo转po
		ContentCategory cc=dozerMapper.map(contentCategory, ContentCategory.class);
		return contentCategoryBusiness.save(cc);
	}

	@Override
	public Integer deleteById(Serializable id) {
		return contentCategoryBusiness.deleteById(id);
	}

	@Override
	public List<ContentCategoryVo> queryList(ContentCategoryBo contentCategory) {
		ContentCategory cc=dozerMapper.map(contentCategory, ContentCategory.class);
		List<ContentCategory> list= contentCategoryBusiness.queryList(cc);
		List<ContentCategoryVo> result=pojoToVo(list);
		return result;
	}

	@Override
	public Integer updateSelective(ContentCategoryBo contentCategory) {
		ContentCategory cc=dozerMapper.map(contentCategory, ContentCategory.class);
		return contentCategoryBusiness.updateSelective(cc);
	}
	/**
	 * pojo转vo对象
	 * @param contentCategorys
	 * @return
	 */
	private List<ContentCategoryVo>  pojoToVo(List<ContentCategory>  contentCategorys){
		List<ContentCategoryVo> list=new ArrayList<ContentCategoryVo>();
		for(ContentCategory cc:contentCategorys){
			ContentCategoryVo vo=dozerMapper.map(cc, ContentCategoryVo.class);
			list.add(vo);
		}
		return list;
	}

}

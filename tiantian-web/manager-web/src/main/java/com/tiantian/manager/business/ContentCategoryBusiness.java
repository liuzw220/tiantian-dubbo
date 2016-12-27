package com.tiantian.manager.business;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.item.apis.ContentCategoryService;

@Service
public class ContentCategoryBusiness {

	@Autowired(required=false)
	private ContentCategoryService  contentCategoryService;


	public Integer deleteById(Serializable id){
		return contentCategoryService.deleteById(id);
	}

}

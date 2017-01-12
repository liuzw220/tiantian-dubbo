package com.tiantian.item.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.item.apis.ItemDescService;
import com.tiantian.item.bo.ItemDescBo;
import com.tiantian.item.dao.ItemDescDao;
import com.tiantian.item.pojo.ItemDesc;
import com.tiantian.item.vo.ItemDescVo;

@Service("itemDescService")
public class ItemDescServiceImp implements ItemDescService {

	@Autowired
	private ItemDescDao  itemDescDao;
	@Autowired
	private Mapper dozerMapper;
	@Override
	public ItemDescVo querySingle(ItemDescBo itemDescBo) {
		ItemDesc  itemDesc=dozerMapper.map(itemDescBo, ItemDesc.class);
		ItemDesc result=itemDescDao.findSingle(itemDesc);
		return dozerMapper.map(result, ItemDescVo.class);
	}

}

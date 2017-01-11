package com.tiantian.item.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.item.apis.ItemParamItemService;
import com.tiantian.item.business.ItemParamItemBusiness;
import com.tiantian.item.pojo.ItemParamItem;
import com.tiantian.item.vo.ItemParamItemVo;

@Service("itemParamItemService")
public class ItemParamItemServiceImp implements ItemParamItemService {
	@Autowired
	private ItemParamItemBusiness  itemParamItemBusiness;
	@Autowired
	private Mapper dozerMapper;
	@Override
	public ItemParamItemVo queryByitemId(Long itemId) {
		ItemParamItem itemParamItem = itemParamItemBusiness.queryByitemId(itemId);
		return dozerMapper.map(itemParamItem, ItemParamItemVo.class);
	}

}

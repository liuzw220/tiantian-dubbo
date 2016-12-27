package com.tiantian.item.service;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.tiantian.item.apis.ItemParamService;
import com.tiantian.item.bo.ItemParamBo;
import com.tiantian.item.vo.ItemParamVo;

@Service("itemParamService")
public class ItemParamServiceImp implements ItemParamService {

	@Override
	public ItemParamVo queryByItemCatId(Long itemCatId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<ItemParamVo> queryAllPage(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ItemParamVo itemParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSelective(ItemParamBo itemParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}

package com.tiantian.item.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.tiantian.core.beans.PageResult;
import com.tiantian.item.apis.ItemParamService;
import com.tiantian.item.bo.BaseBo;
import com.tiantian.item.bo.ItemParamBo;
import com.tiantian.item.business.ItemParamBusiness;
import com.tiantian.item.pojo.ItemParam;
import com.tiantian.item.vo.ItemParamVo;

@Service("itemParamService")
public class ItemParamServiceImp implements ItemParamService {

	@Autowired
	private ItemParamBusiness  itemParamBusiness;
	@Autowired
	private Mapper dozerMapper;

	@Override
	public ItemParamVo queryByItemCatId(Long itemCatId) {
		ItemParam itemParam = itemParamBusiness.queryByItemCatId(itemCatId);
		return dozerMapper.map(itemParam, ItemParamVo.class);
	}

	@Override
	public PageResult<ItemParamVo> queryAllPage(BaseBo bo) {
		PageInfo<ItemParam> itemParams = itemParamBusiness.queryListPage(bo);
		@SuppressWarnings("unchecked")
		PageResult<ItemParamVo> result=dozerMapper.map(itemParams, PageResult.class);
		result.setRows(pojoToVo(itemParams.getList()));
		return result;
	}

	@Override
	public void save(ItemParamBo itemParamBo) {
		ItemParam itemParam=dozerMapper.map(itemParamBo, ItemParam.class);
		itemParamBusiness.save(itemParam);

	}

	@Override
	public void updateSelective(ItemParamBo itemParamBo) {
		ItemParam itemParam=dozerMapper.map(itemParamBo, ItemParam.class);
		itemParamBusiness.updateSelective(itemParam);
	}

	@Override
	public void deleteById(Long id) {
		itemParamBusiness.deleteById(id);
	}


	/**
	 * pojo转vo对象
	 * @param contentCategorys
	 * @return
	 */
	private List<ItemParamVo>  pojoToVo(List<ItemParam>  itemParams){
		List<ItemParamVo> list=new ArrayList<ItemParamVo>();
		for(ItemParam ip:itemParams){
			ItemParamVo vo=dozerMapper.map(ip, ItemParamVo.class);
			list.add(vo);
		}
		return list;
	}

}

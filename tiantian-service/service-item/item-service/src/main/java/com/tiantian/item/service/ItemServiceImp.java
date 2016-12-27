package com.tiantian.item.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.tiantian.common.bean.EasyUIResult;
import com.tiantian.item.apis.ItemService;
import com.tiantian.item.bo.ItemBo;
import com.tiantian.item.business.ItemBusiness;
import com.tiantian.item.pojo.Item;
import com.tiantian.item.vo.ItemVo;


@Service("itemService")
public class ItemServiceImp implements ItemService {

	@Autowired
	private ItemBusiness itemBusiness;
	@Autowired
	private Mapper dozerMapper;
	
	@Override
	public EasyUIResult queryListPage(Integer pageIndex, Integer pageSize) {
		PageInfo<Item> items=itemBusiness.queryAllPage(pageIndex, pageSize);
		List<Item> is= items.getList();
		EasyUIResult result=new EasyUIResult(items.getTotal(),pojoToVo(is));
		return result;
	}

	@Override
	public Integer save(ItemBo item, String decs, String itemParams) {
		return null;
	}

	@Override
	public Integer updateSelective(ItemBo item, String decs, String itemParams) {
		return null;
	}

	@Override
	public Integer deleteById(Long id) {
		return itemBusiness.deleteById(id);
	}

	@Override
	public ItemVo queryById(Long itemId) {
		Item item=itemBusiness.queryById(itemId);
		ItemVo vo=dozerMapper.map(item, ItemVo.class);
		return vo;
	}

	@Override
	public void deleteByIds(Long[] ids) {
		 itemBusiness.deleteByIds(ids);
	}

	
	/**
	 * pojo转vo对象
	 * @param contentCategorys
	 * @return
	 */
	private List<ItemVo>  pojoToVo(List<Item>  items){
		List<ItemVo> list=new ArrayList<ItemVo>();
		for(Item item:items){
			ItemVo vo=dozerMapper.map(item, ItemVo.class);
			list.add(vo);
		}
		return list;
	}
	
}

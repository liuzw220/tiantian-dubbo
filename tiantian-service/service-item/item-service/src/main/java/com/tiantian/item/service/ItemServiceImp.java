package com.tiantian.item.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.tiantian.core.beans.PageResult;
import com.tiantian.item.apis.ItemService;
import com.tiantian.item.bo.BaseBo;
import com.tiantian.item.bo.ItemBo;
import com.tiantian.item.dao.ItemDao;
import com.tiantian.item.pojo.Item;
import com.tiantian.item.vo.ItemVo;

@SuppressWarnings("unchecked")
@Service("itemService")
public class ItemServiceImp implements ItemService {

	@Autowired
	private ItemDao itemDao;
	@Autowired
	private Mapper dozerMapper;
	
	@Override
	public PageResult<ItemVo> queryListPage(BaseBo bo) {
		PageInfo<Item> items=itemDao.queryListPage(bo);
		PageResult<ItemVo> result=dozerMapper.map(items, PageResult.class);
		result.setRows(pojoToVo(items.getList()));
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
		return itemDao.deleteById(id);
	}

	@Override
	public ItemVo queryById(Long itemId) {
		Item item=itemDao.findById(itemId);
		ItemVo vo=dozerMapper.map(item, ItemVo.class);
		return vo;
	}

	@Override
	public void deleteByIds(Long[] ids) {
		 itemDao.deleteByIds(ids);
	}
	/**
	 * pojo转vo对象
	 * @param contentCategorys
	 * @return
	 */
	private List<ItemVo>  pojoToVo(List<Item>  items){
		if(items==null) {
            return null;
        }
		List<ItemVo> list=new ArrayList<ItemVo>();
		for(Item item:items){
			ItemVo vo=dozerMapper.map(item, ItemVo.class);
			list.add(vo);
		}
		return list;
	}
	
}

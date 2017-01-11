package com.tiantian.item.business;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiantian.item.bo.BaseBo;
import com.tiantian.item.pojo.ItemParam;

@Service
public class ItemParamBusiness extends BaseBusiness<ItemParam> {

	/**
	 *  通过商品类目id查找对应的商品规格
	 * @param itemCatId
	 * @return
	 */
	public ItemParam queryByItemCatId(Long itemCatId) {
		ItemParam itemParam=new ItemParam();
		itemParam.setItemCatId(itemCatId);
		return super.findSingle(itemParam);
	}

	public PageInfo<ItemParam> queryListPage(BaseBo bo) {
		// 设置分页参数
		PageHelper.startPage(bo.getPageIndex(), bo.getPageSize());
		return super.queryListPage(new ItemParam());
	}



}

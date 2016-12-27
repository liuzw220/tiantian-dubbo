package com.tiantian.item.apis;

import com.github.pagehelper.PageInfo;
import com.tiantian.item.bo.ItemParamBo;
import com.tiantian.item.vo.ItemParamVo;

public interface ItemParamService {

	/**
	 *  通过商品类目id查找对应的商品规格
	 * @param itemCatId
	 * @return
	 */
	public abstract ItemParamVo queryByItemCatId(Long itemCatId);

	public abstract PageInfo<ItemParamVo> queryAllPage(Integer page, Integer rows);

	public abstract void save(ItemParamVo itemParam);

	public abstract void updateSelective(ItemParamBo itemParam);

	public abstract void deleteById(Long id);

}
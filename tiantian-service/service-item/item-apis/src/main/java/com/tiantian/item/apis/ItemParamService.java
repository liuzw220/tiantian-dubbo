package com.tiantian.item.apis;

import com.tiantian.core.beans.PageResult;
import com.tiantian.item.bo.BaseBo;
import com.tiantian.item.bo.ItemParamBo;
import com.tiantian.item.vo.ItemParamVo;

public interface ItemParamService {

	/**
	 *  通过商品类目id查找对应的商品规格
	 * @param itemCatId
	 * @return
	 */
	public abstract ItemParamVo queryByItemCatId(Long itemCatId);

	public abstract PageResult<ItemParamVo> queryAllPage(BaseBo bo);

	public abstract void save(ItemParamBo itemParam);

	public abstract void updateSelective(ItemParamBo itemParam);

	public abstract void deleteById(Long id);

}
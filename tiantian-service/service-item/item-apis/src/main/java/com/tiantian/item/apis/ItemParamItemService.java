package com.tiantian.item.apis;

import com.tiantian.item.vo.ItemParamItemVo;

public interface ItemParamItemService {

	/**
	 * 通过商品id 查找商品对应的规格参数(数据)
	 * @param itemId
	 * @return
	 */
	public abstract ItemParamItemVo queryByitemId(Long itemId);

}
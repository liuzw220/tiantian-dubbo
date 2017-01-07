package com.tiantian.item.apis;

import java.util.List;

import com.tiantian.item.beans.ItemCatResult;
import com.tiantian.item.vo.ItemCatVo;

public interface ItemCatService {

	public abstract List<ItemCatVo> queryItemCatList(Long parentId);

	/**
	 * 提供商品目录接口,获取所有的商品目录(通过树的形式返回)
	 * @return 树形结构的商品目录
	 */
	public abstract ItemCatResult queryItemCatWebAll();

	public abstract ItemCatVo queryById(long id);

}
package com.tiantian.item.apis;

import com.tiantian.core.beans.PageResult;
import com.tiantian.item.bo.BaseBo;
import com.tiantian.item.bo.ItemBo;
import com.tiantian.item.vo.ItemVo;

public interface ItemService {

	/**
	 * 五条件的查询商品(分页)
	 * @param pageIndex 当前页码
	 * @param pageSize 页面大小
	 * @return PageOperation 结果集(包含，总记录跳数和商品的集合)
	 */
	
	public abstract PageResult<ItemVo> queryListPage(BaseBo bo);

	/**
	 * 添加商品，同时添加一条商品描述
	 * @param item 商品对象
	 * @param decs 商品描述
	 * @return 受影响的行数
	 */
	public abstract Integer save(ItemBo item, String decs, String itemParams);

	/**
	 * 修改商品，同时修改商品描述,只修改不为空的字段
	 * @param item 商品对象
	 * @param decs 商品描述
	 * @return 受影响的行数
	 */
	public abstract Integer updateSelective(ItemBo item, String decs,
			String itemParams);

	/**
	 * 通过商品id删除商品
	 * @param id
	 * @return
	 */
	public abstract Integer deleteById(Long id);

	public abstract ItemVo queryById(Long itemId);

	public abstract void deleteByIds(Long[] ids);

}
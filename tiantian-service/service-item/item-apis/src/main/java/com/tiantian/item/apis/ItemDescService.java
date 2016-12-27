package com.tiantian.item.apis;

import com.tiantian.item.bo.ItemDescBo;
import com.tiantian.item.vo.ItemDescVo;

public interface ItemDescService {

	public abstract ItemDescVo querySingle(ItemDescBo itemDesc);

}
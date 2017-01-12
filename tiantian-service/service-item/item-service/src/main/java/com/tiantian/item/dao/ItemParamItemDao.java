package com.tiantian.item.dao;

import org.springframework.stereotype.Service;

import com.tiantian.item.pojo.ItemParamItem;

@Service
public class ItemParamItemDao extends BaseDao<ItemParamItem> {

    /**
     * 通过商品id 查找商品对应的规格参数(数据)
     * @param itemId
     * @return
     */
    public ItemParamItem queryByitemId(Long itemId) {
        ItemParamItem itemParamItem=new ItemParamItem();
        itemParamItem.setItemId(itemId);
        return super.findSingle(itemParamItem);
    }

}

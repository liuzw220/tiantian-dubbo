package com.tiantian.item.business;

import org.springframework.stereotype.Service;

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

}

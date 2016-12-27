package com.tiantian.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiantian.item.apis.ItemParamItemService;
import com.tiantian.item.vo.ItemParamItemVo;

@Controller
@RequestMapping("item/param/item")
public class ItemParamItemController {

	 @Autowired(required=false)
    private ItemParamItemService  itemParamItemService;
    
    /**
     *  提供rest接口，通过商品id查找该商品对象的商品规格
     * @param id
     * @return
     */
    @RequestMapping(value="{itemId}",method=RequestMethod.GET)
    public ResponseEntity<ItemParamItemVo> queryById(@PathVariable("itemId")Long itemId){
        try {
            ItemParamItemVo item=itemParamItemService.queryByitemId(itemId);
            //200
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }
}

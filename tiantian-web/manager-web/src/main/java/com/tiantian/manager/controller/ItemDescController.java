package com.tiantian.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiantian.item.apis.ItemDescService;
import com.tiantian.item.bo.ItemDescBo;
import com.tiantian.item.vo.ItemDescVo;

@Controller
@RequestMapping("item/desc")
public class ItemDescController {

	 @Autowired(required=false)
    private ItemDescService itemDescService;
    /**
     * 通过商品id找到对象的商品描述
     * @param itemId 商品id
     * @return 商品描述对象
     */
    @RequestMapping(value="{itemId}", method=RequestMethod.GET)
    public ResponseEntity<ItemDescVo> queryDescByItemId(@PathVariable("itemId")Long itemId){
        try {
            ItemDescBo itemDesc=new ItemDescBo();
            itemDesc.setItemId(itemId);
            ItemDescVo desc = itemDescService.querySingle(itemDesc);
            //200
            return  ResponseEntity.ok(desc);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

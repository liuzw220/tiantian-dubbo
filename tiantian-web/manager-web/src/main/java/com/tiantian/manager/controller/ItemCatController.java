package com.tiantian.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.common.bean.ItemCatResult;
import com.tiantian.item.apis.ItemCatService;
import com.tiantian.item.vo.ItemCatVo;

@Controller
@RequestMapping("item/cat")
public class ItemCatController {

	 @Autowired(required=false)
    private ItemCatService itemCatService;
    /**
     * rest 风格的接口, 通过父菜单的id查找菜单
     * @param pId
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ItemCatVo>> queryItemCatList(@RequestParam(value = "id", defaultValue = "0")Long pId ){
        try {
            List<ItemCatVo> itemCatList = itemCatService.queryItemCatList(pId);
            //200
            return ResponseEntity.ok(itemCatList);
        } catch (Exception e) {
            e.printStackTrace();
            //写日志
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    /**
     * 提供rest风格的接口，根据商品商品目录id查找商品目录
     * @param id
     * @return
     */
    @RequestMapping(value="{id}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ItemCatVo> queryItemCatById(@PathVariable("id") long id){
        try {
            ItemCatVo itemCat = itemCatService.queryById(id);
            //200
            return ResponseEntity.ok(itemCat);
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    /**
     * 提供商品目录接口,获取所有的商品目录(通过树的形式返回)
     * @return 树形结构的商品目录
     * 
     */
    @RequestMapping(value="web/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ItemCatResult> queryItemCatAll(){
        try {
            ItemCatResult itemCatResult = itemCatService.queryItemCatWebAll();
            //200
            return ResponseEntity.ok(itemCatResult);
        } catch (Exception e) {
            e.printStackTrace();
            //写日志
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}

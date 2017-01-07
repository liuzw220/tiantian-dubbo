package com.tiantian.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiantian.core.beans.PageResult;
import com.tiantian.item.apis.ItemParamService;
import com.tiantian.item.bo.ItemParamBo;
import com.tiantian.item.vo.ItemParamVo;

@RequestMapping("item/param")
@Controller
public class ItemParamController {

	 @Autowired(required=false)
    private ItemParamService itemParamService;

    /**
     * 提供rest 风格的接口，查询商品规格
     * @param page 当前页码
     * @param rows
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<PageResult<ItemParamVo>> queryItemParamAll(@RequestParam("page")Integer page,
            @RequestParam("rows") Integer rows){
        try {
        	PageResult<ItemParamVo> pageInfo = itemParamService.queryAllPage(null);
        	PageResult<ItemParamVo> resUiResult=new PageResult<ItemParamVo>(pageInfo.getTotal(),pageInfo.getRows());
            //200
            return  ResponseEntity.ok(resUiResult);
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    /**
     * 提供rest 风格的接口，根据主键查找商品规格(模板)
     * @param itemCatId 商品规格模板
     * @return
     */
    @RequestMapping(value="{itemCatId}", method=RequestMethod.GET)
    public ResponseEntity<ItemParamVo> queryItemParamByItemId(@PathVariable("itemCatId")Long itemCatId){
        try {
            ItemParamVo itemParam=this.itemParamService.queryByItemCatId(itemCatId);
            //200
            return  ResponseEntity.ok(itemParam);
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    /**
     * 提供rest 风格的接口，添加商品规格模板
     * @param itemParam 商品规格对象
     * @return 返回http状态 如果成功返回201
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> saveItemParam(ItemParamVo itemParam){
        try {
            this.itemParamService.save(itemParam);
            //201
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    /**
     * 提供rest 风格的接口，添加商品规格模板
     * @param itemParam 商品规格对象
     * @return 返回http状态 如果成功返回203
     */
    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Void> updateItemParam(ItemParamBo itemParam){
        try {
            this.itemParamService.updateSelective(itemParam);
            //203
            return  ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * 提供rest 风格的接口，删除商品规格模板
     * @param itemParam 商品规格对象
     * @return 返回http状态 如果成功返回203
     */
    @RequestMapping(method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteItemParam(@RequestParam("id")Long id){
        try {
            this.itemParamService.deleteById(id);
            //203
            return  ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

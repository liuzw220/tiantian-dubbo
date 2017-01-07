package com.tiantian.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.core.beans.PageResult;
import com.tiantian.item.apis.ItemService;
import com.tiantian.item.bo.BaseBo;
import com.tiantian.item.bo.ItemBo;
import com.tiantian.item.vo.ItemVo;

@Controller
@RequestMapping("item")
public class ItemController {

	@Autowired(required=false)
	private ItemService itemService;
	/***
	 * 提供了REST接口，分页查询商品数据，商品数据是按照更新时间做倒序排序
	 * @param page 当前页
	 * @param rows 页面大小
	 * @return 返回http状态为200时包含EasyUIResult（按照EasyUI的结构封装的对象）的数据，否则返回500状态
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PageResult<ItemVo>> queryItemList(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows){
		try {
			BaseBo bo=new ItemBo();
			bo.setPageIndex(page);
			bo.setPageSize(rows);
			PageResult<ItemVo> easyUIResult=itemService.queryListPage(bo);
			//200
			return ResponseEntity.ok(easyUIResult);
		} catch (Exception e) {
			// TODO  写日志
			e.printStackTrace();
			//500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@RequestMapping(value="{itemId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ItemVo> queryItemById(@PathVariable("itemId")Long itemId){
		try {
			ItemVo item=itemService.queryById(itemId);
			//200
			return ResponseEntity.ok(item);
		} catch (Exception e) {
			// TODO  写日志
			e.printStackTrace();
			//500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}



	/**
	 * 提供了REST风格的接口，添加商品
	 * @param item 商品对象
	 * @return 添加成功时，返回http状态为201(资源添加成功),出错返回500
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> saveItem(ItemBo item,@RequestParam("desc")String desc,@RequestParam("itemParams")String itemParams){
		try {
			itemService.save(item,desc,itemParams);
			//201
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (Exception e) {
			// TODO  写日志
			e.printStackTrace();
			//500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	/**
	 * 提供了REST风格的接口，修改商品
	 * @param item 商品对象
	 * @return 修改成功时，返回http状态为204(操作已经执行,但没有返回数据),出错返回500
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Void> updateItem(ItemBo item,@RequestParam(value="desc",required=false)String desc,
			@RequestParam(value="itemParams",required=false)String itemParams){
		try {
			itemService.updateSelective(item,desc,itemParams);
			//204
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			// TODO  写日志
			e.printStackTrace();
			//500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	/**
	 * 提供了REST风格的接口，删除商品
	 * @param ids 要删除的id集合
	 * @return 修改成功时，返回http状态为204(操作已经执行,但没有返回数据),出错返回500
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> deleteItem(@RequestParam("ids")Long[] ids){
		try {
			itemService.deleteByIds((Long[])ids);
			//204
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			// TODO  写日志
			e.printStackTrace();
			//500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}

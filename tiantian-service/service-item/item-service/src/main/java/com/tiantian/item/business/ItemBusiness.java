package com.tiantian.item.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiantian.item.bo.BaseBo;
import com.tiantian.item.mapper.ItemDescMapper;
import com.tiantian.item.mapper.ItemParamItemMapper;
import com.tiantian.item.pojo.Item;
import com.tiantian.item.pojo.ItemDesc;
import com.tiantian.item.pojo.ItemParamItem;

@Service
public class ItemBusiness extends BaseBusiness<Item>  {

	//注入商品描述mapper
	@Autowired 
	private ItemDescMapper itemDescMapper;

	//注入商品规格参数数据mapper
	@Autowired 
	private ItemParamItemMapper itemParamItemMapper;
	/**
	 * 五条件的查询商品(分页)
	 * @param pageIndex 当前页码
	 * @param pageSize 页面大小
	 * @return EasyUIResult 结果集(包含，总记录跳数和商品的集合)
	 */
	public PageInfo<Item>  queryListPage(BaseBo bo) {
		Item item=new Item();
		// 设置分页参数
		PageHelper.startPage(bo.getPageIndex(), bo.getPageSize());
		return super.queryListPage(item);
	}

	/**
	 * 添加商品，同时添加一条商品描述
	 * @param item 商品对象
	 * @param decs 商品描述
	 * @return 受影响的行数
	 */
	public Integer save(Item item,String decs,String itemParams) {
		//默认是1 正常(上架)
		item.setId(null);
		item.setStatus(1);
		Integer count = super.save(item);
		//构造商品描述对象
		ItemDesc itemDesc=new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(decs);
		count=+itemDescMapper.insertSelective(itemDesc);
		//构造商品规格参数数据
		ItemParamItem itemParamItem=new ItemParamItem();
		itemParamItem.setId(null);
		itemParamItem.setItemId(item.getId());
		itemParamItem.setParamData(itemParams);
		count=+itemParamItemMapper.insert(itemParamItem);
		//发送添加消息(通知更改缓存和索引库)
		this.sendMsg("insert",item.getId());
		return count;
	}

	/**
	 * 修改商品，同时修改商品描述,只修改不为空的字段
	 * @param item 商品对象
	 * @param decs 商品描述
	 * @return 受影响的行数
	 */
	public Integer updateSelective(Item item,String decs,String itemParams) {
		//如果商品描述为空就不修改  ,上架下架的时候(可以通用)
		if(decs!=null&&!decs.equals("")){
			ItemDesc itemDesc=new ItemDesc();
			itemDesc.setCreated(null);//创建时间不能修改
			itemDesc.setUpdated(new Date());
			itemDesc.setItemId(item.getId());
			itemDesc.setItemDesc(decs);
			itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		}
		if(itemParams!=null&&!itemParams.equals("")){
			ItemParamItem itemParamItem=new ItemParamItem();
			itemParamItem.setItemId(item.getId());
			List<ItemParamItem> itemParamItems = this.itemParamItemMapper.select(itemParamItem);
			//如果该商品存在商品规格参数则修改
			if(itemParamItems!=null&&itemParamItems.size()>0){
				itemParamItem=this.itemParamItemMapper.select(itemParamItem).get(0);
				itemParamItem.setCreated(null);//创建时间不能修改
				itemParamItem.setUpdated(new Date());
				itemParamItem.setParamData(itemParams);
				this.itemParamItemMapper.updateByPrimaryKeySelective(itemParamItem);
			}
			//如果该商品不存在商品规格则为该商品添加商品规格参数
			else{
				itemParamItem.setCreated(new Date());
				itemParamItem.setUpdated(itemParamItem.getCreated());
				itemParamItem.setParamData(itemParams);
				this.itemParamItemMapper.insert(itemParamItem);
			}

		}
		this.sendMsg("update",item.getId());
		return super.updateSelective(item);
	}
	/**
	 * 通过商品id删除商品
	 * @param id
	 * @return
	 */
	public Integer deleteById(Long id) {
		this.sendMsg("delete", id);
		return super.deleteById(id);
	}

	private void sendMsg(String type,Long id){
		Map<String, Object> msg=new HashMap<String, Object>();
		msg.put("type", type);
		msg.put("id", id);
		String result ="";//JsonEntityUtils.entityToJson(msg);
		super.sendMsg("item."+type, result);
	}
}

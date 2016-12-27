package com.tiantian.item.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantain.common.service.RedisService;
import com.tiantian.common.bean.ItemCatData;
import com.tiantian.common.bean.ItemCatResult;
import com.tiantian.common.utils.JsonEntityUtils;
import com.tiantian.item.pojo.ItemCat;

@Service
public class ItemCatBusiness extends BaseBusiness<ItemCat> {

    public List<ItemCat> queryItemCatList(Long parentId) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);
        return super.queryList(itemCat);
    }

    @Autowired(required=false)
    private RedisService redisService;

    //获取所有的菜单存入redis的键
    private static final String REDIS_MANAGE_ITEM_CAT_KEY="MANAGE_ITEM_CAT_ALL";
    //获取所有的菜单存入redis的时间
    private static  final Integer REDIS_MANAGE_ITEM_CAT_Time=60*60*24*30;
    
    /**
     * 提供商品目录接口,获取所有的商品目录(通过树的形式返回)
     * @return 树形结构的商品目录
     */
    public ItemCatResult queryItemCatWebAll() {
        try {
            String eacheData = redisService.get(REDIS_MANAGE_ITEM_CAT_KEY);
            if(eacheData!=null){
                System.out.println("缓存读取");
                //return MAPPER.readValue(eacheData, ItemCatResult.class);
                return (ItemCatResult) JsonEntityUtils.jsonToObject(eacheData, ItemCatResult.class);
            }
        } catch (Exception e1) {
            // TODO 写日志
            System.out.println("读取缓存失败");
            //e1.printStackTrace();
        }
        ItemCatResult result = new ItemCatResult();
        // 全部查出，并且在内存中生成树形结构
        List<ItemCat> cats = super.queryAll();

        // 转为map存储，key为父节点ID，value为数据集合
        Map<Long, List<ItemCat>> itemCatMap = new HashMap<Long, List<ItemCat>>();
        for (ItemCat itemCat : cats) {
            if (!itemCatMap.containsKey(itemCat.getParentId())) {
                itemCatMap.put(itemCat.getParentId(), new ArrayList<ItemCat>());
            }
            itemCatMap.get(itemCat.getParentId()).add(itemCat);
        }
        // 封装一级对象
        List<ItemCat> itemCatList1 = itemCatMap.get(0L);
        for (ItemCat itemCat : itemCatList1) {
            ItemCatData itemCatData = new ItemCatData();
            itemCatData.setUrl("/products/" + itemCat.getId() + ".html");
            itemCatData.setName("<a href='" + itemCatData.getUrl() + "'>" + itemCat.getName() + "</a>");
            result.getItemCats().add(itemCatData);
            if (!itemCat.getIsParent()) {
                continue;
            }
            // 封装二级对象
            List<ItemCat> itemCatList2 = itemCatMap.get(itemCat.getId());
            List<ItemCatData> itemCatData2 = new ArrayList<ItemCatData>();
            itemCatData.setItems(itemCatData2);
            for (ItemCat itemCat2 : itemCatList2) {
                ItemCatData id2 = new ItemCatData();
                id2.setName(itemCat2.getName());
                id2.setUrl("/products/" + itemCat2.getId() + ".html");
                itemCatData2.add(id2);
                if (itemCat2.getIsParent()) {
                    // 封装三级对象
                    List<ItemCat> itemCatList3 = itemCatMap.get(itemCat2.getId());
                    List<String> itemCatData3 = new ArrayList<String>();
                    id2.setItems(itemCatData3);
                    for (ItemCat itemCat3 : itemCatList3) {
                        itemCatData3.add("/products/" + itemCat3.getId() + ".html|" + itemCat3.getName());
                    }
                }
            }
            if (result.getItemCats().size() >= 14) {
                break;
            }
        }
        try {
            redisService.set(REDIS_MANAGE_ITEM_CAT_KEY, JsonEntityUtils.entityToJson(result),REDIS_MANAGE_ITEM_CAT_Time);
        } catch (Exception e) {
            // TODO 写日志
            System.out.println("写入缓存失败");
            //e.printStackTrace();
        }
        System.out.println("数据库读取");
        return result;
    }


}

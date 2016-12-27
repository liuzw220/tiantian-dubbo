package com.tiantian.item.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品规则参数
 * @author schoolBoy
 *
 */
@Table(name="tb_item_param")
public class ItemParam extends BasePojo {
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    //商品类目ID
    @Column(name="item_cat_id")
    private Long itemCatId;
    //参数数据(模板)
    @Column(name="param_data")
    private String paramData;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getItemCatId() {
        return itemCatId;
    }
    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }
    public String getParamData() {
        return paramData;
    }
    public void setParamData(String paramData) {
        this.paramData = paramData;
    }
    
    

}

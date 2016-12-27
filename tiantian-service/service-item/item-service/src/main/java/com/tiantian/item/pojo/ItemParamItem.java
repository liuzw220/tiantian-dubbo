package com.tiantian.item.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品规格和商品的关系
 * @author schoolBoy
 *
 */
@Table(name="tb_item_param_item")
public class ItemParamItem extends BasePojo {

    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    //商品id
    @Column(name="item_id")
    private Long itemId;
    //规格参数数据
    @Column(name="param_data")
    private String paramData;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public String getParamData() {
        return paramData;
    }
    public void setParamData(String paramData) {
        this.paramData = paramData;
    }
    
}

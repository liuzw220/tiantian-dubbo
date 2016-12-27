package com.tiantian.item.vo;


/**
 * 商品规格和商品的关系
 * @author schoolBoy
 *
 */
public class ItemParamItemVo extends BaseVo {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5867286777224625769L;
	//主键
    private Long id;
    //商品id
    private Long itemId;
    //规格参数数据
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

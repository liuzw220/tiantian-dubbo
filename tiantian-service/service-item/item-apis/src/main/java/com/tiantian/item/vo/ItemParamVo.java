package com.tiantian.item.vo;


/**
 * 商品规则参数
 * @author schoolBoy
 *
 */
public class ItemParamVo extends BaseVo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3210050371747462273L;
	//主键
    private Long id;
    //商品类目ID
    private Long itemCatId;
    //参数数据(模板)
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

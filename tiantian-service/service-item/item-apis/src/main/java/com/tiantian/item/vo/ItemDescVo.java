package com.tiantian.item.vo;


public class ItemDescVo extends BaseVo {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6701550468349628720L;
	//商品主键
    private Long itemId;
    //商品描述
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public String getItemDesc() {
        return itemDesc;
    }
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

}

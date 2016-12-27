package com.tiantian.item.bo;


public class ItemCatBo extends BaseBo {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9198051912065164195L;
	private Long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Boolean getIsParent() {
        return isParent;
    }
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    //扩展字段，用于EasyUI中tree结构
    public String getText(){
        return this.name;
    }
    //用户判断当前是否是父节点
    public String getState(){
        return getIsParent() ? "closed" : "open";
    }


}

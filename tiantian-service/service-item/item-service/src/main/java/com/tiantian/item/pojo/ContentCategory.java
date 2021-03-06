package com.tiantian.item.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 内容分类
 * @author schoolBoy
 *
 */
@Table(name="tb_content_category")
public class ContentCategory extends BasePojo {
    //主键 类目ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    //父类目ID=0时
    @Column(name="parent_id")
    private Long parentId;
    //分类名称
    private String name;
    //状态。可选值:1(正常),2(删除)
    private Integer status;
    //排列序号
    @Column(name="sort_order")
    private Integer sortOrder;
    //该类目是否为父类目，1为true，0为false
    @Column(name="is_parent")
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

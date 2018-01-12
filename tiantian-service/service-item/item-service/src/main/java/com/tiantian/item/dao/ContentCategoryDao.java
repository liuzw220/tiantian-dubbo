package com.tiantian.item.dao;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.item.mapper.ContentCategoryMapper;
import com.tiantian.item.pojo.ContentCategory;

@Service
public class ContentCategoryDao extends BaseDao<ContentCategory> {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;
    
    
    /**
     * 添加内容分类
     */
    @Override
    public Integer save(ContentCategory contentCategory) {
        contentCategory.setIsParent(false);//默认不是父节点
        contentCategory.setSortOrder(1);//默认第一位
        contentCategory.setStatus(1);
        Integer count=super.save(contentCategory);
        //查找当前类目的父类目
        ContentCategory cc= super.findById(contentCategory.getParentId());
        //当前父目录不是暂时还不是父目录
        if(cc!=null&&!cc.getIsParent()){
            ContentCategory newContentCategory=new ContentCategory();
            newContentCategory.setId(cc.getId());
            newContentCategory.setIsParent(true);
            count=+super.updateSelective(newContentCategory);
        }
        return count;
    }

    /**
     * 删除内容目录,修改父目录的状态和联级删除子目录
     */
    @Override
    public Integer deleteById(Serializable id) {
        //找到当前目录
        ContentCategory cc= super.findById((long)id);
        //删除当前目录
        Integer count =super.deleteById(id);
        //构造条件目录(用于查找同级目录)
        ContentCategory conditionCc=new ContentCategory();
        conditionCc.setParentId(cc.getParentId());
        //通过当前目录的父id去查找该父目录下的子目录(查找同一级目录)
        if(super.queryList(conditionCc)==null){
            //如果没有找到同级目录(该目录的父目录不存在子目录，那么该父目录将不再是父目录)
            ContentCategory pCc=new ContentCategory();
            pCc.setId(cc.getParentId());
            pCc.setIsParent(false);
            count=+super.update(pCc);
        };
        //删除子级目录
        count=contentCategoryMapper.deleteBypId(id);

        return count;

    }

}

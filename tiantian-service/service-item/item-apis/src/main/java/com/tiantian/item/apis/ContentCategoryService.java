package com.tiantian.item.apis;

import java.io.Serializable;
import java.util.List;

import com.tiantian.item.bo.ContentCategoryBo;
import com.tiantian.item.vo.ContentCategoryVo;

public interface ContentCategoryService {
	
	 /**
     * 添加内容分类
     */
    public Integer save(ContentCategoryBo contentCategory);
    /**
     * 删除内容目录,修改父目录的状态和联级删除子目录
     */
    public Integer deleteById(Serializable id);
    
    
    public List<ContentCategoryVo>  queryList(ContentCategoryBo cc);
    
    public Integer updateSelective(ContentCategoryBo contentCategory);
    
    

}
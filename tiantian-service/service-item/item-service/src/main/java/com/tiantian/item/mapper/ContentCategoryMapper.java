package com.tiantian.item.mapper;

import java.io.Serializable;

import com.tiantian.item.beas.mapper.TiantianMapper;
import com.tiantian.item.pojo.ContentCategory;

public interface ContentCategoryMapper extends TiantianMapper<ContentCategory> {

    /**
     * 根据父目录删除子目录
     * @param id
     * @return
     */
    Integer deleteBypId(Serializable id);

}

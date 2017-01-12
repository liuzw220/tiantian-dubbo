package com.tiantian.item.dao;

import org.springframework.stereotype.Service;

/**
 * 用户获取env配置文件的属性
 * @author schoolBoy
 *
 */
@Service
public class PropertieDao {
    //@Value("${IMAGE_BASE_URL}")
    public String IMAGE_BASE_URL;
    //@Value("${REPOSITORY_PATH}")
    public String REPOSITORY_PATH;
}

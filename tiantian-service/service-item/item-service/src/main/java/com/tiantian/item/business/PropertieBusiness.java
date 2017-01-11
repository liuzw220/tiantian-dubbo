package com.tiantian.item.business;

import org.springframework.stereotype.Service;

/**
 * 用户获取env配置文件的属性
 * @author schoolBoy
 *
 */
@Service
public class PropertieBusiness {
    //@Value("${IMAGE_BASE_URL}")
    public String IMAGE_BASE_URL;
    //@Value("${REPOSITORY_PATH}")
    public String REPOSITORY_PATH;
}

package com.tiantian.member.mapper;

import org.apache.ibatis.annotations.Param;

import com.tiantian.member.pojo.User;

public interface UserMapper {

    /**
     * 提供验证用户是否可用的接口
     * @param param 用户名或者是邮箱或者手机号
     * @param type 类型 1,username 2,phone 3,email
     * @return user对象
     */
    User queryByType(@Param("type")String type, @Param("param")String param);

    /**
     * 添加用户
     * @param user 用户对象
     * @return 是否添加成功
     */
    Boolean save(User user);
}

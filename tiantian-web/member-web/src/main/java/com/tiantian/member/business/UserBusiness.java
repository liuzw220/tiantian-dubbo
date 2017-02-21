package com.tiantian.member.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.member.apis.UserService;
import com.tiantian.member.bo.UserBo;
import com.tiantian.member.vo.UserVo;

@Service
public class UserBusiness {
	
	@Autowired
	private UserService userService;
    /**
     * 提供验证用户是否可用的接口
     * @param param 用户名或者是邮箱或者手机号
     * @param type 类型 1,username 2,phone 3,email
     * @return 布尔类型
     */
    public Boolean checkUser(String param, Integer type) {
        return userService.checkUser(param, type);

    }
    /**
     * 用户注册
     * @param user 用户对象实体
     */
    public Boolean register(UserBo user) {
        return userService.register(user);
    }
    /**
     * 通过用户名查找 用户 
     * @param username 用户名
     * @return 用户对象
     */
    public UserVo findByUserName(String username) {
        return userService.findByUserName(username);
    }
    /**
     * 把一个用户信息保存到redis缓存中
     * @param user
     * @return
     */
    public String saveRedis(UserBo user) {
        return userService.saveRedis(user);
    }
    /**
     * 通过ticket 到redis缓存中命中用户数据,如果命中则更新当前用户数据的过期时间
     * @param ticket 用户对应的ticket的值
     * @return 用户
     */
    public UserVo queryUserByTicket(String ticket) {
        return userService.queryUserByTicket(ticket);
    }

}

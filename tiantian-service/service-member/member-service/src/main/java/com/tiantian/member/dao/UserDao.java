package com.tiantian.member.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.core.utils.JsonEntityUtils;
import com.tiantian.member.mapper.UserMapper;
import com.tiantian.member.pojo.User;
import com.tiantian.service.redis.RedisService;

@Service
public class UserDao {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    private static final Integer USER_SESSION_TIME=60*30;

    private static Map<Integer, String> TYPE=new HashMap<Integer, String>();
    static{
        TYPE.put(1, "username");
        TYPE.put(2, "phone");
        TYPE.put(3, "email");
    }
    /**
     * 提供验证用户是否可用的接口
     * @param param 用户名或者是邮箱或者手机号
     * @param type 类型 1,username 2,phone 3,email
     * @return 布尔类型
     */
    public Boolean checkUser(String param, Integer type) {
        if(!TYPE.containsKey(type))  throw new RuntimeException("参数类型错误,只能是1，,2,3");
        String checkType= TYPE.get(type);
        boolean flag=false;
        User user= userMapper.queryByType(checkType,param);
        if(user==null) flag= true;
        return flag;

    }
    /**
     * 用户注册
     * @param user 用户对象实体
     */
    public Boolean register(User user) {
        User queryByType = userMapper.queryByType("username",user.getUsername());
        if(queryByType!=null) return false;
        return userMapper.save(user);
    }
    /**
     * 通过用户名查找 用户 
     * @param username 用户名
     * @return 用户对象
     */
    public User findByUserName(String username) {
        return userMapper.queryByType("username",username);
    }
    /**
     * 把一个用户信息保存到redis缓存中
     * @param user
     * @return
     */
    public String saveRedis(User user) {
        String ticket=DigestUtils.md5Hex(user.getUsername()+System.currentTimeMillis());
        String josnUser=JsonEntityUtils.entityToJson(user);
        redisService.set(ticket, josnUser, USER_SESSION_TIME);
        return ticket;
    }
    /**
     * 通过ticket 到redis缓存中命中用户数据,如果命中则更新当前用户数据的过期时间
     * @param ticket 用户对应的ticket的值
     * @return 用户
     */
    public User queryUserByTicket(String ticket) {
        String redisData = redisService.get(ticket);
        User user=null;
        if(redisData!=null){
            user=(User) JsonEntityUtils.jsonToObject(redisData, User.class);
            //跟新当前用户的ticket的过期时间
            redisService.expire(ticket, USER_SESSION_TIME);
        }
        return user;
    }

}

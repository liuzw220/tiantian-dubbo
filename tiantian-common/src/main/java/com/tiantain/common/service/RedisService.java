package com.tiantain.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 简化redis api操作
 * @author schoolBoy 刘志伟
 * @data 2015年5月18日 下午1:18:30
 */
@Service
public class RedisService {

    //注入 ShardedJedisPool(链接池对象)
    @Autowired(required=false)
    private ShardedJedisPool shardedJedisPool;

    /**
     * 通用操作
     * @param fun 执行具体业务的接口，限制业务
     * @return 返回执行完毕以后的结果对象
     */
    private <T> T execut(IRedisFunction<ShardedJedis, T> fun){
        ShardedJedis shardedJedis=null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis= shardedJedisPool.getResource();
            //执行业务
            return fun.callBack(shardedJedis);
        } catch (Exception e) {
            // TODO 写日志
            //e.printStackTrace();
            return null;
        }finally{
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
    }
    /**
     * 向redis缓存中添加一个对象
     * @param key 对象名称
     * @param value 对象值
     * @return 返回
     */
    public String set(final String key, final String value) {
        return this.execut(new IRedisFunction<ShardedJedis,String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                return shardedJedis.set(key, value);
            }
        });
    }

    /**
     * 向redis缓存中添加一个对象并同时设置过期时间
     * @param key 对象名称
     * @param value 对象值
     * @param seconds 过期时间
     * @return 返回
     */
    public String set(final String key, final String value,final Integer seconds){
        return this.execut(new IRedisFunction<ShardedJedis,String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                // TODO 可以写日志，提示添加和设置时间成功
                String result= shardedJedis.set(key, value);
                shardedJedis.expire(key, seconds);
                return result;
            }
        });
    }
    /**
     * 向redis缓存中获取一个对象
     * @param key 对象的键
     * @return 对象
     */
    public String get(final String key) {
        return this.execut(new IRedisFunction<ShardedJedis,String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                return shardedJedis.get(key);
            }
        });
    }

    /**
     * 设置一个对象的过期时间
     * @param key 对象的键
     * @param seconds 过期时间
     * @return
     */
    public Long expire(final String key,final Integer seconds){
        return this.execut(new IRedisFunction<ShardedJedis,Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                // TODO 可以写日志，提示设置时间成功
                Long result= shardedJedis.expire(key, seconds);
                return result;
            }
        });
    }

    /**
     * 通过键删除一个对象
     * @param key 对象的键
     * @return 删除的个数
     */
    public Long expire(final String key){
        return this.execut(new IRedisFunction<ShardedJedis,Long>() {
            @Override
            public Long callBack(ShardedJedis e) {
                // TODO 可以写日志，提示删除成功
                Long result= e.del(key);
                return result;
            }
        });
    }

}

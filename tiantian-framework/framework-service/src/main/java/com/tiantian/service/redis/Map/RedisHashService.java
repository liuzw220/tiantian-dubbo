package com.tiantian.service.redis.Map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.ShardedJedis;

import com.tiantian.service.redis.BaseRedis;
import com.tiantian.service.redis.IRedisFunction;

/**
 * 简化redis api操作
 * 
 * @author liuzhiwei
 * 2016年8月5日 下午2:57:21
 */
public class RedisHashService extends BaseRedis {

	/**
	 * 向Hash集合里面存储一个字段
	 * @param key  hash集合的key
	 * @param field 字段名称
	 * @param value 字段值
	 * @return
	 */
	public  Long hset(final String key,final String field, final String value) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return  shardedJedis.hset(key, field, value);
			}
		});
	}
	/**
	 * 向Hash集合里面获取一个字段 的值
	 * @param key hash集合的key
	 * @param field 字段名称
	 * @return
	 */
	public  String hget(final String key, final String field) {
		return this.execute(new IRedisFunction<ShardedJedis, String>() {
			@Override
			public String callBack(ShardedJedis shardedJedis) {
				return shardedJedis.hget(key, field);
			}

		});
	}
	/**
	 * 向Hash集合里面存储一组字段
	 * @param key hash集合的key
	 * @param hashMap 字段和值的集合(HashMap<key, value>)
	 * @return
	 */
	public Boolean  hmset(final String key,final HashMap<String, String> hashMap) {
		return this.execute(new IRedisFunction<ShardedJedis, Boolean>() {
			@Override
			public Boolean callBack(ShardedJedis shardedJedis) {
				return shardedJedis.hmset(key,hashMap).equals("OK");
			}
		});
	}


	/**
	 * 获取指定Hash的指定字段的值的集合<br/>
	 * @param key  hash集合的key
	 * @param fields 字段的集合
	 * @return
	 */
	public List<String>  hmget(final String key,final String... fields) {
		return this.execute(new IRedisFunction<ShardedJedis, List<String>>() {
			@Override
			public  List<String> callBack(ShardedJedis shardedJedis) {
				return shardedJedis.hmget(key, fields);
			}
		});
	}

	/**
	 * 获取指定Hash的所有字段以及对应的值的集合
	 * @param key hash集合的key
	 * @param fields 字段和值的集合 Entry<key, value>
	 * @return
	 */
	public Map<String, String>  hgetAll(final String key,final String... fields) {
		return this.execute(new IRedisFunction<ShardedJedis,Map<String, String>>() {
			@Override
			public  Map<String, String> callBack(ShardedJedis shardedJedis) {
				return  shardedJedis.hgetAll(key);
			}
		});
	}
}

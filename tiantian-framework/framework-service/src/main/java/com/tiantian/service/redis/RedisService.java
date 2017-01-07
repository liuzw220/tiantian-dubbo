package com.tiantian.service.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisService extends BaseRedis {
	public RedisService() {
		super();
	}
	public RedisService(ShardedJedisPool shardedJedisPool) {
		super(shardedJedisPool);
	} 

	/**
	 * 向redis缓存中添加一个对象
	 * @param key 对象名称
	 * @param value 对象值
	 * @return 返回
	 */
	public String set(final String key, final String value) {
		return this.execute(new IRedisFunction<ShardedJedis, String>() {
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
	public String set(final String key, final String value, final Integer seconds) {
		return this.execute(new IRedisFunction<ShardedJedis, String>() {
			@Override
			public String callBack(ShardedJedis shardedJedis) {
				String str = shardedJedis.set(key, value);
				shardedJedis.expire(key, seconds);
				return str;
			}
		});
	}

	 /**
     * 向redis缓存中获取一个对象
     * @param key 对象的键
     * @return 对象
     */
	public String get(final String key) {
		return this.execute(new IRedisFunction<ShardedJedis, String>() {
			@Override
			public String callBack(ShardedJedis shardedJedis) {
				return shardedJedis.get(key);
			}
		});
	}

}

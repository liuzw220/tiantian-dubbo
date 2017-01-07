package com.tiantian.service.redis.Map;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import com.tiantian.service.redis.BaseRedis;
import com.tiantian.service.redis.IRedisFunction;

/**
 * 简化redis api操作
 * 
 * @author liuzhiwei
 * 2016年8月5日 下午2:57:21
 */
public class RedisSetService extends BaseRedis {

	/**
	 * 向set集合中添加元素
	 * @param key set集合的key
	 * @param members 元素,可变参数
	 * @return 
	 */
	public  Long sadd(final String key, final String... members) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return shardedJedis.sadd(key, members);
			}
		});
	}
	/**
	 * 返回set集合中的所有元素
	 * @param key set集合的key
	 * @return 所有元素的集合
	 */
	public  Set<String> smembers(final String key) {
		return this.execute(new IRedisFunction<ShardedJedis, Set<String>>() {
			@Override
			public Set<String> callBack(ShardedJedis shardedJedis) {
				return shardedJedis.smembers(key);
			}

		});
	}

	/**
	 * 获取set集合中的元素的个数
	 * @param key set集合的key
	 * @return 元素的个数
	 */
	public  Long  scard(final String key) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return shardedJedis.scard(key);
			}
		});
	}
	/**
	 * 判断某个元素是否在集合中
	 * @param key set集合的key
	 * @param member 被检验的元素
	 * @return
	 */
	public  Boolean  sismember(final String key,final String member) {
		return this.execute(new IRedisFunction<ShardedJedis, Boolean>() {
			@Override
			public Boolean callBack(ShardedJedis shardedJedis) {
				return shardedJedis.sismember(key,member);
			}
		});
	}
	public  Set<String>  sunion(final  String... key) {
		return this.execute(new IRedisFunction<ShardedJedis, Set<String>>() {
			@Override
			public Set<String> callBack(ShardedJedis shardedJedis) {
				return shardedJedis.getShard(key[0]).sunion(key);
			}
		});
	}
}

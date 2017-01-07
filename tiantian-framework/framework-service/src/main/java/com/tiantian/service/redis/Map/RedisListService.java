package com.tiantian.service.redis.Map;

import java.util.List;

import redis.clients.jedis.ShardedJedis;

import com.tiantian.service.redis.BaseRedis;
import com.tiantian.service.redis.IRedisFunction;

/**
 * 简化redis api操作
 * 
 * @author liuzhiwei
 * 2016年8月5日 下午2:57:21
 */
public class RedisListService extends BaseRedis {
	/**
	 * 存储REDIS队列 顺序存储<br/> 头部插入
	 * @param  key list 的名称
	 * @param  value 键值
	 */
	public  Long lpush(final String key, final String value) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return shardedJedis.lpush(key, value);
			}
		});
	}
	/**
	 * 存储REDIS队列 反向存储 <br/> 尾部插入
	 * @param   key list 的名称
	 * @param   value 键值
	 * @return 
	 */
	public  Long rpush(final String key, final String value) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return shardedJedis.rpush(key, value);
			}

		});
	}
	/**
	 * 存储REDIS队列 元素的个数
	 * @param key list 的名称
	 * @return
	 */
	public  Long  llen(final String key) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return shardedJedis.llen(key);
			}
		});
	}

	/**
	 * 将列表 source 中的第一个元素(头部元素)弹出，并返回给客户端
	 * @param  key reids键名
	 */
	public String  lpop(final String key) {
		return this.execute(new IRedisFunction<ShardedJedis, String>() {
			@Override
			public  String callBack(ShardedJedis shardedJedis) {
				return shardedJedis.lpop(key);
			}
		});
	}


	/**
	 * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
	 * @param  key reids键名
	 */
	public String rpop(final String key) {
		return this.execute(new IRedisFunction<ShardedJedis, String>() {
			@Override
			public  String callBack(ShardedJedis shardedJedis) {
				return shardedJedis.rpop(key);
			}
		});
	}

	/**
	 * 获取队列数据
	 * @param key 键名
	 * @return
	 */
	public  List<String> brpop(final String key) {
		return this.execute(new IRedisFunction<ShardedJedis,  List<String>>() {
			@Override
			public  List<String> callBack(ShardedJedis shardedJedis) {
				return shardedJedis.brpop(1000,key);
			}
		});
	}
}

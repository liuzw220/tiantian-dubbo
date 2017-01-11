package com.tiantian.service.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis 通用的操作方法
 *
 * @author LiuZhiwei <br/>
 *
 *@date 2016年10月8日 下午10:27:10
 *
 */
public abstract class BaseRedis {
	//注入 ShardedJedisPool(链接池对象)
	private ShardedJedisPool shardedJedisPool;
	public BaseRedis() {
		super();
	}
	public BaseRedis(ShardedJedisPool shardedJedisPool) {
		super();
		this.shardedJedisPool = shardedJedisPool;
	}
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}
	/**
	 * 通用操作
	 * @param fun 执行具体业务的接口，限制业务
	 * @return 返回执行完毕以后的结果对象
	 */
	protected  <T> T execute(IRedisFunction<ShardedJedis, T> fun) {
		ShardedJedis shardedJedis = null;
		try {
			// 从连接池中获取到jedis分片对象
			shardedJedis = shardedJedisPool.getResource();
			return fun.callBack(shardedJedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != shardedJedis) {
				// 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
				shardedJedis.close();
			}
		}
		return null;
	}

	/**
	 * 通过键删除一个对象
	 * @param key 对象的键
	 * @return 删除的个数
	 */
	public Long del(final String key) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return shardedJedis.del(key);
			}
		});
	}

	/**
	 * 设置一个对象的过期时间
	 * @param key 对象的键
	 * @param seconds 过期时间
	 * @return
	 */
	public Long expire(final String key, final Integer seconds) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return shardedJedis.expire(key, seconds);
			}
		});
	}
	/**
	 * 获取自增序列
	 * @param key 对象的键
	 * @return
	 */
	public Long incr(final String key) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return shardedJedis.incr(key);
			}
		});
	}

	/**
	 *   获取自定义序列
	 * @param key 对象的键
	 * @param integer 自定义序列
	 * @return
	 */
	public Long incr(final String key,final Integer integer) {
		return this.execute(new IRedisFunction<ShardedJedis, Long>() {
			@Override
			public Long callBack(ShardedJedis shardedJedis) {
				return shardedJedis.incrBy(key, integer);
			}
		});
	}
}

package zx.soft.sns.redis.shard.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import zx.soft.sns.redis.common.RedisProtocol;
import zx.soft.sns.redis.dao.Cache;

public class RedisCache implements Cache {

	private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

	private final ValueShardedJedisPool pool;

	public RedisCache(String redisServers, String password) {
		this(redisServers, RedisProtocol.DEFAULT_PORT, password);
	}

	public RedisCache(String redisServers, int redisPort, String password) {
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		for (String redisServer : redisServers.split(",")) {
			redisServer = redisServer.trim();
			// 设置访问端口
			logger.info("Add redis server: {}:{}", redisServer, redisPort);
			JedisShardInfo jsi = new JedisShardInfo(redisServer, redisPort, RedisProtocol.DEFAULT_TIMEOUT);
			// 设置访问密码
			jsi.setPassword(password);
			shards.add(jsi);
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(RedisProtocol.DEFAULT_MAX_TOTAL);
		config.setMaxIdle(RedisProtocol.DEFAULT_MAX_IDLE);
		config.setMinEvictableIdleTimeMillis(RedisProtocol.DEFAULT_MIN_EVICTABLE_IDLETIME);
		config.setMaxWaitMillis(RedisProtocol.DEFAULT_MAX_WAITTIME);
		pool = new ValueShardedJedisPool(config, shards);
	}

	@Override
	public Long del(String... keys) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			return jedis.del(keys);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public boolean exists(String key) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			return jedis.exists(key);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public void eval(String script, String[] keys, String... members) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			jedis.eval(script, keys, members);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public void sadd(String key, String... members) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			jedis.sadd(key, members);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long scard(String key) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			return jedis.scard(key);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public boolean sismember(String key, String member) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			return jedis.sismember(key, member);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> smembers(String key) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			return jedis.smembers(key);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public String spop(String key) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			return jedis.spop(key);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public String srandmember(String key) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			return jedis.srandmember(key);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> srandmember(String key, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long srem(String key, String... members) {
		ValueShardedJedis jedis = pool.getResource();
		try {
			return jedis.srem(key, members);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public void close() {
		pool.destroy();
	}

}

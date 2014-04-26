package zx.soft.sns.redis.shard.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

/**
 * 根据value值进行分片，原因是：
 *     这里的Redis主要是存放舆情数据的id，所以基本上在少量集和中，
 *     也就是每个集和的数据量很大，而不是集和很多，所以应该针对每个
 *     集和的value进行分片。
 * @author wanggang
 *
 */
public class ValueShardedJedis extends Sharded<Jedis, JedisShardInfo> implements JedisCommands {

	private final Random random = new Random();

	private final Jedis[] allShards;

	private static final Map<String, String> scripts = new HashMap<String, String>();

	public ValueShardedJedis(List<JedisShardInfo> shards) {
		super(shards);
		allShards = getAllShards().toArray(new Jedis[0]);
	}

	public ValueShardedJedis(List<JedisShardInfo> shards, Hashing algo) {
		super(shards, algo);
		allShards = getAllShards().toArray(new Jedis[0]);
	}

	@Override
	public Long append(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long decr(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long decrBy(String key, long integer) {
		throw new UnsupportedOperationException();
	}

	/**
	 *  根据一组key，删除对应的集和。
	 *  对于同一个集和下面的数据可能分布式所有shard上，
	 *  所以需要循环每个shard删除对应的key集和。
	 * @param keys
	 * @return
	 */
	public Long del(String... keys) {
		long result = 0;
		for (Jedis jedis : allShards) {
			result |= jedis.del(keys);
		}
		return result;
	}

	public void eval(String script, String[] keys, String... members) {
		if (members.length == 1) {
			eval(getShard(members[0]), script, keys, members);
		}

		for (Entry<Jedis, List<String>> entry : getShards(members)) {
			eval(entry.getKey(), script, keys, entry.getValue().toArray(new String[entry.getValue().size()]));
		}
	}

	@Override
	public Boolean exists(String key) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Long expire(String key, int seconds) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long expireAt(String key, long unixTime) {
		throw new UnsupportedOperationException();

	}

	@Override
	public String get(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean getbit(String key, long offset) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getSet(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long hdel(String key, String... field) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean hexists(String key, String field) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String hget(String key, String field) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> hkeys(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long hlen(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long hset(String key, String field, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> hvals(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long incr(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long incrBy(String key, long integer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String lindex(String key, long index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long llen(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String lpop(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long lpush(String key, String... string) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long lrem(String key, long count, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String lset(String key, long index, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String ltrim(String key, long start, long end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String rpop(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long rpush(String key, String... string) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long sadd(String key, String... members) {
		if (members.length == 1) {
			return getShard(members[0]).sadd(key, members[0]);
		}

		long result = 0;
		for (Entry<Jedis, List<String>> entry : getShards(members)) {
			result += entry.getKey().sadd(key, entry.getValue().toArray(new String[entry.getValue().size()]));
		}
		return result;
	}

	@Override
	public Long scard(String key) {
		long result = 0;
		for (Jedis jedis : allShards) {
			result += jedis.scard(key);
		}
		return result;
	}

	@Override
	public String set(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean setbit(String key, long offset, boolean value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String setex(String key, int seconds, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long setnx(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long setrange(String key, long offset, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean sismember(String key, String member) {
		return getShard(member).sismember(key, member);
	}

	@Override
	public Set<String> smembers(String key) {
		Set<String> result = new HashSet<String>();
		for (Jedis jedis : allShards) {
			result.addAll(jedis.smembers(key));
		}
		return result;
	}

	@Override
	public List<String> sort(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String spop(String key) {
		// 随机选择一个shard
		Jedis jedis = allShards[random.nextInt(allShards.length)];
		String result = jedis.spop(key);
		if (result != null) {
			return result;
		}

		// 如果碰巧随机到的shard中没有数据，则继续随机剩下所有的shard
		List<Jedis> js = new ArrayList<Jedis>();
		for (Jedis j : allShards) {
			js.add(j);
		}
		js.remove(jedis);
		return spop(key, js);
	}

	@Override
	public String srandmember(String key) {
		// 随机选择一个shard
		Jedis jedis = allShards[random.nextInt(allShards.length)];
		String result = jedis.srandmember(key);
		if (result != null) {
			return result;
		}

		// 如果碰巧随机到的shard中没有数据，则继续随机剩下所有的shard
		List<Jedis> js = new ArrayList<Jedis>();
		for (Jedis j : allShards) {
			js.add(j);
		}
		js.remove(jedis);
		return srandmember(key, js);
	}

	@Override
	public Long srem(String key, String... members) {
		if (members.length == 1) {
			return getShard(members[0]).srem(key, members[0]);
		}

		long result = 0;
		for (Entry<Jedis, List<String>> entry : getShards(members)) {
			result += entry.getKey().srem(key, entry.getValue().toArray(new String[entry.getValue().size()]));
		}
		return result;
	}

	@Override
	public String substr(String key, int start, int end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long ttl(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String type(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zadd(String key, double score, String member) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zcard(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zcount(String key, double min, double max) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zcount(String key, String min, String max) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Double zincrby(String key, double score, String member) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrange(String key, long start, long end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zrank(String key, String member) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zrem(String key, String... member) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zremrangeByScore(String key, String start, String end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrevrange(String key, long start, long end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zrevrank(String key, String member) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Double zscore(String key, String member) {
		throw new UnsupportedOperationException();
	}

	private Object eval(Jedis jedis, String script, String[] keys, String... members) {
		String[] params = new String[keys.length + members.length];
		System.arraycopy(keys, 0, params, 0, keys.length);
		System.arraycopy(members, 0, params, keys.length, members.length);
		String sha = scripts.get(script);
		if (sha == null) {
			sha = DigestUtils.shaHex(script);
			scripts.put(script, sha);
		}
		try {
			return jedis.evalsha(sha, keys.length, params);
		} catch (JedisDataException e) {
			return jedis.eval(script, keys.length, params);
		}
	}

	private Set<Entry<Jedis, List<String>>> getShards(String[] members) {
		Map<Jedis, List<String>> jedisMembersMap = new HashMap<Jedis, List<String>>();
		for (String member : members) {
			Jedis jedis = getShard(member);
			List<String> ms = jedisMembersMap.get(jedis);
			if (ms == null) {
				ms = new ArrayList<String>();
				jedisMembersMap.put(jedis, ms);
			}
			ms.add(member);
		}
		return jedisMembersMap.entrySet();
	}

	private String spop(String key, List<Jedis> jedises) {
		if (jedises.isEmpty()) {
			return null;
		}
		int index = random.nextInt(jedises.size());
		Jedis jedis = jedises.get(index);
		String result = jedis.spop(key);
		if (result != null) {
			return result;
		}
		jedises.remove(index);
		return spop(key, jedises);
	}

	private String srandmember(String key, List<Jedis> jedises) {
		if (jedises.isEmpty()) {
			return null;
		}
		int index = random.nextInt(jedises.size());
		Jedis jedis = jedises.get(index);
		String result = jedis.srandmember(key);
		if (result != null) {
			return result;
		}
		jedises.remove(index);
		return srandmember(key, jedises);
	}

	@Override
	public Long persist(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean setbit(String key, long offset, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long strlen(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long lpushx(String key, String... string) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long rpushx(String key, String... string) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> blpop(String arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> brpop(String arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long del(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String echo(String string) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long move(String key, int dbIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long bitcount(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long bitcount(String key, long start, long end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ScanResult<String> sscan(String key, int cursor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ScanResult<Tuple> zscan(String key, int cursor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		throw new UnsupportedOperationException();
	}

}

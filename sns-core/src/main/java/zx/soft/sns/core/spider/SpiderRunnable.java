package zx.soft.sns.core.spider;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.dao.domain.QQRecordInsert;
import zx.soft.sns.dao.qq.QQInfo;
import zx.soft.sns.parser.core.ParserCore;
import zx.soft.sns.parser.domain.QQRecord;
import zx.soft.sns.redis.dao.Cache;

/**
 * 爬虫实现类
 * @author wanggang
 *
 */
public class SpiderRunnable implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(SpiderRunnable.class);

	private final ParserCore parserCore;

	private final QQInfo qqInfo;

	private final long qq;

	private final Cache cache;

	public static final String CLOSE_USERS_KEY = "sns:qq:closeUsers";

	public static final String PROCESSED_USERS_KEY = "sns:qq:processedUsers";

	public static final String WAIT_USERS_KEY = "sns:qq:waitUsers";

	public static final String INSERTED_QQ_QQGROUP = "sns:qq:inserted";

	private static final String QQ_TABLE = "qq_info_";

	private static AtomicInteger count = new AtomicInteger(0);

	/**
	 * 如果members不在key2和key3所关联的set中，则保存到key1所关联的set中
	 */
	public static final String saddIfNotExistOthers_script = "local count = 0\n" //
			+ "for i, qq in ipairs(ARGV) do\n" //
			+ "    if redis.call('sismember', KEYS[2], qq) == 0 and redis.call('sismember', KEYS[3], qq) == 0 then\n" //
			+ "        redis.call('sadd', KEYS[1], qq)\n" //
			+ "        count = count + 1\n" //
			+ "    end\n" //
			+ "end\n" //
			+ "return count";

	public SpiderRunnable(final Cache cache, ParserCore parserCore, QQInfo qqInfo, long qq) {
		if (qq == 0) {
			throw new IllegalArgumentException("qq is empty.");
		}
		this.qq = qq;
		this.cache = cache;
		this.parserCore = parserCore;
		this.qqInfo = qqInfo;
	}

	@Override
	public void run() {

		logger.info("count=" + count.addAndGet(1));

		try {
			cache.sadd(PROCESSED_USERS_KEY, qq + "");
			List<QQRecord> records = parserCore.parserQQInfo(qq);
			if (records == null) {
				logger.info("qq: " + qq + " has no info.");
				return;
			}
			String[] newqqs = save(records);
			String[] keys = new String[] { WAIT_USERS_KEY, PROCESSED_USERS_KEY, CLOSE_USERS_KEY };
			cache.eval(saddIfNotExistOthers_script, keys, newqqs);
		} catch (Exception e) {
			throw new RuntimeException("qq: " + qq, e);
		}

	}

	private String[] save(List<QQRecord> records) {

		HashSet<String> hs = new HashSet<>();
		for (QQRecord record : records) {
			if (!cache.sismember(INSERTED_QQ_QQGROUP, record.getQq() + "," + record.getQqGroup())) {
				// 下面两句顺序不可改变，否则会导致线程安全
				cache.sadd(INSERTED_QQ_QQGROUP, record.getQq() + "," + record.getQqGroup());
				try {
					qqInfo.insertQQRecord(new QQRecordInsert.Builder(QQ_TABLE + record.getQq() % 32, record.getQq())
							.setName(record.getName()).setAge(record.getAge()).setSex(record.isSex())
							.setQqGroup(record.getQqGroup()).build());
				} catch (Exception e) {
					logger.error("Insert qq=" + record.getQq() + " occurs Exception=" + e);
				}
			}
			hs.add(record.getQq() + "");
			hs.add(record.getQqGroup() + "");
		}

		return hs.toArray(new String[0]);
	}

}

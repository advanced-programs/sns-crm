package zx.soft.sns.core.spider;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sns.core.utils.AnalyzerTool;
import zx.soft.sns.core.utils.CheckSumUtils;
import zx.soft.sns.dao.domain.WeixinRecordInsert;
import zx.soft.sns.dao.weixin.WeixinInfo;
import zx.soft.sns.parser.core.ParserWeixinCore;
import zx.soft.sns.parser.domain.WeixinRecord;
import zx.soft.sns.redis.dao.Cache;

/**
 * 爬虫实现类
 * 
 * @author wanggang
 *
 */
public class SpiderWeixinRunnable implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(SpiderWeixinRunnable.class);

	private static final AnalyzerTool analyzerTool = new AnalyzerTool();

	private final ParserWeixinCore parserCore;

	private final WeixinInfo weixinInfo;

	private final String keyword;

	private final Cache cache;

	public static final String CLOSE_USERS_KEY = "sns:weixin:closeUsers";

	public static final String PROCESSED_USERS_KEY = "sns:weixin:processedUsers";

	public static final String WAIT_USERS_KEY = "sns:weixin:waitUsers";

	public static final String INSERTED_QQ_QQGROUP = "sns:weixin:inserted";

	private static final String WEIXIN_TABLE = "weixin_info_";

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

	public SpiderWeixinRunnable(final Cache cache, ParserWeixinCore parserCore, WeixinInfo weixinInfo, String keyword) {
		if (keyword.length() == 0) {
			throw new IllegalArgumentException("keyword is empty.");
		}
		this.keyword = keyword;
		this.cache = cache;
		this.parserCore = parserCore;
		this.weixinInfo = weixinInfo;
	}

	@Override
	public void run() {

		logger.info("count=" + count.addAndGet(1));

		try {
			cache.sadd(PROCESSED_USERS_KEY, keyword + "");
			List<WeixinRecord> records = parserCore.parserWeixinInfo(keyword);
			if (records == null) {
				logger.info("keyword: " + keyword + " has no info.");
				return;
			}
			String[] newKeywords = save(records);
			String[] keys = new String[] { WAIT_USERS_KEY, PROCESSED_USERS_KEY, CLOSE_USERS_KEY };
			cache.eval(saddIfNotExistOthers_script, keys, newKeywords);
		} catch (Exception e) {
			throw new RuntimeException("keyword: " + keyword, e);
		}

	}

	private String[] save(List<WeixinRecord> records) {

		HashSet<String> hs = new HashSet<>();
		for (WeixinRecord record : records) {
			if (!cache.sismember(INSERTED_QQ_QQGROUP, record.getWid())) {
				// 下面两句顺序不可改变，否则会导致线程安全
				cache.sadd(INSERTED_QQ_QQGROUP, record.getWid());
				try {
					weixinInfo.insertWeixinRecord(new WeixinRecordInsert.Builder(WEIXIN_TABLE
							+ CheckSumUtils.getCRC32(record.getWid()) % 32, record.getWid(), record.getName())
							.setHeadUrl(record.getHeadUrl()).setOpenId(record.getOpenId())
							.setDescription(record.getDescription()).setVerifyInfo(record.getVerifyInfo()).build());
				} catch (Exception e) {
					logger.error("Insert wid=" + record.getWid() + " occurs Exception=" + e);
				}
			}
			String[] words = analyzerTool.analyzerTextToArr(record.getName() + record.getDescription()
					+ record.getVerifyInfo());
			for (String word : words) {
				hs.add(word);
			}
		}

		return hs.toArray(new String[0]);
	}

}

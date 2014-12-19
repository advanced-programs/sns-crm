package zx.soft.sns.core.spider;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.redis.client.cache.Cache;
import zx.soft.redis.client.cache.CacheFactory;
import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.weixin.WeChatDaoImpl;
import zx.soft.sns.parser.wechat.WeChatParser;
import zx.soft.utils.threads.ApplyThreadPool;

/**
 * 爬虫微信公共帐号主程序
 * 
 * @author wanggang
 *
 */
public class SpiderWeChatPAMain {

	private static Logger logger = LoggerFactory.getLogger(SpiderWeChatPAMain.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		String seedKeyword = "娱乐";
		SpiderWeChatPAMain.spider(seedKeyword);
		//		SpiderMain.trancateRedis();
	}

	public static void trancateRedis() {

		Cache cache = CacheFactory.getInstance();
		String[] keys = { SpiderWeChatPARunnable.CLOSE_USERS_KEY, SpiderWeChatPARunnable.INSERTED_WECHAT,
				SpiderWeChatPARunnable.PROCESSED_USERS_KEY, SpiderWeChatPARunnable.WAIT_USERS_KEY };
		cache.del(keys);

		cache.close();
	}

	public static void spider(String seedKeyword) {

		Cache cache = CacheFactory.getInstance();
		logger.info("Add seed keyword: " + seedKeyword);
		cache.sadd(SpiderWeChatPARunnable.WAIT_USERS_KEY, seedKeyword);

		WeChatParser parserCore = new WeChatParser();
		WeChatDaoImpl weixinInfo = new WeChatDaoImpl(MybatisConfig.ServerEnum.sns);

		//		TsdbReporter reporter = new TsdbReporter(Constant.getTsdbHost(), Constant.getTsdbPort());
		//		reporter.addReport(new GatherQueueReport(cache));

		final ThreadPoolExecutor pool = ApplyThreadPool.getThreadPoolExector(16);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				pool.shutdown();
			}
		}));

		while (!pool.isShutdown()) {
			String keyword = cache.spop(SpiderWeChatPARunnable.WAIT_USERS_KEY);
			if (keyword != null) {
				try {
					pool.execute(new SpiderWeChatPARunnable(cache, parserCore, weixinInfo, keyword));
				} catch (IllegalArgumentException e) {
					logger.warn("illegal argumentException, keyword={}", keyword);
				} catch (Exception e) {
					logger.error("Thread exception: " + Thread.currentThread().getName(), e);
					break;
				}
			} else if (pool.getActiveCount() == 0) {
				logger.info("WaitUsers queue is empty, exit...");
				break;
			}
		}

		pool.shutdown();
		try {
			pool.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		cache.close();
	}

}

package zx.soft.sns.core.spider;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.redis.client.cache.Cache;
import zx.soft.redis.client.cache.CacheFactory;
import zx.soft.sns.dao.common.MybatisConfig;
import zx.soft.sns.dao.weixin.WeixinInfo;
import zx.soft.sns.parser.weixin.WeixinParser;

/**
 * 爬虫主类
 * 
 * @author wanggang
 *
 */
public class SpiderWeixinMain {

	private static Logger logger = LoggerFactory.getLogger(SpiderWeixinMain.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		String seedKeyword = "娱乐";
		SpiderWeixinMain.spider(seedKeyword);

		//		SpiderMain.trancateRedis();

	}

	public static void trancateRedis() {

		Cache cache = CacheFactory.getInstance();
		String[] keys = { SpiderWeixinRunnable.CLOSE_USERS_KEY, SpiderWeixinRunnable.INSERTED_QQ_QQGROUP,
				SpiderWeixinRunnable.PROCESSED_USERS_KEY, SpiderWeixinRunnable.WAIT_USERS_KEY };
		cache.del(keys);

		cache.close();
	}

	public static void spider(String seedKeyword) {

		Cache cache = CacheFactory.getInstance();
		logger.info("Add seed keyword: " + seedKeyword);
		cache.sadd(SpiderWeixinRunnable.WAIT_USERS_KEY, seedKeyword);

		WeixinParser parserCore = new WeixinParser();
		WeixinInfo weixinInfo = new WeixinInfo(MybatisConfig.ServerEnum.sns);

		//		TsdbReporter reporter = new TsdbReporter(Constant.getTsdbHost(), Constant.getTsdbPort());
		//		reporter.addReport(new GatherQueueReport(cache));

		final ThreadPoolExecutor pool = getThreadPoolExecutor();

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				pool.shutdown();
			}
		}));

		while (!pool.isShutdown()) {
			String keyword = cache.spop(SpiderWeixinRunnable.WAIT_USERS_KEY);
			if (keyword != null) {
				try {
					pool.execute(new SpiderWeixinRunnable(cache, parserCore, weixinInfo, keyword));
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

	private static ThreadPoolExecutor getThreadPoolExecutor() {

		//		final ThreadPoolExecutor result = new ThreadPoolExecutor(64, 64, 0L, TimeUnit.MILLISECONDS,
		//				new ArrayBlockingQueue<Runnable>(128), new ThreadPoolExecutor.CallerRunsPolicy());
		final ThreadPoolExecutor result = new ThreadPoolExecutor(32, 32, 0L, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(64), new ThreadPoolExecutor.CallerRunsPolicy());
		result.setThreadFactory(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
					@Override
					public void uncaughtException(Thread t, Throwable e) {
						logger.error("Thread exception: " + t.getName(), e);
						result.shutdown();
					}
				});
				return t;
			}
		});

		return result;
	}

}

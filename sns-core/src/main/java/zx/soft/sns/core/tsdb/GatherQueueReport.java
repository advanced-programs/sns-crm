package zx.soft.sns.core.tsdb;

import java.util.ArrayList;
import java.util.List;

import zx.soft.redis.client.cache.Cache;
import zx.soft.sns.core.spider.SpiderWeixinRunnable;

/**
 * 统计Redis中的数据条数等实时数据。
 * @author wanggang
 *
 */
public class GatherQueueReport implements Reportable {

	private final Cache cache;

	public GatherQueueReport(final Cache cache) {
		this.cache = cache;
	}

	@Override
	public List<Tsdb> report() {
		List<Tsdb> result = new ArrayList<Tsdb>();
		long count;
		count = cache.scard(SpiderWeixinRunnable.CLOSE_USERS_KEY);
		result.add(new Tsdb("gather.spider." + SpiderWeixinRunnable.CLOSE_USERS_KEY, count, "type",
				SpiderWeixinRunnable.CLOSE_USERS_KEY + "-count"));
		// 其他数据根据需求添加
		// ......
		// ......
		return result;
	}

}

package zx.soft.sns.core.tsdb;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 用于定时统计Cache中的数据。
 * @author wanggang
 *
 */
public class Tsdb {

	private final String metric;

	private final int time;

	private final Number value;

	private final Map<String, String> tags;

	public Tsdb(String metric, Number value, String... tags) {
		if (tags.length % 2 != 0) {
			throw new RuntimeException("tags format: k1, v1, k2, v2...");
		}
		this.metric = metric;
		this.value = value;
		this.time = (int) (System.currentTimeMillis() / 1000);
		this.tags = new HashMap<String, String>();
		for (int i = 0; i < tags.length; i += 2) {
			this.tags.put(tags[i], tags[i + 1]);
		}
	}

	String serialize() {
		StringBuilder result = new StringBuilder("put ").append(metric).append(" ").append(time).append(" ")
				.append(value);
		for (Entry<String, String> entry : tags.entrySet()) {
			result.append(" ").append(entry.getKey()).append("=").append(entry.getValue());
		}
		return result.toString();
	}

}

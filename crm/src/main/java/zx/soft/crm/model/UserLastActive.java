package zx.soft.crm.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: PM2:28
 * 用户最后交互信息表Model
 */
public class UserLastActive {

	private long uid;
	private long mid;
	private Date last_active_time;
	private int active_count;

	public long getUid() {
		return uid;
	}

	public long getMid() {
		return mid;
	}

	public Date getLast_active_time() {
		return last_active_time;
	}

	public int getActive_count() {
		return active_count;
	}

	public UserLastActive setUid(long uid) {
		this.uid = uid;
		return this;
	}

	public UserLastActive setMid(long mid) {
		this.mid = mid;
		return this;
	}

	public UserLastActive setLast_active_time(Date last_active_time) {
		this.last_active_time = last_active_time;
		return this;
	}

	public UserLastActive setActive_count(int active_count) {
		this.active_count = active_count;
		return this;
	}

	@Override
	public String toString() {
		return "UserLastActive{" +
				"uid=" + uid +
				", mid=" + mid +
				", last_active_time=" + last_active_time +
				", active_count=" + active_count +
				'}';
	}
}

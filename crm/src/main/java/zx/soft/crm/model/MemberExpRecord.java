package zx.soft.crm.model;

import java.util.Date;

public class MemberExpRecord {

	private long uid;

	private long mid;

	private String reason;

	private int exp_change;

	private Date create_time;

	public Date getCreate_time() {
		return create_time;
	}

	public int getExp_change() {
		return exp_change;
	}

	public long getMid() {
		return mid;
	}

	public String getReason() {
		return reason;
	}

	public long getUid() {
		return uid;
	}

	public MemberExpRecord setCreate_time(Date create_time) {
		this.create_time = create_time;
		return this;
	}

	public MemberExpRecord setExp_change(int exp_change) {
		this.exp_change = exp_change;
		return this;
	}

	public MemberExpRecord setMid(long mid) {
		this.mid = mid;
		return this;
	}

	public MemberExpRecord setReason(String reason) {
		this.reason = reason;
		return this;
	}

	public MemberExpRecord setUid(long uid) {
		this.uid = uid;
		return this;
	}

	@Override
	public String toString() {
		return "MemberExpRecord [uid=" + uid + ", mid=" + mid + ", reason=" + reason + ", exp_change=" + exp_change
				+ ", create_time=" + create_time + "]";
	}

}

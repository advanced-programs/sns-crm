package zx.soft.crm.model;

import java.util.Date;

public class MemberPointRecord {

	private long uid;

	private long mid;

	private String reason;

	private int point_change;

	private Date create_time;

	public Date getCreate_time() {
		return create_time;
	}

	public long getMid() {
		return mid;
	}

	public int getPoint_change() {
		return point_change;
	}

	public String getReason() {
		return reason;
	}

	public long getUid() {
		return uid;
	}

	public MemberPointRecord setCreate_time(Date create_time) {
		this.create_time = create_time;
		return this;
	}

	public MemberPointRecord setMid(long mid) {
		this.mid = mid;
		return this;
	}

	public MemberPointRecord setPoint_change(int point_change) {
		this.point_change = point_change;
		return this;
	}

	public MemberPointRecord setReason(String reason) {
		this.reason = reason;
		return this;
	}

	public MemberPointRecord setUid(long uid) {
		this.uid = uid;
		return this;
	}

	@Override
	public String toString() {
		return "MemberPointRecord [uid=" + uid + ", mid=" + mid + ", reason=" + reason + ", point_change="
				+ point_change + ", create_time=" + create_time + "]";
	}

}

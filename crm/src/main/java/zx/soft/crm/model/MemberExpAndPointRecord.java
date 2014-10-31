package zx.soft.crm.model;

import java.util.Date;

public class MemberExpAndPointRecord {

	private long uid;

	private long mid;

	private String type;

	private String reason;

	private int change;

	private Date create_time;

	public int getChange() {
		return change;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public long getMid() {
		return mid;
	}

	public String getReason() {
		return reason;
	}

	public String getType() {
		return type;
	}

	public long getUid() {
		return uid;
	}

	public void setChange(int change) {
		this.change = change;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "MemberExpAndPointRecord [uid=" + uid + ", mid=" + mid + ", type=" + type + ", reason=" + reason
				+ ", change=" + change + ", create_time=" + create_time + "]";
	}

}

package zx.soft.crm.model;

import java.util.Date;

public class Platform {

	private long uid;

	private long mid;

	private int platform;

	private String platform_user_id;

	private Date create_time;

	public Date getCreate_time() {
		return create_time;
	}

	public long getMid() {
		return mid;
	}

	public int getPlatform() {
		return platform;
	}

	public String getPlatform_user_id() {
		return platform_user_id;
	}

	public long getUid() {
		return uid;
	}

	public Platform setCreate_time(Date create_time) {
		this.create_time = create_time;
		return this;
	}

	public Platform setMid(long mid) {
		this.mid = mid;
		return this;
	}

	public Platform setPlatform(int platform) {
		this.platform = platform;
		return this;
	}

	public Platform setPlatform_user_id(String platform_user_id) {
		this.platform_user_id = platform_user_id;
		return this;
	}

	public Platform setUid(long uid) {
		this.uid = uid;
		return this;
	}

	@Override
	public String toString() {
		return "Platform [uid=" + uid + ", mid=" + mid + ", platform=" + platform + ", platform_user_id="
				+ platform_user_id + "]";
	}

}

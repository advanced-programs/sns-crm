package zx.soft.crm.model;

public class UserTag {

	private long uid;

	private long mid;

	private int tag_id;

	public UserTag() {
	}

	public UserTag(long uid, long mid, int tag_id) {
		super();
		this.uid = uid;
		this.mid = mid;
		this.tag_id = tag_id;
	}

	public long getUid() {
		return uid;
	}

	public int getTag_id() {
		return tag_id;
	}

	public long getMid() {
		return mid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	@Override
	public String toString() {
		return "UserTag [uid=" + uid + ", mid=" + mid + ", tag_id=" + tag_id + "]";
	}

}

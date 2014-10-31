package zx.soft.crm.model;

public class TagInfo {

	private long uid;

	private int tag_id;

	private String tag_name;

	public TagInfo() {
		super();
	}

	public TagInfo(long uid, int tag_id) {
		super();
		this.uid = uid;
		this.tag_id = tag_id;
	}

	public TagInfo(long uid, int tag_id, String tag_name) {
		super();
		this.uid = uid;
		this.tag_id = tag_id;
		this.tag_name = tag_name;
	}

	public TagInfo(long uid, String tag_name) {
		super();
		this.uid = uid;
		this.tag_name = tag_name;
	}

	public long getUid() {
		return uid;
	}

	public int getTag_id() {
		return tag_id;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	@Override
	public String toString() {
		return "TagInfo [uid=" + uid + ", tag_id=" + tag_id + ", tag_name=" + tag_name + "]";
	}

}

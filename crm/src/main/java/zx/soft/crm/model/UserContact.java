package zx.soft.crm.model;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: AM11:39
 * 用户联系信息表Model
 */
public class UserContact {

	private long id;
	private long uid;
	private long mid;
	private int contact_id;
	private String value;

	public int getContact_id() {
		return contact_id;
	}

	public UserContact setContact_id(int contact_id) {
		this.contact_id = contact_id;
		return this;
	}

	public long getId() {
		return id;
	}

	public UserContact setId(long id) {
		this.id = id;
		return this;
	}

	public long getMid() {
		return mid;
	}

	public UserContact setMid(long mid) {
		this.mid = mid;
		return this;
	}

	public long getUid() {
		return uid;
	}

	public UserContact setUid(long uid) {
		this.uid = uid;
		return this;
	}

	public String getValue() {
		return value;
	}

	public UserContact setValue(String value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		return "UserContact{" +
				"id=" + id +
				", uid=" + uid +
				", mid=" + mid +
				", contact_id=" + contact_id +
				", value='" + value + '\'' +
				'}';
	}
}

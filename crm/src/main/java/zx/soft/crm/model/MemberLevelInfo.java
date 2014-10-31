package zx.soft.crm.model;

public class MemberLevelInfo {

	private int member_level_id;

	private long uid;

	private String level_name;

	private String level_description;

	private int exp_start;

	private int exp_end;

	private int discount;

	private int status;

	public MemberLevelInfo() {
		super();
	}

	public MemberLevelInfo(int member_level_id, long uid, String level_name, String level_description, int exp_start,
			int exp_end, int discount) {
		super();
		this.member_level_id = member_level_id;
		this.uid = uid;
		this.level_name = level_name;
		this.level_description = level_description;
		this.exp_start = exp_start;
		this.exp_end = exp_end;
		this.discount = discount;
	}

	public MemberLevelInfo(long uid, String level_name, String level_description, int exp_start, int exp_end,
			int discount) {
		super();
		this.uid = uid;
		this.level_name = level_name;
		this.level_description = level_description;
		this.exp_start = exp_start;
		this.exp_end = exp_end;
		this.discount = discount;
	}

	public int getDiscount() {
		return discount;
	}

	public int getExp_end() {
		return exp_end;
	}

	public int getExp_start() {
		return exp_start;
	}

	public String getLevel_description() {
		return level_description;
	}

	public String getLevel_name() {
		return level_name;
	}

	public int getMember_level_id() {
		return member_level_id;
	}

	public long getUid() {
		return uid;
	}

	public int getStatus() {
		return status;
	}

	public MemberLevelInfo setDiscount(int discount) {
		this.discount = discount;
		return this;
	}

	public MemberLevelInfo setExp_end(int exp_end) {
		this.exp_end = exp_end;
		return this;
	}

	public MemberLevelInfo setExp_start(int exp_start) {
		this.exp_start = exp_start;
		return this;
	}

	public MemberLevelInfo setLevel_description(String level_description) {
		this.level_description = level_description;
		return this;
	}

	public MemberLevelInfo setLevel_name(String level_name) {
		this.level_name = level_name;
		return this;
	}

	public MemberLevelInfo setMember_level_id(int member_level_id) {
		this.member_level_id = member_level_id;
		return this;
	}

	public MemberLevelInfo setUid(long uid) {
		this.uid = uid;
		return this;
	}

	public MemberLevelInfo setStatus(int status) {
		this.status = status;
		return this;
	}

	@Override
	public String toString() {
		return "MemberLevelInfo [member_level_id=" + member_level_id + ", uid=" + uid + ", level_name=" + level_name
				+ ", level_description=" + level_description + ", exp_start=" + exp_start + ", exp_end=" + exp_end
				+ ", discount=" + discount + ", status=" + status + "]";
	}

}

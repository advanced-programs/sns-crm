package zx.soft.sns.dao.domain;

public class QQRecordInsert {

	private final String tablename;
	private final long qq; // QQ号
	private final String name; // 群备注
	private final boolean sex; // 性别：0--男，1---女
	private final int age; // 年龄
	private final long qqGroup; // QQ群

	public QQRecordInsert(Builder builder) {
		this.tablename = builder.tablename;
		this.qq = builder.qq;
		this.name = builder.name;
		this.sex = builder.sex;
		this.age = builder.age;
		this.qqGroup = builder.qqGroup;
	}

	public static class Builder {

		private String tablename = "";
		private final long qq; // QQ号
		private String name = ""; // 群备注
		private boolean sex; // 性别：0--男，1---女
		private int age; // 年龄
		private long qqGroup; // QQ群

		public Builder(String tablename, long qq) {
			this.tablename = tablename;
			this.qq = qq;
		}

		public Builder setAge(int age) {
			this.age = age;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setSex(boolean sex) {
			this.sex = sex;
			return this;
		}

		public Builder setQqGroup(long qqGroup) {
			this.qqGroup = qqGroup;
			return this;
		}

		public QQRecordInsert build() {
			return new QQRecordInsert(this);
		}

	}

	public String getTablename() {
		return tablename;
	}

	public long getQq() {
		return qq;
	}

	public String getName() {
		return name;
	}

	public boolean isSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public long getQqGroup() {
		return qqGroup;
	}

}

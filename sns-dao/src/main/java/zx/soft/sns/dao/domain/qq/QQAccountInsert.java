package zx.soft.sns.dao.domain.qq;

public class QQAccountInsert {

	private final String tablename;
	private final long qq;
	private final String name;
	private final String gender;
	private final int age;
	private final long qqGroup;

	public QQAccountInsert(Builder builder) {
		this.tablename = builder.tablename;
		this.qq = builder.qq;
		this.name = builder.name;
		this.gender = builder.gender;
		this.age = builder.age;
		this.qqGroup = builder.qqGroup;
	}

	public static class Builder {

		private String tablename = "";
		private final long qq;
		private String name = "";
		private String gender = "";
		private int age;
		private long qqGroup;

		public Builder(String tablename, long qq, String name) {
			this.tablename = tablename;
			this.qq = qq;
			this.name = name;
		}

		public Builder setGender(String gender) {
			this.gender = gender;
			return this;
		}

		public Builder setAge(int age) {
			this.age = age;
			return this;
		}

		public Builder setQqGroup(long qqGroup) {
			this.qqGroup = qqGroup;
			return this;
		}

		public QQAccountInsert build() {
			return new QQAccountInsert(this);
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

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public long getQqGroup() {
		return qqGroup;
	}

}

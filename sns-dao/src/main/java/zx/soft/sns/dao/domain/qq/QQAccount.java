package zx.soft.sns.dao.domain.qq;

/**
 * QQ帐号信息
 * 
 * @author wanggang
 *
 */
public class QQAccount {

	private final long qq;
	private final String name;
	private final String gender;
	private final int age;
	private final long qqGroup;

	public QQAccount(Builder builder) {
		this.qq = builder.qq;
		this.name = builder.name;
		this.gender = builder.gender;
		this.age = builder.age;
		this.qqGroup = builder.qqGroup;
	}

	public static class Builder {

		private final long qq;
		private String name = "";
		private String gender = "";
		private int age;
		private long qqGroup;

		public Builder(long qq, String name) {
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

		public QQAccount build() {
			return new QQAccount(this);
		}

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

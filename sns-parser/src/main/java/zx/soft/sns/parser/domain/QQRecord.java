package zx.soft.sns.parser.domain;

/**
 * QQ信息
 * @author wanggang
 *
 */
public class QQRecord {

	private final long qq; // QQ号
	private final String name; // 群备注
	private final boolean sex; // 性别：0--男，1---女
	private final int age; // 年龄
	private final long qqGroup; // QQ群

	public QQRecord(Builder builder) {
		this.qq = builder.qq;
		this.name = builder.name;
		this.sex = builder.sex;
		this.age = builder.age;
		this.qqGroup = builder.qqGroup;
	}

	@Override
	public String toString() {
		return "QQRecord:[qq=" + qq + ",name=" + name + ",sex=" + sex + ",age=" + age + ",qqGroup=" + qqGroup + "]";
	}

	public static class Builder {

		private final long qq; // QQ号
		private String name = ""; // 群备注
		private boolean sex; // 性别：0--男，1---女
		private int age; // 年龄
		private long qqGroup; // QQ群

		public Builder(long qq, String name) {
			this.qq = qq;
			this.name = name;
		}

		public Builder setSex(boolean sex) {
			this.sex = sex;
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

		public QQRecord build() {
			return new QQRecord(this);
		}

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

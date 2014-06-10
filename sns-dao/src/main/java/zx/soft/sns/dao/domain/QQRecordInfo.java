package zx.soft.sns.dao.domain;

public class QQRecordInfo {

	private long qq; // QQ号
	private String name = ""; // 群备注
	private boolean sex; // 性别：0--男，1---女
	private int age; // 年龄
	private long qq_group; // QQ群

	@Override
	public String toString() {
		return "QQRecordInfo:[qq=" + qq + ",name=" + name + ",sex=" + sex + ",age=" + age + ",qqGroup=" + qq_group
				+ "]";
	}

	public long getQq() {
		return qq;
	}

	public void setQq(long qq) {
		this.qq = qq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getQq_group() {
		return qq_group;
	}

	public void setQq_group(long qq_group) {
		this.qq_group = qq_group;
	}

}

package zx.soft.sns.dao.domain;

public class QQRecordInfo {

	private long qq; // QQ号
	private String name = ""; // 群备注
	private boolean sex; // 性别：0--男，1---女
	private int age; // 年龄
	private long qqGroup; // QQ群

	@Override
	public String toString() {
		return "QQRecordInfo:[qq=" + qq + ",name=" + name + ",sex=" + sex + ",age=" + age + ",qqGroup=" + qqGroup + "]";
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

	public long getQqGroup() {
		return qqGroup;
	}

	public void setQqGroup(long qqGroup) {
		this.qqGroup = qqGroup;
	}

}

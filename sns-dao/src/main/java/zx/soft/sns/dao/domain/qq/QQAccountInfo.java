package zx.soft.sns.dao.domain.qq;

public class QQAccountInfo {

	private long qq;
	private String name;
	private String gender;
	private int age;
	private long qq_group;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

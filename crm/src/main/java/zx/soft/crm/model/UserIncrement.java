package zx.soft.crm.model;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-24
 * Time: PM2:22
 * 用户增长统计
 */
public class UserIncrement {

	private int count;
	private int platform;
	private String day;

	public int getCount() {
		return count;
	}

	public UserIncrement setCount(int count) {
		this.count = count;
		return this;
	}

	public int getPlatform() {
		return platform;
	}

	public UserIncrement setPlatform(int platform) {
		this.platform = platform;
		return this;
	}

	public String getDay() {
		return day;
	}

	public UserIncrement setDay(String day) {
		this.day = day;
		return this;
	}

	@Override
	public String toString() {
		return "UserIncrement{" +
				"count=" + count +
				", platform=" + platform +
				", day='" + day + '\'' +
				'}';
	}
}

package zx.soft.crm.model;

/**
 * Created with IntelliJ IDEA.
 * User: SnowMan
 * Date: 14-7-15
 * Time: AM9:53
 * 用户扩展表Model
 */
public class UserExt {

	private long uid;
	private long mid;
	private String avatar;
	private String country;
	private String province;
	private String city;
	private String county;
	private String ip;

	public String getAvatar() {
		return avatar;
	}

	public UserExt setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	public String getCity() {
		return city;
	}

	public UserExt setCity(String city) {
		this.city = city;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public UserExt setCountry(String country) {
		this.country = country;
		return this;
	}

	public String getCounty() {
		return county;
	}

	public UserExt setCounty(String county) {
		this.county = county;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public UserExt setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public long getMid() {
		return mid;
	}

	public UserExt setMid(long mid) {
		this.mid = mid;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public UserExt setProvince(String province) {
		this.province = province;
		return this;
	}

	public long getUid() {
		return uid;
	}

	public UserExt setUid(long uid) {
		this.uid = uid;
		return this;
	}

	@Override
	public String toString() {
		return "UserExt{" +
				"uid=" + uid +
				", mid=" + mid +
				", avatar='" + avatar + '\'' +
				", country='" + country + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", county='" + county + '\'' +
				", ip='" + ip + '\'' +
				'}';
	}
}

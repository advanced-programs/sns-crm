package zx.soft.crm.model;

public class Address {

	private long address_id;
	private long mid;
	private String name;
	private String province;
	private String city;
	private String county;
	private String address;
	private String phone;

	public String getAddress() {
		return address;
	}

	public Address setAddress(String address) {
		this.address = address;
		return this;
	}

	public long getAddress_id() {
		return address_id;
	}

	public Address setAddress_id(long address_id) {
		this.address_id = address_id;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Address setCity(String city) {
		this.city = city;
		return this;
	}

	public String getCounty() {
		return county;
	}

	public Address setCounty(String county) {
		this.county = county;
		return this;
	}

	public long getMid() {
		return mid;
	}

	public Address setMid(long mid) {
		this.mid = mid;
		return this;
	}

	public String getName() {
		return name;
	}

	public Address setName(String name) {
		this.name = name;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Address setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public Address setProvince(String province) {
		this.province = province;
		return this;
	}

	@Override
	public String toString() {
		return "Address{" +
				"address_id=" + address_id +
				", mid=" + mid +
				", name='" + name + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", county='" + county + '\'' +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}

package zx.soft.crm.model;

import java.util.Date;

public class Member {

	private long uid;

	private long mid;

	private String address;

	private Date birthday;

	private Integer member_point;

	private Integer experience;

	private String id_card_no;

	private String member_card_no;

	private Integer member_level_id;

	private Date update_time;

	public Member defaultValue() {
		if (address == null) {
			address = "";
		}
		if (member_point == null) {
			member_point = 0;
		}
		if (experience == null) {
			experience = 0;
		}
		if (id_card_no == null) {
			id_card_no = "";
		}
		if (member_card_no == null) {
			member_card_no = "";
		}
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Integer getExperience() {
		return experience;
	}

	public String getId_card_no() {
		return id_card_no;
	}

	public String getMember_card_no() {
		return member_card_no;
	}

	public Integer getMember_level_id() {
		return member_level_id;
	}

	public Integer getMember_point() {
		return member_point;
	}

	public long getMid() {
		return mid;
	}

	public long getUid() {
		return uid;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public Member setAddress(String address) {
		this.address = address;
		return this;
	}

	public Member setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}

	public Member setExperience(Integer experience) {
		this.experience = experience;
		return this;
	}

	public Member setId_card_no(String id_card_no) {
		this.id_card_no = id_card_no;
		return this;
	}

	public Member setMember_card_no(String member_card_no) {
		this.member_card_no = member_card_no;
		return this;
	}

	public Member setMember_level_id(Integer member_level_id) {
		this.member_level_id = member_level_id;
		return this;
	}

	public Member setMember_point(Integer member_point) {
		this.member_point = member_point;
		return this;
	}

	public Member setMid(long mid) {
		this.mid = mid;
		return this;
	}

	public Member setUid(long uid) {
		this.uid = uid;
		return this;
	}

	public Member setUpdate_time(Date update_time) {
		this.update_time = update_time;
		return this;
	}

	@Override
	public String toString() {
		return "Member [uid=" + uid + ", mid=" + mid + ", address=" + address + ", birthday=" + birthday
				+ ", member_point=" + member_point + ", experience=" + experience + ", id_card_no=" + id_card_no
				+ ", member_card_no=" + member_card_no + ", member_level_id=" + member_level_id + "]";
	}

}

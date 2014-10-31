package zx.soft.crm.model;

import java.util.Date;
import java.util.List;

public class User {

	private long uid;
	private long mid;
	private Integer is_member;
	private String identify;
	private String name;
	private String nick;
	private Integer gender;
	private Date update_time;
	private int status;
	private Member member;
	private List<Platform> platforms;
	private List<UserTag> tags;
	private List<UserContact> userContacts;
	private List<UserLastActive> userLastActives;
	private List<TagInfo> tagInfos;
	private List<UserExt> userExts;

	public User defaultValue() {
		if (is_member == null) {
			is_member = 0;
		}
		if (identify == null) {
			identify = "";
		}
		if (name == null) {
			name = "";
		}
		if (nick == null) {
			nick = "";
		}
		if (gender == null) {
			gender = 0;
		}
		return this;
	}

	public int getGender() {
		return gender;
	}

	public User setGender(int gender) {
		this.gender = gender;
		return this;
	}

	public String getIdentify() {
		return identify;
	}

	public User setIdentify(String identify) {
		this.identify = identify;
		return this;
	}

	public int getIs_member() {
		return is_member;
	}

	public User setIs_member(int is_member) {
		this.is_member = is_member;
		return this;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public long getMid() {
		return mid;
	}

	public User setMid(long mid) {
		this.mid = mid;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getNick() {
		return nick;
	}

	public User setNick(String nick) {
		this.nick = nick;
		return this;
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public int getStatus() {
		return status;
	}

	public User setStatus(int status) {
		this.status = status;
		return this;
	}

	public List<TagInfo> getTagInfos() {
		return tagInfos;
	}

	public void setTagInfos(List<TagInfo> tagInfos) {
		this.tagInfos = tagInfos;
	}

	public List<UserTag> getTags() {
		return tags;
	}

	public void setTags(List<UserTag> tags) {
		this.tags = tags;
	}

	public long getUid() {
		return uid;
	}

	public User setUid(long uid) {
		this.uid = uid;
		return this;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public User setUpdate_time(Date update_time) {
		this.update_time = update_time;
		return this;
	}

	public List<UserContact> getUserContacts() {
		return userContacts;
	}

	public void setUserContacts(List<UserContact> userContacts) {
		this.userContacts = userContacts;
	}

	public List<UserExt> getUserExts() {
		return userExts;
	}

	public void setUserExts(List<UserExt> userExts) {
		this.userExts = userExts;
	}

	public List<UserLastActive> getUserLastActives() {
		return userLastActives;
	}

	public void setUserLastActives(List<UserLastActive> userLastActives) {
		this.userLastActives = userLastActives;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", mid=" + mid + ", is_member=" + is_member + ", identify=" + identify + ", name="
				+ name + ", nick=" + nick + ", gender=" + gender + ", status=" + status + "]";
	}

}

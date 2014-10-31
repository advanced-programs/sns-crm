package zx.soft.crm.model;

import org.eclipse.jetty.util.StringUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserQueryCondition {

	private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private long uid;
	private int page = 1;
	private int per_page = 20;
	private Integer is_member;
	private Integer tag_id;
	private Integer member_level_id;
	private Integer platform;
	private Integer gender;
	private Boolean has_telephone;
	private Boolean has_email;
	private Boolean has_qq;
	private Boolean has_message;
	private Boolean has_activity;
	private String message_start_time;
	private String message_end_time;
	private String province;
	private String city;
	private Integer status;
	private String nick;
	private String name;
	private String identify;
	private Integer min_member_point;
	private Integer max_member_point;
	private String member_card_no;
	private Date startTime;
	private Date endTime;
	private Boolean has_identify;
	private String con_telephone;

	public String getCity() {
		return city;
	}

	public UserQueryCondition setCity(String city) {
		this.city = city;
		return this;
	}

	public String getCon_telephone() {
		return con_telephone;
	}

	public UserQueryCondition setCon_telephone(String con_telephone) {
		this.con_telephone = con_telephone;
		return this;
	}

	public Date getEndTime() {
		return endTime;
	}

	public UserQueryCondition setEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}

	public Integer getGender() {
		return gender;
	}

	public UserQueryCondition setGender(Integer gender) {
		this.gender = gender;
		return this;
	}

	public Boolean getHas_activity() {
		return has_activity;
	}

	public UserQueryCondition setHas_activity(Boolean has_activity) {
		this.has_activity = has_activity;
		return this;
	}

	public Boolean getHas_email() {
		return has_email;
	}

	public UserQueryCondition setHas_email(Boolean has_email) {
		this.has_email = has_email;
		return this;
	}

	public Boolean getHas_identify() {
		return has_identify;
	}

	public UserQueryCondition setHas_identify(Boolean has_identify) {
		this.has_identify = has_identify;
		return this;
	}

	public Boolean getHas_message() {
		return has_message;
	}

	public UserQueryCondition setHas_message(Boolean has_message) {
		this.has_message = has_message;
		return this;
	}

	public Boolean getHas_qq() {
		return has_qq;
	}

	public UserQueryCondition setHas_qq(Boolean has_qq) {
		this.has_qq = has_qq;
		return this;
	}

	public Boolean getHas_telephone() {
		return has_telephone;
	}

	public UserQueryCondition setHas_telephone(Boolean has_telephone) {
		this.has_telephone = has_telephone;
		return this;
	}

	public String getIdentify() {
		return identify;
	}

	public UserQueryCondition setIdentify(String identify) {
		this.identify = identify;
		return this;
	}

	public Integer getIs_member() {
		return is_member;
	}

	public UserQueryCondition setIs_member(Integer is_member) {
		this.is_member = is_member;
		return this;
	}

	public int getLimit() {
		return per_page;
	}

	public Integer getMax_member_point() {
		return max_member_point;
	}

	public UserQueryCondition setMax_member_point(Integer max_member_point) {
		this.max_member_point = max_member_point;
		return this;
	}

	public String getMember_card_no() {
		return member_card_no;
	}

	public UserQueryCondition setMember_card_no(String member_card_no) {
		this.member_card_no = member_card_no;
		return this;
	}

	public Integer getMember_level_id() {
		return member_level_id;
	}

	public void setMember_level_id(Integer member_level_id) {
		this.member_level_id = member_level_id;
	}

	public String getMessage_end_time() {
		return message_end_time;
	}

	public UserQueryCondition setMessage_end_time(String message_end_time) {
		this.message_end_time = message_end_time;
		return this;
	}

	public String getMessage_start_time() {
		return message_start_time;
	}

	public UserQueryCondition setMessage_start_time(String message_start_time) {
		this.message_start_time = message_start_time;
		return this;
	}

	public Integer getMin_member_point() {
		return min_member_point;
	}

	public UserQueryCondition setMin_member_point(Integer min_member_point) {
		this.min_member_point = min_member_point;
		return this;
	}

	public String getName() {
		return name;
	}

	public UserQueryCondition setName(String name) {
		this.name = name;
		return this;
	}

	public String getNick() {
		return nick;
	}

	public UserQueryCondition setNick(String nick) {
		this.nick = nick;
		return this;
	}

	public int getOffset() {
		return (page - 1) * per_page;
	}

	public int getPage() {
		return page;
	}

	public UserQueryCondition setPage(int page) {
		this.page = page;
		return this;
	}

	public int getPer_page() {
		return per_page;
	}

	public UserQueryCondition setPer_page(int per_page) {
		this.per_page = per_page;
		return this;
	}

	public Integer getPlatform() {
		return platform;
	}

	public UserQueryCondition setPlatform(Integer platform) {
		this.platform = platform;
		return this;

	}

	public String getProvince() {
		return province;
	}

	public UserQueryCondition setProvince(String province) {
		this.province = province;
		return this;
	}

	public Date getStartTime() {
		return startTime;
	}

	public UserQueryCondition setStartTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public UserQueryCondition setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Integer getTag_id() {
		return tag_id;
	}

	public UserQueryCondition setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
		return this;
	}

	public long getUid() {
		return uid;
	}

	public UserQueryCondition setUid(long uid) {
		this.uid = uid;
		return this;
	}

	public void setDefualt() {
		if (StringUtil.isBlank(this.message_start_time)) {
			this.startTime = null;
		} else {
			try {
				startTime = formatter.parse(this.message_start_time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (StringUtil.isBlank(this.message_end_time)) {
			this.endTime = null;
		} else {
			try {
				endTime = formatter.parse(this.message_end_time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}


	}
}

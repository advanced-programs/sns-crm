package zx.soft.sns.dao.domain;

public class WeixinRecordInfo {

	private String wid;
	private String name;
	private String open_id;
	private String head_url;
	private String description;
	private String verify_info;

	@Override
	public String toString() {
		return "WeixinRecord:[wid=" + wid + ",name=" + name + ",openId=" + open_id + ",headUrl=" + head_url
				+ ",description=" + description + ",verifyInfo=" + verify_info + "]";
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getHead_url() {
		return head_url;
	}

	public void setHead_url(String head_url) {
		this.head_url = head_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVerify_info() {
		return verify_info;
	}

	public void setVerify_info(String verify_info) {
		this.verify_info = verify_info;
	}

}

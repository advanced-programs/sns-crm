package zx.soft.sns.dao.domain.wechat;

import java.util.Date;

public class WeChatAInfo {

	private String aid;
	private String title;
	private String url;
	private String pic_url;
	private Date timestamp;
	private String wechat_name;
	private String wechat_open_id;
	private String content;

	public WeChatAInfo() {
		//		
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getWechat_name() {
		return wechat_name;
	}

	public void setWechat_name(String wechat_name) {
		this.wechat_name = wechat_name;
	}

	public String getWechat_open_id() {
		return wechat_open_id;
	}

	public void setWechat_open_id(String wechat_open_id) {
		this.wechat_open_id = wechat_open_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

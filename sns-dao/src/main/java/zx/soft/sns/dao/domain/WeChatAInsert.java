package zx.soft.sns.dao.domain;

import java.util.Date;

/**
 * 微信公共号文章数据模型
 * 
 * @author wgybzb
 *
 */
public class WeChatAInsert {

	private final String tablename;
	private final String aid;
	private final String title;
	private final String url;
	private final String picUrl;
	private final Date timestamp;
	private final String weChatName;
	private final String weChatOpenId;
	private final String content;

	public WeChatAInsert(Builder builder) {
		this.tablename = builder.tablename;
		this.aid = builder.aid;
		this.title = builder.title;
		this.url = builder.url;
		this.picUrl = builder.picUrl;
		this.timestamp = builder.timestamp;
		this.weChatName = builder.weChatName;
		this.weChatOpenId = builder.weChatOpenId;
		this.content = builder.content;
	}

	public static class Builder {

		private String tablename = "";
		private String aid = "";
		private String title = "";
		private String url = "";
		private String picUrl = "";
		private Date timestamp = null;
		private String weChatName = "";
		private String weChatOpenId = "";
		private String content = "";

		public Builder(String tablename, String aid, String title, String url) {
			this.tablename = tablename;
			this.aid = aid;
			this.title = title;
			this.url = url;
		}

		public Builder setPicUrl(String picUrl) {
			this.picUrl = picUrl;
			return this;
		}

		public Builder setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder setWeChatName(String weChatName) {
			this.weChatName = weChatName;
			return this;
		}

		public Builder setWeChatOpenId(String weChatOpenId) {
			this.weChatOpenId = weChatOpenId;
			return this;
		}

		public Builder setContent(String content) {
			this.content = content;
			return this;
		}

		public WeChatAInsert build() {
			return new WeChatAInsert(this);
		}

	}

	public String getTablename() {
		return tablename;
	}

	public String getAid() {
		return aid;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getWeChatName() {
		return weChatName;
	}

	public String getWeChatOpenId() {
		return weChatOpenId;
	}

	public String getContent() {
		return content;
	}

}

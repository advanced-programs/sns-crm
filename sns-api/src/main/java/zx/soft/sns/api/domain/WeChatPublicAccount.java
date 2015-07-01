package zx.soft.sns.api.domain;

import java.io.Serializable;

/**
 * 微信公共帐号数据模型
 *
 * @author wgybzb
 *
 */
public class WeChatPublicAccount implements Serializable {

	private static final long serialVersionUID = -4423937694427388461L;

	// 微信号
	private String wid;
	// 微信名称
	private String name;
	// Open ID
	private String open_id;
	// 头像地址
	private String head_url;
	// 功能介绍
	private String description;
	// 微信认证
	private String verify_info;
	// 最近文章地址
	private String last_article_url;

	public WeChatPublicAccount() {
		super();
	}

	public WeChatPublicAccount(Builder builder) {
		this.wid = builder.wid;
		this.name = builder.name;
		this.open_id = builder.open_id;
		this.head_url = builder.head_url;
		this.description = builder.description;
		this.verify_info = builder.verify_info;
		this.last_article_url = builder.last_article_url;
	}

	public static class Builder {

		private String wid = "";
		private String name = "";
		private String open_id = "";
		private String head_url = "";
		private String description = "";
		private String verify_info = "";
		private String last_article_url = "";

		public Builder(String wid, String name) {
			this.wid = wid;
			this.name = name;
		}

		public Builder setOpen_id(String open_id) {
			this.open_id = open_id;
			return this;
		}

		public Builder setHead_url(String head_url) {
			this.head_url = head_url;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setVerify_info(String verify_info) {
			this.verify_info = verify_info;
			return this;
		}

		public Builder setLast_article_url(String last_article_url) {
			this.last_article_url = last_article_url;
			return this;
		}

		public WeChatPublicAccount build() {
			return new WeChatPublicAccount(this);
		}

	}

	@Override
	public String toString() {
		return "WeChatPublicAccount [wid=" + wid + ", name=" + name + ", open_id=" + open_id + ", head_url=" + head_url
				+ ", description=" + description + ", verify_info=" + verify_info + ", last_article_url="
				+ last_article_url + "]";
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

	public String getLast_article_url() {
		return last_article_url;
	}

	public void setLast_article_url(String last_article_url) {
		this.last_article_url = last_article_url;
	}

}

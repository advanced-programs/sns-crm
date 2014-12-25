package zx.soft.sns.dao.domain.wechat;

import java.util.Date;

/**
 * 微信公共号文章数据模型
 * 
 * @author wgybzb
 *
 */
public class WeChatArticle {

	// 文章唯一ID，由url经MD5加密得到
	private final String aid;
	// 文章标题
	private final String title;
	// 文章地址
	private final String url;
	// 文章中图片地址
	private final String picUrl;
	// 文章发布时间：精确到秒，10位数字
	private final Date timestamp;
	// 公共号名称
	private final String weChatName;
	// 公共号OpenID
	private final String weChatOpenId;
	// 文章内容
	private final String content;

	public WeChatArticle(Builder builder) {
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

		private String aid = "";
		private String title = "";
		private String url = "";
		private String picUrl = "";
		private Date timestamp;
		private String weChatName = "";
		private String weChatOpenId = "";
		private String content = "";

		public Builder(String aid, String title, String url) {
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

		public WeChatArticle build() {
			return new WeChatArticle(this);
		}

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

	@Override
	public String toString() {
		return "WeChatArticle:[aid=" + aid + ",title=" + title + ",url=" + url + ",picUrl=" + picUrl + ",timestamp="
				+ timestamp + ",weChatName=" + weChatName + ",weChatOpenId=" + weChatOpenId + ",content=" + content
				+ "]";
	}

}

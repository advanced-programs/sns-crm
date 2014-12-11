package zx.soft.sns.dao.domain;

/**
 * 微信公共帐号数据模型
 * 
 * @author wgybzb
 *
 */
public class WeChatPublicAccount {

	// 微信号
	private final String wid;
	// 微信名称
	private final String name;
	// Open ID
	private final String openId;
	// 头像地址
	private final String headUrl;
	// 功能介绍
	private final String description;
	// 微信认证
	private final String verifyInfo;
	// 最近文章地址
	private final String lastArticleUrl;

	public WeChatPublicAccount(Builder builder) {
		this.wid = builder.wid;
		this.name = builder.name;
		this.openId = builder.openId;
		this.headUrl = builder.headUrl;
		this.description = builder.description;
		this.verifyInfo = builder.verifyInfo;
		this.lastArticleUrl = builder.lastArticleUrl;
	}

	public static class Builder {

		private String wid = "";
		private String name = "";
		private String openId = "";
		private String headUrl = "";
		private String description = "";
		private String verifyInfo = "";
		private String lastArticleUrl = "";

		public Builder(String wid, String name) {
			this.wid = wid;
			this.name = name;
		}

		public Builder setOpenId(String openId) {
			this.openId = openId;
			return this;
		}

		public Builder setHeadUrl(String headUrl) {
			this.headUrl = headUrl;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setVerifyInfo(String verifyInfo) {
			this.verifyInfo = verifyInfo;
			return this;
		}

		public Builder setLastArticleUrl(String lastArticleUrl) {
			this.lastArticleUrl = lastArticleUrl;
			return this;
		}

		public WeChatPublicAccount build() {
			return new WeChatPublicAccount(this);
		}

	}

	public String getWid() {
		return wid;
	}

	public String getName() {
		return name;
	}

	public String getOpenId() {
		return openId;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public String getDescription() {
		return description;
	}

	public String getVerifyInfo() {
		return verifyInfo;
	}

	public String getLastArticleUrl() {
		return lastArticleUrl;
	}

	@Override
	public String toString() {
		return "WeChatPublicAccount:[wid=" + wid + ",name=" + name + ",openId=" + openId + ",headUrl=" + headUrl
				+ ",description=" + description + ",verifyInfo=" + verifyInfo + ",lastArticleUrl=" + lastArticleUrl
				+ "]";
	}

}

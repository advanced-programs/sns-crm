package zx.soft.sns.parser.domain;

public class WeixinRecord {

	private final String wid;
	private final String name;
	private final String openId;
	private final String headUrl;
	private final String description;
	private final String verifyInfo;

	@Override
	public String toString() {
		return "WeixinRecord:[wid=" + wid + ",name=" + name + ",openId=" + openId + ",headUrl=" + headUrl
				+ ",description=" + description + ",verifyInfo=" + verifyInfo + "]";
	}

	public WeixinRecord(Builder builder) {
		this.wid = builder.wid;
		this.name = builder.name;
		this.openId = builder.openId;
		this.headUrl = builder.headUrl;
		this.description = builder.description;
		this.verifyInfo = builder.verifyInfo;
	}

	public static class Builder {

		private String wid = "";
		private String name = "";
		private String openId = "";
		private String headUrl = "";
		private String description = "";
		private String verifyInfo = "";

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

		public WeixinRecord build() {
			return new WeixinRecord(this);
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

}

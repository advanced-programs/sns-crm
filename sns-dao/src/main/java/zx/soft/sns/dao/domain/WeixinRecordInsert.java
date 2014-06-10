package zx.soft.sns.dao.domain;

public class WeixinRecordInsert {

	private final String tablename;
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

	public WeixinRecordInsert(Builder builder) {
		this.tablename = builder.tablename;
		this.wid = builder.wid;
		this.name = builder.name;
		this.openId = builder.openId;
		this.headUrl = builder.headUrl;
		this.description = builder.description;
		this.verifyInfo = builder.verifyInfo;
	}

	public static class Builder {

		private String tablename = "";
		private String wid = "";
		private String name = "";
		private String openId = "";
		private String headUrl = "";
		private String description = "";
		private String verifyInfo = "";

		public Builder(String tablename, String wid, String name) {
			this.tablename = tablename;
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

		public WeixinRecordInsert build() {
			return new WeixinRecordInsert(this);
		}

	}

	public String getTablename() {
		return tablename;
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

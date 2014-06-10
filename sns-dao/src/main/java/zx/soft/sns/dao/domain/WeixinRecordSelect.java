package zx.soft.sns.dao.domain;

public class WeixinRecordSelect {

	private String tablename;
	private String wid;

	public WeixinRecordSelect() {
		//
	}

	public WeixinRecordSelect(String tablename, String wid) {
		this.tablename = tablename;
		this.wid = wid;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

}

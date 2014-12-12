package zx.soft.sns.dao.domain;

public class WeChatSelect {

	private String tablename;
	private String tid;

	public WeChatSelect() {
		//
	}

	public WeChatSelect(String tablename, String tid) {
		super();
		this.tablename = tablename;
		this.tid = tid;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

}

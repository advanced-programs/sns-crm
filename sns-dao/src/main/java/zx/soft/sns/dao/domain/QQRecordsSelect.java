package zx.soft.sns.dao.domain;

public class QQRecordsSelect {

	private String tablename;
	private long qq;

	public QQRecordsSelect() {
		//
	}

	public QQRecordsSelect(String tablename, long qq) {
		this.tablename = tablename;
		this.qq = qq;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public long getQq() {
		return qq;
	}

	public void setQq(long qq) {
		this.qq = qq;
	}

}

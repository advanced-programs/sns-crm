package zx.soft.crm.model;

import java.util.Date;

public class RecordQueryCondition {

	private long uid;

	private long mid;

	private int page = 1;

	private int per_page = 20;

	private Date start_time;

	private Date end_time;

	public Date getEnd_time() {
		return end_time;
	}

	public int getLimit() {
		return per_page;
	}

	public int getOffset() {
		return (page - 1) * per_page;
	}

	public int getPage() {
		return page;
	}

	public int getPer_page() {
		return per_page;
	}

	public long getUid() {
		return uid;
	}

	public Date getStart_time() {
		return start_time;
	}

	public long getMid() {
		return mid;
	}

	public RecordQueryCondition setEnd_time(Date end_time) {
		this.end_time = end_time;
		return this;
	}

	public RecordQueryCondition setPage(int page) {
		this.page = page;
		return this;
	}

	public RecordQueryCondition setPer_page(int per_page) {
		this.per_page = per_page;
		return this;
	}

	public RecordQueryCondition setUid(long uid) {
		this.uid = uid;
		return this;
	}

	public RecordQueryCondition setStart_time(Date start_time) {
		this.start_time = start_time;
		return this;
	}

	public RecordQueryCondition setMid(long mid) {
		this.mid = mid;
		return this;
	}

	@Override
	public String toString() {
		return "PointRecordQueryCondition [uid=" + uid + ", mid=" + mid + ", page=" + page + ", per_page=" + per_page
				+ ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}

}

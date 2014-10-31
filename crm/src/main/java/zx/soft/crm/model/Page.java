package zx.soft.crm.model;

import java.util.List;

public class Page {

	private int page;
	private int per_page;
	private int total;
	private List<? extends Object> datas;

	public Page() {
	}

	public Page(int page, int per_page, int total, List<? extends Object> datas) {
		this.page = page;
		this.per_page = per_page;
		this.total = total;
		this.datas = datas;
	}

	public List<? extends Object> getDatas() {
		return datas;
	}

	public int getPage() {
		return page;
	}

	public int getPer_page() {
		return per_page;
	}

	public int getTotal() {
		return total;
	}

	public void setDatas(List<? extends Object> datas) {
		this.datas = datas;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}

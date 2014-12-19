package zx.soft.sns.parser.domain;

import java.util.List;

/**
 * 调用JS接口返回的文章响应信息
 * 
 * @author wanggang
 *
 */
public class ArticlesResponse {

	private int page;
	private int totalItems;
	private int totalPages;
	private List<String> items;

	public ArticlesResponse() {
		//
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

}

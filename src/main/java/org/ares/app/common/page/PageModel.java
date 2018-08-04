package org.ares.app.common.page;

import java.io.Serializable;

public class PageModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public PageModel(){
		this.curPage=1;
	}
	
	public PageModel(int totalRows, int pageSize) {
		this.total = totalRows;
		this.pageSize = pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
		this.totalPages = totalRows / pageSize;
		if (totalRows % pageSize != 0)
			totalPages++;
		this.curPage = 1;
		if (totalRows == 0)
			this.curPage = 0;
		this.start = 0;
	}
	
	public void refresh(int totalRows,int pageSize){
		this.total = totalRows;
		this.pageSize = pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
		this.totalPages = totalRows / pageSize;
		if (totalRows % pageSize != 0)
			totalPages++;
		if (totalRows == 0)
			this.curPage = 0;
		gotoPage(curPage);
	}
	
	public void refresh(int totalRows){
		refresh(totalRows,this.pageSize);
	}

	public void first() {
		curPage = 1;
		start = 0;
	}

	public void last() {
		curPage = totalPages;
		start = (curPage - 1) * pageSize;
	}

	public void previous() {
		if (curPage == 1)
			return;
		start = (--curPage - 1) * pageSize;
	}

	public void next() {
		if (curPage == totalPages)
			return;
		start = (++curPage - 1) * pageSize;
	}

	public void gotoPage(int toPage) {
		curPage = toPage;
		if (curPage > totalPages)
			curPage = totalPages;
		if (curPage < 1)
			curPage = 1;
		start = (curPage - 1) * pageSize;
	}

	public boolean isFirst() {
		return curPage == 1;
	}

	public boolean isLast() {
		return curPage == totalPages;
	}

	public boolean isPrev() {
		return curPage > 1;
	}

	public boolean isNext() {
		return curPage < totalPages;
	}
	
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int startRow) {
		this.start = startRow;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
		if(pageNumber!=null && pageNumber.matches("^\\d+$"))
			this.curPage=Integer.parseInt(pageNumber);
	}
	
	public boolean isFirstQuery(){
		return this.getPageNumber()==null;
	}
	
	public int getEnd(){
		return this.start+pageSize>total?total:this.start+pageSize;
	}
	
	private int total; // 总数

	private int pageSize = 10; // 页大小

	private int curPage; // 当前页

	private int totalPages; // 总页数

	private int start; // 起始记录
	
	private String pageNumber;// jeasyui datagrid pageNumber property,verify first query
	
	private final static int DEFAULT_PAGE_SIZE=10;
}
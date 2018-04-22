package test;

public class Student {
	private Integer pageNum;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "Student [pageNum=" + pageNum + "]";
	}

}

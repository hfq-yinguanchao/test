package test;

public class Student {
	private Integer pageNum;
	private Integer pageNum22;

	public Integer getPageNum() {
		return pageNum;
	}

	public Integer getPageNum22() {
		return pageNum22;
	}

	public void setPageNum22(Integer pageNum22) {
		this.pageNum22 = pageNum22;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "Student [pageNum=" + pageNum + "]";
	}

}

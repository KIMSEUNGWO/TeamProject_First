package july.lease.domain;

public class Criteria {

    private int page;
    private int perPageNum;
    private int pageStart;
    private String search;
    private String startDate;
    private String endDate;
    private String categoryId;
    private long startRow;
    private long endRow;
    private String categoryName;

    public Criteria() {
        page = 1;
        perPageNum = 12;
        setPageStart();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page <= 0)
            page = 1;

        this.page = page;
        setPageStart(); // 페이지 번호가 변경되면 startRow도 업데이트
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        if (perPageNum <= 0 || perPageNum > 100)
            perPageNum = 12;

        this.perPageNum = perPageNum;
        setPageStart(); // 페이지 당 개수가 변경되면 startRow도 업데이트
    }

    public int getPageStart() {
        return pageStart;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public long getStartRow() {
        return startRow;
    }

    public long getEndRow() {
        return endRow;
    }

    public void setPageStart() {
        this.pageStart = (page - 1) * perPageNum;
        this.startRow = pageStart + 1;
        this.endRow = pageStart + perPageNum;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "page=" + page +
                ", perPageNum=" + perPageNum +
                ", pageStart=" + pageStart +
                ", search='" + search + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                '}';
    }
}
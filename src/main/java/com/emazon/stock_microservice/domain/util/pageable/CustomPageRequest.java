package com.emazon.stock_microservice.domain.util.pageable;



public class CustomPageRequest {
    private int page;
    private int size;
    private boolean ascending; //true for ascending
    private String sortBy;

    public CustomPageRequest(int page, int size, boolean ascending, String sortBy) {
        this.page = page;
        this.size = size;
        this.ascending = ascending;
        this.sortBy = sortBy;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
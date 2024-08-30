package com.emazon.stock_microservice.domain.util.pageable;

public class CustomPageRequest {
    private int page;
    private int size;
    private boolean ascending; //true for ascending

    public CustomPageRequest(int page, int size, boolean ascending) {
        this.page = page;
        this.size = size;
        this.ascending = ascending;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public boolean isAscending() {
        return ascending;
    }
}
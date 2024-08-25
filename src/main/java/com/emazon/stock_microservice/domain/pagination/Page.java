package com.emazon.stock_microservice.domain.pagination;

import com.emazon.stock_microservice.domain.pagination.Sort;

import java.util.List;

public class Page<T> {

    private final List<T> content; // this can be a brand, a category or article
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;
    private final int totalPages;
    private final boolean first;
    private final boolean last;
    private final Sort sort;


    public Page(List<T> content, int pageNumber, int pageSize, long totalElements, Sort sort) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize); // gets the number of pages
        this.first = pageNumber == 0;
        this.last = pageNumber == totalPages -1;
        this.sort = sort;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public Sort getSort() {
        return sort;
    }
}

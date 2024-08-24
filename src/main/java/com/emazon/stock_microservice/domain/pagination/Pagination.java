package com.emazon.stock_microservice.domain.pagination;

import java.util.List;

public class Pagination<T> {

    private List<T> data;
    private int totalElements;
    private int totalPages;
    private int currentPage;
    private boolean ascending;
    private boolean empty;

    public Pagination(List<T> data, boolean empty, boolean ascending, int currentPage, int totalPages, int totalElements) {
        this.data = data;
        this.empty = empty;
        this.ascending = ascending;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}

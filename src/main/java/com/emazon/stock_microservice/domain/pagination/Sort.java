package com.emazon.stock_microservice.domain.pagination;

public class Sort {

    private final boolean sorted;
    private final boolean unsorted;

    public Sort(boolean sorted) {
        this.sorted = sorted;
        this.unsorted = !sorted;
    }



}


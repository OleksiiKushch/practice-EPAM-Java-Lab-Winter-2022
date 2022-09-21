package com.epam.task13.util;

public class PagePaginationData {
    public static final int DEFAULT_PAGE_SIZE = 4;
    public static final int DEFAULT_START_PAGE_NUMBER = 1;
    public static final int SHIFT = 0;
    private int pageSize;
    private int pageNumber;
    private int numberOfPages;
    private int minPossiblePageNumber;
    private int maxPossiblePageNumber;

    public PagePaginationData() {
        pageSize = DEFAULT_PAGE_SIZE;
        pageNumber = DEFAULT_START_PAGE_NUMBER;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getMinPossiblePageNumber() {
        return minPossiblePageNumber;
    }

    public void setMinPossiblePageNumber(int minPossiblePageNumber) {
        this.minPossiblePageNumber = minPossiblePageNumber;
    }

    public int getMaxPossiblePageNumber() {
        return maxPossiblePageNumber;
    }

    public void setMaxPossiblePageNumber(int maxPossiblePageNumber) {
        this.maxPossiblePageNumber = maxPossiblePageNumber;
    }

    @Override
    public String toString() {
        return "PagePaginationData{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", numberOfPages=" + numberOfPages +
                ", minPossiblePageNumber=" + minPossiblePageNumber +
                ", maxPossiblePageNumber=" + maxPossiblePageNumber +
                '}';
    }

    public void processAuxiliaryParameters(int totalNumberOfItems) {
        minPossiblePageNumber = Math.max(pageNumber - SHIFT, 1);
        numberOfPages = (int) Math.ceil((double) totalNumberOfItems / (double) pageSize);
        maxPossiblePageNumber = Math.min(pageNumber + SHIFT, numberOfPages);
    }
}

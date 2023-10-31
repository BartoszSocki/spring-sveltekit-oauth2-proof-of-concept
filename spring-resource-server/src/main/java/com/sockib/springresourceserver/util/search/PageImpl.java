package com.sockib.springresourceserver.util.search;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class PageImpl<T> implements Page<T> {

    private final List<T> content;
    private final Long currentPage;
    private final Long pageSize;
    private final Long total;

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public Long getPage() {
        return currentPage;
    }

    @Override
    public Long getTotal() {
        return total;
    }

    @Override
    public Long getPageSize() {
        return pageSize;
    }

    @Override
    public Long getPages() {
        return (long) Math.ceil((double) total / pageSize);
    }

    @Override
    public Boolean isFirstPage() {
        return 0 == currentPage;
    }

    @Override
    public Boolean isLastPage() {
        return Objects.equals(getPages(), 1 + currentPage);
    }
}

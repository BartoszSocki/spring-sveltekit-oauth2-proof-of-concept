package com.sockib.springresourceserver.util.search.page;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SimplePageImpl<T> implements SimplePage<T> {

    private final List<T> content;
    private final Boolean isFirstPage;
    private final Boolean isLastPage;

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public Boolean isFirstPage() {
        return isFirstPage;
    }

    @Override
    public Boolean isLastPage() {
        return isLastPage;
    }

}

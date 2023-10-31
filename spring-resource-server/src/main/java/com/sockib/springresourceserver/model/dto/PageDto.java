package com.sockib.springresourceserver.model.dto;

import com.sockib.springresourceserver.util.search.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDto<T> {

    private List<T> content;
    private Long pageNumber;
    private Boolean isLastPage;
    private Boolean isFirstPage;

    public PageDto(Page<T> page) {
        this.content = page.getContent();
        this.pageNumber = page.getPage();
        this.isLastPage = page.isLastPage();
        this.isFirstPage = page.isFirstPage();
    }
}

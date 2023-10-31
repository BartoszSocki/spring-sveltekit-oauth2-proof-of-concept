package com.sockib.springresourceserver.util.search;

import java.util.List;

public interface Page<T> {

    List<T> getContent();
    Long getPage();
    Long getTotal();
    Long getPageSize();
    Long getPages();
    Boolean isFirstPage();
    Boolean isLastPage();

}

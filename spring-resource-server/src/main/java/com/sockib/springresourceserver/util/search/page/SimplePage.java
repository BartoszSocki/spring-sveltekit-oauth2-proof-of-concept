package com.sockib.springresourceserver.util.search.page;

import java.util.List;

public interface SimplePage<T> {

    List<T> getContent();

    Boolean isFirstPage();

    Boolean isLastPage();

}

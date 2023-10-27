package com.sockib.springresourceserver.util.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Page {

    private Integer offset;
    private Integer limit;

    public static Page of(Integer offset, Integer limit) {
        return Page.builder()
                .offset(offset)
                .limit(limit)
                .build();
    }

}

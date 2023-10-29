package com.sockib.springresourceserver.util.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
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

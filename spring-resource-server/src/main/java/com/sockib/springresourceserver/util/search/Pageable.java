package com.sockib.springresourceserver.util.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Pageable {

    private Integer offset;
    private Integer limit;

    public static Pageable of(Integer offset, Integer limit) {
        return Pageable.builder()
                .offset(offset)
                .limit(limit)
                .build();
    }

}

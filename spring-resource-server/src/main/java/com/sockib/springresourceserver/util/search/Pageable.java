package com.sockib.springresourceserver.util.search;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Pageable {

    @PositiveOrZero
    private Long offset;

    @Positive
    private Long limit;

    public static Pageable of(Long offset, Long limit) {
        return Pageable.builder()
                .offset(offset)
                .limit(limit)
                .build();
    }

    public Integer getPage() {
        return (int) Math.ceil((double) offset / limit);
    }

}

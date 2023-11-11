package com.sockib.springresourceserver.model.dto.converter;

public interface ToDtoConverter<T, U> {

    U convert(T t);

}

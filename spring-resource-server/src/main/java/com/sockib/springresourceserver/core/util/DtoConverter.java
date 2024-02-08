package com.sockib.springresourceserver.model.dto.converter;

public interface DtoConverter<T, U> {

    U convert(T t);

}

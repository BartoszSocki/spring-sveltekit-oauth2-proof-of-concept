package com.sockib.springresourceserver.core.util;

public interface DtoConverter<T, U> {

    U convert(T t);

}

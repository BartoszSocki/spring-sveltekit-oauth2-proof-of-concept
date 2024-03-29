package com.sockib.springresourceserver.model.dto.validation;

public final class RequestValidation {

    public static final String WITHOUT_INVALID_CHARACTERS = "^[\\s\\w\\d\\!@\\&,\\./\\?;\\:\\[\\]\\{\\}\\(\\)\\+\\=_\\-]+$";
    public static final String SUPPORTED_CURRENCIES = "^USD$";
    public static final String POSTAL_CODE = "^[0-9]{2}-[0-9]{3}$";

}
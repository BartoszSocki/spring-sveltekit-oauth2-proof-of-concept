package com.sockib.springresourceserver.model.value;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;


@Data
@Embeddable
public class Address {

    private String addressLine;
    private String country;
    private String city;
    private String postalCode;

}

package com.sockib.springresourceserver.model.valueobject;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Embeddable
public class Address {

    private String address;

}

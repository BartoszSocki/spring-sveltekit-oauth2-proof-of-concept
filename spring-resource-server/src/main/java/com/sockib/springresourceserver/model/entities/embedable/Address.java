package com.sockib.springresourceserver.model.entities.embedable;

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

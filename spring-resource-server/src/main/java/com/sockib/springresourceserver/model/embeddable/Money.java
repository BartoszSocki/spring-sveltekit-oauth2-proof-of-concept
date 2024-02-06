package com.sockib.springresourceserver.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Embeddable
public class Money implements Cloneable {

    private Double amount;
    private String currency;

    @Override
    public Money clone() {
        try {
            return (Money) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}

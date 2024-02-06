package com.sockib.springresourceserver.model.value;

import com.sockib.springresourceserver.exception.MixedCurrenciesException;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Money implements Cloneable {

    private Double amount;
    private String currency;

    private Money(Double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money USD(double amount) {
        return new Money(amount, "USD");
    }

    public Money add(Money money) {
        if (!this.currency.equals(money.getCurrency())) {
            throw new MixedCurrenciesException("cannot add " + currency + " and " + money.getCurrency() + " together");
        }
        return new Money(this.getAmount() + money.getAmount(), this.currency);
    }

    public Money subtract(Money money) {
        if (!this.currency.equals(money.getCurrency())) {
            throw new MixedCurrenciesException("cannot add " + currency + " and " + money.getCurrency() + " together");
        }
        return new Money(this.getAmount() - money.getAmount(), this.currency);
    }

    public Money multiply(double factor) {
        return new Money(factor * this.getAmount(), this.currency);
    }

    @Override
    public Money clone() {
        try {
            return (Money) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}

package com.sockib.springresourceserver.model.dto.input;

import com.sockib.springresourceserver.model.entity.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInput {

    @NotBlank
    private String addressLine;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String postalCode;

    public Address toAddress() {
        var address = new Address();
        address.setAddressLine(addressLine);
        address.setCountry(country);
        address.setCity(city);
        address.setPostalCode(postalCode);

        return address;
    }

}

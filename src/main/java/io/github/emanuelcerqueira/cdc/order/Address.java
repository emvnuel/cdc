package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.common.Default;
import io.github.emanuelcerqueira.cdc.location.Country;
import io.github.emanuelcerqueira.cdc.location.State;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @NotEmpty
    private String addressLine1;

    @NotEmpty
    private String addressLine2;

    @NotEmpty
    private String city;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String zipCode;

    public String getFormattedAddress() {
        return addressLine1 + "\n" +
                addressLine2 + "\n" +
                city + ", "+state()+" "+ zipCode+ "\n" +
                country.getName() + "\n" +
                phoneNumber;
    }

    private String state() {
        return state != null ? state.getName():"";
    }

    @Default
    public Address(@NotEmpty String addressLine1,
                   @NotEmpty String addressLine2,
                   @NotEmpty String city,
                   @NotNull Country country,
                   State state,
                   @NotEmpty String phoneNumber,
                   @NotEmpty String zipCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.country = country;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
    }
}

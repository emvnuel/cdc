package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.common.validation.CPFCNPJ;
import io.github.emanuelcerqueira.cdc.common.validation.ExistsValue;
import io.github.emanuelcerqueira.cdc.coupon.Coupon;
import io.github.emanuelcerqueira.cdc.location.Country;
import io.github.emanuelcerqueira.cdc.location.State;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Value
@OrderInsert
public class OrderRequest {

    @NotNull
    @Email
    String email;

    @NotEmpty
    String name;

    @NotEmpty
    String surname;

    @NotEmpty
    @CPFCNPJ
    String cpfCnpj;

    @NotEmpty
    String addressLine1;

    @NotEmpty
    String addressLine2;

    @NotEmpty
    String city;

    @NotNull
    @ExistsValue(fieldName = "id", domainClass = Country.class, message = "A country with the given id does not exist")
    Long countryId;

    @ExistsValue(fieldName = "id", domainClass = State.class, message = "A state with the given id does not exist")
    Long stateId;

    @NotEmpty
    String phoneNumber;

    @NotEmpty
    String zipCode;

    @NotNull
    @DecimalMin(value = "0.01")
    BigDecimal total;

    @NotNull
    @Size(min = 1)
    @Valid
    List<CartItemRequest> items;

    @ExistsValue(fieldName = "code", domainClass = Coupon.class, message = "The given coupon code does not exist")
    String coupon;
}

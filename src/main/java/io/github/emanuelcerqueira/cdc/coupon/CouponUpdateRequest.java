package io.github.emanuelcerqueira.cdc.coupon;

import lombok.Value;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class CouponUpdateRequest implements CouponUpdate {

    @NotEmpty
    String code;

    @NotNull
    @Positive
    BigDecimal percentageDiscount;

    @NotNull
    @Future
    LocalDate expirationDate;
}

package io.github.emanuelcerqueira.cdc.coupon;

import io.github.emanuelcerqueira.cdc.common.validation.UniqueValue;
import lombok.Value;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class CouponRequest {

    @NotEmpty
    @UniqueValue(fieldName = "code", domainClass = Coupon.class, message = "This code already exits.")
    String code;

    @NotNull
    @Positive
    BigDecimal percentageDiscount;

    @NotNull
    @Future
    LocalDate expirationDate;
    
}

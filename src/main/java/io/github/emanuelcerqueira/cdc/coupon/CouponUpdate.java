package io.github.emanuelcerqueira.cdc.coupon;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CouponUpdate {

    String getCode();
    BigDecimal getPercentageDiscount();
    LocalDate getExpirationDate();
}

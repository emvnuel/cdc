package io.github.emanuelcerqueira.cdc.coupon;

import io.github.emanuelcerqueira.cdc.common.configuration.MapstructConfig;
import org.mapstruct.Mapper;

@Mapper(
        config = MapstructConfig.class,
        componentModel = "spring"
)
public interface CouponMapper {

    Coupon mapToCoupon(CouponRequest couponRequest);

}

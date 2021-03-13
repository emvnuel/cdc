package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.common.configuration.MapstructConfig;
import io.github.emanuelcerqueira.cdc.coupon.CouponService;
import io.github.emanuelcerqueira.cdc.location.CountryService;
import io.github.emanuelcerqueira.cdc.location.StateService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        config = MapstructConfig.class,
        uses = {CartItemMapper.class, CountryService.class, StateService.class, CouponService.class}
)
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "address.addressLine1", source = "orderRequest.addressLine1"),
            @Mapping(target = "address.addressLine2", source = "orderRequest.addressLine2"),
            @Mapping(target = "address.city", source = "orderRequest.city"),
            @Mapping(target = "address.country", source = "orderRequest.countryId"),
            @Mapping(target = "address.state", source = "orderRequest.stateId"),
            @Mapping(target = "address.phoneNumber", source = "orderRequest.phoneNumber"),
            @Mapping(target = "address.zipCode", source = "orderRequest.zipCode"),
    })
    Order mapToOrder(OrderRequest orderRequest);

    @Mappings({
            @Mapping(target = "address", source = "order.address.formattedAddress")
    })
    OrderResponse mapToOrderResponse(Order order);

}

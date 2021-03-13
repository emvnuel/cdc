package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.common.AbstractService;
import io.github.emanuelcerqueira.cdc.common.exceptions.BusinessException;
import io.github.emanuelcerqueira.cdc.coupon.Coupon;
import io.github.emanuelcerqueira.cdc.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService extends AbstractService<Order, OrderRepository> {

    private final CouponRepository couponRepository;

    @Transactional
    public Order save(Order order, Optional<String> optionalCouponCode) {

        if (!order.isClientTotalEqualToServerTotal()) {
            throw new BusinessException("The total calculated on the server mismatches the client total");
        }
        optionalCouponCode.ifPresent(couponCode -> {
            Optional<Coupon> couponOptional = couponRepository.findByCode(couponCode);
            couponOptional.ifPresent(coupon -> order.applyCoupon(coupon));
        });

        return super.save(order);
    }
}

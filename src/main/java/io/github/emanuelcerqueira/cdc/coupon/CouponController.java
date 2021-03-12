package io.github.emanuelcerqueira.cdc.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponMapper couponMapper;
    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<Coupon> save(@Valid @RequestBody CouponRequest couponRequest) {

        Coupon coupon = couponMapper.mapToCoupon(couponRequest);
        Coupon persistedCoupon = couponService.save(coupon);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(persistedCoupon.getId()).toUri();

        return ResponseEntity.created(uri).body(persistedCoupon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coupon> update(@PathVariable("id") Long id,
                                 @Valid @RequestBody CouponUpdateRequest couponRequest) {
        Coupon updatedCoupon = couponService.update(id, couponRequest);
        return ResponseEntity.ok(updatedCoupon);
    }

}

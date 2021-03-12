package io.github.emanuelcerqueira.cdc.coupon;

import io.github.emanuelcerqueira.cdc.common.AbstractService;
import io.github.emanuelcerqueira.cdc.common.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService extends AbstractService<Coupon, CouponRepository> {

    @Transactional
    public Coupon update(Long id, CouponUpdate couponUpdate) {
        Coupon coupon = findById(id);
        coupon.update(couponUpdate);
        return repository.save(coupon);
    }

    @Transactional(readOnly = true)
    public Coupon findByCode(String code) {
        return repository.findByCode(code)
                    .orElseThrow(() -> new ObjectNotFoundException("The coupon with the given code does not exist"));
    }

}

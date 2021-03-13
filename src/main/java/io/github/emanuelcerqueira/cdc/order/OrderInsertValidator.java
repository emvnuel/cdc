package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.common.controller.advice.FieldMessage;
import io.github.emanuelcerqueira.cdc.coupon.Coupon;
import io.github.emanuelcerqueira.cdc.coupon.CouponRepository;
import io.github.emanuelcerqueira.cdc.location.State;
import io.github.emanuelcerqueira.cdc.location.StateRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderInsertValidator implements ConstraintValidator<OrderInsert, OrderRequest> {

    private final StateRepository stateRepository;
    private final CouponRepository couponRepository;

    @Override
    public void initialize(OrderInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(OrderRequest orderRequest, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        List<State> statesOfTheCountry = stateRepository.findAllByCountryId(orderRequest.getCountryId());
        boolean countryHasStates = !statesOfTheCountry.isEmpty();
        boolean stateIsFilled = orderRequest.getStateId() != null;

        if (countryHasStates && !stateIsFilled)
            list.add(new FieldMessage("stateId", "State is required when a country has states"));

        if (orderRequest.getCoupon() != null) {

            Optional<Coupon> couponOptional = this.couponRepository.findByCode(orderRequest.getCoupon());

            couponOptional.ifPresent(value -> {
                Coupon coupon = value;
                if (coupon.isExpired())
                    list.add(new FieldMessage("coupon", "The given coupon code is expired"));
            });

        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}

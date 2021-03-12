package io.github.emanuelcerqueira.cdc.coupon;

import io.github.emanuelcerqueira.cdc.common.Default;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_COUPON")
public class Coupon {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column(name = "percentage_discount")
    private BigDecimal percentageDiscount;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Default
    public Coupon(String code, BigDecimal percentageDiscount, LocalDate expirationDate) {
        this.code = code;
        this.percentageDiscount = percentageDiscount;
        this.expirationDate = expirationDate;
    }

    public void update(CouponUpdate couponUpdate) {
        this.code = couponUpdate.getCode();
        this.percentageDiscount = couponUpdate.getPercentageDiscount();
        this.expirationDate = couponUpdate.getExpirationDate();
    }
}

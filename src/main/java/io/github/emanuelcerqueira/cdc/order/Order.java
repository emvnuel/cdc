package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.common.Default;
import io.github.emanuelcerqueira.cdc.common.validation.CPFCNPJ;
import io.github.emanuelcerqueira.cdc.coupon.Coupon;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_ORDER")
public class Order {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    @CPFCNPJ
    private String cpfCnpj;

    @NotNull
    @Embedded
    @Valid
    private Address address;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal total;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal discounts;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal totalFinal;

    @NotNull
    @Size(min = 1)
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    @Valid
    private List<CartItem> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Default
    public Order(@NotNull @Email String email,
                 @NotEmpty String name,
                 @NotEmpty String surname,
                 @NotEmpty String cpfCnpj,
                 @NotNull @Valid Address address,
                 @NotNull @DecimalMin(value = "0.01") BigDecimal total,
                 @NotNull @Size(min = 1) @Valid List<CartItem> items) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
        this.total = total;
        this.items = items;
        this.discounts = BigDecimal.ZERO;
        this.totalFinal = this.total;
    }

    public void applyCoupon(Coupon coupon) {
        Assert.isTrue(!coupon.isExpired(), "Cannot apply a expired coupon");
        this.coupon = coupon;
        this.discounts = this.total.multiply(this.coupon.getPercentageDiscount());
        this.totalFinal = this.total.subtract(this.discounts);
    }

    private BigDecimal calculateTotal() {
      return items.stream()
              .map(CartItem::getSubtotal)
              .reduce(BigDecimal::add)
              .orElse(BigDecimal.ZERO);
    }

    public boolean isClientTotalEqualToServerTotal() {
        Assert.notNull(this.total, "Total can not be null");
        return this.getTotal().compareTo(calculateTotal()) == 0;
    }

}

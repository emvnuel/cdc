package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.book.Book;
import io.github.emanuelcerqueira.cdc.common.Default;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_CART_ITEM")
public class CartItem {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Min(1)
    private Integer quantity;

    public BigDecimal getSubtotal() {
        return book.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Default
    public CartItem(@NotNull Book book, @NotNull Order order, @Min(1) Integer quantity) {
        this.book = book;
        this.order = order;
        this.quantity = quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

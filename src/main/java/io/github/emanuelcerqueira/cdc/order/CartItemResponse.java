package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.book.BookResponse;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CartItemResponse {

    BookResponse book;
    Integer quantity;
    BigDecimal subtotal;
}

package io.github.emanuelcerqueira.cdc.book;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class BookResponse {

    Long id;
    String title;
    BigDecimal price;

}

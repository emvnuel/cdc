package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.book.Book;
import io.github.emanuelcerqueira.cdc.common.validation.ExistsValue;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
public class CartItemRequest {

    @NotNull
    @ExistsValue(fieldName = "id", domainClass = Book.class, message = "A book with the given id does not exist")
    Long bookId;

    @NotNull
    @Min(1)
    Integer quantity;
}

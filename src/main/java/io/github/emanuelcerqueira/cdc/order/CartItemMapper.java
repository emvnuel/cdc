package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.book.BookMapper;
import io.github.emanuelcerqueira.cdc.book.BookService;
import io.github.emanuelcerqueira.cdc.common.configuration.MapstructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        config = MapstructConfig.class,
        uses = {BookMapper.class, BookService.class}
)
public interface CartItemMapper {

    @Mappings({
            @Mapping(target = "book", source = "cartItem.bookId")
    })
    CartItem mapToCartItem(CartItemRequest cartItem);

    @Mappings({
            @Mapping(target = "book", source = "cartItem.book"),
            @Mapping(target = "subtotal", source = "cartItem.subtotal")
    })
    CartItemResponse mapToCartItemResponse(CartItem cartItem);

}

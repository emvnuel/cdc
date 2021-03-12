package io.github.emanuelcerqueira.cdc.book;

import io.github.emanuelcerqueira.cdc.author.AuthorMapper;
import io.github.emanuelcerqueira.cdc.author.AuthorService;
import io.github.emanuelcerqueira.cdc.category.CategoryMapper;
import io.github.emanuelcerqueira.cdc.category.CategoryService;
import io.github.emanuelcerqueira.cdc.common.configuration.MapstructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(
        config = MapstructConfig.class,
        uses = {AuthorService.class, CategoryService.class, CategoryMapper.class, AuthorMapper.class}
)
public interface BookMapper {

    @Mappings({
        @Mapping(target="category", source="bookRequest.categoryId"),
        @Mapping(target="author", source="bookRequest.authorId")
    })
    Book mapToBook(BookRequest bookRequest);

    @Mappings({
            @Mapping(target="category", source="book.category"),
            @Mapping(target="author", source="book.author")
    })
    BookDetailsResponse mapToBookDetailsResponse(Book book);

    BookResponse mapToBookResponse(Book book);
}

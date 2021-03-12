package io.github.emanuelcerqueira.cdc.book;


import io.github.emanuelcerqueira.cdc.author.AuthorDetailsResponse;
import io.github.emanuelcerqueira.cdc.category.CategoryResponse;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class BookDetailsResponse {

    Long id;
    String title;
    String summary;
    String contents;
    BigDecimal price;
    Integer numberOfPages;
    String isbn;
    LocalDate publicationDate;
    CategoryResponse category;
    AuthorDetailsResponse author;

}

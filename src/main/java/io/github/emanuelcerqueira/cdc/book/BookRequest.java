package io.github.emanuelcerqueira.cdc.book;

import io.github.emanuelcerqueira.cdc.author.Author;
import io.github.emanuelcerqueira.cdc.category.Category;
import io.github.emanuelcerqueira.cdc.common.validation.ExistsValue;
import io.github.emanuelcerqueira.cdc.common.validation.UniqueValue;
import lombok.Value;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class BookRequest {

    @NotEmpty
    @UniqueValue(domainClass = Book.class, fieldName = "title", message = "A book with this title already exists")
    String title;

    @NotEmpty
    @Length(max = 500)
    String summary;

    String contents;

    @NotNull
    @Min(20)
    BigDecimal price;

    @NotNull
    @Min(100)
    Integer numberOfPages;

    @ISBN(type = ISBN.Type.ANY)
    @UniqueValue(domainClass = Book.class, fieldName = "isbn", message = "A book with this isbn already exists")
    String isbn;

    @Future
    LocalDate publicationDate;

    @NotNull
    @ExistsValue(domainClass = Category.class, fieldName = "id", message = "A category with the given id does not exist")
    Long categoryId;

    @NotNull
    @ExistsValue(domainClass = Author.class, fieldName = "id",  message = "An author with the given id does not exist")
    Long authorId;

}

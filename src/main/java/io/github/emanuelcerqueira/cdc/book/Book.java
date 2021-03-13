package io.github.emanuelcerqueira.cdc.book;

import io.github.emanuelcerqueira.cdc.author.Author;
import io.github.emanuelcerqueira.cdc.category.Category;
import io.github.emanuelcerqueira.cdc.common.Default;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_BOOK")
public class Book {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String title;

    @NotEmpty
    @Length(max = 500)
    @Column(length = 500)
    private String summary;

    private String contents;

    @NotNull
    @Min(20)
    private BigDecimal price;

    @NotNull
    @Min(100)
    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @ISBN
    @NaturalId
    private String isbn;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(@NotEmpty String title,
                @NotEmpty @Length(max = 500) String summary,
                String contents,
                @NotNull @Min(20) BigDecimal price,
                @NotNull @Min(100) Integer numberOfPages,
                @ISBN String isbn,
                LocalDate publicationDate,
                @NotNull Category category,
                @NotNull Author author) {
        this.title = title;
        this.summary = summary;
        this.contents = contents;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.category = category;
        this.author = author;
    }
}

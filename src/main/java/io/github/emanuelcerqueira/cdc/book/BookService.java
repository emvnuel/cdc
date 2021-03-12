package io.github.emanuelcerqueira.cdc.book;

import io.github.emanuelcerqueira.cdc.common.AbstractService;
import io.github.emanuelcerqueira.cdc.common.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractService<Book, BookRepository> {

    public Book findByIsbn(String isbn) {
        return repository.findByIsbn(isbn)
                .orElseThrow(() -> new ObjectNotFoundException("A book with the given isbn does not exist"));
    }
}

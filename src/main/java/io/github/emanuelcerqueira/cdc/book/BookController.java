package io.github.emanuelcerqueira.cdc.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDetailsResponse> save(@Valid @RequestBody BookRequest bookRequest) {
        Book book = bookMapper.mapToBook(bookRequest);
        Book persistedBook = bookService.save(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(persistedBook.getId()).toUri();
        BookDetailsResponse response = bookMapper.mapToBookDetailsResponse(persistedBook);

        return ResponseEntity.created(uri).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsResponse> findById(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        BookDetailsResponse response = bookMapper.mapToBookDetailsResponse(book);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-isbn/{isbn}")
    public ResponseEntity<BookDetailsResponse> findByIsbn(@PathVariable("isbn") String isbn) {
        Book book = bookService.findByIsbn(isbn);
        BookDetailsResponse response = bookMapper.mapToBookDetailsResponse(book);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> findAll(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<BookResponse> response = bookService.findAll(page, linesPerPage, orderBy, direction)
                .map(bookMapper::mapToBookResponse);

        return ResponseEntity.ok(response);
    }

}

package io.github.emanuelcerqueira.cdc.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @PostMapping
    public ResponseEntity<AuthorDetailsResponse> save(@Valid @RequestBody AuthorRequest authorRequest) {
        Author newAuthor = authorMapper.mapToAuthor(authorRequest);
        Author persistedAuthor = authorService.save(newAuthor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(persistedAuthor.getId()).toUri();
        AuthorDetailsResponse response = authorMapper.mapToAuthorDetailsResponse(persistedAuthor);

        return ResponseEntity.created(uri).body(response);
    }

}

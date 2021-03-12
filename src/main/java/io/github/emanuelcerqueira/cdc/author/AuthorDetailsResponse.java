package io.github.emanuelcerqueira.cdc.author;

import lombok.Value;

@Value
public class AuthorDetailsResponse {

    Long id;
    String name;
    String email;
    String bio;

}

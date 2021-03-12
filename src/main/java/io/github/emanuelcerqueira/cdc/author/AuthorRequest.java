package io.github.emanuelcerqueira.cdc.author;

import io.github.emanuelcerqueira.cdc.common.validation.UniqueValue;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Value
public class AuthorRequest {

    @NotEmpty
    String name;

    @NotEmpty
    @Email
    @UniqueValue(
            domainClass = Author.class,
            fieldName = "email",
            message = "An author with the given email already exists"
    )
    String email;

    @NotEmpty
    @Length(max = 400)
    String bio;

}

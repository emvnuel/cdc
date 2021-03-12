package io.github.emanuelcerqueira.cdc.author;

import io.github.emanuelcerqueira.cdc.common.configuration.MapstructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface AuthorMapper {

    Author mapToAuthor(AuthorRequest authorRequest);
    AuthorDetailsResponse mapToAuthorDetailsResponse(Author author);

}

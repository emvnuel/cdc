package io.github.emanuelcerqueira.cdc.location;

import io.github.emanuelcerqueira.cdc.common.validation.ExistsValue;
import io.github.emanuelcerqueira.cdc.common.validation.UniqueValue;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class StateRequest {

    @NotEmpty
    @UniqueValue(
            domainClass = State.class,
            fieldName = "name",
            message = "A state with the given name already exists"
    )
    String name;

    @NotNull
    @ExistsValue(
            domainClass = Country.class,
            fieldName = "id",
            message = "A country with the given id does not exist"
    )
    Long countryId;

}

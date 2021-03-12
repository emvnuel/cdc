package io.github.emanuelcerqueira.cdc.location;

import io.github.emanuelcerqueira.cdc.common.validation.UniqueValue;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class CountryRequest {

    @NotEmpty
    @UniqueValue(
            domainClass = Country.class,
            fieldName = "name",
            message = "A country with the given name already exists"
    )
    String name;

}

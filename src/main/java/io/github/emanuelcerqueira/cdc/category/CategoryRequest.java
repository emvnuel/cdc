package io.github.emanuelcerqueira.cdc.category;

import io.github.emanuelcerqueira.cdc.common.validation.UniqueValue;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class CategoryRequest {

    @NotEmpty
    @UniqueValue(
            domainClass = Category.class,
            fieldName = "name",
            message = "A category with the given name already exists"
    )
    String name;

}

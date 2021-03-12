package io.github.emanuelcerqueira.cdc.category;

import io.github.emanuelcerqueira.cdc.common.configuration.MapstructConfig;
import org.mapstruct.Mapper;

@Mapper(
        config = MapstructConfig.class,
        componentModel = "spring"
)
public interface CategoryMapper {

    Category mapToCategory(CategoryRequest categoryRequest);
    CategoryResponse mapToCategoryResponse(Category category);
}

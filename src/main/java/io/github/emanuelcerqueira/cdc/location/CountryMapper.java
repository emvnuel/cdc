package io.github.emanuelcerqueira.cdc.location;

import io.github.emanuelcerqueira.cdc.common.configuration.MapstructConfig;
import org.mapstruct.Mapper;

@Mapper(
        config = MapstructConfig.class,
        componentModel = "spring"
)
public interface CountryMapper {

    Country mapToCountry(CountryRequest countryRequest);
}

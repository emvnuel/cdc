package io.github.emanuelcerqueira.cdc.location;

import io.github.emanuelcerqueira.cdc.common.configuration.MapstructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapstructConfig.class, uses = CountryService.class)
public interface StateMapper {

    @Mappings({
            @Mapping(target="country", source="stateRequest.countryId")
    })
    State mapToState(StateRequest stateRequest);
}

package io.github.emanuelcerqueira.cdc.location;

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
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final CountryMapper countryMapper;
    private final StateMapper stateMapper;
    private final CountryService countryService;
    private final StateService stateService;

    @PostMapping("/countries")
    public ResponseEntity<Country> save(@Valid @RequestBody CountryRequest countryRequest) {
        Country country = countryMapper.mapToCountry(countryRequest);
        Country persistedCountry = countryService.save(country);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(persistedCountry.getId()).toUri();

        return ResponseEntity.created(uri).body(persistedCountry);
    }

    @PostMapping("/states")
    public ResponseEntity<State> save(@Valid @RequestBody StateRequest stateRequest) {
        State state = stateMapper.mapToState(stateRequest);
        State persistedState = stateService.save(state);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(persistedState.getId()).toUri();

        return ResponseEntity.created(uri).body(persistedState);
    }

}

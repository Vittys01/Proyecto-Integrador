package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Country;
import com.digital.booking.core.port.input.CountryCreationCommand;
import com.digital.booking.core.port.output.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class CountryCreationUseCase implements CountryCreationCommand {

    private final CountryRepository countryRepository;

    public CountryCreationUseCase(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country execute(Data data) {
        Country country = Country.builder()
                .name(data.getName())
                .build();
        return countryRepository.save(country);
    }
}

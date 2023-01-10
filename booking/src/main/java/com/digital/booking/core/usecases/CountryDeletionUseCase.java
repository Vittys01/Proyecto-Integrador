package com.digital.booking.core.usecases;

import com.digital.booking.core.port.input.CountryDeletionCommand;
import com.digital.booking.core.port.output.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class CountryDeletionUseCase implements CountryDeletionCommand {

    private final CountryRepository countryRepository;

    public CountryDeletionUseCase(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void execute(Long id) {
        countryRepository.deleteById(id);
    }
}

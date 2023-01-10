package com.digital.booking.core.usecases;

import com.digital.booking.core.port.input.CityDeletionCommand;
import com.digital.booking.core.port.output.CityRepository;
import org.springframework.stereotype.Component;

@Component
public class CityDeletionUseCase implements CityDeletionCommand {

    private final CityRepository cityRepository;

    public CityDeletionUseCase(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void execute(Long id) {
        cityRepository.deleteById(id);
    }
}

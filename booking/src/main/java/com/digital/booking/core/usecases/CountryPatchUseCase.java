package com.digital.booking.core.usecases;


import com.digital.booking.core.domain.Country;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.CountryPatchCommand;
import com.digital.booking.core.port.output.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CountryPatchUseCase implements CountryPatchCommand {

    private final CountryRepository countryRepository;

    public CountryPatchUseCase(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country execute(Data data) {
        if(data.getId() == null) throw new BusinessException(ErrorCode.MISSING_CATEGORY_ID);
        Optional<Country> CountryFound = countryRepository.getEntityById(data.getId());
        if(CountryFound.isEmpty()) throw new BusinessException(ErrorCode.CATEGORY_TO_UPDATE_NOT_FOUND);
        Country country = Country.builder()
                .id(data.getId())
                .name(data.getName())
                .build();
        return countryRepository.save(country);
    }
}

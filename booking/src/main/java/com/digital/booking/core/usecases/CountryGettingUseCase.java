package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Country;
import com.digital.booking.core.port.input.CountryGettingQuery;
import com.digital.booking.core.port.output.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CountryGettingUseCase implements CountryGettingQuery {

    private CountryRepository countryRepository;

    public CountryGettingUseCase(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Page<Country> execute(Data data) {
        Integer page = data.getPage() == null ? 0 : data.getPage();
        Integer size = data.getSize() == null || data.getSize() < 10 ? 10 : data.getSize();
        return countryRepository.getPaginated(page, size);
    }
}

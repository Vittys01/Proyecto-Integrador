package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.City;
import com.digital.booking.core.port.input.CityGettingQuery;
import com.digital.booking.core.port.output.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CityGettingUseCase implements CityGettingQuery {

    private CityRepository cityRepository;

    public CityGettingUseCase(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Page<City> execute(Data data) {
        Integer page = data.getPage() == null ? 0 : data.getPage();
        Integer size = data.getSize() == null || data.getSize() < 10 ? 10 : data.getSize();
        return cityRepository.getPaginated(page, size);
    }
}

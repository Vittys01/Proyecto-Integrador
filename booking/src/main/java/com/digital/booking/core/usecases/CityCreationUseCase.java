package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.City;
import com.digital.booking.core.domain.Province;
import com.digital.booking.core.port.input.CityCreationCommand;
import com.digital.booking.core.port.output.CityRepository;
import org.springframework.stereotype.Component;

@Component
public class CityCreationUseCase implements CityCreationCommand {

    private final CityRepository cityRepository;

    public CityCreationUseCase(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City execute(Data data) {
        City city = City.builder()
                .name(data.getName())
                .province(Province.builder().id(data.getProvinceId()).build())
                .build();
        return cityRepository.save(city);
    }
}

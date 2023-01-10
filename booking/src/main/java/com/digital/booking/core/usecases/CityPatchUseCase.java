package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.City;
import com.digital.booking.core.domain.Province;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.CityPatchCommand;
import com.digital.booking.core.port.output.CityRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CityPatchUseCase implements CityPatchCommand {

    private final CityRepository cityRepository;

    public CityPatchUseCase(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City execute(Data data) {
        if(data.getId() == null) throw new BusinessException(ErrorCode.MISSING_CATEGORY_ID);
        Optional<City> CityFound = cityRepository.getEntityById(data.getId());
        if(CityFound.isEmpty()) throw new BusinessException(ErrorCode.CATEGORY_TO_UPDATE_NOT_FOUND);
        City city = City.builder()
                .id(data.getId())
                .name(data.getName())
                .province(Province.builder().id(data.getProvinceId()).build())
                .build();
        return cityRepository.patch(city);
    }
}

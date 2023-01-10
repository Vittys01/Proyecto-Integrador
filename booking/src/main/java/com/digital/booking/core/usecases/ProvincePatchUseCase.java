package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Country;
import com.digital.booking.core.domain.Province;
import com.digital.booking.core.port.input.ProvincePatchCommand;
import com.digital.booking.core.port.output.ProvinceRepository;
import org.springframework.stereotype.Component;

@Component
public class ProvincePatchUseCase implements ProvincePatchCommand {

    private final ProvinceRepository provinceRepository;

    public ProvincePatchUseCase(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Province execute(Data data) {
        Province province = Province.builder()
                .id(data.getId())
                .name(data.getName())
                .country(Country.builder().id(data.getCountryId()).build())
                .build();
        return provinceRepository.save(province);
    }
}

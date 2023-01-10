package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Country;
import com.digital.booking.core.domain.Province;
import com.digital.booking.core.port.input.ProvinceCreationCommand;
import com.digital.booking.core.port.output.ProvinceRepository;
import org.springframework.stereotype.Component;

@Component
public class ProvinceCreationUseCase implements ProvinceCreationCommand {

    private final ProvinceRepository provinceRepository;

    public ProvinceCreationUseCase(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Province execute(Data data) {
        Province province = Province.builder()
                .name(data.getName())
                .country(Country.builder().id(data.getCountryId()).build())
                .build();
        return provinceRepository.save(province);
    }
}

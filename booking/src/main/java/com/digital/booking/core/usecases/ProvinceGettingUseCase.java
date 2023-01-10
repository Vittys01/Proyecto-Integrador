package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.Province;
import com.digital.booking.core.port.input.ProvinceGettingQuery;
import com.digital.booking.core.port.output.ProvinceRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProvinceGettingUseCase implements ProvinceGettingQuery {

    private final ProvinceRepository provinceRepository;

    public ProvinceGettingUseCase(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Page<Province> execute(Data data) {
        Integer page = data.getPage() == null ? 0 : data.getPage();
        Integer size = data.getSize() == null || data.getSize() < 10 ? 10 : data.getSize();
        return provinceRepository.getPaginated(page,size);
    }
}
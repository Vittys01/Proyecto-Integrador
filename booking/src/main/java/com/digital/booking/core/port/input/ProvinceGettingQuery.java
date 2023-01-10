package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Province;
import org.springframework.data.domain.Page;
import lombok.Builder;

public interface ProvinceGettingQuery {

    Page<Province> execute(ProvinceGettingQuery.Data data);

    @Builder
    @lombok.Data
    class Data{
        Integer page;
        Integer size;
    }
}

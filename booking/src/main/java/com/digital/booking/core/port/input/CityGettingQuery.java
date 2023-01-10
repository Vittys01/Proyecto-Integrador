package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.City;
import org.springframework.data.domain.Page;
import lombok.Builder;

public interface CityGettingQuery {

    Page<City> execute(CityGettingQuery.Data data);

    @lombok.Data
    @Builder
    class Data{
        Integer page;
        Integer size;
    }
}

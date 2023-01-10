package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Country;
import org.springframework.data.domain.Page;
import lombok.Builder;

public interface CountryGettingQuery {

    Page<Country> execute(CountryGettingQuery.Data data);

    @lombok.Data
    @Builder
    class Data{
        Integer page;
        Integer size;
    }
}

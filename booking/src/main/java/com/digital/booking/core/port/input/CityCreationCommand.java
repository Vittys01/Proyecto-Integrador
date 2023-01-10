package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.City;
import lombok.Builder;

public interface CityCreationCommand {

    City execute(CityCreationCommand.Data data);

    @lombok.Data
    @Builder
    class Data {
        String name;
        Long provinceId;
    }
}

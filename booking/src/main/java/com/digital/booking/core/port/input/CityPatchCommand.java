package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.*;
import lombok.Builder;

public interface CityPatchCommand {

    City execute(CityPatchCommand.Data data);

    @lombok.Data
    @Builder
    class Data{
        private Long id;
        private String name;
        private Long provinceId;
    }
}

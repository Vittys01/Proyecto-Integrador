package com.digital.booking.core.port.input;


import com.digital.booking.core.domain.Province;
import lombok.Builder;

public interface ProvincePatchCommand {

    Province execute(ProvincePatchCommand.Data data);

    @lombok.Data
    @Builder
    class Data{
        private Long id;
        private String name;
        private Long countryId;
    }
}

package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.*;
import lombok.Builder;

public interface CountryPatchCommand {

    Country execute(CountryPatchCommand.Data data);

    @lombok.Data
    @Builder
    class Data{
        private Long id;
        private String name;
    }
}

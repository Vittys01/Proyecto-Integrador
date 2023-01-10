package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Country;
import lombok.Value;
import lombok.Builder;

public interface CountryCreationCommand {

    Country execute(CountryCreationCommand.Data data);

    @Value
    @Builder
    class Data {
        String name;
    }
}

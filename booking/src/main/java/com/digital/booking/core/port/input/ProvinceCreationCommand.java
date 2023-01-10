package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Province;
import lombok.Builder;

public interface ProvinceCreationCommand {

    Province execute(ProvinceCreationCommand.Data data);

    @lombok.Data
    @Builder
    class Data {
        String name;
        Long countryId;
    }
}

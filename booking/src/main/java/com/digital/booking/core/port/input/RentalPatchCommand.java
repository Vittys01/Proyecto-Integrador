package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.*;
import lombok.Builder;

public interface RentalPatchCommand {

    Rental execute(RentalPatchCommand.Data data);

    @lombok.Data
    @Builder
    class Data{
        private Long id;
        private String name;
        private String description;
        private Long categoryId;
        private Long cityId;
        private Integer distance;
    }
}

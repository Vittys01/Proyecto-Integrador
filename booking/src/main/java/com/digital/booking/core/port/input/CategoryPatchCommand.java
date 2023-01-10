package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Category;
import lombok.Builder;

public interface CategoryPatchCommand {

    Category execute(Data data);

    @lombok.Data
    @Builder
    class Data{
        private Long id;
        private String name;
        private String image;
        private String description;
        private Boolean status;
    }
}

package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Category;
import lombok.Value;
import lombok.Builder;

public interface CategoryCreationCommand {

    Category execute(Data data);

    @Value
    @Builder
    class Data{
        String description;
        String image;
        String name;
    }
}

package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.*;
import lombok.Value;
import lombok.Builder;

import java.util.Set;

public interface RentalCreationCommand {

    Rental execute(Data data);

    @Value
    @Builder
    class Data{
        String name;
        String description;
        Long categoryId;
        Long cityId;
        Integer distance;
        Set<Image> images;
        Set<Characteristic> characteristics;
    }
}

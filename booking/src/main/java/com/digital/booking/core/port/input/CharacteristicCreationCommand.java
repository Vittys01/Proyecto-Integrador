package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Category;
import com.digital.booking.core.domain.Characteristic;
import lombok.Value;
import lombok.Builder;

public interface CharacteristicCreationCommand {

    Characteristic execute(Data data);

    @Value
    @Builder
    class Data{
        String title;
        String icon;
    }
}

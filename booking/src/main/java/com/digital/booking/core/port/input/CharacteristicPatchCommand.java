package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Characteristic;
import lombok.Value;
import lombok.Builder;

public interface CharacteristicPatchCommand {

    Characteristic execute(Data data);

    @Value
    @Builder
    class Data{
        Long id;
        String title;
        String icon;
    }
}

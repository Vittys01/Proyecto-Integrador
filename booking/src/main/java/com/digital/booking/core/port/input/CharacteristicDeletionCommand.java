package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Characteristic;
import lombok.Builder;
import lombok.Value;

public interface CharacteristicDeletionCommand {

    Characteristic execute(Data data);

    @Value
    @Builder
    class Data{
        Long id;
    }
}

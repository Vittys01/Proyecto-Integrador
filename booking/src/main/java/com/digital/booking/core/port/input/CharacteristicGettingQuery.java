package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Characteristic;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Page;

public interface CharacteristicGettingQuery {

    Page<Characteristic> execute(Data data);

    @Value
    @Builder
    class Data {
        private Integer page;
        private Integer size;
    }
}

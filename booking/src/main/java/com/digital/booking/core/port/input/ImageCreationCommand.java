package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.Image;
import lombok.Builder;
import lombok.Value;

public interface ImageCreationCommand {

    Image execute(Data data);

    @Value
    @Builder
    class Data {
        String title;
        String url;
        Long rentalId;
    }
}
